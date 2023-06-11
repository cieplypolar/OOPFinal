package model.entities.Objects;

import static _utilities.constants.Constants.ViewConstants.SCALE;
import static _utilities.constants.Constants.ViewConstants.TILES_SIZE;

public class Spikes extends GameObject{


    public Spikes(int x, int y, int type) {
        super(x, y, type);
        initHitBox(TILES_SIZE*SCALE,TILES_SIZE*SCALE/2);
        xDrawOffset=0;
        yDrawOffset=TILES_SIZE*SCALE/2;
    }


}
