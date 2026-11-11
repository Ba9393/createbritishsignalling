package com.ba22.britishsignalling.block;

import com.ba22.britishsignalling.block.fourapsignal.SignalAspect;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class Four_Aperture_Signal extends HorizontalDirectionalBlock {

    private static final VoxelShape NORTH_SHAPE = Shapes.or(
            Block.box(5, 0, 11, 11, 14.75, 14.5)
    );

    private static final VoxelShape SOUTH_SHAPE = Shapes.or(
            Block.box(5, 0, 1.5, 11, 14.75, 5)
    );

    private static final VoxelShape EAST_SHAPE = Shapes.or(
            Block.box(1.5, 0, 5, 5, 14.75, 11)
    );

    private static final VoxelShape WEST_SHAPE = Shapes.or(
            Block.box(11, 0, 5, 14.5, 14.75, 11)
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

    public static final EnumProperty<SignalAspect> ASPECT =
            EnumProperty.create("aspect", SignalAspect.class);

    public static final DirectionProperty FACING =
            BlockStateProperties.HORIZONTAL_FACING;

    public Four_Aperture_Signal(Properties properties) {
        super(properties);

        registerDefaultState(
                stateDefinition.any()
                        .setValue(FACING, Direction.NORTH)
                        .setValue(ASPECT, SignalAspect.DANGER)
        );
    }

    @Override
    protected MapCodec<? extends HorizontalDirectionalBlock> codec() {
        return null;
    } // idk

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return defaultBlockState().setValue(
                FACING,
                context.getHorizontalDirection().getOpposite()
        );
    }

    @Override
    protected void createBlockStateDefinition(
            StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING, ASPECT);
    }

}
