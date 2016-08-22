package com.vova_cons.window;

import com.vova_cons.controller.Controller;
import com.vova_cons.physic.rect.Rect;
import com.vova_cons.sprite.Sprite;

public interface Window {
    /**
     * Создание буферного полотна и его очистка.
     */
    void clear();

    /**
     * замена текущего полотна на буферный (свап полотен)
     */
    void flush();

    /**
     * Установка контроллера-слушателя для окна (ввод)
     * @param controller контроллер.
     */
    void setController(Controller controller);

    /**
     * Отрисовка спрайта на окно
     * @param sprite отрисовываемый спрайт
     * @param pos позиция для отрисовка (позиция и размер)
     * @param angle угол наклона спрайта
     */
    void render(Sprite sprite, Rect pos, float angle);

    /**
     * Отрисовка спрайта на окно
     * @param sprite отрисовываемый спрайт
     * @param pos позиция для отрисовка (позиция и размер)
     */
    void render(Sprite sprite, Rect pos);

    /**
     * Изменяет заголовок окна
     * @param title новый заголовок
     */
    void setTitle(String title);

    /**
     * Устанавливает активность окна
     * @param condition состояние активности
     */
    void setVisible(boolean condition);

    /**
     * Установить новый размер окна
     * @param size размер окна (x и y игнорируются)
     */
    void setSize(Rect size);
}
