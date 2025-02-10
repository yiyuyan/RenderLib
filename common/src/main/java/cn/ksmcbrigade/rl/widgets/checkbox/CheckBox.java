package cn.ksmcbrigade.rl.widgets.checkbox;

import cn.ksmcbrigade.rl.Color;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.OptionInstance;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.AbstractButton;
import net.minecraft.client.gui.components.MultiLineTextWidget;
import net.minecraft.client.gui.components.Tooltip;
import net.minecraft.client.gui.narration.NarratedElementType;
import net.minecraft.client.gui.narration.NarrationElementOutput;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import org.lwjgl.glfw.GLFW;

public class CheckBox extends AbstractButton {
    public static final ResourceLocation CHECKBOX_SELECTED_HIGHLIGHTED_SPRITE = ResourceLocation.withDefaultNamespace("widget/checkbox_selected_highlighted");
    public static final ResourceLocation CHECKBOX_SELECTED_SPRITE = ResourceLocation.withDefaultNamespace("widget/checkbox_selected");
    public static final ResourceLocation CHECKBOX_HIGHLIGHTED_SPRITE = ResourceLocation.withDefaultNamespace("widget/checkbox_highlighted");
    public static final ResourceLocation CHECKBOX_SPRITE = ResourceLocation.withDefaultNamespace("widget/checkbox");
    public static int TEXT_COLOR = 14737632;
    public static int SPACING = 4;
    public static int BOX_PADDING = 8;
    
    public boolean selected;
    public CheckBox.OnValueChange onValueChange;
    public MultiLineTextWidget textWidget;
    public Color backgroundColor;
    public int backgroundSize = 2;
    public boolean backgroundText = false,draggable = false,pressing = false;
    public int boxSize = 17;
    public OnDragged onDragged = (box,x,y)->{};

    CheckBox(int pX, int pY, int pMaxWidth, Component pMessage, Font pFont, boolean pSelected, CheckBox.OnValueChange pOnValueChange,Color backgroundColor) {
        super(pX, pY, 0, 0, pMessage);
        this.width = this.getAdjustedWidth(pMaxWidth, pMessage, pFont);
        this.textWidget = (new MultiLineTextWidget(pMessage, pFont)).setMaxWidth(this.width).setColor(14737632);
        this.height = this.getAdjustedHeight(pFont);
        this.selected = pSelected;
        this.onValueChange = pOnValueChange;
        this.backgroundColor = backgroundColor;
    }

    public int getAdjustedWidth(int pMaxWidth, Component pMessage, Font pFont) {
        return Math.min(getDefaultWidth(pMessage, pFont), pMaxWidth);
    }

    public int getAdjustedHeight(Font pFont) {
        return Math.max(getBoxSize(pFont), this.textWidget.getHeight());
    }

    static int getDefaultWidth(Component pMessage, Font pFont) {
        return getBoxSize(pFont) + 4 + pFont.width(pMessage);
    }

    public static CheckBox.Builder builder(Component pMessage, Font pFont,Color backgroundColor) {
        return new CheckBox.Builder(pMessage, pFont,backgroundColor);
    }

    public static int getBoxSize(Font pFont) {
        return 17;
    }

    public int getBoxSize(){
        return boxSize;
    }

    public void setBoxSize(int boxSize) {
        this.boxSize = boxSize;
    }

    public void onPress() {
        this.selected = !this.selected;
        this.onValueChange.onValueChange(this, this.selected);
    }

    public boolean selected() {
        return this.selected;
    }

    public void updateWidgetNarration(NarrationElementOutput pNarrationElementOutput) {
        pNarrationElementOutput.add(NarratedElementType.TITLE, this.createNarrationMessage());
        if (this.active) {
            if (this.isFocused()) {
                pNarrationElementOutput.add(NarratedElementType.USAGE, Component.translatable("narration.checkbox.usage.focused"));
            } else {
                pNarrationElementOutput.add(NarratedElementType.USAGE, Component.translatable("narration.checkbox.usage.hovered"));
            }
        }

    }

    @Override
    public boolean mouseDragged(double pMouseX, double pMouseY, int pButton, double pDragX, double pDragY) {
        if(this.draggable && this.pressing){
            this.setPosition((int) pMouseX, (int) pMouseY);
            this.onDragged.on(this,pMouseX,pMouseY);
        }
        return super.mouseDragged(pMouseX, pMouseY, pButton, pDragX, pDragY);
    }

    public void renderWidget(GuiGraphics pGuiGraphics, int pMouseX, int pMouseY, float pPartialTick) {
        this.pressing = GLFW.glfwGetMouseButton(Minecraft.getInstance().getWindow().getWindow(),GLFW.GLFW_MOUSE_BUTTON_1)==1;
        Minecraft minecraft = Minecraft.getInstance();
        RenderSystem.enableDepthTest();
        Font font = minecraft.font;
        pGuiGraphics.setColor(1.0F, 1.0F, 1.0F, this.alpha);
        RenderSystem.enableBlend();
        ResourceLocation resourcelocation;
        if (this.selected) {
            resourcelocation = this.isFocused() ? CHECKBOX_SELECTED_HIGHLIGHTED_SPRITE : CHECKBOX_SELECTED_SPRITE;
        } else {
            resourcelocation = this.isFocused() ? CHECKBOX_HIGHLIGHTED_SPRITE : CHECKBOX_SPRITE;
        }

        int i = getBoxSize(font);
        int w = this.textWidget.getWidth();
        pGuiGraphics.fill(this.getX()-backgroundSize,this.getY()-backgroundSize,this.getX()+i+backgroundSize+(!this.backgroundText?0:(this.backgroundSize>4?w:(4-this.backgroundSize+w))),this.getY()+this.height+backgroundSize,backgroundColor.toInt());
        pGuiGraphics.blitSprite(resourcelocation, this.getX(), this.getY(), i, i);

        int j = this.getX() + i + 4;
        int k = this.getY() + i / 2 - this.textWidget.getHeight() / 2;
        this.textWidget.setPosition(j, k);
        this.textWidget.renderWidget(pGuiGraphics, pMouseX, pMouseY, pPartialTick);
    }

    public interface OnValueChange {
        CheckBox.OnValueChange NOP = (p_309046_, p_309014_) -> {
        };

        void onValueChange(CheckBox var1, boolean var2);
    }

    public static class Builder {
        public final Component message;
        public final Font font;
        public int maxWidth;
        public int x = 0;
        public int y = 0;
        public Color backgroundColor;
        public int backgroundSize;
        public CheckBox.OnValueChange onValueChange;
        public boolean selected;
        public OptionInstance<Boolean> option;
        public Tooltip tooltip;
        public boolean backgroundText = false,draggable = false;
        public OnDragged onDragged = (box,x,y)->{};

        public Builder(Component pMessage, Font pFont,Color backgroundColor) {
            this.onValueChange = CheckBox.OnValueChange.NOP;
            this.selected = false;
            this.option = null;
            this.tooltip = null;
            this.message = pMessage;
            this.font = pFont;
            this.maxWidth = CheckBox.getDefaultWidth(pMessage, pFont);
            this.backgroundColor = backgroundColor;
        }

        public CheckBox.Builder pos(int pX, int pY) {
            this.x = pX;
            this.y = pY;
            return this;
        }

        public CheckBox.Builder onValueChange(CheckBox.OnValueChange pOnValueChange) {
            this.onValueChange = pOnValueChange;
            return this;
        }

        public CheckBox.Builder selected(boolean pSelected) {
            this.selected = pSelected;
            this.option = null;
            return this;
        }

        public CheckBox.Builder selected(OptionInstance<Boolean> pOption) {
            this.option = pOption;
            this.selected = pOption.get();
            return this;
        }

        public CheckBox.Builder tooltip(Tooltip pTooltip) {
            this.tooltip = pTooltip;
            return this;
        }

        public CheckBox.Builder onDragged(OnDragged onDragged){
            this.onDragged = onDragged;
            return this;
        }

        public CheckBox.Builder draggable(boolean draggable){
            this.draggable = draggable;
            return this;
        }

        public CheckBox.Builder draggable(){
            return this.draggable(!this.draggable);
        }

        public CheckBox.Builder backgroundText(boolean v) {
            this.backgroundText = v;
            return this;
        }

        public CheckBox.Builder maxWidth(int pMaxWidth) {
            this.maxWidth = pMaxWidth;
            return this;
        }

        public CheckBox.Builder backgroundSize(int size) {
            this.backgroundSize = size;
            return this;
        }

        public CheckBox build() {
            CheckBox.OnValueChange checkbox$onvaluechange = this.option == null ? this.onValueChange : (p_309064_, p_308939_) -> {
                this.option.set(p_308939_);
                this.onValueChange.onValueChange(p_309064_, p_308939_);
            };
            CheckBox checkbox = new CheckBox(this.x, this.y, this.maxWidth, this.message, this.font, this.selected, checkbox$onvaluechange,this.backgroundColor);
            checkbox.setTooltip(this.tooltip);
            checkbox.backgroundSize = this.backgroundSize;
            checkbox.backgroundText = this.backgroundText;
            checkbox.onDragged = this.onDragged;
            checkbox.draggable = this.draggable;
            return checkbox;
        }
    }
}
