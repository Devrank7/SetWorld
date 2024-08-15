package com.example.setworld.mixins.noise;

import com.example.setworld.inter.IRandomState;
import com.example.setworld.world.NoiseWritingHelp;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.biome.Climate;
import net.minecraft.world.level.levelgen.*;
import net.minecraft.world.level.levelgen.synth.BlendedNoise;
import net.minecraft.world.level.levelgen.synth.NormalNoise;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Mixin(RandomState.class)
public class MixRandomState implements IRandomState {

    @Mutable
    @Shadow
    @Final
    PositionalRandomFactory random;

    @Mutable
    @Shadow
    @Final
    private Map<ResourceKey<NormalNoise.NoiseParameters>, NormalNoise> noiseIntances;

    @Mutable
    @Shadow
    @Final
    private HolderGetter<NormalNoise.NoiseParameters> noises;

    @Mutable
    @Shadow
    @Final
    private PositionalRandomFactory aquiferRandom;

    @Mutable
    @Shadow
    @Final
    private PositionalRandomFactory oreRandom;

    @Mutable
    @Shadow
    @Final
    private Map<ResourceLocation, PositionalRandomFactory> positionalRandoms;

    @Mutable
    @Shadow
    @Final
    private SurfaceSystem surfaceSystem;

    @Mutable
    @Shadow
    @Final
    private NoiseRouter router;

    @Mutable
    @Shadow
    @Final
    private Climate.Sampler sampler;

    /**
     * @author
     * @reason
     */
    @Overwrite
    public NormalNoise getOrCreateNoise(ResourceKey<NormalNoise.NoiseParameters> p_224561_) {
        return noiseIntances.computeIfAbsent(p_224561_, p_255589_ -> Noises.instantiate(this.noises, this.random, p_224561_));
    }

    private static NormalNoise instantiate(
            Holder<NormalNoise.NoiseParameters> p_256362_, PositionalRandomFactory p_256306_, ResourceKey<NormalNoise.NoiseParameters> p_256639_
    ) {
        return NormalNoise.create(p_256306_.fromHashOf(p_256362_.unwrapKey().orElseThrow().location()), p_256362_.value());
    }

    @Redirect(method = "<init>", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/levelgen/NoiseRouter;mapAll(Lnet/minecraft/world/level/levelgen/DensityFunction$Visitor;)Lnet/minecraft/world/level/levelgen/NoiseRouter;"))
    private NoiseRouter initOn(NoiseRouter noiseRouter, DensityFunction.Visitor visitor, NoiseGeneratorSettings p_255668_, HolderGetter<NormalNoise.NoiseParameters> p_256663_, final long p_255691_) {
        System.err.println("Redirect");
        return noiseRouter.mapAll(new NoiseWritingHelp.NoiseWiringHelper((RandomState) (Object) this, this.random, p_255668_.useLegacyRandomSource(), p_255691_));
    }

    @Override
    public NormalNoise getOrCreateNoiseOr(ResourceKey<NormalNoise.NoiseParameters> p_224561_, Holder<NormalNoise.NoiseParameters> holder) {
        return noiseIntances.computeIfAbsent(p_224561_, p_255589_ -> instantiate(holder, this.random, p_224561_));
    }
}