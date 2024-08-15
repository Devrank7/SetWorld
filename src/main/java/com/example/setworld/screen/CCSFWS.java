package com.example.setworld.screen;

import com.example.setworld.world.WorldParam;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.Tooltip;
import net.minecraft.client.gui.layouts.HeaderAndFooterLayout;
import net.minecraft.client.gui.layouts.LinearLayout;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.network.chat.Component;

public abstract class CCSFWS extends Screen {
    private CreateCustomSettingsForWorldScreen main_screen;
    private Screen lastScreen;
    private WorldParam worldParam;
    private OpList opList;
    public final HeaderAndFooterLayout layout = new HeaderAndFooterLayout(this);
    public boolean hasNextPage = false;
    public boolean hasPreviousPage = false;

    public CCSFWS(Component p_96550_, CreateCustomSettingsForWorldScreen main_screen, Screen lastScreen, WorldParam worldParam) {
        super(p_96550_);
        this.main_screen = main_screen;
        this.lastScreen = lastScreen;
        this.worldParam = worldParam;
    }

    @Override
    protected void init() {
        super.init();
        this.addTitle();
        this.addContents();
        this.layout.visitWidgets(p_344531_ -> {
            AbstractWidget abstractwidget = this.addRenderableWidget(p_344531_);
        });
        LinearLayout linearlayout1 = this.layout.addToFooter(LinearLayout.horizontal().spacing(4));
        linearlayout1.addChild(Button.builder(CommonComponents.GUI_DONE, p_325363_ -> {
            this.main_screen.applySettings.accept(new WorldParam(
                    main_screen.generalSizeSlider.get(),
                    main_screen.seaLevelSlider.get(),
                    main_screen.maxHeightSlider.get(),
                    main_screen.minHeightSlider.get(),
                    this.worldParam.getTemperature(),
                    this.worldParam.getVegatatio(),
                    this.worldParam.getErosion(),
                    this.worldParam.getContinets(),
                    this.worldParam.getRidges(),
                    main_screen.dimensionSelectType,
                    this.worldParam.getCaveSettings(),
                    this.worldParam.getCaveGeneratorSettings(),
                    this.worldParam.getAdvancedSettings()
            ).setStructureSettings(this.worldParam.getStructureSettings()));
            this.onClose();
        }).width(120).build());

        linearlayout1.addChild(Button.builder(CommonComponents.GUI_CANCEL, p_325364_ -> this.onClose()).width(120).build());
        linearlayout1.addChild(Button.builder(Component.literal("Reset"), p_325365_ -> {
            opList.reset();
        }).tooltip(Tooltip.create(Component.literal("Reset to default settings"))).width(120).build());
    }

    @Override
    protected void repositionElements() {
        this.layout.arrangeElements();
        this.opList.updateSize(this.width, this.layout);
    }

    protected void addTitle() {
        this.layout.addTitleHeader(this.title, this.font);
    }

    protected void addContents() {
        //this.opList = this.layout.addToContents(new OpList(this.minecraft, this.width, this));
    }

    public Screen getLastScreen() {
        return lastScreen;
    }

    public void setLastScreen(Screen lastScreen) {
        this.lastScreen = lastScreen;
    }

    public WorldParam getWorldParam() {
        return worldParam;
    }

    public void setWorldParam(WorldParam worldParam) {
        this.worldParam = worldParam;
    }

    public CreateCustomSettingsForWorldScreen getMain_screen() {
        return main_screen;
    }

    public void setMain_screen(CreateCustomSettingsForWorldScreen main_screen) {
        this.main_screen = main_screen;
    }

    protected abstract void nextPage();

    protected abstract void previousPage();


    @Override
    public void onClose() {
        this.main_screen.onClose();
    }
}
