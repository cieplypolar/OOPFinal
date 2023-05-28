package view.player;

import controller.loop.Game;
import model.entities.Player;

import java.awt.*;
import java.awt.image.BufferedImage;

import static utilities.constants.Constants.PlayerConstants.*;
import static utilities.constants.Constants.PlayerConstants.playerState.*;
import static utilities.constants.Constants.View.SCALE;
import static utilities.helpers.PlayerHelperMethods.CanMoveHere;
import static utilities.images.ImageHandler.reflectImg;
import static utilities.loaders.PlayerLoader.loadPlayerAnimations;

public class PlayerManager {
    private Game game;
    private BufferedImage[][] animations;
    private int aniTick, aniIndex, aniSpeed = 25;
    private Player player;

    public PlayerManager(Game game, int x, int y) {
        this.game = game;
        this.player = new Player(x, y);
        this.animations = loadPlayerAnimations();
    }

    public void update() {
        updatePos();
        player.updateHitBox();
        updateAnimationTick();
        setAnimation();
    }

    private void updateAnimationTick() {
        aniTick++;
        if (aniTick >= aniSpeed) {
            aniTick = 0;
            aniIndex++;
            if (aniIndex >= getSpriteAmount(this.player.getPlayerAction().toString())) {
                aniIndex = 0;
                this.player.setAttacking(false);
            }
        }
    }

    private void setAnimation() {
        playerState startAni = this.player.getPlayerAction();

        if (this.player.isMoving()) {
            this.player.setPlayerAction(RUNNING);
        } else {
            this.player.setPlayerAction(IDLE);
        }
        if (this.player.isAttacking()) {
            this.player.setPlayerAction(ATTACK_1);
        }
        if (startAni != this.player.getPlayerAction()) {
            resetAniTick();
        }
    }

    private void resetAniTick() {
        aniTick = 0;
        aniIndex = 0;
    }

    private void updatePos() {
        player.setMoving(false);

        if (!player.isLeft() && !player.isRight() && !player.isUp() && !player.isDown())
            return;

        double xTemp = 0, yTemp = 0;

        if (player.isLeft() && !player.isRight() && !player.isAttacking())
            xTemp = -player.getPlayerSpeed();
        else if (player.isRight() && !player.isLeft() && !player.isAttacking())
            xTemp = player.getPlayerSpeed();

        if (player.isUp() && !player.isDown() && !player.isAttacking())
            yTemp = -player.getPlayerSpeed();
        else if (player.isDown() && !player.isUp() && !player.isAttacking())
            yTemp = player.getPlayerSpeed();
        if (CanMoveHere(player.getPlayerX() + xTemp, player.getPlayerY() + yTemp, player.getPlayerWidth(), player.getPlayerHeight(), game.getLevelManager().getLevel().getLevelLayout())) {
			player.setPlayerX((int) (player.getPlayerX() + xTemp));
			player.setPlayerY((int) (player.getPlayerY() + yTemp));
			player.setMoving(true);
		}
    }

    public void render(Graphics g) {
        g.drawImage(((this.player.getFacing() == Facing.RIGHT) ? animations[getAnimationIndex(this.player.getPlayerAction().toString())][aniIndex] : reflectImg(animations[getAnimationIndex(this.player.getPlayerAction().toString())][aniIndex])), (int) this.player.getPlayerX(), (int) this.player.getPlayerY(), PLAYER_WIDTH * SCALE, PLAYER_HEIGHT * SCALE, null);
        player.drawHitBox(g);
    }

    public Player getPlayer() {
        return player;
    }
}
