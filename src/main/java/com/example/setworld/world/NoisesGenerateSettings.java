package com.example.setworld.world;

import com.example.setworld.holder.HolderOwn;
import com.example.setworld.inter.INoiseRouterData;
import net.minecraft.client.Minecraft;
import net.minecraft.core.*;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.StructureSets;
import net.minecraft.data.worldgen.Structures;
import net.minecraft.data.worldgen.SurfaceRuleData;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.biome.OverworldBiomeBuilder;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.DensityFunction;
import net.minecraft.world.level.levelgen.NoiseGeneratorSettings;
import net.minecraft.world.level.levelgen.NoiseRouterData;
import net.minecraft.world.level.levelgen.NoiseSettings;
import net.minecraft.world.level.levelgen.structure.BuiltinStructures;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructureSet;
import net.minecraft.world.level.levelgen.structure.placement.ConcentricRingsStructurePlacement;
import net.minecraft.world.level.levelgen.structure.placement.RandomSpreadStructurePlacement;
import net.minecraft.world.level.levelgen.structure.placement.RandomSpreadType;
import net.minecraft.world.level.levelgen.structure.placement.StructurePlacement;
import net.minecraft.world.level.levelgen.synth.NormalNoise;

import java.util.*;

public class NoisesGenerateSettings {
    public static NoiseGeneratorSettings of(HolderGetter<DensityFunction> holderGetter1,
                                            HolderGetter<NormalNoise.NoiseParameters> settings,
                                            int size,
                                            WorldParam.TemperatureParam temperatureParam,
                                            WorldParam.VegatatioParam vegatatioParam,
                                            WorldParam.ContinetsParam continetsParam,
                                            WorldParam.ErosionParam erosionParam,
                                            WorldParam.RidgesParam ridgesParam,
                                            int minHeight,
                                            int maxHeight,
                                            int seaLevel,
                                            WorldParam.AdvancedSettings advancedSettings) {
        return new NoiseGeneratorSettings(NoiseSettings.create(minHeight, maxHeight, 1, 2),
                Blocks.STONE.defaultBlockState(),
                Blocks.WATER.defaultBlockState(),
                ((INoiseRouterData) new NoiseRouterData()).custom_overworld_large(
                        holderGetter1,
                        settings,
                        -size,
                        temperatureParam,
                        vegatatioParam,
                        continetsParam,
                        erosionParam,
                        ridgesParam,
                        minHeight,
                        maxHeight,
                        advancedSettings),
                SurfaceRuleData.overworld(),
                new OverworldBiomeBuilder().spawnTarget(),
                seaLevel,
                false,
                true,
                true,
                false);
    }

    public static List<Holder<StructureSet>> ofStructures(HolderGetter<Structure> holdergetter, HolderGetter<Biome> holdergetter1, Map<ResourceKey<Structure>,StructureSettings> settingsMap) {
        Map<ResourceKey<Structure>,Holder.Reference<StructureSet>> map = new HashMap();
        map.put(BuiltinStructures.VILLAGE_PLAINS,Holder.Reference.createIntrusive(new HolderOwn<>(), new StructureSet(
                List.of(
                        StructureSet.entry(holdergetter.getOrThrow(BuiltinStructures.VILLAGE_PLAINS)),
                        StructureSet.entry(holdergetter.getOrThrow(BuiltinStructures.VILLAGE_DESERT)),
                        StructureSet.entry(holdergetter.getOrThrow(BuiltinStructures.VILLAGE_SAVANNA)),
                        StructureSet.entry(holdergetter.getOrThrow(BuiltinStructures.VILLAGE_SNOWY)),
                        StructureSet.entry(holdergetter.getOrThrow(BuiltinStructures.VILLAGE_TAIGA))
                ),
                settingsMap.get(BuiltinStructures.VILLAGE_PLAINS).structurePlacement()
        )));

        map.put(BuiltinStructures.DESERT_PYRAMID,Holder.Reference.createIntrusive(new HolderOwn<>(), new StructureSet(holdergetter.getOrThrow(BuiltinStructures.DESERT_PYRAMID), settingsMap.get(BuiltinStructures.DESERT_PYRAMID).structurePlacement())));
        map.put(BuiltinStructures.IGLOO,Holder.Reference.createIntrusive(new HolderOwn<>(), new StructureSet(holdergetter.getOrThrow(BuiltinStructures.IGLOO), settingsMap.get(BuiltinStructures.IGLOO).structurePlacement())));
        map.put(BuiltinStructures.JUNGLE_TEMPLE,Holder.Reference.createIntrusive(new HolderOwn<>(), new StructureSet(holdergetter.getOrThrow(BuiltinStructures.JUNGLE_TEMPLE), settingsMap.get(BuiltinStructures.JUNGLE_TEMPLE).structurePlacement())));
        map.put(BuiltinStructures.SWAMP_HUT,Holder.Reference.createIntrusive(new HolderOwn<>(), new StructureSet(holdergetter.getOrThrow(BuiltinStructures.SWAMP_HUT), settingsMap.get(BuiltinStructures.SWAMP_HUT).structurePlacement())));
        map.put(BuiltinStructures.PILLAGER_OUTPOST,Holder.Reference.createIntrusive(new HolderOwn<>(), new StructureSet(
                holdergetter.getOrThrow(BuiltinStructures.PILLAGER_OUTPOST),
                settingsMap.get(BuiltinStructures.PILLAGER_OUTPOST).structurePlacement()
        )));
        // Ancient Cities
        map.put(BuiltinStructures.ANCIENT_CITY,Holder.Reference.createIntrusive(new HolderOwn<>(), new StructureSet(
                holdergetter.getOrThrow(BuiltinStructures.ANCIENT_CITY),
                settingsMap.get(BuiltinStructures.ANCIENT_CITY).structurePlacement()
        )));

// Ocean Monuments
        map.put(BuiltinStructures.OCEAN_MONUMENT,Holder.Reference.createIntrusive(new HolderOwn<>(), new StructureSet(
                holdergetter.getOrThrow(BuiltinStructures.OCEAN_MONUMENT),
                settingsMap.get(BuiltinStructures.OCEAN_MONUMENT).structurePlacement()
        )));

// Woodland Mansions
        map.put(BuiltinStructures.WOODLAND_MANSION,Holder.Reference.createIntrusive(new HolderOwn<>(), new StructureSet(
                holdergetter.getOrThrow(BuiltinStructures.WOODLAND_MANSION),
                settingsMap.get(BuiltinStructures.WOODLAND_MANSION).structurePlacement()
        )));

// Buried Treasures
        map.put(BuiltinStructures.BURIED_TREASURE,Holder.Reference.createIntrusive(new HolderOwn<>(), new StructureSet(
                holdergetter.getOrThrow(BuiltinStructures.BURIED_TREASURE),
                settingsMap.get(BuiltinStructures.BURIED_TREASURE).structurePlacement()
        )));

// Mineshafts
        map.put(BuiltinStructures.MINESHAFT,Holder.Reference.createIntrusive(new HolderOwn<>(), new StructureSet(
                List.of(
                        StructureSet.entry(holdergetter.getOrThrow(BuiltinStructures.MINESHAFT)),
                        StructureSet.entry(holdergetter.getOrThrow(BuiltinStructures.MINESHAFT_MESA))
                ),
                settingsMap.get(BuiltinStructures.MINESHAFT).structurePlacement()
        )));

// Ruined Portals
        map.put(BuiltinStructures.RUINED_PORTAL_STANDARD,Holder.Reference.createIntrusive(new HolderOwn<>(), new StructureSet(
                List.of(
                        StructureSet.entry(holdergetter.getOrThrow(BuiltinStructures.RUINED_PORTAL_STANDARD)),
                        StructureSet.entry(holdergetter.getOrThrow(BuiltinStructures.RUINED_PORTAL_DESERT)),
                        StructureSet.entry(holdergetter.getOrThrow(BuiltinStructures.RUINED_PORTAL_JUNGLE)),
                        StructureSet.entry(holdergetter.getOrThrow(BuiltinStructures.RUINED_PORTAL_SWAMP)),
                        StructureSet.entry(holdergetter.getOrThrow(BuiltinStructures.RUINED_PORTAL_MOUNTAIN)),
                        StructureSet.entry(holdergetter.getOrThrow(BuiltinStructures.RUINED_PORTAL_OCEAN)),
                        StructureSet.entry(holdergetter.getOrThrow(BuiltinStructures.RUINED_PORTAL_NETHER))
                ),
                settingsMap.get(BuiltinStructures.RUINED_PORTAL_STANDARD).structurePlacement()
        )));

// Shipwrecks
        map.put(BuiltinStructures.SHIPWRECK,Holder.Reference.createIntrusive(new HolderOwn<>(), new StructureSet(
                List.of(
                        StructureSet.entry(holdergetter.getOrThrow(BuiltinStructures.SHIPWRECK)),
                        StructureSet.entry(holdergetter.getOrThrow(BuiltinStructures.SHIPWRECK_BEACHED))
                ),
                settingsMap.get(BuiltinStructures.SHIPWRECK).structurePlacement()
        )));

// Ocean Ruins
        map.put(BuiltinStructures.OCEAN_RUIN_COLD,Holder.Reference.createIntrusive(new HolderOwn<>(), new StructureSet(
                List.of(
                        StructureSet.entry(holdergetter.getOrThrow(BuiltinStructures.OCEAN_RUIN_COLD)),
                        StructureSet.entry(holdergetter.getOrThrow(BuiltinStructures.OCEAN_RUIN_WARM))
                ),
                settingsMap.get(BuiltinStructures.OCEAN_RUIN_COLD).structurePlacement()
        )));

// Nether Complexes
        map.put(BuiltinStructures.FORTRESS,Holder.Reference.createIntrusive(new HolderOwn<>(), new StructureSet(
                List.of(
                        StructureSet.entry(holdergetter.getOrThrow(BuiltinStructures.FORTRESS), 2),
                        StructureSet.entry(holdergetter.getOrThrow(BuiltinStructures.BASTION_REMNANT), 3)
                ),
                settingsMap.get(BuiltinStructures.FORTRESS).structurePlacement()
        )));

// Nether Fossils
        map.put(BuiltinStructures.NETHER_FOSSIL,Holder.Reference.createIntrusive(new HolderOwn<>(), new StructureSet(
                holdergetter.getOrThrow(BuiltinStructures.NETHER_FOSSIL),
                settingsMap.get(BuiltinStructures.NETHER_FOSSIL).structurePlacement()
        )));

// End Cities
        map.put(BuiltinStructures.END_CITY,Holder.Reference.createIntrusive(new HolderOwn<>(), new StructureSet(
                holdergetter.getOrThrow(BuiltinStructures.END_CITY),
                settingsMap.get(BuiltinStructures.END_CITY).structurePlacement()
        )));

// Strongholds
        map.put(BuiltinStructures.STRONGHOLD,Holder.Reference.createIntrusive(new HolderOwn<>(), new StructureSet(
                holdergetter.getOrThrow(BuiltinStructures.STRONGHOLD),
                settingsMap.get(BuiltinStructures.STRONGHOLD).structurePlacement()
        )));

// Trail Ruins
        map.put(BuiltinStructures.TRAIL_RUINS,Holder.Reference.createIntrusive(new HolderOwn<>(), new StructureSet(
                holdergetter.getOrThrow(BuiltinStructures.TRAIL_RUINS),
                settingsMap.get(BuiltinStructures.TRAIL_RUINS).structurePlacement()
        )));

// Trial Chambers
        map.put(BuiltinStructures.TRIAL_CHAMBERS,Holder.Reference.createIntrusive(new HolderOwn<>(), new StructureSet(
                holdergetter.getOrThrow(BuiltinStructures.TRIAL_CHAMBERS),
                settingsMap.get(BuiltinStructures.TRIAL_CHAMBERS).structurePlacement()
        )));
        List<Holder<StructureSet>> filterList = new ArrayList<>(map.values().stream().toList());
        for (Map.Entry<ResourceKey<Structure>, Holder.Reference<StructureSet>> entry : map.entrySet()) {
            if (!settingsMap.get(entry.getKey()).allowSpawn) {
                filterList.remove(entry.getValue());
            }
        }
        return filterList;
    }
    public static class StructureSettings {
        private StructurePlacement structurePlacement;
        private boolean allowSpawn;

        public StructureSettings(StructurePlacement structurePlacement, boolean allowSpawn) {
            this.structurePlacement = structurePlacement;
            this.allowSpawn = allowSpawn;
        }

        public StructurePlacement structurePlacement() {
            return structurePlacement;
        }
        public boolean allowSpawn() {
            return allowSpawn;
        }

        public void setStructurePlacement(StructurePlacement structurePlacement) {
            this.structurePlacement = structurePlacement;
        }

        public void setAllowSpawn(boolean allowSpawn) {
            this.allowSpawn = allowSpawn;
        }

        @Override
        public String toString() {
            return "StructureSettings{" +
                    "structurePlacement=[" + (structurePlacement instanceof RandomSpreadStructurePlacement r ? r.spacing() : structurePlacement) + "]" +
                    ", allowSpawn=" + allowSpawn +
                    '}';
        }
    }
}
