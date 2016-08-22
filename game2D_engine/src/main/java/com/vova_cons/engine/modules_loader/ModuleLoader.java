package com.vova_cons.engine.modules_loader;

import com.vova_cons.engine.module.Module;

/**
 * Created by Kons on 15.07.2016.
 */
public interface ModuleLoader {
    ModuleLoader loader = new XmlModuleLoader();

    static Module load(String settingFile){
        return loader.loadFromFile(settingFile);
    }

    Module loadFromFile(String moduleSettingFile);
}
