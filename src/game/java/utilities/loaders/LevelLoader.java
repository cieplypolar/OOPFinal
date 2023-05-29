package utilities.loaders;

import controller.loop.Game;

import java.awt.*;
import java.awt.image.BufferedImage;

import static utilities.images.ImageHandler.importImg;

public class LevelLoader {
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
