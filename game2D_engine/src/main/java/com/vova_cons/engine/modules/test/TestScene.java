package com.vova_cons.engine.modules.test;

import com.vova_cons.controller.Controller;
import com.vova_cons.engine.core.Core;
import com.vova_cons.engine.scene.Scene;
import com.vova_cons.physic.Physic;
import com.vova_cons.physic.rect.Rect;
import com.vova_cons.sprite.Sprite;
import com.vova_cons.text.RGBColor;
import com.vova_cons.text.TextSprite;

/**
 * Created by Kons on 15.07.2016.
 */
public class TestScene extends Scene {
    private Sprite textSprite;
    private Rect pos;
    private float angle = 0;

    @Override
    public void construct(String arg) {
        pos = Physic.createRect(300, 300, 300, 50);
        textSprite = TextSprite.create(arg, Physic.createRect(0, 0, 300, 50), 30, RGBColor.RED);
    }

    @Override
    protected void concreteSceneAttach() {}
    @Override
    protected void concreteSceneDetach() {}

    @Override
    protected Controller getWindowController() {
        return new Controller() {
            @Override
            public void keyPressed(int i) {

            }

            @Override
            public void keyReleased(int i) {
                if (i == Controller.ESC)
                    Core.core.changeScene("main_menu");
            }

            @Override
            public void mousePosition(int i, int i1) {}
        };
    }

    @Override
    public void update(double elapsedTimeSec) {
        getWindow().render(textSprite, pos, angle);
        angle += 0.1;
    }
}
