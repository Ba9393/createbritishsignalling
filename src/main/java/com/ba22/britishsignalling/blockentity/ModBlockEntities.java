
package com.ba22.britishsignalling.blockentity;

import com.ba22.britishsignalling.CreateBritishSignalling;
import com.ba22.britishsignalling.block.ModBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModBlockEntities {

    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(
                    Registries.BLOCK_ENTITY_TYPE,
                    CreateBritishSignalling.MODID
            );

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<RemoteSignalControllerBlockEntity>>
            REMOTE_SIGNAL_CONTROLLER = BLOCK_ENTITIES.register(
            "remote_signal_controller",
            () -> BlockEntityType.Builder.of(
                    RemoteSignalControllerBlockEntity::new,
                    ModBlocks.REMOTE_SIGNAL_CONTROLLER.get()
            ).build(null)
    );

    public static void register(IEventBus eventBus) {
        BLOCK_ENTITIES.register(eventBus);
    }
}
