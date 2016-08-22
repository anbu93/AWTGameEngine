package com.vova_cons.sprite_loader;

import com.vova_cons.sprite.Sprite;

public interface SpriteCollection {
    Sprite getSprite(String id) throws Exception;
}
