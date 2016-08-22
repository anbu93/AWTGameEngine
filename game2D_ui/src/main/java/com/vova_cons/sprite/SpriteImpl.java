package com.vova_cons.sprite;

import com.vova_cons.image.Image;
import com.vova_cons.physic.Physic;
import com.vova_cons.physic.rect.Rect;
import com.vova_cons.window.Window;

public class SpriteImpl implements Sprite {
    private String id;
    private Image image;
    private Rect texture;

    public SpriteImpl(String id, Image image, Rect texture) {
        this.id = id;
        this.image = image;
        this.texture = texture;
    }

    @Override
    public void draw(Window window, Rect position, float angle) {
        image.draw(window, position, texture, angle);
    }

    @Override
    public Rect getSize() {
        return Physic.createRect(0, 0, texture.getWidth(), texture.getHeight());
    }

    @Override
    public String toString() {
        return id;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof SpriteImpl){
            return id.equals(obj.toString()) && image.equals(((SpriteImpl)obj).image)
                    && texture.equals(((SpriteImpl)obj).texture) ;
        }
        return false;
    }
}
