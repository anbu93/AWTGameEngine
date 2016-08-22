package com.vova_cons.engine.core_loader.core_xml_setting_file;

import javax.xml.bind.annotation.XmlAttribute;

/**
 * Created by Kons on 15.07.2016.
 */
public class WindowXml {
    @XmlAttribute (name = "title")
    public String title;

    @XmlAttribute (name = "width")
    public int width;

    @XmlAttribute (name = "height")
    public int height;
}
