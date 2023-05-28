package model.entities;

import utilities.constants.Constants;

public abstract class Entity {
    protected double x;
    protected double y;
    protected double health;

    protected Constants.PlayerConstants.Facing facing = Constants.PlayerConstants.Facing.RIGHT;

    Entity(double x, double y, double health) {
        this.x = x;
        this.y = y;
        this.health = health;
    }
}
