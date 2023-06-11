package _utilities.constants;

import org.codehaus.jackson.map.ObjectMapper;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Set;

import static _utilities.constants.Constants.ViewConstants.SCALE;

public class Constants {
    public static class GameConstants {
    }

    public static class LevelConstants {

    }
    public static class ObjectConstants {
        public static final int CRATE =0;
        public static final int BARREL = 1;
        public static final int HEART = 2;

        public static final int CONTAINER_WIDTH_DEFAULT = 40;
        public static final int CONTAINER_HEIGHT_DEFAULT = 30;
        public static final int CONTAINER_WIDTH = (int) (SCALE * CONTAINER_WIDTH_DEFAULT);
        public static final int CONTAINER_HEIGHT = (int) (SCALE * CONTAINER_HEIGHT_DEFAULT);

        public static final int CRATE_X_OFFSET=7;
        public static final int CRATE_Y_OFFSET=12;
        public static final int BARREL_X_OFFSET=8;
        public static final int BARREL_Y_OFFSET=5;

        public static final int HEART_WIDTH_DEFAULT = 14;
        public static final int HEART_HEIGHT_DEFAULT = 14;
        public static final int HEART_WIDTH = (int) (SCALE * HEART_WIDTH_DEFAULT);
        public static final int HEART_HEIGHT = (int) (SCALE * HEART_HEIGHT_DEFAULT);

        public static int getSpriteAmount(int object_type) {
            switch (object_type) {
                case HEART:
                    return 5;
                case BARREL, CRATE:
                    return 8;
            }
            return 1;
        }

    }
    public static class ViewConstants {
        public static final int SCALE = 2;
        public final static int HEIGHT_IN_TILES = 10;
        public final static int WIDTH_IN_TILES = 20;
        public final static int TILES_SIZE = 32;
        public final static int GAME_HEIGHT = HEIGHT_IN_TILES * TILES_SIZE * SCALE;
        public final static int GAME_WIDTH = WIDTH_IN_TILES * TILES_SIZE * SCALE;
    }

    public static class PlayerConstants {
        public static final int PLAYER_WIDTH = 50;
        public static final int PLAYER_HEIGHT = 37;
        public static final int REAL_PLAYER_WIDTH = 13;
        public static final int REAL_PLAYER_HEIGHT = 25;
        public static final float xOffSet = 19;
        public static final float yOffset = 11;

        public enum Facing {
            LEFT, RIGHT
        }

        public enum playerState {
            IDLE, RUNNING, JUMP, FALLING, HIT, ATTACK_1, ATTACK_2, DIE;

            @Override
            public String toString() {
                return this.name();
            }
        }

        private static HashMap<String, Integer> spriteAmounts;
        private static HashMap<String, String> animationPaths;
        private static HashMap<String, Integer> animationIndex;
        private static final String extension = ".png";

        public static void initPlayerConstants() {
            InputStream is1 = PlayerConstants.class.getResourceAsStream("/jsons/player/spriteAmount.json");
            InputStream is2 = PlayerConstants.class.getResourceAsStream("/jsons/player/animationPath.json");
            InputStream is3 = PlayerConstants.class.getResourceAsStream("/jsons/player/animationIndex.json");
            try {
                spriteAmounts = new ObjectMapper().readValue(is1, HashMap.class);
                animationPaths = new ObjectMapper().readValue(is2, HashMap.class);
                animationIndex = new ObjectMapper().readValue(is3, HashMap.class);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        public static int getSpriteAmount(String s) {
            return spriteAmounts.get(s);
        }

        public static String getSpritePath(String s, int index) {
            return animationPaths.get(s) + index + extension;
        }

        public static int getAnimationIndex(String s) {
            return animationIndex.get(s);
        }

        public static Set<String> getPlayerStates() {
            return animationIndex.keySet();
        }
    }

}