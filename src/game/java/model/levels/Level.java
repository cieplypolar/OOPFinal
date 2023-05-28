package model.levels;

import java.awt.*;
import java.awt.image.BufferedImage;

import static utilities.images.ImageHandler.importImg;

import controller.loop.Game;

public class Level {
    private int[][] levelLayout;

    public Level(int[][] levelLayout) {
        this.levelLayout = levelLayout;
    }

    public static int[][] getLevel(String path) {
        BufferedImage image = importImg(path);
        int[][] layout = new int[Game.HEIGHT][Game.WIDTH];
        for (int i = 0; i < Game.HEIGHT; i++) {
            for (int j = 0; j < Game.WIDTH; j++) {
                Color color = new Color(image.getRGB(j, i));
                if (color.getRed() >= 180) layout[i][j] = 0;
                else layout[i][j] = color.getRed();
            }
        }
        return layout;
    }

    public Level() {
        this.levelLayout = new int[][]{{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                                       {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                                       {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                                       {0, 0, 0, 0, 117, 118, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                                       {0, 0, 0, 0, 135, 136, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                                       {0, 0, 0, 0, 153, 154, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 2, 2, 2, 2},
                                       {0, 0, 0, 0, 171, 172, 0, 0, 0, 0, 1, 2, 2, 2, 2, 20, 20, 20, 20, 20},
                                       {2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20},
                                       {20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20},
                                       {20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20}};
    }

    public int[][] getLevelLayout() {
        return levelLayout;
    }

    public void setLevelLayout(int[][] levelLayout) {
        this.levelLayout = levelLayout;
    }
}
