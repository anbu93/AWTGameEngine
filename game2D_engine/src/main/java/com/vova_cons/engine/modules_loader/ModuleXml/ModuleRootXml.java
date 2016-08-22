package com.vova_cons.engine.modules_loader.ModuleXml;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Kons on 15.07.2016.
 */
@XmlRootElement (name = "module")
public class ModuleRootXml {
    @XmlAttribute (name = "id")
    public String id;

    @XmlElement (name = "resources")
    public ResourcesXml resourcesXml;

    @XmlElement (name = "scene")
    public List<SceneXml> sceneList = new LinkedList<>();
}
