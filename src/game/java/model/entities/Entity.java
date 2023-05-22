package model.entities;

public abstract class Entity {
    protected double x;
    protected double y;
    protected double health;
    Entity(double x, double y, double health){
        this.x = x;
        this.y = y;
        this.health = health;
    }
}
