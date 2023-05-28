package controller.loop;

import view.level.LevelManager;
import view.player.PlayerManager;
import view.window.GamePanel;
import view.window.GameWindow;

import java.awt.*;

import static utilities.constants.Constants.View.SCALE;

public class Game implements Runnable {

    private GameWindow gameWindow;
    private GamePanel gamePanel;
    private Thread gameThread;
    private final int FPS_SET = 120;
    private final int UPS_SET = 200;
    public final static int HEIGHT = 10;
    public final static int WIDTH = 20;
    public final static int GAME_HEIGHT = HEIGHT * 32 * SCALE;
    public final static int GAME_WIDTH = WIDTH * 32 * SCALE;
    private PlayerManager player;
    private LevelManager level;

    public Game() {
        initClasses();

        gamePanel = new GamePanel(this);
        gameWindow = new GameWindow(gamePanel);
        gamePanel.requestFocus();
        startGameLoop();

    }

    private void initClasses() {
        player = new PlayerManager(100, 100, 100);
        level = new LevelManager(this);
    }

    private void startGameLoop() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    public void update() {
        level.update();
        player.update();

    }

    public void render(Graphics g) {
        level.draw(g);
        player.render(g);

    }

    public void windowFocusLost() {
        player.getPlayer().resetDirBooleans();
    }

    public PlayerManager getPlayerManager() {
        return player;
    }

    public LevelManager getLevelManager() {
        return level;
    }

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

}