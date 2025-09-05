package com.Cell_SINON.InstantHealMod.block.ChemicalReactor;

import com.Cell_SINON.InstantHealMod.regi.InstantHealModEntities;
import com.Cell_SINON.InstantHealMod.regi.InstantHealModBlocks;
import com.Cell_SINON.InstantHealMod.regi.InstantHealModMenuTypes;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.items.SlotItemHandler;


public class ChemicalReactorMenu extends AbstractContainerMenu {

    public ChemicalReactorMenu(int a, Inventory inventory){
        this(a, inventory, new SimpleContainer(4));
    }

    private Container container;

    public ChemicalReactorMenu(int a, Inventory inventory, Container container) {
        super(InstantHealModMenuTypes.CHEMICAL_REACTOR_MENU.get(), a);
        this.container = container;
        checkContainerSize(container, 4);
        container.startOpen(inventory.player);


        for (int i = 0; i < 2; i++) {
            this.addSlot(new Slot(container, i, 8 + i * 49, 47));

        }

        for (int k = 0; k < 3; k++) {
            for (int l = 0; l < 9; l++) {
                this.addSlot(new Slot(inventory, l + 9 + k * 9, 8 + l * 18, 84 + k * 18));

            }
        }

        for (int m = 0; m < 9; m++) {
            this.addSlot(new Slot(inventory, m, 8 + m * 18, 142));

        }


    }



    @Override
    public ItemStack quickMoveStack(Player player, int a) {
        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = this.slots.get(a);
        if (slot != null && slot.hasItem()) {
            ItemStack itemstack1 = slot.getItem();
            itemstack = itemstack1.copy();
            if (a < this.container.getContainerSize()) {
                if (!this.moveItemStackTo(itemstack1, this.container.getContainerSize(), this.slots.size(), true)) {
                    return ItemStack.EMPTY;
                }
            } else if (!this.moveItemStackTo(itemstack1, 0, this.container.getContainerSize(), false)) {
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

    @Override
    public void removed(Player player) {
        super.removed(player);
        this.container.stopOpen(player);
    }
}


