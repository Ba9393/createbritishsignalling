package com.ba22.britishsignalling.datagen;

import com.ba22.britishsignalling.CreateBritishSignalling;
import com.ba22.britishsignalling.block.ModBlocks;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

/**
 * This class is responsible for generating block tags for the mod.
 * It extends BlockTagsProvider and defines the tags for blocks in the mod.
 */

public class ModBlockTagProvider extends BlockTagsProvider {
    public ModBlockTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, CreateBritishSignalling.MODID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.@NotNull Provider provider) {
        tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(ModBlocks.FOUR_AP_SIGNAL.get())
                .add(ModBlocks.POLE.get())
                .add(ModBlocks.SIGNAL_GIRDER.get())
                .add(ModBlocks.REMOTE_SIGNAL_CONTROLLER.get());

        tag(BlockTags.create(ResourceLocation.fromNamespaceAndPath("create", "wrench_pickup")))
                .add(ModBlocks.FOUR_AP_SIGNAL.get())
                .add(ModBlocks.SIGNAL_GIRDER.get())
                .add(ModBlocks.REMOTE_SIGNAL_CONTROLLER.get());
        
    }
}
