package controller.game;


import _utilities.constants.Constants;
import model.entities.EnemyUnfortunatelyOnlyGoblin;
import model.entities.Player;

import java.awt.*;

import static _utilities.constants.Constants.Animations.ANI_SPEED;
import static _utilities.constants.Constants.Enemy.EnemyState.*;
import static _utilities.constants.Constants.Enemy.getEnemySpriteAmount;
import static _utilities.constants.Constants.ViewConstants.SCALE;
import static _utilities.constants.Constants.ViewConstants.TILES_SIZE;
import static model.entities.EntityHelperMethods.*;

public abstract class EnemyManager {
    protected int aniTick, aniIndex;
    protected EnemyUnfortunatelyOnlyGoblin enemy;
    protected boolean firstUpdate = true;
    private boolean setY = false;

    public EnemyManager(EnemyUnfortunatelyOnlyGoblin enemy) {
        this.enemy = enemy;
    }

    protected void resetAniTick() {
        aniTick = 0; aniIndex = 0;
    }


    protected void firstUpdateCheck(int[][] lvlData) {
        if (firstUpdate) {
            if (!isOnGround(enemy.getHitBox(), lvlData)) {
                enemy.setInAir(true);
            }
            firstUpdate = false;
        }
    }

    protected void updateAttackBox(){
        enemy.getAttackBox().x = enemy.getHitBox().x - enemy.getAttackOffset();
        enemy.getAttackBox().y = enemy.getHitBox().y;
    }

    protected void inAirUpdate(int[][] lvlData) {
        if (canMoveHere(enemy.getHitBox().x, enemy.getHitBox().y + enemy.getFallSpeed(), enemy.getHitBox().width, enemy.getHitBox().height, lvlData)) {
            enemy.getHitBox().y += enemy.getFallSpeed();
            enemy.setFallSpeed(enemy.getFallSpeed() + enemy.getGravity());
        } else {
            enemy.setInAir(false);
            enemy.getHitBox().y = getEntityYPosUnderRoofOrAboveFloor(enemy.getHitBox(), enemy.getFallSpeed());
            if(!setY){
                enemy.setTileY((int) (enemy.getHitBox().y / (TILES_SIZE * SCALE)));
                setY = true;
            }
        }
    }

    protected void moveEnemy(int[][] lvlData) {
        float xSpeed = 0f;
        if (enemy.getFacing() == Constants.Facing.LEFT) {
            xSpeed = -enemy.getWalkSpeed();
        } else {
            xSpeed = enemy.getWalkSpeed();
        }

        if (canMoveHere(enemy.getHitBox().x + xSpeed, enemy.getHitBox().y, enemy.getHitBox().width, enemy.getHitBox().height, lvlData)) {
            if (isFloor(enemy.getHitBox(), xSpeed, lvlData)) {
                enemy.getHitBox().x += xSpeed;
                return;
            }
        }
        if (enemy.getFacing() == Constants.Facing.LEFT) {
            enemy.setFacing(Constants.Facing.RIGHT);
        } else {
            enemy.setFacing(Constants.Facing.LEFT);
        }
    }

    protected boolean canSeePlayer(int[][] lvlData, Player player){
        int playerTileY = (int) (player.getHitBox().y / (TILES_SIZE * SCALE));
        if (playerTileY == enemy.getTileY()){
            if(isPlayerInRange(player)){
                if (isSightClear(lvlData, enemy.getHitBox(), player.getHitBox(), playerTileY)){
                    return true;
                }
            }
        }
        return false;
    }

    protected void moveTowardsPlayer(Player player){
        if(player.getHitBox().x > enemy.getHitBox().x){
            enemy.setFacing(Constants.Facing.RIGHT);
        }
        else {
            enemy.setFacing(Constants.Facing.LEFT);
        }
    }

    protected boolean isPlayerAttackable(Player player){
        return (int) (Math.abs(player.getHitBox().x - enemy.getHitBox().x)) <= enemy.getAttackRange();

    }

    private boolean isPlayerInRange(Player player) {
        return (int) (Math.abs(player.getHitBox().x - enemy.getHitBox().x)) <= 5 * enemy.getAttackRange();
    }

    protected void changeState(Constants.Enemy.EnemyState state){
        resetAniTick();
        enemy.setEnemyState(state);
    }


    public int getAniIndex() {
        return aniIndex;
    }

    protected void updateAnimationTick() {
        aniTick++;
        if (aniTick >= ANI_SPEED) {
            aniTick = 0;
            aniIndex++;
            if (aniIndex >= getEnemySpriteAmount(enemy.getEnemyType(), enemy.getEnemyState().toString())) {
                aniIndex = 0;
                if (enemy.getEnemyState() == ATTACK){
                    enemy.setEnemyState(IDLE);
                }
                else if (enemy.getEnemyState() == HIT){
                    enemy.setEnemyState(IDLE);
                }
                else if (enemy.getEnemyState() == DIE){
                    enemy.setAlive(false);
                }
            }
        }
    }

    public EnemyUnfortunatelyOnlyGoblin getEnemy() {
        return enemy;
    }

    public Constants.Facing getFacing() {
        return enemy.getFacing();
    }
}
