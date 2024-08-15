package com.example.setworld.holder;

import net.minecraft.core.HolderOwner;

public class HolderOwn<T> implements HolderOwner<T> {
    @Override
    public boolean canSerializeIn(HolderOwner<T> p_255875_) {
        return HolderOwner.super.canSerializeIn(p_255875_);
    }
}
