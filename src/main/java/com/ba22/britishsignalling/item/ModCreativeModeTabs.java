package com.ba22.britishsignalling.item;

import com.ba22.britishsignalling.CreateBritishSignalling;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModCreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, CreateBritishSignalling.MODID);

    public static final Supplier<CreativeModeTab> CREATE_ELCTROLYSIS_TAB = CREATIVE_MODE_TABS.register("create_british_signalling",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.SIGNAL_BLOCK.get()))
                    .title(Component.translatable("Create British Signalling"))
                    .displayItems((parameters, output) -> {
                        output.accept(ModItems.SIGNAL_BLOCK.get());
                        output.accept(ModItems.POLE.get());
                        output.accept(ModItems.SIGNAL_GIRDER.get());
                        output.accept(ModItems.REMOTE_SIGNAL_CONTROLLER.get());
                    }).build());


    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
