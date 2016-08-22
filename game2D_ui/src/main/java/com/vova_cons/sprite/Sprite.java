package com.vova_cons.sprite;

import com.vova_cons.physic.rect.Rect;
import com.vova_cons.window.Window;

public interface Sprite {
    void draw(Window window, Rect position, float angle);
    Rect getSize();
}
