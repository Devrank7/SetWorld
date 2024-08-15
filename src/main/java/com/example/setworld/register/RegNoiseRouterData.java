package com.example.setworld.register;

import com.example.setworld.ExampleMod;
import com.example.setworld.inter.INoiseRouterData;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.DensityFunction;
import net.minecraft.world.level.levelgen.NoiseRouterData;

public class RegNoiseRouterData {
    public static final ResourceKey<DensityFunction> CONTINENTS_LARGE = createKey("overworld_super_large_biomes/continents");
    public static final ResourceKey<DensityFunction> EROSION_LARGE = createKey("overworld_super_large_biomes/erosion");
    public static final ResourceKey<DensityFunction> OFFSET_LARGE = createKey("overworld_super_large_biomes/offset");
    public static final ResourceKey<DensityFunction> FACTOR_LARGE = createKey("overworld_super_large_biomes/factor");
    public static final ResourceKey<DensityFunction> JAGGEDNESS_LARGE = createKey("overworld_super_large_biomes/jaggedness");
    public static final ResourceKey<DensityFunction> DEPTH_LARGE = createKey("overworld_super_large_biomes/depth");
    public static final ResourceKey<DensityFunction> SLOPED_CHEESE_LARGE = createKey("overworld_super_large_biomes/sloped_cheese");
    private static ResourceKey<DensityFunction> createKey(String p_209537_) {
        return ResourceKey.create(Registries.DENSITY_FUNCTION, ResourceLocation.fromNamespaceAndPath(ExampleMod.MODID,p_209537_));
    }
    public static Holder<? extends DensityFunction> bootstrap(BootstrapContext<DensityFunction> p_335193_) {
        return ((INoiseRouterData)new NoiseRouterData()).bootstraps(p_335193_,CONTINENTS_LARGE,EROSION_LARGE);
    }
}
