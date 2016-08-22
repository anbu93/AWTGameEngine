package com.vova_cons.engine.core_loader;

import com.vova_cons.common.JAXBParser;
import com.vova_cons.engine.common.logger.Logger;
import com.vova_cons.engine.core.Core;
import com.vova_cons.engine.core.LoadedCore;
import com.vova_cons.engine.core_loader.core_xml_setting_file.CoreRootXml;
import com.vova_cons.engine.modules_loader.ModuleLoader;

import javax.xml.bind.JAXBException;
import java.io.File;

/**
 * Created by Kons on 15.07.2016.
 */
class CoreLoaderImpl {
    private CoreRootXml coreXmlSettingFile;
    private LoadedCore loadedCore;

    public void load(String coreSettingFileXml){
        loadedCore = (LoadedCore) Core.core;
        parseSettingFile(coreSettingFileXml);
        createMainLoop();
        createWindow();
        loadModules();
        addScenesToRegistry();
        setInitScene();
    }

    private void parseSettingFile(String coreSettingFileXml){
        File settingFile = new File(coreSettingFileXml);
        try {
            coreXmlSettingFile = (CoreRootXml) JAXBParser.parseFromFile(CoreRootXml.class, settingFile);
        } catch (JAXBException e) {
            Logger.log("Загрузчик ядра: ошибка при парсинге (" + e.getMessage() + ")", Logger.FATAL_ERROR);
        }
    }
    private void createMainLoop(){
        loadedCore.createMainLoop(coreXmlSettingFile.mainLoopXml.tick_time);
    }
    private void createWindow(){
        loadedCore.createWindow(coreXmlSettingFile.windowXml.title,
                coreXmlSettingFile.windowXml.width,
                coreXmlSettingFile.windowXml.height);
    }
    private void loadModules(){
        coreXmlSettingFile.moduleXmlList.stream().forEach(module -> loadModule(module.src));
    }
    private void loadModule(String moduleSettingSource){
        loadedCore.addModule(ModuleLoader.load(moduleSettingSource));
    }
    private void addScenesToRegistry() {
        loadedCore.addScenesToRegistry();
    }
    private void setInitScene(){
        loadedCore.setInitScene(coreXmlSettingFile.initXml.init_scene);
    }
}
