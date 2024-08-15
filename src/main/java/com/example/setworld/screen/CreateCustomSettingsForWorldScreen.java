package com.example.setworld.screen;

import com.example.setworld.gui.FixOptionInstance;
import com.example.setworld.world.DimensionSelectType;
import com.example.setworld.world.NoisesGenerateSettings;
import com.example.setworld.world.WorldParam;
import com.mojang.serialization.Codec;
import net.minecraft.client.Minecraft;
import net.minecraft.client.Options;
import net.minecraft.client.gui.components.*;
import net.minecraft.client.gui.layouts.HeaderAndFooterLayout;
import net.minecraft.client.gui.layouts.LinearLayout;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.worldselection.WorldCreationContext;
import net.minecraft.core.*;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.util.Mth;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraft.world.level.levelgen.structure.placement.ConcentricRingsStructurePlacement;
import net.minecraft.world.level.levelgen.structure.placement.RandomSpreadStructurePlacement;
import net.minecraft.world.level.levelgen.structure.placement.StructurePlacement;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Map;
import java.util.Optional;
import java.util.function.Consumer;

public class CreateCustomSettingsForWorldScreen extends Screen {
    public final HeaderAndFooterLayout layout = new HeaderAndFooterLayout(this);
    private final Screen parent;
    public final Consumer<WorldParam> applySettings;
    private WorldParam worldParam;
    public FixOptionInstance<Integer> generalSizeSlider;
    public FixOptionInstance<Integer> seaLevelSlider;
    public FixOptionInstance<Integer> maxHeightSlider;
    public FixOptionInstance<Integer> minHeightSlider;
    public DimensionSelectType dimensionSelectType;
    private OpList optionsList;
    private WorldCreationContext worldCreationContext;

    public CreateCustomSettingsForWorldScreen(Screen p_232732_, WorldCreationContext p_232733_, Consumer<WorldParam> p_232734_) {
        super(Component.translatable("World"));
        this.parent = p_232732_;
        this.applySettings = p_232734_;
        this.worldCreationContext = p_232733_;
        RegistryAccess registryaccess = p_232733_.worldgenLoadContext();
        HolderGetter<Biome> holdergetter = registryaccess.lookupOrThrow(Registries.BIOME);
        dimensionSelectType = DimensionSelectType.DEFAULT_OVERWORLD;
        this.worldParam = new WorldParam(
                4,
                63,
                384,
                -64,
                new WorldParam.TemperatureParam(4, 1.5f, 975, 0, 10, -1, 3, 0, 1),
                new WorldParam.VegatatioParam(4, 1, 975, 0, 10),
                new WorldParam.ErosionParam(4, 1, 975, 0, 10),
                new WorldParam.ContinetsParam(4, 1, 975, 0, 10),
                new WorldParam.RidgesParam(4, 1, 975, 0, 10),
                DimensionSelectType.DEFAULT_OVERWORLD,
                new WorldParam.CaveSettings(-54, 0),
                WorldParam.CaveGeneratorSettings.toDefault(),
                new WorldParam.AdvancedSettings().toDefault()
        ).setStructureSettings(WorldParam.getDefaultStructureSettings(holdergetter));
    }

    @Override
    public void onClose() {
        this.minecraft.setScreen(this.parent);
    }

    public WorldParam getWorldParam() {
        return worldParam;
    }

    public void setWorldParam(WorldParam worldParam) {
        this.worldParam = worldParam;
    }

    @Override
    protected void init() {
        this.addTitle();
        this.addContents();
        this.layout.visitWidgets(p_344531_ -> {
            AbstractWidget abstractwidget = this.addRenderableWidget(p_344531_);
        });
        AbstractWidget abstractWidget = new StringWidget(Component.literal("World Settings"), font);
        abstractWidget.setWidth(width / 2 + 85);
        optionsList.addSmall(abstractWidget, null);
        this.generalSizeSlider = new FixOptionInstance<>(
                "general_size",
                FixOptionInstance.noTooltip(),
                Options::genericValueOrOffLabel,
                new FixOptionInstance.IntRange(0, 20),
                Codec.intRange(0, 20),
                this.worldParam.getGeneral_size(),
                this.worldParam::setGeneral_size,
                null,
                null
        );

        this.seaLevelSlider = new FixOptionInstance<>(
                "sea_level",
                FixOptionInstance.noTooltip(),
                Options::genericValueOrOffLabel,
                new FixOptionInstance.IntRange(-64, 384),
                Codec.intRange(-64, 384),
                this.worldParam.getSea_level(),
                this.worldParam::setSea_level,
                null,
                null
        );
        this.maxHeightSlider = new FixOptionInstance<>(
                "max_height",
                FixOptionInstance.noTooltip(),
                Options::genericValueOrOffLabel,
                new FixOptionInstance.IntRange(0, 1000),
                Codec.intRange(0, 1000),
                this.worldParam.getMax_height(),
                this.worldParam::setMax_height,
                null,
                null
        );

        this.minHeightSlider = new FixOptionInstance<>(
                "min_height",
                FixOptionInstance.noTooltip(),
                Options::genericValueOrOffLabel,
                new FixOptionInstance.IntRange(-200, 0),
                Codec.intRange(-200, 0),
                this.worldParam.getMin_height(),
                this.worldParam::setMin_height,
                null,
                null
        );
        FixOptionInstance<DimensionSelectType> dimensionSelectTypeFixOptionInstance = new FixOptionInstance<>(
                "Settings",
                FixOptionInstance.noTooltip(),
                (k, v) -> {
                    return v.getCaption();
                },
                new FixOptionInstance.Enum<>(Arrays.asList(DimensionSelectType.values()), DimensionSelectType.CODEC),
                DimensionSelectType.DEFAULT_OVERWORLD,
                p -> {
                    System.err.println("New dimension type: " + p + " old type: " + dimensionSelectType);
                    dimensionSelectType = p;
                }
        );
        optionsList.addBig(this.generalSizeSlider);
        optionsList.addBig(this.seaLevelSlider);
        optionsList.addBig(this.maxHeightSlider);
        optionsList.addBig(this.minHeightSlider);
        optionsList.addBig(dimensionSelectTypeFixOptionInstance);
        addCategory("Temperature", this.worldParam.getTemperature());
        addCategory("Vegetation", this.worldParam.getVegatatio());
        addCategory("Continets", this.worldParam.getContinets());
        addCategory("Erosion", this.worldParam.getErosion());
        addCategory("Ridge", this.worldParam.getRidges());
        addCaveSettings();
        addAdvancedSettings();
        addStructureSettings();
        LinearLayout linearlayout1 = this.layout.addToFooter(LinearLayout.horizontal().spacing(4));
        linearlayout1.addChild(Button.builder(CommonComponents.GUI_DONE, p_325363_ -> {
            this.applySettings.accept(new WorldParam(
                    this.generalSizeSlider.get(),
                    this.seaLevelSlider.get(),
                    this.maxHeightSlider.get(),
                    this.minHeightSlider.get(),
                    this.worldParam.getTemperature(),
                    this.worldParam.getVegatatio(),
                    this.worldParam.getErosion(),
                    this.worldParam.getContinets(),
                    this.worldParam.getRidges(),
                    this.dimensionSelectType,
                    this.worldParam.getCaveSettings(),
                    this.worldParam.getCaveGeneratorSettings(),
                    this.worldParam.getAdvancedSettings()
            ).setStructureSettings(this.worldParam.getStructureSettings()));
            this.onClose();
        }).width(120).build());

        linearlayout1.addChild(Button.builder(CommonComponents.GUI_CANCEL, p_325364_ -> this.onClose()).width(120).build());
        linearlayout1.addChild(Button.builder(Component.literal("Reset"), p_325365_ -> {
            optionsList.reset();
        }).tooltip(Tooltip.create(Component.literal("Reset to default settings"))).width(120).build());
        this.layout.visitWidgets(this::addRenderableWidget);
        this.repositionElements();
    }

    private void addCaveSettings() {
        AbstractWidget abstractWidget = new StringWidget(Component.literal("Cave settings"), font);
        abstractWidget.setWidth(width / 2 + 85);
        optionsList.addSmall(abstractWidget, null);
        FixOptionInstance<Integer> optionInstance = new FixOptionInstance<Integer>(
                "lava level",
                FixOptionInstance.noTooltip(),
                Options::genericValueOrOffLabel,
                new FixOptionInstance.IntRange(-256, 40),
                Codec.intRange(-256, 40),
                worldParam.getCaveSettings().getLavaLevel(),
                worldParam.getCaveSettings()::setLavaLevel,
                () -> {
                    RegistryAccess registryAccess = worldCreationContext.worldgenLoadContext();
                    Registry<DimensionType> dimensionTypes = registryAccess.registryOrThrow(Registries.DIMENSION_TYPE);
                    Holder<DimensionType> holder = dimensionTypes.getHolderOrThrow(dimensionSelectType.getKey_resource());
                    int min = holder.value().minY() + 6;
                    double norMin = (double) (min + 256) / (40 + 256);
                    return norMin;
                },
                () -> {
                    int sea = worldParam.getSea_level() - 6;
                    double norMax = (double) (sea + 256) / (40 + 256);
                    return Mth.clamp(norMax, 0, 1);
                }
        );
        FixOptionInstance<Integer> optionInstance1 = new FixOptionInstance<Integer>(
                "error for level lava",
                (p) -> Tooltip.create(Component.literal("not recommender to change")),
                Options::genericValueOrOffLabel,
                new FixOptionInstance.IntRange(0, 16),
                Codec.intRange(0, 16),
                worldParam.getCaveSettings().getForceOfErrorForChunk(),
                worldParam.getCaveSettings()::setForceOfErrorForChunk,
                null,
                null
        );
        optionsList.addBig(optionInstance);
        optionsList.addBig(optionInstance1);
        AbstractWidget abstractWidget1 = new StringWidget(Component.literal("Cave carvers settings(Only players that have skills to change carves!!!)"), font);
        AbstractWidget abstractWidget2 = new StringWidget(Component.literal(" "), font);
        abstractWidget.setWidth(width / 2 + 85);
        abstractWidget2.setWidth(width / 2 + 85);
        optionsList.addSmall(abstractWidget1, null);
        optionsList.addSmall(abstractWidget2, null);
        FixOptionInstance<Integer> optionInstance2 = new FixOptionInstance<Integer>(
                "probability",
                FixOptionInstance.noTooltip(),
                Options::genericValueOrOffLabel,
                new FixOptionInstance.IntRange(0, 100),
                Codec.intRange(0, 100),
                (int) (worldParam.getCaveGeneratorSettings().probability() * 100),
                (k) -> {
                    worldParam.setCaveGeneratorSettings(worldParam.getCaveGeneratorSettings().with_probability(k * 0.01f));
                },
                null,
                null
        );
        FixOptionInstance<Integer> optionInstance3 = new FixOptionInstance<Integer>(
                "min Y",
                FixOptionInstance.noTooltip(),
                Options::genericValueOrOffLabel,
                new FixOptionInstance.IntRange(0, 300),
                Codec.intRange(0, 300),
                (int) worldParam.getCaveGeneratorSettings().minY(),
                (p) -> worldParam.setCaveGeneratorSettings(worldParam.getCaveGeneratorSettings().with_minY(p)),
                () -> 0D,
                () -> {
                    double minNir = (double) worldParam.getCaveGeneratorSettings().maxY() / 300;
                    return minNir - 0.1f;
                }
        );
        FixOptionInstance<Integer> optionInstance4 = new FixOptionInstance<Integer>(
                "max Y",
                FixOptionInstance.noTooltip(),
                Options::genericValueOrOffLabel,
                new FixOptionInstance.IntRange(0, 300),
                Codec.intRange(0, 300),
                (int) worldParam.getCaveGeneratorSettings().maxY(),
                p -> worldParam.setCaveGeneratorSettings(worldParam.getCaveGeneratorSettings().with_maxY(p)),
                () -> {
                    double minNir = (double) worldParam.getCaveGeneratorSettings().minY() / 300;
                    return minNir + 0.1f;
                },
                () -> 1D
        );
        FixOptionInstance<Integer> optionInstance5 = new FixOptionInstance<Integer>(
                "min Scale Y",
                FixOptionInstance.noTooltip(),
                Options::genericValueOrOffLabel,
                new FixOptionInstance.IntRange(0, 20),
                Codec.intRange(0, 20),
                (int) (worldParam.getCaveGeneratorSettings().minScaleY() * 10),
                (p) -> {
                    worldParam.setCaveGeneratorSettings(worldParam.getCaveGeneratorSettings().with_minScaleY(p * 0.1f));
                },
                () -> 0D,
                () -> {
                    double minNir = (double) worldParam.getCaveGeneratorSettings().maxScaleY() / 20;
                    return minNir - 0.05f;
                }
        );
        FixOptionInstance<Integer> optionInstance6 = new FixOptionInstance<Integer>(
                "max Scale Y",
                FixOptionInstance.noTooltip(),
                Options::genericValueOrOffLabel,
                new FixOptionInstance.IntRange(0, 20),
                Codec.intRange(0, 20),
                (int) (worldParam.getCaveGeneratorSettings().maxScaleY() * 10),
                (p) -> {
                    worldParam.setCaveGeneratorSettings(worldParam.getCaveGeneratorSettings().with_maxScaleY(p * 0.1f));
                },
                () -> {
                    double minNir = (double) worldParam.getCaveGeneratorSettings().minScaleY() / 20;
                    return minNir + 0.05f;
                },
                () -> 1D

        );
        FixOptionInstance<Integer> optionInstance7 = new FixOptionInstance<Integer>(
                "min Horizontal radius multiplyer",
                FixOptionInstance.noTooltip(),
                Options::genericValueOrOffLabel,
                new FixOptionInstance.IntRange(0, 30),
                Codec.intRange(0, 30),
                (int) (worldParam.getCaveGeneratorSettings().horizontalRadiusMultiplierMin() * 10),
                (p) -> {
                    worldParam.setCaveGeneratorSettings(worldParam.getCaveGeneratorSettings().with_horizontalRadiusMultiplierMin(p * 0.1f));
                },
                () -> 0D,
                () -> {
                    double minNir = (double) worldParam.getCaveGeneratorSettings().horizontalRadiusMultiplierMax() / 30;
                    return minNir - 0.1f;
                }
        );
        FixOptionInstance<Integer> optionInstance8 = new FixOptionInstance<Integer>(
                "max Horizontal radius multiplyer",
                FixOptionInstance.noTooltip(),
                Options::genericValueOrOffLabel,
                new FixOptionInstance.IntRange(0, 30),
                Codec.intRange(0, 30),
                (int) (worldParam.getCaveGeneratorSettings().horizontalRadiusMultiplierMax() * 10),
                (p) -> {
                    worldParam.setCaveGeneratorSettings(worldParam.getCaveGeneratorSettings().with_horizontalRadiusMultiplierMax(p * 0.1f));
                },
                () -> {
                    double minNir = (double) worldParam.getCaveGeneratorSettings().horizontalRadiusMultiplierMin() / 30;
                    return minNir + 0.1f;
                },
                () -> 1D

        );
        FixOptionInstance<Integer> optionInstance9 = new FixOptionInstance<Integer>(
                "min Vertical radius multiplyer",
                FixOptionInstance.noTooltip(),
                Options::genericValueOrOffLabel,
                new FixOptionInstance.IntRange(0, 30),
                Codec.intRange(0, 30),
                (int) (worldParam.getCaveGeneratorSettings().verticalRadiusMultiplierMin() * 10),
                (p) -> {
                    worldParam.setCaveGeneratorSettings(worldParam.getCaveGeneratorSettings().with_verticalRadiusMultiplierMin(p * 0.1f));
                },
                () -> 0D,
                () -> {
                    double minNir = (double) worldParam.getCaveGeneratorSettings().verticalRadiusMultiplierMax() / 30;
                    return minNir - 0.1f;
                }
        );
        FixOptionInstance<Integer> optionInstance10 = new FixOptionInstance<Integer>(
                "max Vertical radius multiplyer",
                FixOptionInstance.noTooltip(),
                Options::genericValueOrOffLabel,
                new FixOptionInstance.IntRange(0, 30),
                Codec.intRange(0, 30),
                (int) (worldParam.getCaveGeneratorSettings().verticalRadiusMultiplierMax() * 10),
                (p) -> {
                    worldParam.setCaveGeneratorSettings(worldParam.getCaveGeneratorSettings().with_verticalRadiusMultiplierMax(p * 0.1f));
                },
                () -> {
                    double minNir = (double) worldParam.getCaveGeneratorSettings().verticalRadiusMultiplierMin() / 30;
                    return minNir + 0.1f;
                },
                () -> 1D

        );
        FixOptionInstance<Integer> optionInstance11 = new FixOptionInstance<Integer>(
                "min floor",
                FixOptionInstance.noTooltip(),
                Options::genericValueOrOffLabel,
                new FixOptionInstance.IntRange(-10, 5),
                Codec.intRange(-10, 5),
                (int) (worldParam.getCaveGeneratorSettings().floorLevelMin() * 10),
                (p) -> {
                    worldParam.setCaveGeneratorSettings(worldParam.getCaveGeneratorSettings().with_floorLevelMin(p * 0.1f));
                },
                () -> 0D,
                () -> {
                    double minNir = (double) (worldParam.getCaveGeneratorSettings().floorLevelMax() + 10) / 15;
                    return minNir - 0.1f;
                }
        );
        FixOptionInstance<Integer> optionInstance12 = new FixOptionInstance<Integer>(
                "max floor",
                FixOptionInstance.noTooltip(),
                Options::genericValueOrOffLabel,
                new FixOptionInstance.IntRange(-10, 5),
                Codec.intRange(-10, 5),
                (int) (worldParam.getCaveGeneratorSettings().floorLevelMax() * 10),
                (p) -> {
                    worldParam.setCaveGeneratorSettings(worldParam.getCaveGeneratorSettings().with_floorLevelMax(p * 0.1f));
                },
                () -> {
                    double minNir = (double) (worldParam.getCaveGeneratorSettings().floorLevelMin() + 10) / 15;
                    return minNir + 0.1f;
                },
                () -> 1D

        );
        FixOptionInstance<Integer> optionInstance13 = new FixOptionInstance<Integer>(
                "absolte Extreme Y",
                FixOptionInstance.noTooltip(),
                Options::genericValueOrOffLabel,
                new FixOptionInstance.IntRange(0, 300),
                Codec.intRange(0, 300),
                (int) worldParam.getCaveGeneratorSettings().maxYForExtreme(),
                p -> {
                    worldParam.setCaveGeneratorSettings(worldParam.getCaveGeneratorSettings().with_maxYForExtreme(p));
                },
                () -> {
                    double minNir = (double) worldParam.getCaveGeneratorSettings().minY() / 300;
                    return minNir + 0.1f;
                },
                () -> 1D
        );
        optionsList.addBig(optionInstance2);
        optionsList.addSmall(optionInstance3, optionInstance4);
        optionsList.addSmall(optionInstance5, optionInstance6);
        optionsList.addSmall(optionInstance7, optionInstance8);
        optionsList.addSmall(optionInstance9, optionInstance10);
        optionsList.addSmall(optionInstance11, optionInstance12);
        optionsList.addBig(optionInstance13);
    }

    private void addCategory(String title, WorldParam.Param param) {
        AbstractWidget abstractWidget = new StringWidget(Component.literal(title + " settings"), font);
        abstractWidget.setWidth(width / 2 + 85);
        optionsList.addSmall(abstractWidget, null);
        FixOptionInstance<Integer> optionInstance = new FixOptionInstance<Integer>(
                title + " noise size multiply",
                FixOptionInstance.noTooltip(),
                Options::genericValueOrOffLabel,
                new FixOptionInstance.IntRange(0, 20),
                Codec.intRange(0, 20),
                param.getSize(),
                param::setSize,
                null,
                null
        );
        FixOptionInstance<Integer> optionInstance2 = new FixOptionInstance<Integer>(
                title + " amplitude strength",
                FixOptionInstance.noTooltip(),
                Options::genericValueOrOffLabel,
                new FixOptionInstance.IntRange(0, 100),
                Codec.intRange(0, 200),
                (int) (param.getAmplitude() * 10),
                p -> param.setAmplitude((float) p / 10),
                null,
                null
        );
        FixOptionInstance<Integer> optionInstance11 = new FixOptionInstance<Integer>(
                title + " density size",
                FixOptionInstance.noTooltip(),
                Options::genericValueOrOffLabel,
                new FixOptionInstance.IntRange(0, 1000),
                Codec.intRange(0, 1000),
                param.getDensity_size(),
                param::setDensity_size,
                null,
                null
        );
        FixOptionInstance<Integer> optionInstance12 = new FixOptionInstance<Integer>(
                title + " amplitude distortion",
                FixOptionInstance.noTooltip(),
                Options::genericValueOrOffLabel,
                new FixOptionInstance.IntRange(0, 100),
                Codec.intRange(0, 100),
                param.getAmplitude_distortion(),
                param::setAmplitude_distortion,
                null,
                null
        );
        FixOptionInstance<Integer> optionInstance13 = new FixOptionInstance<Integer>(
                title + " amplitude distortion strength",
                FixOptionInstance.noTooltip(),
                Options::genericValueOrOffLabel,
                new FixOptionInstance.IntRange(0, 40),
                Codec.intRange(0, 40),
                param.getAmplitude_distortion_offset(),
                param::setAmplitude_distortion_offset,
                null,
                null
        );
        optionsList.addBig(optionInstance11);
        optionsList.addBig(optionInstance);
        optionsList.addBig(optionInstance2);
        optionsList.addBig(optionInstance12);
        optionsList.addBig(optionInstance13);
        if (param instanceof WorldParam.TemperatureParam temperatureParam) {
            FixOptionInstance<Integer> optionInstance3 = new FixOptionInstance<Integer>(
                    "max temperature ",
                    FixOptionInstance.noTooltip(),
                    (p, k) -> {
                        if (k < temperatureParam.getMin() * 10) {
                            return Options.genericValueLabel(Component.literal("ERRORS"), p);
                        }
                        return Options.genericValueOrOffLabel(p, k);
                    },
                    new FixOptionInstance.IntRange(-10, 30),
                    Codec.intRange(-10, 30),
                    (int) temperatureParam.getMax() * 10,
                    p -> temperatureParam.setMax((float) p / 10),
                    () -> (double) ((temperatureParam.getMin() + 1) / 4) + 0.1f,
                    () -> 1D
            ).setDragOff((v) -> {
                System.err.println("Max = " + v);
            });
            FixOptionInstance<Integer> optionInstance4 = new FixOptionInstance<Integer>(
                    "min temperature ",
                    FixOptionInstance.noTooltip(),
                    (p, k) -> {
                        if (k > temperatureParam.getMax() * 10) {
                            return Options.genericValueLabel(Component.literal("ERRORS"), p);
                        }
                        return Options.genericValueOrOffLabel(p, k);
                    },
                    new FixOptionInstance.IntRange(-10, 30),
                    Codec.intRange(-10, 30),
                    (int) temperatureParam.getMin() * 10,
                    p -> temperatureParam.setMin((float) p / 10),
                    () -> {
                        return 0D;
                    },
                    () -> {
                        double norMax = ((temperatureParam.getMax() + 1) / 4);
                        return norMax - 0.1f;
                    }
            ).setDragOff((v) -> {
                //doneButton.active = !(v < 0.5);
                System.err.println("Min = " + v);
            });
            FixOptionInstance<Integer> optionInstance5 = new FixOptionInstance<Integer>(
                    "min humid ",
                    FixOptionInstance.noTooltip(),
                    (p, k) -> {
                        if (k > temperatureParam.getMax_humid() * 10) {
                            return Options.genericValueLabel(Component.literal("ERRORS"), p);
                        }
                        return Options.genericValueOrOffLabel(p, k);
                    },
                    new FixOptionInstance.IntRange(0, 10),
                    Codec.intRange(0, 10),
                    (int) temperatureParam.getMin_humid() * 10,
                    p -> temperatureParam.setMin_humid(Mth.clamp((float) p / 10, 0, temperatureParam.getMax_humid() - 0.1f)),
                    () -> {
                        return 0D;
                    },
                    () -> (double) temperatureParam.getMax_humid() - 0.1f
            );
            FixOptionInstance<Integer> optionInstance6 = new FixOptionInstance<>(
                    "max humid ",
                    FixOptionInstance.noTooltip(),
                    (p, k) -> {
                        if (k < temperatureParam.getMin_humid() * 10) {
                            return Options.genericValueLabel(Component.literal("ERRORS"), p);
                        }
                        return Options.genericValueOrOffLabel(p, k);
                    },
                    new FixOptionInstance.IntRange(0, 10),
                    Codec.intRange(0, 10),
                    (int) temperatureParam.getMax_humid() * 10,
                    p -> temperatureParam.setMax_humid(Mth.clamp((float) p / 10, temperatureParam.getMin_humid() + 0.1f, 1)),
                    () -> (double) temperatureParam.getMin_humid() + 0.1f,
                    () -> 1D
            );
            optionsList.addSmall(optionInstance3, optionInstance4);
            optionsList.addSmall(optionInstance5, optionInstance6);
        }
        //temperatureLayout.addChild(optionInstance.createButton(this.minecraft.options));
        //temperatureLayout.addChild(optionInstance2.createButton(this.minecraft.options));
        //layout.addChild(temperatureLayout);
    }

    public void addStructureSettings() {
        optionsList.addSmall(new StringWidget(Component.literal("Structure Settings"), font), null);
        for (Map.Entry<String, NoisesGenerateSettings.StructureSettings> entry : worldParam.getStructureSettings().entrySet()) {
            optionsList.addSmall(new StringWidget(Component.literal(entry.getKey()), font), null);
            if (entry.getValue().structurePlacement() instanceof RandomSpreadStructurePlacement ran_placement) {
                FixOptionInstance<Integer> optionInstance = new FixOptionInstance<>(
                        entry.getKey() + " spacing",
                        FixOptionInstance.noTooltip(),
                        Options::genericValueOrOffLabel,
                        new FixOptionInstance.IntRange(0, 1000),
                        Codec.intRange(0, 1000),
                        ran_placement.spacing(),
                        p -> {
                            System.err.println("UP");
                            NoisesGenerateSettings.StructureSettings settings = worldParam.getStructureSettings().get(entry.getKey());
                            if (settings.structurePlacement() instanceof RandomSpreadStructurePlacement r) {
                                System.err.println("SPACING = " + r.spacing());

                                EntryPont entryPont = CreateCustomSettingsForWorldScreen.getDataStructurePlacement(r);
                                settings.setStructurePlacement(new RandomSpreadStructurePlacement(entryPont.location(), entryPont.frequencyReductionMethod(), entryPont.frequency(), entryPont.salt, entryPont.exclusionZone(), p, r.separation(), r.spreadType()));
                            }
                        },
                        () -> {
                            RandomSpreadStructurePlacement r = (RandomSpreadStructurePlacement) worldParam.getStructureSettings().get(entry.getKey()).structurePlacement();
                            return ((double) r.separation() / 1000D) + 0.001D;
                        },
                        () -> 1D
                );
                FixOptionInstance<Integer> optionInstance2 = new FixOptionInstance<>(
                        entry.getKey() + " separation",
                        FixOptionInstance.noTooltip(),
                        Options::genericValueOrOffLabel,
                        new FixOptionInstance.IntRange(0, 1000),
                        Codec.intRange(0, 1000),
                        ran_placement.separation(),
                        p -> {
                            NoisesGenerateSettings.StructureSettings settings = worldParam.getStructureSettings().get(entry.getKey());
                            RandomSpreadStructurePlacement structurePlacement = (RandomSpreadStructurePlacement) settings.structurePlacement();
                            EntryPont entryPont = CreateCustomSettingsForWorldScreen.getDataStructurePlacement(structurePlacement);
                            settings.setStructurePlacement(new RandomSpreadStructurePlacement(entryPont.location(), entryPont.frequencyReductionMethod(), entryPont.frequency(), entryPont.salt, entryPont.exclusionZone(), structurePlacement.spacing(), p, structurePlacement.spreadType()));
                        },
                        () -> 0D,
                        () -> {
                            RandomSpreadStructurePlacement r = (RandomSpreadStructurePlacement) worldParam.getStructureSettings().get(entry.getKey()).structurePlacement();
                            return ((double) r.spacing() / 1000D) - 0.001D;
                        }
                );
                optionsList.addBig(optionInstance);
                optionsList.addBig(optionInstance2);


            } else if (entry.getValue().structurePlacement() instanceof ConcentricRingsStructurePlacement con_placement) {
                FixOptionInstance<Integer> optionInstance = new FixOptionInstance<>(
                        entry.getKey() + " distance",
                        FixOptionInstance.noTooltip(),
                        Options::genericValueOrOffLabel,
                        new FixOptionInstance.IntRange(0, 2000),
                        Codec.intRange(0, 2000),
                        con_placement.distance(),
                        p -> {
                            NoisesGenerateSettings.StructureSettings settings = worldParam.getStructureSettings().get(entry.getKey());
                            ConcentricRingsStructurePlacement structurePlacement = (ConcentricRingsStructurePlacement) settings.structurePlacement();
                            EntryPont entryPont = CreateCustomSettingsForWorldScreen.getDataStructurePlacement(structurePlacement);
                            settings.setStructurePlacement(new ConcentricRingsStructurePlacement(entryPont.location, entryPont.frequencyReductionMethod(), entryPont.frequency(), entryPont.salt, entryPont.exclusionZone(), p, structurePlacement.spread(), structurePlacement.count(), structurePlacement.preferredBiomes()));
                        },
                        null,
                        null
                );
                FixOptionInstance<Integer> optionInstance1 = new FixOptionInstance<>(
                        entry.getKey() + " spread",
                        FixOptionInstance.noTooltip(),
                        Options::genericValueOrOffLabel,
                        new FixOptionInstance.IntRange(0, 2000),
                        Codec.intRange(0, 2000),
                        con_placement.spread(),
                        p -> {
                            NoisesGenerateSettings.StructureSettings settings = worldParam.getStructureSettings().get(entry.getKey());
                            ConcentricRingsStructurePlacement structurePlacement = (ConcentricRingsStructurePlacement) settings.structurePlacement();
                            EntryPont entryPont = CreateCustomSettingsForWorldScreen.getDataStructurePlacement(structurePlacement);
                            settings.setStructurePlacement(new ConcentricRingsStructurePlacement(entryPont.location, entryPont.frequencyReductionMethod(), entryPont.frequency(), entryPont.salt, entryPont.exclusionZone(), structurePlacement.distance(), p, structurePlacement.count(), structurePlacement.preferredBiomes()));
                        },
                        null,
                        null
                );
                FixOptionInstance<Integer> optionInstance2 = new FixOptionInstance<>(
                        entry.getKey() + " count",
                        FixOptionInstance.noTooltip(),
                        Options::genericValueOrOffLabel,
                        new FixOptionInstance.IntRange(0, 6000),
                        Codec.intRange(0, 6000),
                        con_placement.count(),
                        p -> {
                            NoisesGenerateSettings.StructureSettings settings = worldParam.getStructureSettings().get(entry.getKey());
                            ConcentricRingsStructurePlacement structurePlacement = (ConcentricRingsStructurePlacement) settings.structurePlacement();
                            EntryPont entryPont = CreateCustomSettingsForWorldScreen.getDataStructurePlacement(structurePlacement);
                            settings.setStructurePlacement(new ConcentricRingsStructurePlacement(entryPont.location, entryPont.frequencyReductionMethod(), entryPont.frequency(), entryPont.salt, entryPont.exclusionZone(), structurePlacement.distance(), structurePlacement.spread(), p, structurePlacement.preferredBiomes()));
                        },
                        null,
                        null
                );
                optionsList.addBig(optionInstance);
                optionsList.addBig(optionInstance1);
                optionsList.addBig(optionInstance2);
            }
            FixOptionInstance<Boolean> optionInstance = FixOptionInstance.createBoolean("spawn allow", entry.getValue().allowSpawn(), p -> worldParam.getStructureSettings().get(entry.getKey()).setAllowSpawn(p));
            optionsList.addBig(optionInstance);
        }
    }

    public void addAdvancedSettings() {
        optionsList.addSmall(new StringWidget(Component.literal("Advanced Settings (Only Expert player!!!)"), font), null);
        addGeneralNoiseSettings();
        addBlendingSettings();
        addSliderSettings();
        addEntrySettings();
        addNoodleSettings();
        addPeaksAndValleysSettings();
        addSpageti2DSettings();
        addSpageti2DRoughnessSettings();
        addUndergroundSettings();
        addAquiferSettings();

    }
    private void addAquiferSettings() {
        optionsList.addSmall(new StringWidget(Component.literal("Aquifer Settings"), font), null);
        FixOptionInstance<Integer> aquifer_barrier = new FixOptionInstance<>(
                "Aquifer Barrier",
                FixOptionInstance.noTooltip(),
                Options::genericValueOrOffLabel,
                new FixOptionInstance.IntRange(-100, 500), // Преобразуем диапазон float в int диапазон
                Codec.intRange(-100, 100),
                (int) (worldParam.getAdvancedSettings().getAqualifierSettings().getAquifer_barrier() * 100), // Преобразуем float в int
                p -> {
                    worldParam.getAdvancedSettings().getAqualifierSettings().setAquifer_barrier(p / 100.0F); // Преобразуем int обратно в float
                },
                null,
                null
        );

        FixOptionInstance<Integer> aquifer_fluid_level_floodness = new FixOptionInstance<>(
                "Aquifer Fluid Level Floodness",
                FixOptionInstance.noTooltip(),
                Options::genericValueOrOffLabel,
                new FixOptionInstance.IntRange(-100, 500), // Преобразуем диапазон float в int диапазон
                Codec.intRange(-100, 500),
                (int) (worldParam.getAdvancedSettings().getAqualifierSettings().getAquifer_fluid_level_floodness() * 100), // Преобразуем float в int
                p -> {
                    worldParam.getAdvancedSettings().getAqualifierSettings().setAquifer_fluid_level_floodness(p / 100.0F); // Преобразуем int обратно в float
                },
                null,
                null
        );

        FixOptionInstance<Integer> aquifer_fluid_level_spread = new FixOptionInstance<>(
                "Aquifer Fluid Level Spread",
                FixOptionInstance.noTooltip(),
                Options::genericValueOrOffLabel,
                new FixOptionInstance.IntRange(-100, 500), // Преобразуем диапазон float в int диапазон
                Codec.intRange(-100, 500),
                (int) (worldParam.getAdvancedSettings().getAqualifierSettings().getAquifer_fluid_level_spread() * 100), // Преобразуем float в int
                p -> {
                    worldParam.getAdvancedSettings().getAqualifierSettings().setAquifer_fluid_level_spread(p / 100.0F); // Преобразуем int обратно в float
                },
                null,
                null
        );

        optionsList.addBig(aquifer_barrier);
        optionsList.addBig(aquifer_fluid_level_floodness);
        optionsList.addBig(aquifer_fluid_level_spread);

    }
    private void addSpageti2DRoughnessSettings() {
        optionsList.addSmall(new StringWidget(Component.literal("Spageti 2D Roughness Settings"), font), null);
        FixOptionInstance<Integer> scalingFactor = new FixOptionInstance<>(
                "scaling Factor",
                FixOptionInstance.noTooltip(),
                Options::genericValueOrOffLabel,
                new FixOptionInstance.IntRange(-300, 0),
                Codec.intRange(-300, 0),
                (int)(worldParam.getAdvancedSettings().getUndergroundSettings().getCaveLayerFrequency() * 100),
                p -> {
                    worldParam.getAdvancedSettings().getUndergroundSettings().setCaveLayerFrequency((float)p * 0.01F);
                },
                null,
                null
        );

        optionsList.addBig(scalingFactor);
    }
    private void addUndergroundSettings() {
        optionsList.addSmall(new StringWidget(Component.literal("Underground Settings"), font), null);
        FixOptionInstance<Integer> cave_layer_frequency = new FixOptionInstance<>(
                "Cave Layer Frequency",
                FixOptionInstance.noTooltip(),
                Options::genericValueOrOffLabel,
                new FixOptionInstance.IntRange(1, 1000),
                Codec.intRange(1, 1000),
                (int)(worldParam.getAdvancedSettings().getUndergroundSettings().getCaveLayerFrequency() * 10),
                p -> {
                    worldParam.getAdvancedSettings().getUndergroundSettings().setCaveLayerFrequency((float)p * 0.1F);
                },
                null,
                null
        );

        FixOptionInstance<Integer> cave_cheese_factor = new FixOptionInstance<>(
                "Cave Cheese Factor",
                FixOptionInstance.noTooltip(),
                Options::genericValueOrOffLabel,
                new FixOptionInstance.IntRange(-400, 400),
                Codec.intRange(-400, 400),
                (int)(worldParam.getAdvancedSettings().getUndergroundSettings().getCaveCheeseFactor() * 100),
                p -> {
                    worldParam.getAdvancedSettings().getUndergroundSettings().setCaveCheeseFactor((float)p * 0.01F);
                },
                null,
                null
        );

        FixOptionInstance<Integer> pillar_density = new FixOptionInstance<>(
                "Pillar Density",
                FixOptionInstance.noTooltip(),
                Options::genericValueOrOffLabel,
                new FixOptionInstance.IntRange(0, 100),
                Codec.intRange(0, 100),
                (int)(worldParam.getAdvancedSettings().getUndergroundSettings().getPillarDensity() * 100),
                p -> {
                    worldParam.getAdvancedSettings().getUndergroundSettings().setPillarDensity((float)p * 0.01F);
                },
                null,
                null
        );
        optionsList.addBig(cave_layer_frequency);
        optionsList.addBig(cave_cheese_factor);
        optionsList.addBig(pillar_density);

    }
    private void addSpageti2DSettings() {
        optionsList.addSmall(new StringWidget(Component.literal("Spageti 2D Settings"), font), null);
        FixOptionInstance<Integer> spaghetti2d_frequency = new FixOptionInstance<>(
                "Spaghetti 2D Frequency",
                FixOptionInstance.noTooltip(),
                Options::genericValueOrOffLabel,
                new FixOptionInstance.IntRange(1, 200),
                Codec.intRange(1, 200),
                (int)(worldParam.getAdvancedSettings().getSpaghetti2DSettings().getSpaghetti2DFrequency() * 10),
                p -> {
                    worldParam.getAdvancedSettings().getSpaghetti2DSettings().setSpaghetti2DFrequency((float)p * 0.1F);
                },
                null,
                null
        );

        FixOptionInstance<Integer> spaghetti2d_thickness = new FixOptionInstance<>(
                "Spaghetti 2D Thickness",
                FixOptionInstance.noTooltip(),
                Options::genericValueOrOffLabel,
                new FixOptionInstance.IntRange(-400, 400),
                Codec.intRange(-400, 400),
                (int)(worldParam.getAdvancedSettings().getSpaghetti2DSettings().getSpaghetti2DThickness() * 1000),
                p -> {
                    worldParam.getAdvancedSettings().getSpaghetti2DSettings().setSpaghetti2DThickness((float)p * 0.001F);
                },
                null,
                null
        );
        optionsList.addBig(spaghetti2d_frequency);
        optionsList.addBig(spaghetti2d_thickness);

    }
    private void addPeaksAndValleysSettings() {
        optionsList.addSmall(new StringWidget(Component.literal("Peaks and Valleys Settings"), font), null);
        FixOptionInstance<Integer> abs_value_adjustment = new FixOptionInstance<>(
                "Absolute Value Adjustment",
                FixOptionInstance.noTooltip(),
                Options::genericValueOrOffLabel,
                new FixOptionInstance.IntRange(-500, 0),
                Codec.intRange(-500, 0),
                (int)(worldParam.getAdvancedSettings().getPeaksAndValleysSettings().getAbsValueAdjustment() * 100),
                p -> {
                    worldParam.getAdvancedSettings().getPeaksAndValleysSettings().setAbsValueAdjustment((float)p * 0.01F);
                },
                null,
                null
        );

        FixOptionInstance<Integer> final_adjustment = new FixOptionInstance<>(
                "Final Adjustment",
                FixOptionInstance.noTooltip(),
                Options::genericValueOrOffLabel,
                new FixOptionInstance.IntRange(-400, 0),
                Codec.intRange(-400, 0),
                (int)(worldParam.getAdvancedSettings().getPeaksAndValleysSettings().getFinalAdjustment() * 100),
                p -> {
                    worldParam.getAdvancedSettings().getPeaksAndValleysSettings().setFinalAdjustment((float)p * 0.01F);
                },
                null,
                null
        );

        FixOptionInstance<Integer> scaling_factor = new FixOptionInstance<>(
                "Scaling Factor",
                FixOptionInstance.noTooltip(),
                Options::genericValueOrOffLabel,
                new FixOptionInstance.IntRange(-100, 0),
                Codec.intRange(-100, 0),
                (int)(worldParam.getAdvancedSettings().getPeaksAndValleysSettings().getScalingFactor() * 10),
                p -> {
                    worldParam.getAdvancedSettings().getPeaksAndValleysSettings().setScalingFactor((float)p * 0.1F);
                },
                null,
                null
        );

        optionsList.addBig(abs_value_adjustment);
        optionsList.addBig(final_adjustment);
        optionsList.addBig(scaling_factor);

    }
    private void addNoodleSettings() {
        optionsList.addSmall(new StringWidget(Component.literal("Noodle Settings"), font), null);
        FixOptionInstance<Integer> noodle_frequency = new FixOptionInstance<>(
                "Noodle Frequency",
                FixOptionInstance.noTooltip(),
                Options::genericValueOrOffLabel,
                new FixOptionInstance.IntRange(0, 500),
                Codec.intRange(0, 500),
                (int)(worldParam.getAdvancedSettings().getNoodleSettings().getNoodleFrequency() * 100),
                p -> {
                    worldParam.getAdvancedSettings().getNoodleSettings().setNoodleFrequency((float)p * 0.01F);
                },
                null,
                null
        );

        FixOptionInstance<Integer> noodle_thickness_offset_1 = new FixOptionInstance<>(
                "Noodle Thickness Offset 1",
                FixOptionInstance.noTooltip(),
                Options::genericValueOrOffLabel,
                new FixOptionInstance.IntRange(-500, 500),
                Codec.intRange(-500, 500),
                (int)(worldParam.getAdvancedSettings().getNoodleSettings().getNoodleThicknessOffset1() * 100),
                p -> {
                    worldParam.getAdvancedSettings().getNoodleSettings().setNoodleThicknessOffset1((float)p * 0.01F);
                },
                null,
                null
        );

        FixOptionInstance<Integer> noodle_thickness_offset_2 = new FixOptionInstance<>(
                "Noodle Thickness Offset 2",
                FixOptionInstance.noTooltip(),
                Options::genericValueOrOffLabel,
                new FixOptionInstance.IntRange(-500, 500),
                Codec.intRange(-500, 500),
                (int)(worldParam.getAdvancedSettings().getNoodleSettings().getNoodleThicknessOffset2() * 100),
                p -> {
                    worldParam.getAdvancedSettings().getNoodleSettings().setNoodleThicknessOffset2((float)p * 0.01F);
                },
                null,
                null
        );

        FixOptionInstance<Integer> ridge_frequency = new FixOptionInstance<>(
                "Ridge Frequency",
                FixOptionInstance.noTooltip(),
                Options::genericValueOrOffLabel,
                new FixOptionInstance.IntRange(0, 800),
                Codec.intRange(0, 500),
                (int)(worldParam.getAdvancedSettings().getNoodleSettings().getRidgeFrequency() * 100),
                p -> {
                    worldParam.getAdvancedSettings().getNoodleSettings().setRidgeFrequency((float)p * 0.01F);
                },
                null,
                null
        );

        optionsList.addBig(noodle_frequency);
        optionsList.addBig(noodle_thickness_offset_1);
        optionsList.addBig(noodle_thickness_offset_2);
        optionsList.addBig(ridge_frequency);
    }
    private void addEntrySettings() {
        optionsList.addSmall(new StringWidget(Component.literal("Entrance Settings"), font), null);

        FixOptionInstance<Integer> spaghetti_thickness_offset_1 = new FixOptionInstance<>(
                "Spaghetti Thickness Offset 1",
                FixOptionInstance.noTooltip(),
                Options::genericValueOrOffLabel,
                new FixOptionInstance.IntRange(-400, 200),
                Codec.intRange(-400, 200),
                (int)(worldParam.getAdvancedSettings().getEnranceSettings().getSpaghettiThicknessOffset1() * 100),
                p -> {
                    worldParam.getAdvancedSettings().getEnranceSettings().setSpaghettiThicknessOffset1((float)p * 0.01F);
                },
                null,
                null
        );

        FixOptionInstance<Integer> spaghetti_thickness_offset_2 = new FixOptionInstance<>(
                "Spaghetti Thickness Offset 2",
                FixOptionInstance.noTooltip(),
                Options::genericValueOrOffLabel,
                new FixOptionInstance.IntRange(-200, 200),
                Codec.intRange(-400, 200),
                (int)(worldParam.getAdvancedSettings().getEnranceSettings().getSpaghettiThicknessOffset2() * 100),
                p -> {
                    worldParam.getAdvancedSettings().getEnranceSettings().setSpaghettiThicknessOffset2((float)p * 0.01F);
                },
                null,
                null
        );

        FixOptionInstance<Integer> cave_entrance_frequency = new FixOptionInstance<>(
                "Cave Entrance Frequency",
                FixOptionInstance.noTooltip(),
                Options::genericValueOrOffLabel,
                new FixOptionInstance.IntRange(0, 500),
                Codec.intRange(0, 500),
                (int)(worldParam.getAdvancedSettings().getEnranceSettings().getCaveEntranceFrequency() * 100),
                p -> {
                    worldParam.getAdvancedSettings().getEnranceSettings().setCaveEntranceFrequency((float)p * 0.01F);
                },
                null,
                null
        );

        FixOptionInstance<Integer> cave_entrance_amplitude = new FixOptionInstance<>(
                "Cave Entrance Amplitude",
                FixOptionInstance.noTooltip(),
                Options::genericValueOrOffLabel,
                new FixOptionInstance.IntRange(0, 500),
                Codec.intRange(0, 500),
                (int)(worldParam.getAdvancedSettings().getEnranceSettings().getCaveEntranceAmplitude() * 100),
                p -> {
                    worldParam.getAdvancedSettings().getEnranceSettings().setCaveEntranceAmplitude((float)p * 0.01F);
                },
                null,
                null
        );

        FixOptionInstance<Integer> cave_entrance_multiplier = new FixOptionInstance<>(
                "Cave Entrance Multiplier",
                FixOptionInstance.noTooltip(),
                Options::genericValueOrOffLabel,
                new FixOptionInstance.IntRange(0, 1000),
                Codec.intRange(0, 50),
                (int)(worldParam.getAdvancedSettings().getEnranceSettings().getCaveEntranceMULTIPLIER()),
                p -> {
                    worldParam.getAdvancedSettings().getEnranceSettings().setCaveEntranceMULTIPLIER((float)p);
                },
                null,
                null
        );

        optionsList.addBig(spaghetti_thickness_offset_1);
        optionsList.addBig(spaghetti_thickness_offset_2);
        optionsList.addBig(cave_entrance_frequency);
        optionsList.addBig(cave_entrance_amplitude);
        optionsList.addBig(cave_entrance_multiplier);
    }
    private void addGeneralNoiseSettings() {
        optionsList.addSmall(new StringWidget(Component.literal("Geneneral Noise Settings"), font), null);
        FixOptionInstance<Integer> global_offset = new FixOptionInstance<>(
                "global offset",
                FixOptionInstance.noTooltip(),
                Options::genericValueOrOffLabel,
                new FixOptionInstance.IntRange(-200, 200),
                Codec.intRange(-200, 200),
                (int)(worldParam.getAdvancedSettings().getGeneralNoiseSettings().getGlobalOffset() * 100),
                p -> {
                    worldParam.getAdvancedSettings().getGeneralNoiseSettings().setGlobalOffset((p * 0.01F));
                },
                null,
                null
        );
        FixOptionInstance<Integer> ore_thickness = new FixOptionInstance<>(
                "ore thickness",
                FixOptionInstance.noTooltip(),
                Options::genericValueOrOffLabel,
                new FixOptionInstance.IntRange(0, 100),
                Codec.intRange(0, 100),
                (int)(worldParam.getAdvancedSettings().getGeneralNoiseSettings().getOre_thickness() * 100),
                p -> {
                    worldParam.getAdvancedSettings().getGeneralNoiseSettings().setOre_thickness((float)p * 0.01F);
                },
                null,
                null
        );

        FixOptionInstance<Integer> veininess_frequency = new FixOptionInstance<>(
                "veininess frequency",
                FixOptionInstance.noTooltip(),
                Options::genericValueOrOffLabel,
                new FixOptionInstance.IntRange(0, 1000),
                Codec.intRange(0, 1000),
                (int)(worldParam.getAdvancedSettings().getGeneralNoiseSettings().getVeininess_frequency() * 100),
                p -> {
                    worldParam.getAdvancedSettings().getGeneralNoiseSettings().setVeininess_frequency((float)p * 0.01F);
                },
                null,
                null
        );

        FixOptionInstance<Integer> surface_density_threshold = new FixOptionInstance<>(
                "surface density threshold",
                FixOptionInstance.noTooltip(),
                Options::genericValueOrOffLabel,
                new FixOptionInstance.IntRange(0, 1000),
                Codec.intRange(0, 1000),
                (int)(worldParam.getAdvancedSettings().getGeneralNoiseSettings().getSurface_density_threshold() * 100),
                p -> {
                    worldParam.getAdvancedSettings().getGeneralNoiseSettings().setSurface_density_threshold((float)p * 0.01F);
                },
                null,
                null
        );

        FixOptionInstance<Integer> cheese_noise_target = new FixOptionInstance<>(
                "cheese noise target",
                FixOptionInstance.noTooltip(),
                Options::genericValueOrOffLabel,
                new FixOptionInstance.IntRange(-1000, 1000),
                Codec.intRange(-200, 200),
                (int)(worldParam.getAdvancedSettings().getGeneralNoiseSettings().getCheese_noise_target() * 100),
                p -> {
                    worldParam.getAdvancedSettings().getGeneralNoiseSettings().setCheese_noise_target((float)p * 0.01F);
                },
                null,
                null
        );

        FixOptionInstance<Integer> cheese_noise_clamp_factor = new FixOptionInstance<>(
                "cheese noise clamp factor",
                FixOptionInstance.noTooltip(),
                Options::genericValueOrOffLabel,
                new FixOptionInstance.IntRange(256, 256),
                Codec.intRange(256, 256),
                (int)(worldParam.getAdvancedSettings().getGeneralNoiseSettings().getCheese_noise_clamp_factor()),
                p -> {
                    worldParam.getAdvancedSettings().getGeneralNoiseSettings().setCheese_noise_clamp_factor(p);
                },
                null,
                null
        );

        FixOptionInstance<Integer> depth_min_amplitude = new FixOptionInstance<>(
                "depth min amplitude",
                FixOptionInstance.noTooltip(),
                Options::genericValueOrOffLabel,
                new FixOptionInstance.IntRange(-10, -500),
                Codec.intRange(-10, -500),
                (int)(worldParam.getAdvancedSettings().getGeneralNoiseSettings().getDepth_min_ampitude() * 100),
                p -> {
                    worldParam.getAdvancedSettings().getGeneralNoiseSettings().setDepth_min_ampitude((float)p * 0.01F);
                },
                null,
                null
        );

        FixOptionInstance<Integer> depth_max_amplitude = new FixOptionInstance<>(
                "depth max amplitude",
                FixOptionInstance.noTooltip(),
                Options::genericValueOrOffLabel,
                new FixOptionInstance.IntRange(10, 500),
                Codec.intRange(10, 500),
                (int)(worldParam.getAdvancedSettings().getGeneralNoiseSettings().getDepth_max_ampitude() * 100),
                p -> {
                    worldParam.getAdvancedSettings().getGeneralNoiseSettings().setDepth_max_ampitude((float)p * 0.01F);
                },
                null,
                null
        );

        FixOptionInstance<Integer> blending_factor = new FixOptionInstance<>(
                "blending factor",
                FixOptionInstance.noTooltip(),
                Options::genericValueOrOffLabel,
                new FixOptionInstance.IntRange(0, 120),
                Codec.intRange(0, 120),
                (int)(worldParam.getAdvancedSettings().getGeneralNoiseSettings().getBlending_factor()),
                p -> {
                    worldParam.getAdvancedSettings().getGeneralNoiseSettings().setBlending_factor((float)p);
                },
                null,
                null
        );

        FixOptionInstance<Integer> juggedness_factor = new FixOptionInstance<>(
                "juggedness factor",
                FixOptionInstance.noTooltip(),
                Options::genericValueOrOffLabel,
                new FixOptionInstance.IntRange(0, 120),
                Codec.intRange(0, 120),
                (int)(worldParam.getAdvancedSettings().getGeneralNoiseSettings().getJuggedness_factor()),
                p -> {
                    worldParam.getAdvancedSettings().getGeneralNoiseSettings().setJuggedness_factor((float)p);
                },
                null,
                null
        );

        FixOptionInstance<Integer> juggedness_xzScale = new FixOptionInstance<>(
                "juggedness xz scale",
                FixOptionInstance.noTooltip(),
                Options::genericValueOrOffLabel,
                new FixOptionInstance.IntRange(0, 800),
                Codec.intRange(0, 800),
                (int)(worldParam.getAdvancedSettings().getGeneralNoiseSettings().getJuggedness_xzScale() * 0.1f),
                p -> {
                    worldParam.getAdvancedSettings().getGeneralNoiseSettings().setJuggedness_xzScale((float)p * 10f);
                },
                null,
                null
        );
        optionsList.addBig(global_offset);
        optionsList.addBig(ore_thickness);
        optionsList.addBig(veininess_frequency);
        optionsList.addBig(surface_density_threshold);
        optionsList.addBig(cheese_noise_target);
        optionsList.addBig(cheese_noise_clamp_factor);
        optionsList.addSmall(depth_min_amplitude,depth_max_amplitude);
        optionsList.addBig(blending_factor);
        optionsList.addBig(juggedness_factor);
        optionsList.addBig(juggedness_xzScale);
    }
    private void addBlendingSettings() {
        optionsList.addSmall(new StringWidget(Component.literal("Blending Noise Settings"), font), null);
        FixOptionInstance<Integer> xz_scale = new FixOptionInstance<>(
                "XZ Scale",
                FixOptionInstance.noTooltip(),
                Options::genericValueOrOffLabel,
                new FixOptionInstance.IntRange(0, 500),
                Codec.intRange(0, 500),
                (int)(worldParam.getAdvancedSettings().getBlendingNoisesSettings().getXzScale() * 100),
                p -> {
                    worldParam.getAdvancedSettings().getBlendingNoisesSettings().setXzScale((double)p * 0.01);
                },
                null,
                null
        );

        FixOptionInstance<Integer> y_scale = new FixOptionInstance<>(
                "Y Scale",
                FixOptionInstance.noTooltip(),
                Options::genericValueOrOffLabel,
                new FixOptionInstance.IntRange(0, 500),
                Codec.intRange(0, 500),
                (int)(worldParam.getAdvancedSettings().getBlendingNoisesSettings().getYScale() * 100),
                p -> {
                    worldParam.getAdvancedSettings().getBlendingNoisesSettings().setyScale((double)p * 0.01);
                },
                null,
                null
        );

        FixOptionInstance<Integer> xz_factor = new FixOptionInstance<>(
                "XZ Factor",
                FixOptionInstance.noTooltip(),
                Options::genericValueOrOffLabel,
                new FixOptionInstance.IntRange(0, 2000),
                Codec.intRange(0, 2000),
                (int)(worldParam.getAdvancedSettings().getBlendingNoisesSettings().getXzFactor()),
                p -> {
                    worldParam.getAdvancedSettings().getBlendingNoisesSettings().setXzFactor((double)p);
                },
                null,
                null
        );

        FixOptionInstance<Integer> y_factor = new FixOptionInstance<>(
                "Y Factor",
                FixOptionInstance.noTooltip(),
                Options::genericValueOrOffLabel,
                new FixOptionInstance.IntRange(0, 2000),
                Codec.intRange(0, 2000),
                (int)(worldParam.getAdvancedSettings().getBlendingNoisesSettings().getYFactor()),
                p -> {
                    worldParam.getAdvancedSettings().getBlendingNoisesSettings().setYFactor((double)p);
                },
                null,
                null
        );

        FixOptionInstance<Integer> smear_scale_multiplier = new FixOptionInstance<>(
                "Smear Scale Multiplier",
                FixOptionInstance.noTooltip(),
                Options::genericValueOrOffLabel,
                new FixOptionInstance.IntRange(0, 200),
                Codec.intRange(0, 200),
                (int)(worldParam.getAdvancedSettings().getBlendingNoisesSettings().getSmearScaleMultiplier()),
                p -> {
                    worldParam.getAdvancedSettings().getBlendingNoisesSettings().setSmearScaleMultiplier((double)p);
                },
                null,
                null
        );

        optionsList.addSmall(xz_scale,y_scale);
        optionsList.addSmall(xz_factor,y_factor);
        optionsList.addBig(smear_scale_multiplier);

    }
    private void addSliderSettings() {
        optionsList.addSmall(new StringWidget(Component.literal("Slider Noise Settings"), font), null);
        FixOptionInstance<Integer> vertical_shift_start = new FixOptionInstance<>(
                "Vertical Shift Start",
                FixOptionInstance.noTooltip(),
                Options::genericValueOrOffLabel,
                new FixOptionInstance.IntRange(-250, 250),
                Codec.intRange(-250, 250),
                (int)worldParam.getAdvancedSettings().getSlideSettings().getVerticalShiftStart(),
                p -> {
                    worldParam.getAdvancedSettings().getSlideSettings().setVerticalShiftStart((float)p);
                },
                () -> {
                    double norMax = (worldParam.getAdvancedSettings().getSlideSettings().getVerticalShiftEnd() + 250) / 500;
                    return norMax + 0.1f;
                },
                () -> 1D
        );

        FixOptionInstance<Integer> vertical_shift_end = new FixOptionInstance<>(
                "Vertical Shift End",
                FixOptionInstance.noTooltip(),
                Options::genericValueOrOffLabel,
                new FixOptionInstance.IntRange(-1000, 1000),
                Codec.intRange(-250, 250),
                (int)worldParam.getAdvancedSettings().getSlideSettings().getVerticalShiftEnd(),
                p -> {
                    worldParam.getAdvancedSettings().getSlideSettings().setVerticalShiftEnd((float)p);
                },
                () -> 0D,
                () -> {
                    double norMax = (worldParam.getAdvancedSettings().getSlideSettings().getVerticalShiftStart() + 250) / 500;
                    return norMax - 0.1f;
                }
        );

        FixOptionInstance<Integer> shift_intensity = new FixOptionInstance<>(
                "Shift Intensity",
                FixOptionInstance.noTooltip(),
                Options::genericValueOrOffLabel,
                new FixOptionInstance.IntRange(-1000, 1000),
                Codec.intRange(-1000, 1000),
                (int)(worldParam.getAdvancedSettings().getSlideSettings().getShiftIntensity() * 1000),
                p -> {
                    worldParam.getAdvancedSettings().getSlideSettings().setShiftIntensity((float)p * 0.001F);
                },
                null,
                null
        );

        FixOptionInstance<Integer> final_gradient_start = new FixOptionInstance<>(
                "Final Gradient Start",
                FixOptionInstance.noTooltip(),
                Options::genericValueOrOffLabel,
                new FixOptionInstance.IntRange(-900, 900),
                Codec.intRange(-900, 900),
                (int)(worldParam.getAdvancedSettings().getSlideSettings().getFinalGradientStart() * 10),
                p -> {
                    worldParam.getAdvancedSettings().getSlideSettings().setFinalGradientStart((float)p * 0.1F);
                },
                () -> 0D,
                () -> {
                    double norMax = (worldParam.getAdvancedSettings().getSlideSettings().getFinalGradientEnd() + 900) / 1800;
                    return norMax - 0.1f;
                }
        );

        FixOptionInstance<Integer> final_gradient_end = new FixOptionInstance<>(
                "Final Gradient End",
                FixOptionInstance.noTooltip(),
                Options::genericValueOrOffLabel,
                new FixOptionInstance.IntRange(-900, 900),
                Codec.intRange(-900, 900),
                (int)(worldParam.getAdvancedSettings().getSlideSettings().getFinalGradientEnd() * 10),
                p -> {
                    worldParam.getAdvancedSettings().getSlideSettings().setFinalGradientEnd((float)p * 0.1F);
                },
                () -> {
                    double norMax = (worldParam.getAdvancedSettings().getSlideSettings().getFinalGradientStart() + 900) / 1800;
                    return norMax + 0.1f;
                },
                () -> 1D
        );

        FixOptionInstance<Integer> final_interpolation_factor = new FixOptionInstance<>(
                "Final Interpolation Factor",
                FixOptionInstance.noTooltip(),
                Options::genericValueOrOffLabel,
                new FixOptionInstance.IntRange(-500, 500),
                Codec.intRange(-500, 500),
                (int)(worldParam.getAdvancedSettings().getSlideSettings().getFinalInterpolationFactor() * 100),
                p -> {
                    worldParam.getAdvancedSettings().getSlideSettings().setFinalInterpolationFactor((float)p * 0.01F);
                },
                null,
                null
        );

        optionsList.addBig(vertical_shift_start);
        optionsList.addBig(vertical_shift_end);
        optionsList.addBig(shift_intensity);
        optionsList.addBig(final_gradient_start);
        optionsList.addBig(final_gradient_end);
        optionsList.addBig(final_interpolation_factor);

    }


    @Override
    protected void repositionElements() {
        this.layout.arrangeElements();
        this.optionsList.updateSize(this.width, this.layout);
    }

    protected void addTitle() {
        this.layout.addTitleHeader(this.title, this.font);
    }

    protected void addContents() {
        this.optionsList = this.layout.addToContents(new OpList(this.minecraft, this.width, this));
    }

    private static EntryPont getDataStructurePlacement(StructurePlacement structurePlacement) {
        try {
            Method locationMethod = StructurePlacement.class.getDeclaredMethod("locateOffset");
            locationMethod.setAccessible(true);
            Vec3i location = (Vec3i) locationMethod.invoke(structurePlacement);
            Method frequencyReductionMethod = StructurePlacement.class.getDeclaredMethod("frequencyReductionMethod");
            frequencyReductionMethod.setAccessible(true);
            StructurePlacement.FrequencyReductionMethod frequencyReduction = (StructurePlacement.FrequencyReductionMethod) frequencyReductionMethod.invoke(structurePlacement);
            Method frequencyMethod = StructurePlacement.class.getDeclaredMethod("frequency");
            frequencyMethod.setAccessible(true);
            float frequency = (float) frequencyMethod.invoke(structurePlacement);
            Method saltMethod = StructurePlacement.class.getDeclaredMethod("salt");
            saltMethod.setAccessible(true);
            int salt = (int) saltMethod.invoke(structurePlacement);
            Method exclusionZoneMethod = StructurePlacement.class.getDeclaredMethod("exclusionZone");
            exclusionZoneMethod.setAccessible(true);
            Optional<StructurePlacement.ExclusionZone> exclusionZone = (Optional<StructurePlacement.ExclusionZone>) exclusionZoneMethod.invoke(structurePlacement);
            return new EntryPont(location, frequencyReduction, frequency, salt, exclusionZone);
        } catch (Exception e) {
            throw new RuntimeException("WTF: " + e);
        }
    }

    record EntryPont(Vec3i location, StructurePlacement.FrequencyReductionMethod frequencyReductionMethod,
                     float frequency, int salt, Optional<StructurePlacement.ExclusionZone> exclusionZone) {
    }
}
