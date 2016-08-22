package com.vova_cons.engine.core_loader.core_xml_setting_file;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Kons on 15.07.2016.
 */
@XmlRootElement (name = "core")
public class CoreRootXml {
    @XmlElement (name = "main_loop")
    public MainLoopXml mainLoopXml;

    @XmlElement (name = "window")
    public WindowXml windowXml;

    @XmlElement (name = "init")
    public InitXml initXml;

    @XmlElement (name = "module")
    public List<ModuleXml> moduleXmlList = new LinkedList<>();
}
