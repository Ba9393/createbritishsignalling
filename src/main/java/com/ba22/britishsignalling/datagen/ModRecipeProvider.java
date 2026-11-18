package com.ba22.britishsignalling.datagen;

import com.ba22.britishsignalling.item.ModItems;
import com.simibubi.create.AllBlockEntityTypes;
import com.simibubi.create.AllBlocks;
import com.simibubi.create.AllItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.material.Fluids;
import net.neoforged.neoforge.common.conditions.IConditionBuilder;
import net.neoforged.neoforge.fluids.FluidStack;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.CompletableFuture;

/**
 * This class is responsible for generating recipes for the mod.
 * It extends RecipeProvider and defines the recipes for items and blocks in the mod.
 */

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {
    
    public ModRecipeProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries);
    }

    @Override
    protected void buildRecipes(@NotNull RecipeOutput recipeOutput) {
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.SIGNAL_BLOCK.get(), 8)
                .pattern("212")
                .pattern("232")
                .pattern("232")
                .define('1', Items.REDSTONE)
                .define('2', Items.IRON_INGOT)
                .define('3', Items.GLOWSTONE_DUST)
                .unlockedBy("has_redstone", has(Items.REDSTONE))
                .save(recipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.POLE.get(), 4)
                .requires(Items.IRON_INGOT)
                .requires(Items.IRON_INGOT)
                .unlockedBy("has_iron_ingot", has(Items.IRON_INGOT))
                .save(recipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.SIGNAL_GIRDER.get(), 2)
                .requires(AllBlocks.METAL_GIRDER.get(), 2)
                .requires(Items.IRON_INGOT)
                .unlockedBy("has_iron_ingot", has(Items.IRON_INGOT))
                .save(recipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.REMOTE_SIGNAL_CONTROLLER.get(), 4)
                .pattern("212")
                .pattern("232")
                .pattern("232")
                .define('1', AllBlocks.REDSTONE_LINK.get())
                .define('2', Items.IRON_INGOT)
                .define('3', Items.REDSTONE)
                .unlockedBy("has_iron_ingot", has(Items.IRON_INGOT))
                .save(recipeOutput);
    }
}
