package com.vova_cons.physic.point;

import com.vova_cons.physic.Physic;

/**
 * Интерфейс точки в двухмерном пространстве
 */
public interface Point {
    Point offset(Point offset);
    default Point offset(float x, float y){
        return offset(Physic.createPoint(x, y));
    }

    float getLength();
    Point createVector(Point to);
    Point createSubvector(float length);

    float getX();
    float getY();

    double createAngle(Point to);
    double createAngleToGradus(Point to);
}
