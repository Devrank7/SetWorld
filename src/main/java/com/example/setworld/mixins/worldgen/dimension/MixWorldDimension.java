package com.example.setworld.mixins.worldgen.dimension;

import com.example.setworld.inter.IWorldDimensions;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.DimensionTypes;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.dimension.BuiltinDimensionTypes;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraft.world.level.dimension.LevelStem;
import net.minecraft.world.level.levelgen.WorldDimensions;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;

import java.util.Map;

@Mixin(WorldDimensions.class)
public abstract class MixWorldDimension implements IWorldDimensions {

    @Shadow @Final private Map<ResourceKey<LevelStem>, LevelStem> dimensions;

    @Shadow public static Map<ResourceKey<LevelStem>, LevelStem> withOverworld(Map<ResourceKey<LevelStem>, LevelStem> p_329337_, Holder<DimensionType> p_251895_, ChunkGenerator p_250220_){return null;}

    @Override
    @Unique
    public WorldDimensions replaceOverworldGeneratorWithDimensions(RegistryAccess p_251390_, ChunkGenerator p_248755_, Holder<DimensionType> dimensionType) {
        Registry<DimensionType> registry = p_251390_.registryOrThrow(Registries.DIMENSION_TYPE);
        Map<ResourceKey<LevelStem>, LevelStem> map = withOverworldWithDimensions(registry, this.dimensions, p_248755_, dimensionType);
        return new WorldDimensions(map);
    }
    public Map<ResourceKey<LevelStem>, LevelStem> withOverworldWithDimensions(Registry<DimensionType> registry, Map<ResourceKey<LevelStem>, LevelStem> dimensions, ChunkGenerator p_248755_, Holder<DimensionType> dimensionType) {
        LevelStem levelstem = dimensions.get(LevelStem.OVERWORLD);
        Holder<DimensionType> holder = (Holder<DimensionType>)(levelstem == null ? registry.getHolderOrThrow(BuiltinDimensionTypes.OVERWORLD) : dimensionType);
        //System.err.println("DIM: " + holder.value());
        return withOverworld(dimensions, holder, p_248755_);
    }
}
