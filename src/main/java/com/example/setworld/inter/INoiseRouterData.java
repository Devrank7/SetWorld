package com.example.setworld.inter;

import com.example.setworld.world.WorldParam;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.DensityFunction;
import net.minecraft.world.level.levelgen.NoiseRouter;
import net.minecraft.world.level.levelgen.synth.NormalNoise;

public interface INoiseRouterData {
    public NoiseRouter overworld_large( HolderGetter<DensityFunction> p_255681_, HolderGetter<NormalNoise.NoiseParameters> p_256005_);
    public NoiseRouter custom_overworld_large(
            HolderGetter<DensityFunction> p_255681_,
                                              HolderGetter<NormalNoise.NoiseParameters> p_256005_,
                                              int general_size,
                                              WorldParam.TemperatureParam temperatureParam,
                                              WorldParam.VegatatioParam vegatatioParam,
                                              WorldParam.ContinetsParam continetsParam,
                                              WorldParam.ErosionParam erosionParam,
                                              WorldParam.RidgesParam ridgesParam,
            int minY,
            int maxY,
            WorldParam.AdvancedSettings advancedSettings
    );
    public Holder<? extends DensityFunction> bootstraps(BootstrapContext<DensityFunction> p_335193_,ResourceKey<DensityFunction> key1, ResourceKey<DensityFunction> key2);
}
