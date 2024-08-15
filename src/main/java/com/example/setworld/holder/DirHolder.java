package com.example.setworld.holder;

import com.example.setworld.ExampleMod;
import com.mojang.datafixers.util.Either;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderOwner;
import net.minecraft.core.IdMap;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.protocol.game.ClientboundLoginPacket;
import net.minecraft.network.protocol.game.CommonPlayerSpawnInfo;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;

import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class DirHolder<T> implements Holder<T> {
    private T value;
    private ResourceKey<T> key;

    public DirHolder(T value, ResourceKey<T> key) {
        this.value = value;
        this.key = key;
    }

    @Override
    public boolean isBound() {
        return true;
    }
    public ResourceKey<T> key() {
        if (this.key == null) {
            throw new IllegalStateException("Trying to access unbound value '" + this.value + "' from registry ");
        } else {
            return this.key;
        }
    }

    @Override
    public boolean is(ResourceLocation p_205779_) {
        return this.key().location().equals(p_205779_);
    }

    @Override
    public boolean is(ResourceKey<T> p_205774_) {
        return this.key() == p_205774_;
    }

    @Override
    public boolean is(TagKey<T> p_205760_) {
        return true;
    }

    @Override
    public boolean is(Holder<T> p_335729_) {
        return p_335729_.is(this.key());
    }

    @Override
    public boolean is(Predicate<ResourceKey<T>> p_205772_) {
        return p_205772_.test(this.key());
    }

    @Override
    public Either<ResourceKey<T>, T> unwrap() {
        return Either.right(this.value);
    }

    @Override
    public Optional<ResourceKey<T>> unwrapKey() {
        return Optional.of(key());
    }

    @Override
    public Holder.Kind kind() {
        return Holder.Kind.DIRECT;
    }

    @Override
    public String toString() {
        return "Direct{" + this.value + "}";
    }

    @Override
    public boolean canSerializeIn(HolderOwner<T> p_256328_) {
        return true;
    }

    @Override
    public Stream<TagKey<T>> tags() {
        return Stream.of();
    }

    @Override
    public T value() {
        return this.value;
    }
}
