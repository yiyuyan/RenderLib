package cn.ksmcbrigade.rl.widgets.editbox;

import cn.ksmcbrigade.rl.Color;
import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import org.jetbrains.annotations.NotNull;
import org.lwjgl.glfw.GLFW;

public class EditBox extends net.minecraft.client.gui.components.EditBox {

    public Color bacgkroundColor;
    public int backgroundSize = 2;

    public boolean pressing = false;
    public boolean draggable = false;
    public OnDragged onDragged = (box,x,y)->{};

    public EditBox(Font pFont, int pWidth, int pHeight, Component pMessage,Color bacgkroundColor) {
        super(pFont, pWidth, pHeight, pMessage);
        this.bacgkroundColor = bacgkroundColor;
    }

    @Override
    public void renderWidget(@NotNull GuiGraphics pGuiGraphics, int pMouseX, int pMouseY, float pPartialTick) {
        pGuiGraphics.fill(this.getX()-backgroundSize,this.getY()-backgroundSize,this.getX()+this.width+backgroundSize,this.getY()+this.height+backgroundSize,bacgkroundColor.toInt());
        this.pressing = GLFW.glfwGetMouseButton(Minecraft.getInstance().getWindow().getWindow(), InputConstants.MOUSE_BUTTON_LEFT)==1;
        super.renderWidget(pGuiGraphics, pMouseX, pMouseY, pPartialTick);
    }

    @Override
    public boolean mouseDragged(double pMouseX, double pMouseY, int pButton, double pDragX, double pDragY) {
        if(this.draggable && this.pressing){
            this.setPosition((int) pMouseX, (int) pMouseY);
            this.onDragged.on(this,pMouseX,pMouseY);
        }
        return super.mouseDragged(pMouseX, pMouseY, pButton, pDragX, pDragY);
    }
}
