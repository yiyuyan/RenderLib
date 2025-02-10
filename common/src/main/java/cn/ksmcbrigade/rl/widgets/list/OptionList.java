package cn.ksmcbrigade.rl.widgets.list;

import cn.ksmcbrigade.rl.widgets.list.entry.AbstractWidgetEntry;
import cn.ksmcbrigade.rl.widgets.list.entry.ListEntry;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.ContainerObjectSelectionList;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class OptionList extends ContainerObjectSelectionList<ListEntry> {

    private int rowWidth = 330;
    public boolean render = true;

    public OptionList(int pWidth, int pHeight, int pY, int pItemHeight) {
        super(Minecraft.getInstance(), pWidth, pHeight, pY, pItemHeight);
        this.setRenderHeader(false,0);
    }

    @Override
    public int addEntry(@NotNull ListEntry pEntry) {
        return super.addEntry(pEntry);
    }

    @Override
    public void addEntryToTop(@NotNull ListEntry pEntry) {
        super.addEntryToTop(pEntry);
    }

    @Override
    public boolean removeEntry(@NotNull ListEntry pEntry) {
        return super.removeEntry(pEntry);
    }

    @Nullable
    @Override
    public ListEntry remove(int pIndex) {
        return super.remove(pIndex);
    }

    @Override
    public void clearEntries() {
        super.clearEntries();
    }

    @Override
    public int getRowWidth() {
        return rowWidth;
    }

    public void setRowWidth(int rowWidth) {
        this.rowWidth = rowWidth;
    }

    @Override
    public void renderWidget(@NotNull GuiGraphics pGuiGraphics, int pMouseX, int pMouseY, float pPartialTick) {
        if(this.render) super.renderWidget(pGuiGraphics, pMouseX, pMouseY, pPartialTick);
        for (ListEntry child : this.children()) {
            if(child instanceof AbstractWidgetEntry entry){
                entry.rendering = this.render;
            }
        }
    }

    @Override
    protected void renderHeader(@NotNull GuiGraphics pGuiGraphics, int pX, int pY) {
    }

    @Override
    protected void renderDecorations(@NotNull GuiGraphics pGuiGraphics, int pMouseX, int pMouseY) {
    }

    @Override
    protected void renderListBackground(@NotNull GuiGraphics pGuiGraphics) {
    }

    @Override
    protected void renderListSeparators(@NotNull GuiGraphics pGuiGraphics) {
    }

    @Override
    protected boolean scrollbarVisible() {
        return false;
    }
}
