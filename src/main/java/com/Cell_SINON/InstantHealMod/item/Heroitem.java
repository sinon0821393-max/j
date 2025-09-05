package com.Cell_SINON.InstantHealMod.item;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;

public class Heroitem {
public static final FoodProperties HEROITEM = new FoodProperties.Builder()
        .nutrition(10)
        .saturationMod(16F)
        .fast()
        .alwaysEat()
        .effect(new MobEffectInstance(MobEffects.REGENERATION, 20 * 30 * 1, 1, true, true), 1.0F)
        .effect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 20 * 60 * 1, 0, true, true), 1.0F)
        .effect(new MobEffectInstance(MobEffects.ABSORPTION, 20 * 60 * 1, 1, true, true), 1.0F)

        .build();
}
