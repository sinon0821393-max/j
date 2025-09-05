package com.Cell_SINON.InstantHealMod.Recipe;

import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
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

public class DistillationRecipe implements Recipe<SimpleContainer> {

    private final ResourceLocation id;
    private final Ingredient recipeItem;
    private final ItemStack output1;
    private final ItemStack output2;

    public DistillationRecipe(ResourceLocation pId, Ingredient pRecipeItem, ItemStack pOutput1, ItemStack pOutput2) {
        this.id = pId;
        this.recipeItem = pRecipeItem;
        this.output1 = pOutput1;
        this.output2 = pOutput2;
    }

    @Override
    public boolean matches(SimpleContainer pContainer, Level pLevel) {
        if (pLevel.isClientSide()) return false;
        return recipeItem.test(pContainer.getItem(1));
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

    public ItemStack getOutput1() { return this.output1.copy(); }
    public ItemStack getOutput2() { return this.output2.copy(); }

    @Override
    public NonNullList<Ingredient> getIngredients() {
        return NonNullList.withSize(1, this.recipeItem);
    }



    @Override
    public ResourceLocation getId() { return this.id; }

    @Override
    public RecipeSerializer<?> getSerializer() { return ModRecipeSerializers.DISTILLATION_SERIALIZER.get(); }

    @Override
    public RecipeType<?> getType() { return ModRecipeTypes.DISTILLATION_TYPE.get(); }



    // --- 5. Serializerの内部クラス ---
    public static class Serializer implements RecipeSerializer<DistillationRecipe> {
        @Override
        public DistillationRecipe fromJson(ResourceLocation pRecipeId, JsonObject pSerializedRecipe) {
            Ingredient input = Ingredient.fromJson(GsonHelper.getAsJsonObject(pSerializedRecipe, "ingredient"));
            ItemStack output1 = ShapedRecipe.itemStackFromJson(GsonHelper.getAsJsonObject(pSerializedRecipe, "output1"));
            ItemStack output2 = ShapedRecipe.itemStackFromJson(GsonHelper.getAsJsonObject(pSerializedRecipe, "output2"));
            return new DistillationRecipe(pRecipeId, input, output1, output2);
        }

        @Nullable
        @Override
        public DistillationRecipe fromNetwork(ResourceLocation pRecipeId, FriendlyByteBuf pBuffer) {
            Ingredient input = Ingredient.fromNetwork(pBuffer);
            ItemStack output1 = pBuffer.readItem();
            ItemStack output2 = pBuffer.readItem();
            return new DistillationRecipe(pRecipeId, input, output1, output2);
        }

        @Override
        public void toNetwork(FriendlyByteBuf pBuffer, DistillationRecipe pRecipe) {
            pRecipe.recipeItem.toNetwork(pBuffer);
            pBuffer.writeItem(pRecipe.getOutput1());
            pBuffer.writeItem(pRecipe.getOutput2());
        }
    }

}
