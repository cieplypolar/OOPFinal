package controller.inputs;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import GameStates.GameState;
import view.window.GamePanel;

public class KeyboardInputs implements KeyListener {

    private GamePanel gamePanel;

    public KeyboardInputs(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // TODO Auto-generated method stub
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (GameState.gamestate) {
            case MENU -> {

            }
            case GAMERUN,DEAD -> {
                gamePanel.getGame().getGameRun().keyReleased(e);
            }

        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (GameState.gamestate) {
            case MENU -> {

            }
            case GAMERUN -> {
                gamePanel.getGame().getGameRun().keyPressed(e);
            }
            case DEAD ->{}
        }
    }

}