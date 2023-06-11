package GameStates;

import controller.loop.Game;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import static GameStates.GameState.*;
import static utilities.images.ImageHandler.importImg;

public class Menu extends State implements StateInterface{

BufferedImage menubackground;
MenuButton[] buttons = new MenuButton[3];


    public Menu(Game game) {
        super(game);
        menubackground = importImg("/menugraphics/menu.png");
        buttons[0]= new MenuButton(Game.GAME_WIDTH/2, 200, 0,GAMERUN);
        buttons[1]= new MenuButton(Game.GAME_WIDTH/2, 300, 1,QUIT);
        buttons[2]= new MenuButton(Game.GAME_WIDTH/2, 400, 2,QUIT);
    }
    @Override
    public void update(){
        for(MenuButton button : buttons)button.update();
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(menubackground,0,0,Game.GAME_WIDTH, Game.GAME_HEIGHT, null);
        for(MenuButton button : buttons)button.draw(g);
    }
    public boolean isOver(MouseEvent e, MenuButton button){
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
        for(MenuButton button : buttons){
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
        for(MenuButton button : buttons){
            button.setMouseOver(false);
        }

        for(MenuButton button : buttons){
            if(isOver(e,button)){button.setMouseOver(true);
                break;}
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        for(MenuButton button : buttons){
            if(isOver(e,button)){button.changeGameState();
            break;}
        }


    }

    ;


}
