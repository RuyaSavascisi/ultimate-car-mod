package de.maxhenkel.car.items;

import de.maxhenkel.car.Main;
import de.maxhenkel.car.entity.car.parts.Part;
import de.maxhenkel.car.entity.car.parts.PartRegistry;
import de.maxhenkel.car.gui.GuiHandler;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

public class ItemNumberPlate extends ItemCarPart {

    public ItemNumberPlate(String name, Part part) {
        super(name, part);
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        super.addInformation(stack, worldIn, tooltip, flagIn);

        String text = getText(stack);

        if (text != null && !text.isEmpty()) {
            tooltip.add(new TextComponentTranslation("tooltip.number_plate_text", text).getFormattedText());
        }
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        ItemStack stack = playerIn.getHeldItem(handIn);
        playerIn.openGui(Main.MODID, GuiHandler.GUI_NUMBER_PLATE, worldIn, (int) playerIn.posX, (int) playerIn.posY, (int) playerIn.posZ);
        return new ActionResult(EnumActionResult.SUCCESS, stack);
    }

    public static void setText(ItemStack stack, String text) {
        if (!stack.hasTagCompound()) {
            stack.setTagCompound(new NBTTagCompound());
        }

        NBTTagCompound compound = stack.getTagCompound();

        compound.setString("plate_text", text);
    }

    public static String getText(ItemStack stack) {
        if (!stack.hasTagCompound()) {
            return "";
        }

        NBTTagCompound compound = stack.getTagCompound();

        if (!compound.hasKey("plate_text")) {
            return "";
        }

        return compound.getString("plate_text");
    }
}
