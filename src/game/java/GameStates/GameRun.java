package GameStates;

import controller.loop.Game;
import model.entities.Player;
import view.level.LevelManager;
import view.player.PlayerManager;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class GameRun extends State implements  StateInterface {
    private PlayerManager player;
    private LevelManager level;

    public GameRun(Game game) {
        super(game);
    }
    public void initClasses() {
        player = new PlayerManager(this.game, 100, 100);
        level = new LevelManager(this.game);
    }

    @Override
    public void update() {
        level.update();
        player.update();
    }

    @Override
    public void render() {

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
                game.getPlayerManager().getPlayer().setUp(false);
                break;
            case KeyEvent.VK_LEFT:
                game.getPlayerManager().getPlayer().setLeft(false);
                break;
            case KeyEvent.VK_DOWN:
                game.getPlayerManager().getPlayer().setDown(false);
                break;
            case KeyEvent.VK_RIGHT:
                game.getPlayerManager().getPlayer().setRight(false);
                break;
            case KeyEvent.VK_X:
                game.getPlayerManager().getPlayer().setAttacking(false);
                break;
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
                game.getPlayerManager().getPlayer().setUp(true);
                break;
            case KeyEvent.VK_LEFT:
//                gamePanel.getGame().getPlayerManager().getPlayer().setFacing(Constants.PlayerConstants.Facing.LEFT);
                game.getPlayerManager().getPlayer().setLeft(true);
                break;
            case KeyEvent.VK_DOWN:
                game.getPlayerManager().getPlayer().setDown(true);
                break;
            case KeyEvent.VK_RIGHT:
//                gamePanel.getGame().getPlayerManager().getPlayer().setFacing(Constants.PlayerConstants.Facing.RIGHT);
                game.getPlayerManager().getPlayer().setRight(true);
                break;
            case KeyEvent.VK_X:
                game.getPlayerManager().getPlayer().setAttacking(true);
                break;
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }
    public PlayerManager getPlayerManager(){
        return player;
    }
    public LevelManager getLevelManager(){
        return level;
    }
}
