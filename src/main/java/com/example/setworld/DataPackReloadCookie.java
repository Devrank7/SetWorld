package com.example.setworld;

import net.minecraft.world.level.WorldDataConfiguration;
import net.minecraft.world.level.levelgen.WorldGenSettings;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public record DataPackReloadCookie(WorldGenSettings worldGenSettings, WorldDataConfiguration dataConfiguration) {
}
