package com.vova_cons.sprite_loader;

import com.vova_cons.sprite.Sprite;

import java.util.List;

public interface SpriteLoader {
    List<Sprite> load(String source) throws Exception;
}
