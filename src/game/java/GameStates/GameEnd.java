package GameStates;

import controller.loop.Game;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import static GameStates.GameState.*;
import static _utilities.constants.Constants.ViewConstants.GAME_HEIGHT;
import static _utilities.constants.Constants.ViewConstants.GAME_WIDTH;
import static _utilities.loaders.ImageHandler.importImg;

public class GameEnd extends State implements  StateInterface{




        BufferedImage background;
        GameOverButton[] buttons = new GameOverButton[2];


        public GameEnd(Game game) {
            super(game);
            background = importImg("/menugraphics/gameover.png");
            buttons[0]= new GameOverButton(GAME_WIDTH/2, 200, 0,MENU);
            buttons[1]= new GameOverButton(GAME_WIDTH/2, 350, 1,QUIT);
        }
        @Override
        public void update(){
            for(GameOverButton button : buttons)button.update();
        }

        @Override
        public void render(Graphics g) {
            g.drawImage(background,0,0,GAME_WIDTH, GAME_HEIGHT, null);
            for(GameOverButton button : buttons)button.draw(g);
        }
        public boolean isOver(MouseEvent e, GameOverButton button){
            return button.getHitbox().contains(e.getX(),e.getY());

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
            for(GameOverButton button : buttons){
                if(isOver(e,button))button.setMousePressed(true);
            }
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
            for(GameOverButton button : buttons){
                button.setMouseOver(false);
            }

            for(GameOverButton button : buttons){
                if(isOver(e,button)){button.setMouseOver(true);
                    break;}
            }
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            for(GameOverButton button : buttons){
                if(isOver(e,button)){
                    game.getGameRun().initClasses();
                    button.changeGameState();
                    break;}
            }


        }






}
