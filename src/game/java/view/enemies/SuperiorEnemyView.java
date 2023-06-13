package view.enemies;

import _utilities.constants.Constants;
import controller.game.GoblinManager;
import controller.game.SuperiorEnemyManager;

import java.awt.*;
import java.awt.image.BufferedImage;

import static _utilities.constants.Constants.Enemy.Goblin.initGoblinConstants;
import static _utilities.constants.Constants.Enemy.getEnemyAnimationIndex;
import static _utilities.constants.Constants.ViewConstants.SCALE;
import static _utilities.loaders.EnemyLoader.loadEnemyAnimations;
import static _utilities.loaders.ImageHandler.reflectImg;

public class SuperiorEnemyView {
    private BufferedImage[][] goblinAni;
    private SuperiorEnemyManager enemyManager;
    public SuperiorEnemyView(SuperiorEnemyManager enemyManager){
        this.enemyManager = enemyManager;
        initGoblinConstants();
        goblinAni = loadEnemyAnimations(Constants.Enemy.EnemyType.Goblin);
    }


    public void draw(Graphics g, int xoff, int yoff){
        drawGoblins(g, xoff, yoff);
    }

    public void drawGoblins(Graphics g, int xoff, int yoff){
        for (GoblinManager gob : enemyManager.getGoblinArr()){
            if (gob.getEnemy().isAlive()) {
                g.drawImage((gob.getFacing() == Constants.Facing.RIGHT) ? goblinAni[getEnemyAnimationIndex(Constants.Enemy.EnemyType.Goblin, gob.getEnemy().getEnemyState().toString())][gob.getAniIndex()] :
                                reflectImg(goblinAni[getEnemyAnimationIndex(Constants.Enemy.EnemyType.Goblin, gob.getEnemy().getEnemyState().toString())][gob.getAniIndex()]),
                        (int) (gob.getEnemy().getHitBox().x - Constants.Enemy.Goblin.xOffSet * SCALE - xoff), (int) (gob.getEnemy().getHitBox().y - Constants.Enemy.Goblin.yOffset * SCALE - yoff),
                        Constants.Enemy.EnemyType.Goblin.WIDTH * SCALE, Constants.Enemy.EnemyType.Goblin.HEIGHT * SCALE, null);
                gob.getEnemy().drawHitBox(g, xoff, yoff);
                gob.drawAttackBox(g, xoff, yoff);
            }
        }
    }
}
