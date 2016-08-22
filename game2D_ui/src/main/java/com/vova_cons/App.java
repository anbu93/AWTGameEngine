package com.vova_cons;

import com.vova_cons.physic.Physic;
import com.vova_cons.physic.point.Point;
import com.vova_cons.physic.rect.Rect;
import com.vova_cons.sprite.Sprite;
import com.vova_cons.text.RGBColor;
import com.vova_cons.text.TextSprite;
import com.vova_cons.window.AwtWindow;
import com.vova_cons.window.Window;

/**
 * пример использования модуля
 */
public class App 
{
    public static void main( String[] args )
    {
        try {
            Window window = new AwtWindow("TEST", Physic.createRect(0,0, 800, 600));
            Resources resources = new Resources(Resources.ATLAS_SOURCE);
            Sprite sprite = resources.getSprite("test");
            Rect pos = Physic.createRect(0, 0, 150, 100);
            Point speed = Physic.createPoint(10, 10);
            float angle = 45;
            TextSprite text = TextSprite.create("Тестовая строка\nПожалуйста подождите", Physic.createRect(0, 0, 400, 100), 30, RGBColor.RED);
            Rect textPos = Physic.createRect(200, 0, 400, 100);
            while(true){
                window.clear();
                window.render(text, textPos);
                window.render(sprite, pos, angle);
                pos = pos.offset(speed);
                angle += 0.1;
                window.flush();
                sleep(100);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    private static void sleep(long ms){
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {}
    }
}
