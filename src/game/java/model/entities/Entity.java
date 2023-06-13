package model.entities;

import _utilities.constants.Constants;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public abstract class Entity {
    protected float x;
    protected float y;
    protected int health;
    protected int width, height;
    protected Rectangle2D.Float hitBox;

    protected Constants.Facing facing = Constants.Facing.RIGHT;

    Entity(float x, float y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    protected void initHitBox(float x, float y, float width, float height) {
        hitBox = new Rectangle2D.Float(x, y, width, height);
    }

    public void drawHitBox(Graphics g, int xlvlOffset, int ylvlOffset) {
        g.setColor(Color.CYAN);
        g.drawRect((int) hitBox.x - xlvlOffset, (int) hitBox.y - ylvlOffset, (int) hitBox.width, (int) hitBox.height);
    }

    public Rectangle2D.Float getHitBox() {
        return hitBox;
    }
}
