package view.level;

import model.levels.Level;
import controller.loop.Game;

import java.awt.*;
import java.awt.image.BufferedImage;

import static model.levels.Level.getLevel;
import static utilities.constants.Constants.View.SCALE;
import static utilities.images.ImageHandler.importImg;

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
        BufferedImage image = importImg("/Tileset.png");
        levelImage = new BufferedImage[180];
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 18; j++) {
                int index = i * 18 + j;
                levelImage[(index + 1) % 180] = image.getSubimage(j * 32, i * 32, 32, 32);
            }
        }
    }

    public void draw(Graphics graphic) {
//        level.setLevelLayout(getLevel("/test.png"));
        for (int i = 0; i < Game.HEIGHT; i++) {
            for (int j = 0; j < Game.WIDTH; j++) {
                int index = i * Game.WIDTH + j;
                graphic.drawImage(levelImage[level.getLevelLayout()[i][j]], 32 * SCALE * j, 32 * SCALE * i, 32 * SCALE, 32 * SCALE, null);
            }
        }
    }

    public Level getLevel(){
        return level;
    }

    public void update() {
    }
}
