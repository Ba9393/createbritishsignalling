package com.ba22.britishsignalling.item;

import com.ba22.britishsignalling.CreateBritishSignalling;
import com.ba22.britishsignalling.block.ModBlocks;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModItems {

    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(CreateBritishSignalling.MODID);

    public static final DeferredItem<Item> SIGNAL_BLOCK = ITEMS.register("four-aperture-signal",
            () -> new BlockItem(ModBlocks.FOUR_AP_SIGNAL.get(), new Item.Properties()));

    public static final DeferredItem<Item> POLE = ITEMS.register("pole",
            () -> new BlockItem(ModBlocks.POLE.get(), new Item.Properties()));

    public static final DeferredItem<Item> SIGNAL_GIRDER = ITEMS.register("signal-girder",
            () -> new BlockItem(ModBlocks.SIGNAL_GIRDER.get(), new Item.Properties()));

    public static final DeferredItem<Item> REMOTE_SIGNAL_CONTROLLER =
            ITEMS.register("remote_signal_controller",
                    () -> new RemoteSignalControllerItem(new Item.Properties()));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }

}
