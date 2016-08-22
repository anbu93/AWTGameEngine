package com.vova_cons.engine.scene_registry;

public interface SceneRegistry {
    void addScene(String scene, String module);
    String getModuleWithScene(String scene);
}
