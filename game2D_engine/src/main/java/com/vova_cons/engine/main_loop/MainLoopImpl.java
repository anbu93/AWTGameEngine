package com.vova_cons.engine.main_loop;

import com.vova_cons.engine.common.Clock;
import com.vova_cons.engine.common.logger.Logger;
import com.vova_cons.engine.scene.Scene;
import com.vova_cons.window.Window;

public class MainLoopImpl implements MainLoop {
    private final Object lockObject = new Object();
    private Window window;
    private Scene activeScene;
    private long tickTime;
    private Clock clock = new Clock();
    private boolean isRunning = false;

    public MainLoopImpl(long tickTime) {
        this.tickTime = tickTime;
    }

    @Override
    public void run() {
        clock.restartSeconds();
        isRunning = true;
        if (window == null)
            Logger.log("Не установлено окно для главного цикла", Logger.ERROR);
        if (activeScene == null)
            Logger.log("Не установлена активная сцена", Logger.ERROR);
        while(isRunning){
            synchronized (lockObject) {
                window.clear();
                if (activeScene != null) activeScene.update(clock.restartSeconds());
                window.flush();
            }
            Clock.sleepMillis(tickTime);
        }
    }

    @Override
    public void setScene(Scene scene) {
        synchronized (lockObject) {
            if (activeScene != null) this.activeScene.detach();
            this.activeScene = scene;
            if (activeScene == null)
                Logger.log("Установка activeScene для главного цикла на null", Logger.ERROR);
            else this.activeScene.attach();
        }
    }

    @Override
    public void setWindow(Window window) {
        synchronized (lockObject) {
            this.window = window;
        }
    }

    @Override
    public boolean isRunning() {
        return isRunning;
    }

    @Override
    public void stop(String message) {
        synchronized (lockObject) {
            isRunning = false;
        }
        Logger.log("Остановка главного цикла: " + message, Logger.INFO);
    }
}
