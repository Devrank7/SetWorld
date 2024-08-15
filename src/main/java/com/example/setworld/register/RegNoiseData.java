package com.example.setworld.register;

import com.example.setworld.ExampleMod;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.synth.NormalNoise;

public class RegNoiseData {

    public static final ResourceKey<NormalNoise.NoiseParameters> TEMPERATURE_SUPER_LARGE = createKey("temperature_super_large");
    public static final ResourceKey<NormalNoise.NoiseParameters> VEGETATION_SUPER_LARGE = createKey("vegetation_super_large");
    public static final ResourceKey<NormalNoise.NoiseParameters> CONTINENTALNESS_SUPER_LARGE = createKey("continentalness_super_large");
    public static final ResourceKey<NormalNoise.NoiseParameters> EROSION_SUPER_LARGE = createKey("erosion_super_large");


    public static void bootstrap(BootstrapContext<NormalNoise.NoiseParameters> p_330944_) {
        registerBiomeNoises(p_330944_, -5, TEMPERATURE_SUPER_LARGE, VEGETATION_SUPER_LARGE, CONTINENTALNESS_SUPER_LARGE, EROSION_SUPER_LARGE);
    }

    private static void registerBiomeNoises(
            BootstrapContext<NormalNoise.NoiseParameters> p_331173_,
            int p_236479_,
            ResourceKey<NormalNoise.NoiseParameters> p_236480_,
            ResourceKey<NormalNoise.NoiseParameters> p_236481_,
            ResourceKey<NormalNoise.NoiseParameters> p_236482_,
            ResourceKey<NormalNoise.NoiseParameters> p_236483_
    ) {
        register(p_331173_, p_236480_, -10 + p_236479_, 1.5, 0.0, 1.0, 0.0, 0.0, 0.0);
        register(p_331173_, p_236481_, -8 + p_236479_, 1.0, 1.0, 0.0, 0.0, 0.0, 0.0);
        register(p_331173_, p_236482_, -9 + p_236479_, 1.0, 1.0, 2.0, 2.0, 2.0, 1.0, 1.0, 1.0, 1.0);
        register(p_331173_, p_236483_, -9 + p_236479_, 1.0, 1.0, 0.0, 1.0, 1.0);
    }

    private static void register(
            BootstrapContext<NormalNoise.NoiseParameters> p_333944_,
            ResourceKey<NormalNoise.NoiseParameters> p_255970_,
            int p_256539_,
            double p_256566_,
            double... p_255998_
    ) {
        p_333944_.register(p_255970_, new NormalNoise.NoiseParameters(p_256539_, p_256566_, p_255998_));
    }
    public static ResourceKey<NormalNoise.NoiseParameters> createKey(String name) {
        return ResourceKey.create(Registries.NOISE, ResourceLocation.fromNamespaceAndPath( ExampleMod.MODID,name));
    }
}
