package view.player;

import _utilities.constants.Constants;
import model.entities.Player;

import java.awt.*;
import java.awt.image.BufferedImage;

import static _utilities.constants.Constants.PlayerConstants.*;
import static _utilities.constants.Constants.ViewConstants.SCALE;
import static _utilities.loaders.ImageHandler.reflectImg;
import static _utilities.loaders.PlayerLoader.loadPlayerAnimations;

public class PlayerView {
    private Player player;
    private BufferedImage[][] animations;
    public PlayerView(Player player){
        this.player = player;
        this.animations = loadPlayerAnimations();
    }

    public void render(Graphics g, int xlvlOffset, int ylvlOffset, int aniIndex) {
        g.drawImage(((this.player.getFacing() == Constants.PlayerConstants.Facing.RIGHT) ? animations[getAnimationIndex(this.player.getPlayerAction().toString())][aniIndex] : reflectImg(animations[getAnimationIndex(this.player.getPlayerAction().toString())][aniIndex])),
                (int) (this.player.getHitBox().x - xOffSet * SCALE) - xlvlOffset, (int) (this.player.getHitBox().y - yOffset * SCALE) - ylvlOffset,
                PLAYER_WIDTH * SCALE, PLAYER_HEIGHT * SCALE, null);
        player.drawHitBox(g, xlvlOffset, ylvlOffset);
    }
}
