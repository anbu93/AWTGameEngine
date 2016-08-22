package com.vova_cons.physic.rect;

import com.vova_cons.physic.Physic;
import com.vova_cons.physic.point.Point;

public interface Rect {
    Point getCenter();
    Point getTopLeft();

    Point getSize();
    float getWidth();
    float getHeight();

    Rect offset(Point offset);
    default Rect offset(float x, float y){
        return offset(Physic.createPoint(x, y));
    }

    boolean isCollised(Rect rect);
    boolean isInclude(Point point);

    Rect scaledRect(float scale);
}
