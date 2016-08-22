package com.vova_cons.engine.core;

import com.vova_cons.engine.module.AttachedModule;
import com.vova_cons.window.Window;

public interface Core {
    Core core = new CoreImpl();

    void changeScene(String sceneId);

    void startMainLoop();
    void stopMainLoop();

    Window getWindow();
}
