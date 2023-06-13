package _utilities.loaders;

import _utilities.constants.Constants;

import java.awt.image.BufferedImage;

import static _utilities.constants.Constants.Enemy.*;
import static _utilities.constants.Constants.Enemy.Goblin.*;
import static _utilities.loaders.ImageHandler.importImg;

public class EnemyLoader {
    public static BufferedImage[][] loadEnemyAnimations(Constants.Enemy.EnemyType et) {
        initGoblinConstants();
        BufferedImage[][] animations = new BufferedImage[5][8];
        for (String key : getEnemyStates(et)) {
            BufferedImage temp = importImg(getEnemySpritePath(et, key));
            for (int i = 0; i < getEnemySpriteAmount(et, key); ++i) {
                animations[getEnemyAnimationIndex(et, key)][i] = temp.getSubimage(i * et.WIDTH, 0, et.WIDTH, et.HEIGHT);
            }
        }
        return animations;
    }
}
