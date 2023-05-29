package model.entities;

import utilities.constants.Constants;

import java.awt.*;
import java.awt.geom.Rectangle2D;

import static utilities.constants.Constants.View.SCALE;

public abstract class Entity {
    protected float x;
    protected float y;
    protected float health;
    protected int width, height;
    protected Rectangle2D.Float hitBox;

    protected Constants.PlayerConstants.Facing facing = Constants.PlayerConstants.Facing.RIGHT;

    Entity(float x, float y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    protected void initHitBox(float x, float y, float width, float height) {
        hitBox = new Rectangle2D.Float(x, y, width, height);
    }

    public void drawHitBox(Graphics g, int lvlOffset) {
        g.setColor(Color.CYAN);
        g.drawRect((int) hitBox.x - lvlOffset, (int) hitBox.y, (int) hitBox.width, (int) hitBox.height);
    }

    public Rectangle2D.Float getHitBox() {
        return hitBox;
    }
}
