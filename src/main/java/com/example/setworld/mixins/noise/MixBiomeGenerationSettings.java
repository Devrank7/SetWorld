package com.example.setworld.mixins.noise;

import com.example.setworld.inter.IBiomeGenerationSettings;
import com.google.common.collect.ImmutableBiMap;
import net.minecraft.core.HolderSet;
import net.minecraft.world.level.biome.BiomeGenerationSettings;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.carver.ConfiguredWorldCarver;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import java.util.Map;

@Mixin(BiomeGenerationSettings.class)
public class MixBiomeGenerationSettings implements IBiomeGenerationSettings {

    @Shadow @Final private Map<GenerationStep.Carving, HolderSet<ConfiguredWorldCarver<?>>> carvers;

    @Override
    public Map<GenerationStep.Carving, HolderSet<ConfiguredWorldCarver<?>>> getCarvers() {
        return carvers;
    }
}
