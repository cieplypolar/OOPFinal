package controller.game;

import controller.loop.Game;
import model.entities.Objects.Heart;
import model.entities.Objects.GameContainer;
import model.entities.Objects.Spikes;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import static _utilities.constants.Constants.ObjectConstants.*;
import static _utilities.constants.Constants.ViewConstants.SCALE;
import static _utilities.constants.Constants.ViewConstants.TILES_SIZE;
import static _utilities.loaders.ImageHandler.importImg;
import static _utilities.loaders.ObjectsLoader.loadContainers;
import static _utilities.loaders.ObjectsLoader.loadSpikes;

public class ObjectManager {
    Game game;
    private BufferedImage[][] itemImages, containerImages;
    private ArrayList<Heart> items;
    private ArrayList<GameContainer> containers;
    private BufferedImage spikeImage;
    private ArrayList<Spikes> spikes;

    public ObjectManager(Game game) {
        this.game = game;
        loadImages();
        items=new ArrayList<>();
        containers = loadContainers("/level.graphics/biggestlevel.png");
        spikes = loadSpikes("/level.graphics/biggestlevel.png");
    }

    private void loadImages() {

        BufferedImage containerimage = importImg("/objects/objects_sprites.png");
        containerImages = new BufferedImage[2][getSpriteAmount(BARREL)];
        for (int i = 0; i < containerImages.length; i++)
            for (int j = 0; j < containerImages[i].length; j++)
                containerImages[i][j] = containerimage.getSubimage(j * CONTAINER_WIDTH_DEFAULT, i * CONTAINER_HEIGHT_DEFAULT, CONTAINER_WIDTH_DEFAULT, CONTAINER_HEIGHT_DEFAULT);

        BufferedImage heartimage = importImg("/objects/heart.png");
        itemImages = new BufferedImage[1][getSpriteAmount(HEART)];
        for (int i = 0; i < itemImages.length; i++)
            for (int j = 0; j < itemImages[i].length; j++)
                itemImages[i][j] = heartimage.getSubimage(j * HEART_WIDTH_DEFAULT, i * HEART_HEIGHT_DEFAULT, HEART_WIDTH_DEFAULT, HEART_HEIGHT_DEFAULT);
        spikeImage = importImg("/objects/spikes.png");
    }

    public void update() {
        for (Heart heart : items) {
            if (heart.isActive()) heart.update();

        }
        for (GameContainer c : containers) {
            if (c.isActive()) c.update();
        }
    }
    public void checkIfTouched(Rectangle2D.Float hitbox){
        for(Heart heart : items){
            if(heart.isActive()){
                if(heart.getHitBox().intersects(hitbox)){
                    heart.setActive(false);
                    pickUp(heart);
                }
            }
        }

    }
    public void checkIfHit(Rectangle2D.Float hitbox) { //TODO: CHANGE TO ATTACKBOX
        for (GameContainer c : containers) {
            if (c.isActive() && !c.isDoAnimation()) {
                if(c.getHitBox().intersects(hitbox)){
                    c.setAnimation(true);
                    items.add(new Heart((int) (c.getHitBox().x+c.getHitBox().width/4),(int) (c.getHitBox().y+c.getHitBox().height/4),2));
                    System.out.println("what");
                }
            }
        }
    }
    public void checkSpikesTouched(Rectangle2D.Float hitbox){
        for(Spikes spike : spikes)if(spike.getHitBox().intersects(hitbox)){
            game.getPlayerManager().getPlayer().setHealth(0);
            game.getPlayerManager().checkHealth();
        }


    }
    public void pickUp(Heart heart){
        game.getPlayerManager().getPlayer().setHealth(game.getPlayerManager().getPlayer().getHealth()+1);
    }
    public void draw(Graphics g, int xLvlOffset, int yLvlOffset) {
        drawHearts(g, xLvlOffset, yLvlOffset);
        drawContainers(g, xLvlOffset, yLvlOffset);
        for(Spikes spike : spikes) g.drawImage(spikeImage,(int) spike.getHitBox().x - spike.getxDrawOffset()-xLvlOffset,
                (int) spike.getHitBox().y-yLvlOffset,TILES_SIZE*SCALE, TILES_SIZE*SCALE,null);

    }

    private void drawHearts(Graphics g, int xLvlOffset, int yLvlOffset) {
        for (Heart heart : items) {
            if (heart.isActive())
                g.drawImage(itemImages[0][heart.getAniIndex()], (int) heart.getHitBox().x - heart.getxDrawOffset() - xLvlOffset,
                        (int) heart.getHitBox().y - heart.getyDrawOffset() - yLvlOffset, HEART_WIDTH, HEART_HEIGHT, null);

        }
    }

    private void drawContainers(Graphics g, int xLvlOffset, int yLvlOffset) {
        for (GameContainer c : containers) {
            if (c.isActive()) {
                int type = 0;
                if (c.getType() == BARREL) type = 1;
                g.drawImage(containerImages[type][c.getAniIndex()], (int) c.getHitBox().x - c.getxDrawOffset() - xLvlOffset,
                        (int) c.getHitBox().y - c.getyDrawOffset() - yLvlOffset, CONTAINER_WIDTH, CONTAINER_HEIGHT, null);
                // c.drawHitBox(g,xLvlOffset,yLvlOffset);
            }

        }
    }


}

