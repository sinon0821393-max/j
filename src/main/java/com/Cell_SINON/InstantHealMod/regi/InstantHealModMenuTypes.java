package com.Cell_SINON.InstantHealMod.regi;

import com.Cell_SINON.InstantHealMod.block.ChemicalReactor.ChemicalReactorMenu;

import com.Cell_SINON.InstantHealMod.block.Distiller.DistillerMenu;
import com.Cell_SINON.InstantHealMod.block.Electrolysis.ElectrolysisMenu;
import com.Cell_SINON.InstantHealMod.block.Pyrolysis.PyrolysisMenu;
import com.Cell_SINON.InstantHealMod.block.fermentation.FermentationMenu;
import com.Cell_SINON.InstantHealMod.main.InstantHealMod;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class InstantHealModMenuTypes {
    public static final DeferredRegister<MenuType<?>> MENU_TYPES =
            DeferredRegister.create(ForgeRegistries.MENU_TYPES, InstantHealMod.MOD_id);

    public static final RegistryObject<MenuType<FermentationMenu>> FERMENTATION_MENU =
            MENU_TYPES.register("fermentation_menu", ()-> set(FermentationMenu::new));

    public static final RegistryObject<MenuType<ElectrolysisMenu>> ELECTROLYSISMENU =
            MENU_TYPES.register("electrolysismenu", ()-> set(ElectrolysisMenu::new));


    public static final RegistryObject<MenuType<DistillerMenu>> DISTILLER_MENU =
            MENU_TYPES.register("distiller_menu", ()-> set(DistillerMenu::new));

    public static final RegistryObject<MenuType<ChemicalReactorMenu>> CHEMICAL_REACTOR_MENU =
            MENU_TYPES.register("chemical_reactor_menu", ()-> set(ChemicalReactorMenu::new));

    public static final RegistryObject<MenuType<PyrolysisMenu>> PYROLYSIS_MENU =
            MENU_TYPES.register("pyrolysis_menu", ()-> set(PyrolysisMenu::new));



    public static <T extends AbstractContainerMenu> MenuType<T> set(MenuType.MenuSupplier<T> supplier){
        return new MenuType<>(supplier, FeatureFlags.REGISTRY.allFlags());
    }
}
