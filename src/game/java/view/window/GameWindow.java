package view.window;

import view.window.GamePanel;

import javax.swing.JFrame;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;

public class GameWindow {
    private final JFrame jframe;

    public GameWindow(GamePanel gamePanel) {

        jframe = new JFrame();

        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jframe.add(gamePanel);
        jframe.setLocationRelativeTo(null);
        jframe.setResizable(false);
        jframe.pack();
        jframe.setVisible(true);
        jframe.addWindowFocusListener(new WindowFocusListener() {
            @Override
            public void windowGainedFocus(WindowEvent e) {
                gamePanel.getGame().windowFocusLost();
            }
            @Override
            public void windowLostFocus(WindowEvent e) {

            }
        });
    }

}