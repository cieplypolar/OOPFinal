package controller.inputs;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import utilities.constants.Constants;
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
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
                gamePanel.getGame().getPlayerManager().getPlayer().setUp(false);
                break;
            case KeyEvent.VK_LEFT:
                gamePanel.getGame().getPlayerManager().getPlayer().setLeft(false);
                break;
            case KeyEvent.VK_DOWN:
                gamePanel.getGame().getPlayerManager().getPlayer().setDown(false);
                break;
            case KeyEvent.VK_RIGHT:
                gamePanel.getGame().getPlayerManager().getPlayer().setRight(false);
                break;
            case KeyEvent.VK_X:
                gamePanel.getGame().getPlayerManager().getPlayer().setAttacking(false);
                break;
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
                gamePanel.getGame().getPlayerManager().getPlayer().setUp(true);
                break;
            case KeyEvent.VK_LEFT:
                gamePanel.getGame().getPlayerManager().getPlayer().setFacing(Constants.PlayerConstants.Facing.LEFT);
                gamePanel.getGame().getPlayerManager().getPlayer().setLeft(true);
                break;
            case KeyEvent.VK_DOWN:
                gamePanel.getGame().getPlayerManager().getPlayer().setDown(true);
                break;
            case KeyEvent.VK_RIGHT:
                gamePanel.getGame().getPlayerManager().getPlayer().setFacing(Constants.PlayerConstants.Facing.RIGHT);
                gamePanel.getGame().getPlayerManager().getPlayer().setRight(true);
                break;
            case KeyEvent.VK_X:
                gamePanel.getGame().getPlayerManager().getPlayer().setAttacking(true);
                break;
        }
    }
}