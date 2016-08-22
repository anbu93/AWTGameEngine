package com.vova_cons.engine.resources;

import com.vova_cons.engine.common.logger.Logger;
import com.vova_cons.sprite.Sprite;

public class Resources {
    private com.vova_cons.Resources uiResources;

    public Resources(String uiResourcesPath){
        try {
            uiResources = new com.vova_cons.Resources(uiResourcesPath);
        } catch (Exception e) {
            Logger.log("Не удалось загрузить графические ресурсы (" + e.getMessage() + ")", Logger.FATAL_ERROR);
        }
    }

    public Sprite getSprite(String spriteId) {
        try {
            return uiResources.getSprite(spriteId);
        } catch (Exception e) {
            Logger.log(
                    "Не удалось найти требуемый спрайт среди графических ресурсов: " + spriteId +
                            " (" + e.getMessage() + ")", Logger.ERROR);
            return null;
        }
    }
}