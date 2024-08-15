package com.example.setworld.mixins.noise;

import com.example.setworld.inter.ICaveWorldCarver;
import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.chunk.CarvingMask;
import net.minecraft.world.level.chunk.ChunkAccess;
import net.minecraft.world.level.levelgen.Aquifer;
import net.minecraft.world.level.levelgen.carver.CarvingContext;
import net.minecraft.world.level.levelgen.carver.CaveCarverConfiguration;
import net.minecraft.world.level.levelgen.carver.CaveWorldCarver;
import net.minecraft.world.level.levelgen.carver.WorldCarver;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;

import java.util.function.Function;

@Mixin(CaveWorldCarver.class)
public class MixCaveWorldCarver extends WorldCarver<CaveCarverConfiguration> implements ICaveWorldCarver {

    private int range = 4;
    public MixCaveWorldCarver(Codec<CaveCarverConfiguration> p_159366_) {
        super(p_159366_);
    }

    @Override
    public int getRange() {
        return range;
    }

    @Override
    @Unique
    public void setRange(int range) {
        this.range = range;
    }

    @Override
    @Shadow
    public boolean carve(CarvingContext p_224913_, CaveCarverConfiguration p_224914_, ChunkAccess p_224915_, Function<BlockPos, Holder<Biome>> p_224916_, RandomSource p_224917_, Aquifer p_224918_, ChunkPos p_224919_, CarvingMask p_224920_) {
        return false;
    }

    @Override
    @Shadow
    public boolean isStartChunk(CaveCarverConfiguration p_224908_, RandomSource p_224909_) {
        return false;
    }
}
