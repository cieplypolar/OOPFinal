package GameStates;

import controller.loop.Game;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import static GameStates.GameState.GAMERUN;

public class Menu extends State implements StateInterface{

    public Menu(Game game) {
        super(game);
    }
    @Override
    public void update(){

    }

    @Override
    public void render() {

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
        GameState.gamestate = GAMERUN;
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

    }

    ;


}
