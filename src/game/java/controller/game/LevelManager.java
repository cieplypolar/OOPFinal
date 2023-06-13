package controller.game;

import model.levels.Level;
import controller.loop.Game;

import java.awt.*;
import java.awt.image.BufferedImage;

import static _utilities.constants.Constants.Paths.*;
import static _utilities.constants.Constants.ViewConstants.*;
import static _utilities.loaders.ImageHandler.importImg;
import static _utilities.loaders.LevelLoader.loadLevel;

public class LevelManager {

    private Game game;
    private BufferedImage[] levelImage;
    private Level level;

    public LevelManager(Game game) {
        this.game = game;
        importLevelImage();
        this.level = new Level();
    }

    public void importLevelImage() {
        BufferedImage image = importImg(TILE_SET);
        levelImage = new BufferedImage[180];
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 18; j++) {
                int index = i * 18 + j;
                levelImage[(index + 1) % 180] = image.getSubimage(j * TILES_SIZE, i * TILES_SIZE, TILES_SIZE, TILES_SIZE);
            }
        }
    }

    public void draw(Graphics graphic, int xlvlOffset, int ylvlOffset) {
        level.setLevelLayout(loadLevel(LEVEL_DATA));
        for (int i = level.getLevelLayout().length - 1; i >= 0; i--) {
            for (int j = 0; j < level.getLevelLayout()[0].length; j++) {
                graphic.drawImage(levelImage[level.getLevelLayout()[i][j]], TILES_SIZE * SCALE * j - xlvlOffset, TILES_SIZE * SCALE * i - ylvlOffset, TILES_SIZE * SCALE, TILES_SIZE * SCALE, null);
            }
        }
    }

    public void update() {
    }

    public Level getLevel() {
        return level;
    }
}
