package com.vova_cons.engine.module;

import com.vova_cons.Resources;
import com.vova_cons.engine.common.logger.Logger;
import com.vova_cons.engine.modules_loader.ModuleXml.ModuleRootXml;
import com.vova_cons.engine.modules_loader.ModuleXml.SceneXml;
import com.vova_cons.engine.scene.Scene;
import com.vova_cons.engine.scene_registry.SceneRegistry;
import com.vova_cons.sprite.Sprite;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Kons on 15.07.2016.
 */
public class AttachedModule extends ModuleState {
    private Resources uiResources;
    private Map<String, Scene> sceneMap = new HashMap<>();

    public AttachedModule(StateableModule parent, ModuleRootXml moduleSettingFile) {
        super(parent, moduleSettingFile);
        try {
            uiResources = new Resources(moduleSettingFile.resourcesXml.src);
            moduleSettingFile.sceneList.forEach(this::addScene);
        } catch (Exception e) {
            Logger.log("Критическая ошибка при попытке загрузить модуль " + parent.getID() + " " + e.getMessage(),
                    Logger.FATAL_ERROR);
            e.printStackTrace();
        }
    }

    private void addScene(SceneXml sceneXml) {
        try {
            Scene scene = (Scene) Class.forName(sceneXml.classPath).newInstance();
            scene.setID(sceneXml.id);
            scene.setParentModule(parent);
            sceneMap.put(sceneXml.id, scene);
        } catch (Exception e) {
            Logger.log("Ошибка при попытке загрузить сцену: " + sceneXml.id + " в модуль " + parent.getID() +
                    "(" + e.getMessage() + ")",
                    Logger.ERROR);
        }
    }

    public void constructScenes(ModuleRootXml moduleSettingFile) {
        for(SceneXml sceneXml : moduleSettingFile.sceneList) {
            Scene scene = sceneMap.get(sceneXml.id);
            if(scene != null) scene.construct(sceneXml.arg);
            else Logger.log("Ошибка при попытке построить сцену " + sceneXml.id + " (требуемой сцены нет в карте сцен)",
                    Logger.ERROR);
        }
    }

    @Override
    public void attach() {
        Logger.log("Попытка загрузить уже активный модуль", Logger.INFO);
    }

    @Override
    public void detach() {
        super.parent.changeState(new DetachedModule(super.parent, super.moduleSettingFile));
    }

    @Override
    public Scene getScene(String sceneId) {
        if (sceneMap.containsKey(sceneId))
            return sceneMap.get(sceneId);
        Logger.log("Требуемой сцены \'" + sceneId + "\' не существует в модуле \'" + parent.getID(), Logger.ERROR);
        return null;
    }

    @Override
    public void addScenesToRegistry(SceneRegistry registry) {
        Logger.log("Попытка добавить сцены в реестр из уже загруженного модуля", Logger.ERROR);
    }

    @Override
    public Sprite getSprite(String id) {
        try {
            return uiResources.getSprite(id);
        } catch (Exception e) {
            Logger.log("Ошибка при попытке взять спрайт из графических ресурсов " + e.getMessage(),
                    Logger.ERROR);
            return null;
        }
    }

    @Override
    public String state() {
        return "attached";
    }
}
