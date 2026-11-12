package com.ba22.britishsignalling.datagen;

import com.ba22.britishsignalling.CreateBritishSignalling;
import net.minecraft.data.PackOutput;
import net.minecraft.world.item.ItemDisplayContext;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

/**
 * This class is responsible for generating item models for the mod.
 * It extends ItemModelProvider.
 */

public class ModItemModelProvider extends ItemModelProvider {
    
    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, CreateBritishSignalling.MODID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        withExistingParent(
                "four_aperture_signal",
                modLoc("block/four_aperture_signal")
        ).transforms()
                .transform(ItemDisplayContext.GUI) //todo: fix ts
                .rotation(30, 45, 0)
                .translation(4, -1.15F, 0)
                .scale(1)
                .end()
                .transform(ItemDisplayContext.GROUND)
                .rotation(0, 0, 0)
                .translation(0, 2, 2.2F)
                .scale(0.5F)
                .end()
                .transform(ItemDisplayContext.FIXED)
                .rotation(0, 180, 0)
                .scale(0.5F)
                .end()
                .transform(ItemDisplayContext.THIRD_PERSON_RIGHT_HAND)
                .rotation(0, 0, 0) // 75
                .translation(0,  2, 3)
                .scale(0.375F)
                .end()
                .transform(ItemDisplayContext.FIRST_PERSON_RIGHT_HAND)
                .rotation(0, 0, 0)
                .translation(0, 2.5F, 5)
                .scale(0.6F)
                .end();
    }
    
}
