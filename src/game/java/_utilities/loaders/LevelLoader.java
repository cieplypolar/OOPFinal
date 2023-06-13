package _utilities.loaders;

import _utilities.constants.Constants;
import controller.game.GoblinManager;
import model.entities.Goblin;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import static _utilities.constants.Constants.ViewConstants.SCALE;
import static _utilities.constants.Constants.ViewConstants.TILES_SIZE;
import static _utilities.loaders.ImageHandler.importImg;

public class LevelLoader {

    public static ArrayList<GoblinManager> getGoblins(String path) {
        BufferedImage image = importImg(path);
        ArrayList<GoblinManager> gob = new ArrayList<>();
        for (int i = 0; i < image.getHeight(); i++) {
            for (int j = 0; j < image.getWidth(); j++) {
                Color color = new Color(image.getRGB(j, i));
                if (color.getGreen() == Constants.Enemy.EnemyType.Goblin.token) {
                    gob.add(new GoblinManager(new Goblin(j * TILES_SIZE * SCALE, i * TILES_SIZE * SCALE)));
                }
            }
        }
        return gob;
    }

    public static int[][] loadLevel(String path) {
        BufferedImage image = importImg(path);
        int[][] layout = new int[image.getHeight()][image.getWidth()];
        for (int i = 0; i < image.getHeight(); i++) {
            for (int j = 0; j < image.getWidth(); j++) {
                Color color = new Color(image.getRGB(j, i));
                if (color.getRed() >= 180) layout[i][j] = 0;
                else layout[i][j] = color.getRed();
            }
        }
        return layout;
    }
}
