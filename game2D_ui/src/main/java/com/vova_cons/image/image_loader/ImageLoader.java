package com.vova_cons.image.image_loader;

import com.vova_cons.image.Image;

public interface ImageLoader {
    Image loadImage(String src) throws Exception;
}
