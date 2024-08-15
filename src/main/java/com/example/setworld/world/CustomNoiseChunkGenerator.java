package com.example.setworld.world;

import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.HolderSet;
import net.minecraft.core.QuartPos;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.WorldGenRegion;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.StructureManager;
import net.minecraft.world.level.biome.BiomeGenerationSettings;
import net.minecraft.world.level.biome.BiomeManager;
import net.minecraft.world.level.biome.BiomeSource;
import net.minecraft.world.level.chunk.CarvingMask;
import net.minecraft.world.level.chunk.ChunkAccess;
import net.minecraft.world.level.chunk.ChunkGeneratorStructureState;
import net.minecraft.world.level.chunk.ProtoChunk;
import net.minecraft.world.level.levelgen.*;
import net.minecraft.world.level.levelgen.blending.Blender;
import net.minecraft.world.level.levelgen.carver.CarvingContext;
import net.minecraft.world.level.levelgen.carver.ConfiguredWorldCarver;
import net.minecraft.world.level.levelgen.structure.StructureSet;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public class CustomNoiseChunkGenerator extends NoiseBasedChunkGenerator {
    private List<Holder.Reference<StructureSet>> holdersStructures;

    public CustomNoiseChunkGenerator(BiomeSource p_256415_, Holder<NoiseGeneratorSettings> p_256182_, List<Holder.Reference<StructureSet>> holdersStructures) {
        super(p_256415_, p_256182_);
        this.holdersStructures = holdersStructures;
    }

    @Override
    public ChunkGeneratorStructureState createState(HolderLookup<StructureSet> p_256405_, RandomState p_256101_, long p_256018_) {
        HolderLookup<StructureSet> holdersS = new HolderLookup<StructureSet>() {
            @Override
            public Stream<Holder.Reference<StructureSet>> listElements() {
                return holdersStructures.stream();
            }

            @Override
            public Stream<HolderSet.Named<StructureSet>> listTags() {
                return Stream.empty();
            }

            @Override
            public Optional<Holder.Reference<StructureSet>> get(ResourceKey<StructureSet> p_255645_) {
                return Optional.empty();
            }

            @Override
            public Optional<HolderSet.Named<StructureSet>> get(TagKey<StructureSet> p_256283_) {
                return Optional.empty();
            }
        };
        ChunkGeneratorStructureState.createForNormal(p_256101_, p_256018_, this.biomeSource, holdersS);
        return super.createState(p_256405_, p_256101_, p_256018_);
    }
}
