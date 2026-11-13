package com.ba22.britishsignalling.block;

import com.mojang.serialization.MapCodec;
import com.simibubi.create.content.equipment.wrench.IWrenchable;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class Signal_Girder extends HorizontalDirectionalBlock implements IWrenchable {

    private static final VoxelShape NORTH_SHAPE = Shapes.or(
            Block.box(3, 0, 3, 13, 16, 13),
            Block.box(0, 4, 6, 16, 12, 10),
            Block.box(4, 2, 0, 12, 14, 16)
    );

    private static final VoxelShape EAST_SHAPE = Shapes.or(
            Block.box(3, 0, 3, 13, 16, 13),
            Block.box(6, 4, 0, 10, 12, 16),
            Block.box(0, 2, 4, 16, 14, 12)
    );

    private static final VoxelShape SOUTH_SHAPE = Shapes.or(
            Block.box(3, 0, 3, 13, 16, 13),
            Block.box(0, 4, 6, 16, 12, 10),
            Block.box(4, 2, 0, 12, 14, 16)
    );

    private static final VoxelShape WEST_SHAPE = Shapes.or(
            Block.box(3, 0, 3, 13, 16, 13),
            Block.box(6, 4, 0, 10, 12, 16),
            Block.box(0, 2, 4, 16, 14, 12)
    );

    @Override
    public VoxelShape getShape(
            BlockState state,
            BlockGetter level,
            BlockPos pos,
            CollisionContext context) {

        return switch (state.getValue(FACING)) {
            case NORTH -> NORTH_SHAPE;
            case SOUTH -> SOUTH_SHAPE;
            case EAST -> EAST_SHAPE;
            case WEST -> WEST_SHAPE;
            default -> NORTH_SHAPE;
        };
    }

    @Override
    public VoxelShape getCollisionShape(
            BlockState state,
            BlockGetter level,
            BlockPos pos,
            CollisionContext context) {

        return switch (state.getValue(FACING)) {
            case NORTH -> NORTH_SHAPE;
            case SOUTH -> SOUTH_SHAPE;
            case EAST -> EAST_SHAPE;
            case WEST -> WEST_SHAPE;
            default -> NORTH_SHAPE;
        };
    }

    public static final DirectionProperty FACING =
            BlockStateProperties.HORIZONTAL_FACING;

    public Signal_Girder(Properties properties) {
        super(properties);

        registerDefaultState(
                stateDefinition.any().setValue(FACING, Direction.NORTH)
        );
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
                context.getHorizontalDirection()
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
}
