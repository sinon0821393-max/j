package com.Cell_SINON.InstantHealMod.regi.tab;

import com.Cell_SINON.InstantHealMod.main.InstantHealMod;
import com.Cell_SINON.InstantHealMod.regi.InstantHealModBlocks;
import com.Cell_SINON.InstantHealMod.regi.InstantHealModItems;
import com.Cell_SINON.InstantHealMod.regi.InstantHealModPotions;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.eventbus.api.IEventBus;

public class InstantHealTabs {

    public static final DeferredRegister<CreativeModeTab> MOD_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, InstantHealMod.MOD_id);
    public static final RegistryObject<CreativeModeTab> INSTANTHEAL_MAIN = MOD_TABS.register("instantheal_main",
            () -> CreativeModeTab.builder()
                    .icon(() -> new ItemStack(InstantHealModItems.INSTANTRECOVERYAGENTS.get()))
                    .title(Component.translatable("itemGroup.instantheal_main"))
                    .displayItems((param, output) -> {
                             // --- アイテムの追加
                            output.accept(InstantHealModItems.INSTANTRECOVERYAGENTS.get());
                            output.accept(InstantHealModItems.HEROITEM.get());
                            output.accept(InstantHealModItems.MATERIAL.get());
                            output.accept(InstantHealModItems.FERMENTED_LIQUID_BUCKET.get());
                            output.accept(InstantHealModItems.CHLORINE_BOTTLE.get());
                            output.accept(InstantHealModItems.HYDROGEN_BOTTLE.get());
                            output.accept(InstantHealModItems.ETHANOL_BOTTLE.get());
                            output.accept(InstantHealModItems.WASTE_LIQUID_BUCKET.get());
                            output.accept(InstantHealModItems.WASTE_ITEM.get());

                        // --- ブロックの追加 ---
                        output.accept(InstantHealModItems.FERMENTATIONBLOCkITEM.get());
                        output.accept(InstantHealModItems.ELECTROLYSIS_BLOCK_ITEM.get());
                        output.accept(InstantHealModItems.DISTILLER_BLOCK_ITEM.get());
                        output.accept(InstantHealModItems.CHEMICAL_REACTOR_BLOCK_ITEM.get());
                        output.accept(InstantHealModItems.PYROLYZER_BLOCK_ITEM.get());


                        output.accept(PotionUtils.setPotion(new ItemStack(Items.POTION), InstantHealModPotions.HEROPOTION.get()));
                        ;
                    })
                    .build());
    public static void register(IEventBus eventBus) {
        MOD_TABS.register(eventBus);
    }
}
