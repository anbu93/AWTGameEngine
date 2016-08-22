package com.vova_cons.engine.modules_loader.ModuleXml;

import javax.xml.bind.annotation.XmlAttribute;

/**
 * Created by Kons on 15.07.2016.
 */
public class SceneXml {
    @XmlAttribute (name = "id")
    public String id;

    @XmlAttribute (name = "class")
    public String classPath;

    @XmlAttribute (name = "arg")
    public String arg;
}
