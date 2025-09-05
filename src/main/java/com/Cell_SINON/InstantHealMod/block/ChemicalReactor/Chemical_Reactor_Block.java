package com.Cell_SINON.InstantHealMod.block.ChemicalReactor;

import com.Cell_SINON.InstantHealMod.Recipe.ChemicalReactionRecipe;
import com.Cell_SINON.InstantHealMod.Recipe.ModRecipeTypes;
import com.Cell_SINON.InstantHealMod.block.ChemicalReactor.ChemicalReactorBlockEntity;
import com.Cell_SINON.InstantHealMod.block.Electrolysis.ElectrolysisBlockEntity;
import com.Cell_SINON.InstantHealMod.regi.InstantHealModEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.util.RandomSource;
import net.minecraft.world.Containers;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.FurnaceBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.network.NetworkHooks;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public class Chemical_Reactor_Block extends BaseEntityBlock {


    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level pLevel, BlockState pState, BlockEntityType<T> pBlockEntityType) {
        if (pLevel.isClientSide()) {
            return null;
        }
        return createTickerHelper(pBlockEntityType, InstantHealModEntities.CHEMICAL_REACTOR_BLOCK_ENTITY.get(), ChemicalReactorBlockEntity::tick);
    }



    public Chemical_Reactor_Block() {
        super(Properties.of().strength(2.5F, 500F).sound(SoundType.STONE));
    }

    @Override
    public @Nullable BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new ChemicalReactorBlockEntity(pos, state);
    }

    @Override
    public RenderShape getRenderShape(BlockState p_49232_) {

        return RenderShape.MODEL;
    }

    @Override
    public void onRemove(BlockState state, Level world, BlockPos pos, BlockState blockState, boolean bool) {
        BlockEntity te = world.getBlockEntity(pos);
        ChemicalReactorBlockEntity entity = (ChemicalReactorBlockEntity)te;
        super.onRemove(state, world, pos, blockState, bool);
        Containers.dropContents(world, pos, entity);


    }

    @Override
    public InteractionResult use(BlockState state, Level world, BlockPos pos, Player player, InteractionHand hand, BlockHitResult result) {
        BlockEntity be = world.getBlockEntity(pos);
        ChemicalReactorBlockEntity cr = (ChemicalReactorBlockEntity) be;

        if (player.isSteppingCarefully()) {

        }
        if (!world.isClientSide) {
            MenuProvider providerc = this.getMenuProvider(state, world, pos);
            player.openMenu(providerc);
        }

        return InteractionResult.SUCCESS;
    }}




