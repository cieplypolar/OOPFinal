package model.entities;

import java.awt.geom.Rectangle2D;

import static _utilities.constants.Constants.ViewConstants.*;


public class EntityHelperMethods {
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
        if (x <= 0 || x >= lvlData[0].length * TILES_SIZE * SCALE)
            return true;
        if (y <= 0 || y >= lvlData.length * TILES_SIZE * SCALE)
            return true;

        float xIndex = x / (TILES_SIZE * SCALE);
        float yIndex = y / (TILES_SIZE * SCALE);

        return isTileSolid(x, y, lvlData);
    }
    public static boolean isOnFinish(Rectangle2D.Float hitbox, int[][] levellayout){


        if(levellayout[(int) hitbox.y/ (TILES_SIZE * SCALE)][(int) hitbox.x/ (TILES_SIZE * SCALE)]==175 && levellayout[(int) (hitbox.y)/ (TILES_SIZE * SCALE)][(int) (hitbox.x+hitbox.width)/ (TILES_SIZE * SCALE)]==175 )return true;

        return false;

    }


    private static boolean isTileSolid(float x, float y, int[][] lvlData) {
        float xIndex = x / (TILES_SIZE * SCALE);
        float yIndex = y / (TILES_SIZE * SCALE);

        int value = lvlData[(int) yIndex][(int) xIndex];
        if (value == 117) return false;
        if (value == 118) return false;
        if (value == 135) return false;
        if (value == 136) return false;
        if (value == 153) return false;
        if (value == 154) return false;
        if (value == 171) return false;
        if (value == 172) return false;

        if (value <= 180 && value != 0)
            return true;
        return false;
    }

    public static float getEntityYPosUnderRoofOrAboveFloor(Rectangle2D.Float hitbox, float airSpeed) {
        int currentTile = (int) (hitbox.y / (TILES_SIZE * SCALE));
        if (airSpeed > 0) {
            int tileYPos = currentTile * TILES_SIZE * SCALE;
            int yOffset = (int) (TILES_SIZE * SCALE - hitbox.height);
            return tileYPos + yOffset - 1;
        } else {
            return currentTile * TILES_SIZE * SCALE;
        }
    }

    public static boolean isFloor(Rectangle2D.Float hitbox, float xSpeed, int[][] lvlData) {
        return isSolid(hitbox.x + xSpeed, hitbox.y + hitbox.height + 10, lvlData);
    }

    public static boolean isSightClear(int[][] lvlData, Rectangle2D.Float hitbox1, Rectangle2D.Float hitbox2, int y) {
        int x1 = (int) (hitbox1.x / TILES_SIZE * SCALE);
        int x2 = (int) (hitbox2.x / TILES_SIZE * SCALE);
        if (x1 > x2) {
            for (int i = 0; i < x1 - x2; ++i) {
                if (isTileSolid(x2 + i, y, lvlData)) {
                    return false;
                }
//                if(!isTileSolid(x2 + i, y + 10, lvlData)){
//                    return false;
//                }
            }
        } else {
            for (int i = 0; i < x2 - x1; ++i) {
                if (isTileSolid(x1 + i, y, lvlData)) {
                    return false;
                }
//                if(!isTileSolid(x1 + i, y + 2, lvlData)){
//                    return false;
//                }
            }
        }
        return true;
    }
}
