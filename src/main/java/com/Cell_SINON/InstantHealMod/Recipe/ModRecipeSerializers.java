package com.Cell_SINON.InstantHealMod.Recipe;

import com.Cell_SINON.InstantHealMod.main.InstantHealMod;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.minecraft.core.NonNullList;
import net.minecraft.core.RegistryAccess;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.ShapedRecipe;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import com.Cell_SINON.InstantHealMod.Recipe.ChemicalReactionRecipe;
import org.jetbrains.annotations.Nullable;

public class ModRecipeSerializers {   // RecipeSerializerを登録するためのDeferredRegisterを作成
    public static final DeferredRegister<RecipeSerializer<?>> RECIPE_SERIALIZERS =
            DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, InstantHealMod.MOD_id);

    // ChemicalReactionRecipeの中にあるSerializerを参照するように変更
    public static final RegistryObject<RecipeSerializer<ChemicalReactionRecipe>> CHEMICAL_REACTION_SERIALIZER =
            RECIPE_SERIALIZERS.register("chemical_reaction", ModRecipeTypes.Serializer::new);

    public static final RegistryObject<RecipeSerializer<FermentationRecipe>> FERMENTATION_SERIALIZER =
            RECIPE_SERIALIZERS.register("fermentation", FermentationRecipe.Serializer::new);


    public static final RegistryObject<RecipeSerializer<DistillationRecipe>> DISTILLATION_SERIALIZER =
            RECIPE_SERIALIZERS.register("distillation", DistillationRecipe.Serializer::new);

    public static final RegistryObject<RecipeSerializer<ElectrolysisReactionRecipe>> ELECTROLYSIS_SERIALIZER =
            RECIPE_SERIALIZERS.register("electrolysis", ElectrolysisReactionRecipe.Serializer::new);

    public static final RegistryObject<RecipeSerializer<PyrolysisRecipe>> PYROLYSIS_SERIALIZER =
            RECIPE_SERIALIZERS.register("pyrolysis", PyrolysisRecipe.Serializer::new);


    public static void register(IEventBus eventBus) {
        RECIPE_SERIALIZERS.register(eventBus);
    }
}
