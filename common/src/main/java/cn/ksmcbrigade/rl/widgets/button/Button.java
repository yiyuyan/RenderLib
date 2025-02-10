package cn.ksmcbrigade.rl.widgets.button;

import cn.ksmcbrigade.rl.Color;
import com.mojang.blaze3d.platform.InputConstants;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.AbstractButton;
import net.minecraft.client.gui.narration.NarrationElementOutput;
import net.minecraft.network.chat.Component;
import net.minecraft.util.Mth;
import org.jetbrains.annotations.NotNull;
import org.lwjgl.glfw.GLFW;

public class Button extends AbstractButton {

    public Color color;
    public Color hoveredOrFocusedColor;
    public Color outlineColor = new Color(255,255,255,1);
    public int activeColor = 16777215;
    public int notActiveColor = 10526880;
    public boolean draggable = false;
    public boolean pressing = false;
    public OnDragged onDragged = (b,x,y)->{};
    public OnPress onPress = (b)->{};

    public Button(int pX, int pY, int pWidth, int pHeight, Component pMessage, Color color,Color hoveredOrFocusedColor) {
        super(pX, pY, pWidth, pHeight, pMessage);
        this.color = color;
        this.hoveredOrFocusedColor = hoveredOrFocusedColor;
    }

    public Button(int pX, int pY, int pWidth, int pHeight, Component pMessage, Color color, Color hoveredOrFocusedColor, OnPress onPress) {
        this(pX, pY, pWidth, pHeight, pMessage, color, hoveredOrFocusedColor);
        this.onPress = onPress;
    }

    @Override
    public void onPress() {
        this.onPress.on(this);
    }

    @Override
    public boolean mouseDragged(double pMouseX, double pMouseY, int pButton, double pDragX, double pDragY) {
        if(this.draggable && this.pressing){
            this.setPosition((int) pMouseX, (int) pMouseY);
            this.onDragged.on(this,pMouseX,pMouseY);
        }
        return super.mouseDragged(pMouseX, pMouseY, pButton, pDragX, pDragY);
    }

    @Override
    protected void renderWidget(GuiGraphics pGuiGraphics, int pMouseX, int pMouseY, float pPartialTick) {
        this.pressing = GLFW.glfwGetMouseButton(Minecraft.getInstance().getWindow().getWindow(),InputConstants.MOUSE_BUTTON_LEFT)==1;
        Minecraft minecraft = Minecraft.getInstance();
        pGuiGraphics.setColor(1.0F, 1.0F, 1.0F, this.alpha);
        RenderSystem.enableBlend();
        RenderSystem.enableDepthTest();

        pGuiGraphics.fill(this.getX(),this.getY(),this.getX()+this.getWidth(),this.getY()+this.getHeight(),(isHoveredOrFocused()?hoveredOrFocusedColor:color).toInt());

        pGuiGraphics.renderOutline(this.getX(),this.getY(),this.width,this.height,outlineColor.toInt());

        pGuiGraphics.setColor(1.0F, 1.0F, 1.0F, 1.0F);
        int i = this.active ? activeColor : notActiveColor;
        this.renderString(pGuiGraphics, minecraft.font, i | Mth.ceil(this.alpha * 255.0F) << 24);
    }

    @Override
    protected void updateWidgetNarration(@NotNull NarrationElementOutput narrationElementOutput) {

    }
}
