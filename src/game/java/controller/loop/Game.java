package controller.loop;

import GameStates.GameOver;
import GameStates.GameRun;
import GameStates.GameState;
import controller.game.LevelManager;
import controller.game.ObjectManager;
import controller.game.PlayerManager;
import view.window.GamePanel;
import view.window.GameWindow;
import GameStates.Menu;
import java.awt.*;

import static _utilities.constants.Constants.ViewConstants.*;
import static _utilities.loaders.ImageHandler.importImg;

public class Game implements Runnable {

    private GameWindow gameWindow;
    private GamePanel gamePanel;
    private Thread gameThread;
    private final int FPS_SET = 120;
    private final int UPS_SET = 200;
    private GameRun gamerun;
    private Menu menu;
    private GameOver gameover;
    public GameOver getGameover() {
        return gameover;
    }

    public void setGameover(GameOver gameover) {
        this.gameover = gameover;
    }



    public int getxLvlOffset() {
        return xLvlOffset;
    }

    public int getyLvlOffset() {
        return yLvlOffset;
    }

    private int xLvlOffset;
    private int letfBorder = (int) (0.2 * GAME_WIDTH);
    private int rightBorder = (int) (0.8 * GAME_WIDTH);
    private int lvlTilesWidth = importImg("/level.graphics/bigggglvl.png").getWidth();
    private int maxTilesOffsetHor = lvlTilesWidth - WIDTH_IN_TILES;
    private int maxLvlOffsetHor = maxTilesOffsetHor * TILES_SIZE * SCALE;
    private int yLvlOffset;
    private int upBorder = (int) (0.2 * GAME_HEIGHT);
    private int downBorder = (int) (0.8 * GAME_HEIGHT);
    private int lvlTilesHeight = importImg("/level.graphics/bigggglvl.png").getHeight();
    private int maxTilesOffsetVer = lvlTilesHeight - HEIGHT_IN_TILES;
    private int maxLvlOffsetVer = maxTilesOffsetVer * TILES_SIZE * SCALE;

    public Game() {
        gamerun=new GameRun(this);
        menu= new Menu(this);
        gameover = new GameOver(this);
        gamerun.initClasses();

        gamePanel = new GamePanel(this);
        gameWindow = new GameWindow(gamePanel);
        gamePanel.requestFocus();
        startGameLoop();

    }

    private void startGameLoop() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    public void update() {
        switch(GameState.gamestate){
            case MENU -> {
                menu.update();

            }
            case GAMERUN, DEAD -> {
                gamerun.update();
                checkCloseToBorder();
            }
            case QUIT -> {
                System.exit(0);
            }
            case GAMEOVER -> {
                gameover.update();
            }
        }


    }

    private void checkCloseToBorder() {
        int playerX = (int) gamerun.getPlayerManager().getPlayer().getHitBox().x;
        int playerY = (int) gamerun.getPlayerManager().getPlayer().getHitBox().y;
        int diffX = playerX - xLvlOffset;
        int diffY = playerY - yLvlOffset;
        if (diffX > rightBorder) {
            xLvlOffset += diffX - rightBorder;
        } else if (diffX < letfBorder) {
            xLvlOffset += diffX - letfBorder;
        }

        if (xLvlOffset > maxLvlOffsetHor) {
            xLvlOffset = maxLvlOffsetHor;
        } else if (xLvlOffset < 0) {
            xLvlOffset = 0;
        }

        if (diffY > downBorder) {
            yLvlOffset += diffY - downBorder;
        } else if (diffY < upBorder) {
            yLvlOffset += diffY - upBorder;
        }
        if (yLvlOffset > maxLvlOffsetVer) {
            yLvlOffset = maxLvlOffsetVer;
        } else if (yLvlOffset < 0) {
            yLvlOffset = 0;
        }
    }

    public void render(Graphics g) {

        switch(GameState.gamestate){
            case MENU -> {
                menu.render(g);
            }
            case GAMERUN, DEAD -> {
                gamerun.render(g);
            }
            case GAMEOVER -> {
                gameover.render(g);
            }
        }


    }

    public void windowFocusLost() {
        gamerun.getPlayerManager().getPlayer().resetDirBooleans();
    }

    public PlayerManager getPlayerManager() {
        return gamerun.getPlayerManager();
    }

    public LevelManager getLevelManager() {
        return gamerun.getLevelManager();
    }
    public ObjectManager getObjectManager(){
        return gamerun.getObjectManager();
    }
    public GameRun getGameRun(){return gamerun;}
    @Override
    public void run() {

        double timePerFrame = 1000000000.0 / FPS_SET;
        double timePerUpdate = 1000000000.0 / UPS_SET;

        long previousTime = System.nanoTime();

        int frames = 0;
        int updates = 0;
        long lastCheck = System.currentTimeMillis();

        double deltaU = 0;
        double deltaF = 0;
        long currentTime;

        while (true) {
            currentTime = System.nanoTime();

            deltaU += (currentTime - previousTime) / timePerUpdate;
            deltaF += (currentTime - previousTime) / timePerFrame;
            previousTime = currentTime;

            if (deltaU >= 1) {
                update();
                updates++;
                deltaU--;
            }

            if (deltaF >= 1) {
                gamePanel.repaint();
                frames++;
                deltaF--;
            }

            if (System.currentTimeMillis() - lastCheck >= 1000) {
                lastCheck = System.currentTimeMillis();
                System.out.println("FPS: " + frames + " | UPS: " + updates);
                frames = 0;
                updates = 0;

            }
        }

    }

    public Menu getMenu() {
        return menu;
    }
}