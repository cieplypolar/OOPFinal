package GameStates;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public interface StateInterface {
    public void update();
    public void render(Graphics g);
    public void keyTyped(KeyEvent e);
    public void keyReleased(KeyEvent e);
    public void keyPressed(KeyEvent e);
    public void mouseClicked(MouseEvent e);
    public void mousePressed(MouseEvent e);
    public void mouseDragged(MouseEvent e);
    public void mouseEntered(MouseEvent e);
    public void mouseExited(MouseEvent e);
    public void mouseMoved(MouseEvent e);
    public void mouseReleased(MouseEvent e);



}
