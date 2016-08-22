package com.vova_cons.engine.module;

import com.vova_cons.Resources;
import com.vova_cons.engine.Updateable;
import com.vova_cons.engine.common.logger.Logger;
import com.vova_cons.engine.scene.Scene;
import com.vova_cons.engine.scene_registry.SceneRegistry;
import com.vova_cons.sprite.Sprite;

import java.util.HashMap;
import java.util.Map;

public class StandartModule implements Module {
    private String id;
    private String uiResourcesPath;
    private Resources uiResources;
    private Map<String, Updateable> sceneMap = new HashMap<>();

    public StandartModule(String id, String uiResourcesCoreFile){
        this.id = id;
        this.uiResourcesPath = uiResourcesCoreFile;
    }

    @Override
    public void attach() {
        try {
            uiResources = new Resources(uiResourcesPath);
        } catch (Exception e) {
            Logger.log("Module + "+id + " ошибка при попытке создать модуль. Ошибка при попытке создать графические ресурсы",
                    Logger.ERROR);
        }
    }

    @Override
    public void detach() {
        uiResources = null;
    }

    @Override
    public String getID() {
        return id;
    }

    @Override
    public Scene getScene(String sceneId) {
        return (Scene) sceneMap.get(sceneId);
    }

    @Override
    public Sprite getSprite(String id) {
        if (uiResources != null)
            try {
                return uiResources.getSprite(id);
            } catch (Exception e) {
                Logger.log("Module " + this.id + ": Ошибка при попытке взять спрайт: " + id +
                        "(" + e.getMessage() + ")", Logger.ERROR);
            }
        else Logger.log("Module " + this.id + ": Ошибка при попытке взять спрайт: "+id+
                "(графические ресурсы не созданы)", Logger.ERROR);
        return null;
    }

    @Override
    public void addScenesToRegistry(SceneRegistry registry) {
        sceneMap.keySet().stream().forEach(scene -> registry.addScene(scene, id));
    }
}
