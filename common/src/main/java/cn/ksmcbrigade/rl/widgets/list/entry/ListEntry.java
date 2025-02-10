package cn.ksmcbrigade.rl.widgets.list.entry;

import net.minecraft.client.gui.components.ContainerObjectSelectionList;

public abstract class ListEntry extends ContainerObjectSelectionList.Entry<ListEntry>{
    abstract void refreshEntry();
}
