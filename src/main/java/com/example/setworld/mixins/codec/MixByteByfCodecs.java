package com.example.setworld.mixins.codec;

import com.example.setworld.holder.DirHolder;
import com.example.setworld.holder.StaticVar;
import com.example.setworld.holder.SupMapId;
import net.minecraft.core.*;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.DimensionTypes;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.VarInt;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.IdDispatchCodec;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.level.dimension.BuiltinDimensionTypes;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraft.world.level.levelgen.WorldDimensions;
import net.minecraftforge.event.entity.living.MobSpawnEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

@Mixin(ByteBufCodecs.class)
public interface MixByteByfCodecs {

    /**
     * @author
     * @reason
     */
    @Overwrite
    private static <T, R> StreamCodec<RegistryFriendlyByteBuf, R> registry(
            final ResourceKey<? extends Registry<T>> p_332046_, final Function<Registry<T>, IdMap<R>> p_332827_
    ) {
        return new StreamCodec<RegistryFriendlyByteBuf, R>() {
            private IdMap<R> getRegistryOrThrow(RegistryFriendlyByteBuf p_336378_) {
                return p_332827_.apply(p_336378_.registryAccess().registryOrThrow(p_332046_));
            }

            public R decode(RegistryFriendlyByteBuf p_328896_) {
                int i = VarInt.read(p_328896_);
                IdMap<R> idmap = this.getRegistryOrThrow(p_328896_);
                R r = (R) idmap.byIdOrThrow(i);
                return r;
            }

            public void encode(RegistryFriendlyByteBuf p_335592_, R p_330248_) {
               if (p_330248_ instanceof Holder<?> holder && holder.value() instanceof DimensionType dimensionType) {
                   System.err.println(holder.unwrapKey().orElseThrow());
               }
                //System.err.println("p_330248_ = " + this.getRegistryOrThrow(p_335592_).getClass());
                int i = this.getRegistryOrThrow(p_335592_).getIdOrThrow(p_330248_);
                VarInt.write(p_335592_, i);
            }
        };


    }
}
