package view.window;

import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JPanel;

import controller.inputs.KeyboardInputs;
import controller.inputs.MouseInputs;
import controller.loop.Game;

import static _utilities.constants.Constants.ViewConstants.*;

public class GamePanel extends JPanel {

    private MouseInputs mouseInputs;
    private Game game;

    public GamePanel(Game game) {
        mouseInputs = new MouseInputs(this);
        this.game = game;

        setPanelSize();
        addKeyListener(new KeyboardInputs(this));
        addMouseListener(mouseInputs);
        addMouseMotionListener(mouseInputs);
    }

    private void setPanelSize() {
        Dimension size = new Dimension(GAME_WIDTH, GAME_HEIGHT);
        setPreferredSize(size);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        game.render(g);

    }

    public Game getGame() {
        return game;
    }

}