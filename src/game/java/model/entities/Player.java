package model.entities;

import static utilities.constans.Constants.PlayerConstants.*;

public class Player extends Entity {
    private int playerAction = IDLE;
    private boolean moving = false, attacking = false;
    private boolean left, up, right, down;
    private double playerSpeed = 2.0f;

    private int playerWidth = 50, playerHeight = 37;
    public Player(double x, double y, double health) {
        super(x, y, health);
    }

    public void resetDirBooleans() {
        left = false;
        right = false;
        up = false;
        down = false;
    }

    public int getPlayerHeight() {
        return this.playerHeight;
    }

    public int getPlayerWidth() {
        return this.playerWidth;
    }

    public int getPlayerAction(){
        return this.playerAction;
    }

    public double getPlayerX(){
        return this.x;
    }

    public double getPlayerY(){
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

    public void setPlayerAction(int playerAction) {
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

    public void setPlayerX(int x){
        this.x = x;
    }

    public void setPlayerY(int y){
        this.y = y;
    }

    public double getPlayerSpeed(){
        return this.playerSpeed;
    }
}
