package com.vova_cons.physic.rect;

import com.vova_cons.physic.Physic;
import com.vova_cons.physic.point.Point;

public class TopLeftDefineRectangle implements Rect {
    private final Point topLeft;
    private final Point size;

    public TopLeftDefineRectangle(Point topLeft, Point size) {
        this.topLeft = topLeft;
        this.size =size;
    }

    @Override
    public Point getCenter() {
        return topLeft.offset(getWidth()/2, getHeight()/2);
    }

    @Override
    public Point getTopLeft() {
        return topLeft;
    }

    @Override
    public Point getSize() {
        return size;
    }

    @Override
    public float getWidth() {
        return size.getX();
    }

    @Override
    public float getHeight() {
        return size.getY();
    }

    @Override
    public Rect offset(Point offset) {
        return new TopLeftDefineRectangle(topLeft.offset(offset), size);
    }

    @Override
    public boolean isCollised(Rect rect) {
        Point center = getCenter();
        Point otherCenter = rect.getCenter();
        Point otherSize = rect.getSize();
        return Math.abs(center.getX()-otherCenter.getX()) < (size.getX()/2 + otherSize.getX()/2)
                && Math.abs(center.getY()-otherCenter.getY()) < (size.getY()/2 + otherSize.getY()/2);
    }

    @Override
    public boolean isInclude(Point point) {
        Point center = getCenter();
        return Math.abs(center.getX()-point.getX()) <= (size.getX()/2)
                && Math.abs(center.getY()-point.getY()) <= (size.getY()/2);
    }

    @Override
    public Rect scaledRect(float scale) {
        return new TopLeftDefineRectangle(topLeft, Physic.createPoint(size.getX()*scale, size.getY()*scale));
    }

    @Override
    public String toString() {
        return "r" + topLeft.toString() + ":" + size.toString();
    }

    @Override
    public int hashCode() {
        return toString().hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof TopLeftDefineRectangle){
            TopLeftDefineRectangle rect = (TopLeftDefineRectangle) obj;
            return rect.topLeft.equals(topLeft) && rect.size.equals(size);
        }
        return super.equals(obj);
    }
}
