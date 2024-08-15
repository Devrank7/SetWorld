package com.example.setworld.inter;

import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.synth.NormalNoise;

public interface IRandomState {
    public NormalNoise getOrCreateNoiseOr(ResourceKey<NormalNoise.NoiseParameters> p_224561_, Holder<NormalNoise.NoiseParameters> holder);
}
