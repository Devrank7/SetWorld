package com.example.setworld.mixins.codec;

import com.example.setworld.inter.IHR;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderOwner;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import java.lang.ref.Reference;

@Mixin(Holder.Reference.class)
public class MixHolderRef<T> implements IHR {
    @Shadow
    @Final
    private HolderOwner<T> owner;

    @Override
    public HolderOwner<?> getHolderOwner() {
        return owner;
    }
}
