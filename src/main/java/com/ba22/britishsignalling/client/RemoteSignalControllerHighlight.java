package com.ba22.britishsignalling.client;

import com.ba22.britishsignalling.CreateBritishSignalling;
import com.ba22.britishsignalling.item.ModItems;

import net.createmod.catnip.outliner.Outliner;

import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.item.component.CustomData;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;

import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.ClientTickEvent;

@EventBusSubscriber(
        modid = CreateBritishSignalling.MODID,
        value = Dist.CLIENT
)
public class RemoteSignalControllerHighlight {

    private static final Object OUTLINE_SLOT = new Object();

    @SubscribeEvent
    public static void onClientTick(ClientTickEvent.Post event) {

        Minecraft minecraft = Minecraft.getInstance();

        if (minecraft.player == null || minecraft.level == null)
            return;

        var stack = minecraft.player.getMainHandItem();

        if (!stack.is(ModItems.REMOTE_SIGNAL_CONTROLLER.get())) {
            stack = minecraft.player.getOffhandItem();

            if (!stack.is(ModItems.REMOTE_SIGNAL_CONTROLLER.get()))
                return;
        }

        CustomData data = stack.get(DataComponents.CUSTOM_DATA);

        if (data == null)
            return;

        var tag = data.copyTag();

        if (!tag.contains("TargetX")
                || !tag.contains("TargetY")
                || !tag.contains("TargetZ")
                || !tag.contains("TargetDimension")) {
            return;
        }

        String dimension = tag.getString("TargetDimension");

        if (!minecraft.level.dimension().location().toString().equals(dimension))
            return;

        BlockPos target = new BlockPos(
                tag.getInt("TargetX"),
                tag.getInt("TargetY"),
                tag.getInt("TargetZ")
        );

        Level level = minecraft.level;

        if (!level.hasChunkAt(target))
            return;

        var state = level.getBlockState(target);
        var shape = state.getShape(level, target);

        if (shape.isEmpty())
            return;

        AABB box = shape.bounds()
                .move(target)
                .inflate(0.01);

        Outliner.getInstance()
                .showAABB(OUTLINE_SLOT, box)
                .colored(0xFC5C6B)
                .lineWidth(1 / 16f);
    }
}