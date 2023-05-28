package utilities.helpers;

import controller.loop.Game;

public class PlayerHelperMethods {
    public static boolean CanMoveHere(double x, double y, double width, double height, int[][] lvlData) {
        return !isSolid(x, y, lvlData) && !isSolid(x + width, y + height, lvlData)
                && !isSolid(x + width, y, lvlData) && !isSolid(x, y + height, lvlData);
    }

    private static boolean isSolid(double x, double y, int[][] lvlData) {
        if (x < 0 || x >= Game.GAME_WIDTH)
            return true;
        if (y < 0 || y >= Game.GAME_HEIGHT)
            return true;

        double xIndex = x / Game.TILES_SIZE;
        double yIndex = y / Game.TILES_SIZE;

        int value = lvlData[(int) yIndex][(int) xIndex];

        if (value <= 180 && value != 0)
            return true;
        return false;
    }
}
