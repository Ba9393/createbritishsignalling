package com.ba22.britishsignalling.blockentity;

import com.ba22.britishsignalling.block.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class RemoteSignalControllerBlockEntity extends BlockEntity {

    private BlockPos targetSignal;
    private String targetDimension;

    public RemoteSignalControllerBlockEntity(
            BlockPos pos,
            BlockState state
    ) {
        super(ModBlockEntities.REMOTE_SIGNAL_CONTROLLER.get(), pos, state);
    }

    public BlockPos getTargetSignal() {
        return targetSignal;
    }

    public String getTargetDimension() {
        return targetDimension;
    }

    public void setTargetSignal(BlockPos pos, String dimension) {
        this.targetSignal = pos;
        this.targetDimension = dimension;
        setChanged();
    }

    @Override
    protected void saveAdditional(
            CompoundTag tag,
            HolderLookup.Provider registries
    ) {
        super.saveAdditional(tag, registries);

        if (targetSignal != null) {
            tag.putLong("TargetSignal", targetSignal.asLong());
            tag.putString("TargetDimension", targetDimension);
        }
    }

    @Override
    protected void loadAdditional(
            CompoundTag tag,
            HolderLookup.Provider registries
    ) {
        super.loadAdditional(tag, registries);

        if (tag.contains("TargetSignal")) {
            targetSignal = BlockPos.of(tag.getLong("TargetSignal"));
            targetDimension = tag.getString("TargetDimension");
        }
    }
}