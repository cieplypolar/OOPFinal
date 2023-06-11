package controller.inputs;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import GameStates.GameState;
import view.window.GamePanel;

public class MouseInputs implements MouseListener, MouseMotionListener {

    private GamePanel gamePanel;

    public MouseInputs(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        switch (GameState.gamestate) {
            case MENU -> {
                gamePanel.getGame().getMenu().mouseMoved(e);
            }
            case GAMERUN -> {

            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        switch (GameState.gamestate) {
            case MENU -> {
                gamePanel.getGame().getMenu().mouseClicked(e);
            }
            case GAMERUN -> {
            }
        }
    }
    @Override
    public void mousePressed(MouseEvent e) {
        switch (GameState.gamestate) {
            case MENU -> {
                gamePanel.getGame().getMenu().mousePressed(e);
            }
            case GAMERUN -> {

            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        switch (GameState.gamestate) {
            case MENU -> {
                gamePanel.getGame().getMenu().mouseReleased(e);
            }
            case GAMERUN -> {

            }
        }

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseExited(MouseEvent e) {
        // TODO Auto-generated method stub

    }

}