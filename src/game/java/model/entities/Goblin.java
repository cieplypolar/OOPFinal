package model.entities;

import _utilities.constants.Constants;

import static _utilities.constants.Constants.Enemy.Goblin.REAL_GOBLIN_HEIGHT;
import static _utilities.constants.Constants.Enemy.Goblin.REAL_GOBLIN_WIDTH;

public class Goblin extends EnemyUnfortunatelyOnlyGoblin {
    public Goblin(float x, float y) {
        super(x, y, REAL_GOBLIN_WIDTH, REAL_GOBLIN_HEIGHT, Constants.Enemy.EnemyType.Goblin );
        health = 4;
    }

    public int getDamage() {
        return damage;
    }
}
