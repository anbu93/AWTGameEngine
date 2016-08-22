package com.vova_cons.physic;

import com.vova_cons.physic.point.Point;
import com.vova_cons.physic.point.SimplePoint;
import com.vova_cons.physic.rect.Rect;
import com.vova_cons.physic.rect.TopLeftDefineRectangle;

/**
 * Физика 2D
 * представляет статичные методы для создания объектов физики.
 * Point - является иммутабельным объектом.
 * Rect - также является иммутабельным объектом.
 */
public interface Physic {
    /**
     * Метод создания точки в 2D пространстве
     * @param x координата по х
     * @param y координата по y
     * @return готовую точку
     */
    static Point createPoint(float x, float y){
        return new SimplePoint(x, y);
    }

    /**
     * Создание прямоугольника в 2D пространстве (AABB-прямоугольники)
     * относительно левого верхнего угла.
     * @param topLeft левая верхняя точка
     * @param size точка задающая размер (x - ширина, y - высота)
     * @return готовый прямоугольник
     */
    static Rect createRect(Point topLeft, Point size){
        return new TopLeftDefineRectangle(topLeft, size);
    }
    static Rect createRect(Point topLeft, float width, float height){
        return createRect(topLeft, createPoint(width, height));
    }
    static Rect createRect(float x, float y, float w, float h){
        return createRect(createPoint(x, y), createPoint(w, h));
    }

    /**
     * Создание прямоугольника в 2D пространстве
     * относительно центра прямоугольника.
     * @param center точка центра прямоугольника
     * @param size точка задающая размер прямоугольника
     * @return готовый прямоугольник
     */
    static Rect createRectFromCenter(Point center, Point size){
        return createRect(center.offset(-size.getX()/2, -size.getY()/2), size);
    }
    static Rect createRectFromCenter(Point center, float width, float height){
        return createRectFromCenter(center, createPoint(width, height));
    }
    static Rect createRectFromCenter(float x, float y, float w, float h){
        return createRectFromCenter(createPoint(x, y), createPoint(w, h));
    }
}
