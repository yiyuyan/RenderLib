package cn.ksmcbrigade.rl.widgets.list.entry;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.components.events.GuiEventListener;
import net.minecraft.client.gui.narration.NarratableEntry;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class AbstractWidgetEntry extends ListEntry{

    public final AbstractWidget widget;
    public boolean rendering = true;

    public AbstractWidgetEntry(AbstractWidget widget){
        this.widget = widget;
    }

    @Override
    void refreshEntry() {

    }

    @Override
    public @NotNull List<? extends NarratableEntry> narratables() {
        return List.of();
    }

    @Override
    public void render(@NotNull GuiGraphics guiGraphics, int i, int i1, int i2, int i3, int i4, int i5, int i6, boolean b, float v) {
        this.widget.setPosition(i2,i1-2);
        this.widget.render(guiGraphics,i5,i6,v);
    }

    @Override
    public @NotNull List<? extends GuiEventListener> children() {
        return rendering?List.of(this.widget):List.of();
    }
}
