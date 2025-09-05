package com.Cell_SINON.InstantHealMod.block.Distiller;

import com.Cell_SINON.InstantHealMod.regi.InstantHealModBlocks;
import com.Cell_SINON.InstantHealMod.regi.InstantHealModMenuTypes;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.items.SlotItemHandler;

public class DistillerMenu extends AbstractContainerMenu {
    // --- 変数宣言 ---
    public DistillerMenu(int a, Inventory inventory){
        this(a, inventory, new SimpleContainer(3));
    }

    private Container container;




    public DistillerMenu(int a, Inventory inventory, Container container) {
        super(InstantHealModMenuTypes.DISTILLER_MENU.get(), a);
        this.container = container;
        checkContainerSize(container, 3);
        container.startOpen(inventory.player);

        this.addSlot(new FuelSlot(container, 0, 8, 62));
        this.addSlot(new Slot(container, 1, 47, 56));
        this.addSlot(new GlassBottleSlot(container, 2, 134, 8));

        for (int i = 0; i < 3; i++) {

            for (int k = 0; k < 9; k++) {
                this.addSlot(new Slot(inventory, k + 9 + i * 9, 8 + k * 18, 84 + i * 18));


            }
        }

            for (int l = 0; l < 9; l++) {
                this.addSlot(new Slot(inventory, l, 8 + l * 18, 142));

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


    private static class FuelSlot extends Slot {
        public FuelSlot(Container pContainer, int pIndex, int pX, int pY) {
            super(pContainer, pIndex, pX, pY);
        }
        @Override
        public boolean mayPlace(ItemStack pStack) {
            return ForgeHooks.getBurnTime(pStack, RecipeType.SMELTING) > 0;
        }
    }

    /**
     * ガラス瓶のみを受け入れるスロット
     */
    private static class GlassBottleSlot extends Slot {
        public GlassBottleSlot(Container pContainer, int pIndex, int pX, int pY) {
            super(pContainer, pIndex, pX, pY);
        }
        @Override
        public boolean mayPlace(ItemStack pStack) {
            return pStack.is(Items.GLASS_BOTTLE);
        }
    }



}


