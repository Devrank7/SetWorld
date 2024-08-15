package com.example.setworld.mixins.noise;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderSet;
import net.minecraft.world.level.biome.BiomeGenerationSettings;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.carver.ConfiguredWorldCarver;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import org.spongepowered.asm.mixin.*;

import java.lang.reflect.Constructor;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Mixin(BiomeGenerationSettings.PlainBuilder.class)
public class MixPlainBuilder {

    @Shadow
    @Final
    protected Map<GenerationStep.Carving, List<Holder<ConfiguredWorldCarver<?>>>> carvers;

    @Shadow
    @Final
    protected List<List<Holder<PlacedFeature>>> features;

    /**
     * @author Daniel
     * @reason Mix in
     */
    @Overwrite
    public BiomeGenerationSettings build() {
        Class<?> carver = BiomeGenerationSettings.class;
        try {
            Constructor<?> constructor = carver.getDeclaredConstructor(Map.class, List.class);
            constructor.setAccessible(true);
            return (BiomeGenerationSettings) constructor.newInstance(this.carvers.entrySet().stream().collect(Collectors.toMap(
                            Map.Entry::getKey,
                            p_255831_ -> HolderSet.direct(p_255831_.getValue()))
                    ),
                    this.features.stream().map(HolderSet::direct).collect(ImmutableList.toImmutableList()));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
