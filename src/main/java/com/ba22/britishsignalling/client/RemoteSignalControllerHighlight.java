package com.ba22.britishsignalling.client;

import com.ba22.britishsignalling.CreateBritishSignalling;
import com.ba22.britishsignalling.item.ModItems;
import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import com.mojang.blaze3d.vertex.VertexFormat;
import com.simibubi.create.Create;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.RenderStateShard;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.component.CustomData;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RenderLevelStageEvent;

import java.util.OptionalDouble;

@EventBusSubscriber(
        modid = CreateBritishSignalling.MODID,
        value = Dist.CLIENT
)
public class RemoteSignalControllerHighlight {

    private static final RenderType THICK_LINES = RenderType.create(
            "createbritishsignalling:thick_lines",
            DefaultVertexFormat.POSITION_COLOR_NORMAL,
            VertexFormat.Mode.LINES,
            256,
            false,
            false,
            RenderType.CompositeState.builder()
                    .setShaderState(new RenderStateShard.ShaderStateShard(
                            GameRenderer::getRendertypeLinesShader
                    ))
                    .setLineState(new RenderStateShard.LineStateShard(
                            OptionalDouble.of(10.0)
                    ))
                    .setLayeringState(RenderStateShard.VIEW_OFFSET_Z_LAYERING)
                    .setCullState(RenderStateShard.NO_CULL)
                    .createCompositeState(false)
    );

    @SubscribeEvent
    public static void onRenderLevel(RenderLevelStageEvent event) {
        if (event.getStage() != RenderLevelStageEvent.Stage.AFTER_PARTICLES) {
            return;
        }

        Minecraft minecraft = Minecraft.getInstance();

        if (minecraft.player == null || minecraft.level == null) return;

        var stack = minecraft.player.getMainHandItem();

        if (!stack.is(ModItems.REMOTE_SIGNAL_CONTROLLER.get())) {
            stack = minecraft.player.getOffhandItem();

            if (!stack.is(ModItems.REMOTE_SIGNAL_CONTROLLER.get())) {
                return;
            }
        }

        CustomData data = stack.get(
                net.minecraft.core.component.DataComponents.CUSTOM_DATA
        );

        if (data == null) return;

        var tag = data.copyTag();

        if (!tag.contains("TargetX")
                || !tag.contains("TargetY")
                || !tag.contains("TargetZ")
                || !tag.contains("TargetDimension")) {
            return;
        }

        String dimension = tag.getString("TargetDimension");

        if (!minecraft.level.dimension().location().toString().equals(dimension)) {
            return;
        }

        BlockPos target = new BlockPos(
                tag.getInt("TargetX"),
                tag.getInt("TargetY"),
                tag.getInt("TargetZ")
        );

        Level level = minecraft.level;

        if (!level.hasChunkAt(target)) return;

        var camera = event.getCamera().getPosition();

        AABB box = new AABB(target).inflate(0.01)
                .move(-camera.x, -camera.y, -camera.z);

        var poseStack = event.getPoseStack();
        var buffer = minecraft.renderBuffers()
                .bufferSource()
                .getBuffer(THICK_LINES);

        net.minecraft.client.renderer.LevelRenderer.renderLineBox(
                poseStack,
                buffer,
                box,
                1.0f, 0.85f, 0.0f, 1.0f
        );

        minecraft.renderBuffers().bufferSource().endBatch(RenderType.lines());
    }
}