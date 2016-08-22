package com.vova_cons.image;

import com.vova_cons.image.image_loader.AwtImageLoader;
import com.vova_cons.image.image_loader.ImageLoader;
import junit.framework.Assert;
import junit.framework.TestCase;

public class ImageLoaderTest extends TestCase {

    public void testSuccessLoading() throws Exception {
        ImageLoader loader = new AwtImageLoader();
        try {
            loader.loadImage("img/test.png");
        } catch (Exception assertionFail){
            Assert.fail();
        }
    }

    public void testUnsuccessLoading() throws Exception {
        ImageLoader loader = new AwtImageLoader();
        try {
            loader.loadImage("img/unknown.png");
            Assert.fail();
        } catch (Exception assertionFail){
            //excepted exception on this. Test success.
        }
    }
}
