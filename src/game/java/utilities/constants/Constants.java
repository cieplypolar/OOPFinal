package utilities.constants;

import org.codehaus.jackson.map.ObjectMapper;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Set;

public class Constants {

    public static class View {
        public static final int SCALE = 2;
    }

    public static class PlayerConstants {
        public static final int PLAYER_WIDTH = 50;
        public static final int PLAYER_HEIGHT = 37;
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

//        public static int getSpriteAmount(playerState p) {
//            return spriteAmounts.get(p.toString());
//        }

        public static int getSpriteAmount(String s) {
            return spriteAmounts.get(s);
        }

//        public static String getSpritePath(playerState p, int index) {
//            return animationPaths.get(p.toString()) + index + extension;
//        }

        public static String getSpritePath(String s, int index) {
            return animationPaths.get(s) + index + extension;
        }

//        public static int getAnimationIndex(playerState p) {
//            return animationIndex.get(p.toString());
//        }

        public static int getAnimationIndex(String s) {
            return animationIndex.get(s);
        }

        public static Set<String> getPlayerStates() {
            return animationIndex.keySet();
        }
    }

}