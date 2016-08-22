package com.vova_cons.engine.core_loader;

/**
 * Created by Kons on 15.07.2016.
 */
public interface CoreLoader {
    static void loadFrom(String coreSettingFileXml){
        new CoreLoaderImpl().load(coreSettingFileXml);
    }
}
