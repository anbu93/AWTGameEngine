package com.vova_cons.image;

import com.vova_cons.physic.rect.Rect;
import com.vova_cons.window.Window;

public interface Image {
    void draw(Window window, Rect position, Rect texturePos, float angle);
}
