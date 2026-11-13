package com.ba22.britishsignalling.datagen;

import com.ba22.britishsignalling.block.ModBlocks;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.block.Block;

import java.util.Set;

/**
 * This class is responsible for generating loot tables for the mod's blocks.
 * What happens when a block is broken, and what items it drops, is defined in loot tables.
 */

public class ModBlockLootTableProvider extends BlockLootSubProvider {

    protected ModBlockLootTableProvider(HolderLookup.Provider registries) {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags(), registries);
    }

    @Override
    protected void generate() {

        dropSelf(ModBlocks.FOUR_AP_SIGNAL.get());
        dropSelf(ModBlocks.POLE.get());
        dropSelf(ModBlocks.SIGNAL_GIRDER.get());
        dropSelf(ModBlocks.REMOTE_SIGNAL_CONTROLLER.get());

    }
    

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return ModBlocks.BLOCKS.getEntries().stream().map(Holder::value)::iterator;
    }
}

    
