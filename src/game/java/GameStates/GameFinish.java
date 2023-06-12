package GameStates;

import controller.loop.Game;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import static GameStates.GameState.MENU;
import static GameStates.GameState.QUIT;
import static _utilities.constants.Constants.ViewConstants.GAME_HEIGHT;
import static _utilities.constants.Constants.ViewConstants.GAME_WIDTH;
import static _utilities.loaders.ImageHandler.importImg;

public class GameFinish extends State implements StateInterface{



    BufferedImage background;


    public GameFinish(Game game) {
        super(game);
        background = importImg("/menugraphics/finish.png");

    }
    @Override
    public void update(){

    }

    @Override
    public void render(Graphics g) {
        g.drawImage(background,0,0,GAME_WIDTH, GAME_HEIGHT, null);

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

                game.getGameRun().initClasses();
               GameState.gamestate = MENU;

    }



}
