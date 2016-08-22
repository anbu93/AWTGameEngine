package com.vova_cons;

import com.vova_cons.image.Image;
import com.vova_cons.image.image_loader.AwtImageLoader;
import com.vova_cons.image.image_loader.ImageLoader;
import com.vova_cons.physic.rect.Rect;
import com.vova_cons.sprite.Sprite;
import com.vova_cons.sprite.SpriteImpl;
import com.vova_cons.sprite_loader.MapSpriteCollection;
import com.vova_cons.sprite_loader.SpriteCollection;
import com.vova_cons.sprite_loader.SpriteLoader;
import com.vova_cons.sprite_loader.XmlSpriteLoader;
import com.vova_cons.window.AwtWindow;
import com.vova_cons.window.Window;

public class Resources {
    /** Путь по умолчанию для ресурсов */
    public static String ATLAS_SOURCE = "img/source.xml";
    /** Загрузчик изображений (Загружает изображения с пути) */
    public static ImageLoader imageLoader = new AwtImageLoader();

    private SpriteCollection spriteCollection;

    /**
     * Конструктор для ресурсов
     * @param source путь (относительный от project-path) к файлу xml описывающую ресурсы
     * @throws Exception при ошибке загрузки ресурсов.
     */
    public Resources(String source) throws Exception {
        SpriteLoader loader = new XmlSpriteLoader();
        spriteCollection = new MapSpriteCollection(loader.load(source));
    }

    /**
     * Взятие спрайта из коллекции загруженных спрайтов
     * @param id идентификатор спрайта
     * @return спрайт из коллекции
     * @throws Exception при отсутствии спрайта в коллекции
     */
    public Sprite getSprite(String id) throws Exception {
        return spriteCollection.getSprite(id);
    }

    /**
     * Создание окна.
     * @param title Заголовок окна
     * @param size Размеры окна (x и y пренебрегаются)
     * @return Интерфейсное готового окна. (реализация скрыто внутри метода)
     */
    public static Window createWindow(String title, Rect size){
        return new AwtWindow(title, size);
    }

    /**
     * Создает спрайт без использование xml-файла конфигураций
     * @param id идентификатор изображения
     * @param imagePath полный путь к изображению
     * @param textureRect прямоугольник текстуры */
    public static Sprite createSpriteFromImage(String id, String imagePath, Rect textureRect) throws Exception {
        Image image = imageLoader.loadImage(imagePath);
        Sprite sprite = new SpriteImpl(id, image, textureRect);
        return sprite;
    }
}
