package _utilities.helpers;

import controller.loop.Game;

import java.awt.geom.Rectangle2D;

import static _utilities.constants.Constants.ViewConstants.*;


public class PlayerHelperMethods {
    public static boolean canMoveHere(float x, float y, float width, float height, int[][] lvlData) {
        return !isSolid(x, y, lvlData) && !isSolid(x + width, y + height, lvlData)
                && !isSolid(x + width, y, lvlData) && !isSolid(x, y + height, lvlData);
    }

    public static boolean isOnGround(Rectangle2D.Float hitbox, int[][] levelLayout) {
        if (!isSolid(hitbox.x, hitbox.y + hitbox.height + 1, levelLayout))
            if (!isSolid(hitbox.x + hitbox.width, hitbox.y + hitbox.height + 1, levelLayout)) return false;
        return true;
    }
    public static boolean isOnFinish(Rectangle2D.Float hitbox, int[][] levellayout){


        if(levellayout[(int) hitbox.y/ (TILES_SIZE * SCALE)][(int) hitbox.x/ (TILES_SIZE * SCALE)]==100 && levellayout[(int) (hitbox.y)/ (TILES_SIZE * SCALE)][(int) (hitbox.x+hitbox.width)/ (TILES_SIZE * SCALE)]==100 )return true;

        return false;

    }

    private static boolean isSolid(float x, float y, int[][] lvlData) {
        if (x <= 0 || x >= lvlData[0].length * TILES_SIZE * SCALE)
            return true;
        if (y <= 0 || y >= lvlData.length * TILES_SIZE * SCALE)
            return true;

        float xIndex = x / (TILES_SIZE * SCALE);
        float yIndex = y / (TILES_SIZE * SCALE);

        int value = lvlData[(int) yIndex][(int) xIndex];
        if(value == 117)return false;
        if(value == 118)return false;
        if(value == 135)return false;
        if(value == 136)return false;
        if(value == 153)return false;
        if(value == 154)return false;
        if(value == 171)return false;
        if(value == 172)return false;
        if(value == 100)return false;

        if (value <= 180 && value != 0)
            return true;
        return false;
    }
}
