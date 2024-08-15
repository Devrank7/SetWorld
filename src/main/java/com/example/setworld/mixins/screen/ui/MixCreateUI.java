package com.example.setworld.mixins.screen.ui;

import com.example.setworld.tags.WorldPresentsTags;
import net.minecraft.client.gui.screens.worldselection.CreateWorldScreen;
import net.minecraft.client.gui.screens.worldselection.PresetEditor;
import net.minecraft.client.gui.screens.worldselection.WorldCreationContext;
import net.minecraft.client.gui.screens.worldselection.WorldCreationUiState;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.TagKey;
import net.minecraft.tags.WorldPresetTags;
import net.minecraft.world.level.levelgen.presets.WorldPreset;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Optional;

@Mixin(WorldCreationUiState.class)
public abstract class MixCreateUI {

    private static final Logger log = LoggerFactory.getLogger(MixCreateUI.class);

    @Shadow
    private WorldCreationUiState.WorldTypeEntry worldType;

    @Shadow
    public abstract void updateDimensions(WorldCreationContext.DimensionsUpdater p_268314_);

    @Shadow
    public abstract WorldCreationContext getSettings();

    @Shadow
    @Final
    private List<WorldCreationUiState.WorldTypeEntry> normalPresetList;

    @Shadow
    @Final
    private List<WorldCreationUiState.WorldTypeEntry> altPresetList;


    @Shadow public abstract WorldCreationUiState.WorldTypeEntry getWorldType();

    /**
     * @author
     * @reason
     */
    @Overwrite
    private static Optional<Holder<WorldPreset>> findPreset(WorldCreationContext p_268025_, Optional<ResourceKey<WorldPreset>> p_268184_) {
        return p_268184_.flatMap(p_267974_ -> {
            log.info("Local = {}", p_267974_.location());
            p_268025_.worldgenLoadContext().registryOrThrow(Registries.WORLD_PRESET).holders().forEach(worldPresetReference -> {
                log.info("Holder = {}", worldPresetReference.toString());
            });
            return p_268025_.worldgenLoadContext().registryOrThrow(Registries.WORLD_PRESET).getHolder((ResourceKey<WorldPreset>) p_267974_);
        });
    }

    /**
     * @author
     * @reason
     */
    @Overwrite
    private void updatePresetLists() {
        Registry<WorldPreset> registry = this.getSettings().worldgenLoadContext().registryOrThrow(Registries.WORLD_PRESET);
        this.normalPresetList.clear();
        this.normalPresetList
                .addAll(getNonEmptyList(registry, WorldPresentsTags.IMPACT).orElseGet(() -> registry.holders().map(WorldCreationUiState.WorldTypeEntry::new).toList()));
        this.altPresetList.clear();
        this.altPresetList.addAll(getNonEmptyList(registry, WorldPresentsTags.IMPACT).orElse(this.normalPresetList));
        Holder<WorldPreset> holder = this.worldType.preset();
        if (holder != null) {
            this.worldType = findPreset(this.getSettings(), holder.unwrapKey()).map(WorldCreationUiState.WorldTypeEntry::new).orElse(this.normalPresetList.get(0));
        }
    }

    /**
     * @author
     * @reason
     */
    @Overwrite
    private static Optional<List<WorldCreationUiState.WorldTypeEntry>> getNonEmptyList(Registry<WorldPreset> p_268296_, TagKey<WorldPreset> p_268097_) {
        log.info("HOLL = {}", p_268296_.holders().toList());
        return p_268296_.getTag(p_268097_)
                .map(p_268149_ -> p_268149_.stream().map(WorldCreationUiState.WorldTypeEntry::new).toList())
                .filter(p_268066_ -> !p_268066_.isEmpty());
    }

    /**
     * @author
     * @reason
     */
    @Overwrite
    public void setWorldType(WorldCreationUiState.WorldTypeEntry p_268117_) {
        log.info("{}",(p_268117_.describePreset().getString()));
        this.worldType = p_268117_;
        Holder<WorldPreset> holder = p_268117_.preset();
        if (holder != null) {
            log.info("{}", holder.value().createWorldDimensions().levels());
            this.updateDimensions((p_268134_, p_268035_) -> holder.value().createWorldDimensions());
        }
    }
    /**
     * @author
     * @reason
     */
    @Nullable
    @Overwrite
    public PresetEditor getPresetEditor() {
        Holder<WorldPreset> holder = this.getWorldType().preset();
        log.warn("holder type = {}", holder);
        return holder != null ? holder.unwrapKey().map(net.minecraftforge.client.PresetEditorManager::get).orElse(null) : null; // FORGE: redirect lookup to expanded map
    }

}
