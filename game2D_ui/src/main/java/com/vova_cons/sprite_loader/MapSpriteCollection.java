package com.vova_cons.sprite_loader;

import com.vova_cons.sprite.Sprite;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapSpriteCollection implements SpriteCollection {
    private Map<String, Sprite> spriteMap = new HashMap<>();

    public MapSpriteCollection(List<Sprite> spriteList){
        for(Sprite sprite : spriteList)
            spriteMap.put(sprite.toString(), sprite);
    }

    @Override
    public Sprite getSprite(String id) throws Exception {
        if (spriteMap.containsKey(id))
            return spriteMap.get(id);
        throw new Exception("Требуемый спрайт не найден: " + id);
    }
}
