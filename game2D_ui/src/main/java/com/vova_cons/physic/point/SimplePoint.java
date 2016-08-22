package com.vova_cons.physic.point;

import com.vova_cons.common.FloatCommon;

/**
 * @author vova_
 * Класс представляющий реализацию точки в двухмерном пространстве
 */
public class SimplePoint implements Point {
    private float x;
    private float y;

    public SimplePoint(float x, float y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public Point offset(Point offset) {
        return new SimplePoint(x + offset.getX(), y + offset.getY());
    }

    @Override
    public float getLength() {
        return (float) Math.sqrt(x*x + y*y);
    }

    @Override
    public Point createVector(Point to) {
        return new SimplePoint(to.getX() - x, to.getY() - y);
    }

    @Override
    public Point createSubvector(float length) {
        float old_length = getLength();
        if (old_length == 0) return new SimplePoint(0, 0);
        float dx = (length * x) / old_length;
        float dy = (length * y) / old_length;
        return new SimplePoint(dx, dy);
    }

    @Override
    public float getX() {
        return x;
    }

    @Override
    public float getY() {
        return y;
    }

    @Override
    public double createAngle(Point to){
        double x1 = x, y1 = y;
        double x2 = to.getX(), y2 = to.getY();
        return Math.atan2(y1 - y2, x1 - x2);
    }

    @Override
    public double createAngleToGradus(Point to){
        double A = createAngle(to) / Math.PI * 180;
        A = (A < 0) ? A + 360 : A;   //Без этого диапазон от 0...180 и -1...-180
        return A;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Point){
            Point p = (Point) obj;
            return FloatCommon.isEquals(x, p.getX())
                    && FloatCommon.isEquals(y, p.getY());
        }
        return false;
    }

    @Override
    public int hashCode() {
        return toString().hashCode();
    }

    @Override
    public String toString() {
        return "(" + x + ";" + y + ")";
    }
}
