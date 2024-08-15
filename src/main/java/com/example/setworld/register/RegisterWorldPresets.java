package com.example.setworld.register;

import com.example.setworld.ExampleMod;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.*;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.dimension.BuiltinDimensionTypes;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraft.world.level.dimension.LevelStem;
import net.minecraft.world.level.levelgen.DebugLevelSource;
import net.minecraft.world.level.levelgen.FlatLevelSource;
import net.minecraft.world.level.levelgen.NoiseBasedChunkGenerator;
import net.minecraft.world.level.levelgen.NoiseGeneratorSettings;
import net.minecraft.world.level.levelgen.flat.FlatLevelGeneratorSettings;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.presets.WorldPreset;
import net.minecraft.world.level.levelgen.presets.WorldPresets;
import net.minecraft.world.level.levelgen.structure.StructureSet;

import java.util.Map;

public class RegisterWorldPresets {

    public static final ResourceKey<WorldPreset> SUPER_LARGE_BIOMES = ResourceKey.create(Registries.WORLD_PRESET, ResourceLocation.fromNamespaceAndPath(ExampleMod.MODID,"super_large_biomes"));
    public static final ResourceKey<WorldPreset> CUSTOM = ResourceKey.create(Registries.WORLD_PRESET, ResourceLocation.fromNamespaceAndPath(ExampleMod.MODID,"custom_world"));

    public static void bootstrap(BootstrapContext<WorldPreset> p_329030_) {
        new RegisterWorldPresets.Bootstrap(p_329030_).bootstrap();
    }
    static class Bootstrap {
        private final BootstrapContext<WorldPreset> context;
        private final HolderGetter<NoiseGeneratorSettings> noiseSettings;
        private final HolderGetter<Biome> biomes;
        private final HolderGetter<PlacedFeature> placedFeatures;
        private final HolderGetter<StructureSet> structureSets;
        private final HolderGetter<MultiNoiseBiomeSourceParameterList> multiNoiseBiomeSourceParameterLists;
        private final Holder<DimensionType> overworldDimensionType;
        private final LevelStem netherStem;
        private final LevelStem endStem;

        Bootstrap(BootstrapContext<WorldPreset> p_335809_) {
            this.context = p_335809_;
            HolderGetter<DimensionType> holdergetter = p_335809_.lookup(Registries.DIMENSION_TYPE);
            this.noiseSettings = p_335809_.lookup(Registries.NOISE_SETTINGS);
            this.biomes = p_335809_.lookup(Registries.BIOME);
            this.placedFeatures = p_335809_.lookup(Registries.PLACED_FEATURE);
            this.structureSets = p_335809_.lookup(Registries.STRUCTURE_SET);
            this.multiNoiseBiomeSourceParameterLists = p_335809_.lookup(Registries.MULTI_NOISE_BIOME_SOURCE_PARAMETER_LIST);
            this.overworldDimensionType = holdergetter.getOrThrow(BuiltinDimensionTypes.OVERWORLD);
            Holder<DimensionType> holder = holdergetter.getOrThrow(BuiltinDimensionTypes.NETHER);
            Holder<NoiseGeneratorSettings> holder1 = this.noiseSettings.getOrThrow(NoiseGeneratorSettings.NETHER);
            Holder.Reference<MultiNoiseBiomeSourceParameterList> reference = this.multiNoiseBiomeSourceParameterLists.getOrThrow(MultiNoiseBiomeSourceParameterLists.NETHER);
            this.netherStem = new LevelStem(holder, new NoiseBasedChunkGenerator(MultiNoiseBiomeSource.createFromPreset(reference), holder1));
            Holder<DimensionType> holder2 = holdergetter.getOrThrow(BuiltinDimensionTypes.END);
            Holder<NoiseGeneratorSettings> holder3 = this.noiseSettings.getOrThrow(NoiseGeneratorSettings.END);
            this.endStem = new LevelStem(holder2, new NoiseBasedChunkGenerator(TheEndBiomeSource.create(this.biomes), holder3));
        }

        private LevelStem makeOverworld(ChunkGenerator p_226488_) {
            return new LevelStem(this.overworldDimensionType, p_226488_);
        }

        private LevelStem makeNoiseBasedOverworld(BiomeSource p_226485_, Holder<NoiseGeneratorSettings> p_226486_) {
            return this.makeOverworld(new NoiseBasedChunkGenerator(p_226485_, p_226486_));
        }

        private WorldPreset createPresetWithCustomOverworld(LevelStem p_226490_) {
            return new WorldPreset(Map.of(LevelStem.OVERWORLD, p_226490_, LevelStem.NETHER, this.netherStem, LevelStem.END, this.endStem));
        }

        private void registerCustomOverworldPreset(ResourceKey<WorldPreset> p_256570_, LevelStem p_256269_) {
            this.context.register(p_256570_, this.createPresetWithCustomOverworld(p_256269_));
        }

        private void registerOverworlds(BiomeSource p_273133_) {
            Holder<NoiseGeneratorSettings> holder1 = this.noiseSettings.getOrThrow(RegNoiseGeneratorSettings.SUPER_LARGE_BIOMES);
            this.registerCustomOverworldPreset(SUPER_LARGE_BIOMES, this.makeNoiseBasedOverworld(p_273133_, holder1));
            Holder<NoiseGeneratorSettings> holder2 = this.noiseSettings.getOrThrow(RegNoiseGeneratorSettings.CUSTOM);
            this.registerCustomOverworldPreset(CUSTOM, this.makeNoiseBasedOverworld(p_273133_, holder2));
        }

        public void bootstrap() {
            Holder.Reference<MultiNoiseBiomeSourceParameterList> reference = this.multiNoiseBiomeSourceParameterLists.getOrThrow(MultiNoiseBiomeSourceParameterLists.OVERWORLD);
            this.registerOverworlds(MultiNoiseBiomeSource.createFromPreset(reference));
            Holder<NoiseGeneratorSettings> holder = this.noiseSettings.getOrThrow(NoiseGeneratorSettings.OVERWORLD);
            Holder.Reference<Biome> reference1 = this.biomes.getOrThrow(Biomes.PLAINS);
            this.registerCustomOverworldPreset(WorldPresets.SINGLE_BIOME_SURFACE, this.makeNoiseBasedOverworld(new FixedBiomeSource(reference1), holder));
            this.registerCustomOverworldPreset(
                    WorldPresets.FLAT,
                    this.makeOverworld(new FlatLevelSource(FlatLevelGeneratorSettings.getDefault(this.biomes, this.structureSets, this.placedFeatures)))
            );
            this.registerCustomOverworldPreset(WorldPresets.DEBUG, this.makeOverworld(new DebugLevelSource(reference1)));
        }
    }

}
