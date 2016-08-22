package com.vova_cons.physic.rect;

import com.vova_cons.physic.Physic;
import com.vova_cons.physic.point.Point;
import junit.framework.Assert;
import junit.framework.TestCase;

import java.util.Random;

public class TopLeftDefineRectangleTest extends TestCase {
    private static Random random = new Random(System.currentTimeMillis());

    private static float getRandomValue(){
        return random.nextFloat() * 201 - 100;
    }

    public void testGetCenter() throws Exception {
        float x = getRandomValue();
        float y = getRandomValue();
        Rect rect = Physic.createRect(0,0, x, y);
        Point excepted = Physic.createPoint(x/2, y/2);
        Assert.assertEquals(excepted, rect.getCenter());
    }

    public void testGetTopLeft() throws Exception {
        float x = getRandomValue();
        float y = getRandomValue();
        Rect rect = Physic.createRectFromCenter(x/2, y/2, x, y);
        Point excepted = Physic.createPoint(0, 0);
        Assert.assertEquals(excepted, rect.getTopLeft());
    }

    public void testGetSize() throws Exception {
        float x = getRandomValue();
        float y = getRandomValue();
        Rect rect = Physic.createRect(0,0, x, y);
        Point exceptedSize = Physic.createPoint(x, y);
        Assert.assertEquals(exceptedSize, rect.getSize());
    }

    public void testGetWidth() throws Exception {
        float w = getRandomValue();
        Rect rect = Physic.createRect(getRandomValue(), getRandomValue(), w, getRandomValue());
        Assert.assertEquals(w, rect.getWidth());
    }

    public void testGetHeight() throws Exception {
        float h = getRandomValue();
        Rect rect = Physic.createRect(getRandomValue(), getRandomValue(), getRandomValue(), h);
        Assert.assertEquals(h, rect.getHeight());
    }

    public void testOffset() throws Exception {
        float x = getRandomValue();
        float y = getRandomValue();
        float w = getRandomValue();
        float h = getRandomValue();
        float offsetX = getRandomValue();
        float offsetY = getRandomValue();
        Rect original = Physic.createRect(x, y, w, h);
        Rect excepted = Physic.createRect(x+offsetX, y+offsetY, w, h);
        Rect actual = original.offset(offsetX, offsetY);
        Assert.assertEquals(excepted, actual);
    }

    public void testIsCollised() throws Exception {
        Rect original = Physic.createRect(getRandomValue(), getRandomValue(), 100, 100);
        includeCollisionTest(original);
        nonCollisionTest(original);
        includedTwoPoitCollisionTest(original);
        nonIncludedCollisionTest(original);
    }
    private void includeCollisionTest(Rect original) throws Exception {
        Rect includedRect = Physic.createRect(original.getTopLeft().offset(5, 5), 50, 50);
        Assert.assertTrue("included collision", original.isCollised(includedRect));
    }
    private void nonCollisionTest(Rect original) throws Exception {
        Rect notCollisedRect = original.offset(150, -150);
        Assert.assertFalse("not collision", original.isCollised(notCollisedRect));
    }
    private void includedTwoPoitCollisionTest(Rect original) throws Exception {
        Rect rect = Physic.createRect(original.getTopLeft().offset(10, 10), 50, 200);
        Assert.assertTrue("include two points", original.isCollised(rect));
    }
    private void nonIncludedCollisionTest(Rect original) throws Exception {
        Rect rect = Physic.createRect(original.getTopLeft().offset(-10, 10), 200, 50);
        Assert.assertTrue("non included collision", original.isCollised(rect));
    }

    public void testIsInclude() throws Exception {
        Rect rectangle = Physic.createRect(0,0, 100, 100);
        Point included = Physic.createPoint(50, 50);
        Point notIncluded = Physic.createPoint(-10, 100);
        Point onLine = Physic.createPoint(0, 55);
        Assert.assertTrue("included point", rectangle.isInclude(included));
        Assert.assertFalse("not included point", rectangle.isInclude(notIncluded));
        Assert.assertTrue("on line point", rectangle.isInclude(onLine));

    }

    public void testScaledRect() throws Exception {
        Random random = new Random(System.currentTimeMillis());
        Rect original = Physic.createRect(random.nextFloat() * 100, random.nextFloat() * 100, 100, 200);
        Rect scaledRect = original.scaledRect((float)0.5);
        Assert.assertEquals(Physic.createPoint(50, 100), scaledRect.getSize());
    }
}