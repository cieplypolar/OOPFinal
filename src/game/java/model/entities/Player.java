package model.entities;

import utilities.constants.Constants;

import static utilities.constants.Constants.PlayerConstants.*;
import static utilities.constants.Constants.PlayerConstants.playerState.*;
import static utilities.constants.Constants.View.SCALE;

public class Player extends Entity {
    private playerState playerAction = IDLE;
    private boolean moving = false, attacking = false;
    private boolean left, up, right, down;
    private float playerSpeed = 1.5f;
    public Player(float x, float y) {
        super(x, y, REAL_PLAYER_WIDTH * SCALE, REAL_PLAYER_HEIGHT * SCALE);
        initHitBox(x, y, REAL_PLAYER_WIDTH * SCALE, REAL_PLAYER_HEIGHT * SCALE);
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
    public int getPlayerWidth(){
        return this.width;
    }
    public int getPlayerHeight(){
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


}
