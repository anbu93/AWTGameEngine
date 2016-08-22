package com.vova_cons.physic.point;

import com.vova_cons.physic.Physic;
import junit.framework.Assert;
import junit.framework.TestCase;

import java.util.Random;

public class SimplePointTest extends TestCase {

    private static Random random = new Random(System.currentTimeMillis());

    public void testOffset() throws Exception {
        Point point = Physic.createPoint(10, 10);
        Point excepted = Physic.createPoint(15, 13);
        Point offset = Physic.createPoint(5, 3);
        Point actual = point.offset(offset);
        Assert.assertEquals(excepted, actual);
    }
    public void testOffset2() throws Exception {
        Point point = Physic.createPoint(10, 10);
        Point excepted = Physic.createPoint(15, 13);
        Point actual = point.offset(5, 3);
        Assert.assertEquals(excepted, actual);
    }

    public void testGetLength() throws Exception {
        float x = random.nextFloat() * 100 - 50;
        float y = random.nextFloat() * 100 - 50;
        float exceptedValue = (float) Math.sqrt(x*x + y*y);
        Point actualPoint = Physic.createPoint(x, y);
        Assert.assertEquals(exceptedValue, actualPoint.getLength());
    }

    public void testCreateVector() throws Exception {
        float exceptedX = random.nextFloat() * 100 - 50;
        float exceptedY = random.nextFloat() * 100 - 50;
        Point excepted = Physic.createPoint(exceptedX, exceptedY);
        float x = random.nextFloat();
        float y = random.nextFloat();
        Point from = Physic.createPoint(x, y);
        Point to = Physic.createPoint(x + exceptedX, y + exceptedY);
        Point actual = from.createVector(to);
        Assert.assertEquals(excepted, actual);
    }

    public void testCreateSubvector() throws Exception {
        Point original = Physic.createPoint(100, 100);
        Point actual = original.createSubvector((float) Math.sqrt(10*10 + 10*10));
        Point excepted = Physic.createPoint(10, 10);
        Assert.assertEquals(excepted, actual);
    }

    public void testGetX() throws Exception {
        float x = random.nextFloat()*100 -50;
        Point point = Physic.createPoint(x, 0);
        Assert.assertEquals(x, point.getX());
    }

    public void testGetY() throws Exception {
        float y = random.nextFloat()*100 -50;
        Point point = Physic.createPoint(0, y);
        Assert.assertEquals(y, point.getY());
    }

    public void testEquals() throws Exception {
        float x = random.nextFloat()*100 -50;
        float y = random.nextFloat()*100-50;
        Point original = Physic.createPoint(x, y);
        Point equalPoint = Physic.createPoint(x, y);
        Point notEqualPoint = Physic.createPoint(random.nextFloat()*100 - 50, random.nextFloat()*100 -50);
        Assert.assertTrue(original.equals(equalPoint));
        Assert.assertTrue(equalPoint.equals(original));
        Assert.assertFalse(original.equals(notEqualPoint));
    }

    public void testToString() throws Exception {
        float x = random.nextFloat() * 100 -50;
        float y = random.nextFloat() * 100 -50;
        String exceptedString = "(" + x + ";" + y + ")";
        Point actual = Physic.createPoint(x, y);
        Assert.assertEquals(exceptedString, actual.toString());
    }
}