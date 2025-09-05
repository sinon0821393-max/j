package com.Cell_SINON.InstantHealMod.Recipe;


import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.ItemStack;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.minecraft.core.NonNullList;
import net.minecraft.core.RegistryAccess;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;

public class FermentationRecipe implements Recipe<SimpleContainer>{

    // --- 1. 変数(フィールド)の宣言 ---
    private final ResourceLocation id;
    private final NonNullList<Ingredient> recipeItems; // 入力アイテム (2つ)
    private final ItemStack output1;     // 出力アイテム1
    private final ItemStack output2;     // 出力アイテム2

    // --- 2. コンストラクタ ---
    public FermentationRecipe(ResourceLocation pId, NonNullList<Ingredient> pRecipeItems, ItemStack pOutput1, ItemStack pOutput2) {
        this.id = pId;
        this.recipeItems = pRecipeItems;
        this.output1 = pOutput1;
        this.output2 = pOutput2;
    }

    // --- 3. 必須のオーバーライドメソッド (すべて public にする) ---
    @Override
    public boolean matches(SimpleContainer pContainer, Level pLevel) {
        if (pLevel.isClientSide()) return false;
        // 材料1がスロット0と、材料2がスロット1と一致するか
        return recipeItems.get(0).test(pContainer.getItem(0)) && recipeItems.get(1).test(pContainer.getItem(1));
    }

    @Override
    public ItemStack assemble(SimpleContainer pContainer, RegistryAccess pRegistryAccess) {
        return output1.copy();
    }

    @Override
    public boolean canCraftInDimensions(int pWidth, int pHeight) {
        return true;
    }

    @Override
    public ItemStack getResultItem(RegistryAccess pRegistryAccess) {
        return output1.copy();
    }

    @Override
    public NonNullList<Ingredient> getIngredients() {
        return this.recipeItems;
    }

    @Override
    public ResourceLocation getId() { return this.id; }

    @Override
    public RecipeSerializer<?> getSerializer() { return ModRecipeSerializers.FERMENTATION_SERIALIZER.get(); }

    @Override
    public RecipeType<?> getType() { return ModRecipeTypes.FERMENTATION_TYPE.get(); }

    // --- 4. ゲッターメソッド ---
    public ItemStack getOutput1() { return this.output1.copy(); }
    public ItemStack getOutput2() { return this.output2.copy(); }

    // --- 5. Serializerの内部クラス ---
    public static class Serializer implements RecipeSerializer<FermentationRecipe> {
        @Override
        public FermentationRecipe fromJson(ResourceLocation pRecipeId, JsonObject pSerializedRecipe) {
            JsonArray ingredients = GsonHelper.getAsJsonArray(pSerializedRecipe, "ingredients");
            NonNullList<Ingredient> inputs = NonNullList.withSize(2, Ingredient.EMPTY);
            for (int i = 0; i < inputs.size(); i++) {
                inputs.set(i, Ingredient.fromJson(ingredients.get(i)));
            }

            ItemStack output1 = ShapedRecipe.itemStackFromJson(GsonHelper.getAsJsonObject(pSerializedRecipe, "output1"));
            ItemStack output2 = ShapedRecipe.itemStackFromJson(GsonHelper.getAsJsonObject(pSerializedRecipe, "output2"));
            return new FermentationRecipe(pRecipeId, inputs, output1, output2);
        }

        @Nullable
        @Override
        public FermentationRecipe fromNetwork(ResourceLocation pRecipeId, FriendlyByteBuf pBuffer) {
            NonNullList<Ingredient> inputs = NonNullList.withSize(pBuffer.readInt(), Ingredient.EMPTY);
            for (int i = 0; i < inputs.size(); i++) {
                inputs.set(i, Ingredient.fromNetwork(pBuffer));
            }
            ItemStack output1 = pBuffer.readItem();
            ItemStack output2 = pBuffer.readItem();
            return new FermentationRecipe(pRecipeId, inputs, output1, output2);
        }

        @Override
        public void toNetwork(FriendlyByteBuf pBuffer, FermentationRecipe pRecipe) {
            pBuffer.writeInt(pRecipe.getIngredients().size());
            for (Ingredient ing : pRecipe.getIngredients()) {
                ing.toNetwork(pBuffer);
            }
            pBuffer.writeItem(pRecipe.getOutput1());
            pBuffer.writeItem(pRecipe.getOutput2());
        }
    }
}

