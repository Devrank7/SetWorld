package com.example.setworld.mixins.codec;

import com.mojang.datafixers.util.Pair;
import com.mojang.serialization.Lifecycle;
import it.unimi.dsi.fastutil.objects.ObjectList;
import it.unimi.dsi.fastutil.objects.Reference2IntMap;
import net.minecraft.Util;
import net.minecraft.core.*;
import net.minecraft.data.worldgen.placement.CavePlacements;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.levelgen.NoiseSettings;
import net.minecraft.world.level.levelgen.carver.CaveCarverConfiguration;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

import java.util.*;
import java.util.stream.Stream;

@Mixin(MappedRegistry.class)
public abstract class MixMappedReg<T> implements WritableRegistry<T> {

    @Override
    @Shadow
    public ResourceKey<? extends Registry<T>> key() {
        return null;
    }

    @Nullable
    @Override
    @Shadow
    public ResourceLocation getKey(T p_123006_) {
        return null;
    }

    @Override
    @Shadow
    public Optional<ResourceKey<T>> getResourceKey(T p_123008_) {
        return Optional.empty();
    }

    @Override
    @Shadow
    public int getId(@Nullable T p_122977_) {
        return 0;
    }

    @Nullable
    @Override
    @Shadow
    public T get(@Nullable ResourceKey<T> p_122980_) {
        return null;
    }

    @Nullable
    @Override
    @Shadow
    public T get(@Nullable ResourceLocation p_123002_) {
        return null;
    }

    @Override
    @Shadow
    public Optional<RegistrationInfo> registrationInfo(ResourceKey<T> p_333179_) {
        return Optional.empty();
    }

    @Override
    @Shadow
    public Lifecycle registryLifecycle() {
        return null;
    }

    @Override
    @Shadow
    public Optional<Holder.Reference<T>> getAny() {
        return Optional.empty();
    }

    @Override
    @Shadow
    public Set<ResourceLocation> keySet() {
        return Set.of();
    }

    @Override
    @Shadow
    public Set<Map.Entry<ResourceKey<T>, T>> entrySet() {
        return Set.of();
    }

    @Override
    @Shadow
    public Set<ResourceKey<T>> registryKeySet() {
        return Set.of();
    }

    @Override
    @Shadow
    public Optional<Holder.Reference<T>> getRandom(RandomSource p_235781_) {
        return Optional.empty();
    }

    @Override
    @Shadow
    public boolean containsKey(ResourceLocation p_123011_) {
        return false;
    }

    @Override
    @Shadow
    public boolean containsKey(ResourceKey<T> p_175475_) {
        return false;
    }

    @Override
    @Shadow
    public Registry<T> freeze() {
        return null;
    }

    @Override
    @Shadow
    public Holder.Reference<T> createIntrusiveHolder(T p_206068_) {
        return null;
    }

    @Override
    @Shadow
    public Optional<Holder.Reference<T>> getHolder(ResourceLocation p_329586_) {
        return Optional.empty();
    }

    @Override
    @Shadow
    public Optional<Holder.Reference<T>> getHolder(ResourceKey<T> p_206050_) {
        return Optional.empty();
    }

    @Override
    @Shadow
    public Holder<T> wrapAsHolder(T p_263382_) {
        return null;
    }

    @Override
    @Shadow
    public Stream<Holder.Reference<T>> holders() {
        return Stream.empty();
    }

    @Override
    @Shadow
    public Optional<HolderSet.Named<T>> getTag(TagKey<T> p_206052_) {
        return Optional.empty();
    }

    @Override
    @Shadow
    public HolderSet.Named<T> getOrCreateTag(TagKey<T> p_206045_) {
        return null;
    }

    @Override
    @Shadow
    public Stream<Pair<TagKey<T>, HolderSet.Named<T>>> getTags() {
        return Stream.empty();
    }

    @Override
    @Shadow
    public Stream<TagKey<T>> getTagNames() {
        return Stream.empty();
    }

    @Override
    @Shadow
    public void resetTags() {

    }

    @Override
    @Shadow
    public void bindTags(Map<TagKey<T>, List<Holder<T>>> p_205997_) {

    }

    @Override
    @Shadow
    public HolderOwner<T> holderOwner() {
        return null;
    }

    @Override
    @Shadow
    public HolderLookup.RegistryLookup<T> asLookup() {
        return null;
    }

    @Nullable
    @Override
    @Shadow
    public T byId(int p_122651_) {
        return null;
    }

    @Override
    @Shadow
    public int size() {
        return 0;
    }

    @NotNull
    @Override
    @Shadow
    public Iterator<T> iterator() {
        return null;
    }

    @Shadow
    private boolean frozen;

    @Shadow @Final private ObjectList<Holder.Reference<T>> byId;

    @Shadow protected abstract void markKnown();

    @Shadow @Final private Map<ResourceLocation, Holder.Reference<T>> byLocation;

    @Shadow @Final private Map<T, Holder.Reference<T>> byValue;

    @Shadow @javax.annotation.Nullable protected Map<T, Holder.Reference<T>> unregisteredIntrusiveHolders;

    @Shadow @Final private Map<ResourceKey<T>, Holder.Reference<T>> byKey;

    @Shadow @Final private Reference2IntMap<T> toId;

    @Shadow @Final private Map<ResourceKey<T>, RegistrationInfo> registrationInfos;

    @Shadow private Lifecycle registryLifecycle;

    /**
     * @author
     * @reason
     */
    @Overwrite
    private void validateWrite(ResourceKey<T> p_205922_) {
        if (p_205922_.location().getPath().contains("custom")) return;
        if (this.frozen) {
            throw new IllegalStateException("Registry is already frozen (trying to add key " + p_205922_ + ")");
        }
    }

    /**
     * @author
     * @reason
     */
    @Overwrite
    private void validateWrite() {
        if (this.frozen) {
            throw new IllegalStateException("Registry is already frozen");
        }
    }
    @Override
    public Optional<Holder.Reference<T>> getHolder(int p_205907_) {
        return p_205907_ >= 0 && p_205907_ < this.byId.size() ? Optional.ofNullable(this.byId.get(p_205907_)) : Optional.empty();
    }
    /**
     * @author
     * @reason
     */
    @Override
    @Overwrite
    public Holder.Reference<T> register(ResourceKey<T> p_256252_, T p_256591_, RegistrationInfo p_329661_) {
        markKnown();
        this.validateWrite(p_256252_);
        Objects.requireNonNull(p_256252_);
        Objects.requireNonNull(p_256591_);
        if (this.byLocation.containsKey(p_256252_.location())) {
            Util.pauseInIde(new IllegalStateException("Adding duplicate key '" + p_256252_ + "' to registry"));
        }

        if (this.byValue.containsKey(p_256591_)) {
            Util.pauseInIde(new IllegalStateException("Adding duplicate value '" + p_256591_ + "' to registry"));
        }

        Holder.Reference<T> reference;
        if (this.unregisteredIntrusiveHolders != null) {
            reference = this.unregisteredIntrusiveHolders.remove(p_256591_);
            if (reference == null) {
                throw new AssertionError("Missing intrusive holder for " + p_256252_ + ":" + p_256591_);
            }

            reference.bindKey(p_256252_);
        } else {
            reference = this.byKey.computeIfAbsent(p_256252_, p_258168_ -> Holder.Reference.createStandAlone(this.holderOwner(), (ResourceKey<T>)p_258168_));
            // Forge: Bind the value immediately so it can be queried while the registry is not frozen
            reference.bindValue(p_256591_);
        }
        if (p_256252_.location().getPath().contains("custom")) {
            System.err.println(reference.toString());
        }

        this.byKey.put(p_256252_, reference);
        this.byLocation.put(p_256252_.location(), reference);
        this.byValue.put(p_256591_, reference);
        int i = this.byId.size();
        this.byId.add(reference);
        this.toId.put(p_256591_, i);
        this.registrationInfos.put(p_256252_, p_329661_);
        this.registryLifecycle = this.registryLifecycle.add(p_329661_.lifecycle());
        if (p_256252_.location().getPath().contains("custom")) {
            System.err.println("byId size = " + this.byId.size() + " hashCode = " + hashCode());
        }
        return reference;
    }
}
