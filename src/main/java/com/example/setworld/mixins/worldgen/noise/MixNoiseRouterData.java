package com.example.setworld.mixins.worldgen.noise;

import com.example.setworld.ExampleMod;
import com.example.setworld.VeinType;
import com.example.setworld.holder.DirHolder;
import com.example.setworld.inter.INoiseRouterData;
import com.example.setworld.register.RegNoiseData;
import com.example.setworld.register.RegNoiseRouterData;
import com.example.setworld.world.WorldParam;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.TerrainProvider;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraft.world.level.levelgen.*;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.synth.BlendedNoise;
import net.minecraft.world.level.levelgen.synth.NormalNoise;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.stream.Stream;

@Mixin(NoiseRouterData.class)
public abstract class MixNoiseRouterData implements INoiseRouterData {

    @Shadow
    private static DensityFunction getFunction(HolderGetter<DensityFunction> p_256312_, ResourceKey<DensityFunction> p_256077_) {
        return null;
    }

    @Shadow
    @Final
    private static ResourceKey<DensityFunction> SHIFT_X;

    @Shadow
    @Final
    private static ResourceKey<DensityFunction> SHIFT_Z;

    @Shadow
    private static DensityFunction noiseGradientDensity(DensityFunction p_212272_, DensityFunction p_212273_) {
        return null;
    }

    @Shadow
    private static DensityFunction underground(HolderGetter<DensityFunction> p_256548_, HolderGetter<NormalNoise.NoiseParameters> p_256236_, DensityFunction p_256658_) {
        return null;
    }

    @Shadow
    @Final
    private static ResourceKey<DensityFunction> Y;

    @Shadow
    private static DensityFunction yLimitedInterpolatable(DensityFunction p_209472_, DensityFunction p_209473_, int p_209474_, int p_209475_, int p_209476_) {
        return null;
    }

    @Shadow
    private static DensityFunction slideOverworld(boolean p_224490_, DensityFunction p_224491_) {
        return null;
    }

    @Shadow
    @Final
    private static ResourceKey<DensityFunction> FACTOR_LARGE;

    @Shadow
    @Final
    private static ResourceKey<DensityFunction> DEPTH_LARGE;

    @Shadow
    @Final
    private static ResourceKey<DensityFunction> SLOPED_CHEESE_LARGE;

    @Shadow
    @Final
    private static ResourceKey<DensityFunction> ENTRANCES;

    @Shadow
    private static DensityFunction postProcess(DensityFunction p_224493_) {
        return null;
    }

    @Shadow
    @Final
    public static ResourceKey<DensityFunction> CONTINENTS_LARGE;

    @Shadow
    @Final
    public static ResourceKey<DensityFunction> EROSION_LARGE;

    @Shadow
    @Final
    public static ResourceKey<DensityFunction> RIDGES;

    @Shadow
    @Final
    private static ResourceKey<DensityFunction> NOODLE;

    @Shadow
    private static DensityFunction registerAndWrap(BootstrapContext<DensityFunction> p_331307_, ResourceKey<DensityFunction> p_255905_, DensityFunction p_255856_) {
        return null;
    }

    @Shadow
    private static void registerTerrainNoises(BootstrapContext<DensityFunction> p_330992_, HolderGetter<DensityFunction> p_256393_, DensityFunction p_224476_, Holder<DensityFunction> p_224477_, Holder<DensityFunction> p_224478_, ResourceKey<DensityFunction> p_224479_, ResourceKey<DensityFunction> p_224480_, ResourceKey<DensityFunction> p_224481_, ResourceKey<DensityFunction> p_224482_, ResourceKey<DensityFunction> p_224483_, boolean p_224484_) {
    }

    @Shadow
    @Final
    private static ResourceKey<DensityFunction> OFFSET_LARGE;

    @Shadow
    @Final
    private static ResourceKey<DensityFunction> OFFSET_AMPLIFIED;

    @Shadow
    @Final
    private static ResourceKey<DensityFunction> JAGGEDNESS_LARGE;

    @Shadow
    @Final
    private static ResourceKey<DensityFunction> FACTOR_AMPLIFIED;

    @Shadow
    @Final
    private static ResourceKey<DensityFunction> JAGGEDNESS_AMPLIFIED;

    @Shadow
    @Final
    private static ResourceKey<DensityFunction> DEPTH_AMPLIFIED;

    @Shadow
    @Final
    private static ResourceKey<DensityFunction> SLOPED_CHEESE_AMPLIFIED;

    @Shadow
    @Final
    public static ResourceKey<DensityFunction> CONTINENTS;

    @Shadow
    @Final
    public static ResourceKey<DensityFunction> EROSION;

    @Shadow
    @Final
    private static ResourceKey<DensityFunction> PILLARS;

    @Shadow
    private static DensityFunction pillars(HolderGetter<NormalNoise.NoiseParameters> p_255985_) {
        return null;
    }

    @Shadow
    private static DensityFunction splineWithBlending(DensityFunction p_224454_, DensityFunction p_224455_) {
        return null;
    }

    @Shadow
    @Final
    public static ResourceKey<DensityFunction> RIDGES_FOLDED;

    @Shadow
    @Final
    private static DensityFunction BLENDING_FACTOR;

    @Shadow
    @Final
    private static DensityFunction BLENDING_JAGGEDNESS;

    @Shadow
    @Final
    private static ResourceKey<DensityFunction> BASE_3D_NOISE_END;

    @Shadow
    @Final
    private static ResourceKey<DensityFunction> BASE_3D_NOISE_OVERWORLD;

    @Shadow @Final private static ResourceKey<DensityFunction> SPAGHETTI_2D_THICKNESS_MODULATOR;

    @Shadow @Final private static ResourceKey<DensityFunction> SPAGHETTI_ROUGHNESS_FUNCTION;

    @Shadow private static DensityFunction slide(DensityFunction p_224444_, int p_224445_, int p_224446_, int p_224447_, int p_224448_, double p_224449_, int p_224450_, int p_224451_, double p_224452_){return null;}

    @Shadow @Final public static ResourceKey<DensityFunction> DEPTH;

    @Override
    public NoiseRouter overworld_large(HolderGetter<DensityFunction> p_255681_, HolderGetter<NormalNoise.NoiseParameters> p_256005_) {
        System.err.println("CREATES");
        DensityFunction densityfunction = DensityFunctions.noise(p_256005_.getOrThrow(Noises.AQUIFER_BARRIER), 0.5);
        DensityFunction densityfunction1 = DensityFunctions.noise(p_256005_.getOrThrow(Noises.AQUIFER_FLUID_LEVEL_FLOODEDNESS), 0.67);
        DensityFunction densityfunction2 = DensityFunctions.noise(p_256005_.getOrThrow(Noises.AQUIFER_FLUID_LEVEL_SPREAD), 0.7142857142857143);
        DensityFunction densityfunction3 = DensityFunctions.noise(p_256005_.getOrThrow(Noises.AQUIFER_LAVA));
        DensityFunction densityfunction4 = getFunction(p_255681_, SHIFT_X);
        DensityFunction densityfunction5 = getFunction(p_255681_, SHIFT_Z);
        DensityFunction densityfunction6 = DensityFunctions.shiftedNoise2d(
                densityfunction4, densityfunction5, 0.25, p_256005_.getOrThrow(RegNoiseData.TEMPERATURE_SUPER_LARGE)
        );

        DensityFunction densityfunction7 = DensityFunctions.shiftedNoise2d(
                densityfunction4, densityfunction5, 0.25, p_256005_.getOrThrow(RegNoiseData.VEGETATION_SUPER_LARGE)
        );
        DensityFunction densityfunction8 = getFunction(p_255681_, RegNoiseRouterData.FACTOR_LARGE);
        DensityFunction densityfunction9 = getFunction(p_255681_, RegNoiseRouterData.DEPTH_LARGE);
        DensityFunction densityfunction10 = noiseGradientDensity(DensityFunctions.cache2d(densityfunction8), densityfunction9);
        DensityFunction densityfunction11 = getFunction(p_255681_, RegNoiseRouterData.SLOPED_CHEESE_LARGE);
        DensityFunction densityfunction12 = DensityFunctions.min(
                densityfunction11, DensityFunctions.mul(DensityFunctions.constant(5.0), getFunction(p_255681_, ENTRANCES))
        );
        DensityFunction densityfunction13 = DensityFunctions.rangeChoice(
                densityfunction11, -1000000.0, 1.5625, densityfunction12, underground(p_255681_, p_256005_, densityfunction11)
        );
        DensityFunction densityfunction14 = DensityFunctions.min(postProcess(slideOverworld(false, densityfunction13)), getFunction(p_255681_, NOODLE));
        DensityFunction densityfunction15 = getFunction(p_255681_, Y);
        int i = Stream.of(VeinType.values()).mapToInt(p_224495_ -> p_224495_.minY).min().orElse(-DimensionType.MIN_Y * 2);
        int j = Stream.of(VeinType.values()).mapToInt(p_224457_ -> p_224457_.maxY).max().orElse(-DimensionType.MIN_Y * 2);
        DensityFunction densityfunction16 = yLimitedInterpolatable(densityfunction15, DensityFunctions.noise(p_256005_.getOrThrow(Noises.ORE_VEININESS), 1.5, 1.5), i, j, 0);
        float f = 4.0F;
        DensityFunction densityfunction17 = yLimitedInterpolatable(densityfunction15, DensityFunctions.noise(p_256005_.getOrThrow(Noises.ORE_VEIN_A), 4.0, 4.0), i, j, 0)
                .abs();
        DensityFunction densityfunction18 = yLimitedInterpolatable(densityfunction15, DensityFunctions.noise(p_256005_.getOrThrow(Noises.ORE_VEIN_B), 4.0, 4.0), i, j, 0)
                .abs();
        DensityFunction densityfunction19 = DensityFunctions.add(
                DensityFunctions.constant(-0.08F), DensityFunctions.max(densityfunction17, densityfunction18)
        );
        DensityFunction densityfunction20 = DensityFunctions.noise(p_256005_.getOrThrow(Noises.ORE_GAP));
        return new NoiseRouter(
                densityfunction,
                densityfunction1,
                densityfunction2,
                densityfunction3,
                densityfunction6,
                densityfunction7,
                getFunction(p_255681_, RegNoiseRouterData.CONTINENTS_LARGE),
                getFunction(p_255681_, RegNoiseRouterData.EROSION_LARGE),
                densityfunction9,
                getFunction(p_255681_, RIDGES),
                slideOverworld(false, DensityFunctions.add(densityfunction10, DensityFunctions.constant(-0.703125)).clamp(-64.0, 64.0)),
                densityfunction14,
                densityfunction16,
                densityfunction19,
                densityfunction20
        );
    }

    @Override
    public Holder<? extends DensityFunction> bootstraps(BootstrapContext<DensityFunction> p_335193_, ResourceKey<DensityFunction> key1, ResourceKey<DensityFunction> key2) {
        HolderGetter<DensityFunction> holdergetter1 = p_335193_.lookup(Registries.DENSITY_FUNCTION);
        HolderGetter<NormalNoise.NoiseParameters> holdergetter = p_335193_.lookup(Registries.NOISE);
        DensityFunction densityfunction3 = DensityFunctions.noise(holdergetter.getOrThrow(Noises.JAGGED), 1500.0, 0.0);
        DensityFunction densityfunction = registerAndWrap(
                p_335193_, SHIFT_X, DensityFunctions.flatCache(DensityFunctions.cache2d(DensityFunctions.shiftA(holdergetter.getOrThrow(Noises.SHIFT))))
        );
        DensityFunction densityfunction1 = registerAndWrap(
                p_335193_, SHIFT_Z, DensityFunctions.flatCache(DensityFunctions.cache2d(DensityFunctions.shiftB(holdergetter.getOrThrow(Noises.SHIFT))))
        );
        Holder<DensityFunction> holder = p_335193_.register(
                CONTINENTS,
                DensityFunctions.flatCache(DensityFunctions.shiftedNoise2d(densityfunction, densityfunction1, 0.25, holdergetter.getOrThrow(Noises.CONTINENTALNESS)))
        );
        Holder<DensityFunction> holder1 = p_335193_.register(
                EROSION,
                DensityFunctions.flatCache(DensityFunctions.shiftedNoise2d(densityfunction, densityfunction1, 0.25, holdergetter.getOrThrow(Noises.EROSION)))
        );
        Holder<DensityFunction> holder2 = p_335193_.register(
                CONTINENTS_LARGE,
                DensityFunctions.flatCache(DensityFunctions.shiftedNoise2d(densityfunction, densityfunction1, 0.25, holdergetter.getOrThrow(Noises.CONTINENTALNESS_LARGE)))
        );
        Holder<DensityFunction> holder3 = p_335193_.register(
                EROSION_LARGE,
                DensityFunctions.flatCache(DensityFunctions.shiftedNoise2d(densityfunction, densityfunction1, 0.25, holdergetter.getOrThrow(Noises.EROSION_LARGE)))
        );
        Holder<DensityFunction> holder4 = p_335193_.register(
                key1,
                DensityFunctions.flatCache(DensityFunctions.shiftedNoise2d(densityfunction, densityfunction1, 0.25, holdergetter.getOrThrow(RegNoiseData.CONTINENTALNESS_SUPER_LARGE)))
        );
        Holder<DensityFunction> holder5 = p_335193_.register(
                key2,
                DensityFunctions.flatCache(DensityFunctions.shiftedNoise2d(densityfunction, densityfunction1, 0.25, holdergetter.getOrThrow(RegNoiseData.EROSION_SUPER_LARGE)))
        );
        registerTerrainNoises(p_335193_, holdergetter1, densityfunction3, holder2, holder3, OFFSET_LARGE, FACTOR_LARGE, JAGGEDNESS_LARGE, DEPTH_LARGE, SLOPED_CHEESE_LARGE, false);
        registerTerrainNoises(p_335193_, holdergetter1, densityfunction3, holder4, holder5, RegNoiseRouterData.OFFSET_LARGE, RegNoiseRouterData.FACTOR_LARGE, RegNoiseRouterData.JAGGEDNESS_LARGE, RegNoiseRouterData.DEPTH_LARGE, RegNoiseRouterData.SLOPED_CHEESE_LARGE, false);
        registerTerrainNoises(p_335193_, holdergetter1, densityfunction3, holder, holder1, OFFSET_AMPLIFIED, FACTOR_AMPLIFIED, JAGGEDNESS_AMPLIFIED, DEPTH_AMPLIFIED, SLOPED_CHEESE_AMPLIFIED, true);
        return p_335193_.register(PILLARS, pillars(holdergetter));
    }


    @Override
    public NoiseRouter custom_overworld_large(HolderGetter<DensityFunction> p_255681_,
                                              HolderGetter<NormalNoise.NoiseParameters> p_256005_,
                                              int general_size,
                                              WorldParam.TemperatureParam temperatureParam,
                                              WorldParam.VegatatioParam vegatatioParam,
                                              WorldParam.ContinetsParam continetsParam,
                                              WorldParam.ErosionParam erosionParam,
                                              WorldParam.RidgesParam ridgesParam,
                                              int minY, int maxY,
                                              WorldParam.AdvancedSettings advancedSettings) {
        System.err.println("custom_overworld_large minY = " + minY + " maxY = " + maxY);
        int s = general_size + 4;
        int et = -(temperatureParam.getSize() - 4);
        int ev = -(vegatatioParam.getSize() - 4);
        int ec = -(continetsParam.getSize() - 4);
        int ee = -(erosionParam.getSize() - 4);
        int er = -(ridgesParam.getSize() - 4);
        float ot = temperatureParam.getAmplitude();
        float ov = vegatatioParam.getAmplitude();
        float oc = continetsParam.getAmplitude();
        float oe = erosionParam.getAmplitude();
        float or = ridgesParam.getAmplitude();
        float dst = (1000 - (float) temperatureParam.getDensity_size()) / 100;
        float dsv = (1000 - (float) vegatatioParam.getDensity_size()) / 100;
        float dsc = (1000 - (float) continetsParam.getDensity_size()) / 100;
        float dse = (1000 - (float) erosionParam.getDensity_size()) / 100;
        float dsr = (1000 - (float) ridgesParam.getDensity_size()) / 100;
        double[] at  = genOctave(temperatureParam.getAmplitude_distortion(),temperatureParam.getAmplitude_distortion_offset(),new double[]{0.0, 1.0, 0.0, 0.0, 0.0,0.0,0.0});
        double[] av  = genOctave(vegatatioParam.getAmplitude_distortion(),vegatatioParam.getAmplitude_distortion_offset(),new double[]{1.0, 0.0, 0.0, 0.0, 0.0});
        double[] ac  = genOctave(continetsParam.getAmplitude_distortion(),continetsParam.getAmplitude_distortion_offset(),new double[]{1.0, 2.0, 2.0, 2.0, 1.0, 1.0, 1.0, 1.0});
        double[] ae  = genOctave(erosionParam.getAmplitude_distortion(),erosionParam.getAmplitude_distortion_offset(),new double[]{1.0, 0.0, 1.0, 1.0});
        double[] ar  = genOctave(ridgesParam.getAmplitude_distortion(),ridgesParam.getAmplitude_distortion_offset(),new double[]{2.0, 1.0, 0.0, 0.0, 0.0});
        DensityFunction densityfunction = DensityFunctions.noise(p_256005_.getOrThrow(Noises.AQUIFER_BARRIER), advancedSettings.getAqualifierSettings().getAquifer_barrier());
        DensityFunction densityfunction1 = DensityFunctions.noise(p_256005_.getOrThrow(Noises.AQUIFER_FLUID_LEVEL_FLOODEDNESS), advancedSettings.getAqualifierSettings().getAquifer_fluid_level_floodness());
        DensityFunction densityfunction2 = DensityFunctions.noise(p_256005_.getOrThrow(Noises.AQUIFER_FLUID_LEVEL_SPREAD), advancedSettings.getAqualifierSettings().getAquifer_fluid_level_spread());
        DensityFunction densityfunction3 = DensityFunctions.noise(p_256005_.getOrThrow(Noises.AQUIFER_LAVA));
        DensityFunction densityfunction4 = getFunction(p_255681_, SHIFT_X);
        DensityFunction densityfunction5 = getFunction(p_255681_, SHIFT_Z);
        DensityFunction densityfunction6 = DensityFunctions.shiftedNoise2d(
                densityfunction4, densityfunction5, dst, new DirHolder<>(creareNoise(-10 + s + et, ot, 0.0 + at[0], 1.0 + at[1], 0.0 + at[2], 0.0 + at[3], 0.0 + at[4], 0.0 + at[5], 0.0 + at[6]), ResourceKey.create(Registries.NOISE, ResourceLocation.fromNamespaceAndPath(ExampleMod.MODID, "temperature_custom")))
        );
        NormalNoise.NoiseParameters ridge = creareNoise(-7 + s + er, or, 2.0 + ar[0], 1.0 + ar[1], 0.0 + ar[2], 0.0 + ar[3], 0.0 + ar[4]);
        DensityFunction ridgeDensity = DensityFunctions.flatCache(DensityFunctions.shiftedNoise2d(densityfunction4, densityfunction5, dsr, new DirHolder<>(ridge, ResourceKey.create(Registries.NOISE, ResourceLocation.fromNamespaceAndPath(ExampleMod.MODID, "ridges_custom")))));
        Holder<DensityFunction> densityFunctionHolder = new DirHolder<>(ridgeDensity, ResourceKey.create(Registries.DENSITY_FUNCTION, ResourceLocation.fromNamespaceAndPath(ExampleMod.MODID, "ridges_density_custom")));
        DensityFunction folRidgeDensity = peaksAndValleys(ridgeDensity,advancedSettings.getPeaksAndValleysSettings());
        DensityFunction densityfunction7 = DensityFunctions.shiftedNoise2d(
                densityfunction4, densityfunction5, dsv, new DirHolder<>(creareNoise(-8 + s + ev, ov, 1.0 + av[0], 0.0 + av[1], 0.0 + av[2], 0.0 + av[3], 0.0 + av[4]), ResourceKey.create(Registries.NOISE, ResourceLocation.fromNamespaceAndPath(ExampleMod.MODID, "vegetation_custom")))
        );
        DensityFunction d1 = DensityFunctions.flatCache(DensityFunctions.shiftedNoise2d(densityfunction4, densityfunction5, dsc, new DirHolder<>(creareNoise(-9 + s + ec, oc, 1.0 + ac[0], 2.0 + ac[1], 2.0 + ac[2], 2.0 + ac[3], 1.0 + ac[4], 1.0 + ac[5], 1.0 + ac[6], 1.0 + ac[7]), ResourceKey.create(Registries.NOISE, ResourceLocation.fromNamespaceAndPath(ExampleMod.MODID, "continentalness_custom")))));
        DensityFunction d2 = DensityFunctions.flatCache(DensityFunctions.shiftedNoise2d(densityfunction4, densityfunction5, dse, new DirHolder<>(creareNoise(-9 + s + ee, oe, 1.0 + ae[0], 0.0 + ae[1], 1.0 + ae[2], 1.0 + ae[3]), ResourceKey.create(Registries.NOISE, ResourceLocation.fromNamespaceAndPath(ExampleMod.MODID, "erosion_custom")))));
        DensityFunction densityfunction111 = DensityFunctions.noise(p_256005_.getOrThrow(Noises.JAGGED), advancedSettings.getGeneralNoiseSettings().getJuggedness_xzScale(), 0.0);
        DensityFunction densityfunction8 = getFactor(new DirHolder<>(d1, ResourceKey.create(Registries.DENSITY_FUNCTION, ResourceLocation.fromNamespaceAndPath(ExampleMod.MODID, "factor_continentalness_custom"))), new DirHolder<>(d2, ResourceKey.create(Registries.DENSITY_FUNCTION, ResourceLocation.fromNamespaceAndPath(ExampleMod.MODID, "factor_erosion_custom"))), p_255681_, densityfunction111, densityFunctionHolder, folRidgeDensity,advancedSettings.getGeneralNoiseSettings().getGlobalOffset(),advancedSettings.getGeneralNoiseSettings().getDepth_min_ampitude(),advancedSettings.getGeneralNoiseSettings().getDepth_max_ampitude(),minY,maxY,advancedSettings.getGeneralNoiseSettings().getBlending_factor(),advancedSettings.getGeneralNoiseSettings().getJuggedness_factor(),advancedSettings.getBlendingNoisesSettings(), 1);
        DensityFunction densityfunction9 = getFactor(new DirHolder<>(d1, ResourceKey.create(Registries.DENSITY_FUNCTION, ResourceLocation.fromNamespaceAndPath(ExampleMod.MODID, "def_continentalness_custom"))), new DirHolder<>(d2, ResourceKey.create(Registries.DENSITY_FUNCTION, ResourceLocation.fromNamespaceAndPath(ExampleMod.MODID, "def_erosion_custom"))), p_255681_, densityfunction111, densityFunctionHolder, folRidgeDensity,advancedSettings.getGeneralNoiseSettings().getGlobalOffset(),advancedSettings.getGeneralNoiseSettings().getDepth_min_ampitude(),advancedSettings.getGeneralNoiseSettings().getDepth_max_ampitude(),minY,maxY,advancedSettings.getGeneralNoiseSettings().getBlending_factor(),advancedSettings.getGeneralNoiseSettings().getJuggedness_factor(),advancedSettings.getBlendingNoisesSettings(), 2);
        DensityFunction densityfunction10 = noiseGradientDensity(DensityFunctions.cache2d(densityfunction8), densityfunction9);
        DensityFunction densityfunction11 = getFactor(new DirHolder<>(d1, ResourceKey.create(Registries.DENSITY_FUNCTION, ResourceLocation.fromNamespaceAndPath(ExampleMod.MODID, "slide_continentalness_custom"))), new DirHolder<>(d2, ResourceKey.create(Registries.DENSITY_FUNCTION, ResourceLocation.fromNamespaceAndPath(ExampleMod.MODID, "slide_erosion_custom"))), p_255681_, densityfunction111, densityFunctionHolder, folRidgeDensity,advancedSettings.getGeneralNoiseSettings().getGlobalOffset(),advancedSettings.getGeneralNoiseSettings().getDepth_min_ampitude(),advancedSettings.getGeneralNoiseSettings().getDepth_max_ampitude(),minY,maxY,advancedSettings.getGeneralNoiseSettings().getBlending_factor(),advancedSettings.getGeneralNoiseSettings().getJuggedness_factor(),advancedSettings.getBlendingNoisesSettings(), 4);
        DensityFunction ddd;
        try {
            ddd =entrances(p_255681_,p_256005_,advancedSettings.getEnranceSettings(),advancedSettings.getSpaghettiRougnessSettings());
        } catch (Exception e) {
            throw new RuntimeException("SUPER ERROR 8: " + e.getMessage());
        }
        DensityFunction densityfunction12 = DensityFunctions.min(
                densityfunction11, DensityFunctions.mul(DensityFunctions.constant(advancedSettings.getEnranceSettings().getCaveEntranceMULTIPLIER()), ddd)
        );
        DensityFunction dd;
        try {
            dd = spaghetti2D(p_255681_, p_256005_, minY, maxY,advancedSettings.getSpaghetti2DSettings());
        } catch (Exception e) {
            throw new RuntimeException("SUPER ERROR: " + e.getMessage());
        }
        DensityFunction densityfunction13 = DensityFunctions.rangeChoice(
                densityfunction11, -1000000.0, advancedSettings.getGeneralNoiseSettings().getSurface_density_threshold(), densityfunction12, underground(p_255681_, p_256005_, densityfunction11,dd,minY,advancedSettings.getUndergroundSettings(),ddd,advancedSettings.getSpaghettiRougnessSettings())
        );
        DensityFunction densityfunction14 = DensityFunctions.min(postProcess(slideOverworld(densityfunction13,advancedSettings.getSlideSettings(),minY,maxY)), noodle(p_255681_,p_256005_,minY,maxY,advancedSettings.getNoodleSettings()));
        DensityFunction densityfunction15 = getFunction(p_255681_, Y);
        int i = Stream.of(VeinType.values()).mapToInt(p_224495_ -> p_224495_.minY).min().orElse(-DimensionType.MIN_Y * 2);
        int j = Stream.of(VeinType.values()).mapToInt(p_224457_ -> p_224457_.maxY).max().orElse(-DimensionType.MIN_Y * 2);
        DensityFunction densityfunction16 = yLimitedInterpolatable(densityfunction15, DensityFunctions.noise(p_256005_.getOrThrow(Noises.ORE_VEININESS), advancedSettings.getGeneralNoiseSettings().getVeininess_frequency(), advancedSettings.getGeneralNoiseSettings().getVeininess_frequency()), i, j, 0);
        float f = 4.0F;
        DensityFunction densityfunction17 = yLimitedInterpolatable(densityfunction15, DensityFunctions.noise(p_256005_.getOrThrow(Noises.ORE_VEIN_A), 4.0, 4.0), i, j, 0)
                .abs();
        DensityFunction densityfunction18 = yLimitedInterpolatable(densityfunction15, DensityFunctions.noise(p_256005_.getOrThrow(Noises.ORE_VEIN_B), 4.0, 4.0), i, j, 0)
                .abs();
        DensityFunction densityfunction19 = DensityFunctions.add(
                DensityFunctions.constant(-advancedSettings.getGeneralNoiseSettings().getOre_thickness()), DensityFunctions.max(densityfunction17, densityfunction18)
        );
        DensityFunction densityfunction20 = DensityFunctions.noise(p_256005_.getOrThrow(Noises.ORE_GAP));
        return new NoiseRouter(
                densityfunction,
                densityfunction1,
                densityfunction2,
                densityfunction3,
                densityfunction6,
                densityfunction7,
                d1,
                d2,
                densityfunction9,
                ridgeDensity,
                slideOverworld(DensityFunctions.add(densityfunction10, DensityFunctions.constant(advancedSettings.getGeneralNoiseSettings().getCheese_noise_target())).clamp(-advancedSettings.getGeneralNoiseSettings().getCheese_noise_clamp_factor(), advancedSettings.getGeneralNoiseSettings().getCheese_noise_clamp_factor()),advancedSettings.getSlideSettings(),minY,maxY),
                densityfunction14,
                densityfunction16,
                densityfunction19,
                densityfunction20
        );
    }

    private static NormalNoise.NoiseParameters creareNoise(int p_256539_,
                                                           double p_256566_,
                                                           double... p_255998_) {
        return new NormalNoise.NoiseParameters(p_256539_, p_256566_, p_255998_);
    }

    private static DensityFunction getFactor(Holder<DensityFunction> p_224477_,
                                             Holder<DensityFunction> p_224478_,
                                             HolderGetter<DensityFunction> p_256393_,
                                             DensityFunction p_224476_,
                                             Holder<DensityFunction> ridges,
                                             DensityFunction ridgesFolded,
                                             float global_offset,
                                             float depthMin,
                                             float depthMax,
                                             float minY,
                                             float maxY,
                                             float blending_factor,
                                             float juggedness_factor,
                                             WorldParam.AdvancedSettings.BlendingNoisesSettings blendingNoisesSettings,
                                             int i) {
        return generateTerrainNoises(p_224477_, p_224478_, p_256393_, p_224476_, ridges, ridgesFolded,global_offset, depthMin, depthMax, minY, maxY,blending_factor, juggedness_factor, blendingNoisesSettings, false)[i];
    }

    private static DensityFunction[] generateTerrainNoises(
            Holder<DensityFunction> p_224477_,
            Holder<DensityFunction> p_224478_,
            HolderGetter<DensityFunction> p_256393_,
            DensityFunction p_224476_,
            Holder<DensityFunction> ridges,
            DensityFunction ridgesFolded,
            float global_offset,
            float depthMin,
            float depthMax,
            float minY,
            float maxY,
            float blending_factor,
            float juggedness_factor,
            WorldParam.AdvancedSettings.BlendingNoisesSettings blendingNoisesSettings,
            boolean p_224484_
    ) {
        DensityFunctions.Spline.Coordinate densityfunctions$spline$coordinate = new DensityFunctions.Spline.Coordinate(p_224477_);
        DensityFunctions.Spline.Coordinate densityfunctions$spline$coordinate1 = new DensityFunctions.Spline.Coordinate(p_224478_);
        DensityFunctions.Spline.Coordinate densityfunctions$spline$coordinate2 = new DensityFunctions.Spline.Coordinate(ridges);
        DensityFunctions.Spline.Coordinate densityfunctions$spline$coordinate3 = new DensityFunctions.Spline.Coordinate(new DirHolder<>(ridgesFolded, ResourceKey.create(Registries.DENSITY_FUNCTION, ResourceLocation.fromNamespaceAndPath(ExampleMod.MODID, "ridged_folded_custom"))));
        // Создание densityfunction аналогично оригинальному коду
        DensityFunction densityfunction = splineWithBlending(
                DensityFunctions.add(
                        DensityFunctions.constant(global_offset),
                        DensityFunctions.spline(
                                TerrainProvider.overworldOffset(
                                        densityfunctions$spline$coordinate, densityfunctions$spline$coordinate1, densityfunctions$spline$coordinate3, p_224484_
                                )
                        )
                ),
                DensityFunctions.blendOffset()
        );

        DensityFunction densityfunction1 = splineWithBlending(
                DensityFunctions.spline(
                        TerrainProvider.overworldFactor(
                                densityfunctions$spline$coordinate,
                                densityfunctions$spline$coordinate1,
                                densityfunctions$spline$coordinate2,
                                densityfunctions$spline$coordinate3,
                                p_224484_
                        )
                ),
                DensityFunctions.constant(blending_factor)
        );

        DensityFunction densityfunction2 = DensityFunctions.add(
                DensityFunctions.yClampedGradient(-64, 320, depthMax, depthMin), densityfunction
        );

        DensityFunction densityfunction3 = splineWithBlending(
                DensityFunctions.spline(
                        TerrainProvider.overworldJaggedness(
                                densityfunctions$spline$coordinate,
                                densityfunctions$spline$coordinate1,
                                densityfunctions$spline$coordinate2,
                                densityfunctions$spline$coordinate3,
                                p_224484_
                        )
                ),
                DensityFunctions.constant(juggedness_factor)
        );
        DensityFunction densityfunction4 = DensityFunctions.mul(densityfunction3, p_224476_.halfNegative());
        DensityFunction densityfunction5 = noiseGradientDensity(densityfunction1, DensityFunctions.add(densityfunction2, densityfunction4));
        DensityFunction finalDensityFunction = DensityFunctions.add(densityfunction5, BlendedNoise.createUnseeded(blendingNoisesSettings.getXzScale(), blendingNoisesSettings.getYScale(), blendingNoisesSettings.getXzFactor(), blendingNoisesSettings.getYFactor(), blendingNoisesSettings.getSmearScaleMultiplier()));

        // Создание массива DensityFunction
        return new DensityFunction[]{
                densityfunction, // offset
                densityfunction1, // factor
                densityfunction2, // depth
                densityfunction3, // jaggedness
                finalDensityFunction // gradient

        };
    }
    private static double[] genOctave(int distortion, int strength, double[] octaves) {
        float dist = (float)distortion / 10;
        float st = (float) strength / 10;
        double[] oct = new double[octaves.length];
        for (int i = 0; i < octaves.length; i++) {
            oct[i] = Math.max(0,st - octaves[i]);
            dist-= (float) oct[i];
            if (dist < 0) {
                oct[i]+= dist;
                break;
            }
        }
        return oct;
    }
    @Unique
    private static DensityFunction spaghetti2D(HolderGetter<DensityFunction> p_256535_, HolderGetter<NormalNoise.NoiseParameters> p_255650_, int minY, int maxY, WorldParam.AdvancedSettings.Spageti2DSettings spageti2DSettings) throws Exception {
        DensityFunction densityfunction = DensityFunctions.noise(p_255650_.getOrThrow(Noises.SPAGHETTI_2D_MODULATOR), spageti2DSettings.getSpaghetti2DFrequency(), 1.0);
        int i = minY + 4;
        int j = maxY;
        //
        Class<?> weirdScaledSamplerClass = Class.forName("net.minecraft.world.level.levelgen.DensityFunctions$WeirdScaledSampler");

        Class<?> rarityValueMapperClass = Class.forName("net.minecraft.world.level.levelgen.DensityFunctions$WeirdScaledSampler$RarityValueMapper");

        // Получаем доступ к полю TYPE2
        Field type2Field = rarityValueMapperClass.getDeclaredField("TYPE2");
        type2Field.setAccessible(true);
        Object type2Value = type2Field.get(null); // так как поле статическое

        // Теперь вы можете использовать type2Value в DensityFunctions.weirdScaledSampler
        // например:
        Constructor<?> constructor = weirdScaledSamplerClass.getDeclaredConstructor(
                DensityFunction.class,
                DensityFunction.NoiseHolder.class,
                rarityValueMapperClass
        );
        constructor.setAccessible(true);
        Object weirdScaledSamplerInstance = constructor.newInstance(densityfunction, new DensityFunction.NoiseHolder(p_255650_.getOrThrow(Noises.SPAGHETTI_2D)), type2Value);
        DensityFunction densityfunction1 = (DensityFunction) weirdScaledSamplerInstance;
        //
        DensityFunction densityfunction2 = DensityFunctions.mappedNoise(p_255650_.getOrThrow(Noises.SPAGHETTI_2D_ELEVATION), 0.0, (double)Math.floorDiv(minY, 8), 8.0);
        DensityFunction densityfunction3 = getFunction(p_256535_, SPAGHETTI_2D_THICKNESS_MODULATOR);
        DensityFunction densityfunction4 = DensityFunctions.add(densityfunction2, DensityFunctions.yClampedGradient(minY, j, 8.0, minY + 24.0D)).abs();
        DensityFunction densityfunction5 = DensityFunctions.add(densityfunction4, densityfunction3).cube();
        double d0 = spageti2DSettings.getSpaghetti2DThickness();
        DensityFunction densityfunction6 = DensityFunctions.add(
                densityfunction1, DensityFunctions.mul(DensityFunctions.constant(d0), densityfunction3)
        );
        return DensityFunctions.max(densityfunction6, densityfunction5).clamp(-spageti2DSettings.getSpaghetti2DFrequency(), spageti2DSettings.getSpaghetti2DFrequency());
    }
    @Unique
    private static DensityFunction noodle(HolderGetter<DensityFunction> p_256402_, HolderGetter<NormalNoise.NoiseParameters> p_255632_, int minY, int maxY, WorldParam.AdvancedSettings.NoodleSettings noodleSettings) {
        DensityFunction densityfunction = getFunction(p_256402_, Y);
        int i = minY + 4;
        int j = maxY;
        DensityFunction densityfunction1 = yLimitedInterpolatable(densityfunction, DensityFunctions.noise(p_255632_.getOrThrow(Noises.NOODLE), noodleSettings.getNoodleFrequency(), noodleSettings.getNoodleFrequency()), i, j, -1);
        DensityFunction densityfunction2 = yLimitedInterpolatable(
                densityfunction, DensityFunctions.mappedNoise(p_255632_.getOrThrow(Noises.NOODLE_THICKNESS), 1.0, 1.0, noodleSettings.getNoodleThicknessOffset1(), noodleSettings.getNoodleThicknessOffset2()), i, j, 0
        );
        double d0 = noodleSettings.getRidgeFrequency();
        DensityFunction densityfunction3 = yLimitedInterpolatable(
                densityfunction, DensityFunctions.noise(p_255632_.getOrThrow(Noises.NOODLE_RIDGE_A), d0, d0), i, j, 0
        );
        DensityFunction densityfunction4 = yLimitedInterpolatable(
                densityfunction, DensityFunctions.noise(p_255632_.getOrThrow(Noises.NOODLE_RIDGE_B), d0, d0), i, j, 0
        );
        DensityFunction densityfunction5 = DensityFunctions.mul(
                DensityFunctions.constant(1.5), DensityFunctions.max(densityfunction3.abs(), densityfunction4.abs())
        );
        return DensityFunctions.rangeChoice(
                densityfunction1, -1000000.0, 0.0, DensityFunctions.constant(-minY), DensityFunctions.add(densityfunction2, densityfunction5)
        );
    }
    @Unique
    private static DensityFunction underground(
            HolderGetter<DensityFunction> p_256548_, HolderGetter<NormalNoise.NoiseParameters> p_256236_, DensityFunction p_256658_, DensityFunction dd, double miny, WorldParam.AdvancedSettings.UndergroundSettings undergroundSettings, DensityFunction ent, WorldParam.AdvancedSettings.SpagetiRougnessSettings r
            ) {
        DensityFunction densityfunction = dd;
        DensityFunction densityfunction1 = spaghettiRoughnessFunction(p_256236_,r);
        DensityFunction densityfunction2 = DensityFunctions.noise(p_256236_.getOrThrow(Noises.CAVE_LAYER), undergroundSettings.getCaveLayerFrequency());
        DensityFunction densityfunction3 = DensityFunctions.mul(DensityFunctions.constant(undergroundSettings.getCaveCheeseFactor() * 0.5f), densityfunction2.square());
        DensityFunction densityfunction4 = DensityFunctions.noise(p_256236_.getOrThrow(Noises.CAVE_CHEESE), undergroundSettings.getCaveCheeseFactor());
        DensityFunction densityfunction5 = DensityFunctions.add(
                DensityFunctions.add(DensityFunctions.constant(0.27), densityfunction4).clamp(-1.0, 1.0),
                DensityFunctions.add(DensityFunctions.constant(1.5), DensityFunctions.mul(DensityFunctions.constant(miny * 0.01), p_256658_))
                        .clamp(0.0, 0.5)
        );
        DensityFunction densityfunction6 = DensityFunctions.add(densityfunction3, densityfunction5);
        DensityFunction densityfunction7 = DensityFunctions.min(
                DensityFunctions.min(densityfunction6, ent), DensityFunctions.add(densityfunction, densityfunction1)
        );
        DensityFunction densityfunction8 = getFunction(p_256548_, PILLARS);
        DensityFunction densityfunction9 = DensityFunctions.rangeChoice(
                densityfunction8, -1000000.0, undergroundSettings.getPillarDensity(), DensityFunctions.constant(-1000000.0), densityfunction8
        );
        return DensityFunctions.max(densityfunction7, densityfunction9);
    }
    private static DensityFunction entrances(HolderGetter<DensityFunction> p_256511_, HolderGetter<NormalNoise.NoiseParameters> p_255899_, WorldParam.AdvancedSettings.EnranceSettings  entranceSettings,WorldParam.AdvancedSettings.SpagetiRougnessSettings spagetiRougnessSettings) throws Exception {
        DensityFunction densityfunction = DensityFunctions.cacheOnce(DensityFunctions.noise(p_255899_.getOrThrow(Noises.SPAGHETTI_3D_RARITY), entranceSettings.getSpaghettiRarityFrequency(), entranceSettings.getSpaghettiRarityFrequency() * 0.5));
        DensityFunction densityfunction1 = DensityFunctions.mappedNoise(p_255899_.getOrThrow(Noises.SPAGHETTI_3D_THICKNESS), entranceSettings.getSpaghettiThicknessOffset1(), entranceSettings.getSpaghettiThicknessOffset2(), 8.0, 8.0);
        Class<?> weirdScaledSamplerClass = Class.forName("net.minecraft.world.level.levelgen.DensityFunctions$WeirdScaledSampler");
        Class<?> rarityValueMapperClass = Class.forName("net.minecraft.world.level.levelgen.DensityFunctions$WeirdScaledSampler$RarityValueMapper");
        Field type2Field = rarityValueMapperClass.getDeclaredField("TYPE2");
        Field type1Field = rarityValueMapperClass.getDeclaredField("TYPE1");
        type2Field.setAccessible(true);
        type1Field.setAccessible(true);
        Object type2Value = type2Field.get(null);
        Object type1Value = type1Field.get(null);
        Constructor<?> constructor = weirdScaledSamplerClass.getDeclaredConstructor(
                DensityFunction.class,
                DensityFunction.NoiseHolder.class,
                rarityValueMapperClass
        );
        constructor.setAccessible(true);
        Object weirdScaledSamplerInstance = constructor.newInstance(densityfunction, new DensityFunction.NoiseHolder(p_255899_.getOrThrow(Noises.SPAGHETTI_2D)), type2Value);
        DensityFunction densityfunction2 = (DensityFunction) weirdScaledSamplerInstance;
        DensityFunction densityfunction3 = (DensityFunction) constructor.newInstance(densityfunction, new DensityFunction.NoiseHolder(p_255899_.getOrThrow(Noises.SPAGHETTI_3D_2)), type1Value);
        float m = entranceSettings.getSpaghettiRarityFrequency() * 0.5f;
        DensityFunction densityfunction4 = DensityFunctions.add(DensityFunctions.max(densityfunction2, densityfunction3), densityfunction1)
                .clamp(-m, m);
        DensityFunction densityfunction5 = spaghettiRoughnessFunction(p_255899_,spagetiRougnessSettings);
        DensityFunction densityfunction6 = DensityFunctions.noise(p_255899_.getOrThrow(Noises.CAVE_ENTRANCE), entranceSettings.getCaveEntranceFrequency(), entranceSettings.getCaveEntranceAmplitude());
        DensityFunction densityfunction7 = DensityFunctions.add(
                DensityFunctions.add(densityfunction6, DensityFunctions.constant(0.37)), DensityFunctions.yClampedGradient(-10, 30, 0.3, 0.0)
        );
        return DensityFunctions.cacheOnce(DensityFunctions.min(densityfunction7, DensityFunctions.add(densityfunction5, densityfunction4)));
    }
    private static DensityFunction spaghettiRoughnessFunction(HolderGetter<NormalNoise.NoiseParameters> p_255763_, WorldParam.AdvancedSettings.SpagetiRougnessSettings spagetiRougnessSettings) {
        DensityFunction densityfunction = DensityFunctions.noise(p_255763_.getOrThrow(Noises.SPAGHETTI_ROUGHNESS));
        DensityFunction densityfunction1 = DensityFunctions.mappedNoise(p_255763_.getOrThrow(Noises.SPAGHETTI_ROUGHNESS_MODULATOR), 0.0, -0.1);
        return DensityFunctions.cacheOnce(
                DensityFunctions.mul(densityfunction1, DensityFunctions.add(densityfunction.abs(), DensityFunctions.constant(spagetiRougnessSettings.getScalingFactor())))
        );
    }
    private static DensityFunction peaksAndValleys(DensityFunction p_224438_,WorldParam.AdvancedSettings.PeaksAndValleysSettings peaksAndValleysSettings) {
        return DensityFunctions.mul(
                DensityFunctions.add(
                        DensityFunctions.add(p_224438_.abs(), DensityFunctions.constant(peaksAndValleysSettings.getAbsValueAdjustment())).abs(),
                        DensityFunctions.constant(peaksAndValleysSettings.getFinalAdjustment())
                ),
                DensityFunctions.constant(peaksAndValleysSettings.getScalingFactor())
        );
    }
    private static DensityFunction slideOverworld(DensityFunction p_224491_, WorldParam.AdvancedSettings.SlideSettings slideSettings,int minY, int maxY) {
        return slide(p_224491_, minY, maxY, (int) slideSettings.getVerticalShiftStart(), (int) slideSettings.getVerticalShiftEnd(), slideSettings.getShiftIntensity(), (int) slideSettings.getFinalGradientStart(), (int) slideSettings.getFinalGradientEnd(), slideSettings.getFinalInterpolationFactor());
    }
}
