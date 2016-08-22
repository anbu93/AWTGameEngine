package com.vova_cons.sprite_loader.Xml;

import com.vova_cons.image.Image;
import com.vova_cons.physic.Physic;
import com.vova_cons.sprite.Sprite;
import com.vova_cons.sprite.SpriteImpl;

import javax.xml.bind.annotation.XmlAttribute;

public class SpriteSettingXml {
    @XmlAttribute (name = "id")
    public String id;

    @XmlAttribute (name = "x")
    public float x;
    @XmlAttribute (name = "y")
    public float y;
    @XmlAttribute (name = "w")
    public float w;
    @XmlAttribute (name = "h")
    public float h;

    public Sprite getSprite(Image image){
        return new SpriteImpl(id, image, Physic.createRect(x, y, w, h));
    }

}
