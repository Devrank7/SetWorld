package com.example.setworld.mixins.screen;

import com.example.setworld.DataPackReloadCookie;
import com.example.setworld.register.RegisterWorldPresets;
import com.example.setworld.tabs.GamesTab;
import com.example.setworld.tabs.MoresTab;
import com.example.setworld.tabs.WorldsTab;
import net.minecraft.Util;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.tabs.TabManager;
import net.minecraft.client.gui.components.tabs.TabNavigationBar;
import net.minecraft.client.gui.layouts.HeaderAndFooterLayout;
import net.minecraft.client.gui.layouts.LinearLayout;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.worldselection.CreateWorldScreen;
import net.minecraft.client.gui.screens.worldselection.WorldCreationContext;
import net.minecraft.client.gui.screens.worldselection.WorldCreationUiState;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.server.WorldLoader;
import net.minecraft.server.packs.repository.PackRepository;
import net.minecraft.server.packs.repository.ServerPacksSource;
import net.minecraft.world.level.WorldDataConfiguration;
import net.minecraft.world.level.levelgen.WorldGenSettings;
import net.minecraft.world.level.levelgen.WorldOptions;
import net.minecraft.world.level.levelgen.presets.WorldPresets;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import javax.annotation.Nullable;
import java.lang.reflect.Constructor;
import java.util.Optional;
import java.util.OptionalLong;
import java.util.concurrent.CompletableFuture;

@Mixin(CreateWorldScreen.class)
public abstract class MixCreateWorldScreen extends Screen{

    public MixCreateWorldScreen(Component p_96550_) {
        super(p_96550_);
    }

    @Shadow @Final private static Component PREPARING_WORLD_DATA;

    @Shadow protected static void queueLoadScreen(Minecraft p_232900_, Component p_232901_){}

    @Shadow protected static WorldLoader.InitConfig createDefaultLoadConfig(PackRepository p_251829_, WorldDataConfiguration p_251555_){return null;}
    @Shadow(remap = false)
    private TabNavigationBar tabNavigationBar;
    @Final
    @Shadow(remap = false)
    private TabManager tabManager;

    @Unique
    public int width = 427;
    @Final
    @Shadow(remap = false)
    WorldCreationUiState uiState;

    @Shadow(remap = false)
    protected abstract void onCreate();

    @Shadow(remap = false)
    public abstract void popScreen();

    @Shadow(remap = false)
    public abstract void repositionElements();
    @Shadow(remap = false)
    @Final
    private HeaderAndFooterLayout layout;

    @Shadow protected abstract void setInitialFocus();

    /**
     * @author
     * @reason
     */
    @Overwrite
    public static void openFresh(Minecraft p_232897_, @Nullable Screen p_232898_) {
        queueLoadScreen(p_232897_, PREPARING_WORLD_DATA);
        PackRepository packrepository = new PackRepository(new ServerPacksSource(p_232897_.directoryValidator()));
        net.minecraftforge.event.ForgeEventFactory.addPackFindersServer(packrepository::addPackFinder);
        WorldLoader.InitConfig worldloader$initconfig = createDefaultLoadConfig(packrepository, WorldDataConfiguration.DEFAULT);
        CompletableFuture<WorldCreationContext> completablefuture = WorldLoader.load(
                worldloader$initconfig,
                p_247792_ -> new WorldLoader.DataLoadOutput<>(
                        new DataPackReloadCookie(
                                new WorldGenSettings(WorldOptions.defaultWithRandomSeed(), WorldPresets.createNormalWorldDimensions(p_247792_.datapackWorldgen())), p_247792_.dataConfiguration()
                        ),
                        p_247792_.datapackDimensions()
                ),
                (p_247798_, p_247799_, p_247800_, p_247801_) -> {
                    p_247798_.close();
                    return new WorldCreationContext(p_247801_.worldGenSettings(), p_247800_, p_247799_, p_247801_.dataConfiguration());
                },
                Util.backgroundExecutor(),
                p_232897_
        );
        //System.err.println("join =  " + completablefuture.join().worldgenRegistries().compositeAccess().registryOrThrow(Registries.WORLD_PRESET));
        p_232897_.managedBlock(completablefuture::isDone);
        try {
            Constructor<CreateWorldScreen> constructor = CreateWorldScreen.class.getDeclaredConstructor(
                    Minecraft.class,
                    Screen.class,
                    WorldCreationContext.class,
                    Optional.class,
                    OptionalLong.class
            );
            constructor.setAccessible(true); // Делаем конструктор доступным
            CreateWorldScreen screen = constructor.newInstance(
                    p_232897_,
                    p_232898_,
                    completablefuture.join(),
                    Optional.of(RegisterWorldPresets.SUPER_LARGE_BIOMES),
                    OptionalLong.empty()
            );
            p_232897_.setScreen(screen);
        } catch (Exception e) {
            throw new RuntimeException("Failed to create CreateWorldScreen", e);
        }
    }
    @Inject(at = @At("HEAD"), method = "init", cancellable = true)
    public void init(CallbackInfo ci) {
        ci.cancel();
        CreateWorldScreen screen = (CreateWorldScreen) (Object) this;
        this.tabNavigationBar = TabNavigationBar.builder(this.tabManager, this.width)
                .addTabs(new GamesTab(screen), new WorldsTab(screen), new MoresTab(screen))
                .build();
        this.addRenderableWidget(this.tabNavigationBar);
        LinearLayout linearlayout = this.layout.addToFooter(LinearLayout.horizontal().spacing(8));
        linearlayout.addChild(Button.builder(Component.translatable("selectWorld.create"), p_232938_ -> this.onCreate()).build());
        linearlayout.addChild(Button.builder(CommonComponents.GUI_CANCEL, p_232903_ -> this.popScreen()).build());
        this.layout.visitWidgets(p_267851_ -> {
            p_267851_.setTabOrderGroup(1);
            this.addRenderableWidget(p_267851_);
        });
        this.tabNavigationBar.selectTab(0, false);
        this.uiState.onChanged();
        this.repositionElements();
    }
}
