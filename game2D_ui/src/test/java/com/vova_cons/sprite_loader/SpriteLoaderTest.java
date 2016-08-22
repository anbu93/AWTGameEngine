package com.vova_cons.sprite_loader;

import com.vova_cons.Resources;
import com.vova_cons.sprite.Sprite;
import junit.framework.Assert;
import junit.framework.TestCase;

import java.util.List;

public class SpriteLoaderTest extends TestCase {
    public void testLoad() throws Exception {
        SpriteLoader loader = new XmlSpriteLoader();
        List<Sprite> result = loader.load(Resources.ATLAS_SOURCE);
        Assert.assertTrue(result.size()!= 0);
        SpriteCollection collection = new MapSpriteCollection(result);
        Assert.assertEquals("test", collection.getSprite("test").toString());
    }
}
