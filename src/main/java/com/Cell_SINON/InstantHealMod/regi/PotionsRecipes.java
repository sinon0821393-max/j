package com.Cell_SINON.InstantHealMod.regi;


import com.Cell_SINON.InstantHealMod.main.InstantHealMod;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.brewing.BrewingRecipe;
import net.minecraftforge.common.brewing.BrewingRecipeRegistry;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import com.Cell_SINON.InstantHealMod.regi.InstantHealModPotions;
//@Mod.EventBusSubscriber(modid = InstantHealMod.MOD_id, bus = Mod.EventBusSubscriber.Bus.MOD)
public class PotionsRecipes { @SubscribeEvent
public static void commonSetup(FMLCommonSetupEvent event) {
    // event.enqueueWork(...) を使うことで、メインスレッドで安全に処理を実行できます。
    event.enqueueWork(() -> {

        // --- ここからが醸造レシピの定義です ---

        // 【レシピ1】ベースとなる「村の英雄のポーション」を作成するレシピ
        // 材料: 奇妙なポーション + エメラルド  =>  完成品: 村の英雄のポーション (3:00)
        BrewingRecipeRegistry.addRecipe(
                new BrewingRecipe(
                        Ingredient.of(PotionUtils.setPotion(new ItemStack(Items.POTION), Potions.AWKWARD)),
                        Ingredient.of(Items.EMERALD),
                        PotionUtils.setPotion(new ItemStack(Items.POTION), InstantHealModPotions.HEROPOTION.get())
                ));

    });}}