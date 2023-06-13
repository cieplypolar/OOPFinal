package view.player;

import _utilities.constants.Constants;
import model.entities.Player;

import java.awt.*;
import java.awt.image.BufferedImage;

import static _utilities.constants.Constants.PlayerConstants.*;
import static _utilities.constants.Constants.ViewConstants.SCALE;
import static _utilities.loaders.ImageHandler.importImg;
import static _utilities.loaders.ImageHandler.reflectImg;
import static _utilities.loaders.PlayerLoader.loadPlayerAnimations;

public class PlayerView {
    private Player player;
    private BufferedImage[][] animations;
    private BufferedImage healthbar;
    public PlayerView(Player player){
        this.player = player;
        this.animations = loadPlayerAnimations();
        healthbar = importImg("/objects/healthbar.png");

    }

    private void drawAttackBox(Graphics g, int xlvlOffset, int ylvlOffset){
        g.setColor(Color.RED);
        g.drawRect((int) player.getAttackBox().x - xlvlOffset, (int) player.getAttackBox().y - ylvlOffset, (int) player.getAttackBox().width, (int) player.getAttackBox().height);
    }

    public void render(Graphics g, int xlvlOffset, int ylvlOffset, int aniIndex) {
        g.drawImage(((this.player.getFacing() == Constants.Facing.RIGHT) ? animations[getAnimationIndex(this.player.getPlayerAction().toString())][aniIndex] : reflectImg(animations[getAnimationIndex(this.player.getPlayerAction().toString())][aniIndex])),
                (int) (this.player.getHitBox().x - xOffSet * SCALE) - xlvlOffset, (int) (this.player.getHitBox().y - yOffset * SCALE) - ylvlOffset,
                PLAYER_WIDTH * SCALE, PLAYER_HEIGHT * SCALE, null);
        player.drawHitBox(g, xlvlOffset, ylvlOffset);
        drawAttackBox(g, xlvlOffset, ylvlOffset);
        for(int i=0; i<this.player.getHealth();i++){
            g.drawImage(healthbar,10+i* healthbar.getWidth()*SCALE*2,10,healthbar.getWidth()*2*SCALE,  healthbar.getHeight()*2*SCALE,null);
        }
    }

}
