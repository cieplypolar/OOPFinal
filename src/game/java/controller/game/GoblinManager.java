package controller.game;

import _utilities.constants.Constants;
import model.entities.EnemyUnfortunatelyOnlyGoblin;
import model.entities.Player;

import java.awt.*;

import static _utilities.constants.Constants.Enemy.getEnemyAnimationIndex;

public class GoblinManager extends EnemyManager{
    private boolean attackCheck = false;
    public GoblinManager(EnemyUnfortunatelyOnlyGoblin enemy) {
        super(enemy);
    }
    public void update(int[][] lvlData, Player player) {
        updateMove(lvlData, player);
        updateAnimationTick();
        updateAttackBox();
    }
    //debug
    public void drawAttackBox(Graphics g, int xlvlOffset, int ylvlOffset){
        g.setColor(Color.RED);
        g.drawRect((int) enemy.getAttackBox().x - xlvlOffset, (int) enemy.getAttackBox().y - ylvlOffset, (int) enemy.getAttackBox().width, (int) enemy.getAttackBox().height);
    }

    public int getAniIndex() {
        return aniIndex;
    }
    public void updateMove(int[][] lvlData, Player player) {
        firstUpdateCheck(lvlData);
        if(enemy.isInAir()){
            inAirUpdate(lvlData);
        }
        else{
            switch (enemy.getEnemyState()) {
                case IDLE:
                    changeState(Constants.Enemy.EnemyState.RUNNING);
                    break;
                case RUNNING:
                    if(canSeePlayer(lvlData, player)){
                        moveTowardsPlayer(player);
                    }
                    if (isPlayerAttackable(player) && canSeePlayer(lvlData, player)){
                        changeState(Constants.Enemy.EnemyState.ATTACK);
                    }
                    moveEnemy(lvlData);
                    break;
                case ATTACK:
                    if (aniIndex == 0){
                        attackCheck = false;
                    }
                    if (aniIndex == getEnemyAnimationIndex(Constants.Enemy.EnemyType.Goblin, Constants.Enemy.EnemyState.ATTACK.toString()) - 1
                    && !attackCheck){
                        getEnemy().checkEnemyHit(player);
                        attackCheck = true;
                    }
                    break;
                case HIT:
                    break;
            }
        }
    }
}
