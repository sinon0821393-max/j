package com.Cell_SINON.InstantHealMod.Recipe;

import com.Cell_SINON.InstantHealMod.main.InstantHealMod;
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
import com.Cell_SINON.InstantHealMod.Recipe.ModRecipeSerializers;
import com.Cell_SINON.InstantHealMod.Recipe.ModRecipeTypes;

import javax.annotation.Nullable;

public class ChemicalReactionRecipe implements Recipe<SimpleContainer> {

    private final ResourceLocation id;
    private final NonNullList<Ingredient> recipeItems;
    private final ItemStack output1;
    private final ItemStack output2;

    public ChemicalReactionRecipe(ResourceLocation pID, NonNullList<Ingredient> pRecipeItems, ItemStack pOutput1, ItemStack pOutput2) {
        this.id = pID;
        this.recipeItems = pRecipeItems;
        this.output1 = pOutput1;
        this.output2 = pOutput2;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return ModRecipeSerializers.CHEMICAL_REACTION_SERIALIZER.get();
    }

    @Override
    public RecipeType<?> getType() {
        return ModRecipeTypes.CHEMICAL_REACTION_TYPE.get();
    }

    public static class Serializer implements RecipeSerializer<ChemicalReactionRecipe> {
        // IDはSerializer自身が持つのではなく、レシピタイプから参照するのが安全です
        // public static final ResourceLocation ID = new ResourceLocation(InstantHealMod.MOD_id, "chemical_reaction");

        /**
         * JSONファイルからレシピを読み込むメソッド
         * @param pRecipeId レシピのID (例: instanthealmod:diamond_from_ingots)
         * @param pSerializedRecipe 読み込まれたJSONオブジェクト
         * @return 新しいChemicalReactionRecipeインスタンス
         */
        @Override
        public ChemicalReactionRecipe fromJson(ResourceLocation pRecipeId, JsonObject pSerializedRecipe) {
            // "output1"と"output2"をJSONから読み込む
            ItemStack output1 = ShapedRecipe.itemStackFromJson(GsonHelper.getAsJsonObject(pSerializedRecipe, "output1"));
            ItemStack output2 = ShapedRecipe.itemStackFromJson(GsonHelper.getAsJsonObject(pSerializedRecipe, "output2"));

            // "ingredients"をJSONから読み込む
            JsonArray ingredientsJson = GsonHelper.getAsJsonArray(pSerializedRecipe, "ingredients");
            NonNullList<Ingredient> inputs = NonNullList.withSize(2, Ingredient.EMPTY); // 2つの材料スロットを確保

            for (int i = 0; i < inputs.size(); i++) {
                inputs.set(i, Ingredient.fromJson(ingredientsJson.get(i)));
            }

            return new ChemicalReactionRecipe(pRecipeId, inputs, output1, output2);
        }

        /**
         * サーバーからのネットワークデータでレシピを読み込むメソッド
         */
        @Nullable
        @Override
        public ChemicalReactionRecipe fromNetwork(ResourceLocation pRecipeId, FriendlyByteBuf pBuffer) {
            NonNullList<Ingredient> inputs = NonNullList.withSize(pBuffer.readInt(), Ingredient.EMPTY);

            for (int i = 0; i < inputs.size(); i++) {
                inputs.set(i, Ingredient.fromNetwork(pBuffer));
            }

            ItemStack output1 = pBuffer.readItem();
            ItemStack output2 = pBuffer.readItem();
            return new ChemicalReactionRecipe(pRecipeId, inputs, output1, output2);
        }

        /**
         * レシピをサーバーからクライアントへ送るデータに変換するメソッド
         */
        @Override
        public void toNetwork(FriendlyByteBuf pBuffer, ChemicalReactionRecipe pRecipe) {
            pBuffer.writeInt(pRecipe.getIngredients().size());
            for (Ingredient ing : pRecipe.getIngredients()) {
                ing.toNetwork(pBuffer);
            }
            pBuffer.writeItem(pRecipe.getOutput1());
            pBuffer.writeItem(pRecipe.getOutput2());
        }
    }

    @Override
    public boolean matches(SimpleContainer pContainer, Level pLevel) {
        if (pLevel.isClientSide()){
            return false;
        }

        boolean match1 = recipeItems.get(0).test(pContainer.getItem(0));
        boolean match2 = recipeItems.get(1).test(pContainer.getItem(1));

        return match1 && match2;
    }

    @Override
    public ItemStack assemble(SimpleContainer pContainer, RegistryAccess pRegistryAccess) {
        return output1.copy();
    }

    public ItemStack getOutput1(){
        return this.output2.copy();
    }

    public ItemStack getOutput2() {
        return this.output2.copy();
    }


    @Override
    public boolean canCraftInDimensions(int pWidth, int pHeight) {
        return true; // クラフトグリッドのサイズには依存しない
    }

    @Override
    public ItemStack getResultItem(RegistryAccess pRegistryAccess) {
        return output1.copy();
    }

    @Override
    public ResourceLocation getId() {
        return this.id;
    }







}
