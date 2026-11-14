
package com.ba22.britishsignalling.item;

import com.ba22.britishsignalling.block.ModBlocks;
import net.minecraft.core.component.DataComponents;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.component.CustomData;
import net.minecraft.world.item.context.UseOnContext;

public class RemoteSignalControllerItem extends BlockItem {

    public RemoteSignalControllerItem(Item.Properties properties) {
        super(ModBlocks.REMOTE_SIGNAL_CONTROLLER.get(), properties);
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        var level = context.getLevel();
        var pos = context.getClickedPos();

        // Only allow linking to four-aperture signals
        if (!level.getBlockState(pos).is(ModBlocks.FOUR_AP_SIGNAL.get())) {
            return super.useOn(context);
        }

        if (!level.isClientSide) {
            var stack = context.getItemInHand();

            CompoundTag data = stack.getOrDefault(
                    DataComponents.CUSTOM_DATA,
                    CustomData.EMPTY
            ).copyTag();

            data.putInt("TargetX", pos.getX());
            data.putInt("TargetY", pos.getY());
            data.putInt("TargetZ", pos.getZ());
            data.putString("TargetDimension",
                    level.dimension().location().toString());

            stack.set(DataComponents.CUSTOM_DATA, CustomData.of(data));
        }

        return InteractionResult.sidedSuccess(level.isClientSide);
    }
}
