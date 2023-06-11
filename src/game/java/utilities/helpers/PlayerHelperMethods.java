package utilities.helpers;

import controller.loop.Game;

import java.awt.geom.Rectangle2D;

import static utilities.constants.Constants.View.SCALE;

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

    private static boolean isSolid(float x, float y, int[][] lvlData) {
        if (x <= 0 || x >= lvlData[0].length * Game.TILES_SIZE * SCALE)
            return true;
        if (y <= 0 || y >= lvlData.length * Game.TILES_SIZE * SCALE)
            return true;

        float xIndex = x / (Game.TILES_SIZE * SCALE);
        float yIndex = y / (Game.TILES_SIZE * SCALE);

        int value = lvlData[(int) yIndex][(int) xIndex];
        if(value == 117)return false;
        if(value == 118)return false;
        if(value == 135)return false;
        if(value == 136)return false;
        if(value == 153)return false;
        if(value == 154)return false;
        if(value == 171)return false;
        if(value == 172)return false;

        if (value <= 180 && value != 0)
            return true;
        return false;
    }
}
