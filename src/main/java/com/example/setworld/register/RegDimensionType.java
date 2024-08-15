package com.example.setworld.register;

import com.example.setworld.ExampleMod;
import com.google.common.collect.Maps;
import com.mojang.serialization.Lifecycle;
import net.minecraft.core.*;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.DimensionTypes;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.Bootstrap;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.level.dimension.BuiltinDimensionTypes;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.levelgen.synth.NormalNoise;

import java.util.Map;
import java.util.OptionalLong;
import java.util.function.Supplier;

public class RegDimensionType {
    public static final ResourceKey<DimensionType> OVERWORLD_16_0 = register("overworld_16_0");
    public static final ResourceKey<DimensionType> OVERWORLD_32_0 = register("overworld_32_0");
    public static final ResourceKey<DimensionType> OVERWORLD_48_0 = register("overworld_48_0");
    public static final ResourceKey<DimensionType> OVERWORLD_64_0 = register("overworld_64_0");
    public static final ResourceKey<DimensionType> OVERWORLD_80_0 = register("overworld_80_0");
    public static final ResourceKey<DimensionType> OVERWORLD_96_0 = register("overworld_96_0");
    public static final ResourceKey<DimensionType> OVERWORLD_112_0 = register("overworld_112_0");
    public static final ResourceKey<DimensionType> OVERWORLD_128_0 = register("overworld_128_0");
    public static final ResourceKey<DimensionType> OVERWORLD_192_0 = register("overworld_192_0");
    public static final ResourceKey<DimensionType> OVERWORLD_0_16 = register("overworld_0_16");
    public static final ResourceKey<DimensionType> OVERWORLD_0_32 = register("overworld_0_32");
    public static final ResourceKey<DimensionType> OVERWORLD_0_64 = register("overworld_0_64");
    public static final ResourceKey<DimensionType> OVERWORLD_0_128 = register("overworld_0_128");
    public static final ResourceKey<DimensionType> OVERWORLD_0_256 = register("overworld_0_256");
    public static final ResourceKey<DimensionType> OVERWORLD_16_16 = register("overworld_16_16");
    public static final ResourceKey<DimensionType> OVERWORLD_16_32 = register("overworld_16_32");
    public static final ResourceKey<DimensionType> OVERWORLD_16_64 = register("overworld_16_64");
    public static final ResourceKey<DimensionType> OVERWORLD_16_128 = register("overworld_16_128");
    public static final ResourceKey<DimensionType> OVERWORLD_16_256 = register("overworld_16_256");
    public static final ResourceKey<DimensionType> OVERWORLD_32_16 = register("overworld_32_16");
    public static final ResourceKey<DimensionType> OVERWORLD_32_32 = register("overworld_32_32");
    public static final ResourceKey<DimensionType> OVERWORLD_32_64 = register("overworld_32_64");
    public static final ResourceKey<DimensionType> OVERWORLD_32_128 = register("overworld_32_128");
    public static final ResourceKey<DimensionType> OVERWORLD_32_256 = register("overworld_32_256");
    public static final ResourceKey<DimensionType> OVERWORLD_48_16 = register("overworld_48_16");
    public static final ResourceKey<DimensionType> OVERWORLD_48_32 = register("overworld_48_32");
    public static final ResourceKey<DimensionType> OVERWORLD_48_64 = register("overworld_48_64");
    public static final ResourceKey<DimensionType> OVERWORLD_48_128 = register("overworld_48_128");
    public static final ResourceKey<DimensionType> OVERWORLD_48_256 = register("overworld_48_256");
    public static final ResourceKey<DimensionType> OVERWORLD_64_16 = register("overworld_64_16");
    public static final ResourceKey<DimensionType> OVERWORLD_64_32 = register("overworld_64_32");
    public static final ResourceKey<DimensionType> OVERWORLD_64_64 = register("overworld_64_64");
    public static final ResourceKey<DimensionType> OVERWORLD_64_128 = register("overworld_64_128");
    public static final ResourceKey<DimensionType> OVERWORLD_64_256 = register("overworld_64_256");
    public static final ResourceKey<DimensionType> OVERWORLD_80_16 = register("overworld_80_16");
    public static final ResourceKey<DimensionType> OVERWORLD_80_32 = register("overworld_80_32");
    public static final ResourceKey<DimensionType> OVERWORLD_80_64 = register("overworld_80_64");
    public static final ResourceKey<DimensionType> OVERWORLD_80_128 = register("overworld_80_128");
    public static final ResourceKey<DimensionType> OVERWORLD_80_256 = register("overworld_80_256");
    public static final ResourceKey<DimensionType> OVERWORLD_96_16 = register("overworld_96_16");
    public static final ResourceKey<DimensionType> OVERWORLD_96_32 = register("overworld_96_32");
    public static final ResourceKey<DimensionType> OVERWORLD_96_64 = register("overworld_96_64");
    public static final ResourceKey<DimensionType> OVERWORLD_96_128 = register("overworld_96_128");
    public static final ResourceKey<DimensionType> OVERWORLD_96_256 = register("overworld_96_256");
    public static final ResourceKey<DimensionType> OVERWORLD_112_16 = register("overworld_112_16");
    public static final ResourceKey<DimensionType> OVERWORLD_112_32 = register("overworld_112_32");
    public static final ResourceKey<DimensionType> OVERWORLD_112_64 = register("overworld_112_64");
    public static final ResourceKey<DimensionType> OVERWORLD_112_128 = register("overworld_112_128");
    public static final ResourceKey<DimensionType> OVERWORLD_112_256 = register("overworld_112_256");
    public static final ResourceKey<DimensionType> OVERWORLD_128_16 = register("overworld_128_16");
    public static final ResourceKey<DimensionType> OVERWORLD_128_32 = register("overworld_128_32");
    public static final ResourceKey<DimensionType> OVERWORLD_128_64 = register("overworld_128_64");
    public static final ResourceKey<DimensionType> OVERWORLD_128_128 = register("overworld_128_128");
    public static final ResourceKey<DimensionType> OVERWORLD_128_256 = register("overworld_128_256");
    public static final ResourceKey<DimensionType> OVERWORLD_192_16 = register("overworld_192_16");
    public static final ResourceKey<DimensionType> OVERWORLD_192_32 = register("overworld_192_32");
    public static final ResourceKey<DimensionType> OVERWORLD_192_64 = register("overworld_192_64");
    public static final ResourceKey<DimensionType> OVERWORLD_192_128 = register("overworld_192_128");
    public static final ResourceKey<DimensionType> OVERWORLD_192_256 = register("overworld_192_256");

    private static ResourceKey<DimensionType> register(String p_223548_) {
        return ResourceKey.create(Registries.DIMENSION_TYPE, ResourceLocation.fromNamespaceAndPath(ExampleMod.MODID,p_223548_));
    }
    public static void bootstrap(BootstrapContext<DimensionType> p_330944_) {
        p_330944_.register(OVERWORLD_16_0,new DimensionType(
                OptionalLong.empty(),
                true,
                false,
                false,
                true,
                1.0,
                true,
                false,
                -64 - 16,
                384 + 16,
                384 + 16,
                BlockTags.INFINIBURN_OVERWORLD,
                BuiltinDimensionTypes.OVERWORLD_EFFECTS,
                0.0F,
                new DimensionType.MonsterSettings(false, true, UniformInt.of(0, 7), 0)
        ));
        p_330944_.register(OVERWORLD_32_0,new DimensionType(
                OptionalLong.empty(),
                true,
                false,
                false,
                true,
                1.0,
                true,
                false,
                -64 - 32,
                384 + 32,
                384 + 32,
                BlockTags.INFINIBURN_OVERWORLD,
                BuiltinDimensionTypes.OVERWORLD_EFFECTS,
                0.0F,
                new DimensionType.MonsterSettings(false, true, UniformInt.of(0, 7), 0)
        ));
        p_330944_.register(OVERWORLD_48_0,new DimensionType(
                OptionalLong.empty(),
                true,
                false,
                false,
                true,
                1.0,
                true,
                false,
                -64 - 48,
                384 + 48,
                384 + 48,
                BlockTags.INFINIBURN_OVERWORLD,
                BuiltinDimensionTypes.OVERWORLD_EFFECTS,
                0.0F,
                new DimensionType.MonsterSettings(false, true, UniformInt.of(0, 7), 0)
        ));
        p_330944_.register(OVERWORLD_64_0,new DimensionType(
                OptionalLong.empty(),
                true,
                false,
                false,
                true,
                1.0,
                true,
                false,
                -64 - 64,
                384 + 64,
                384 + 64,
                BlockTags.INFINIBURN_OVERWORLD,
                BuiltinDimensionTypes.OVERWORLD_EFFECTS,
                0.0F,
                new DimensionType.MonsterSettings(false, true, UniformInt.of(0, 7), 0)
        ));
        p_330944_.register(OVERWORLD_80_0,new DimensionType(
                OptionalLong.empty(),
                true,
                false,
                false,
                true,
                1.0,
                true,
                false,
                -64 - 80,
                384 + 80,
                384 + 80,
                BlockTags.INFINIBURN_OVERWORLD,
                BuiltinDimensionTypes.OVERWORLD_EFFECTS,
                0.0F,
                new DimensionType.MonsterSettings(false, true, UniformInt.of(0, 7), 0)
        ));
        p_330944_.register(OVERWORLD_96_0,new DimensionType(
                OptionalLong.empty(),
                true,
                false,
                false,
                true,
                1.0,
                true,
                false,
                -64 - 96,
                384 + 96,
                384 + 96,
                BlockTags.INFINIBURN_OVERWORLD,
                BuiltinDimensionTypes.OVERWORLD_EFFECTS,
                0.0F,
                new DimensionType.MonsterSettings(false, true, UniformInt.of(0, 7), 0)
        ));
        p_330944_.register(OVERWORLD_112_0,new DimensionType(
                OptionalLong.empty(),
                true,
                false,
                false,
                true,
                1.0,
                true,
                false,
                -64 - 112,
                384 + 112,
                384 + 112,
                BlockTags.INFINIBURN_OVERWORLD,
                BuiltinDimensionTypes.OVERWORLD_EFFECTS,
                0.0F,
                new DimensionType.MonsterSettings(false, true, UniformInt.of(0, 7), 0)
        ));
        p_330944_.register(OVERWORLD_128_0,new DimensionType(
                OptionalLong.empty(),
                true,
                false,
                false,
                true,
                1.0,
                true,
                false,
                -64 - 128,
                384 + 128,
                384 + 128,
                BlockTags.INFINIBURN_OVERWORLD,
                BuiltinDimensionTypes.OVERWORLD_EFFECTS,
                0.0F,
                new DimensionType.MonsterSettings(false, true, UniformInt.of(0, 7), 0)
        ));
        p_330944_.register(OVERWORLD_192_0,new DimensionType(
                OptionalLong.empty(),
                true,
                false,
                false,
                true,
                1.0,
                true,
                false,
                -64 - 192,
                384 + 192,
                384 + 192,
                BlockTags.INFINIBURN_OVERWORLD,
                BuiltinDimensionTypes.OVERWORLD_EFFECTS,
                0.0F,
                new DimensionType.MonsterSettings(false, true, UniformInt.of(0, 7), 0)
        ));
        p_330944_.register(OVERWORLD_0_16,new DimensionType(
                OptionalLong.empty(),
                true,
                false,
                false,
                true,
                1.0,
                true,
                false,
                -64,
                384 + 16,
                384 + 16,
                BlockTags.INFINIBURN_OVERWORLD,
                BuiltinDimensionTypes.OVERWORLD_EFFECTS,
                0.0F,
                new DimensionType.MonsterSettings(false, true, UniformInt.of(0, 7), 0)
        ));
        p_330944_.register(OVERWORLD_0_32,new DimensionType(
                OptionalLong.empty(),
                true,
                false,
                false,
                true,
                1.0,
                true,
                false,
                -64,
                384 + 32,
                384 + 32,
                BlockTags.INFINIBURN_OVERWORLD,
                BuiltinDimensionTypes.OVERWORLD_EFFECTS,
                0.0F,
                new DimensionType.MonsterSettings(false, true, UniformInt.of(0, 7), 0)
        ));
        p_330944_.register(OVERWORLD_0_64,new DimensionType(
                OptionalLong.empty(),
                true,
                false,
                false,
                true,
                1.0,
                true,
                false,
                -64,
                384 + 64,
                384 + 64,
                BlockTags.INFINIBURN_OVERWORLD,
                BuiltinDimensionTypes.OVERWORLD_EFFECTS,
                0.0F,
                new DimensionType.MonsterSettings(false, true, UniformInt.of(0, 7), 0)
        ));
        p_330944_.register(OVERWORLD_0_128,new DimensionType(
                OptionalLong.empty(),
                true,
                false,
                false,
                true,
                1.0,
                true,
                false,
                -64,
                384 + 128,
                384 + 128,
                BlockTags.INFINIBURN_OVERWORLD,
                BuiltinDimensionTypes.OVERWORLD_EFFECTS,
                0.0F,
                new DimensionType.MonsterSettings(false, true, UniformInt.of(0, 7), 0)
        ));
        p_330944_.register(OVERWORLD_0_256,new DimensionType(
                OptionalLong.empty(),
                true,
                false,
                false,
                true,
                1.0,
                true,
                false,
                -64,
                384 + 256,
                384 + 256,
                BlockTags.INFINIBURN_OVERWORLD,
                BuiltinDimensionTypes.OVERWORLD_EFFECTS,
                0.0F,
                new DimensionType.MonsterSettings(false, true, UniformInt.of(0, 7), 0)
        ));

        p_330944_.register(OVERWORLD_16_16,new DimensionType(
                OptionalLong.empty(),
                true,
                false,
                false,
                true,
                1.0,
                true,
                false,
                -64 - 16,
                384 + 16 + 16,
                384 + 16 + 16,
                BlockTags.INFINIBURN_OVERWORLD,
                BuiltinDimensionTypes.OVERWORLD_EFFECTS,
                0.0F,
                new DimensionType.MonsterSettings(false, true, UniformInt.of(0, 7), 0)
        ));
        p_330944_.register(OVERWORLD_16_32,new DimensionType(
                OptionalLong.empty(),
                true,
                false,
                false,
                true,
                1.0,
                true,
                false,
                -64 - 16,
                384 + 32 + 16,
                384 + 32 + 16,
                BlockTags.INFINIBURN_OVERWORLD,
                BuiltinDimensionTypes.OVERWORLD_EFFECTS,
                0.0F,
                new DimensionType.MonsterSettings(false, true, UniformInt.of(0, 7), 0)
        ));
        p_330944_.register(OVERWORLD_16_64,new DimensionType(
                OptionalLong.empty(),
                true,
                false,
                false,
                true,
                1.0,
                true,
                false,
                -64 - 16,
                384 + 64 + 16,
                384 + 64 + 16,
                BlockTags.INFINIBURN_OVERWORLD,
                BuiltinDimensionTypes.OVERWORLD_EFFECTS,
                0.0F,
                new DimensionType.MonsterSettings(false, true, UniformInt.of(0, 7), 0)
        ));
        p_330944_.register(OVERWORLD_16_128,new DimensionType(
                OptionalLong.empty(),
                true,
                false,
                false,
                true,
                1.0,
                true,
                false,
                -64 - 16,
                384 + 128 + 16,
                384 + 128 + 16,
                BlockTags.INFINIBURN_OVERWORLD,
                BuiltinDimensionTypes.OVERWORLD_EFFECTS,
                0.0F,
                new DimensionType.MonsterSettings(false, true, UniformInt.of(0, 7), 0)
        ));
        p_330944_.register(OVERWORLD_16_256,new DimensionType(
                OptionalLong.empty(),
                true,
                false,
                false,
                true,
                1.0,
                true,
                false,
                -64 - 16,
                384 + 256 + 16,
                384 + 256 + 16,
                BlockTags.INFINIBURN_OVERWORLD,
                BuiltinDimensionTypes.OVERWORLD_EFFECTS,
                0.0F,
                new DimensionType.MonsterSettings(false, true, UniformInt.of(0, 7), 0)
        ));
        p_330944_.register(OVERWORLD_32_16,new DimensionType(
                OptionalLong.empty(),
                true,
                false,
                false,
                true,
                1.0,
                true,
                false,
                -64 - 32,
                384 + 16 + 32,
                384 + 16 + 32,
                BlockTags.INFINIBURN_OVERWORLD,
                BuiltinDimensionTypes.OVERWORLD_EFFECTS,
                0.0F,
                new DimensionType.MonsterSettings(false, true, UniformInt.of(0, 7), 0)
        ));
        p_330944_.register(OVERWORLD_32_32,new DimensionType(
                OptionalLong.empty(),
                true,
                false,
                false,
                true,
                1.0,
                true,
                false,
                -64 - 32,
                384 + 32 + 32,
                384 + 32 + 32,
                BlockTags.INFINIBURN_OVERWORLD,
                BuiltinDimensionTypes.OVERWORLD_EFFECTS,
                0.0F,
                new DimensionType.MonsterSettings(false, true, UniformInt.of(0, 7), 0)
        ));
        p_330944_.register(OVERWORLD_32_64,new DimensionType(
                OptionalLong.empty(),
                true,
                false,
                false,
                true,
                1.0,
                true,
                false,
                -64 - 32,
                384 + 64 + 32,
                384 + 64 + 32,
                BlockTags.INFINIBURN_OVERWORLD,
                BuiltinDimensionTypes.OVERWORLD_EFFECTS,
                0.0F,
                new DimensionType.MonsterSettings(false, true, UniformInt.of(0, 7), 0)
        ));
        p_330944_.register(OVERWORLD_32_128,new DimensionType(
                OptionalLong.empty(),
                true,
                false,
                false,
                true,
                1.0,
                true,
                false,
                -64 - 32,
                384 + 128 + 32,
                384 + 128 + 32,
                BlockTags.INFINIBURN_OVERWORLD,
                BuiltinDimensionTypes.OVERWORLD_EFFECTS,
                0.0F,
                new DimensionType.MonsterSettings(false, true, UniformInt.of(0, 7), 0)
        ));
        p_330944_.register(OVERWORLD_32_256,new DimensionType(
                OptionalLong.empty(),
                true,
                false,
                false,
                true,
                1.0,
                true,
                false,
                -64 - 32,
                384 + 256 + 32,
                384 + 256 + 32,
                BlockTags.INFINIBURN_OVERWORLD,
                BuiltinDimensionTypes.OVERWORLD_EFFECTS,
                0.0F,
                new DimensionType.MonsterSettings(false, true, UniformInt.of(0, 7), 0)
        ));
        p_330944_.register(OVERWORLD_64_16,new DimensionType(
                OptionalLong.empty(),
                true,
                false,
                false,
                true,
                1.0,
                true,
                false,
                -64 - 64,
                384 + 16 + 64,
                384 + 16 + 64,
                BlockTags.INFINIBURN_OVERWORLD,
                BuiltinDimensionTypes.OVERWORLD_EFFECTS,
                0.0F,
                new DimensionType.MonsterSettings(false, true, UniformInt.of(0, 7), 0)
        ));
        p_330944_.register(OVERWORLD_64_32,new DimensionType(
                OptionalLong.empty(),
                true,
                false,
                false,
                true,
                1.0,
                true,
                false,
                -64 - 64,
                384 + 32 + 64,
                384 + 32 + 64,
                BlockTags.INFINIBURN_OVERWORLD,
                BuiltinDimensionTypes.OVERWORLD_EFFECTS,
                0.0F,
                new DimensionType.MonsterSettings(false, true, UniformInt.of(0, 7), 0)
        ));
        p_330944_.register(OVERWORLD_64_64,new DimensionType(
                OptionalLong.empty(),
                true,
                false,
                false,
                true,
                1.0,
                true,
                false,
                -64 - 64,
                384 + 64 + 64,
                384 + 64 + 64,
                BlockTags.INFINIBURN_OVERWORLD,
                BuiltinDimensionTypes.OVERWORLD_EFFECTS,
                0.0F,
                new DimensionType.MonsterSettings(false, true, UniformInt.of(0, 7), 0)
        ));
        p_330944_.register(OVERWORLD_64_128,new DimensionType(
                OptionalLong.empty(),
                true,
                false,
                false,
                true,
                1.0,
                true,
                false,
                -64 - 64,
                384 + 128 + 64,
                384 + 128 + 64,
                BlockTags.INFINIBURN_OVERWORLD,
                BuiltinDimensionTypes.OVERWORLD_EFFECTS,
                0.0F,
                new DimensionType.MonsterSettings(false, true, UniformInt.of(0, 7), 0)
        ));
        p_330944_.register(OVERWORLD_64_256,new DimensionType(
                OptionalLong.empty(),
                true,
                false,
                false,
                true,
                1.0,
                true,
                false,
                -64 - 64,
                384 + 256 + 64,
                384 + 256 + 64,
                BlockTags.INFINIBURN_OVERWORLD,
                BuiltinDimensionTypes.OVERWORLD_EFFECTS,
                0.0F,
                new DimensionType.MonsterSettings(false, true, UniformInt.of(0, 7), 0)
        ));

        p_330944_.register(OVERWORLD_48_16,new DimensionType(
                OptionalLong.empty(),
                true,
                false,
                false,
                true,
                1.0,
                true,
                false,
                -64 - 48,
                384 + 16 + 48,
                384 + 16 + 48,
                BlockTags.INFINIBURN_OVERWORLD,
                BuiltinDimensionTypes.OVERWORLD_EFFECTS,
                0.0F,
                new DimensionType.MonsterSettings(false, true, UniformInt.of(0, 7), 0)
        ));
        p_330944_.register(OVERWORLD_48_32,new DimensionType(
                OptionalLong.empty(),
                true,
                false,
                false,
                true,
                1.0,
                true,
                false,
                -64 - 48,
                384 + 32 + 48,
                384 + 32 + 48,
                BlockTags.INFINIBURN_OVERWORLD,
                BuiltinDimensionTypes.OVERWORLD_EFFECTS,
                0.0F,
                new DimensionType.MonsterSettings(false, true, UniformInt.of(0, 7), 0)
        ));
        p_330944_.register(OVERWORLD_48_64,new DimensionType(
                OptionalLong.empty(),
                true,
                false,
                false,
                true,
                1.0,
                true,
                false,
                -64 - 48,
                384 + 64 + 48,
                384 + 64 + 48,
                BlockTags.INFINIBURN_OVERWORLD,
                BuiltinDimensionTypes.OVERWORLD_EFFECTS,
                0.0F,
                new DimensionType.MonsterSettings(false, true, UniformInt.of(0, 7), 0)
        ));
        p_330944_.register(OVERWORLD_48_128,new DimensionType(
                OptionalLong.empty(),
                true,
                false,
                false,
                true,
                1.0,
                true,
                false,
                -64 - 48,
                384 + 128 + 48,
                384 + 128 + 48,
                BlockTags.INFINIBURN_OVERWORLD,
                BuiltinDimensionTypes.OVERWORLD_EFFECTS,
                0.0F,
                new DimensionType.MonsterSettings(false, true, UniformInt.of(0, 7), 0)
        ));
        p_330944_.register(OVERWORLD_48_256,new DimensionType(
                OptionalLong.empty(),
                true,
                false,
                false,
                true,
                1.0,
                true,
                false,
                -64 - 48,
                384 + 256 + 48,
                384 + 256 + 48,
                BlockTags.INFINIBURN_OVERWORLD,
                BuiltinDimensionTypes.OVERWORLD_EFFECTS,
                0.0F,
                new DimensionType.MonsterSettings(false, true, UniformInt.of(0, 7), 0)
        ));

        p_330944_.register(OVERWORLD_80_16,new DimensionType(
                OptionalLong.empty(),
                true,
                false,
                false,
                true,
                1.0,
                true,
                false,
                -64 - 80,
                384 + 16 + 80,
                384 + 16 + 80,
                BlockTags.INFINIBURN_OVERWORLD,
                BuiltinDimensionTypes.OVERWORLD_EFFECTS,
                0.0F,
                new DimensionType.MonsterSettings(false, true, UniformInt.of(0, 7), 0)
        ));
        p_330944_.register(OVERWORLD_80_32,new DimensionType(
                OptionalLong.empty(),
                true,
                false,
                false,
                true,
                1.0,
                true,
                false,
                -64 - 80,
                384 + 32 + 80,
                384 + 32 + 80,
                BlockTags.INFINIBURN_OVERWORLD,
                BuiltinDimensionTypes.OVERWORLD_EFFECTS,
                0.0F,
                new DimensionType.MonsterSettings(false, true, UniformInt.of(0, 7), 0)
        ));
        p_330944_.register(OVERWORLD_80_64,new DimensionType(
                OptionalLong.empty(),
                true,
                false,
                false,
                true,
                1.0,
                true,
                false,
                -64 - 80,
                384 + 64 + 80,
                384 + 64 + 80,
                BlockTags.INFINIBURN_OVERWORLD,
                BuiltinDimensionTypes.OVERWORLD_EFFECTS,
                0.0F,
                new DimensionType.MonsterSettings(false, true, UniformInt.of(0, 7), 0)
        ));
        p_330944_.register(OVERWORLD_80_128,new DimensionType(
                OptionalLong.empty(),
                true,
                false,
                false,
                true,
                1.0,
                true,
                false,
                -64 - 80,
                384 + 128 + 80,
                384 + 128 + 80,
                BlockTags.INFINIBURN_OVERWORLD,
                BuiltinDimensionTypes.OVERWORLD_EFFECTS,
                0.0F,
                new DimensionType.MonsterSettings(false, true, UniformInt.of(0, 7), 0)
        ));
        p_330944_.register(OVERWORLD_80_256,new DimensionType(
                OptionalLong.empty(),
                true,
                false,
                false,
                true,
                1.0,
                true,
                false,
                -64 - 80,
                384 + 256 + 80,
                384 + 256 + 80,
                BlockTags.INFINIBURN_OVERWORLD,
                BuiltinDimensionTypes.OVERWORLD_EFFECTS,
                0.0F,
                new DimensionType.MonsterSettings(false, true, UniformInt.of(0, 7), 0)
        ));

        p_330944_.register(OVERWORLD_96_16,new DimensionType(
                OptionalLong.empty(),
                true,
                false,
                false,
                true,
                1.0,
                true,
                false,
                -64 - 96,
                384 + 16 + 96,
                384 + 16 + 96,
                BlockTags.INFINIBURN_OVERWORLD,
                BuiltinDimensionTypes.OVERWORLD_EFFECTS,
                0.0F,
                new DimensionType.MonsterSettings(false, true, UniformInt.of(0, 7), 0)
        ));
        p_330944_.register(OVERWORLD_96_32,new DimensionType(
                OptionalLong.empty(),
                true,
                false,
                false,
                true,
                1.0,
                true,
                false,
                -64 - 96,
                384 + 32 + 96,
                384 + 32 + 96,
                BlockTags.INFINIBURN_OVERWORLD,
                BuiltinDimensionTypes.OVERWORLD_EFFECTS,
                0.0F,
                new DimensionType.MonsterSettings(false, true, UniformInt.of(0, 7), 0)
        ));
        p_330944_.register(OVERWORLD_96_64,new DimensionType(
                OptionalLong.empty(),
                true,
                false,
                false,
                true,
                1.0,
                true,
                false,
                -64 - 96,
                384 + 64 + 96,
                384 + 64 + 96,
                BlockTags.INFINIBURN_OVERWORLD,
                BuiltinDimensionTypes.OVERWORLD_EFFECTS,
                0.0F,
                new DimensionType.MonsterSettings(false, true, UniformInt.of(0, 7), 0)
        ));
        p_330944_.register(OVERWORLD_96_128,new DimensionType(
                OptionalLong.empty(),
                true,
                false,
                false,
                true,
                1.0,
                true,
                false,
                -64 - 96,
                384 + 128 + 96,
                384 + 128 + 96,
                BlockTags.INFINIBURN_OVERWORLD,
                BuiltinDimensionTypes.OVERWORLD_EFFECTS,
                0.0F,
                new DimensionType.MonsterSettings(false, true, UniformInt.of(0, 7), 0)
        ));
        p_330944_.register(OVERWORLD_96_256,new DimensionType(
                OptionalLong.empty(),
                true,
                false,
                false,
                true,
                1.0,
                true,
                false,
                -64 - 96,
                384 + 256 + 96,
                384 + 256 + 96,
                BlockTags.INFINIBURN_OVERWORLD,
                BuiltinDimensionTypes.OVERWORLD_EFFECTS,
                0.0F,
                new DimensionType.MonsterSettings(false, true, UniformInt.of(0, 7), 0)
        ));

        p_330944_.register(OVERWORLD_112_16,new DimensionType(
                OptionalLong.empty(),
                true,
                false,
                false,
                true,
                1.0,
                true,
                false,
                -64 - 112,
                384 + 16 + 112,
                384 + 16 + 112,
                BlockTags.INFINIBURN_OVERWORLD,
                BuiltinDimensionTypes.OVERWORLD_EFFECTS,
                0.0F,
                new DimensionType.MonsterSettings(false, true, UniformInt.of(0, 7), 0)
        ));
        p_330944_.register(OVERWORLD_112_32,new DimensionType(
                OptionalLong.empty(),
                true,
                false,
                false,
                true,
                1.0,
                true,
                false,
                -64 - 112,
                384 + 32 + 112,
                384 + 32 + 112,
                BlockTags.INFINIBURN_OVERWORLD,
                BuiltinDimensionTypes.OVERWORLD_EFFECTS,
                0.0F,
                new DimensionType.MonsterSettings(false, true, UniformInt.of(0, 7), 0)
        ));
        p_330944_.register(OVERWORLD_112_64,new DimensionType(
                OptionalLong.empty(),
                true,
                false,
                false,
                true,
                1.0,
                true,
                false,
                -64 - 112,
                384 + 64 + 112,
                384 + 64 + 112,
                BlockTags.INFINIBURN_OVERWORLD,
                BuiltinDimensionTypes.OVERWORLD_EFFECTS,
                0.0F,
                new DimensionType.MonsterSettings(false, true, UniformInt.of(0, 7), 0)
        ));
        p_330944_.register(OVERWORLD_112_128,new DimensionType(
                OptionalLong.empty(),
                true,
                false,
                false,
                true,
                1.0,
                true,
                false,
                -64 - 112,
                384 + 128 + 112,
                384 + 128 + 112,
                BlockTags.INFINIBURN_OVERWORLD,
                BuiltinDimensionTypes.OVERWORLD_EFFECTS,
                0.0F,
                new DimensionType.MonsterSettings(false, true, UniformInt.of(0, 7), 0)
        ));
        p_330944_.register(OVERWORLD_112_256,new DimensionType(
                OptionalLong.empty(),
                true,
                false,
                false,
                true,
                1.0,
                true,
                false,
                -64 - 112,
                384 + 256 + 112,
                384 + 256 + 112,
                BlockTags.INFINIBURN_OVERWORLD,
                BuiltinDimensionTypes.OVERWORLD_EFFECTS,
                0.0F,
                new DimensionType.MonsterSettings(false, true, UniformInt.of(0, 7), 0)
        ));

        p_330944_.register(OVERWORLD_128_16,new DimensionType(
                OptionalLong.empty(),
                true,
                false,
                false,
                true,
                1.0,
                true,
                false,
                -64 - 128,
                384 + 16 + 128,
                384 + 16 + 128,
                BlockTags.INFINIBURN_OVERWORLD,
                BuiltinDimensionTypes.OVERWORLD_EFFECTS,
                0.0F,
                new DimensionType.MonsterSettings(false, true, UniformInt.of(0, 7), 0)
        ));
        p_330944_.register(OVERWORLD_128_32,new DimensionType(
                OptionalLong.empty(),
                true,
                false,
                false,
                true,
                1.0,
                true,
                false,
                -64 - 128,
                384 + 32 + 128,
                384 + 32 + 128,
                BlockTags.INFINIBURN_OVERWORLD,
                BuiltinDimensionTypes.OVERWORLD_EFFECTS,
                0.0F,
                new DimensionType.MonsterSettings(false, true, UniformInt.of(0, 7), 0)
        ));
        p_330944_.register(OVERWORLD_128_64,new DimensionType(
                OptionalLong.empty(),
                true,
                false,
                false,
                true,
                1.0,
                true,
                false,
                -64 - 128,
                384 + 64 + 128,
                384 + 64 + 128,
                BlockTags.INFINIBURN_OVERWORLD,
                BuiltinDimensionTypes.OVERWORLD_EFFECTS,
                0.0F,
                new DimensionType.MonsterSettings(false, true, UniformInt.of(0, 7), 0)
        ));
        p_330944_.register(OVERWORLD_128_128,new DimensionType(
                OptionalLong.empty(),
                true,
                false,
                false,
                true,
                1.0,
                true,
                false,
                -64 - 128,
                384 + 128 + 128,
                384 + 128 + 128,
                BlockTags.INFINIBURN_OVERWORLD,
                BuiltinDimensionTypes.OVERWORLD_EFFECTS,
                0.0F,
                new DimensionType.MonsterSettings(false, true, UniformInt.of(0, 7), 0)
        ));
        p_330944_.register(OVERWORLD_128_256,new DimensionType(
                OptionalLong.empty(),
                true,
                false,
                false,
                true,
                1.0,
                true,
                false,
                -64 - 128,
                384 + 256 + 128,
                384 + 256 + 128,
                BlockTags.INFINIBURN_OVERWORLD,
                BuiltinDimensionTypes.OVERWORLD_EFFECTS,
                0.0F,
                new DimensionType.MonsterSettings(false, true, UniformInt.of(0, 7), 0)
        ));

        p_330944_.register(OVERWORLD_192_16,new DimensionType(
                OptionalLong.empty(),
                true,
                false,
                false,
                true,
                1.0,
                true,
                false,
                -64 - 192,
                384 + 16 + 192,
                384 + 16 + 192,
                BlockTags.INFINIBURN_OVERWORLD,
                BuiltinDimensionTypes.OVERWORLD_EFFECTS,
                0.0F,
                new DimensionType.MonsterSettings(false, true, UniformInt.of(0, 7), 0)
        ));
        p_330944_.register(OVERWORLD_192_32,new DimensionType(
                OptionalLong.empty(),
                true,
                false,
                false,
                true,
                1.0,
                true,
                false,
                -64 - 192,
                384 + 32 + 192,
                384 + 32 + 192,
                BlockTags.INFINIBURN_OVERWORLD,
                BuiltinDimensionTypes.OVERWORLD_EFFECTS,
                0.0F,
                new DimensionType.MonsterSettings(false, true, UniformInt.of(0, 7), 0)
        ));
        p_330944_.register(OVERWORLD_192_64,new DimensionType(
                OptionalLong.empty(),
                true,
                false,
                false,
                true,
                1.0,
                true,
                false,
                -64 - 192,
                384 + 64 + 192,
                384 + 64 + 192,
                BlockTags.INFINIBURN_OVERWORLD,
                BuiltinDimensionTypes.OVERWORLD_EFFECTS,
                0.0F,
                new DimensionType.MonsterSettings(false, true, UniformInt.of(0, 7), 0)
        ));
        p_330944_.register(OVERWORLD_192_128,new DimensionType(
                OptionalLong.empty(),
                true,
                false,
                false,
                true,
                1.0,
                true,
                false,
                -64 - 192,
                384 + 128 + 192,
                384 + 128 + 192,
                BlockTags.INFINIBURN_OVERWORLD,
                BuiltinDimensionTypes.OVERWORLD_EFFECTS,
                0.0F,
                new DimensionType.MonsterSettings(false, true, UniformInt.of(0, 7), 0)
        ));
        p_330944_.register(OVERWORLD_192_256,new DimensionType(
                OptionalLong.empty(),
                true,
                false,
                false,
                true,
                1.0,
                true,
                false,
                -64 - 192,
                384 + 256 + 192,
                384 + 256 + 192,
                BlockTags.INFINIBURN_OVERWORLD,
                BuiltinDimensionTypes.OVERWORLD_EFFECTS,
                0.0F,
                new DimensionType.MonsterSettings(false, true, UniformInt.of(0, 7), 0)
        ));
    }
}

