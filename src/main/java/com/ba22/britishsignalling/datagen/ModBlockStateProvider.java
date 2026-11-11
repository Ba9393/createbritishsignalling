package com.ba22.britishsignalling.datagen;

import com.ba22.britishsignalling.CreateBritishSignalling;
import com.ba22.britishsignalling.block.ModBlocks;
import com.ba22.britishsignalling.block.Four_Aperture_Signal;
import net.minecraft.core.Direction;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.client.model.generators.ConfiguredModel;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

/**
 * This class is responsible for generating block states and models for the mod's blocks. 
 * It extends the BlockStateProvider class provided by NeoForge.
 */

public class ModBlockStateProvider extends BlockStateProvider  {
    
    public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, CreateBritishSignalling.MODID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {

        ModelFile danger = models()
                .withExistingParent("four_aperture_signal_danger",
                        modLoc("block/four_aperture_signal"))
                .texture("aspect", modLoc("block/signal/danger"));

        ModelFile caution = models()
                .withExistingParent("four_aperture_signal_caution",
                        modLoc("block/four_aperture_signal"))
                .texture("aspect", modLoc("block/signal/caution"));

        ModelFile precaution = models()
                .withExistingParent("four_aperture_signal_precaution",
                        modLoc("block/four_aperture_signal"))
                .texture("aspect", modLoc("block/signal/pre"));

        ModelFile proceed = models()
                .withExistingParent("four_aperture_signal_proceed",
                        modLoc("block/four_aperture_signal"))
                .texture("aspect", modLoc("block/signal/proceed"));

        getVariantBuilder(ModBlocks.FOUR_AP_SIGNAL.get())
                .forAllStates(state -> {

                    Direction direction = state.getValue(
                            Four_Aperture_Signal.FACING
                    );

                    ModelFile model = switch (state.getValue(
                            Four_Aperture_Signal.ASPECT)) {
                        case DANGER -> danger;
                        case CAUTION -> caution;
                        case PRELIMINARY_CAUTION -> precaution;
                        case PROCEED -> proceed;
                    };

                    return ConfiguredModel.builder()
                            .modelFile(model)
                            .rotationY((int) direction.toYRot())
                            .build();
                });
    }
}
