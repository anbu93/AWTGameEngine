package com.vova_cons.sprite_loader.Xml;

import com.vova_cons.common.JAXBParser;
import com.vova_cons.sprite.Sprite;

import javax.xml.bind.annotation.XmlAttribute;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class AtlasSource {
    @XmlAttribute(name = "src")
    public String src;

    public List<Sprite> load(String path) throws Exception {
        try {
            File file = new File(path + src);
            if (file == null)
                throw new Exception("file not found: " + path + src);
            AtlasXml atlas = (AtlasXml) JAXBParser.parseFromFile(AtlasXml.class, file);
            return atlas.getSprites(path);
        } catch (IOException error){
            throw new Exception ("ERROR: file not found: " + path + src);
        } catch (Exception error){
            throw new Exception ("ERROR: on load atlas: " + path + src + " > " + error.getMessage());
        }
    }
}
