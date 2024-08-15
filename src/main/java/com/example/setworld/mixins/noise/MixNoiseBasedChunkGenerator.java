package com.example.setworld.mixins.noise;

import com.example.setworld.holder.DirHolder;
import com.example.setworld.inter.IBiomeGenerationSettings;
import com.example.setworld.inter.IHR;
import com.example.setworld.inter.INoiseBasedChunkGenerator;
import com.example.setworld.world.WorldParam;
import com.google.common.base.Suppliers;
import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import io.netty.util.ThreadDeathWatcher;
import net.minecraft.core.*;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.Carvers;
import net.minecraft.data.worldgen.placement.CavePlacements;
import net.minecraft.resources.RegistryDataLoader;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ChunkMap;
import net.minecraft.server.level.WorldGenRegion;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.util.valueproviders.UniformFloat;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.LevelHeightAccessor;
import net.minecraft.world.level.NoiseColumn;
import net.minecraft.world.level.StructureManager;
import net.minecraft.world.level.biome.BiomeGenerationSettings;
import net.minecraft.world.level.biome.BiomeManager;
import net.minecraft.world.level.biome.BiomeSource;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.chunk.*;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraft.world.level.levelgen.*;
import net.minecraft.world.level.levelgen.blending.Blender;
import net.minecraft.world.level.levelgen.carver.*;
import net.minecraft.world.level.levelgen.heightproviders.UniformHeight;
import net.minecraft.world.level.levelgen.structure.StructureSet;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.gen.Invoker;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Mixin(NoiseBasedChunkGenerator.class)
public abstract class MixNoiseBasedChunkGenerator extends ChunkGenerator implements INoiseBasedChunkGenerator {

    public MixNoiseBasedChunkGenerator(BiomeSource p_256133_) {
        super(p_256133_);
    }

    @Shadow
    @Final
    @Mutable
    public static MapCodec<NoiseBasedChunkGenerator> CODEC = RecordCodecBuilder.mapCodec(
            p_255585_ -> p_255585_.group(
                            BiomeSource.CODEC.fieldOf("biome_source").forGetter(p_255584_ -> p_255584_.getBiomeSource()),
                            NoiseGeneratorSettings.CODEC.fieldOf("settings").forGetter(p_224278_ -> p_224278_.generatorSettings()),
                            Codec.list(StructureSet.CODEC).fieldOf("holders_structures").forGetter(p_255584_ -> ((INoiseBasedChunkGenerator) p_255584_).getStructureSet())
                    )
                    .apply(p_255585_, p_255585_.stable((biomeSource1, noiseGeneratorSettingsHolder, holders) -> {
                        NoiseBasedChunkGenerator noiseBasedChunkGenerator = new NoiseBasedChunkGenerator(biomeSource1, noiseGeneratorSettingsHolder);
                        ((INoiseBasedChunkGenerator) noiseBasedChunkGenerator).setStructureSet(holders);
                        return noiseBasedChunkGenerator;
                    }))
    );

    static {
        // Overwrite the CODEC field
        CODEC = RecordCodecBuilder.mapCodec(
                instance -> instance.group(
                                BiomeSource.CODEC.fieldOf("biome_source").forGetter(NoiseBasedChunkGenerator::getBiomeSource),
                                NoiseGeneratorSettings.CODEC.fieldOf("settings").forGetter(NoiseBasedChunkGenerator::generatorSettings),
                                Codec.list(StructureSet.CODEC).optionalFieldOf("holders_structures", List.of()).forGetter(generator -> Optional.ofNullable(((INoiseBasedChunkGenerator) generator).getStructureSet()).orElse(List.of()))
                        )
                        .apply(instance, instance.stable((biomeSource, settings, structures) -> {
                            NoiseBasedChunkGenerator generator = new NoiseBasedChunkGenerator(biomeSource, settings);
                            ((INoiseBasedChunkGenerator) generator).setStructureSet(structures);
                            System.err.println("@ark");
                            return generator;
                        }))
        );
    }

    private List<Holder<StructureSet>> holdersStructures;

    private WorldParam.CaveGeneratorSettings caveGeneratorSettings;

    @Override
    @Shadow
    protected MapCodec<? extends ChunkGenerator> codec() {
        return null;
    }

    @Override
    @Shadow
    public void buildSurface(WorldGenRegion p_223050_, StructureManager p_223051_, RandomState p_223052_, ChunkAccess p_223053_) {

    }

    @Override
    @Shadow
    public void spawnOriginalMobs(WorldGenRegion p_62167_) {

    }

    @Override
    @Shadow
    public int getGenDepth() {
        return 0;
    }

    @Override
    @Shadow
    public CompletableFuture<ChunkAccess> fillFromNoise(Blender p_223210_, RandomState p_223211_, StructureManager p_223212_, ChunkAccess p_223213_) {
        return null;
    }

    @Override
    @Shadow
    public int getSeaLevel() {
        return 0;
    }

    @Override
    @Shadow
    public int getMinY() {
        return 0;
    }

    @Override
    @Shadow
    public int getBaseHeight(int p_223032_, int p_223033_, Heightmap.Types p_223034_, LevelHeightAccessor p_223035_, RandomState p_223036_) {
        return 0;
    }

    @Override
    @Shadow
    public NoiseColumn getBaseColumn(int p_223028_, int p_223029_, LevelHeightAccessor p_223030_, RandomState p_223031_) {
        return null;
    }

    @Override
    @Shadow
    public void addDebugScreenInfo(List<String> p_223175_, RandomState p_223176_, BlockPos p_223177_) {

    }

    @Shadow
    protected abstract NoiseChunk createNoiseChunk(ChunkAccess p_224257_, StructureManager p_224258_, Blender p_224259_, RandomState p_224260_);

    @Shadow
    @Final
    private Holder<NoiseGeneratorSettings> settings;

    @Override
    @Unique
    public List<Holder<StructureSet>> getStructureSet() {
        return holdersStructures;
    }

    private int lavaLevel = -54;
    @Shadow
    @Final
    @Mutable
    private Supplier<Aquifer.FluidPicker> globalFluidPicker;

    @Override
    @Unique
    public void setStructureSet(List<Holder<StructureSet>> structureSet) {
        holdersStructures = structureSet;
        System.err.println("holdersStructures.stream().count() = " + holdersStructures.stream().count());
    }

    /**
     * @author
     * @reason
     */
    @Override
    public ChunkGeneratorStructureState createState(HolderLookup<StructureSet> p_256405_, RandomState p_256101_, long p_256018_) {
        HolderLookup<StructureSet> holdersS = new HolderLookup<StructureSet>() {
            @Override
            public Stream<Holder.Reference<StructureSet>> listElements() {
                Stream<Holder.Reference<StructureSet>> stream = holdersStructures.stream().map(p -> (Holder.Reference<StructureSet>) p);
                return stream;
            }

            @Override
            public Stream<HolderSet.Named<StructureSet>> listTags() {
                return Stream.empty();
            }

            @Override
            public Optional<Holder.Reference<StructureSet>> get(ResourceKey<StructureSet> p_255645_) {
                return Optional.empty();
            }

            @Override
            public Optional<HolderSet.Named<StructureSet>> get(TagKey<StructureSet> p_256283_) {
                return Optional.empty();
            }
        };
        ChunkGeneratorStructureState.createForNormal(p_256101_, p_256018_, this.biomeSource, holdersS);
        return super.createState(p_256405_, p_256101_, p_256018_);
    }

    /**
     * @author
     * @reason
     */
    @Override
    @Overwrite
    @SuppressWarnings("unchecked")
    public void applyCarvers(
            WorldGenRegion p_224224_,
            long p_224225_,
            RandomState p_224226_,
            BiomeManager p_224227_,
            StructureManager p_224228_,
            ChunkAccess p_224229_,
            GenerationStep.Carving p_224230_
    ) {
        BiomeManager biomemanager = p_224227_.withDifferentSource(
                (p_255581_, p_255582_, p_255583_) -> this.biomeSource.getNoiseBiome(p_255581_, p_255582_, p_255583_, p_224226_.sampler())
        );
        WorldgenRandom worldgenrandom = new WorldgenRandom(new LegacyRandomSource(RandomSupport.generateUniqueSeed()));
        int i = 8;
        ChunkPos chunkpos = p_224229_.getPos();
        NoiseChunk noisechunk = p_224229_.getOrCreateNoiseChunk(p_224250_ -> this.createNoiseChunk(p_224250_, p_224228_, Blender.of(p_224224_), p_224226_));
        Aquifer aquifer = noisechunk.aquifer();
        CarvingContext carvingcontext = new CarvingContext(
                (NoiseBasedChunkGenerator) (Object) this, p_224224_.registryAccess(), p_224229_.getHeightAccessorForGeneration(), noisechunk, p_224226_, this.settings.value().surfaceRule()
        );
        Random random = new Random();
        CarvingMask carvingmask = ((ProtoChunk) p_224229_).getOrCreateCarvingMask(p_224230_);
        for (int j = -8; j <= 8; j++) {
            for (int k = -8; k <= 8; k++) {
                ChunkPos chunkpos1 = new ChunkPos(chunkpos.x + j, chunkpos.z + k);
                ChunkAccess chunkaccess = p_224224_.getChunk(chunkpos1.x, chunkpos1.z);
                BiomeGenerationSettings biomegenerationsettings = chunkaccess.carverBiome(
                        () -> this.getBiomeGenerationSettings(
                                this.biomeSource
                                        .getNoiseBiome(QuartPos.fromBlock(chunkpos1.getMinBlockX()), 0, QuartPos.fromBlock(chunkpos1.getMinBlockZ()), p_224226_.sampler())
                        )
                );
                if (random.nextInt(1000000) % 98999 == 0) {
                    ConfiguredWorldCarver<?> configuredworldcarver = ((IBiomeGenerationSettings) biomegenerationsettings).getCarvers().get(GenerationStep.Carving.AIR).stream().filter(p -> p.is(Carvers.CAVE_EXTRA_UNDERGROUND)).findAny().orElse(null).value();
                    if (configuredworldcarver.config() instanceof CaveCarverConfiguration configuration) {
                        System.err.println("Config[ probability = " + configuration.probability + " Y = " + configuration.y + " lavaLevel = " + configuration.lavaLevel + "]");
                    }
                    //System.err.println(((IBiomeGenerationSettings) biomegenerationsettings).getCarvers().get(GenerationStep.Carving.AIR).stream().filter(p -> p.is(Carvers.CAVE_EXTRA_UNDERGROUND)).findAny().orElse(null).value().config());
                }
                HolderGetter<Block> holderGetter = p_224224_.registryAccess().asGetterLookup().lookup(Registries.BLOCK).orElseThrow();
                Map<GenerationStep.Carving, HolderSet<ConfiguredWorldCarver<?>>> map = ((IBiomeGenerationSettings) (Object) biomegenerationsettings).getCarvers();
                map.put(GenerationStep.Carving.AIR, HolderSet.direct(
                        map.get(GenerationStep.Carving.AIR).stream().map(p -> {
                            if (p.value().config() instanceof CaveCarverConfiguration) {
                                Holder.Reference<ConfiguredWorldCarver<?>> holder = (Holder.Reference<ConfiguredWorldCarver<?>>) p;
                                HolderOwner<ConfiguredWorldCarver<?>> holderOwner;
                                try {
                                    WorldParam.CaveGeneratorSettings caveSettings = this.getCaveSettings();
                                    if (caveSettings == null) {
                                        caveSettings = WorldParam.CaveGeneratorSettings.toDefault();
                                        System.err.println("caveSettings = NULL");
                                    }
                                    boolean isExtreme = holder.is(Carvers.CAVE_EXTRA_UNDERGROUND);
                                    Class<?> clazz2 = Holder.Reference.class;
                                    Constructor<?> constructor = clazz2.getDeclaredConstructor(Holder.Reference.Type.class, HolderOwner.class, ResourceKey.class, Object.class);
                                    constructor.setAccessible(true);
                                    holderOwner = (HolderOwner<ConfiguredWorldCarver<?>>) ((IHR) holder).getHolderOwner();
                                    Holder.Reference<ConfiguredWorldCarver<?>> ps = (Holder.Reference<ConfiguredWorldCarver<?>>) constructor.newInstance(holder.getType(), holderOwner, holder.key(), WorldCarver.CAVE.configured(new CaveCarverConfiguration(
                                            isExtreme ? caveSettings.probability() / 2 : caveSettings.probability(),
                                            UniformHeight.of(VerticalAnchor.aboveBottom(caveSettings.minY()), VerticalAnchor.absolute(isExtreme ? caveSettings.maxYForExtreme() : caveSettings.maxY())),
                                            UniformFloat.of(caveSettings.minScaleY(), caveSettings.maxScaleY()),
                                            VerticalAnchor.aboveBottom(8),
                                            CarverDebugSettings.of(false, Blocks.CRIMSON_BUTTON.defaultBlockState()),
                                            holderGetter.getOrThrow(BlockTags.OVERWORLD_CARVER_REPLACEABLES),
                                            UniformFloat.of(caveSettings.horizontalRadiusMultiplierMin(), caveSettings.horizontalRadiusMultiplierMax()),
                                            UniformFloat.of(caveSettings.verticalRadiusMultiplierMin(), caveSettings.verticalRadiusMultiplierMax()),
                                            UniformFloat.of(caveSettings.floorLevelMin(), caveSettings.floorLevelMax())
                                    )));
                                    return ps;
                                } catch (Exception e) {
                                    throw new RuntimeException(e);
                                }
                            }

                            return p;
                        }).toList()
                ));

                Iterable<Holder<ConfiguredWorldCarver<?>>> iterable = biomegenerationsettings.getCarvers(p_224230_);
                int l = 0;

                for (Holder<ConfiguredWorldCarver<?>> holder : iterable) {
                    ConfiguredWorldCarver<?> configuredworldcarver = holder.value();
                    worldgenrandom.setLargeFeatureSeed(p_224225_ + (long) l, chunkpos1.x, chunkpos1.z);
                    if (configuredworldcarver.isStartChunk(worldgenrandom)) {
                        configuredworldcarver.carve(carvingcontext, p_224229_, biomemanager::getBiome, worldgenrandom, aquifer, chunkpos1, carvingmask);
                    }


                    l++;
                }
            }
        }
    }

    /**
     * @author
     * @reason
     */
    @Overwrite
    private static Aquifer.FluidPicker createFluidPicker(NoiseGeneratorSettings p_249264_) {
        Aquifer.FluidStatus aquifer$fluidstatus = new Aquifer.FluidStatus(-54, Blocks.LAVA.defaultBlockState());
        int i = p_249264_.seaLevel();
        Aquifer.FluidStatus aquifer$fluidstatus1 = new Aquifer.FluidStatus(i, p_249264_.defaultFluid());
        return (p_224274_, p_224275_, p_224276_) -> p_224275_ < Math.min(-54, i) ? aquifer$fluidstatus : aquifer$fluidstatus1;
    }

    @Unique
    private static Aquifer.FluidPicker createFluidPickerCustom(NoiseGeneratorSettings p_249264_, WorldParam.CaveSettings caveSettings) {
        int errorLavaLevel = caveSettings.getForceOfErrorForChunk() == 0 ? caveSettings.getLavaLevel() : new Random().nextInt(caveSettings.getLavaLevel() - caveSettings.getForceOfErrorForChunk(), caveSettings.getLavaLevel() + caveSettings.getForceOfErrorForChunk());
        Aquifer.FluidStatus aquifer$fluidstatus = new Aquifer.FluidStatus(errorLavaLevel, Blocks.LAVA.defaultBlockState());
        int i = p_249264_.seaLevel();
        Aquifer.FluidStatus aquifer$fluidstatus1 = new Aquifer.FluidStatus(i, p_249264_.defaultFluid());
        return (p_224274_, p_224275_, p_224276_) -> p_224275_ < Math.min(errorLavaLevel, i) ? aquifer$fluidstatus : aquifer$fluidstatus1;
    }

    @Override
    public int getLavaLevel() {
        return lavaLevel;
    }

    @Override
    public void setLavaLevel(int lavaLevel) {
        this.lavaLevel = lavaLevel;
    }

    @Override
    public Supplier<Aquifer.FluidPicker> getFluidPicker() {
        return globalFluidPicker;
    }

    @Override
    public void setFluidPicker(WorldParam.CaveSettings caveSettings) {
        globalFluidPicker = Suppliers.memoize(() -> createFluidPickerCustom(settings.value(), caveSettings));
    }

    @Override
    public WorldParam.CaveGeneratorSettings getCaveSettings() {
        return caveGeneratorSettings;
    }

    @Override
    public void setCaveSettings(WorldParam.CaveGeneratorSettings caveSettings) {
        this.caveGeneratorSettings = caveSettings;
    }
}
