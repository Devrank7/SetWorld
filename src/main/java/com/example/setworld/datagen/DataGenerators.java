package com.example.setworld.datagen;

import com.example.setworld.ExampleMod;
import com.example.setworld.register.*;
import com.example.setworld.tags.WorldPresentsTags;
import com.example.setworld.tags.WrldPresentTagsProvider;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.DataProvider;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.DatapackBuiltinEntriesProvider;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.fml.common.Mod;

import java.io.IOException;
import java.util.Map;
import java.util.Set;

public class DataGenerators {
    @Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class GatherDataGenerators {

        public static final RegistrySetBuilder BUILDER = new RegistrySetBuilder()
                .add(Registries.NOISE, RegNoiseData::bootstrap)
                .add(Registries.DENSITY_FUNCTION, RegNoiseRouterData::bootstrap)
                .add(Registries.NOISE_SETTINGS, RegNoiseGeneratorSettings::bootstrap)
                .add(Registries.WORLD_PRESET, RegisterWorldPresets::bootstrap)
                .add(Registries.DIMENSION_TYPE, RegDimensionType::bootstrap);

        @SubscribeEvent
        public static void gatherData(GatherDataEvent event) throws IOException, InterruptedException {
            System.err.println("DATA");
            DataGenerator generator = event.getGenerator();
            for (Map.Entry<String, DataProvider> data : generator.getProvidersView().entrySet()) {
                System.err.println("key: " + data.getKey() + " value: " + data.getValue());
            }
            PackOutput packOutput = generator.getPackOutput();
            generator.addProvider(event.includeServer(), new DatapackBuiltinEntriesProvider(packOutput, event.getLookupProvider(), BUILDER, Set.of(ExampleMod.MODID)));
            generator.addProvider(event.includeServer(), new WrldPresentTagsProvider(packOutput, event.getLookupProvider(),ExampleMod.MODID, event.getExistingFileHelper()));
        }
    }

    @Mod.EventBusSubscriber(modid = ExampleMod.MODID)
    public class DataGenerators1 {
        @SubscribeEvent
        public static void PlayerTick(TickEvent.PlayerTickEvent event) {
            if (event.side == LogicalSide.SERVER) {
                if (event.player.getRandom().nextInt(100) != 0) return;
                System.out.println(RegisterWorldPresets.SUPER_LARGE_BIOMES.location());
            }
        }
    }
}
