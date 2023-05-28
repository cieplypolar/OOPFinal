package view.player;

import controller.loop.Game;
import model.entities.Player;

import java.awt.*;
import java.awt.image.BufferedImage;

import static utilities.constants.Constants.PlayerConstants.*;
import static utilities.constants.Constants.PlayerConstants.playerState.*;
import static utilities.constants.Constants.View.SCALE;
import static utilities.helpers.PlayerHelperMethods.canMoveHere;
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

        float xTemp = 0, yTemp = 0;

        if (player.isLeft() && !player.isRight() && !player.isAttacking()) {
            player.setFacing(Facing.LEFT);
            xTemp = -player.getPlayerSpeed();
        }
        else if (player.isRight() && !player.isLeft() && !player.isAttacking()) {
            player.setFacing(Facing.RIGHT);
            xTemp = player.getPlayerSpeed();
        }
        else if (player.isRight() && player.isLeft() && !player.isAttacking()) return;

        if (canMoveHere(player.getHitBox().x + xTemp, player.getHitBox().y, player.getHitBox().width, player.getHitBox().height, game.getLevelManager().getLevel().getLevelLayout())) {
            player.getHitBox().x += xTemp;
            player.setMoving(true);
        }

        if (player.isUp() && !player.isDown() && !player.isAttacking())
            yTemp = -player.getPlayerSpeed();
        else if (player.isDown() && !player.isUp() && !player.isAttacking())
            yTemp = player.getPlayerSpeed();
        else if (player.isDown() && player.isUp() && !player.isAttacking()) return;


        if (canMoveHere(player.getHitBox().x, player.getHitBox().y + yTemp, player.getHitBox().width, player.getHitBox().height, game.getLevelManager().getLevel().getLevelLayout())) {
			player.getHitBox().y += yTemp;
			player.setMoving(true);
		}
    }

    public void render(Graphics g) {
        g.drawImage(((this.player.getFacing() == Facing.RIGHT) ? animations[getAnimationIndex(this.player.getPlayerAction().toString())][aniIndex] : reflectImg(animations[getAnimationIndex(this.player.getPlayerAction().toString())][aniIndex])),
                (int)(this.player.getHitBox().x - xOffSet * SCALE), (int)(this.player.getHitBox().y - yOffset * SCALE),
                PLAYER_WIDTH * SCALE, PLAYER_HEIGHT * SCALE, null);
        player.drawHitBox(g);
    }

    public Player getPlayer() {
        return player;
    }
}
