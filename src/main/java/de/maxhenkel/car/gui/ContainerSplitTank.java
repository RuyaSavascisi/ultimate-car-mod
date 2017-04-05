package de.maxhenkel.car.gui;

import de.maxhenkel.car.blocks.tileentity.TileEntitySplitTank;
import net.minecraft.inventory.IContainerListener;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;

public class ContainerSplitTank extends ContainerBase{

	//private IInventory playerInv;
	private TileEntitySplitTank tile;
	
	public ContainerSplitTank(IInventory playerInv, TileEntitySplitTank tile) {
		this.tile=tile;
		//this.playerInv=playerInv;
		
		for (int i = 0; i < 3; ++i) {
			for (int j = 0; j < 9; ++j) {
				this.addSlotToContainer(new Slot(playerInv, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
			}
		}

		for (int k = 0; k < 9; ++k) {
			this.addSlotToContainer(new Slot(playerInv, k, 8 + k * 18, 142));
		}
		
	}
	
	private int currentMix;
	private int currentBioDiesel;
	private int currentGlycerin;
	private int timeToGenerate;

	@Override
	public void detectAndSendChanges() {
		super.detectAndSendChanges();

		for (int i = 0; i < this.listeners.size(); ++i) {
			IContainerListener icontainerlistener = listeners.get(i);

			if (currentMix != tile.getField(0)) {
				icontainerlistener.sendProgressBarUpdate(this, 0, tile.getField(0));
			}

			if (currentBioDiesel != tile.getField(1)) {
				icontainerlistener.sendProgressBarUpdate(this, 1, tile.getField(1));
			}

			if (currentGlycerin != tile.getField(2)) {
				icontainerlistener.sendProgressBarUpdate(this, 2, tile.getField(2));
			}
			
			if (timeToGenerate != tile.getField(3)) {
				icontainerlistener.sendProgressBarUpdate(this, 3, tile.getField(3));
			}
		}

		currentMix = tile.getField(0);
		currentBioDiesel = tile.getField(1);
		currentGlycerin = tile.getField(2);
		timeToGenerate = tile.getField(3);
	}

	@Override
	public void addListener(IContainerListener listener) {
		super.addListener(listener);
		listener.sendProgressBarUpdate(this, 0, currentMix);
		listener.sendProgressBarUpdate(this, 1, currentBioDiesel);
		listener.sendProgressBarUpdate(this, 2, currentGlycerin);
		listener.sendProgressBarUpdate(this, 3, timeToGenerate);
	}

	@Override
	public void updateProgressBar(int id, int data) {
		tile.setField(id, data);
	}

	@Override
	protected int getSizeInventory() {
		return 0;
	}

}
