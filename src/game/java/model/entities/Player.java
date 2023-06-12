package model.entities;

import _utilities.constants.Constants;

import java.awt.geom.Rectangle2D;

import static _utilities.constants.Constants.PlayerConstants.*;
import static _utilities.constants.Constants.PlayerConstants.PlayerState.*;
import static _utilities.constants.Constants.ViewConstants.SCALE;

public class Player extends Entity {
    private PlayerState playerAction = IDLE;
    private boolean moving = false, attacking = false;
    private boolean left, right, up, down, jump;
    private float playerSpeed = 1.5f;
    private float airSpeed = 0f;
    private float gravity = 0.04f * SCALE;
    private float jumpSpeed = -2.7f * SCALE;
    private float fallSpeedCollision = 0.6f * SCALE;
    private boolean inAir = false;
    private boolean isdead = false;
    private Rectangle2D.Float attackBox;

    public Player(float x, float y) {
        super(x, y, REAL_PLAYER_WIDTH * SCALE, REAL_PLAYER_HEIGHT * SCALE);
        initHitBox(x, y, REAL_PLAYER_WIDTH * SCALE, REAL_PLAYER_HEIGHT * SCALE);
        initAttackBox();
        health = 10;
    }

    private void initAttackBox() {
        attackBox = new Rectangle2D.Float(x, y, 20 * SCALE, 20 * SCALE);
    }

    public void resetDirBooleans() {
        left = false;
        right = false;
        up = false;
        down = false;
        moving = false;
        attacking = false;
    }

    public Constants.Facing getFacing() {
        return this.facing;
    }

    public void setFacing(Constants.Facing facing) {
        this.facing = facing;
    }

    public PlayerState getPlayerAction() {
        return this.playerAction;
    }


    public Rectangle2D.Float getAttackBox() {
        return attackBox;
    }

    public void setAttacking(boolean attacking) {
        this.attacking = attacking;
    }

    public boolean isLeft() {
        return left;
    }

    public void setLeft(boolean left) {
        this.left = left;
    }

    public boolean isUp() {
        return up;
    }

    public void setUp(boolean up) {
        this.up = up;
    }

    public boolean isRight() {
        return right;
    }

    public boolean isInAir() {
        return inAir;
    }

    public void setInAir(boolean s) {
        this.inAir = s;
    }

    public void setRight(boolean right) {
        this.right = right;
    }

    public boolean isDown() {
        return down;
    }

    public void setDown(boolean down) {
        this.down = down;
    }

    public void setPlayerAction(PlayerState playerAction) {
        this.playerAction = playerAction;
    }

    public boolean isMoving() {
        return this.moving;
    }

    public void setMoving(boolean moving) {
        this.moving = moving;
    }

    public boolean isAttacking() {
        return this.attacking;
    }


    public float getPlayerSpeed() {
        return this.playerSpeed;
    }

    public float getPlayerAirSpeed() {
        return this.airSpeed;
    }

    public void setPlayerAirSpeed(float f) {
        this.airSpeed = f;
    }

    public float getGravity() {
        return this.gravity;
    }

    public float getfallSpeedCollision() {
        return this.fallSpeedCollision;
    }

    public float getJumpSpeed() {
        return this.jumpSpeed;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void setIsDead(boolean b) {
        isdead = b;
    }

    public boolean getIsDead() {
        return isdead;
    }
}
