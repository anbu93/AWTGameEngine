package com.vova_cons.engine.core_loader.core_xml_setting_file;

import javax.xml.bind.annotation.XmlAttribute;

/**
 * Created by Kons on 15.07.2016.
 */
public class ModuleXml {
    @XmlAttribute (name = "path")
    public String src;
}
