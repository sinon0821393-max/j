package com.Cell_SINON.InstantHealMod.regi;

import com.Cell_SINON.InstantHealMod.main.InstantHealMod;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class InstantHealModPotions {    public static final DeferredRegister<Potion> POTIONS
        = DeferredRegister.create(ForgeRegistries.POTIONS, InstantHealMod.MOD_id);




    public static final RegistryObject<Potion> HEROPOTION = POTIONS.register("heropotion",
            ()-> new Potion(new MobEffectInstance(MobEffects.HERO_OF_THE_VILLAGE, 20 * 3600 * 72, 2, true, false, true)){


            });

    public static void register(IEventBus eventBus){
        POTIONS.register(eventBus);
    }



}
