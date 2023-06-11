package model.entities.Objects;


import static _utilities.constants.Constants.ObjectConstants.*;
import static _utilities.constants.Constants.ViewConstants.SCALE;

public class GameContainer extends GameObject {
    public GameContainer(int x, int y, int type) {
        super(x, y, type);
        createHitbox();
    }

    protected void createHitbox() {
        if (type == CRATE) {
            initHitBox(25,18);
            xDrawOffset=CRATE_X_OFFSET* SCALE;
            yDrawOffset = CRATE_Y_OFFSET*SCALE;
        }
        else {
            initHitBox(23,25);
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