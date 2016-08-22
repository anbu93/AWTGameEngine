package com.vova_cons;

import com.vova_cons.sprite.Sprite;
import junit.framework.Assert;
import junit.framework.TestCase;

public class ResourceTest extends TestCase {
    public void testGetSprite() throws Exception {
        Resources resources = new Resources(Resources.ATLAS_SOURCE);
        Sprite sprite = resources.getSprite("test");
        Assert.assertEquals("test", sprite.toString());
    }
}
