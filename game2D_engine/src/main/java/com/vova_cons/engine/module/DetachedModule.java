package com.vova_cons.engine.module;

import com.vova_cons.engine.common.logger.Logger;
import com.vova_cons.engine.modules_loader.ModuleXml.ModuleRootXml;
import com.vova_cons.engine.modules_loader.ModuleXml.SceneXml;
import com.vova_cons.engine.scene.Scene;
import com.vova_cons.engine.scene_registry.SceneRegistry;
import com.vova_cons.sprite.Sprite;

/**
 * Created by Kons on 15.07.2016.
 */
public class DetachedModule extends  ModuleState {
    public DetachedModule(StateableModule parent, ModuleRootXml moduleSettingFile) {
        super(parent, moduleSettingFile);
    }

    @Override
    public String state() {
        return "detached";
    }

    @Override
    public void attach() {
        AttachedModule attachedModule = new AttachedModule(super.parent, super.moduleSettingFile);
        super.parent.changeState(attachedModule);
        attachedModule.constructScenes(moduleSettingFile);
    }

    @Override
    public void detach() {
        Logger.log("Попытка выгрузить уже выгруженный модуль", Logger.INFO);
    }

    @Override
    public Scene getScene(String sceneId) {
        Logger.log("Попытка взять сцену из выгруженного модуля " + parent.getID() + " сцена: " + sceneId, Logger.ERROR);
        return null;
    }

    @Override
    public void addScenesToRegistry(SceneRegistry registry) {
        for(SceneXml sceneXml : super.moduleSettingFile.sceneList)
            registry.addScene(sceneXml.id, parent.getID());
    }

    @Override
    public Sprite getSprite(String id) {
        Logger.log("Попытка взять ресурс (спрайт) из не загруженного модуля спрайт: " + id, Logger.ERROR);
        return null;
    }
}
