package controller.loop;

import GameStates.GameRun;
import GameStates.GameState;
import view.level.LevelManager;
import view.player.PlayerManager;
import view.window.GamePanel;
import view.window.GameWindow;
import GameStates.Menu;
import java.awt.*;

import static utilities.constants.Constants.View.SCALE;
import static utilities.images.ImageHandler.importImg;

public class Game implements Runnable {

    private GameWindow gameWindow;
    private GamePanel gamePanel;
    private Thread gameThread;
    private final int FPS_SET = 120;
    private final int UPS_SET = 200;
    public final static int HEIGHT = 10;
    public final static int WIDTH = 20;
    public final static int TILES_SIZE = 32;
    public final static int GAME_HEIGHT = HEIGHT * TILES_SIZE * SCALE;
    public final static int GAME_WIDTH = WIDTH * TILES_SIZE * SCALE;
    private GameRun gamerun;
    private Menu menu;

    private int xLvlOffset;
    private int letfBorder = (int) (0.2 * Game.GAME_HEIGHT);
    private int rightBorder = (int) (0.8 * Game.GAME_WIDTH);
    private int lvlTilesWidth = importImg("/level.graphics/biglevel.png").getWidth();
    private int maxTilesOffset = lvlTilesWidth - Game.WIDTH;
    private int maxLvlOffser = maxTilesOffset * Game.TILES_SIZE * SCALE;

    public Game() {
        gamerun=new GameRun(this);
        menu= new Menu(this);
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
            case GAMERUN -> {
                gamerun.update();
                checkCloseToBorder();
            }
        }


    }

    private void checkCloseToBorder() {
        int playerX = (int) gamerun.getPlayerManager().getPlayer().getHitBox().x;
        int diff = playerX - xLvlOffset;
        if (diff > rightBorder) {
            xLvlOffset += diff - rightBorder;
        } else if (diff < letfBorder) {
            xLvlOffset += diff - letfBorder;
        }

        if (xLvlOffset > maxLvlOffser) {
            xLvlOffset = maxLvlOffser;
        } else if (xLvlOffset < 0) {
            xLvlOffset = 0;

        }
    }

    public void render(Graphics g) {

        switch(GameState.gamestate){
            case MENU -> {
                menu.render();
            }
            case GAMERUN -> {
                gamerun.getLevelManager().draw(g, xLvlOffset);
                gamerun.getPlayerManager().render(g, xLvlOffset);
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