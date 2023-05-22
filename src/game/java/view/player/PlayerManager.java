package view.player;

import model.entities.Player;

import java.awt.*;
import java.awt.image.BufferedImage;

import static utilities.constans.Constants.PlayerConstants.*;
import static utilities.constans.Constants.View.SCALE;
import static utilities.images.ImageHandler.reflectImg;
import static utilities.loaders.PlayerLoader.loadPlayerAnimations;

public class PlayerManager {
    private BufferedImage[][] animations;
    private int aniTick, aniIndex, aniSpeed = 25;
    private Player player;
    public PlayerManager(int x, int y, int health){
        this.player = new Player(x, y, health);
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
            if (aniIndex >= GetSpriteAmount(this.player.getPlayerAction())) {
                aniIndex = 0;
                this.player.setAttacking(false);
            }

        }
    }

    private void setAnimation() {
        int startAni = this.player.getPlayerAction();

        if (this.player.isMoving()) {
            this.player.setPlayerAction(RUNNING);
        }
        else {
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
        this.player.setMoving(false);

        if (this.player.isLeft() && !this.player.isRight()) {
            this.player.setPlayerX((int) (this.player.getPlayerX() - this.player.getPlayerSpeed()));
            this.player.setMoving(true);
        } else if (!this.player.isLeft() && this.player.isRight()) {
            this.player.setPlayerX((int) (this.player.getPlayerX() + this.player.getPlayerSpeed()));
            this.player.setMoving(true);
        }

        if (this.player.isUp() && !this.player.isDown()) {
            this.player.setPlayerY((int) (this.player.getPlayerY() - this.player.getPlayerSpeed()));
            this.player.setMoving(true);
        } else if (!this.player.isUp() && this.player.isDown()) {
            this.player.setPlayerY((int) (this.player.getPlayerY() + this.player.getPlayerSpeed()));
            this.player.setMoving(true);
        }
    }

    public void render(Graphics g) {
        g.drawImage(((this.player.getFacing() == Facing.RIGHT) ? animations[this.player.getPlayerAction()][aniIndex] : reflectImg(animations[this.player.getPlayerAction()][aniIndex])),
                (int) this.player.getPlayerX(), (int) this.player.getPlayerY(),
                this.player.getPlayerWidth() * SCALE, this.player.getPlayerHeight() * SCALE,
                null);
    }

    public Player getPlayer(){
        return player;
    }
}
