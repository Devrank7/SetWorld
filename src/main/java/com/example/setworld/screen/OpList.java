package com.example.setworld.screen;

import com.example.setworld.gui.FixOptionInstance;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import net.minecraft.client.Minecraft;
import net.minecraft.client.OptionInstance;
import net.minecraft.client.Options;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.components.ContainerObjectSelectionList;
import net.minecraft.client.gui.components.events.GuiEventListener;
import net.minecraft.client.gui.narration.NarratableEntry;
import net.minecraft.client.gui.screens.Screen;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class OpList extends ContainerObjectSelectionList<OpList.Entry> {
    private static final int BIG_BUTTON_WIDTH = 310;
    private static final int DEFAULT_ITEM_HEIGHT = 25;
    private final CreateCustomSettingsForWorldScreen screen;

    public OpList(Minecraft p_94465_, int p_94466_, CreateCustomSettingsForWorldScreen p_342734_) {
        super(p_94465_, p_94466_, p_342734_.layout.getContentHeight(), p_342734_.layout.getHeaderHeight(), 25);
        this.centerListVertically = false;
        this.screen = p_342734_;
    }

    public void addBig(FixOptionInstance<?> p_232529_) {
        this.addEntry(OpList.OptionEntry.big(this.minecraft.options, p_232529_, this.screen));
    }

    public void addSmall(FixOptionInstance<?>... p_232534_) {
        for (int i = 0; i < p_232534_.length; i += 2) {
            FixOptionInstance<?> optioninstance = i < p_232534_.length - 1 ? p_232534_[i + 1] : null;
            this.addEntry(OpList.OptionEntry.small(this.minecraft.options, p_232534_[i], optioninstance, this.screen));
        }
    }

    public void addSmall(List<AbstractWidget> p_334237_) {
        for (int i = 0; i < p_334237_.size(); i += 2) {
            this.addSmall(p_334237_.get(i), i < p_334237_.size() - 1 ? p_334237_.get(i + 1) : null);
        }
    }

    public void addSmall(AbstractWidget p_330860_, @Nullable AbstractWidget p_333864_) {
        this.addEntry(OpList.Entry.small(p_330860_, p_333864_, this.screen));
    }

    @Override
    public int getRowWidth() {
        return 310;
    }

    @Nullable
    public AbstractWidget findOption(OptionInstance<?> p_232536_) {
        for (OpList.Entry OpList$entry : this.children()) {
            if (OpList$entry instanceof OpList.OptionEntry OpList$optionentry) {
                AbstractWidget abstractwidget = OpList$optionentry.options.get(p_232536_);
                if (abstractwidget != null) {
                    return abstractwidget;
                }
            }
        }

        return null;
    }
    public void reset() {
        List<AbstractWidget> list = new ArrayList<>();
        this.children().forEach(v -> {
            if (v instanceof OpList.OptionEntry OpList$optionentry) {
                list.addAll(OpList$optionentry.options.values().stream().filter(wid -> wid instanceof FixOptionInstance.FixOptionInstanceSliderButton).toList());
            }
        });
        list.forEach(v -> {
            FixOptionInstance.FixOptionInstanceSliderButton<?> fixOptionInstanceSliderButton = (FixOptionInstance.FixOptionInstanceSliderButton<?>)v;
            fixOptionInstanceSliderButton.applyDefault();
        });
    }
    public List<FixOptionInstance<?>> findAll() {
        List<FixOptionInstance<?>> list = new ArrayList<>();
        this.children().forEach(v -> {
            if (v instanceof OpList.OptionEntry OpList$optionentry) {
                list.addAll(OpList$optionentry.options.keySet());
            }
        });
        return list;
    }

    public void applyUnsavedChanges() {
        for (OpList.Entry OpList$entry : this.children()) {
            if (OpList$entry instanceof OpList.OptionEntry) {
                OpList.OptionEntry OpList$optionentry = (OpList.OptionEntry)OpList$entry;

                for (AbstractWidget abstractwidget : OpList$optionentry.options.values()) {
                    if (abstractwidget instanceof FixOptionInstance.FixOptionInstanceSliderButton<?> optioninstancesliderbutton) {
                        optioninstancesliderbutton.applyUnsavedValue();
                    }
                }
            }
        }
    }

    public Optional<GuiEventListener> getMouseOver(double p_94481_, double p_94482_) {
        for (OpList.Entry OpList$entry : this.children()) {
            for (GuiEventListener guieventlistener : OpList$entry.children()) {
                if (guieventlistener.isMouseOver(p_94481_, p_94482_)) {
                    return Optional.of(guieventlistener);
                }
            }
        }

        return Optional.empty();
    }
    @Override
    protected void renderListItems(GuiGraphics p_282079_, int p_239229_, int p_239230_, float p_239231_) {
        int i = this.getRowLeft();
        int j = this.getRowWidth();
        int k = this.itemHeight - 4;
        int l = this.getItemCount();

        for (int i1 = 0; i1 < l; i1++) {
            int j1 = this.getRowTop(i1) ;
            int k1 = this.getRowBottom(i1) ;
            if (k1 >= this.getY() && j1 <= this.getBottom()) {
                this.renderItem(p_282079_, p_239229_, p_239230_, p_239231_, i1, i, j1, j, k);
            }
        }
    }

    @OnlyIn(Dist.CLIENT)
    protected static class Entry extends ContainerObjectSelectionList.Entry<OpList.Entry> {
        private final List<AbstractWidget> children;
        private final Screen screen;
        private static final int X_OFFSET = 160;

        Entry(List<AbstractWidget> p_328739_, Screen p_332963_) {
            this.children = ImmutableList.copyOf(p_328739_);
            this.screen = p_332963_;
        }

        public static OpList.Entry big(List<AbstractWidget> p_331607_, Screen p_332678_) {
            return new OpList.Entry(p_331607_, p_332678_);
        }

        public static OpList.Entry small(AbstractWidget p_332778_, @Nullable AbstractWidget p_330638_, Screen p_328012_) {
            return p_330638_ == null
                    ? new OpList.Entry(ImmutableList.of(p_332778_), p_328012_)
                    : new OpList.Entry(ImmutableList.of(p_332778_, p_330638_), p_328012_);
        }

        @Override
        public void render(
                GuiGraphics p_281311_,
                int p_94497_,
                int p_94498_,
                int p_94499_,
                int p_94500_,
                int p_94501_,
                int p_94502_,
                int p_94503_,
                boolean p_94504_,
                float p_94505_
        ) {
            int i = 0;
            int j = this.screen.width / 2 - 155;

            for (AbstractWidget abstractwidget : this.children) {
                abstractwidget.setPosition(j + i, p_94498_);
                abstractwidget.render(p_281311_, p_94502_, p_94503_, p_94505_);
                i += 160;
            }
        }

        @Override
        public List<? extends GuiEventListener> children() {
            return this.children;
        }

        @Override
        public List<? extends NarratableEntry> narratables() {
            return this.children;
        }
    }

    @OnlyIn(Dist.CLIENT)
    protected static class OptionEntry extends OpList.Entry {
        final Map<FixOptionInstance<?>, AbstractWidget> options;

        private OptionEntry(Map<FixOptionInstance<?>, AbstractWidget> p_331348_, CreateCustomSettingsForWorldScreen p_345262_) {
            super(ImmutableList.copyOf(p_331348_.values()), p_345262_);
            this.options = p_331348_;
        }

        public static OpList.OptionEntry big(Options p_335438_, FixOptionInstance<?> p_329713_, CreateCustomSettingsForWorldScreen p_342690_) {
            return new OpList.OptionEntry(ImmutableMap.of(p_329713_, p_329713_.createButton(p_335438_, 0, 0, 310)), p_342690_);
        }

        public static OpList.OptionEntry small(
                Options p_330617_, FixOptionInstance<?> p_330233_, @Nullable FixOptionInstance<?> p_331704_, CreateCustomSettingsForWorldScreen p_342280_
        ) {
            AbstractWidget abstractwidget = p_330233_.createButton(p_330617_);
            return p_331704_ == null
                    ? new OpList.OptionEntry(ImmutableMap.of(p_330233_, abstractwidget), p_342280_)
                    : new OpList.OptionEntry(ImmutableMap.of(p_330233_, abstractwidget, p_331704_, p_331704_.createButton(p_330617_)), p_342280_);
        }
    }
}
