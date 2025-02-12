package com.example.setworld.gui;

import net.minecraft.client.Options;
import net.minecraft.network.chat.CommonComponents;

public abstract class GuiAbstractOptionSliderButton extends GuiAbsSlider{

    protected final Options options;

    protected GuiAbstractOptionSliderButton(Options p_93379_, int p_93380_, int p_93381_, int p_93382_, int p_93383_, double p_93384_) {
        super(p_93380_, p_93381_, p_93382_, p_93383_, CommonComponents.EMPTY, p_93384_);
        this.options = p_93379_;
    }
}
