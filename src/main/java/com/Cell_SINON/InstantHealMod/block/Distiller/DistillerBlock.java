package com.Cell_SINON.InstantHealMod.block.Distiller;

import com.Cell_SINON.InstantHealMod.block.ChemicalReactor.ChemicalReactorBlockEntity;
import com.Cell_SINON.InstantHealMod.block.Electrolysis.ElectrolysisBlockEntity;
import com.Cell_SINON.InstantHealMod.block.Pyrolysis.PyrolyzerBlockEntity;
import com.Cell_SINON.InstantHealMod.regi.InstantHealModBlocks;
import com.Cell_SINON.InstantHealMod.regi.InstantHealModEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.Containers;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.network.NetworkHooks;
import org.jetbrains.annotations.Nullable;

import javax.annotation.Nonnull;
import java.awt.*;

public class DistillerBlock extends BaseEntityBlock {

    public DistillerBlock() {
        super(Properties.of().strength(2.5F, 500F).sound(SoundType.STONE));
    }

    @Override
    public @Nullable BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new DistillerBlockEntity(pos, state);
    }

    @Override
    public RenderShape getRenderShape(BlockState p_49232_) {

        return RenderShape.MODEL;
    }

    @Override
    public void onRemove(BlockState state, Level world, BlockPos pos, BlockState blockState, boolean bool) {
        super.onRemove(state, world, pos, blockState, bool);
        BlockEntity te = world.getBlockEntity(pos);
        DistillerBlockEntity entity = (DistillerBlockEntity)te;
        Containers.dropContents(world, pos, entity);

    }

    @Override
    public InteractionResult use(BlockState state, Level world, BlockPos pos, Player player, InteractionHand hand, BlockHitResult result) {
        BlockEntity be = world.getBlockEntity(pos);
        DistillerBlockEntity db = (DistillerBlockEntity) be;

        if (player.isSteppingCarefully()) {

        }
        if (!world.isClientSide) {
            MenuProvider provider = this.getMenuProvider(state, world, pos);
            player.openMenu(provider);
            MenuProvider providerd = this.getMenuProvider(state, world, pos);
            player.openMenu(providerd);
        }

        return InteractionResult.SUCCESS;
    }
}












