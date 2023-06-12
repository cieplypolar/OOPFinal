package controller.game;


import gameStates.GameRun;
import model.entities.Goblin;
import model.entities.Player;
import org.w3c.dom.css.Rect;


import java.awt.geom.Rectangle2D;
import java.util.ArrayList;


import static _utilities.constants.Constants.Paths.LEVEL_DATA;

import static _utilities.loaders.LevelLoader.getGoblins;

public class SuperiorEnemyManager {
    private GameRun playing;
    private ArrayList<GoblinManager> goblinArr = new ArrayList<>();

    public SuperiorEnemyManager(GameRun playing) {
        this.playing = playing;
        getEnemies();
    }

    public void checkEnemyHit(Rectangle2D.Float attackBox){
        for (GoblinManager gob : goblinArr){
            if(attackBox.intersects(gob.getEnemy().getHitBox())){
                gob.getEnemy().hurt(1);
                return;
            }
        }
    }

    private void getEnemies() {
        goblinArr = getGoblins(LEVEL_DATA);
    }

    public void update(int[][] lvlData, Player player) {
        for (GoblinManager g : goblinArr) {
            if(!g.getEnemy().isAlive()) {
                continue;
            }
            g.update(lvlData, player);
        }
    }

    public ArrayList<GoblinManager> getGoblinArr() {
        return goblinArr;
    }

    public GameRun getPlaying() {
        return playing;
    }
}
