package model.entities;

import _utilities.constants.Constants;

import static _utilities.constants.Constants.PlayerConstants.*;
import static _utilities.constants.Constants.PlayerConstants.playerState.*;
import static _utilities.constants.Constants.ViewConstants.SCALE;

public class Player extends Entity {
    private playerState playerAction = IDLE;
    private boolean moving = false, attacking = false, isdead = false;
    private boolean left, right, up, down, jump;
    private float playerSpeed = 1.5f;
    private float airSpeed = 0f;
    private float gravity = 0.04f * SCALE;
    private float jumpSpeed = -2.5f * SCALE;
    private float fallSpeedCollision = 0.6f * SCALE;
    private boolean inAir = false;

    public Player(float x, float y) {
        super(x, y, REAL_PLAYER_WIDTH * SCALE, REAL_PLAYER_HEIGHT * SCALE);
        initHitBox(x, y, REAL_PLAYER_WIDTH * SCALE, REAL_PLAYER_HEIGHT * SCALE);
        health = 3;
    }

    public void resetDirBooleans() {
        left = false;
        right = false;
        up = false;
        down = false;
        moving = false;
        attacking = false;
    }

    public Facing getFacing() {
        return this.facing;
    }

    public void setFacing(Constants.PlayerConstants.Facing facing) {
        this.facing = facing;
    }

    public playerState getPlayerAction() {
        return this.playerAction;
    }

    public float getPlayerX() {
        return this.x;
    }

    public int getPlayerWidth() {
        return this.width;
    }

    public int getPlayerHeight() {
        return this.height;
    }

    public float getPlayerY() {
        return this.y;
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

    public void setPlayerAction(playerState playerAction) {
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

    public void setPlayerX(int x) {
        this.x = x;
    }

    public void setPlayerY(int y) {
        this.y = y;
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
