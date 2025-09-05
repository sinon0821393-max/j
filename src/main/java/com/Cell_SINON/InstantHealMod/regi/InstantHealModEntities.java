package com.Cell_SINON.InstantHealMod.regi;

import com.Cell_SINON.InstantHealMod.block.ChemicalReactor.ChemicalReactorBlockEntity;

import com.Cell_SINON.InstantHealMod.block.Distiller.DistillerBlockEntity;
import com.Cell_SINON.InstantHealMod.block.Electrolysis.ElectrolysisBlockEntity;
import com.Cell_SINON.InstantHealMod.block.Pyrolysis.PyrolyzerBlockEntity;
import com.Cell_SINON.InstantHealMod.block.fermentation.FermentationBlockEntity;
import com.Cell_SINON.InstantHealMod.main.InstantHealMod;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;


public class InstantHealModEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITY_TYPES =
            DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, InstantHealMod.MOD_id);

    public static final RegistryObject<BlockEntityType<ChemicalReactorBlockEntity>> CHEMICAL_REACTOR_BLOCK_ENTITY =
            BLOCK_ENTITY_TYPES.register("chemical_reactor_block_entity", ()->set(ChemicalReactorBlockEntity::new, InstantHealModBlocks.CHEMICAL_REACTOR_BLOCK.get()));

    public static final RegistryObject<BlockEntityType<DistillerBlockEntity>> DISTILLER_BLOCK_ENTITY =
            BLOCK_ENTITY_TYPES.register("distiller_block_entity", ()->set(DistillerBlockEntity::new, InstantHealModBlocks.DISTILlER_BLOCK.get()));

    public static final RegistryObject<BlockEntityType<ElectrolysisBlockEntity>> ELECTROLYSIS_BLOCK_ENTITY =
            BLOCK_ENTITY_TYPES.register("electrolysis_block_entity", ()->set(ElectrolysisBlockEntity::new, InstantHealModBlocks.ELECTROLYSIS_BLOCK.get()));

    public static final RegistryObject<BlockEntityType<FermentationBlockEntity>> FERMENTATION_BLOCK_ENTITY =
            BLOCK_ENTITY_TYPES.register("fermentation_block_entity", ()->set(FermentationBlockEntity::new, InstantHealModBlocks.FERMENTATIONBLOCK.get()));

    public static final RegistryObject<BlockEntityType<PyrolyzerBlockEntity>> PYROLYZER_BLOCK_ENTITY =
            BLOCK_ENTITY_TYPES.register("pyrolyzer_block_entity", ()->set(PyrolyzerBlockEntity::new, InstantHealModBlocks.PYROLYSYS_BLOCK.get()));

     private static <T extends BlockEntity> BlockEntityType<T> set(BlockEntityType.BlockEntitySupplier<T> entity, Block block) {
         return BlockEntityType.Builder.of(entity, block).build(null);
     }





}
