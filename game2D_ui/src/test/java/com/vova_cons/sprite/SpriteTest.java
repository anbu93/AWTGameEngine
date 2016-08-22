package com.vova_cons.sprite;

import com.vova_cons.controller.Controller;
import com.vova_cons.image.Image;
import com.vova_cons.physic.Physic;
import com.vova_cons.physic.rect.Rect;
import com.vova_cons.window.Drawable;
import com.vova_cons.window.Window;
import junit.framework.Assert;
import junit.framework.TestCase;

public class SpriteTest extends TestCase {

    public void testGetSpriteID() throws Exception {
        String id = "test";
        SpriteImpl sprite = new SpriteImpl(id, null, null);
        Assert.assertEquals(id, sprite.toString());
    }
    public void testDraw() throws Exception {
        String imageId = "test image";
        Image img = new ImageMock(imageId);
        SpriteImpl sprite = new SpriteImpl("test", img, null);
        WindowMock windowMock = new WindowMock();
        sprite.draw(windowMock, null, 45);
        Assert.assertEquals(imageId, windowMock.canvas);
    }
    public void testEqual() throws Exception {
        Sprite sprite = new SpriteImpl("id", new ImageMock("id"), Physic.createRect(0,0, 10, 10));
        Sprite sprite2 = new SpriteImpl("id", new ImageMock("id"), Physic.createRect(0,0, 10, 10));
        Sprite sprite3 = new SpriteImpl("id", new ImageMock("id"), Physic.createRect(0,0, 100, 100));
        Sprite sprite4 = new SpriteImpl("id", new ImageMock("not"), Physic.createRect(0,0, 10, 10));
        Assert.assertEquals(sprite, sprite2);
        Assert.assertFalse(sprite.equals(sprite3));
        Assert.assertFalse(sprite.equals(sprite4));
    }
    public void testSize() throws Exception {
        Sprite sprite = new SpriteImpl("id", new ImageMock("id"), Physic.createRect(0, 0, 15, 88));
        Assert.assertEquals(Physic.createRect(0, 0, 15, 88), sprite.getSize());
    }

    private class ImageMock implements Image {
        private String id;

        public ImageMock(String id){
            this.id = id;
        }
        @Override
        public void draw(Window window, Rect position, Rect texturePos, float angle) {
            WindowMock windowMock = (WindowMock) window;
            windowMock.drawImageMock(this);
        }

        @Override
        public boolean equals(Object obj) {
            if (obj instanceof ImageMock){
                return id.equals(((ImageMock) obj).id);
            }
            return false;
        }
    }

    private class WindowMock implements Window {
        private String canvas = "";

        @Override
        public void clear() {}

        @Override
        public void flush() {}

        @Override
        public void setController(Controller controller) {}

        @Override
        public void render(Sprite sprite, Rect pos, float angle) {
            sprite.draw(this, pos, angle);
        }

        @Override
        public void render(Sprite sprite, Rect pos) {}

        @Override
        public void setTitle(String title) {

        }

        @Override
        public void setVisible(boolean condition) {

        }

        @Override
        public void setSize(Rect size) {

        }

        void drawImageMock(ImageMock img){
            canvas += img.id;
        }

        @Override
        public String toString() {
            return canvas;
        }
    }
}
