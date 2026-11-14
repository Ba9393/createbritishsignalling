package com.ba22.britishsignalling.block;

import com.ba22.britishsignalling.block.fourapsignal.SignalAspect;
import com.ba22.britishsignalling.blockentity.RemoteSignalControllerBlockEntity;
import com.mojang.serialization.MapCodec;
import com.simibubi.create.content.equipment.wrench.IWrenchable;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.CustomData;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.phys.BlockHitResult;

public class RemoteSignalController extends HorizontalDirectionalBlock implements IWrenchable, EntityBlock {
    public static final DirectionProperty FACING =
            BlockStateProperties.HORIZONTAL_FACING;

    public RemoteSignalController(Properties properties) {
        super(properties);

        registerDefaultState(
                stateDefinition.any().setValue(FACING, Direction.NORTH)
        );
    }

    @Override
    public void setPlacedBy(
            Level level,
            BlockPos pos,
            BlockState state,
            LivingEntity placer,
            ItemStack stack
    ) {
        super.setPlacedBy(level, pos, state, placer, stack);

        if (level.isClientSide) return;

        if (level.getBlockEntity(pos)
                instanceof RemoteSignalControllerBlockEntity blockEntity) {

            var data = stack.get(DataComponents.CUSTOM_DATA);

            if (data != null) {
                var tag = data.copyTag();

                if (tag.contains("TargetX")
                        && tag.contains("TargetY")
                        && tag.contains("TargetZ")) {

                    BlockPos target = new BlockPos(
                            tag.getInt("TargetX"),
                            tag.getInt("TargetY"),
                            tag.getInt("TargetZ")
                    );

                    String dimension = tag.getString("TargetDimension");

                    blockEntity.setTargetSignal(target, dimension);
                    stack.set(DataComponents.CUSTOM_DATA, CustomData.EMPTY);
                }
            }
        }
    }

    private void updateLinkedSignal(
            Level level,
            BlockPos controllerPos,
            BlockPos targetPos,
            String targetDimension,
            Direction inputDirection,
            SignalAspect aspect
    ) {
        BlockPos inputPos = controllerPos.relative(inputDirection);

        if (level.getSignal(inputPos, inputDirection.getOpposite()) == 0) {
            return;
        }

        if (!level.dimension().location().toString().equals(targetDimension)) {
            return;
        }

        if (!level.hasChunkAt(targetPos)) return;

        BlockState targetState = level.getBlockState(targetPos);

        if (targetState.is(ModBlocks.FOUR_AP_SIGNAL.get())) {
            level.setBlock(
                    targetPos,
                    targetState.setValue(Four_Aperture_Signal.ASPECT, aspect),
                    2
            );
        }
    }

    @Override
    public void neighborChanged(
            BlockState state,
            Level level,
            BlockPos pos,
            Block neighborBlock,
            BlockPos neighborPos,
            boolean movedByPiston
    ) {
        super.neighborChanged(
                state, level, pos, neighborBlock, neighborPos, movedByPiston
        );

        if (level.isClientSide) return;

        if (!(level.getBlockEntity(pos)
                instanceof RemoteSignalControllerBlockEntity blockEntity)) {
            return;
        }

        BlockPos target = blockEntity.getTargetSignal();
        String dimension = blockEntity.getTargetDimension();

        if (target == null || dimension == null) return;

        Direction front = state.getValue(HorizontalDirectionalBlock.FACING);
        Direction right = front.getClockWise();
        Direction back = front.getOpposite();
        Direction left = front.getCounterClockWise();

        updateLinkedSignal(level, pos, target, dimension,
                left, SignalAspect.PROCEED);

        updateLinkedSignal(level, pos, target, dimension,
                back, SignalAspect.PRELIMINARY_CAUTION);

        updateLinkedSignal(level, pos, target, dimension,
                right, SignalAspect.CAUTION);

        updateLinkedSignal(level, pos, target, dimension,
                front, SignalAspect.DANGER);
    }

    @Override
    protected MapCodec<? extends HorizontalDirectionalBlock> codec() {
        return null;
    }

    @Override
    public BlockState getStateForPlacement(
            net.minecraft.world.item.context.BlockPlaceContext context) {

        return defaultBlockState().setValue(
                FACING,
                context.getHorizontalDirection().getOpposite()
        );
    }

    @Override
    protected void createBlockStateDefinition(
            StateDefinition.Builder<net.minecraft.world.level.block.Block, BlockState> builder) {
        builder.add(FACING);
    }

    @Override
    public InteractionResult onWrenched(BlockState state, UseOnContext context) {
        Level level = context.getLevel();

        if (!level.isClientSide) {
            level.setBlock(
                    context.getClickedPos(),
                    state.rotate(Rotation.CLOCKWISE_90),
                    3
            );
        }

        return InteractionResult.SUCCESS;
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new RemoteSignalControllerBlockEntity(pos, state);
    }
}
