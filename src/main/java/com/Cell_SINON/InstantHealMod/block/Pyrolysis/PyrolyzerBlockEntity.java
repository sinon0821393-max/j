package com.Cell_SINON.InstantHealMod.block.Pyrolysis;

import com.Cell_SINON.InstantHealMod.Recipe.DistillationRecipe;
import com.Cell_SINON.InstantHealMod.Recipe.ModRecipeTypes;
import com.Cell_SINON.InstantHealMod.Recipe.PyrolysisRecipe;
import com.Cell_SINON.InstantHealMod.block.Distiller.DistillerBlockEntity;
import com.Cell_SINON.InstantHealMod.block.fermentation.FermentationMenu;
import com.Cell_SINON.InstantHealMod.main.InstantHealMod;
import com.Cell_SINON.InstantHealMod.regi.InstantHealModItems;
import com.Cell_SINON.InstantHealMod.regi.InstantHealModEntities;
import com.Cell_SINON.InstantHealMod.block.Distiller.DistillerMenu; // 後で作る
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
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.RandomizableContainerBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;


public class PyrolyzerBlockEntity extends RandomizableContainerBlockEntity {


    public PyrolyzerBlockEntity(BlockPos pos, BlockState state) {
        super(InstantHealModEntities.PYROLYZER_BLOCK_ENTITY.get(), pos, state);

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
        return Component.translatable("container." + InstantHealMod.MOD_id, ".pyrolyzer_bock");
    }

    @Override
    protected AbstractContainerMenu createMenu(int a, Inventory inventory) {
        return new PyrolysisMenu(a, inventory, this);
    }

}





