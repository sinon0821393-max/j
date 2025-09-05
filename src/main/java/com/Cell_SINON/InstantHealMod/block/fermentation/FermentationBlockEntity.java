package com.Cell_SINON.InstantHealMod.block.fermentation;

import com.Cell_SINON.InstantHealMod.Recipe.FermentationRecipe;
import com.Cell_SINON.InstantHealMod.Recipe.ModRecipeTypes;
import com.Cell_SINON.InstantHealMod.block.Distiller.DistillerMenu;
import com.Cell_SINON.InstantHealMod.main.InstantHealMod;
import com.Cell_SINON.InstantHealMod.regi.InstantHealModEntities;
import com.Cell_SINON.InstantHealMod.regi.InstantHealModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.Containers;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.RandomizableContainerBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nullable;
import java.util.Optional;

public class FermentationBlockEntity extends RandomizableContainerBlockEntity {




    public FermentationBlockEntity(BlockPos pos, BlockState state) {
        super(InstantHealModEntities.FERMENTATION_BLOCK_ENTITY.get(), pos, state);


    }


    @Override
    protected void saveAdditional(CompoundTag nbt) {
        super.saveAdditional(nbt);
    }


    @Override
    public void load(CompoundTag nbt) {
        super.load(nbt);

    }


    @Override
    protected NonNullList<ItemStack> getItems() {
        return null;
    }

    @Override
    protected void setItems(NonNullList<ItemStack> list) {

    }

    @Override
    public int getContainerSize() {
        return 0;
    }

    @Override
    protected Component getDefaultName() {
        return Component.translatable("container." + InstantHealMod.MOD_id, ".fermentation_block");
    }

    @Override
    protected AbstractContainerMenu createMenu(int a, Inventory inventory) {
        return new FermentationMenu(a, inventory, this);
    }

}



