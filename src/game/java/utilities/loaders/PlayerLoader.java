package utilities.loaders;

import java.awt.image.BufferedImage;

import static utilities.constants.Constants.PlayerConstants.*;
import static utilities.images.ImageHandler.importImg;

public class PlayerLoader {
    public static BufferedImage[][] loadPlayerAnimations() {
        BufferedImage[][] animations = new BufferedImage[8][7];
        initPlayerConstants();
        for (String key : getPlayerStates()) {
            System.out.println(key);
            for (int i = 0; i < getSpriteAmount(key); ++i) {
                System.out.println(getSpriteAmount(key));
                System.out.println(i);
                animations[getAnimationIndex(key)][i] = importImg(getSpritePath(key, i));
            }
        }
        return animations;
    }
}
