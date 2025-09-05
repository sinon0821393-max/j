package com.Cell_SINON.InstantHealMod.main;
import com.Cell_SINON.InstantHealMod.Recipe.ModRecipeSerializers;
import com.Cell_SINON.InstantHealMod.Recipe.ModRecipeTypes;
import com.Cell_SINON.InstantHealMod.regi.*;
import com.Cell_SINON.InstantHealMod.regi.tab.InstantHealTabs;
import com.Cell_SINON.InstantHealMod.screen.FermentationScreen;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(InstantHealMod.MOD_id)
public class InstantHealMod {
    public static final String MOD_id = "instanthealmod";

    public InstantHealMod() {


        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        InstantHealModBlocks.register(modEventBus);
        // 各登録クラスのregisterメソッドを呼び出す
        InstantHealModPotions.register(modEventBus);
        InstantHealModItems.register(modEventBus);


        ModRecipeTypes.register(modEventBus);
        ModRecipeSerializers.register(modEventBus);
        InstantHealTabs.register(modEventBus);
        InstantHealModMenuTypes.MENU_TYPES.register(modEventBus);
        InstantHealModEntities.BLOCK_ENTITY_TYPES.register(modEventBus);



        modEventBus.register(new PotionsRecipes());
    }

}
