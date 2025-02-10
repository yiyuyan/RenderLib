package cn.ksmcbrigade.rl.widgets.editbox;

import cn.ksmcbrigade.rl.Color;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;

public class EditBoxBuilder {
    public int x = 0,y = 0,w = 25,h = 15;
    public boolean draggable = false;
    public OnDragged onDragged = ((button, mousePosX, mousePosY) ->{});
    public Color color;
    public Component text = Component.nullToEmpty(""),hint = Component.nullToEmpty("");
    public int backgroundSize = 2;

    public EditBoxBuilder(Color color){
        this.color = color;
    }

    public EditBox build(){
        EditBox button = new EditBox(Minecraft.getInstance().font,w,h,text,color);
        button.draggable = this.draggable;
        button.onDragged = this.onDragged;
        button.backgroundSize = this.backgroundSize;
        button.setHint(this.hint);
        button.setPosition(this.x,this.y);
        return button;
    }

    public EditBoxBuilder setPos(int x, int y){
        this.x = x;
        this.y = y;
        return this;
    }

    public EditBoxBuilder setWH(int w, int h){
        this.w = w;
        this.h = h;
        return this;
    }

    public EditBoxBuilder backgroundSize(int size){
        this.backgroundSize = size;
        return this;
    }

    public EditBoxBuilder hint(Component hint){
        this.hint = hint;
        return this;
    }

    public EditBoxBuilder onDragged(OnDragged onDragged){
        this.onDragged = onDragged;
        return this;
    }

    public EditBoxBuilder draggable(boolean draggable){
        this.draggable = draggable;
        return this;
    }

    public EditBoxBuilder draggable(){
        return this.draggable(!this.draggable);
    }

    public EditBoxBuilder setPosAndWH(int x, int y, int w, int h){
        return this.setPos(x,y).setWH(w,h);
    }
}
