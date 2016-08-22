package com.vova_cons.controller;

public interface Controller {
    int OTHER = -5;
    int SPACE = 0;
    int ESC = -1;
    int ENTER = 1;
    int UP = 11;
    int DOWN = 12;
    int LEFT = 13;
    int RIGHT = 14;
    int W = 21;
    int S = 22;
    int A = 23;
    int D = 24;
    int F = 25;
    int G = 26;
    int H = 27;
    int MOUSE_LEFT = -2;
    int MOUSE_RIGHT = -3;
    int MOUSE_WHEEL = -4;

    void keyPressed(int keyCode);

    void keyReleased(int keyCode);

    void mousePosition(int x, int y);
}
