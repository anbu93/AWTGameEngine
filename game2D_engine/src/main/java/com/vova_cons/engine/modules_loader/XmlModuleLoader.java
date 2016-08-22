package com.vova_cons.engine.modules_loader;

import com.vova_cons.common.JAXBParser;
import com.vova_cons.engine.common.logger.Logger;
import com.vova_cons.engine.module.Module;
import com.vova_cons.engine.module.StateableModule;
import com.vova_cons.engine.modules_loader.ModuleXml.ModuleRootXml;

import javax.xml.bind.JAXBException;
import java.io.File;

/**
 * Created by Kons on 15.07.2016.
 */
class XmlModuleLoader implements ModuleLoader {
    @Override
    public Module loadFromFile(String moduleSettingFile) {
        try {
            ModuleRootXml moduleXml = (ModuleRootXml) JAXBParser.parseFromFile(ModuleRootXml.class,
                    new File(moduleSettingFile));
            return new StateableModule(moduleXml.id, moduleXml);
        } catch (Exception e) {
            Logger.log("Ошибка при попытке построить модуль " + moduleSettingFile, Logger.ERROR);
            return null;
        }
    }
}
