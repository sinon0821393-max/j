package com.Cell_SINON.InstantHealMod.block.Pyrolysis;

import com.Cell_SINON.InstantHealMod.block.Distiller.DistillerBlockEntity;
import com.Cell_SINON.InstantHealMod.regi.InstantHealModBlocks;
import com.Cell_SINON.InstantHealMod.regi.InstantHealModMenuTypes;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.items.SlotItemHandler;


public class PyrolysisMenu extends AbstractContainerMenu{



    public PyrolysisMenu(int a, Inventory inventory){
        this(a, inventory, new SimpleContainer(4));
    }

    private Container container;

    public PyrolysisMenu(int a, Inventory inventory, Container container) {
        super(InstantHealModMenuTypes.PYROLYSIS_MENU.get(), a);
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


