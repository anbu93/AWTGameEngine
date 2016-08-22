package com.vova_cons.engine.core;

import com.vova_cons.Resources;
import com.vova_cons.engine.common.logger.Logger;
import com.vova_cons.engine.main_loop.MainLoop;
import com.vova_cons.engine.main_loop.MainLoopImpl;
import com.vova_cons.engine.module.Module;
import com.vova_cons.engine.scene.Scene;
import com.vova_cons.engine.scene_registry.MapSceneRegistry;
import com.vova_cons.engine.scene_registry.SceneRegistry;
import com.vova_cons.physic.Physic;
import com.vova_cons.window.Window;

import java.util.concurrent.ConcurrentHashMap;

class CoreImpl implements Core, LoadedCore {
    private MainLoop mainLoop;
    private Window window;
    private Module activeModule;
    private ConcurrentHashMap<String, Module> moduleMap = new ConcurrentHashMap<>();
    private Thread mainLoopThread;
    private SceneRegistry sceneRegistry;

    // Имплементация интерфейса Core
    @Override
    public void changeScene(String sceneId) {
        String neededModule = sceneRegistry.getModuleWithScene(sceneId);
        if (neededModule == null) {
            Logger.log("Не удалось сменить сцену на " + sceneId +
                            " так как такая сцена не зарегестрирована в реестре сцен.",
                    Logger.ERROR);
            return;
        }
        if (activeModule == null || neededModule != activeModule.getID()) {
            // TODO: 16.07.2016 сплэш экран тут
            Logger.log("Смена модуля на " + neededModule, Logger.INFO);
            setModule(neededModule);
        }
        Scene scene = activeModule.getScene(sceneId);
        if (scene == null){
            Logger.log("Не удалось сменить сцену на " + sceneId +
                            " так как такая сцена не найдена в текущем модуле " + activeModule.getID(),
                    Logger.ERROR);
            return;
        }
        mainLoop.setScene(scene);
    }
    @Override
    public void startMainLoop(){
        if (mainLoop != null) {
            mainLoopThread = new Thread(mainLoop);
            mainLoopThread.start();
        } else {
            Logger.log("Попытка запустить главный цикл, без предварительного создания", Logger.ERROR);
        }
    }
    @Override
    public void stopMainLoop() {
        if (mainLoop != null) {
            if (mainLoop.isRunning()) {
                mainLoop.stop("По вызову Core.stopMainLoop()");
                destroy();
            }
            else Logger.log("Попытка остановить главный цикл, без предварительного его запуска", Logger.ERROR);
        }
        else Logger.log("Попытка остановить главный цикл, без предварительного создания", Logger.ERROR);
    }

    @Override
    public Window getWindow() {
        return window;
    }

    public void setModule(String moduleId){
        if (moduleMap.containsKey(moduleId)) {
            if (activeModule != null) activeModule.detach();
            activeModule = moduleMap.get(moduleId);
            if (activeModule != null) activeModule.attach();
            else Logger.log("Текущий модуль равен null!", Logger.ERROR);
        } else
            Logger.log("Попытка установить ядро на несуществующий модуль: " + moduleId, Logger.ERROR);
    }

    private void destroy(){
        window.setVisible(false);
        mainLoopThread = null;
        Logger.log("Успешное завершение работы", Logger.INFO);
        System.exit(0);
    }

    //////// Имплементация LoadedCore //////

    @Override
    public void createMainLoop(long tickTime) {
        this.mainLoop = new MainLoopImpl(tickTime);
    }

    @Override
    public void createWindow(String title, int width, int height) {
        this.window = Resources.createWindow(title, Physic.createRect(0,0,width,height));
        mainLoop.setWindow(window);
    }

    @Override
    public void addModule(Module module) {
        if (moduleMap.containsKey(module.getID()))
            Logger.log("Ошибка добавления модуля в ядро. Ядро с таким айди уже существует: " + module.getID(),
                    Logger.ERROR);
        else
            moduleMap.put(module.getID(), module);
    }

    @Override
    public void addScenesToRegistry() {
        this.sceneRegistry = new MapSceneRegistry();
        moduleMap.values().stream().forEach(module -> module.addScenesToRegistry(sceneRegistry));
    }

    @Override
    public void setInitScene(String initSceneID) {
        this.changeScene(initSceneID);
    }
}
