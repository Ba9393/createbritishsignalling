package com.ba22.britishsignalling.events;

import com.ba22.britishsignalling.CreateBritishSignalling;
import com.ba22.britishsignalling.block.Four_Aperture_Signal;
import com.ba22.britishsignalling.block.ModBlocks;
import com.ba22.britishsignalling.block.fourapsignal.SignalAspect;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;
import org.joml.Vector3d;

@EventBusSubscriber(modid = CreateBritishSignalling.MODID)
public class TestEvents {
    @SubscribeEvent
    public static void onRightClickBlock(PlayerInteractEvent.RightClickBlock event) {

        if (event.getHand() != InteractionHand.MAIN_HAND) return;
        if (!event.getEntity().getItemInHand(event.getHand()).isEmpty()) return;

        var player = event.getEntity();
        var level = player.level();
        var pos = event.getPos();
        var state = level.getBlockState(pos);

        if (state.is(ModBlocks.FOUR_AP_SIGNAL.get())) {
            event.getEntity().swing(event.getHand());

            SignalAspect current = state.getValue(Four_Aperture_Signal.ASPECT);
            System.out.println(current);

            SignalAspect next = switch (current) {
                case DANGER -> SignalAspect.CAUTION;
                case CAUTION -> SignalAspect.PRELIMINARY_CAUTION;
                case PRELIMINARY_CAUTION -> SignalAspect.PROCEED;
                case PROCEED -> SignalAspect.DANGER;
            };

            level.setBlock(
                    pos,
                    state.setValue(Four_Aperture_Signal.ASPECT, next),
                    3
            );
        }
    }
}
