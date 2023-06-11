package model.entities.Objects;


import static _utilities.constants.Constants.ObjectConstants.*;
import static _utilities.constants.Constants.ViewConstants.SCALE;

public class Heart extends GameObject {


    public Heart(int x, int y, int type) {
        super(x, y, type);
        doAnimation=true;
        initHitBox(HEART_WIDTH_DEFAULT,HEART_HEIGHT_DEFAULT);
        xDrawOffset=1*SCALE;
        yDrawOffset=2*SCALE;

    }
    public void update(){
        updateAnimationTick();
    }

}
