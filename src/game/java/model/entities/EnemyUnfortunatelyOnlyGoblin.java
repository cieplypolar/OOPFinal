package model.entities;

import _utilities.constants.Constants;

import java.awt.geom.Rectangle2D;

import static _utilities.constants.Constants.Enemy.EnemyState.*;
import static _utilities.constants.Constants.ViewConstants.SCALE;
import static _utilities.constants.Constants.ViewConstants.TILES_SIZE;

public abstract class EnemyUnfortunatelyOnlyGoblin extends Entity {
    protected Constants.Enemy.EnemyState enemyState = IDLE;
    protected boolean inAir = true;
    protected float fallSpeed;
    protected float gravity = 0.04f * SCALE;
    protected float walkSpeed = 0.5f * SCALE;
    protected Constants.Facing facing = Constants.Facing.LEFT;
    protected Constants.Enemy.EnemyType enemyType;
    protected int tileY;
    protected float attackRange = TILES_SIZE * SCALE;
    protected int damage =  1;

    protected Rectangle2D.Float attackBox;
    protected int attackOffset;
    protected boolean alive = true;
    EnemyUnfortunatelyOnlyGoblin(float x, float y, int width, int height, Constants.Enemy.EnemyType enemyType) {
        super(x, y, width, height);
        initHitBox(x, y, width, height);
        initAttackBox();
        this.enemyType = enemyType;
    }
    public void checkEnemyHit(Player player){
        if (attackBox.intersects(player.getHitBox())){
            player.setHealth(player.getHealth() - damage);
        }
    }

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    protected void initAttackBox(){
        attackBox = new Rectangle2D.Float(x , y, 23 * SCALE, 20 * SCALE);
        attackOffset = 20 * SCALE;
    }

    public Rectangle2D.Float getAttackBox() {
        return attackBox;
    }

    public int getAttackOffset() {
        return attackOffset;
    }

    public void hurt(int damage){
        if (alive) {
            health -= damage;
            if (health <= 0) {
                enemyState = DIE;
            } else {
                enemyState = HIT;
            }
        }
    }

    public int getTileY() {
        return tileY;
    }

    public float getAttackRange() {
        return attackRange;
    }

    public void setTileY(int tileY) {
        this.tileY = tileY;
    }

    public Constants.Enemy.EnemyType getEnemyType(){
        return enemyType;
    }

    public Constants.Enemy.EnemyState getEnemyState() {
        return enemyState;
    }

    public void setEnemyState(Constants.Enemy.EnemyState enemyState) {
        this.enemyState = enemyState;
    }

    public boolean isInAir() {
        return inAir;
    }

    public void setInAir(boolean inAir) {
        this.inAir = inAir;
    }

    public float getFallSpeed() {
        return fallSpeed;
    }

    public void setFallSpeed(float fallSpeed) {
        this.fallSpeed = fallSpeed;
    }

    public float getGravity() {
        return gravity;
    }

    public void setGravity(float gravity) {
        this.gravity = gravity;
    }

    public float getWalkSpeed() {
        return walkSpeed;
    }

    public void setWalkSpeed(float walkSpeed) {
        this.walkSpeed = walkSpeed;
    }

    public Constants.Facing getFacing() {
        return facing;
    }

    public void setFacing(Constants.Facing facing) {
        this.facing = facing;
    }

    public void setEnemyType(Constants.Enemy.EnemyType enemyType) {
        this.enemyType = enemyType;
    }
}
