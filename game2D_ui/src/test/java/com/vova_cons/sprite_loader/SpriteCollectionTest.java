package com.vova_cons.sprite_loader;

import com.vova_cons.image.AwtImage;
import com.vova_cons.physic.Physic;
import com.vova_cons.sprite.Sprite;
import com.vova_cons.sprite.SpriteImpl;
import junit.framework.Assert;
import junit.framework.TestCase;

import java.util.LinkedList;
import java.util.List;

public class SpriteCollectionTest extends TestCase {
    Sprite sprite1 = new SpriteImpl("1", new AwtImage(null), Physic.createRect(0,0, 10, 10));
    Sprite sprite2 = new SpriteImpl("2", new AwtImage(null), Physic.createRect(0,0, 10, 20));
    Sprite sprite3 = new SpriteImpl("3", new AwtImage(null), Physic.createRect(0,0, 10, 30));

    private SpriteCollection getCollection(){
        List<Sprite> sprites = new LinkedList<>();
        sprites.add(sprite1);
        sprites.add(sprite2);
        sprites.add(sprite3);
        return new MapSpriteCollection(sprites);
    }

    public void testGetSprite() throws Exception {
        SpriteCollection collection = getCollection();
        Assert.assertEquals(sprite1, collection.getSprite("1"));
        Assert.assertEquals(sprite2, collection.getSprite("2"));
        Assert.assertEquals(sprite3, collection.getSprite("3"));
    }
    public void testOnFail() throws Exception {
        SpriteCollection collection = getCollection();
        try {
            collection.getSprite("4");
            Assert.fail();
        } catch (Exception assertionDone){}
    }
}
