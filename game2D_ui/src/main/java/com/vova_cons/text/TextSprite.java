package com.vova_cons.text;

import com.vova_cons.physic.rect.Rect;
import com.vova_cons.sprite.Sprite;

public interface TextSprite extends Sprite {
    static TextSprite create(String text, Rect size, int fontsize, RGBColor color){
        return new AwtTextSprite(text, color, size, fontsize);
    }

    void setText(String text, RGBColor color, int fontsize);
}
