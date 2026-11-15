package com.ba22.britishsignalling.block;

import com.ba22.britishsignalling.blockentity.ModBlockEntities;
import com.ba22.britishsignalling.blockentity.RemoteTrainDetectorBlockEntity;
import com.mojang.serialization.MapCodec;
import com.simibubi.create.content.equipment.wrench.IWrenchable;
import com.simibubi.create.foundation.block.IBE;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DirectionProperty;

public class RemoteTrainDetector extends Block implements IBE<RemoteTrainDetectorBlockEntity>, IWrenchable {

    public RemoteTrainDetector(Properties properties) {
        super(properties);
    }

    @Override
    public Class<RemoteTrainDetectorBlockEntity> getBlockEntityClass() {
        return RemoteTrainDetectorBlockEntity.class;
    }

    @Override
    public BlockEntityType<? extends RemoteTrainDetectorBlockEntity> getBlockEntityType() {
        return ModBlockEntities.REMOTE_TRAIN_DETECTOR.get();
    }
}
