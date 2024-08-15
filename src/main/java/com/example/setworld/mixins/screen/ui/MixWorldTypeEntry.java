package com.example.setworld.mixins.screen.ui;

import com.example.setworld.inter.IEntryWorldType;
import com.example.setworld.register.RegisterWorldPresets;
import net.minecraft.client.gui.screens.worldselection.WorldCreationUiState;
import net.minecraft.core.Holder;
import net.minecraft.network.chat.Component;
import net.minecraft.world.level.levelgen.presets.WorldPreset;
import net.minecraft.world.level.levelgen.presets.WorldPresets;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import javax.annotation.Nullable;
import java.util.Optional;

@Mixin(WorldCreationUiState.WorldTypeEntry.class)
public class MixWorldTypeEntry implements IEntryWorldType {
    private static final Logger log = LoggerFactory.getLogger(MixWorldTypeEntry.class);
    @Shadow @Final @Nullable private Holder<WorldPreset> preset;

    @Shadow @Final private static Component CUSTOM_WORLD_DESCRIPTION;

    @Inject(method = "<init>", at = @At("TAIL"))
    public void init(@Nullable Holder<WorldPreset> preset, CallbackInfo ci) {
        //System.err.println("INIT = " + preset.getRegisteredName());
    }
    /**
     * @author
     * @reason
     */
    @Overwrite
    public Component describePreset() {
        log.info("WorldPreset = {}", Optional.ofNullable(this.preset).orElseThrow().getRegisteredName());
        return Optional.ofNullable(this.preset)
                .flatMap(Holder::unwrapKey)
                .map(p_268048_ -> {
                    System.err.println(p_268048_.location().toLanguageKey("generator"));
                    return (Component)Component.translatable(p_268048_.location().toLanguageKey("generator"));
                })
                .orElse(CUSTOM_WORLD_DESCRIPTION);
    }

    @Override
    public boolean isSuperLarge() {
        return Optional.ofNullable(this.preset).flatMap(Holder::unwrapKey).filter(p_268224_ -> p_268224_.equals(RegisterWorldPresets.SUPER_LARGE_BIOMES)).isPresent();
    }
}
