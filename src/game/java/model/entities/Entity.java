package model.entities;

import utilities.constants.Constants;

import java.awt.*;

import static utilities.constants.Constants.View.SCALE;

public abstract class Entity {
    protected double x;
    protected double y;
    protected double health;
    protected int width, height;
    protected Rectangle hitBox;

    protected Constants.PlayerConstants.Facing facing = Constants.PlayerConstants.Facing.RIGHT;

    Entity(double x, double y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width * SCALE;
        this.height = height * SCALE;
        this.health = health;
        initHitBox();
    }

    private void initHitBox() {
        hitBox = new Rectangle((int) x, (int) y, width, height);

    }
    public void drawHitBox(Graphics g){
        g.setColor(Color.CYAN);
        g.drawRect(hitBox.x, hitBox.y, hitBox.width, hitBox.height);
    }
    public void updateHitBox() {
        hitBox.x = (int) x;
        hitBox.y = (int) y;
    }
    public Rectangle getHitBox() {
        return hitBox;
    }
}
