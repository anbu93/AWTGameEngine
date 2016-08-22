package com.vova_cons.text;

import com.vova_cons.image.AwtImage;
import com.vova_cons.image.Image;
import com.vova_cons.physic.point.Point;
import com.vova_cons.physic.rect.Rect;
import com.vova_cons.window.Window;

import java.awt.*;
import java.awt.image.BufferedImage;

public class AwtTextSprite implements TextSprite {
    private static final String FONT = "Arial";
    private static final int FONT_STYLE = Font.BOLD;
    private static Font font;

    private String text;
    private RGBColor color;
    private Rect size;
    private Image image;
    private int fontsize;

    public AwtTextSprite(String text, RGBColor color, Rect size, int fontSize){
        this.size = size;
        setText(text, color, fontSize);
    }

    @Override
    public void draw(Window window, Rect position, float angle) {
        image.draw(window, position, size, angle);
    }

    @Override
    public void setText(String text, RGBColor color, int fontsize) {
        this.text = text;
        this.color = color;
        image = createTextImage(text, size, fontsize, color);
    }

    @Override
    public Rect getSize() {
        return size;
    }

    private void drawText(String text, Graphics window, Rect rect, int size, RGBColor color){
        Point position = rect.getTopLeft().offset(rect.getWidth()/2, rect.getHeight()/10);
        int width = text.length() * size/2;
        position = (position.offset(-width/2, 0));
        font = new Font(FONT, FONT_STYLE, size);
        String[] lines = text.split("\\n");
        int padding = 0;
        window.setFont(font);
        window.setColor(color.createAwtColor());
        FontMetrics fm = window.getFontMetrics();
        for(String line : lines) {
            Point topLeft = position.offset(0, padding*size);
            int h = fm.getHeight();
            window.drawString(line, (int)topLeft.getX(), (int)topLeft.getY()+h);
            padding++;
        }
    }
    private void drawTextLeftMargin(String text, Graphics window, Rect rect, int size, RGBColor color){
        Point position = rect.getTopLeft().offset(0, rect.getHeight()/10);
        font = new Font(FONT, FONT_STYLE, size);
        String[] lines = text.split("\\n");
        int padding = 0;
        window.setFont(font);
        window.setColor(color.createAwtColor());
        FontMetrics fm = window.getFontMetrics();
        for(String line : lines) {
            Point topLeft = position.offset(0, padding*size);
            int h = fm.getHeight();
            window.drawString(line, (int)topLeft.getX(), (int)topLeft.getY()+h);
            padding++;
        }
    }


    private Image createTextImage(String text, Rect rectangle, int size, RGBColor color){
        BufferedImage image = new BufferedImage((int)rectangle.getWidth(), (int)rectangle.getHeight(), BufferedImage.TYPE_INT_ARGB);
        drawText(text, image.getGraphics(), rectangle, size, color);
        return new AwtImage(image);
    }
}
