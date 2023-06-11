package model.levels;

import controller.loop.Game;

public class Level {
    private int[][] levelLayout;

    public Level(int[][] levelLayout) {
        this.levelLayout = levelLayout;
    }


    public Level() {
        this.levelLayout = new int[Game.GAME_HEIGHT][Game.GAME_WIDTH];
    }

    public int[][] getLevelLayout() {
        return levelLayout;
    }

    public void setLevelLayout(int[][] levelLayout) {
        this.levelLayout = levelLayout;
    }
}
