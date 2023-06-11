package model.levels;

public class Level {
    private int[][] levelLayout;

    public Level(int[][] levelLayout) {
        this.levelLayout = levelLayout;
    }


    public Level() {
        this.levelLayout = new int[1][1];
    }

    public int[][] getLevelLayout() {
        return levelLayout;
    }

    public void setLevelLayout(int[][] levelLayout) {
        this.levelLayout = levelLayout;
    }
}
