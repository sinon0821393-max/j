package com.Cell_SINON.InstantHealMod.block.fermentation;



import com.Cell_SINON.InstantHealMod.regi.InstantHealModBlocks;
import com.Cell_SINON.InstantHealMod.regi.InstantHealModMenuTypes; // 後で作る
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.items.SlotItemHandler;
import net.minecraft.world.level.Level;

public class FermentationMenu extends AbstractContainerMenu {




    public FermentationMenu(int a, Inventory inventory){
        this(a, inventory, new SimpleContainer(4));
    }

    private Container container;

    public FermentationMenu(int a, Inventory inventory, Container container) {
        super(InstantHealModMenuTypes.FERMENTATION_MENU.get(), a);
        this.container = container;
    }


    @Override
    public ItemStack quickMoveStack(Player player, int a) {
        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = this.slots.get(a);
        if (slot != null && slot.hasItem()) {
            ItemStack itemstack1 = slot.getItem();
            itemstack = itemstack1.copy();
            if (a < this.container.getContainerSize() * 9) {
                if (!this.moveItemStackTo(itemstack1, this.container.getContainerSize() * 9, this.slots.size(), true)) {
                    return ItemStack.EMPTY;
                }
            } else if (!this.moveItemStackTo(itemstack1, 0, this.container.getContainerSize() * 9, false)) {
                return ItemStack.EMPTY;
            }

            if (itemstack1.isEmpty()) {
                slot.setByPlayer(ItemStack.EMPTY);
            } else {
                slot.setChanged();
            }
        }

        return itemstack;
    }


    @Override
    public boolean stillValid(Player player) {
        return this.container.stillValid(player);
    }

}




