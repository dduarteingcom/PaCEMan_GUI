package GUI;

import javax.swing.*;
import java.awt.*;

import AbstractFactory.*;

public class WindowClient extends JPanel implements Runnable {
    public static final int WINDOW_WIDTH = 470;
    public static final int WINDOW_HEIGHT = 300;
    static final Dimension SCREEN_SIZE = new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT);
    private Graphics graphics;
    Player player;
    private Thread gameThread;
    private Image image;
    private ElementsFactory elementsFactory;
    /**
     * Current level
     */
    int[][] cLevel;
    WindowClient(){
        player = new Player();
        this.setFocusable(true);
        this.setPreferredSize(SCREEN_SIZE);
        this.setBackground(Color.BLACK);
        gameThread = new Thread(this);
        gameThread.start();

        cLevel = new Levels().level1;
    }

    /**
     * It's the game loop.
     */
    public void run(){
    }

    /**
     * Refresh the elements of the panel
     * @param g  the <code>Graphics</code> context in which to paint
     */
    public void paint(Graphics g) {
        image = createImage(getWidth(), getHeight());
        graphics = image.getGraphics();
        draw(graphics);
        g.drawImage(image, 0, 0, this);
    }

    /**
     * Draws all the elements on the panel
     * @param g the <code>Graphics</code> context in which to paint
     */
    private void draw(Graphics g) {
        for (int i = 0; i < cLevel.length; i++) {
            elementsFactory = new ResourceFactory();
            for (int j = 0; j < cLevel[0].length; j++) {
                if (cLevel[i][j] == 4) {
                    //Draws the blocks
                    elementsFactory.createElement(g,j*20,i*20, 'b');
                } else if (cLevel[i][j] == 1) {
                    //Draws the dots
                    elementsFactory.createElement(g,j*20,i*20, 'd');
                } else if (cLevel[i][j] == 2) {
                    //Draws the pills
                    elementsFactory.createElement(g,j*20,i*20, 'f');
                } else if (cLevel[i][j] == 3) {
                    elementsFactory.createElement(g,j*20,i*20, 'p');
                }
            }
        }
        this.player.draw(g);

    }




}
