package com.example.setworld.tags;

import com.example.setworld.ExampleMod;
import com.example.setworld.register.RegisterWorldPresets;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.TagsProvider;
import net.minecraft.data.tags.WorldPresetTagsProvider;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.WorldPresetTags;
import net.minecraft.world.level.levelgen.presets.WorldPreset;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class WrldPresentTagsProvider extends WorldPresetTagsProvider {

    public WrldPresentTagsProvider(PackOutput p_255701_, CompletableFuture<HolderLookup.Provider> p_255974_, String modId, @Nullable ExistingFileHelper existingFileHelper) {
        super(p_255701_, p_255974_, modId, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider p_256380_) {
        this.tag(WorldPresentsTags.IMPACT);
    }
}
