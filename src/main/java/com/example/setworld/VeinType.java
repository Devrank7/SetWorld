package com.example.setworld;

import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;

public enum VeinType {
        COPPER(Blocks.COPPER_ORE.defaultBlockState(), Blocks.RAW_COPPER_BLOCK.defaultBlockState(), Blocks.GRANITE.defaultBlockState(), 0, 50),
        IRON(Blocks.DEEPSLATE_IRON_ORE.defaultBlockState(), Blocks.RAW_IRON_BLOCK.defaultBlockState(), Blocks.TUFF.defaultBlockState(), -60, -8);

        final BlockState ore;
        final BlockState rawOreBlock;
        final BlockState filler;
        public final int minY;
        public final int maxY;

        private VeinType(final BlockState p_209684_, final BlockState p_209685_, final BlockState p_209686_, final int p_209687_, final int p_209688_) {
            this.ore = p_209684_;
            this.rawOreBlock = p_209685_;
            this.filler = p_209686_;
            this.minY = p_209687_;
            this.maxY = p_209688_;
        }
}
