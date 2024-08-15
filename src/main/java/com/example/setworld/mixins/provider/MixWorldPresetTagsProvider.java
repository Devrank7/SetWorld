package com.example.setworld.mixins.provider;

import com.example.setworld.register.RegisterWorldPresets;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.Registry;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.TagsProvider;
import net.minecraft.data.tags.WorldPresetTagsProvider;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.WorldPresetTags;
import net.minecraft.world.level.levelgen.presets.WorldPreset;
import net.minecraft.world.level.levelgen.presets.WorldPresets;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

import java.util.concurrent.CompletableFuture;

@Mixin(WorldPresetTagsProvider.class)
public class MixWorldPresetTagsProvider extends TagsProvider<WorldPreset> {

    public MixWorldPresetTagsProvider(PackOutput p_256596_, ResourceKey<? extends Registry<WorldPreset>> p_255886_, CompletableFuture<HolderLookup.Provider> p_256513_) {
        super(p_256596_, p_255886_, p_256513_);
    }

    /**
     * @author
     * @reason
     */
    @Override
    @Overwrite
    protected void addTags(HolderLookup.Provider p_255734_) {
        System.err.println("TAGS DATA");
        this.tag(WorldPresetTags.NORMAL)
                .add(WorldPresets.NORMAL)
                .add(WorldPresets.FLAT)
                .add(WorldPresets.LARGE_BIOMES)
                .add(RegisterWorldPresets.SUPER_LARGE_BIOMES)
                .add(WorldPresets.AMPLIFIED)
                .add(WorldPresets.SINGLE_BIOME_SURFACE);
        this.tag(WorldPresetTags.EXTENDED).addTag(WorldPresetTags.NORMAL).add(WorldPresets.DEBUG);
    }
}
