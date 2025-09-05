package com.Cell_SINON.InstantHealMod.regi;

import com.Cell_SINON.InstantHealMod.block.ChemicalReactor.Chemical_Reactor_Block;
import com.Cell_SINON.InstantHealMod.block.Distiller.DistillerBlock;
import com.Cell_SINON.InstantHealMod.block.Electrolysis.ElectrolysisBlock;
import com.Cell_SINON.InstantHealMod.block.Pyrolysis.PyrolysisBlock;
import com.Cell_SINON.InstantHealMod.block.fermentation.FermentationBlock;
import com.Cell_SINON.InstantHealMod.main.InstantHealMod;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class InstantHealModBlocks {

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, InstantHealMod.MOD_id);

    public static final RegistryObject<Block> FERMENTATIONBLOCK = BLOCKS.register("fermentationblock", FermentationBlock::new);

    public static final RegistryObject<Block> ELECTROLYSIS_BLOCK = BLOCKS.register("electrolysis_block", ElectrolysisBlock::new);

    public static final RegistryObject<Block> DISTILlER_BLOCK = BLOCKS.register("distiller_block", DistillerBlock::new);

    public static final RegistryObject<Block> CHEMICAL_REACTOR_BLOCK = BLOCKS.register("chemical_reactor_block", Chemical_Reactor_Block::new);

    public static final RegistryObject<Block> PYROLYSYS_BLOCK = BLOCKS.register("pyrolysis_block", PyrolysisBlock::new);









    public static void register(IEventBus eventBus) {

        BLOCKS.register(eventBus);
    }
 }
