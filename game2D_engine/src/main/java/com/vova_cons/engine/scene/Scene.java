package com.vova_cons.engine.scene;

import com.vova_cons.controller.Controller;
import com.vova_cons.engine.Updateable;
import com.vova_cons.engine.common.logger.Logger;
import com.vova_cons.engine.core.Core;
import com.vova_cons.engine.module.Module;
import com.vova_cons.window.Window;

/**
 * Created by Kons on 15.07.2016.
 */
public abstract class Scene implements Updateable {
    protected static NullController NULL_CONTROLLER = new NullController();

    private Module module;
    private String id = "unknown";
    private Window window;

    public Scene(){
        window = Core.core.getWindow();
    }

    public void setID(String id){
        this.id = id;
    }

    public void setParentModule(Module module){
        this.module = module;
    }

    public abstract void construct(String arg);

    public void attach() {
        this.window = Core.core.getWindow();
        this.window.setController(getWindowController());
        concreteSceneAttach();
    }

    public void detach(){
        concreteSceneDetach();
    }

    public Module getModule(){
        return module;
    }

    public Window getWindow(){
        return window;
    }

    protected abstract void concreteSceneAttach();
    protected abstract void concreteSceneDetach();

    protected abstract Controller getWindowController();

    public String getID(){
        return id;
    }
}
