package com.vova_cons.engine.module;

import com.vova_cons.engine.scene.Scene;
import com.vova_cons.engine.scene_registry.SceneRegistry;
import com.vova_cons.sprite.Sprite;

public interface Module {
    /** Метод выполняемый при присоединении модуля к ядру как активного */
    void attach();

    /** Метод выполняемый при отсоединении модуля из ядра (переход в неактивный режим) */
    void detach();

    /** Возвращает идентификатор модуля */
    String getID();

    Scene getScene(String sceneId);

    void addScenesToRegistry(SceneRegistry registry);

    Sprite getSprite(String id);

}
