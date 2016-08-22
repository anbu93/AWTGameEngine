package com.vova_cons.engine.scene_registry;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Kons on 15.07.2016.
 */
public class MapSceneRegistry implements SceneRegistry {
    private Map<String, String> registry = new HashMap<>();

    @Override
    public void addScene(String scene, String module) {
        registry.put(scene, module);
    }

    @Override
    public String getModuleWithScene(String scene) {
        return registry.get(scene);
    }
}
