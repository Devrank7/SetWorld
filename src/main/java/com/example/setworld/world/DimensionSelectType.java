package com.example.setworld.world;

import com.example.setworld.register.RegDimensionType;
import com.mojang.serialization.Codec;
import net.minecraft.data.worldgen.DimensionTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.OptionEnum;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.level.dimension.BuiltinDimensionTypes;
import net.minecraft.world.level.dimension.DimensionType;

public enum DimensionSelectType implements OptionEnum, StringRepresentable {


    DEFAULT_OVERWORLD(BuiltinDimensionTypes.OVERWORLD, 0, "overworld1", "default_overworld"),
    OVERWORLD_16_0(RegDimensionType.OVERWORLD_16_0, 1, "overworld2", "overworld_16_0"),
    OVERWORLD_32_0(RegDimensionType.OVERWORLD_32_0, 2, "overworld3", "overworld_32_0"),
    OVERWORLD_48_0(RegDimensionType.OVERWORLD_48_0, 3, "overworld4", "overworld_48_0"),
    OVERWORLD_64_0(RegDimensionType.OVERWORLD_64_0, 4, "overworld5", "overworld_64_0"),
    OVERWORLD_80_0(RegDimensionType.OVERWORLD_80_0, 5, "overworld6", "overworld_80_0"),
    OVERWORLD_96_0(RegDimensionType.OVERWORLD_96_0, 6, "overworld7", "overworld_96_0"),
    OVERWORLD_112_0(RegDimensionType.OVERWORLD_112_0, 7, "overworld8", "overworld_112_0"),
    OVERWORLD_128_0(RegDimensionType.OVERWORLD_128_0, 8, "overworld9", "overworld_128_0"),
    OVERWORLD_192_0(RegDimensionType.OVERWORLD_192_0, 9, "overworld10", "overworld_192_0"),
    OVERWORLD_0_16(RegDimensionType.OVERWORLD_0_16, 10, "overworld11", "overworld_0_16"),
    OVERWORLD_0_32(RegDimensionType.OVERWORLD_0_32, 11, "overworld12", "overworld_0_32"),
    OVERWORLD_0_64(RegDimensionType.OVERWORLD_0_64, 12, "overworld13", "overworld_0_64"),
    OVERWORLD_0_128(RegDimensionType.OVERWORLD_0_128, 13, "overworld14", "overworld_0_128"),
    OVERWORLD_0_256(RegDimensionType.OVERWORLD_0_256, 14, "overworld15", "overworld_0_256"),
    OVERWORLD_16_16(RegDimensionType.OVERWORLD_16_16, 15, "overworld16", "overworld_16_16"),
    OVERWORLD_16_32(RegDimensionType.OVERWORLD_16_32, 16, "overworld17", "overworld_16_32"),
    OVERWORLD_16_64(RegDimensionType.OVERWORLD_16_64, 17, "overworld18", "overworld_16_64"),
    OVERWORLD_16_128(RegDimensionType.OVERWORLD_16_128, 18, "overworld19", "overworld_16_128"),
    OVERWORLD_16_256(RegDimensionType.OVERWORLD_16_256, 19, "overworld20", "overworld_16_256"),
    OVERWORLD_32_16(RegDimensionType.OVERWORLD_32_16, 20, "overworld21", "overworld_32_16"),
    OVERWORLD_32_32(RegDimensionType.OVERWORLD_32_32, 21, "overworld22", "overworld_32_32"),
    OVERWORLD_32_64(RegDimensionType.OVERWORLD_32_64, 22, "overworld23", "overworld_32_64"),
    OVERWORLD_32_128(RegDimensionType.OVERWORLD_32_128, 23, "overworld24", "overworld_32_128"),
    OVERWORLD_32_256(RegDimensionType.OVERWORLD_32_256, 24, "overworld25", "overworld_32_256"),
    OVERWORLD_48_16(RegDimensionType.OVERWORLD_48_16, 25, "overworld26", "overworld_48_16"),
    OVERWORLD_48_32(RegDimensionType.OVERWORLD_48_32, 26, "overworld27", "overworld_48_32"),
    OVERWORLD_48_64(RegDimensionType.OVERWORLD_48_64, 27, "overworld28", "overworld_48_64"),
    OVERWORLD_48_128(RegDimensionType.OVERWORLD_48_128, 28, "overworld29", "overworld_48_128"),
    OVERWORLD_48_256(RegDimensionType.OVERWORLD_48_256, 29, "overworld30", "overworld_48_256"),
    OVERWORLD_64_16(RegDimensionType.OVERWORLD_64_16, 30, "overworld31", "overworld_64_16"),
    OVERWORLD_64_32(RegDimensionType.OVERWORLD_64_32, 31, "overworld32", "overworld_64_32"),
    OVERWORLD_64_64(RegDimensionType.OVERWORLD_64_64, 32, "overworld33", "overworld_64_64"),
    OVERWORLD_64_128(RegDimensionType.OVERWORLD_64_128, 33, "overworld34", "overworld_64_128"),
    OVERWORLD_64_256(RegDimensionType.OVERWORLD_64_256, 34, "overworld35", "overworld_64_256"),
    OVERWORLD_80_16(RegDimensionType.OVERWORLD_80_16, 35, "overworld36", "overworld_80_16"),
    OVERWORLD_80_32(RegDimensionType.OVERWORLD_80_32, 36, "overworld37", "overworld_80_32"),
    OVERWORLD_80_64(RegDimensionType.OVERWORLD_80_64, 37, "overworld38", "overworld_80_64"),
    OVERWORLD_80_128(RegDimensionType.OVERWORLD_80_128, 38, "overworld39", "overworld_80_128"),
    OVERWORLD_80_256(RegDimensionType.OVERWORLD_80_256, 39, "overworld40", "overworld_80_256"),
    OVERWORLD_96_16(RegDimensionType.OVERWORLD_96_16, 40, "overworld41", "overworld_96_16"),
    OVERWORLD_96_32(RegDimensionType.OVERWORLD_96_32, 41, "overworld42", "overworld_96_32"),
    OVERWORLD_96_64(RegDimensionType.OVERWORLD_96_64, 42, "overworld43", "overworld_96_64"),
    OVERWORLD_96_128(RegDimensionType.OVERWORLD_96_128, 43, "overworld44", "overworld_96_128"),
    OVERWORLD_96_256(RegDimensionType.OVERWORLD_96_256, 44, "overworld45", "overworld_96_256"),
    OVERWORLD_112_16(RegDimensionType.OVERWORLD_112_16, 45, "overworld46", "overworld_112_16"),
    OVERWORLD_112_32(RegDimensionType.OVERWORLD_112_32, 46, "overworld47", "overworld_112_32"),
    OVERWORLD_112_64(RegDimensionType.OVERWORLD_112_64, 47, "overworld48", "overworld_112_64"),
    OVERWORLD_112_128(RegDimensionType.OVERWORLD_112_128, 48, "overworld49", "overworld_112_128"),
    OVERWORLD_112_256(RegDimensionType.OVERWORLD_112_256, 49, "overworld50", "overworld_112_256"),
    OVERWORLD_128_16(RegDimensionType.OVERWORLD_128_16, 50, "overworld51", "overworld_128_16"),
    OVERWORLD_128_32(RegDimensionType.OVERWORLD_128_32, 51, "overworld52", "overworld_128_32"),
    OVERWORLD_128_64(RegDimensionType.OVERWORLD_128_64, 52, "overworld53", "overworld_128_64"),
    OVERWORLD_128_128(RegDimensionType.OVERWORLD_128_128, 53, "overworld54", "overworld_128_128"),
    OVERWORLD_128_256(RegDimensionType.OVERWORLD_128_256, 54, "overworld55", "overworld_128_256"),
    OVERWORLD_192_16(RegDimensionType.OVERWORLD_192_16, 55, "overworld56", "overworld_192_16"),
    OVERWORLD_192_32(RegDimensionType.OVERWORLD_192_32, 56, "overworld57", "overworld_192_32"),
    OVERWORLD_192_64(RegDimensionType.OVERWORLD_192_64, 57, "overworld58", "overworld_192_64"),
    OVERWORLD_192_128(RegDimensionType.OVERWORLD_192_128, 58, "overworld59", "overworld_192_128"),
    OVERWORLD_192_256(RegDimensionType.OVERWORLD_192_256, 59, "overworld60", "overworld_192_256");

    public static final Codec<DimensionSelectType> CODEC = StringRepresentable.fromEnum(DimensionSelectType::values);

    private final ResourceKey<DimensionType> key_resource;
    private final int id;
    private final String key;
    private final String name;

    DimensionSelectType(ResourceKey<DimensionType> key_resource, int id, String name,String key) {
        this.key_resource = key_resource;
        this.id = id;
        this.name = name;
        this.key = key;
    }

    public ResourceKey<DimensionType> getKey_resource() {
        return key_resource;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public String getKey() {
        return key;
    }

    @Override
    public String getSerializedName() {
        return name;
    }

    @Override
    public Component getCaption() {
        return Component.translatable(this.key);
    }
}
