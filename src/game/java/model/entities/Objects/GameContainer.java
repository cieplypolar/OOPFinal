package model.entities.Objects;


import java.awt.*;

import static _utilities.constants.Constants.ObjectConstants.*;
import static _utilities.constants.Constants.ViewConstants.SCALE;

public class GameContainer extends GameObject {
    public GameContainer(int x, int y, int type) {
        super(x, y, type);
        createHitbox();
    }
    public void drawHitBox(Graphics g, int xlvlOffset, int ylvlOffset) {
        g.setColor(Color.CYAN);
        g.drawRect((int) hitBox.x - xlvlOffset, (int) hitBox.y - ylvlOffset, (int) hitBox.width, (int) hitBox.height);
    }
    protected void createHitbox() {
        if (type == CRATE) {
            initHitBox(25*SCALE,18*SCALE);
            xDrawOffset=CRATE_X_OFFSET* SCALE;
            yDrawOffset = CRATE_Y_OFFSET*SCALE;
        }
        else {
            initHitBox(23*SCALE,25*SCALE);
            xDrawOffset=BARREL_X_OFFSET* SCALE;
            yDrawOffset = BARREL_Y_OFFSET*SCALE;
        }
        hitBox.y+=yDrawOffset+SCALE*2;
        hitBox.x+=xDrawOffset/2;
    }
    public void update(){
        if(doAnimation)updateAnimationTick();
    }


    public void setAnimation(boolean b) {
        this.doAnimation=b;
    }
}