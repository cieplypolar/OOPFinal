package model.entities.Objects;

import java.awt.*;
import java.awt.geom.Rectangle2D;

import static _utilities.constants.Constants.ObjectConstants.*;

public class GameObject {
    protected int x,y,type;
    protected Rectangle2D.Float hitBox;
    protected boolean doAnimation, active;
    protected int aniTick, aniIndex, aniSpeed=25;
    protected int xDrawOffset, yDrawOffset;

    public GameObject(int x, int y, int type){
        this.x=x;
        this.y=y;
        this.type=type;
        doAnimation=false;
        active=true;
    }
    protected void initHitBox(int width, int height) {
        hitBox = new Rectangle2D.Float(x, y, width, height); //czy chcemy mnozyc przez scale?
    }

    public void drawHitBox(Graphics g, int xlvlOffset, int ylvlOffset) {
        g.setColor(Color.CYAN);
        g.drawRect((int) hitBox.x - xlvlOffset, (int) hitBox.y - ylvlOffset, (int) hitBox.width, (int) hitBox.height);
    }

    protected void updateAnimationTick() {
        aniTick++;
        if (aniTick >= aniSpeed) {
            aniTick = 0;
            aniIndex++;
            if (aniIndex >= getSpriteAmount(type)) {
                aniIndex = 0;
                if(type==BARREL || type == CRATE){ //TODO: CHANGE IT !!!
                    doAnimation=false;
                    active=false;
                }
            }
        }
    }
    public void reset() {
    aniIndex=0;
    aniTick=0;
    active=true;
        if(type==BARREL || type == CRATE)doAnimation=false;
        else doAnimation=true;
    //needs to be overrridden
    }

    public Rectangle2D.Float getHitBox() {
        return hitBox;
    }

    public int getType() {
        return type;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public int getxDrawOffset() {
        return xDrawOffset;
    }


    public int getyDrawOffset() {
        return yDrawOffset;
    }
    public int getAniIndex(){
        return aniIndex;
    }

    public boolean isDoAnimation(){
        return doAnimation;
    }
}



