package com.Cell_SINON.InstantHealMod.block.Distiller;

import com.Cell_SINON.InstantHealMod.Recipe.DistillationRecipe;
import com.Cell_SINON.InstantHealMod.Recipe.ModRecipeTypes;
import com.Cell_SINON.InstantHealMod.block.ChemicalReactor.ChemicalReactorMenu;
import com.Cell_SINON.InstantHealMod.main.InstantHealMod;
import com.Cell_SINON.InstantHealMod.regi.InstantHealModItems;
import com.Cell_SINON.InstantHealMod.regi.InstantHealModEntities;
import com.Cell_SINON.InstantHealMod.block.Distiller.DistillerMenu; // 後で作る
import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.ContainerHelper;
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










public class DistillerBlockEntity extends RandomizableContainerBlockEntity {



    private NonNullList<ItemStack> items = NonNullList.withSize(3, ItemStack.EMPTY);
    private static final int FUEL_SLOT = 0, INPUT_SLOT = 1, OUTPUT_SLOT = 2;

    private int progress = 0, maxProgress = 200;
    private int fuelTime = 0, maxFuelTime = 0;

    public final ContainerData data;







    public DistillerBlockEntity(BlockPos pPos, BlockState pState) {
        super(InstantHealModEntities.DISTILLER_BLOCK_ENTITY.get(), pPos, pState);
        this.data = new ContainerData() {
            public int get(int index) {
                return switch (index) {
                    case 0 -> DistillerBlockEntity.this.progress;
                    case 1 -> DistillerBlockEntity.this.maxProgress;
                    case 2 -> DistillerBlockEntity.this.fuelTime;
                    case 3 -> DistillerBlockEntity.this.maxFuelTime;
                    default -> 0;
                };
            }
            public void set(int index, int value) {
                switch (index) {
                    case 0 -> DistillerBlockEntity.this.progress = value;
                    case 1 -> DistillerBlockEntity.this.maxProgress = value;
                    case 2 -> DistillerBlockEntity.this.fuelTime = value;
                    case 3 -> DistillerBlockEntity.this.maxFuelTime = value;
                }
            }
            public int getCount() { return 4; }
        };
    }

    // --- (RandomizableContainerBlockEntityの必須メソッド群はそのまま) ---
    @Override protected NonNullList<ItemStack> getItems() { return this.items; }
    @Override protected void setItems(NonNullList<ItemStack> pItems) { this.items = pItems; }
    @Override protected Component getDefaultName() { return Component.translatable("block.instanthealmod.distiller_block"); }
    @Override public int getContainerSize() { return 3; }
    @Override protected AbstractContainerMenu createMenu(int pContainerId, Inventory pPlayerInventory) {
        return new DistillerMenu(pContainerId, pPlayerInventory, this);
    }

    @Override
    protected void saveAdditional(CompoundTag nbt) {
        super.saveAdditional(nbt);
        ContainerHelper.saveAllItems(nbt, this.items);

    }


    @Override
    public void load(CompoundTag nbt) {
        super.load(nbt);
        ContainerHelper.loadAllItems(nbt, this.items);


    }


    // --- (save/loadメソッドもそのまま) ---

    // --- ここからが「魂」となるロジックです ---
    public static void tick(Level pLevel, BlockPos pPos, BlockState pState, DistillerBlockEntity pBlockEntity) {

        if (pLevel.isClientSide()) return;

        boolean isBurning = pBlockEntity.fuelTime > 0;
        if (isBurning) {
            pBlockEntity.fuelTime--;
            SimpleContainer recipeInventory = new SimpleContainer(pBlockEntity.getContainerSize()); // サイズは3
            // 2. お皿のINPUT_SLOT番目に、機械のINPUT_SLOTの中身をコピーする
            recipeInventory.setItem(INPUT_SLOT, pBlockEntity.getItem(INPUT_SLOT));

            // 3. レシピ検索に、正しいサイズの「お皿」(recipeInventory)を渡す
            Optional<DistillationRecipe> recipe = pLevel.getRecipeManager()
                    .getRecipeFor(ModRecipeTypes.DISTILLATION_TYPE.get(), recipeInventory, pLevel);
        }


        // 1. レシピを検索する (材料はINPUT_SLOT(1番)のみ)
        Optional<DistillationRecipe> recipe = pLevel.getRecipeManager()
                .getRecipeFor(ModRecipeTypes.DISTILLATION_TYPE.get(), new SimpleContainer(pBlockEntity.getItem(INPUT_SLOT)), pLevel);

        // 2. レシピが存在し、出力スロットに空きがあれば...
        if (recipe.isPresent() && canInsertItemsIntoOutputSlot(pBlockEntity, recipe.get())) {
            // 3. 燃料があるか、または補給できるか？
            if (!isBurning && !pBlockEntity.getItem(FUEL_SLOT).isEmpty()) {
                pBlockEntity.fuelTime = ForgeHooks.getBurnTime(pBlockEntity.getItem(FUEL_SLOT), RecipeType.SMELTING);
                pBlockEntity.maxFuelTime = pBlockEntity.fuelTime;
                pBlockEntity.removeItem(FUEL_SLOT, 1);
            }

            // 4. 燃焼中なら、進捗を進める
            if (pBlockEntity.fuelTime > 0) {
                pBlockEntity.progress++;
                setChanged(pLevel, pPos, pState);
                if (pBlockEntity.progress >= pBlockEntity.maxProgress) {
                    craftItem(pBlockEntity, recipe.get());
                    pBlockEntity.resetProgress();
                }
            }
        } else {
            pBlockEntity.resetProgress();
            setChanged(pLevel, pPos, pState);
        }
    }

    private void resetProgress() {
        this.progress = 0;
    }

    private static void craftItem(DistillerBlockEntity pBlockEntity, DistillationRecipe pRecipe) {

        // レシピから完成品を取得し、アイテムを変換・生成する
        pBlockEntity.setItem(INPUT_SLOT, new ItemStack(pRecipe.getOutput2().getItem())); // 廃液バケツに変化
        pBlockEntity.setItem(OUTPUT_SLOT, new ItemStack(pRecipe.getOutput1().getItem())); // エタノール瓶を生成
    }

    public static boolean canInsertItemsIntoOutputSlot(DistillerBlockEntity pBlockEntity, DistillationRecipe pRecipe) {
        // 現在のレシピでは、出力スロットに「空のガラス瓶」が入っていることが、
        // アイテムを生成するための条件です。
        return pBlockEntity.getItem(OUTPUT_SLOT).is(Items.GLASS_BOTTLE);
    }


}




