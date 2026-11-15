package com.ba22.britishsignalling.blockentity;

import com.simibubi.create.foundation.blockEntity.SmartBlockEntity;
import com.simibubi.create.foundation.blockEntity.behaviour.BlockEntityBehaviour;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import com.simibubi.create.content.trains.graph.EdgePointType;
import com.simibubi.create.content.trains.signal.SignalBoundary;
import com.simibubi.create.content.trains.track.TrackTargetingBehaviour;

import java.util.List;

public class RemoteTrainDetectorBlockEntity extends SmartBlockEntity {
    public TrackTargetingBehaviour<SignalBoundary> edgePoint;

    public RemoteTrainDetectorBlockEntity(
            BlockPos pos,
            BlockState state
    ) {
        super(
                ModBlockEntities.REMOTE_TRAIN_DETECTOR.get(),
                pos,
                state
        );
    }

    @Override
    public void addBehaviours(List<BlockEntityBehaviour> behaviours) {
        behaviours.add(
                edgePoint = new TrackTargetingBehaviour<>(
                        this,
                        EdgePointType.SIGNAL
                )
        );
    }
}
