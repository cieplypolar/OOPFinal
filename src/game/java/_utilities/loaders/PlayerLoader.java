package _utilities.loaders;

import java.awt.image.BufferedImage;

import static _utilities.constants.Constants.PlayerConstants.*;
import static _utilities.loaders.ImageHandler.importImg;

public class PlayerLoader {
    public static BufferedImage[][] loadPlayerAnimations() {
        BufferedImage[][] animations = new BufferedImage[8][7];
        initPlayerConstants();
        for (String key : getPlayerStates()) {
            for (int i = 0; i < getSpriteAmount(key); ++i) {
                animations[getAnimationIndex(key)][i] = importImg(getSpritePath(key, i));
            }
        }
        return animations;
    }
}
