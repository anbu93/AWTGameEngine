package com.vova_cons.window;

import com.vova_cons.controller.Controller;
import com.vova_cons.physic.rect.Rect;
import com.vova_cons.sprite.Sprite;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;

public class AwtWindow extends Canvas implements Window {
    public static final long serialVersionUID = 1L;
    private Graphics window = null;
    private AwtController awtController;

    private JFrame frame = null;
    private Rect size;
    private String title;

    public AwtWindow(String title, Rect size){
        this.title = title;
        this.size = size;
        initializeAWT();
        awtController = new AwtController();
        addMouseListener(awtController);
        addKeyListener(awtController);
        addMouseMotionListener(awtController);
    }

    private void initializeAWT(){
        setPreferredSize(new Dimension((int)size.getWidth() - 10, (int)size.getHeight()- 10));
        JFrame frame = new JFrame(title);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.add(this, BorderLayout.CENTER);
        frame.pack();
        frame.setResizable(false);
        frame.setVisible(true);
        this.frame = frame;
    }

    @Override
    public void flush() {
        window.dispose();
        getBufferStrategy().show();
        window = null;
    }

    @Override
    public void clear() {
        window = getWindow();
        window.setColor(Color.BLACK);
        window.fillRect(0, 0, getWidth(), getHeight());
    }

    public void setController(Controller controller) {
        awtController.setController(controller);
    }

    @Override
    public void render(Sprite sprite, Rect pos, float angle) {
        sprite.draw(this, pos, angle);
    }

    @Override
    public void render(Sprite sprite, Rect pos) {
        render(sprite, pos, 0);
    }

    private Graphics getWindow(){
        BufferStrategy bufferStrategy = getBufferStrategy();
        if (bufferStrategy == null) {
            createBufferStrategy(2);
            requestFocus();
            return getWindow();
        }
        return bufferStrategy.getDrawGraphics();
    }

    public Graphics getWindowGraphics(){
        if (window == null)
            clear();
        return window;
    }

    public void setTitle(String title){
        frame.setTitle(title);
    }

    public void setVisible(boolean condition){
        frame.setVisible(false); // TODO: 7/5/2016 как то закрыть окно!
    }

    @Override
    public void setSize(Rect size) {
        frame.setSize((int)size.getWidth(), (int)size.getHeight());
    }
}
