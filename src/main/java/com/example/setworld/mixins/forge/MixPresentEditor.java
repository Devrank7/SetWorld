package com.example.setworld.mixins.forge;

import com.example.setworld.ExampleMod;
import com.example.setworld.holder.DirHolder;
import com.example.setworld.inter.INoiseBasedChunkGenerator;
import com.example.setworld.inter.IWorldDimensions;
import com.example.setworld.register.RegisterWorldPresets;
import com.example.setworld.screen.CreateCustomSettingsForWorldScreen;
import com.example.setworld.world.NoisesGenerateSettings;
import com.example.setworld.world.WorldParam;
import com.mojang.datafixers.util.Pair;
import net.minecraft.client.gui.screens.worldselection.PresetEditor;
import net.minecraft.client.gui.screens.worldselection.WorldCreationContext;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.placement.CavePlacements;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.*;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraft.world.level.levelgen.DensityFunction;
import net.minecraft.world.level.levelgen.NoiseBasedChunkGenerator;
import net.minecraft.world.level.levelgen.NoiseGeneratorSettings;
import net.minecraft.world.level.levelgen.carver.CaveWorldCarver;
import net.minecraft.world.level.levelgen.presets.WorldPreset;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructureSet;
import net.minecraft.world.level.levelgen.synth.NormalNoise;
import net.minecraftforge.client.event.RegisterPresetEditorsEvent;
import net.minecraftforge.fml.ModLoader;
import org.jetbrains.annotations.ApiStatus;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Mixin(net.minecraftforge.client.PresetEditorManager.class)
public class MixPresentEditor {

    @Shadow(remap = false)
    private static Map<ResourceKey<WorldPreset>, PresetEditor> editors;

    /**
     * @author
     * @reason
     */
    @SuppressWarnings("deprecation")
    @ApiStatus.Internal
    @Overwrite(remap = false)
    static void init() {
        System.err.println("Hello from MixPresentEditor!");
        // Start with the vanilla entries
        Map<ResourceKey<WorldPreset>, PresetEditor> gatheredEditors = new HashMap<>();
        // Vanilla's map uses Optional<ResourceKey>s as its keys.
        // As far as we can tell there's no good reason for this, so we'll just use regular keys.
        PresetEditor.EDITORS.forEach((k, v) -> k.ifPresent(key -> gatheredEditors.put(key, v)));
        gatheredEditors.put(RegisterWorldPresets.CUSTOM, cusomEditor());
        // Gather mods' entries
        RegisterPresetEditorsEvent event = new RegisterPresetEditorsEvent(gatheredEditors);
        ModLoader.get().postEventWrapContainerInModOrder(event);

        editors = gatheredEditors;
    }

    private static PresetEditor cusomEditor() {
        return (p_232962_, p_232963_) -> {
            return new CreateCustomSettingsForWorldScreen(p_232962_, p_232963_, p_267861_ -> p_232962_.getUiState().updateDimensions(fixedBiomeConfigurator(p_267861_)));
        };
    }

    private static WorldCreationContext.DimensionsUpdater fixedBiomeConfigurator(WorldParam p267861) {
        return (p_258137_, p_258138_) -> {
            //System.err.println("Values: " + p267861);
            Registry<MultiNoiseBiomeSourceParameterList> multiNoiseBiomeSourceParameterList = p_258137_.registryOrThrow(Registries.MULTI_NOISE_BIOME_SOURCE_PARAMETER_LIST);
            Holder.Reference<MultiNoiseBiomeSourceParameterList> holder1 = multiNoiseBiomeSourceParameterList.getHolderOrThrow(MultiNoiseBiomeSourceParameterLists.OVERWORLD);
            HolderGetter<DensityFunction> holderGetter = p_258137_.asGetterLookup().lookup(Registries.DENSITY_FUNCTION).orElseThrow();
            HolderGetter<NormalNoise.NoiseParameters> holderGetter1 = p_258137_.asGetterLookup().lookup(Registries.NOISE).orElseThrow();
            HolderGetter<Structure> holderGetter2 = p_258137_.asGetterLookup().lookup(Registries.STRUCTURE).orElseThrow();
            HolderGetter<Biome> holderGetter3 = p_258137_.asGetterLookup().lookup(Registries.BIOME).orElseThrow();
            Registry<DimensionType> registriedOrThrow = p_258137_.registryOrThrow(Registries.DIMENSION_TYPE);
            //System.err.println("dim = " + p267861.getDimensionSelectType().getKey_resource());
            Holder<DimensionType> holder = registriedOrThrow.getHolderOrThrow(p267861.getDimensionSelectType().getKey_resource());
            int minHeight = holder.value().minY();
            int maxHeight = holder.value().height();
            Holder<NoiseGeneratorSettings> holder2 = new DirHolder<>(NoisesGenerateSettings.of(
                    holderGetter,
                    holderGetter1,
                    p267861.getGeneral_size(),
                    p267861.getTemperature(),
                    p267861.getVegatatio(),
                    p267861.getContinets(),
                    p267861.getErosion(),
                    p267861.getRidges(),
                    minHeight,
                    maxHeight,
                    p267861.getSea_level(),
                    p267861.getAdvancedSettings()), ResourceKey.create(Registries.NOISE_SETTINGS, ResourceLocation.fromNamespaceAndPath(ExampleMod.MODID, "custom")));
            List<Pair<Climate.ParameterPoint, Holder<Biome>>> pairList = new ArrayList<>(holder1.value().parameters().values());
            pairList.removeIf(t -> {
                return t.getSecond().value().getBaseTemperature() < p267861.getTemperature().getMin() || t.getSecond().value().getBaseTemperature() > p267861.getTemperature().getMax() || t.getSecond().value().getModifiedClimateSettings().downfall() < p267861.getTemperature().getMin_humid() || t.getSecond().value().getModifiedClimateSettings().downfall() > p267861.getTemperature().getMax_humid();
            });
            Climate.ParameterList<Holder<Biome>> climateList = new Climate.ParameterList<>(pairList);
            BiomeSource biomeSource = MultiNoiseBiomeSource.createFromList(climateList);

            List<Holder<StructureSet>> list = NoisesGenerateSettings.ofStructures(holderGetter2, holderGetter3, p267861.unwrapStructureSettings());
            NoiseBasedChunkGenerator chunkgenerator = new NoiseBasedChunkGenerator(
                    biomeSource,
                    holder2
            );
            INoiseBasedChunkGenerator iNoiseBasedChunkGenerator = (INoiseBasedChunkGenerator) chunkgenerator;
            iNoiseBasedChunkGenerator.setLavaLevel(minHeight + 8);

            iNoiseBasedChunkGenerator.setStructureSet(list);
            iNoiseBasedChunkGenerator.setFluidPicker(p267861.getCaveSettings());
            iNoiseBasedChunkGenerator.setCaveSettings(p267861.getCaveGeneratorSettings());
            //System.err.println(p267861.toString());
            //System.err.println("noiseRouter: " + holder2.unwrapKey().orElse(null));
            return ((IWorldDimensions)(Object)p_258138_).replaceOverworldGeneratorWithDimensions(p_258137_, chunkgenerator,holder);
        };
    }
}
