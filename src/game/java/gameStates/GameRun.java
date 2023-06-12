package gameStates;

import _utilities.loaders.ImageHandler;
import controller.game.LevelManager;
import controller.game.ObjectManager;
import controller.game.PlayerManager;
import controller.game.SuperiorEnemyManager;
import controller.loop.Game;
import view.enemies.SuperiorEnemyView;


import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

import static _utilities.constants.Constants.Paths.PLAYING_BACKGROUND;
import static _utilities.constants.Constants.ViewConstants.*;

public class GameRun extends State implements  StateInterface {

    private PlayerManager player;
    private LevelManager level;
    private ObjectManager objects;

    private SuperiorEnemyManager enemyManager;
    private SuperiorEnemyView enemyView;

    private BufferedImage backgroundImg;

    public GameRun(Game game) {
        super(game);
        backgroundImg = ImageHandler.importImg(PLAYING_BACKGROUND);

    }
    public void initClasses() {
        player = new PlayerManager(this.game, 200 * SCALE, 1152 * SCALE);
        level = new LevelManager(this.game);
        enemyManager = new SuperiorEnemyManager(this);
        enemyView = new SuperiorEnemyView(enemyManager);
        objects = new ObjectManager(this.game);
        enemyManager = new SuperiorEnemyManager(this);
        enemyView = new SuperiorEnemyView(enemyManager);
    }

    public void drawBackground(Graphics g){
        g.drawImage(backgroundImg, 0, 0, GAME_WIDTH, GAME_HEIGHT, null);
    }
    @Override
    public void update() {
        level.update();
        objects.update();
        player.update();
        enemyManager.update(level.getLevel().getLevelLayout(), player.getPlayer());
    }

    @Override
    public void render(Graphics g) {
        drawBackground(g);
        int xLvlOffset=game.getxLvlOffset();
        int yLvlOffset= game.getyLvlOffset();

        level.draw(g, xLvlOffset, yLvlOffset);
        player.getPlayerView().render(g, xLvlOffset, yLvlOffset, getPlayerManager().getAniIndex());
        objects.draw(g, xLvlOffset, yLvlOffset);

        enemyView.draw(g, game.getxLvlOffset(), game.getyLvlOffset());
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

    public ObjectManager getObjectManager() {
        return objects;
    }

    public void setGameOver(boolean b) {

    }

    public void checkEnemyHit(Rectangle2D.Float attackBox) {
        enemyManager.checkEnemyHit(attackBox);
    }
}
