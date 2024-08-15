package com.example.setworld.inter;

import net.minecraft.core.Holder;
import net.minecraft.core.RegistryAccess;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraft.world.level.levelgen.WorldDimensions;

public interface IWorldDimensions {

    public WorldDimensions replaceOverworldGeneratorWithDimensions(RegistryAccess p_251390_, ChunkGenerator p_248755_, Holder<DimensionType> dimensionType);
}
