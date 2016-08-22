package com.vova_cons.image;

import com.vova_cons.physic.Physic;
import com.vova_cons.physic.point.Point;
import com.vova_cons.physic.rect.Rect;
import com.vova_cons.window.AwtWindow;
import com.vova_cons.window.Window;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;

public class AwtImage implements Image {
    private java.awt.Image image;

    public AwtImage(java.awt.Image image){
        this.image = image;
    }

    @Override
    public void draw(Window window, Rect position, Rect texturePos, float angle) {
        AwtWindow awtWindow = (AwtWindow) window;
        Graphics graph = awtWindow.getWindowGraphics();
        if (angle != 0)
            drawOnGraphic(graph, position, texturePos, angle);
        else
            drawOnGraphic(graph, position, texturePos);
    }
    private void drawOnGraphic(Graphics window, Rect position, Rect textureRect){
        int px = (int) position.getTopLeft().getX();
        int py = (int) position.getTopLeft().getY();
        int pw = (int) position.getWidth();
        int ph = (int) position.getHeight();
        Point t1 = textureRect.getTopLeft();
        Point t2 = t1.offset(textureRect.getSize());
        window.drawImage(image, px, py, px+pw, py+ph,
                (int)t1.getX(), (int)t1.getY(), (int)t2.getX(), (int)t2.getY(), null);
    }

    private void drawOnGraphic(Graphics window, Rect position, Rect textureRect, float angle){
        int px = (int) position.getTopLeft().getX();
        int py = (int) position.getTopLeft().getY();
        int pw = (int) position.getWidth();
        int ph = (int) position.getHeight();
        Point t1 = textureRect.getTopLeft();
        Point t2 = t1.offset(textureRect.getSize());
        double radius = Math.sqrt(pw*pw + ph+ph) * 2;
        AffineTransform at = new AffineTransform();
        at.setToRotation(angle, radius/2, radius/2);
        AffineTransformOp transformOp  = new AffineTransformOp(at, AffineTransformOp.TYPE_BILINEAR);
        BufferedImage rotatedImg = new BufferedImage((int)radius, (int)radius, BufferedImage.TYPE_INT_ARGB);
        BufferedImage holstImg  = new BufferedImage((int)radius, (int)radius, BufferedImage.TYPE_INT_ARGB);
        Graphics rotatedImgGraphics = rotatedImg.createGraphics();
        int dx = (int) (radius - pw) / 2;
        int dy = (int) (radius - ph) / 2;
        rotatedImgGraphics.drawImage(image, dx, dy, pw+dx, ph+dy,
                (int)t1.getX(), (int)t1.getY(), (int)t2.getX(), (int)t2.getY(), null);
        transformOp.filter(rotatedImg, holstImg);
        window.drawImage(holstImg, px - dx, py - dy, null);
    }

    private void drawOnGraphic1(Graphics window, Rect position, Rect textureRect, float angle){
        AffineTransform at;
        at  = new AffineTransform();
        at.setToRotation(angle, position.getWidth()/2, position.getHeight()/2);
        AffineTransformOp atOp  = new AffineTransformOp(at, AffineTransformOp.TYPE_BILINEAR);
        BufferedImage bi = new BufferedImage((int)position.getWidth(), (int)position.getHeight(), BufferedImage.TYPE_INT_ARGB);
        BufferedImage res  = new BufferedImage((int)position.getWidth(), (int)position.getHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics g = bi.createGraphics();
        Point t1 = textureRect.getTopLeft();
        Point t2 = t1.offset(textureRect.getSize());
        g.drawImage(image, 0, 0, (int)position.getWidth(), (int)position.getHeight(),
                (int)t1.getX(), (int)t1.getY(), (int)t2.getX(), (int)t2.getY(), null);
        atOp.filter(bi, res);
        Point p1 = position.getTopLeft();
        window.drawImage(res, (int)p1.getX(), (int)p1.getY(), null);
    }

    public boolean equals(Object object){
        if (object instanceof AwtImage){
            return image == ((AwtImage) object).image;
        }
        return false;
    }
}
