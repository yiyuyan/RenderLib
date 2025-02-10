package cn.ksmcbrigade.rl.testing;

import cn.ksmcbrigade.rl.Color;
import cn.ksmcbrigade.rl.widgets.button.Button;
import cn.ksmcbrigade.rl.widgets.button.ButtonBuilder;
import cn.ksmcbrigade.rl.widgets.editbox.EditBoxBuilder;
import cn.ksmcbrigade.rl.widgets.list.OptionList;
import cn.ksmcbrigade.rl.widgets.list.entry.AbstractWidgetEntry;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.options.OptionsSubScreen;
import net.minecraft.network.chat.Component;

public class TestScreen extends OptionsSubScreen {
    public TestScreen(Screen last) {
        super(last,Minecraft.getInstance().options,Component.literal("For Test"));
    }

    public boolean show1 = true;
    public boolean show2 = true;
    public boolean show3 = true;
    public boolean show4 = true;
    public boolean show5 = true;

    @Override
    protected void init() {
        OptionList list1 = new OptionList(50, (int) (this.height*0.75),0,25);
        OptionList list2 = new OptionList(50, (int) (this.height*0.75),0,25);
        OptionList list3 = new OptionList(50, (int) (this.height*0.75),0,25);
        OptionList list4 = new OptionList(50, (int) (this.height*0.75),0,25);
        OptionList list5 = new OptionList(50, (int) (this.height*0.75),0,25);

        Button button = new ButtonBuilder(Component.literal("Type1"),new Color(0, 123, 255, 0.65F),new Color(0, 123, 255, 0.35F)).setPosAndWH(0,0,50,25).draggable().outline(new Color(255,255,255,0.75F)).onDragged(((button1, mousePosX, mousePosY) -> {
            list1.setX(button1.getX());
            list1.setY(button1.getY()+button1.getHeight());
        })).onPress((b)->{
            show1=!show1;
            list1.render = show1;
        }).build();

        Button button2 = new ButtonBuilder(Component.literal("Type2"),new Color(0, 123, 255, 0.65F),new Color(0, 123, 255, 0.35F)).setPosAndWH(50 + 2,0,50,25).draggable().outline(new Color(255,255,255,0.75F)).onDragged(((button1, mousePosX, mousePosY) -> {
            list2.setX(button1.getX());
            list2.setY(button1.getY()+button1.getHeight());
        })).onPress((b)->{
            show2=!show2;
            list2.render = show2;
        }).build();

        Button button3 = new ButtonBuilder(Component.literal("Type3"),new Color(0, 123, 255, 0.65F),new Color(0, 123, 255, 0.35F)).setPosAndWH((50+2)*2,0,50,25).draggable().outline(new Color(255,255,255,0.75F)).onDragged(((button1, mousePosX, mousePosY) -> {
            list3.setX(button1.getX());
            list3.setY(button1.getY()+button1.getHeight());
        })).onPress((b)->{
            show3=!show3;
            list3.render = show3;
        }).build();

        Button button4 = new ButtonBuilder(Component.literal("Type4"),new Color(0, 123, 255, 0.65F),new Color(0, 123, 255, 0.35F)).setPosAndWH((50+2)*3,0,50,25).draggable().outline(new Color(255,255,255,0.75F)).onDragged(((button1, mousePosX, mousePosY) -> {
            list4.setX(button1.getX());
            list4.setY(button1.getY()+button1.getHeight());
        })).onPress((b)->{
            show4=!show4;
            list4.render = show4;
        }).build();

        Button button5 = new ButtonBuilder(Component.literal("Type5"),new Color(0, 123, 255, 0.65F),new Color(0, 123, 255, 0.35F)).setPosAndWH((50+2)*4,0,50,25).draggable().outline(new Color(255,255,255,0.75F)).onDragged(((button1, mousePosX, mousePosY) -> {
            list5.setX(button1.getX());
            list5.setY(button1.getY()+button1.getHeight());
        })).onPress((b)->{
            show5=!show5;
            list5.render = show5;
        }).build();

        list1.setX(button.getX());
        list1.setY(button.getY()+button.getHeight());
        list1.setRowWidth(50);

        list2.setX(button2.getX());
        list2.setY(button2.getY()+button2.getHeight());
        list2.setRowWidth(50);

        list3.setX(button3.getX());
        list3.setY(button3.getY()+button3.getHeight());
        list3.setRowWidth(50);

        list4.setX(button4.getX());
        list4.setY(button4.getY()+button4.getHeight());
        list4.setRowWidth(50);

        list5.setX(button5.getX());
        list5.setY(button5.getY()+button5.getHeight());
        list5.setRowWidth(50);

        for (int i = 0; i < 10; i++) {
            list1.addEntry(new AbstractWidgetEntry(new ButtonBuilder(Component.literal("Test1"+i),new Color(0, 123, 255, 0.65F),new Color(0, 123, 255, 0.35F)).noOutlines().setWH(50,25).onPress((b)->System.out.println(b.getMessage().getString())).build()));
        }

        for (int i = 0; i < 5; i++) {
            list2.addEntry(new AbstractWidgetEntry(new ButtonBuilder(Component.literal("Test2"+i),new Color(0, 123, 255, 0.65F),new Color(0, 123, 255, 0.35F)).noOutlines().setWH(50,25).onPress((b)->System.out.println(b.getMessage().getString())).build()));
        }

        for (int i = 0; i < 20; i++) {
            list3.addEntry(new AbstractWidgetEntry(new ButtonBuilder(Component.literal("Test3"+i),new Color(0, 123, 255, 0.65F),new Color(0, 123, 255, 0.35F)).noOutlines().setWH(50,25).onPress((b)->System.out.println(b.getMessage().getString())).build()));
        }

        for (int i = 0; i < 80; i++) {
            list4.addEntry(new AbstractWidgetEntry(new ButtonBuilder(Component.literal("Test4"+i),new Color(0, 123, 255, 0.65F),new Color(0, 123, 255, 0.35F)).noOutlines().setWH(50,25).onPress((b)->System.out.println(b.getMessage().getString())).build()));
        }

        for (int i = 0; i < 3; i++) {
            list5.addEntry(new AbstractWidgetEntry(new ButtonBuilder(Component.literal("Test5"+i),new Color(0, 123, 255, 0.65F),new Color(0, 123, 255, 0.35F)).noOutlines().setWH(50,25).onPress((b)->System.out.println(b.getMessage().getString())).build()));
        }

        this.addRenderableWidget(new EditBoxBuilder(new Color(0, 123, 255, 0.65F)).setPosAndWH((50+2)*6,2,50,20).hint(Component.literal("TEST")).draggable().build());

        this.addRenderableWidget(button);
        this.addRenderableWidget(button2);
        this.addRenderableWidget(button3);
        this.addRenderableWidget(button4);
        this.addRenderableWidget(button5);

        this.addRenderableWidget(list1);
        this.addRenderableWidget(list2);
        this.addRenderableWidget(list3);
        this.addRenderableWidget(list4);
        this.addRenderableWidget(list5);
    }

    @Override
    protected void addOptions() {

    }
}
