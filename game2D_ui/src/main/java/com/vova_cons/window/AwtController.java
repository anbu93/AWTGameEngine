package com.vova_cons.window;

import com.vova_cons.controller.Controller;

import java.awt.event.*;

public class AwtController implements KeyListener, MouseListener, MouseMotionListener {
    private final int PRESS = 1;
    private final int RELEASE = 2;
    private Controller controller = null;

    public void setController(Controller controller){
        this.controller = controller;
    }
    private void sendEventOfState(int keyCode, int state){
        if (controller == null) return;
        switch(state) {
            case PRESS:
                controller.keyPressed(keyCode); break;
            case RELEASE:
                controller.keyReleased(keyCode); break;
            default:
                // do nothing, it's normal. (ignore key)
        }
    }

    /** Key listener */
    @Override
    public void keyPressed(KeyEvent e) {
        handleKeyEvent(e, PRESS);
    }
    @Override
    public void keyReleased(KeyEvent e) {
        handleKeyEvent(e, RELEASE);
    }
    private void handleKeyEvent(KeyEvent event, int state){
        switch(event.getKeyCode()){
            case KeyEvent.VK_UP:
                sendEventOfState(Controller.UP, state); break;
            case KeyEvent.VK_DOWN:
                sendEventOfState(Controller.DOWN, state); break;
            case KeyEvent.VK_LEFT:
                sendEventOfState(Controller.LEFT, state); break;
            case KeyEvent.VK_RIGHT:
                sendEventOfState(Controller.RIGHT, state); break;
            case KeyEvent.VK_W:
                sendEventOfState(Controller.W, state); break;
            case KeyEvent.VK_S:
                sendEventOfState(Controller.S, state); break;
            case KeyEvent.VK_A:
                sendEventOfState(Controller.A, state); break;
            case KeyEvent.VK_D:
                sendEventOfState(Controller.D, state); break;
            case KeyEvent.VK_SPACE:
                sendEventOfState(Controller.SPACE, state); break;
            case KeyEvent.VK_ESCAPE:
                sendEventOfState(Controller.ESC, state); break;
            case KeyEvent.VK_ENTER:
                sendEventOfState(Controller.ENTER, state); break;
            case KeyEvent.VK_F:
                sendEventOfState(Controller.F, state); break;
            case KeyEvent.VK_G:
                sendEventOfState(Controller.G, state); break;
            case KeyEvent.VK_H:
                sendEventOfState(Controller.H, state); break;
            default:
                sendEventOfState(Controller.OTHER, state); break;
        }
    }


    /** Mouse listener */
    @Override
    public void mousePressed(MouseEvent e) {
        handleMouseEvent(e, PRESS);
    }
    @Override
    public void mouseReleased(MouseEvent e) {
        handleMouseEvent(e, RELEASE);
    }
    @Override
    public void mouseDragged(MouseEvent e) {
        if (controller != null) controller.mousePosition(e.getX(), e.getY());
    }
    @Override
    public void mouseMoved(MouseEvent e) {
        if (controller != null) controller.mousePosition(e.getX(), e.getY());
    }
    private void handleMouseEvent(MouseEvent e, int state){
        switch(e.getButton()){
            case MouseEvent.BUTTON1:
                sendEventOfState(Controller.MOUSE_LEFT, state); break;
            case MouseEvent.BUTTON2:
                sendEventOfState(Controller.MOUSE_WHEEL, state); break;
            case MouseEvent.BUTTON3:
            sendEventOfState(Controller.MOUSE_RIGHT, state); break;
            default:
                // It's normal. Other keys for mouse are ignored.
        }
    }

    /**  Ignored methods  */
    @Override
    public void keyTyped(KeyEvent e) {}
    @Override
    public void mouseEntered(MouseEvent e) {}
    @Override
    public void mouseExited(MouseEvent e) {}
    @Override
    public void mouseClicked(MouseEvent e) {}
}
