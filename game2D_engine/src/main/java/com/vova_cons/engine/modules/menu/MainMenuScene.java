package com.vova_cons.engine.modules.menu;

import com.vova_cons.controller.Controller;
import com.vova_cons.engine.core.Core;
import com.vova_cons.engine.scene.Scene;
import com.vova_cons.physic.Physic;
import com.vova_cons.physic.point.Point;
import com.vova_cons.physic.rect.Rect;
import com.vova_cons.sprite.Sprite;

/**
 * Created by Kons on 15.07.2016.
 */
public class MainMenuScene extends Scene {
    private Sprite testImage1;
    private Sprite testImage2;
    private Point cursor = Physic.createPoint(0, 0);
    private Rect pos1;
    private Rect pos2;


    @Override
    public void construct(String ignoredArguments) {}

    @Override
    protected void concreteSceneAttach() {
        testImage1 = getModule().getSprite("test");
        testImage2 = getModule().getSprite("test");
        pos1 = testImage1.getSize();
        pos2 = testImage1.getSize().offset(0, 300);
    }

    @Override
    protected void concreteSceneDetach() {}

    @Override
    public void update(double elapsedTimeSec) {
        getWindow().render(testImage1, pos1);
        getWindow().render(testImage2, pos2);
    }

    @Override
    protected Controller getWindowController() {
        return new Controller() {
            @Override
            public void keyPressed(int key) {}

            @Override
            public void keyReleased(int key) {
                if (key == Controller.MOUSE_LEFT){
                    if (pos1.isInclude(cursor))
                        Core.core.changeScene("test_scene_1");
                    if (pos2.isInclude(cursor))
                        Core.core.changeScene("test_scene_2");
                }
                if (key == Controller.ESC)
                    Core.core.stopMainLoop();
            }

            @Override
            public void mousePosition(int x, int y) {
                cursor = Physic.createPoint(x, y);
            }
        };
    }
}
