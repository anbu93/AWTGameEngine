package com.vova_cons.engine.module;

import com.vova_cons.engine.Updateable;
import com.vova_cons.engine.modules_loader.ModuleXml.ModuleRootXml;
import com.vova_cons.engine.scene.Scene;
import com.vova_cons.engine.scene_registry.SceneRegistry;
import com.vova_cons.sprite.Sprite;

/**
 * Created by Kons on 15.07.2016.
 */
public class StateableModule implements Module {
    private String id;
    ModuleState state;

    public StateableModule(String id, ModuleRootXml settingFile){
        this.id = id;
        this.state = new DetachedModule(this, settingFile);
    }

    @Override
    public void attach() {
        state.attach();
    }
    @Override
    public void detach() {
        state.detach();
    }
    @Override
    public Scene getScene(String sceneId) {
        return state.getScene(sceneId);
    }
    @Override
    public void addScenesToRegistry(SceneRegistry registry) {
        state.addScenesToRegistry(registry);
    }

    @Override
    public Sprite getSprite(String id) {
        return state.getSprite(id);
    }

    @Override
    public String getID() {
        return this.id;
    }

    public void changeState(ModuleState state){
        this.state = state;
    }
}
