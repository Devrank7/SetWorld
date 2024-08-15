package com.example.setworld.world;

import com.example.setworld.inter.IRandomState;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.levelgen.*;
import net.minecraft.world.level.levelgen.synth.BlendedNoise;
import net.minecraft.world.level.levelgen.synth.NormalNoise;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;

public class NoiseWritingHelp {

    public static class NoiseWiringHelper implements DensityFunction.Visitor {
        private final Map<DensityFunction, DensityFunction> wrapped = new HashMap<>();
        RandomState randomState;
        PositionalRandomFactory randomSource;
        long p_255691_;
        boolean flag;
        public NoiseWiringHelper(RandomState randomState,PositionalRandomFactory randomSource, boolean flag, long p_255691_) {
            this.randomState = randomState;
            this.randomSource = randomSource;
            this.flag = flag;
            this.p_255691_ = p_255691_;
        }

        private RandomSource newLegacyInstance(long p_224592_) {
            return new LegacyRandomSource(p_255691_ + p_224592_);
        }

        @Override
        public DensityFunction.NoiseHolder visitNoise(DensityFunction.NoiseHolder p_224594_) {
            Holder<NormalNoise.NoiseParameters> holder = p_224594_.noiseData();
            if (flag) {
                if (holder.is(Noises.TEMPERATURE)) {
                    NormalNoise normalnoise3 = NormalNoise.createLegacyNetherBiome(this.newLegacyInstance(0L), new NormalNoise.NoiseParameters(-7, 1.0, 1.0));
                    return new DensityFunction.NoiseHolder(holder, normalnoise3);
                }

                if (holder.is(Noises.VEGETATION)) {
                    NormalNoise normalnoise2 = NormalNoise.createLegacyNetherBiome(this.newLegacyInstance(1L), new NormalNoise.NoiseParameters(-7, 1.0, 1.0));
                    return new DensityFunction.NoiseHolder(holder, normalnoise2);
                }

                if (holder.is(Noises.SHIFT)) {
                    NormalNoise normalnoise1 = NormalNoise.create(
                            randomSource.fromHashOf(Noises.SHIFT.location()), new NormalNoise.NoiseParameters(0, 0.0)
                    );
                    return new DensityFunction.NoiseHolder(holder, normalnoise1);
                }
            }
            ResourceKey<NormalNoise.NoiseParameters> noiseParametersResourceKey =holder.unwrapKey().orElseThrow();
            String path = noiseParametersResourceKey.location().getPath();
            boolean isCustom = path.contains("custom");
            System.err.println("ERR = " + holder.unwrapKey().orElse(null) + " CUSTOM = " + isCustom);
            NormalNoise normalnoise = isCustom ? ((IRandomState)(Object)randomState).getOrCreateNoiseOr(holder.unwrapKey().orElseThrow(), holder) : randomState.getOrCreateNoise(holder.unwrapKey().orElseThrow());
            return new DensityFunction.NoiseHolder(holder, normalnoise);
        }

        private DensityFunction wrapNew(DensityFunction p_224596_) {
            if (p_224596_ instanceof BlendedNoise blendednoise) {
                RandomSource randomsource = flag ? this.newLegacyInstance(0L) : randomSource.fromHashOf(ResourceLocation.withDefaultNamespace("terrain"));
                return blendednoise.withNewRandom(randomsource);
            } else {
                try {
                    Class<?> shiftedNoiseClass = Class.forName("net.minecraft.world.level.levelgen.DensityFunctions$EndIslandDensityFunction");
                    if (shiftedNoiseClass.isInstance(p_224596_)) {
                        Constructor<?> constructor = shiftedNoiseClass.getDeclaredConstructor(long.class);
                        constructor.setAccessible(true);  // Отключаем проверки доступа
                        return (DensityFunction) constructor.newInstance(p_255691_);
                    } else {
                        return p_224596_;
                    }
                } catch (Throwable e) {
                    throw new RuntimeException(e);
                }
            }
        }

        @Override
        public DensityFunction apply(DensityFunction p_224598_) {
            return this.wrapped.computeIfAbsent(p_224598_, this::wrapNew);
        }
    }
}
