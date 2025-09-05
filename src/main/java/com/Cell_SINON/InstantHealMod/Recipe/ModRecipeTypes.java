package com.Cell_SINON.InstantHealMod.Recipe;

import com.Cell_SINON.InstantHealMod.main.InstantHealMod;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.minecraft.core.NonNullList;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.item.crafting.ShapedRecipe;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.Nullable;

public class ModRecipeTypes {

    // RecipeTypeを登録するためのDeferredRegisterを作成
    public static final DeferredRegister<RecipeType<?>> RECIPE_TYPES =
            DeferredRegister.create(ForgeRegistries.RECIPE_TYPES, InstantHealMod.MOD_id);

    // 新しいレシピタイプ「CHEMICAL_REACTION_TYPE」を登録
    public static final RegistryObject<RecipeType<ChemicalReactionRecipe>> CHEMICAL_REACTION_TYPE =
            RECIPE_TYPES.register("chemical_reaction",
                    // Serializer.ID ではなく、ModRecipeSerializersから直接IDを取得する
                    () -> RecipeType.simple(ModRecipeSerializers.CHEMICAL_REACTION_SERIALIZER.getId()));

    public static final RegistryObject<RecipeType<FermentationRecipe>> FERMENTATION_TYPE =
            RECIPE_TYPES.register("fermentation", () -> RecipeType.simple(ModRecipeSerializers.FERMENTATION_SERIALIZER.getId()));

    public static final RegistryObject<RecipeType<DistillationRecipe>> DISTILLATION_TYPE =
            RECIPE_TYPES.register("distillation", () -> RecipeType.simple(ModRecipeSerializers.DISTILLATION_SERIALIZER.getId()));

    public static final RegistryObject<RecipeType<ElectrolysisReactionRecipe>> ELECTROLYSIS_TYPE =
            RECIPE_TYPES.register("electrolysis", () -> RecipeType.simple(ModRecipeSerializers.ELECTROLYSIS_SERIALIZER.getId()));

    public static final RegistryObject<RecipeType<PyrolysisRecipe>> PYROLYSIS_TYPE =
            RECIPE_TYPES.register("pyrolysis", () -> RecipeType.simple(ModRecipeSerializers.PYROLYSIS_SERIALIZER.getId()));



    public static void register(IEventBus eventBus) {
        RECIPE_TYPES.register(eventBus);
    }

    public static final DeferredRegister<RecipeSerializer<?>> RECIPE_SERIALIZERS =
            DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, InstantHealMod.MOD_id);

    // 新しいRecipeSerializer「CHEMICAL_REACTION_SERIALIZER」を登録
    public static final RegistryObject<RecipeSerializer<ChemicalReactionRecipe>> CHEMICAL_REACTION_SERIALIZER =
            RECIPE_SERIALIZERS.register("chemical_reaction", () -> new ChemicalReactionRecipe.Serializer());


    // --- ここからがSerializer本体の実装 ---

    // ChemicalReactionRecipeクラスの中に、ネストした(内側の)クラスとしてSerializerを定義するのが一般的
    public static class Serializer implements RecipeSerializer<ChemicalReactionRecipe> {
        public static final ResourceLocation ID = new ResourceLocation(InstantHealMod.MOD_id, "chemical_reaction");


        /**
         * JSONファイルからレシピを読み込むメソッド
         */
        @Override
        public ChemicalReactionRecipe fromJson(ResourceLocation pRecipeId, JsonObject pSerializedRecipe) {
            // JSONから"output1"と"output2"を読み込む
            ItemStack output1 = ShapedRecipe.itemStackFromJson(GsonHelper.getAsJsonObject(pSerializedRecipe, "output1"));
            ItemStack output2 = ShapedRecipe.itemStackFromJson(GsonHelper.getAsJsonObject(pSerializedRecipe, "output2"));

            // JSONから"ingredients" (材料) を読み込む
            JsonArray ingredients = GsonHelper.getAsJsonArray(pSerializedRecipe, "ingredients");
            NonNullList<Ingredient> inputs = NonNullList.withSize(2, Ingredient.EMPTY); // 2つの材料スロットを用意

            for (int i = 0; i < inputs.size(); i++) {
                inputs.set(i, Ingredient.fromJson(ingredients.get(i)));
            }

            return new ChemicalReactionRecipe(pRecipeId, inputs, output1, output2);
        }

        /**
         * サーバーからクライアントへレシピデータを送信する際に、ネットワークバッファから読み込むメソッド
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
         * サーバーからクライアントへレシピデータを送信する際に、ネットワークバッファへ書き込むメソッド
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

}
