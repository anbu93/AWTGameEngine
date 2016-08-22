package com.vova_cons.engine.core;

import com.vova_cons.engine.module.Module;

public interface LoadedCore {
    void createMainLoop(long tickTime);
    void createWindow(String title, int width, int height);
    void addModule(Module module);
    void addScenesToRegistry();
    void setInitScene(String test);
}
