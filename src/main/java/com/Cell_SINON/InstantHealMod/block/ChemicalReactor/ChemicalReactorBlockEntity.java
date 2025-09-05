package com.Cell_SINON.InstantHealMod.block.ChemicalReactor;

import com.Cell_SINON.InstantHealMod.Recipe.ChemicalReactionRecipe;
import com.Cell_SINON.InstantHealMod.Recipe.ModRecipeTypes;
import com.Cell_SINON.InstantHealMod.main.InstantHealMod;
import com.Cell_SINON.InstantHealMod.regi.InstantHealModEntities;
import com.Cell_SINON.InstantHealMod.block.ChemicalReactor.ChemicalReactorMenu; // 後で作成
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.*;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.entity.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import java.util.Optional;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;

public class ChemicalReactorBlockEntity extends RandomizableContainerBlockEntity {
    private  NonNullList<ItemStack> Items = NonNullList.withSize(4, ItemStack.EMPTY);




    public ChemicalReactorBlockEntity(BlockPos pos, BlockState state) {
        super(InstantHealModEntities.CHEMICAL_REACTOR_BLOCK_ENTITY.get(), pos, state);

    }


    @Override
    protected void saveAdditional(CompoundTag nbt) {
        super.saveAdditional(nbt);
        ContainerHelper.saveAllItems(nbt, this.Items);
        nbt.putInt("reactor.progress", this.progress);
    }


    @Override
    public void load(CompoundTag nbt) {
        super.load(nbt);
        ContainerHelper.loadAllItems(nbt, this.Items);
        this.progress = nbt.getInt("reactor.progress");

    }


    @Override
    protected NonNullList<ItemStack> getItems() {
        return this.Items;
    }

    @Override
    protected void setItems(NonNullList<ItemStack> list) {
        this.Items = list;

    }

    @Override
    public int getContainerSize() {
        return this.Items.size();
    }

    @Override
    protected Component getDefaultName() {
        return Component.translatable("container." + InstantHealMod.MOD_id, ".chemical_block");
    }

    @Override
    protected AbstractContainerMenu createMenu(int a, Inventory inventory) {
        return new ChemicalReactorMenu(a, inventory, this);
    }

    // --- 1. データ変数を追加 ---
    private int progress = 0;
    private int maxProgress = 200; // 10秒で完成

    // ContainerDataの実装を追加 (GUIに進捗を同期させるため)
    public final ContainerData data = new ContainerData() {
        @Override
        public int get(int pIndex) {
            return pIndex == 0 ? ChemicalReactorBlockEntity.this.progress : ChemicalReactorBlockEntity.this.maxProgress;
        }

        @Override
        public void set(int pIndex, int pValue) {
            if (pIndex == 0) ChemicalReactorBlockEntity.this.progress = pValue;
            else ChemicalReactorBlockEntity.this.maxProgress = pValue;
        }

        @Override
        public int getCount() {
            return 2;
        }
    };

    // --- 2. Tick処理（機械の心臓部）を追加 ---
    public static void tick(Level pLevel, BlockPos pPos, BlockState pState, ChemicalReactorBlockEntity pBlockEntity) {
        if (pLevel.isClientSide()) return;

        // 1. レシピ検索用の「通訳コンテナ」を作成
        SimpleContainer recipeInventory = new SimpleContainer(2);
        recipeInventory.setItem(0, pBlockEntity.getItem(0)); // 左入力スロット
        recipeInventory.setItem(1, pBlockEntity.getItem(1)); // 中央入力スロット

        // 2. レシピ検索に「通訳コンテナ」を渡す
        Optional<ChemicalReactionRecipe> recipe = pLevel.getRecipeManager()
                .getRecipeFor(ModRecipeTypes.CHEMICAL_REACTION_TYPE.get(), recipeInventory, pLevel);

        if (recipe.isPresent() && canInsertItemsIntoOutputSlots(pBlockEntity, recipe.get())) {
            pBlockEntity.progress++;
            setChanged(pLevel, pPos, pState);
            if (pBlockEntity.progress >= pBlockEntity.maxProgress) {
                craftItem(pBlockEntity, recipe.get());
                pBlockEntity.resetProgress();
            }
        } else {
            pBlockEntity.resetProgress();
            setChanged(pLevel, pPos, pState);
        }
    }

    private void resetProgress() { this.progress = 0; }

    private static void craftItem(ChemicalReactorBlockEntity pBlockEntity, ChemicalReactionRecipe pRecipe) {
        ItemStack output1 = pRecipe.getOutput1();
        ItemStack output2 = pRecipe.getOutput2();
        pBlockEntity.removeItem(0, 1);
        pBlockEntity.removeItem(1, 1);
        pBlockEntity.setItem(2, new ItemStack(output1.getItem(), pBlockEntity.getItem(2).getCount() + output1.getCount()));
        pBlockEntity.setItem(3, new ItemStack(output2.getItem(), pBlockEntity.getItem(3).getCount() + output2.getCount()));
    }

    private static boolean canInsertItemsIntoOutputSlots(ChemicalReactorBlockEntity pBlockEntity, ChemicalReactionRecipe pRecipe) {
        ItemStack output1 = pRecipe.getOutput1();
        ItemStack output2 = pRecipe.getOutput2();
        boolean canInsertOutput1 = pBlockEntity.getItem(2).isEmpty() || (pBlockEntity.getItem(2).is(output1.getItem()) && pBlockEntity.getItem(2).getCount() + output1.getCount() <= output1.getMaxStackSize());
        boolean canInsertOutput2 = pBlockEntity.getItem(3).isEmpty() || (pBlockEntity.getItem(3).is(output2.getItem()) && pBlockEntity.getItem(3).getCount() + output2.getCount() <= output2.getMaxStackSize());
        return canInsertOutput1 && canInsertOutput2;
    }
}



