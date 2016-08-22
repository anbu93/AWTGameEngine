package com.vova_cons.sprite_loader.Xml;

import com.vova_cons.sprite.Sprite;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.LinkedList;
import java.util.List;

@XmlRootElement (name = "source")
public class SpriteSourceXml {
    @XmlAttribute (name = "path")
    public String path;

    @XmlElement (name = "atlas")
    public List<AtlasSource> atlasSources = new LinkedList<>();

    public List<Sprite> loadSprites() throws Exception {
        List<Sprite> result = new LinkedList<>();
        for(AtlasSource atlasSource : atlasSources)
            result.addAll(atlasSource.load(path));
        return result;
    }
}
