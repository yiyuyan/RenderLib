package cn.ksmcbrigade.rl.widgets.button;

import cn.ksmcbrigade.rl.Color;
import net.minecraft.network.chat.Component;

public class ButtonBuilder {
    public int x = 0,y = 0,w = 100,h = 20;
    public boolean draggable = false;
    public OnPress onPress = (button->{});
    public OnDragged onDragged = ((button, mousePosX, mousePosY) ->{});
    public Color color,hovColor,outline = new Color(255,255,255,1);
    public Component text;

    public ButtonBuilder(Component text,Color color,Color hovColor){
        this.color = color;
        this.hovColor = hovColor;
        this.text = text;
    }

    public Button build(){
        Button button = new Button(x,y,w,h,text,color,hovColor,onPress);
        button.draggable = this.draggable;
        button.onDragged = this.onDragged;
        button.outlineColor = this.outline;
        return button;
    }

    public ButtonBuilder setPos(int x,int y){
        this.x = x;
        this.y = y;
        return this;
    }

    public ButtonBuilder setWH(int w,int h){
        this.w = w;
        this.h = h;
        return this;
    }

    public ButtonBuilder onPress(OnPress onPress){
        this.onPress = onPress;
        return this;
    }

    public ButtonBuilder onDragged(OnDragged onDragged){
        this.onDragged = onDragged;
        return this;
    }

    public ButtonBuilder draggable(boolean draggable){
        this.draggable = draggable;
        return this;
    }

    public ButtonBuilder draggable(){
        return this.draggable(!this.draggable);
    }

    public ButtonBuilder outline(Color color){
        this.outline = color;
        return this;
    }

    public ButtonBuilder noOutlines(){
        return outline(new Color(255,255,255,0));
    }

    public ButtonBuilder setPosAndWH(int x,int y,int w,int h){
        return this.setPos(x,y).setWH(w,h);
    }
}
