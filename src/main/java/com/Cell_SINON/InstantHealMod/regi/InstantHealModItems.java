
package com.Cell_SINON.InstantHealMod.regi;

import com.Cell_SINON.InstantHealMod.item.FoodEmeraldApple;
import com.Cell_SINON.InstantHealMod.item.Heroitem;
import com.Cell_SINON.InstantHealMod.item.ItemInstantRecoveryagents;
import com.Cell_SINON.InstantHealMod.main.InstantHealMod;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class InstantHealModItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, InstantHealMod.MOD_id);



    public static final RegistryObject<Item> INSTANTRECOVERYAGENTS = ITEMS.register("instantrecoveryagents",
            () -> new ItemInstantRecoveryagents(new Item.Properties()
                    .stacksTo(20)));
    public static final RegistryObject<Item> HEROITEM = ITEMS.register("heroitem", ()->new FoodEmeraldApple(new Item.Properties().food(Heroitem.HEROITEM)));

    public static final RegistryObject<Item> MATERIAL = ITEMS.register("material",
            () -> new Item(new Item.Properties().stacksTo(20)));

    public static final RegistryObject<Item> CHLORINE_BOTTLE = ITEMS.register("chlorine_bottle",
            () -> new Item(new Item.Properties()
                    .stacksTo(1) // 1. スタックサイズを1に設定
                    .craftRemainder(Items.GLASS_BOTTLE) // 2. クラフト後にガラス瓶を返す (任意)
            ));

    public static final RegistryObject<Item> HYDROGEN_BOTTLE = ITEMS.register("hydrogen_bottle",
            () -> new Item(new Item.Properties()
                    .stacksTo(1) // 1. スタックサイズを1に設定
                    // こちらは飲んだら瓶ごと消えるように、craftRemainderを設定しない
            ));

    public static final RegistryObject<Item> WASTE_ITEM = ITEMS.register("waste_item",
            () -> new Item(new Item.Properties()));

    // 発酵液入りバケツ (スタック不可、使用後に空バケツを返すアイテムとして登録)
    public static final RegistryObject<Item> FERMENTED_LIQUID_BUCKET = ITEMS.register("fermented_liquid_bucket",
            () -> new Item(new Item.Properties()
                    .stacksTo(1) // 1. スタックサイズを1に設定
                    .craftRemainder(Items.BUCKET) // 2. クラフト等で消費された後、空のバケツを返す
            ));

    // 仮ID: ethanol_bottle
    public static final RegistryObject<Item> ETHANOL_BOTTLE = ITEMS.register("ethanol_bottle",
            () -> new Item(new Item.Properties().stacksTo(1).craftRemainder(Items.GLASS_BOTTLE)));

    // 仮ID: waste_liquid_bucket
    public static final RegistryObject<Item> WASTE_LIQUID_BUCKET = ITEMS.register("waste_liquid_bucket",
            () -> new Item(new Item.Properties().stacksTo(1).craftRemainder(Items.BUCKET)));

    public static final RegistryObject<Item> FERMENTATIONBLOCkITEM = ITEMS.register("fermentationblock",
            ()->new BlockItem(InstantHealModBlocks.FERMENTATIONBLOCK.get(), new Item.Properties()));

    public static final RegistryObject<Item> ELECTROLYSIS_BLOCK_ITEM  = ITEMS.register("electrolysis_block",
            ()->new BlockItem(InstantHealModBlocks.ELECTROLYSIS_BLOCK.get(), new Item.Properties()));

    public static final RegistryObject<Item> DISTILLER_BLOCK_ITEM = ITEMS.register("distiller_block",
            ()->new BlockItem(InstantHealModBlocks.DISTILlER_BLOCK.get(), new Item.Properties()));

    public static final RegistryObject<Item> CHEMICAL_REACTOR_BLOCK_ITEM = ITEMS.register("chemical_reactor_block",
            ()->new BlockItem(InstantHealModBlocks.CHEMICAL_REACTOR_BLOCK.get(), new Item.Properties()));

    public static final RegistryObject<Item> PYROLYZER_BLOCK_ITEM  = ITEMS.register("pyrolyzer_block",
            ()->new BlockItem(InstantHealModBlocks.PYROLYSYS_BLOCK.get(), new Item.Properties()));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }



    private static <T extends Block> RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block) {
        return ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }


    public static final String MOD_ID = "instanthealmod";




}