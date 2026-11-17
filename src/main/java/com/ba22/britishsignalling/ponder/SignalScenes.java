package com.ba22.britishsignalling.ponder;

import com.ba22.britishsignalling.block.Four_Aperture_Signal;
import com.ba22.britishsignalling.block.ModBlocks;
import com.ba22.britishsignalling.block.RemoteSignalController;
import com.ba22.britishsignalling.block.fourapsignal.SignalAspect;
import com.simibubi.create.foundation.ponder.CreateSceneBuilder;
import net.createmod.catnip.math.Pointing;
import net.createmod.catnip.theme.Color;
import net.createmod.ponder.api.PonderPalette;
import net.createmod.ponder.api.element.ElementLink;
import net.createmod.ponder.api.element.WorldSectionElement;
import net.createmod.ponder.api.scene.SceneBuilder;
import net.createmod.ponder.api.scene.SceneBuildingUtil;
import net.createmod.ponder.api.scene.Selection;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.sounds.SimpleSoundInstance;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.references.Items;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.awt.*;

public class SignalScenes {

    public static void intro(SceneBuilder builder, SceneBuildingUtil util) { // sorry for shit ponder i pondered for a while on how to do this

        CreateSceneBuilder scene = new CreateSceneBuilder(builder);

        scene.title("four_aperture_signal", "");

        scene.configureBasePlate(0, 0, 9);

        scene.addInstruction(s ->
                s.getTransform().yRotation.startWithValue(-45)
        );

        // scene.rotateCameraY(180);

        Selection trainSelection =
                util.select().fromTo(6, 2, 4, 8, 3, 4);

        Selection redstone1Selection =
                util.select().position(3, 1, 6);

        Selection redstone2Selection =
                util.select().fromTo(4, 1, 8, 2, 1, 7);

        scene.world().showSection(
                util.select().everywhere()
                        .substract(trainSelection)
                        .substract(redstone1Selection)
                        .substract(redstone2Selection),
                Direction.DOWN
        );

        scene.idle(40);

        scene.world().toggleControls(util.grid().at(6, 3, 4));
        scene.world().toggleControls(util.grid().at(8, 3, 4));

        scene.idle(20);

        /* scene.world().animateBogey(util.grid().at(7, 2, 4), 3, 40);

        scene.world().moveSection(
                train,
                util.vector().of(-3, 0, 0),
                40
        );
        scene.idle(40); */

        // scene.world().setBlock(util.grid().at(13, 2, 4), Blocks.STONE.defaultBlockState(), true);

        scene.idle(20);

        scene.overlay().showText(30)
                .text("")
                .placeNearTarget()
                .pointAt(util.vector().topOf(4, 2, 6));

        scene.idle(25);

        scene.overlay().showControls(
                util.vector().of(4, 2, 6),
                Pointing.DOWN,
                20
        ).rightClick();

        scene.world().setBlock(util.grid().at(4, 2, 6),
                ModBlocks.FOUR_AP_SIGNAL.get().defaultBlockState()
                        .setValue(Four_Aperture_Signal.ASPECT, SignalAspect.CAUTION)
                        .setValue(Four_Aperture_Signal.FACING, Direction.EAST),
                false);

        scene.idle(40);

        scene.overlay().showText(30)
                .text("why do i need this if im gonna need to make a translation for it anyway")
                .pointAt(util.vector().topOf(4, 2, 6))
                .placeNearTarget()
                .attachKeyFrame();

        scene.idle(35);

        scene.overlay().showText(30)
                .text("if your reading this welcome to my github or my pc depends where u saw this")
                .pointAt(util.vector().topOf(4, 2, 6))
                .placeNearTarget();

        scene.overlay().showControls(
                util.vector().of(4, 2, 6),
                Pointing.DOWN,
                20
        ).rightClick();

        scene.idle(20);

        scene.overlay().showOutline(PonderPalette.RED, "controllerSignalPairing", util.select().position(4, 2, 6), 50);

        scene.idle(20);

        scene.overlay().showText(30)
                .text("STRMISS")
                .pointAt(util.vector().topOf(2, 1, 6))
                .placeNearTarget();

        scene.idle(20);

        scene.overlay().showControls(
                util.vector().of(2, 1, 6),
                Pointing.DOWN,
                20
        ).rightClick();

        scene.idle(10);

        scene.world().setBlock(
                util.grid().at(2, 1, 6),
                ModBlocks.REMOTE_SIGNAL_CONTROLLER.get()
                        .defaultBlockState()
                        .setValue(RemoteSignalController.FACING, Direction.SOUTH),
                true
        );

        scene.world().setBlock(
                util.grid().at(2, 1, 6),
                ModBlocks.REMOTE_SIGNAL_CONTROLLER.get()
                        .defaultBlockState()
                        .setValue(RemoteSignalController.FACING, Direction.SOUTH),
                true
        );

        scene.addInstruction(s -> {
            Minecraft.getInstance().getSoundManager().play(
                    SimpleSoundInstance.forUI(
                            SoundEvents.STONE_PLACE,
                            1.0f,
                            1.0f
                    )
            );
        });

        scene.idle(20);

        scene.overlay().showText(30)
                .text("STRMISS")
                .pointAt(util.vector().topOf(3, 1, 6))
                .placeNearTarget();

        scene.idle(20);

        ElementLink<WorldSectionElement> redstone1 =
                scene.world().showIndependentSection(
                        redstone1Selection,
                        Direction.DOWN
                );

        scene.idle(10);

        scene.world().toggleRedstonePower(
                util.select().position(3, 1, 6)
        );
        scene.effects().indicateRedstone(
                util.grid().at(3, 1, 6)
        );
        scene.addInstruction(s -> {
            Minecraft.getInstance().getSoundManager().play(
                    SimpleSoundInstance.forUI(
                            SoundEvents.LEVER_CLICK,
                            1.0f,
                            1.0f
                    )
            );
        });

        scene.world().setBlock(util.grid().at(4, 2, 6),
                ModBlocks.FOUR_AP_SIGNAL.get().defaultBlockState()
                        .setValue(Four_Aperture_Signal.ASPECT, SignalAspect.PROCEED)
                        .setValue(Four_Aperture_Signal.FACING, Direction.EAST),
                false);

        scene.idle(10);

        scene.overlay().showText(30)
                .text("STRMISS")
                .pointAt(util.vector().topOf(4, 2, 6))
                .placeNearTarget();

        scene.idle(60);

        scene.overlay().showText(60)
                .text("STRMISS")
                .pointAt(util.vector().topOf(4, 2, 6))
                .placeNearTarget()
                .attachKeyFrame();

        scene.idle(60);

        ElementLink<WorldSectionElement> redstone2 =
                scene.world().showIndependentSection(
                        redstone2Selection,
                        Direction.DOWN
                );

        ElementLink<WorldSectionElement> train =
                scene.world().showIndependentSection(
                        trainSelection,
                        Direction.DOWN
                );

        scene.idle(30);

        scene.world().animateBogey(util.grid().at(7, 2, 4), 6, 40);

        scene.world().moveSection(
                train,
                util.vector().of(-6, 0, 0),
                40
        );
        scene.idle(20);

        scene.world().toggleRedstonePower(redstone2Selection);
        scene.world().setBlock(util.grid().at(4, 2, 6),
                ModBlocks.FOUR_AP_SIGNAL.get().defaultBlockState()
                        .setValue(Four_Aperture_Signal.ASPECT, SignalAspect.DANGER)
                        .setValue(Four_Aperture_Signal.FACING, Direction.EAST),
                false);
        scene.idle(10);
        scene.world().toggleRedstonePower(redstone2Selection);

        scene.overlay().showText(40)
                .text("STRMISS")
                .pointAt(util.vector().topOf(4, 2, 6))
                .placeNearTarget();

        scene.idle(60);
    }
}