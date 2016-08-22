package com.vova_cons.engine.module;

import com.vova_cons.engine.modules_loader.ModuleXml.ModuleRootXml;

/**
 * Created by Kons on 15.07.2016.
 */
public abstract class ModuleState implements Module {
    protected StateableModule parent;
    protected ModuleRootXml moduleSettingFile;

    public ModuleState(StateableModule parent, ModuleRootXml moduleSettingFile){
        this.parent = parent;
        this.moduleSettingFile = moduleSettingFile;
    }

    public abstract String state();

    @Override
    public String getID() {
        return parent.getID();
    }
}
