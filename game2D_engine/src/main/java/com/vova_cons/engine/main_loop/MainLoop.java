package com.vova_cons.engine.main_loop;

import com.vova_cons.engine.scene.Scene;
import com.vova_cons.window.Window;

public interface MainLoop extends Runnable {
    static MainLoop create(long tickTime){
        return new MainLoopImpl(tickTime);
    }

    void setScene(Scene scene);
    void setWindow(Window window);

    boolean isRunning();

    void stop(String message);
}
