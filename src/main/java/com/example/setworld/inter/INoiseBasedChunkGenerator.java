package com.example.setworld.inter;

import com.example.setworld.world.WorldParam;
import net.minecraft.core.Holder;
import net.minecraft.world.level.levelgen.Aquifer;
import net.minecraft.world.level.levelgen.structure.StructureSet;

import java.util.List;
import java.util.function.Supplier;

public interface INoiseBasedChunkGenerator {

    List<Holder<StructureSet>> getStructureSet();

    void setStructureSet(List<Holder<StructureSet>> structureSet);
    int getLavaLevel();

    void setLavaLevel(int lavaLevel);
    Supplier<Aquifer.FluidPicker> getFluidPicker();

    void setFluidPicker(WorldParam.CaveSettings caveSettings);
    WorldParam.CaveGeneratorSettings getCaveSettings();

    void setCaveSettings(WorldParam.CaveGeneratorSettings caveSettings);
}
