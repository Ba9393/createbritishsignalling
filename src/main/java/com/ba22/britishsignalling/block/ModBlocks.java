package com.ba22.britishsignalling.block;

import com.ba22.britishsignalling.CreateBritishSignalling;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModBlocks {

    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(CreateBritishSignalling.MODID);

    public static final DeferredBlock<Four_Aperture_Signal> FOUR_AP_SIGNAL = BLOCKS.register("four-aperture-signal",
            () -> new Four_Aperture_Signal(BlockBehaviour.Properties.of()
                    .noOcclusion()
                    .strength(3f)
                    .explosionResistance(1200f)
                    .requiresCorrectToolForDrops()));

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }

}
