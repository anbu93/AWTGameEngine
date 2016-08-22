package com.vova_cons.sprite_loader.Xml;

import com.vova_cons.Resources;
import com.vova_cons.image.Image;
import com.vova_cons.sprite.Sprite;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.LinkedList;
import java.util.List;

@XmlRootElement (name = "atlas")
public class AtlasXml {
    @XmlAttribute (name = "src")
    public String imageSource;

    @XmlElement (name = "sprite")
    public List<SpriteSettingXml> spriteSettingXmls = new LinkedList<>();

    public List<Sprite> getSprites(String path) throws Exception {
        List<Sprite> sprites = new LinkedList<>();
        Image image = Resources.imageLoader.loadImage(path + imageSource);
        for(SpriteSettingXml settingXml : spriteSettingXmls)
            sprites.add(settingXml.getSprite(image));
        return sprites;
    }
}
