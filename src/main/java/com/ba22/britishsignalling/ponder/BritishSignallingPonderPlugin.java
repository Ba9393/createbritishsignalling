package com.ba22.britishsignalling.ponder;

import net.createmod.ponder.api.registration.PonderPlugin;
import net.createmod.ponder.api.registration.PonderSceneRegistrationHelper;
import net.minecraft.resources.ResourceLocation;

public class BritishSignallingPonderPlugin implements PonderPlugin {

    @Override
    public String getModId() {
        return "createbritishsignalling";
    }

    @Override
    public void registerScenes(PonderSceneRegistrationHelper<ResourceLocation> helper) {
        helper.forComponents(
                ResourceLocation.fromNamespaceAndPath(
                        "createbritishsignalling",
                        "four-aperture-signal"
                )
        ).addStoryBoard("signals/intro", SignalScenes::intro);
    }
}