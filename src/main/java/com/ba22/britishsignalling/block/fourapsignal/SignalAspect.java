package com.ba22.britishsignalling.block.fourapsignal;

import net.minecraft.util.StringRepresentable;

public enum SignalAspect implements StringRepresentable {
    DANGER,
    CAUTION,
    PRELIMINARY_CAUTION,
    PROCEED;

    @Override
    public String getSerializedName() {
        return name().toLowerCase();
    }
}