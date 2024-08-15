package com.example.setworld.tags;

import com.example.setworld.ExampleMod;
import net.minecraft.client.gui.screens.worldselection.CreateWorldScreen;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.levelgen.presets.WorldPreset;

public class WorldPresentsTags {

    public static final TagKey<WorldPreset> IMPACT = create("impact");
    private static TagKey<WorldPreset> create(String p_216058_) {
        return TagKey.create(Registries.WORLD_PRESET, ResourceLocation.fromNamespaceAndPath(ExampleMod.MODID,p_216058_));
    }
}
