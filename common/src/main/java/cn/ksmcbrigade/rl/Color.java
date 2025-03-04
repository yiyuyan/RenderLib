package cn.ksmcbrigade.rl;

import net.minecraft.util.FastColor;

public record Color(int r, int g, int b, float a) {

    public static Color parse(int hex){
        return new Color(hex >> 16 & 0xFF,hex >> 8 & 0xFF,hex & 0xFF,0xFF);
    }

    public static Color parse(int hex,int len){
        return new Color(hex >> 16 & 0xFF,hex >> 8 & 0xFF,hex & 0xFF, (float) (len == 8 ? (hex >> 24 & 0xFF) : 0xFF) / 0xFF);
    }

    public static Color parse(String color){
        return parse(Integer.parseUnsignedInt(color.replace("#",""),16),color.contains("#")?color.substring(1).length():color.length());
    }

    public int toInt(){
        return FastColor.ARGB32.color((int) (this.a * 255),this.r,this.g,this.b);
    }
}
