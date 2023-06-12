package gameStates;

import java.awt.*;
import java.awt.image.BufferedImage;

import static _utilities.constants.Constants.ViewConstants.SCALE;
import static _utilities.loaders.ImageHandler.importImg;

public class MenuButton {
    private GameState gamestate;
    private int xPos, yPos, index, whichstate;
    private final int WIDTH = 140;
    private final int HEIGHT = 56;
    private final int BUTTON_WIDTH = WIDTH * SCALE;
    private final int BUTTON_HEIGHT = HEIGHT * SCALE;
    private BufferedImage[] images;

    public Rectangle getHitbox() {
        return hitbox;
    }

    private Rectangle hitbox;

    public boolean isMouseOver() {
        return mouseOver;
    }

    public void setMouseOver(boolean mouseOver) {
        this.mouseOver = mouseOver;
    }

    private boolean mouseOver;

    public boolean isMousePressed() {
        return mousePressed;
    }

    public void setMousePressed(boolean mousePressed) {
        this.mousePressed = mousePressed;
    }

    private boolean mousePressed;

    public MenuButton(int x, int y, int index, GameState gamestate) {
        this.gamestate = gamestate;
        this.xPos = x - BUTTON_WIDTH / 2 - 10;
        this.yPos = y;
        this.index = index;
        hitbox = new Rectangle(xPos, yPos, BUTTON_WIDTH, BUTTON_HEIGHT);
        loadImages();

    }

    private void loadImages() {
        images = new BufferedImage[3];
        BufferedImage image = importImg("/menugraphics/buttons.png");
        for (int i = 0; i < images.length; i++) {
            images[i] = image.getSubimage(i * WIDTH, index * HEIGHT, WIDTH, HEIGHT);
        }
    }

    public void draw(Graphics g) {
        g.drawImage(images[whichstate], xPos, yPos, BUTTON_WIDTH, BUTTON_HEIGHT, null);
    }

    public void update() {
        whichstate = 0;
        if (mouseOver) whichstate = 1;
        if (mousePressed) whichstate = 2;

    }

    public void reset() {
        mouseOver = false;
        mousePressed = false;
    }

    public void changeGameState() {
        GameState.gamestate = this.gamestate;
    }

}
