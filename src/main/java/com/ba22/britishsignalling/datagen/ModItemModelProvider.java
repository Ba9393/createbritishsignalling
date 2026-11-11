package com.ba22.britishsignalling.datagen;

import com.ba22.britishsignalling.CreateBritishSignalling;
import net.minecraft.data.PackOutput;
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

    }
    
}
