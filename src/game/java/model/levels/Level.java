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


    public Level() {
        this.levelLayout = new int[][]{{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 20},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 20},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 20},
                {0, 0, 0, 0, 117, 118, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 20, 20},
                {0, 0, 0, 4, 135, 136, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 20},
                {0, 0, 4, 22, 153, 154, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 2, 2, 2, 20},
                {0, 4, 22, 22, 171, 172, 0, 0, 0, 0, 1, 2, 2, 2, 2, 20, 20, 20, 20, 20},
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
