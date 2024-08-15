package com.example.setworld.mixins.codec;

import com.example.setworld.holder.SupMapId;
import com.mojang.serialization.Keyable;
import net.minecraft.core.Holder;
import net.minecraft.core.IdMap;
import net.minecraft.core.Registry;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

import javax.annotation.Nullable;
import java.util.Iterator;
import java.util.Optional;
import java.util.stream.Stream;

@Mixin(Registry.class)
public interface MixRegistery<T> extends Keyable, IdMap<T> {

    /**
     * @author
     * @reason
     */
    @Overwrite
    default IdMap<Holder<T>> asHolderIdMap() {
        return new SupMapId<T>((Registry<T>) this);
//        return new IdMap<Holder<T>>() {
//            public int getId(Holder<T> p_259992_) {
//                return MixRegistery.this.getId(p_259992_.value());
//            }
//
//            @Nullable
//            public Holder<T> byId(int p_259972_) {
//                return (Holder<T>) MixRegistery.this.getHolder(p_259972_).orElse(null);
//            }
//
//            @Override
//            public int size() {
//                return MixRegistery.this.size();
//            }
//
//            @Override
//            public Iterator<Holder<T>> iterator() {
//                return MixRegistery.this.holders().map(p_260061_ -> (Holder<T>) p_260061_).iterator();
//            }
//        };
    }
}
