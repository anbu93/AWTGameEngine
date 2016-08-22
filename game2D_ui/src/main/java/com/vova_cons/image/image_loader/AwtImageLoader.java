package com.vova_cons.image.image_loader;

import com.vova_cons.image.AwtImage;
import com.vova_cons.image.Image;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class AwtImageLoader implements ImageLoader {
    @Override
    public Image loadImage(String src) throws Exception {
        try {
            BufferedImage sourceImage = ImageIO.read(new File(src));
            java.awt.Image img = Toolkit.getDefaultToolkit().createImage(sourceImage.getSource());
            return new AwtImage(img);
        } catch (IOException e) {
            throw new Exception("Image file not found: " + src);
        }
    }
}
