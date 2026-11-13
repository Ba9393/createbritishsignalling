package com.ba22.britishsignalling.datagen;

import com.ba22.britishsignalling.CreateBritishSignalling;
import net.minecraft.data.PackOutput;
import net.minecraft.world.item.ItemDisplayContext;
import net.neoforged.neoforge.client.model.generators.ItemModelBuilder;
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

    private void standardBlockTransforms(ItemModelBuilder model) {
        model.transforms()
                .transform(ItemDisplayContext.GUI)
                .rotation(30, 225, 0)
                .translation(0, 0, 0)
                .scale(0.625f)
                .end()
                .transform(ItemDisplayContext.GROUND)
                .translation(0, 3, 0)
                .scale(0.25f)
                .end()
                .transform(ItemDisplayContext.FIXED)
                .scale(0.5f)
                .end()
                .transform(ItemDisplayContext.THIRD_PERSON_RIGHT_HAND)
                .rotation(75, 45, 0)
                .translation(0, 2.5f, 0)
                .scale(0.375f)
                .end()
                .transform(ItemDisplayContext.FIRST_PERSON_RIGHT_HAND)
                .rotation(0, 45, 0)
                .scale(0.4f)
                .end()
                .transform(ItemDisplayContext.FIRST_PERSON_LEFT_HAND)
                .rotation(0, 225, 0)
                .scale(0.4f)
                .end()
                .end();
    }

    @Override
    protected void registerModels() {
        withExistingParent(
                "four-aperture-signal",
                modLoc("block/four_aperture_signal")
        ).transforms()
                .transform(ItemDisplayContext.GUI)
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

        withExistingParent(
                "pole",
                modLoc("block/pole"))
                .transforms()
                .transform(ItemDisplayContext.GUI)
                .rotation(30, 45, 0)
                .translation(4, -1.6F, 0)
                .scale(1)
                .end()
                .transform(ItemDisplayContext.GROUND)
                .rotation(0, 0, 0)
                .translation(0, 2, 2.5F)
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

        standardBlockTransforms(withExistingParent("signal-girder", modLoc("block/signal_girder")));

        standardBlockTransforms(withExistingParent("remote_signal_controller", modLoc("block/remote_signal_controller")));
    }
    
}
