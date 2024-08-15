package com.example.setworld.world;

import lombok.*;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.Vec3i;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.structure.BuiltinStructures;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.placement.ConcentricRingsStructurePlacement;
import net.minecraft.world.level.levelgen.structure.placement.RandomSpreadStructurePlacement;
import net.minecraft.world.level.levelgen.structure.placement.RandomSpreadType;
import net.minecraft.world.level.levelgen.structure.placement.StructurePlacement;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;


public class WorldParam {

    private int general_size;
    private int sea_level;
    private int max_height;
    private int min_height;
    private TemperatureParam temperature;
    private VegatatioParam vegatatio;
    private ErosionParam erosion;
    private ContinetsParam continets;
    private RidgesParam ridges;
    private Map<String, NoisesGenerateSettings.StructureSettings> structureSettings;
    private DimensionSelectType dimensionSelectType;
    private CaveSettings caveSettings;
    private CaveGeneratorSettings caveGeneratorSettings;
    private AdvancedSettings advancedSettings;

    public WorldParam(int general_size,
                      int sea_level,
                      int max_height,
                      int min_height,
                      TemperatureParam temperature,
                      VegatatioParam vegatatio,
                      ErosionParam erosion,
                      ContinetsParam continets,
                      RidgesParam ridges,
                      DimensionSelectType dimensionSelectType,
                      CaveSettings caveSettings,
                      CaveGeneratorSettings caveGeneratorSettings,
                      AdvancedSettings advancedSettings) {
        this.general_size = general_size;
        this.sea_level = sea_level;
        this.max_height = max_height;
        this.min_height = min_height;
        this.temperature = temperature;
        this.vegatatio = vegatatio;
        this.erosion = erosion;
        this.continets = continets;
        this.ridges = ridges;
        this.dimensionSelectType = dimensionSelectType;
        this.caveSettings = caveSettings;
        this.caveGeneratorSettings = caveGeneratorSettings;
        this.advancedSettings = advancedSettings;
    }

    public Map<ResourceKey<Structure>, NoisesGenerateSettings.StructureSettings> unwrapStructureSettings() {
        Map<ResourceKey<Structure>, NoisesGenerateSettings.StructureSettings> map = new HashMap<>();
        for (Map.Entry<String, NoisesGenerateSettings.StructureSettings> entry : structureSettings.entrySet()) {
            switch (entry.getKey()) {
                case "village" -> {
                    map.put(BuiltinStructures.VILLAGE_PLAINS, entry.getValue());
                }
                case "desert_pyramid" -> map.put(BuiltinStructures.DESERT_PYRAMID, entry.getValue());
                case "igloo" -> {
                    map.put(BuiltinStructures.IGLOO, entry.getValue());
                }
                case "jungle_temple" -> {
                    map.put(BuiltinStructures.JUNGLE_TEMPLE, entry.getValue());
                }
                case "swamp_hut" -> {
                    map.put(BuiltinStructures.SWAMP_HUT, entry.getValue());
                }
                case "outpost" -> map.put(BuiltinStructures.PILLAGER_OUTPOST, entry.getValue());
                case "ancient_city" -> {
                    map.put(BuiltinStructures.ANCIENT_CITY, entry.getValue());
                }
                case "ocean_monument" -> {
                    map.put(BuiltinStructures.OCEAN_MONUMENT, entry.getValue());
                }
                case "woodland_mansion" -> {
                    map.put(BuiltinStructures.WOODLAND_MANSION, entry.getValue());
                }
                case "buried_treasure" -> {
                    map.put(BuiltinStructures.BURIED_TREASURE, entry.getValue());
                }
                case "mineshaft" -> {
                    map.put(BuiltinStructures.MINESHAFT, entry.getValue());
                }
                case "ruined_portal" -> {
                    map.put(BuiltinStructures.RUINED_PORTAL_STANDARD, entry.getValue());
                }
                case "shipwreck" -> {
                    map.put(BuiltinStructures.SHIPWRECK, entry.getValue());
                }
                case "ocean_ruin" -> {
                    map.put(BuiltinStructures.OCEAN_RUIN_COLD, entry.getValue());
                }
                case "fortress" -> {
                    map.put(BuiltinStructures.FORTRESS, entry.getValue());
                }
                case "bastion_remnant" -> {
                    map.put(BuiltinStructures.BASTION_REMNANT, entry.getValue());
                }
                case "nether_fossil" -> {
                    map.put(BuiltinStructures.NETHER_FOSSIL, entry.getValue());
                }
                case "end_city" -> {
                    map.put(BuiltinStructures.END_CITY, entry.getValue());
                }
                case "stronghold" -> {
                    map.put(BuiltinStructures.STRONGHOLD, entry.getValue());
                }
                case "trail_ruins" -> {
                    map.put(BuiltinStructures.TRAIL_RUINS, entry.getValue());
                }
                case "trial_chambers" -> {
                    map.put(BuiltinStructures.TRIAL_CHAMBERS, entry.getValue());
                }
                default -> {
                    map.put(null, entry.getValue());
                }
            }
        }
        return map;
    }

    public static Map<String, NoisesGenerateSettings.StructureSettings> getDefaultStructureSettings(HolderGetter<Biome> holdergetter1) {
        Map<String, NoisesGenerateSettings.StructureSettings> map = new HashMap<>();
        map.put("village", new NoisesGenerateSettings.StructureSettings(new RandomSpreadStructurePlacement(34, 8, RandomSpreadType.LINEAR, 10387312), true));
        map.put("desert_pyramid", new NoisesGenerateSettings.StructureSettings(new RandomSpreadStructurePlacement(32, 8, RandomSpreadType.LINEAR, 14357617), true));
        map.put("igloo", new NoisesGenerateSettings.StructureSettings(new RandomSpreadStructurePlacement(32, 8, RandomSpreadType.LINEAR, 14357618), true));
        map.put("jungle_temple", new NoisesGenerateSettings.StructureSettings(new RandomSpreadStructurePlacement(32, 8, RandomSpreadType.LINEAR, 14357619), true));
        map.put("swamp_hut", new NoisesGenerateSettings.StructureSettings(new RandomSpreadStructurePlacement(32, 8, RandomSpreadType.LINEAR, 14357620), true));
        map.put("outpost", new NoisesGenerateSettings.StructureSettings(new RandomSpreadStructurePlacement(
                Vec3i.ZERO,
                StructurePlacement.FrequencyReductionMethod.LEGACY_TYPE_1,
                0.2F,
                165745296,
                Optional.empty(),
                32,
                8,
                RandomSpreadType.LINEAR
        ), true));
        map.put("ancient_city", new NoisesGenerateSettings.StructureSettings(new RandomSpreadStructurePlacement(24, 8, RandomSpreadType.LINEAR, 20083232), true));
        map.put("ocean_monument", new NoisesGenerateSettings.StructureSettings(new RandomSpreadStructurePlacement(32, 5, RandomSpreadType.TRIANGULAR, 10387313), true));
        map.put("woodland_mansion", new NoisesGenerateSettings.StructureSettings(new RandomSpreadStructurePlacement(80, 20, RandomSpreadType.TRIANGULAR, 10387319), true));
        map.put("buried_treasure", new NoisesGenerateSettings.StructureSettings(new RandomSpreadStructurePlacement(new Vec3i(9, 0, 9), StructurePlacement.FrequencyReductionMethod.LEGACY_TYPE_2, 0.01F, 0, Optional.empty(), 1, 0, RandomSpreadType.LINEAR), true));
        map.put("mineshaft", new NoisesGenerateSettings.StructureSettings(new RandomSpreadStructurePlacement(Vec3i.ZERO, StructurePlacement.FrequencyReductionMethod.LEGACY_TYPE_3, 0.004F, 0, Optional.empty(), 1, 0, RandomSpreadType.LINEAR), true));
        map.put("ruined_portal", new NoisesGenerateSettings.StructureSettings(new RandomSpreadStructurePlacement(40, 15, RandomSpreadType.LINEAR, 34222645), true));
        map.put("shipwreck", new NoisesGenerateSettings.StructureSettings(new RandomSpreadStructurePlacement(24, 4, RandomSpreadType.LINEAR, 165745295), true));
        map.put("ocean_ruin", new NoisesGenerateSettings.StructureSettings(new RandomSpreadStructurePlacement(20, 8, RandomSpreadType.LINEAR, 14357621), true));
        map.put("fortress", new NoisesGenerateSettings.StructureSettings(new RandomSpreadStructurePlacement(27, 4, RandomSpreadType.LINEAR, 30084232), true));
        map.put("bastion_remnant", new NoisesGenerateSettings.StructureSettings(new RandomSpreadStructurePlacement(27, 4, RandomSpreadType.LINEAR, 30084232), true));
        map.put("nether_fossil", new NoisesGenerateSettings.StructureSettings(new RandomSpreadStructurePlacement(2, 1, RandomSpreadType.LINEAR, 14357921), true));
        map.put("end_city", new NoisesGenerateSettings.StructureSettings(new RandomSpreadStructurePlacement(20, 11, RandomSpreadType.TRIANGULAR, 10387313), true));
        map.put("stronghold", new NoisesGenerateSettings.StructureSettings(new ConcentricRingsStructurePlacement(32, 3, 128, holdergetter1.getOrThrow(BiomeTags.STRONGHOLD_BIASED_TO)), true));
        map.put("trail_ruins", new NoisesGenerateSettings.StructureSettings(new RandomSpreadStructurePlacement(34, 8, RandomSpreadType.LINEAR, 83469867), true));
        map.put("trial_chambers", new NoisesGenerateSettings.StructureSettings(new RandomSpreadStructurePlacement(34, 12, RandomSpreadType.LINEAR, 94251327), true));
        return map;
    }


    public WorldParam setStructureSettings(Map<String, NoisesGenerateSettings.StructureSettings> structureSettings) {
        this.structureSettings = structureSettings;
        return this;
    }

    public int getGeneral_size() {
        return general_size;
    }

    public void setGeneral_size(int general_size) {
        this.general_size = general_size;
    }

    public int getSea_level() {
        return sea_level;
    }

    public void setSea_level(int sea_level) {
        this.sea_level = sea_level;
    }

    public int getMax_height() {
        return max_height;
    }

    public void setMax_height(int max_height) {
        this.max_height = max_height;
    }

    public int getMin_height() {
        return min_height;
    }

    public void setMin_height(int min_height) {
        this.min_height = min_height;
    }

    public TemperatureParam getTemperature() {
        return temperature;
    }

    public void setTemperature(TemperatureParam temperature) {
        this.temperature = temperature;
    }

    public VegatatioParam getVegatatio() {
        return vegatatio;
    }

    public void setVegatatio(VegatatioParam vegatatio) {
        this.vegatatio = vegatatio;
    }

    public ErosionParam getErosion() {
        return erosion;
    }

    public void setErosion(ErosionParam erosion) {
        this.erosion = erosion;
    }

    public ContinetsParam getContinets() {
        return continets;
    }

    public void setContinets(ContinetsParam continets) {
        this.continets = continets;
    }

    public RidgesParam getRidges() {
        return ridges;
    }

    public void setRidges(RidgesParam ridges) {
        this.ridges = ridges;
    }

    public Map<String, NoisesGenerateSettings.StructureSettings> getStructureSettings() {
        return structureSettings;
    }

    public DimensionSelectType getDimensionSelectType() {
        return dimensionSelectType;
    }

    public void setDimensionSelectType(DimensionSelectType dimensionSelectType) {
        this.dimensionSelectType = dimensionSelectType;
    }

    public CaveSettings getCaveSettings() {
        return caveSettings;
    }

    public void setCaveSettings(CaveSettings caveSettings) {
        this.caveSettings = caveSettings;
    }

    public CaveGeneratorSettings getCaveGeneratorSettings() {
        return caveGeneratorSettings;
    }

    public void setCaveGeneratorSettings(CaveGeneratorSettings caveGeneratorSettings) {
        this.caveGeneratorSettings = caveGeneratorSettings;
    }

    public AdvancedSettings getAdvancedSettings() {
        return advancedSettings;
    }

    public void setAdvancedSettings(AdvancedSettings advancedSettings) {
        this.advancedSettings = advancedSettings;
    }

    @Override
    public String toString() {
        return "WorldParam{" +
                "general_size=" + general_size +
                ", sea_level=" + sea_level +
                ", max_height=" + max_height +
                ", min_height=" + min_height +
                ", temperature=" + temperature +
                ", vegatatio=" + vegatatio +
                ", erosion=" + erosion +
                ", continets=" + continets +
                ", ridges=" + ridges +
                ", structureSettings=" + structureSettings +
                '}';
    }

    public static class Param {
        protected int size;
        protected float amplitude;
        protected int amplitude_distortion;
        protected int amplitude_distortion_offset;
        protected int density_size;

        public Param(int size, float amplitude, int density_size, int amplitude_distortion, int amplitude_distortion_offset) {
            this.size = size;
            this.amplitude = amplitude;
            this.density_size = density_size;
            this.amplitude_distortion = amplitude_distortion;
            this.amplitude_distortion_offset = amplitude_distortion_offset;
        }

        public int getSize() {
            return size;
        }

        public void setSize(int size) {
            this.size = size;
        }

        public float getAmplitude() {
            return amplitude;
        }

        public void setAmplitude(float amplitude) {
            this.amplitude = amplitude;
        }

        public int getDensity_size() {
            return density_size;
        }

        public void setDensity_size(int density_size) {
            this.density_size = density_size;
        }

        public int getAmplitude_distortion() {
            return amplitude_distortion;
        }

        public void setAmplitude_distortion(int amplitude_distortion) {
            this.amplitude_distortion = amplitude_distortion;
        }

        public int getAmplitude_distortion_offset() {
            return amplitude_distortion_offset;
        }

        public void setAmplitude_distortion_offset(int amplitude_distortion_offset) {
            this.amplitude_distortion_offset = amplitude_distortion_offset;
        }

        @Override
        public String toString() {
            return "Param{" +
                    "size=" + size +
                    ", amplitude=" + amplitude +
                    '}';
        }
    }

    public static class TemperatureParam extends Param {

        float min;
        float max;
        float min_humid;
        float max_humid;

        public TemperatureParam(int size, float amplitude, int density_size, int amplitude_distortion, int amplitude_distortion_offset, float min, float max, float min_humid, float max_humid) {
            super(size, amplitude, density_size, amplitude_distortion, amplitude_distortion_offset);
            this.min = min;
            this.max = max;
            this.min_humid = min_humid;
            this.max_humid = max_humid;
        }

        public float getMin() {
            return min;
        }

        public void setMin(float min) {
            this.min = min;
        }

        public float getMax() {
            return max;
        }

        public void setMax(float max) {
            this.max = max;
        }

        public float getMin_humid() {
            return min_humid;
        }

        public void setMin_humid(float min_humid) {
            this.min_humid = min_humid;
        }

        public float getMax_humid() {
            return max_humid;
        }

        public void setMax_humid(float max_humid) {
            this.max_humid = max_humid;
        }

        @Override
        public String toString() {
            return "TemperatureParam{" +
                    "min=" + min +
                    ", max=" + max +
                    ", min_humid=" + min_humid +
                    ", max_humid=" + max_humid +
                    ", size=" + size +
                    ", amplitude=" + amplitude +
                    '}';
        }
    }

    public static class VegatatioParam extends Param {

        public VegatatioParam(int size, float amplitude, int d, int amplitude_distortion, int amplitude_distortion_offset) {
            super(size, amplitude, d, amplitude_distortion, amplitude_distortion_offset);
        }
    }

    public static class ErosionParam extends Param {

        public ErosionParam(int size, float amplitude, int d, int amplitude_distortion, int amplitude_distortion_offset) {
            super(size, amplitude, d, amplitude_distortion, amplitude_distortion_offset);
        }
    }

    public static class ContinetsParam extends Param {

        public ContinetsParam(int size, float amplitude, int d, int amplitude_distortion, int amplitude_distortion_offset) {
            super(size, amplitude, d, amplitude_distortion, amplitude_distortion_offset);
        }
    }

    public static class RidgesParam extends Param {

        public RidgesParam(int size, float amplitude, int d, int amplitude_distortion, int amplitude_distortion_offset) {
            super(size, amplitude, d, amplitude_distortion, amplitude_distortion_offset);
        }
    }


    public static class CaveSettings {
        private int lavaLevel;
        private int forceOfErrorForChunk;

        public CaveSettings(int lavaLevel, int forceOfErrorForChunk) {
            this.lavaLevel = lavaLevel;
            this.forceOfErrorForChunk = forceOfErrorForChunk;
        }

        public int getLavaLevel() {
            return lavaLevel;
        }

        public void setLavaLevel(int lavaLevel) {
            this.lavaLevel = lavaLevel;
        }

        public int getForceOfErrorForChunk() {
            return forceOfErrorForChunk;
        }

        public void setForceOfErrorForChunk(int forceOfErrorForChunk) {
            this.forceOfErrorForChunk = forceOfErrorForChunk;
        }
    }

    public record CaveGeneratorSettings(float probability, int minY, int maxY, float minScaleY, float maxScaleY,
                                        float horizontalRadiusMultiplierMin, float horizontalRadiusMultiplierMax,
                                        float verticalRadiusMultiplierMin, float verticalRadiusMultiplierMax,
                                        float floorLevelMin, float floorLevelMax, int maxYForExtreme) {
        public static CaveGeneratorSettings toDefault() {
            return new CaveGeneratorSettings(0.15f, 8, 180, 0.1f, 0.9f, 0.7f, 1.4f, 0.8f, 1.3f, -1.0f, -0.4f, 47);
        }

        public CaveGeneratorSettings with_probability(float probability) {
            return new CaveGeneratorSettings(probability, minY, maxY, minScaleY, maxScaleY, horizontalRadiusMultiplierMin, horizontalRadiusMultiplierMax, verticalRadiusMultiplierMin, verticalRadiusMultiplierMax, floorLevelMin, floorLevelMax, maxYForExtreme);
        }

        public CaveGeneratorSettings with_minY(int minY) {
            return new CaveGeneratorSettings(probability, minY, maxY, minScaleY, maxScaleY, horizontalRadiusMultiplierMin, horizontalRadiusMultiplierMax, verticalRadiusMultiplierMin, verticalRadiusMultiplierMax, floorLevelMin, floorLevelMax, maxYForExtreme);
        }

        public CaveGeneratorSettings with_maxY(int maxY) {
            return new CaveGeneratorSettings(probability, minY, maxY, minScaleY, maxScaleY, horizontalRadiusMultiplierMin, horizontalRadiusMultiplierMax, verticalRadiusMultiplierMin, verticalRadiusMultiplierMax, floorLevelMin, floorLevelMax, maxYForExtreme);
        }

        public CaveGeneratorSettings with_minScaleY(float minScaleY) {
            return new CaveGeneratorSettings(probability, minY, maxY, minScaleY, maxScaleY, horizontalRadiusMultiplierMin, horizontalRadiusMultiplierMax, verticalRadiusMultiplierMin, verticalRadiusMultiplierMax, floorLevelMin, floorLevelMax, maxYForExtreme);
        }

        public CaveGeneratorSettings with_maxScaleY(float maxScaleY) {
            return new CaveGeneratorSettings(probability, minY, maxY, minScaleY, maxScaleY, horizontalRadiusMultiplierMin, horizontalRadiusMultiplierMax, verticalRadiusMultiplierMin, verticalRadiusMultiplierMax, floorLevelMin, floorLevelMax, maxYForExtreme);
        }

        public CaveGeneratorSettings with_horizontalRadiusMultiplierMin(float horizontalRadiusMultiplierMin) {
            return new CaveGeneratorSettings(probability, minY, maxY, minScaleY, maxScaleY, horizontalRadiusMultiplierMin, horizontalRadiusMultiplierMax, verticalRadiusMultiplierMin, verticalRadiusMultiplierMax, floorLevelMin, floorLevelMax, maxYForExtreme);
        }

        public CaveGeneratorSettings with_horizontalRadiusMultiplierMax(float horizontalRadiusMultiplierMax) {
            return new CaveGeneratorSettings(probability, minY, maxY, minScaleY, maxScaleY, horizontalRadiusMultiplierMin, horizontalRadiusMultiplierMax, verticalRadiusMultiplierMin, verticalRadiusMultiplierMax, floorLevelMin, floorLevelMax, maxYForExtreme);
        }

        public CaveGeneratorSettings with_verticalRadiusMultiplierMin(float verticalRadiusMultiplierMin) {
            return new CaveGeneratorSettings(probability, minY, maxY, minScaleY, maxScaleY, horizontalRadiusMultiplierMin, horizontalRadiusMultiplierMax, verticalRadiusMultiplierMin, verticalRadiusMultiplierMax, floorLevelMin, floorLevelMax, maxYForExtreme);
        }

        public CaveGeneratorSettings with_verticalRadiusMultiplierMax(float verticalRadiusMultiplierMax) {
            return new CaveGeneratorSettings(probability, minY, maxY, minScaleY, maxScaleY, horizontalRadiusMultiplierMin, horizontalRadiusMultiplierMax, verticalRadiusMultiplierMin, verticalRadiusMultiplierMax, floorLevelMin, floorLevelMax, maxYForExtreme);
        }

        public CaveGeneratorSettings with_floorLevelMin(float floorLevelMin) {
            return new CaveGeneratorSettings(probability, minY, maxY, minScaleY, maxScaleY, horizontalRadiusMultiplierMin, horizontalRadiusMultiplierMax, verticalRadiusMultiplierMin, verticalRadiusMultiplierMax, floorLevelMin, floorLevelMax, maxYForExtreme);
        }

        public CaveGeneratorSettings with_floorLevelMax(float floorLevelMax) {
            return new CaveGeneratorSettings(probability, minY, maxY, minScaleY, maxScaleY, horizontalRadiusMultiplierMin, horizontalRadiusMultiplierMax, verticalRadiusMultiplierMin, verticalRadiusMultiplierMax, floorLevelMin, floorLevelMax, maxYForExtreme);
        }

        public CaveGeneratorSettings with_maxYForExtreme(int maxYForExtreme) {
            return new CaveGeneratorSettings(probability, minY, maxY, minScaleY, maxScaleY, horizontalRadiusMultiplierMin, horizontalRadiusMultiplierMax, verticalRadiusMultiplierMin, verticalRadiusMultiplierMax, floorLevelMin, floorLevelMax, maxYForExtreme);
        }
    }


    public static class AdvancedSettings {

        private GeneralNoiseSettings generalNoiseSettings;
        private BlendingNoisesSettings blendingNoisesSettings;
        private PeaksAndValleysSettings peaksAndValleysSettings;
        private SpagetiRougnessSettings spaghettiRougnessSettings;
        private EnranceSettings enranceSettings;
        private NoodleSettings noodleSettings;
        private Spageti2DSettings spaghetti2DSettings;
        private UndergroundSettings undergroundSettings;
        private SlideSettings slideSettings;
        private AqualifierSettings aqualifierSettings;

        public AdvancedSettings toDefault() {
            generalNoiseSettings = GeneralNoiseSettings.toDefault();
            blendingNoisesSettings = BlendingNoisesSettings.toDefault();
            peaksAndValleysSettings = PeaksAndValleysSettings.toDefault();
            spaghettiRougnessSettings = SpagetiRougnessSettings.toDefault();
            enranceSettings = EnranceSettings.toDefault();
            noodleSettings = NoodleSettings.toDefault();
            spaghetti2DSettings = Spageti2DSettings.toDefault();
            undergroundSettings = UndergroundSettings.toDefault();
            slideSettings = SlideSettings.toDefault();
            aqualifierSettings = AqualifierSettings.toDefault();
            return this;
        }

        public GeneralNoiseSettings getGeneralNoiseSettings() {
            return generalNoiseSettings;
        }

        public void setGeneralNoiseSettings(GeneralNoiseSettings generalNoiseSettings) {
            this.generalNoiseSettings = generalNoiseSettings;
        }

        public BlendingNoisesSettings getBlendingNoisesSettings() {
            return blendingNoisesSettings;
        }

        public void setBlendingNoisesSettings(BlendingNoisesSettings blendingNoisesSettings) {
            this.blendingNoisesSettings = blendingNoisesSettings;
        }

        public PeaksAndValleysSettings getPeaksAndValleysSettings() {
            return peaksAndValleysSettings;
        }

        public void setPeaksAndValleysSettings(PeaksAndValleysSettings peaksAndValleysSettings) {
            this.peaksAndValleysSettings = peaksAndValleysSettings;
        }

        public SpagetiRougnessSettings getSpaghettiRougnessSettings() {
            return spaghettiRougnessSettings;
        }

        public void setSpaghettiRougnessSettings(SpagetiRougnessSettings spaghettiRougnessSettings) {
            this.spaghettiRougnessSettings = spaghettiRougnessSettings;
        }

        public EnranceSettings getEnranceSettings() {
            return enranceSettings;
        }

        public void setEnranceSettings(EnranceSettings enranceSettings) {
            this.enranceSettings = enranceSettings;
        }

        public NoodleSettings getNoodleSettings() {
            return noodleSettings;
        }

        public void setNoodleSettings(NoodleSettings noodleSettings) {
            this.noodleSettings = noodleSettings;
        }

        public Spageti2DSettings getSpaghetti2DSettings() {
            return spaghetti2DSettings;
        }

        public void setSpaghetti2DSettings(Spageti2DSettings spaghetti2DSettings) {
            this.spaghetti2DSettings = spaghetti2DSettings;
        }

        public UndergroundSettings getUndergroundSettings() {
            return undergroundSettings;
        }

        public void setUndergroundSettings(UndergroundSettings undergroundSettings) {
            this.undergroundSettings = undergroundSettings;
        }

        public SlideSettings getSlideSettings() {
            return slideSettings;
        }

        public void setSlideSettings(SlideSettings slideSettings) {
            this.slideSettings = slideSettings;
        }

        public AqualifierSettings getAqualifierSettings() {
            return aqualifierSettings;
        }

        public void setAqualifierSettings(AqualifierSettings aqualifierSettings) {
            this.aqualifierSettings = aqualifierSettings;
        }


        public static class PeaksAndValleysSettings {
            private float absValueAdjustment = -0.666666666f;
            private float finalAdjustment = -0.333333333f;
            private float scalingFactor = -3.0f;

            public PeaksAndValleysSettings(float absValueAdjustment, float finalAdjustment, float scalingFactor) {
                this.absValueAdjustment = absValueAdjustment;
                this.finalAdjustment = finalAdjustment;
                this.scalingFactor = scalingFactor;
            }

            public static PeaksAndValleysSettings toDefault() {
                return new PeaksAndValleysSettings(-0.666666666f, -0.333333333f, -3.0f);
            }

            public float getAbsValueAdjustment() {
                return absValueAdjustment;
            }

            public void setAbsValueAdjustment(float absValueAdjustment) {
                this.absValueAdjustment = absValueAdjustment;
            }

            public float getFinalAdjustment() {
                return finalAdjustment;
            }

            public void setFinalAdjustment(float finalAdjustment) {
                this.finalAdjustment = finalAdjustment;
            }

            public float getScalingFactor() {
                return scalingFactor;
            }

            public void setScalingFactor(float scalingFactor) {
                this.scalingFactor = scalingFactor;
            }
        }


        public static class SpagetiRougnessSettings {
            private float scalingFactor = -0.4f;

            public SpagetiRougnessSettings(float scalingFactor) {
                this.scalingFactor = scalingFactor;
            }

            public static SpagetiRougnessSettings toDefault() {
                return new SpagetiRougnessSettings(-0.4f);
            }

            public float getScalingFactor() {
                return scalingFactor;
            }

            public void setScalingFactor(float scalingFactor) {
                this.scalingFactor = scalingFactor;
            }
        }


        public static class EnranceSettings {
            private float spaghettiRarityFrequency;
            private float spaghettiThicknessOffset1;
            private float spaghettiThicknessOffset2;
            private float caveEntranceFrequency;
            private float caveEntranceAmplitude;
            private float caveEntranceMULTIPLIER;

            public EnranceSettings(float spaghettiRarityFrequency, float spaghettiThicknessOffset1, float spaghettiThicknessOffset2, float caveEntranceFrequency, float caveEntranceAmplitude, float caveEntranceMULTIPLIER) {
                this.spaghettiRarityFrequency = spaghettiRarityFrequency;
                this.spaghettiThicknessOffset1 = spaghettiThicknessOffset1;
                this.spaghettiThicknessOffset2 = spaghettiThicknessOffset2;
                this.caveEntranceFrequency = caveEntranceFrequency;
                this.caveEntranceAmplitude = caveEntranceAmplitude;
                this.caveEntranceMULTIPLIER = caveEntranceMULTIPLIER;
            }

            public static EnranceSettings toDefault() {
                return new EnranceSettings(2.0f, -0.65f, -0.88f, 0.75f, 0.5f,5.0f);
            }

            public float getSpaghettiRarityFrequency() {
                return spaghettiRarityFrequency;
            }

            public void setSpaghettiRarityFrequency(float spaghettiRarityFrequency) {
                this.spaghettiRarityFrequency = spaghettiRarityFrequency;
            }

            public float getSpaghettiThicknessOffset1() {
                return spaghettiThicknessOffset1;
            }

            public void setSpaghettiThicknessOffset1(float spaghettiThicknessOffset1) {
                this.spaghettiThicknessOffset1 = spaghettiThicknessOffset1;
            }

            public float getSpaghettiThicknessOffset2() {
                return spaghettiThicknessOffset2;
            }

            public void setSpaghettiThicknessOffset2(float spaghettiThicknessOffset2) {
                this.spaghettiThicknessOffset2 = spaghettiThicknessOffset2;
            }

            public float getCaveEntranceFrequency() {
                return caveEntranceFrequency;
            }

            public void setCaveEntranceFrequency(float caveEntranceFrequency) {
                this.caveEntranceFrequency = caveEntranceFrequency;
            }

            public float getCaveEntranceAmplitude() {
                return caveEntranceAmplitude;
            }

            public void setCaveEntranceAmplitude(float caveEntranceAmplitude) {
                this.caveEntranceAmplitude = caveEntranceAmplitude;
            }

            public float getCaveEntranceMULTIPLIER() {
                return caveEntranceMULTIPLIER;
            }

            public void setCaveEntranceMULTIPLIER(float caveEntranceMULTIPLIER) {
                this.caveEntranceMULTIPLIER = caveEntranceMULTIPLIER;
            }
        }


        public static class NoodleSettings {
            private float noodleFrequency;
            private float noodleThicknessOffset1;
            private float noodleThicknessOffset2;
            private float ridgeFrequency;

            public NoodleSettings(float noodleFrequency, float noodleThicknessOffset1, float noodleThicknessOffset2, float ridgeFrequency) {
                this.noodleFrequency = noodleFrequency;
                this.noodleThicknessOffset1 = noodleThicknessOffset1;
                this.noodleThicknessOffset2 = noodleThicknessOffset2;
                this.ridgeFrequency = ridgeFrequency;
            }

            public static NoodleSettings toDefault() {
                return new NoodleSettings(1.0f, -0.05f, -0.1f, 2.6666666666f);
            }

            public float getNoodleFrequency() {
                return noodleFrequency;
            }

            public void setNoodleFrequency(float noodleFrequency) {
                this.noodleFrequency = noodleFrequency;
            }

            public float getNoodleThicknessOffset1() {
                return noodleThicknessOffset1;
            }

            public void setNoodleThicknessOffset1(float noodleThicknessOffset1) {
                this.noodleThicknessOffset1 = noodleThicknessOffset1;
            }

            public float getNoodleThicknessOffset2() {
                return noodleThicknessOffset2;
            }

            public void setNoodleThicknessOffset2(float noodleThicknessOffset2) {
                this.noodleThicknessOffset2 = noodleThicknessOffset2;
            }

            public float getRidgeFrequency() {
                return ridgeFrequency;
            }

            public void setRidgeFrequency(float ridgeFrequency) {
                this.ridgeFrequency = ridgeFrequency;
            }
        }


        public static class Spageti2DSettings {
            private float spaghetti2DFrequency;
            private float spaghetti2DThickness;
            private float spaghetti2DElevalentness;

            public Spageti2DSettings(float spaghetti2DFrequency, float spaghetti2DThickness, float spaghetti2DElevalentness) {
                this.spaghetti2DFrequency = spaghetti2DFrequency;
                this.spaghetti2DThickness = spaghetti2DThickness;
                this.spaghetti2DElevalentness = spaghetti2DElevalentness;
            }

            public static Spageti2DSettings toDefault() {
                return new Spageti2DSettings(2, 0.083f, 20); // minY + spaghetti2DElevalentness
            }

            public float getSpaghetti2DFrequency() {
                return spaghetti2DFrequency;
            }

            public void setSpaghetti2DFrequency(float spaghetti2DFrequency) {
                this.spaghetti2DFrequency = spaghetti2DFrequency;
            }

            public float getSpaghetti2DThickness() {
                return spaghetti2DThickness;
            }

            public void setSpaghetti2DThickness(float spaghetti2DThickness) {
                this.spaghetti2DThickness = spaghetti2DThickness;
            }

            public float getSpaghetti2DElevalentness() {
                return spaghetti2DElevalentness;
            }

            public void setSpaghetti2DElevalentness(float spaghetti2DElevalentness) {
                this.spaghetti2DElevalentness = spaghetti2DElevalentness;
            }
        }


        public static class UndergroundSettings {
            private float spaghetti2DIntensity;
            private float caveLayerFrequency;
            private float caveCheeseFactor;
            private float pillarDensity;

            public UndergroundSettings(float spaghetti2DIntensity, float caveLayerFrequency, float caveCheeseFactor, float pillarDensity) {
                this.spaghetti2DIntensity = spaghetti2DIntensity;
                this.caveLayerFrequency = caveLayerFrequency;
                this.caveCheeseFactor = caveCheeseFactor;
                this.pillarDensity = pillarDensity;
            }

            public static UndergroundSettings toDefault() {
                return new UndergroundSettings(2, 8, 0.6666666666666f, 0.3f);
            }

            public float getSpaghetti2DIntensity() {
                return spaghetti2DIntensity;
            }

            public void setSpaghetti2DIntensity(float spaghetti2DIntensity) {
                this.spaghetti2DIntensity = spaghetti2DIntensity;
            }

            public float getCaveLayerFrequency() {
                return caveLayerFrequency;
            }

            public void setCaveLayerFrequency(float caveLayerFrequency) {
                this.caveLayerFrequency = caveLayerFrequency;
            }

            public float getCaveCheeseFactor() {
                return caveCheeseFactor;
            }

            public void setCaveCheeseFactor(float caveCheeseFactor) {
                this.caveCheeseFactor = caveCheeseFactor;
            }

            public float getPillarDensity() {
                return pillarDensity;
            }

            public void setPillarDensity(float pillarDensity) {
                this.pillarDensity = pillarDensity;
            }
        }


        public static class SlideSettings {
            private float VerticalShiftStart;
            private float VerticalShiftEnd;
            private float ShiftIntensity;
            private float FinalGradientStart;
            private float FinalGradientEnd;
            private float FinalInterpolationFactor;

            public SlideSettings(float verticalShiftStart, float verticalShiftEnd, float shiftIntensity, float finalGradientStart, float finalGradientEnd, float finalInterpolationFactor) {
                VerticalShiftStart = verticalShiftStart;
                VerticalShiftEnd = verticalShiftEnd;
                ShiftIntensity = shiftIntensity;
                FinalGradientStart = finalGradientStart;
                FinalGradientEnd = finalGradientEnd;
                FinalInterpolationFactor = finalInterpolationFactor;
            }

            public static SlideSettings toDefault() {
                return new SlideSettings(80, 64, -0.078125f, 0.0f, 24F, 0.1171875f);
            }

            public float getVerticalShiftStart() {
                return VerticalShiftStart;
            }

            public void setVerticalShiftStart(float verticalShiftStart) {
                VerticalShiftStart = verticalShiftStart;
            }

            public float getVerticalShiftEnd() {
                return VerticalShiftEnd;
            }

            public void setVerticalShiftEnd(float verticalShiftEnd) {
                VerticalShiftEnd = verticalShiftEnd;
            }

            public float getShiftIntensity() {
                return ShiftIntensity;
            }

            public void setShiftIntensity(float shiftIntensity) {
                ShiftIntensity = shiftIntensity;
            }

            public float getFinalGradientStart() {
                return FinalGradientStart;
            }

            public void setFinalGradientStart(float finalGradientStart) {
                FinalGradientStart = finalGradientStart;
            }

            public float getFinalGradientEnd() {
                return FinalGradientEnd;
            }

            public void setFinalGradientEnd(float finalGradientEnd) {
                FinalGradientEnd = finalGradientEnd;
            }

            public float getFinalInterpolationFactor() {
                return FinalInterpolationFactor;
            }

            public void setFinalInterpolationFactor(float finalInterpolationFactor) {
                FinalInterpolationFactor = finalInterpolationFactor;
            }
        }


        public static class GeneralNoiseSettings {
            private float globalOffset;
            private float ore_thickness;
            private float veininess_frequency;
            private float surface_density_threshold;
            private float cheese_noise_target;
            private float cheese_noise_clamp_factor;
            private float depth_min_ampitude;
            private float depth_max_ampitude;
            private float blending_factor;
            private float juggedness_factor;
            private float juggedness_xzScale;

            public GeneralNoiseSettings(float globalOffset, float ore_thickness, float veininess_frequency, float surface_density_threshold, float cheese_noise_target, float cheese_noise_clamp_factor, float depth_min_ampitude, float depth_max_ampitude, float blending_factor, float juggedness_factor, float juggedness_xzScale) {
                this.globalOffset = globalOffset;
                this.ore_thickness = ore_thickness;
                this.veininess_frequency = veininess_frequency;
                this.surface_density_threshold = surface_density_threshold;
                this.cheese_noise_target = cheese_noise_target;
                this.cheese_noise_clamp_factor = cheese_noise_clamp_factor;
                this.depth_min_ampitude = depth_min_ampitude;
                this.depth_max_ampitude = depth_max_ampitude;
                this.blending_factor = blending_factor;
                this.juggedness_factor = juggedness_factor;
                this.juggedness_xzScale = juggedness_xzScale;
            }

            public static GeneralNoiseSettings toDefault() {
                return new GeneralNoiseSettings(-0.50375F, 0.08F, 1.5f, 1.5625f, -0.703125f,64, -1.5f, 1.5f, 10.0f, 0.0f,1500);
            }

            public float getGlobalOffset() {
                return globalOffset;
            }

            public void setGlobalOffset(float globalOffset) {
                this.globalOffset = globalOffset;
            }

            public float getOre_thickness() {
                return ore_thickness;
            }

            public void setOre_thickness(float ore_thickness) {
                this.ore_thickness = ore_thickness;
            }

            public float getVeininess_frequency() {
                return veininess_frequency;
            }

            public void setVeininess_frequency(float veininess_frequency) {
                this.veininess_frequency = veininess_frequency;
            }

            public float getSurface_density_threshold() {
                return surface_density_threshold;
            }

            public void setSurface_density_threshold(float surface_density_threshold) {
                this.surface_density_threshold = surface_density_threshold;
            }

            public float getCheese_noise_target() {
                return cheese_noise_target;
            }

            public void setCheese_noise_target(float cheese_noise_target) {
                this.cheese_noise_target = cheese_noise_target;
            }

            public float getCheese_noise_clamp_factor() {
                return cheese_noise_clamp_factor;
            }

            public void setCheese_noise_clamp_factor(float cheese_noise_clamp_factor) {
                this.cheese_noise_clamp_factor = cheese_noise_clamp_factor;
            }

            public float getDepth_min_ampitude() {
                return depth_min_ampitude;
            }

            public void setDepth_min_ampitude(float depth_min_ampitude) {
                this.depth_min_ampitude = depth_min_ampitude;
            }

            public float getDepth_max_ampitude() {
                return depth_max_ampitude;
            }

            public void setDepth_max_ampitude(float depth_max_ampitude) {
                this.depth_max_ampitude = depth_max_ampitude;
            }

            public float getBlending_factor() {
                return blending_factor;
            }

            public void setBlending_factor(float blending_factor) {
                this.blending_factor = blending_factor;
            }

            public float getJuggedness_factor() {
                return juggedness_factor;
            }

            public void setJuggedness_factor(float juggedness_factor) {
                this.juggedness_factor = juggedness_factor;
            }

            public float getJuggedness_xzScale() {
                return juggedness_xzScale;
            }

            public void setJuggedness_xzScale(float juggedness_xzScale) {
                this.juggedness_xzScale = juggedness_xzScale;
            }
        }


        public static class BlendingNoisesSettings {
            private double xzScale;
            private double yScale;
            private double xzFactor;
            private double yFactor;
            private double smearScaleMultiplier;

            public BlendingNoisesSettings(double xzScale, double yScale, double xzFactor, double yFactor, double smearScaleMultiplier) {
                this.xzScale = xzScale;
                this.yScale = yScale;
                this.xzFactor = xzFactor;
                this.yFactor = yFactor;
                this.smearScaleMultiplier = smearScaleMultiplier;
            }

            public static BlendingNoisesSettings toDefault() {
                return new BlendingNoisesSettings(0.25, 0.125, 80.0, 160.0, 8.0);
            }

            public double getXzScale() {
                return xzScale;
            }

            public void setXzScale(double xzScale) {
                this.xzScale = xzScale;
            }

            public double getYScale() {
                return yScale;
            }

            public void setyScale(double yScale) {
                this.yScale = yScale;
            }

            public double getXzFactor() {
                return xzFactor;
            }

            public void setXzFactor(double xzFactor) {
                this.xzFactor = xzFactor;
            }

            public double getYFactor() {
                return yFactor;
            }

            public void setYFactor(double yFactor) {
                this.yFactor = yFactor;
            }

            public double getSmearScaleMultiplier() {
                return smearScaleMultiplier;
            }

            public void setSmearScaleMultiplier(double smearScaleMultiplier) {
                this.smearScaleMultiplier = smearScaleMultiplier;
            }
        }

        public static class AqualifierSettings {
            private float aquifer_barrier;
            private float aquifer_fluid_level_floodness;
            private float aquifer_fluid_level_spread;

            public AqualifierSettings(float aquifer_barrier, float aquifer_fluid_level_floodness, float aquifer_fluid_level_spread) {
                this.aquifer_barrier = aquifer_barrier;
                this.aquifer_fluid_level_floodness = aquifer_fluid_level_floodness;
                this.aquifer_fluid_level_spread = aquifer_fluid_level_spread;
            }

            public static AqualifierSettings toDefault() {
                return new AqualifierSettings(0.5f, 0.67f, 0.7142857142857143f);
            }

            public float getAquifer_barrier() {
                return aquifer_barrier;
            }

            public void setAquifer_barrier(float aquifer_barrier) {
                this.aquifer_barrier = aquifer_barrier;
            }

            public float getAquifer_fluid_level_floodness() {
                return aquifer_fluid_level_floodness;
            }

            public void setAquifer_fluid_level_floodness(float aquifer_fluid_level_floodness) {
                this.aquifer_fluid_level_floodness = aquifer_fluid_level_floodness;
            }

            public float getAquifer_fluid_level_spread() {
                return aquifer_fluid_level_spread;
            }

            public void setAquifer_fluid_level_spread(float aquifer_fluid_level_spread) {
                this.aquifer_fluid_level_spread = aquifer_fluid_level_spread;
            }
        }
    }
}
