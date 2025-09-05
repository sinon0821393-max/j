package com.Cell_SINON.InstantHealMod.event;

import com.Cell_SINON.InstantHealMod.main.InstantHealMod;
import com.Cell_SINON.InstantHealMod.regi.InstantHealModMenuTypes;
import com.Cell_SINON.InstantHealMod.screen.*;

import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = InstantHealMod.MOD_id, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)

public class ModClientEvents {


    /**
     * FMLClientSetupEventは、クライアントの基本的なセットアップが完了したタイミングで呼び出されます。
     * ScreenとMenuの紐付けは、このタイミングで行うのが最も安全です。
     */
    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event) {
        blolckScreenRegister();
    }

    private static void blolckScreenRegister(){
        MenuScreens.register(InstantHealModMenuTypes.CHEMICAL_REACTOR_MENU.get(), ChemicalReactorScreen::new);
        MenuScreens.register(InstantHealModMenuTypes.PYROLYSIS_MENU.get(), PyrolysisScreen::new);
        MenuScreens.register(InstantHealModMenuTypes.DISTILLER_MENU.get(), DistillerScreen::new);
        MenuScreens.register(InstantHealModMenuTypes.ELECTROLYSISMENU.get(), ElectrolysisScreen::new);
        MenuScreens.register(InstantHealModMenuTypes.FERMENTATION_MENU.get(), FermentationScreen ::new);





    }



    }
