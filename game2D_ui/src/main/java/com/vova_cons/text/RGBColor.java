package com.vova_cons.text;

import java.awt.*;

public class RGBColor {
    public static RGBColor RED = new RGBColor(255, 0, 0);
    public static RGBColor GREEN = new RGBColor(0, 255, 0);
    public static RGBColor BLUE = new RGBColor(0, 0, 255);

    public int red;
    public int green;
    public int blue;

    public RGBColor(int r, int g, int b){
        red = r;
        green = g;
        blue = b;
    }

    public Color createAwtColor() {
        return new Color(red, green, blue);
    }
}
