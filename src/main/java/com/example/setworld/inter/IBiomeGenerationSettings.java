package com.example.setworld.inter;

import net.minecraft.core.HolderSet;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.carver.ConfiguredWorldCarver;

import java.util.Map;

public interface IBiomeGenerationSettings {
    Map<GenerationStep.Carving, HolderSet<ConfiguredWorldCarver<?>>> getCarvers();
}
