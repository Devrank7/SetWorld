package com.example.setworld.holder;

import net.minecraft.core.Holder;
import net.minecraft.core.IdMap;
import net.minecraft.core.Registry;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Iterator;

public class SupMapId<T> implements IdMap<Holder<T>> {

    private Registry<T> registery;

    public SupMapId(Registry<T> registery) {
        this.registery = registery;
    }

    @Override
    public int getId(Holder<T> p_122652_) {
        return registery.getId(p_122652_.value());
    }

    @Nullable
    @Override
    public Holder<T> byId(int p_122651_) {
        return registery.getHolder(p_122651_).orElse(null);
    }

    @Override
    public int size() {
        return registery.size();
    }

    @NotNull
    @Override
    public Iterator<Holder<T>> iterator() {
        return registery.holders().map(p_260061_ -> (Holder<T>) p_260061_).iterator();
    }
    public Registry<T> getRegistery() {
        return registery;
    }
}
