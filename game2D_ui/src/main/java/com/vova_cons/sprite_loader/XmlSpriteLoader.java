package com.vova_cons.sprite_loader;

import com.vova_cons.common.JAXBParser;
import com.vova_cons.sprite.Sprite;
import com.vova_cons.sprite_loader.Xml.SpriteSourceXml;

import javax.xml.bind.JAXBException;
import java.io.File;
import java.util.List;

public class XmlSpriteLoader implements SpriteLoader {
    @Override
    public List<Sprite> load(String source) throws Exception {
        try {
            File file  = new File(source);
            SpriteSourceXml spriteSourceXml = (SpriteSourceXml) JAXBParser.parseFromFile(SpriteSourceXml.class, file);
            return spriteSourceXml.loadSprites();
        } catch (JAXBException e) {
            StringBuilder sb = new StringBuilder();
            sb.append("ERROR on parsing xml-file: ").append(source).append(" > ")
                    .append(e.getMessage()).append("\n");
            sb.append(e.getCause().toString()).append(" ").append(e.getErrorCode()).append("\n");
            StackTraceElement[] stackTrace  =e.getStackTrace();
            for(StackTraceElement element : stackTrace)
                sb.append("\t").append(element.getClassName()).append(".")
                        .append(element.getMethodName()).append("\n");
            throw new Exception(sb.toString());
        }
    }
}
