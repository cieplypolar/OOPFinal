package utilities.constans;

public class Constants {
    public static class Directions {
        public static final int LEFT = 0;
        public static final int UP = 1;
        public static final int RIGHT = 2;
        public static final int DOWN = 3;
    }

    public static class View {
        public static final int SCALE = 2;
    }

    public static class PlayerConstants {
        public static final int IDLE = 0;
        public static final int RUNNING = 1;
        public static final int JUMP = 2;
        public static final int FALLING = 3;
        public static final int HIT = 5;
        public static final int ATTACK_1 = 6;
        public static final int ATTACK_2 = 7;
        public static final int DIE = 8;

        public static int GetSpriteAmount(int player_action) {
            switch (player_action) {
                case RUNNING:
                case ATTACK_2:
                    return 6;
                case JUMP:
                case IDLE:
                    return 4;
                case HIT:
                    return 3;
                case ATTACK_1:
                    return 5;
                case FALLING:
                    return 2;
                case DIE:
                    return 7;
                default:
                    return 1;
            }
        }
    }
    public static class PlayerPaths {
        public static final String EXT = ".png";
        public static final String ATTACK2 = "/animations/player/atack2/adventurer-attack2-0";
        public static final String ATTACK1 = "/animations/player/atack1/adventurer-attack1-0";
        public static final String DIE = "/animations/player/die/adventurer-die-0";
        public static final String FALLING = "/animations/player/fall/adventurer-fall-0";
        public static final String HIT = "/animations/player/hurt/adventurer-hurt-0";
        public static final String IDLE = "/animations/player/idle/adventurer-idle-2-0";
        public static final String JUMP = "/animations/player/jump/adventurer-jump-0";
        public static final String RUNNING = "/animations/player/run/adventurer-run-0";

    }

}