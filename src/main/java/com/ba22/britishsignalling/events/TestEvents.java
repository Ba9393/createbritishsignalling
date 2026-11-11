package com.ba22.britishsignalling.events;

import com.ba22.britishsignalling.CreateBritishSignalling;
import com.ba22.britishsignalling.block.Four_Aperture_Signal;
import com.ba22.britishsignalling.block.fourapsignal.SignalAspect;
import net.minecraft.core.BlockPos;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;
import org.joml.Vector3d;

@EventBusSubscriber(modid = CreateBritishSignalling.MODID)
public class TestEvents {
    @SubscribeEvent
    public static void onPlayerTick(PlayerTickEvent.Post event) {
        var player = event.getEntity();
        var level = player.level();

        var state = level.getBlockState(new BlockPos(0, 0, 0));

        level.setBlock(
                new BlockPos(0, 0, 0),
                state.setValue(Four_Aperture_Signal.ASPECT, SignalAspect.PROCEED),
                3
        );
    }
}
