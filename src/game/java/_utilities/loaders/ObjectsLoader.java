package _utilities.loaders;

import model.entities.Objects.GameContainer;
import model.entities.Objects.Heart;
import model.entities.Objects.Spikes;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import static _utilities.constants.Constants.ObjectConstants.BARREL;
import static _utilities.constants.Constants.ObjectConstants.CRATE;
import static _utilities.loaders.ImageHandler.importImg;
import static  _utilities.constants.Constants.ViewConstants.*;
public class ObjectsLoader {

    public static ArrayList<GameContainer> loadContainers(String path ) {
        BufferedImage image = importImg(path);
        ArrayList<GameContainer> container = new ArrayList<>();
        for (int i = 0; i < image.getHeight(); i++) {
            for (int j = 0; j < image.getWidth(); j++) {
                Color color = new Color(image.getRGB(j, i));
                if (color.getRed() ==200)container.add(new GameContainer(j * TILES_SIZE*SCALE, i * TILES_SIZE*SCALE, 0));
                if(color.getRed() ==201) container.add(new GameContainer(j*TILES_SIZE*SCALE, i*TILES_SIZE*SCALE,1));
            }
        }
return container;
    }

    public static ArrayList<Spikes> loadSpikes(String path ) {
        BufferedImage image = importImg(path);
        ArrayList<Spikes> spikes = new ArrayList<>();
        for (int i = 0; i < image.getHeight(); i++) {
            for (int j = 0; j < image.getWidth(); j++) {
                Color color = new Color(image.getRGB(j, i));
                if (color.getRed() ==203)spikes.add(new Spikes(j * TILES_SIZE*SCALE, i * TILES_SIZE*SCALE, 3));

            }
        }
        return spikes;
    }
}
