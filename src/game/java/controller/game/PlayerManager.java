package controller.game;

import GameStates.GameState;
import _utilities.constants.Constants;
import controller.loop.Game;
import model.entities.Player;
import view.player.PlayerView;

import static _utilities.constants.Constants.ViewConstants.SCALE;
import static GameStates.GameState.*;
import static _utilities.constants.Constants.PlayerConstants.*;
import static _utilities.constants.Constants.PlayerConstants.playerState.*;
import static model.entities.EntityHelperMethods.canMoveHere;
import static model.entities.EntityHelperMethods.isOnGround;

public class PlayerManager {
    private Game game;
    private int aniTick, aniIndex, aniSpeed = 25;
    private Player player;
    private PlayerView playerView;
    private boolean attackCheck = false;

    public PlayerManager(Game game, int x, int y) {
        this.game = game;
        this.player = new Player(x, y);
        this.playerView = new PlayerView(player);
    }

    public void update() {
        updatePos();

        if (player.isMoving()) {
            game.getObjectManager().checkIfTouched(player.getHitBox());
            game.getObjectManager().checkSpikesTouched(player.getHitBox());
            if(isOnFinish(player.getHitBox(),game.getLevelManager().getLevel().getLevelLayout())){
                GameState.gamestate=FINISH;
            };
        }
        if (player.isAttacking()) game.getObjectManager().checkIfHit(player.getHitBox());

        if (player.getHealth() <= 0) {

            GameState.gamestate = DEAD;
            player.setIsDead(true);


        }
        if(player.isAttacking()){
            checkAttack();
        }
        updateAnimationTick();
        setAnimation();
        updateAttackBox();
    }

    private void checkAttack() {
      if (aniIndex == 0 || attackCheck) return;
      game.getGamerun().checkEnemyHit(player.getAttackBox());
      attackCheck = true;
    }

    public void checkHealth() {
        if (player.getHealth() <= 0) {
            GameState.gamestate = DEAD;
            player.setIsDead(true);
        }
    }

    private void updateAnimationTick() {
        aniTick++;
        if (aniTick >= aniSpeed) {
            aniTick = 0;
            aniIndex++;
            if (aniIndex >= getSpriteAmount(this.player.getPlayerAction().toString())) {
                if (player.getIsDead()) GameState.gamestate = GAMEOVER;
                aniIndex = 0;
                attackCheck = false;
                this.player.setAttacking(false);
            }
        }
    }

    private void setAnimation() {
        if (this.player.getIsDead()) {
            this.player.setPlayerAction(DIE);
            aniSpeed = 100;
            return;
        }
        PlayerState startAni = this.player.getPlayerAction();

        if (this.player.isMoving()) {
            this.player.setPlayerAction(RUNNING);
        } else {
            this.player.setPlayerAction(IDLE);
        }
        if (this.player.isAttacking()) {
            this.player.setPlayerAction(ATTACK_1);
        }
        if (this.player.isInAir()) {
            this.player.setPlayerAction(JUMP);
        }
        if (startAni != this.player.getPlayerAction()) {
            resetAniTick();
        }

    }

    public Player getPlayer() {
        return player;
    }

    private void resetAniTick() {
        aniTick = 0;
        aniIndex = 0;
    }

    public void resetPlayer() {

        player.setInAir(false);
        player.setAttacking(false);
        player.setMoving(false);
        player.setHealth(3);
        player.setPlayerAction(IDLE);

    }

    private void updatePos() {
        player.setMoving(false);
        if (!isOnGround(player.getHitBox(), game.getLevelManager().getLevel().getLevelLayout())) player.setInAir(true);
        if (player.isUp()) jump();
        if (!player.isLeft() && !player.isRight() && !player.isInAir())
            return;

        float xTemp = 0;
        if (GameState.gamestate != DEAD) {
            if (player.isLeft() && !player.isRight() && !player.isAttacking()) {
                player.setFacing(Constants.Facing.LEFT);
                xTemp = -player.getPlayerSpeed();

            } else if (player.isRight() && !player.isLeft() && !player.isAttacking()) {
                player.setFacing(Constants.Facing.RIGHT);
                xTemp = player.getPlayerSpeed();

            }
            if (player.isInAir()) {
                if (canMoveHere(player.getHitBox().x, player.getHitBox().y + player.getPlayerAirSpeed(), player.getHitBox().width, player.getHitBox().height, game.getLevelManager().getLevel().getLevelLayout())) {
                    player.getHitBox().y += player.getPlayerAirSpeed();
                    player.setPlayerAirSpeed(player.getGravity() + player.getPlayerAirSpeed());
                    updateXPos(xTemp);
                } else {
                    if (player.getPlayerAirSpeed() > 0) {
                        player.setPlayerAirSpeed(0f);
                        player.setInAir(false);
                    } else {
                        player.setPlayerAirSpeed(player.getfallSpeedCollision());
                    }
                    updateXPos(xTemp);
                }
            } else {
                updateXPos(xTemp);
            }
            player.setMoving(true);
        }
    }

    private void updateXPos(float xTemp) {
        if (canMoveHere(player.getHitBox().x + xTemp, player.getHitBox().y, player.getHitBox().width, player.getHitBox().height, game.getLevelManager().getLevel().getLevelLayout())) {
            player.getHitBox().x += xTemp;
            player.setMoving(true);
        }
    }

    private void updateAttackBox(){
        if(player.isRight()){
            player.getAttackBox().x = player.getHitBox().x + SCALE * 15;
        }
        else if (player.isLeft()){
            player.getAttackBox().x = player.getHitBox().x  - SCALE * 15;
        }
        player.getAttackBox().y = player.getHitBox().y + SCALE * 5;
    }

    private void jump() {
        if (player.isInAir()) return;
        player.setPlayerAirSpeed(player.getJumpSpeed());
        player.setInAir(true);
    }

    public PlayerView getPlayerView() {
        return playerView;
    }

    public int getAniIndex() {
        return aniIndex;
    }
}
