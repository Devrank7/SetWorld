package com.example.setworld.register;

import com.example.setworld.ExampleMod;
import com.example.setworld.inter.INoiseRouterData;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.SurfaceRuleData;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.OverworldBiomeBuilder;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.NoiseGeneratorSettings;
import net.minecraft.world.level.levelgen.NoiseRouter;
import net.minecraft.world.level.levelgen.NoiseRouterData;
import net.minecraft.world.level.levelgen.NoiseSettings;

import static net.minecraft.world.level.levelgen.NoiseGeneratorSettings.overworld;

public class RegNoiseGeneratorSettings {

    public static final ResourceKey<NoiseGeneratorSettings> SUPER_LARGE_BIOMES = ResourceKey.create(Registries.NOISE_SETTINGS, ResourceLocation.fromNamespaceAndPath(ExampleMod.MODID,"super_large_biomes"));
    public static final ResourceKey<NoiseGeneratorSettings> CUSTOM = ResourceKey.create(Registries.NOISE_SETTINGS, ResourceLocation.fromNamespaceAndPath(ExampleMod.MODID,"custom_world"));

    public static void bootstrap(BootstrapContext<NoiseGeneratorSettings> p_334698_) {

        p_334698_.register(SUPER_LARGE_BIOMES,new NoiseGeneratorSettings(NoiseSettings.create(-64, 384, 1, 2),
                Blocks.STONE.defaultBlockState(),
                Blocks.WATER.defaultBlockState(),
                ((INoiseRouterData)(Object)new NoiseRouterData()).overworld_large(p_334698_.lookup(Registries.DENSITY_FUNCTION), p_334698_.lookup(Registries.NOISE)),
                SurfaceRuleData.overworld(),
                new OverworldBiomeBuilder().spawnTarget(),
                63,
                false,
                true,
                true,
                false));
        p_334698_.register(CUSTOM,new NoiseGeneratorSettings(NoiseSettings.create(-64, 384, 1, 2),
                Blocks.STONE.defaultBlockState(),
                Blocks.WATER.defaultBlockState(),
                ((INoiseRouterData)(Object)new NoiseRouterData()).overworld_large(p_334698_.lookup(Registries.DENSITY_FUNCTION), p_334698_.lookup(Registries.NOISE)),
                SurfaceRuleData.overworld(),
                new OverworldBiomeBuilder().spawnTarget(),
                63,
                false,
                true,
                true,
                false));
    }
}
