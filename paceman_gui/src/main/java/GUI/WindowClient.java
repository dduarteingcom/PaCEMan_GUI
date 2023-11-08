package GUI;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;

import AbstractFactory.*;

public class WindowClient extends JPanel implements Runnable {
    public static final int WINDOW_WIDTH = 470;
    public static final int WINDOW_HEIGHT = 300;
    static final Dimension SCREEN_SIZE = new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT);
    private Graphics graphics;
    Player player;
    private final Thread gameThread;
    private Image image;
    private ElementsFactory elementsFactory;

    private GhostFactory ghostFactory;

    public Ghost pinky;

    public Ghost blinky;

    public Ghost inky;

    public Ghost clyde;

    public LinkedList<Ghost> ghostLinkedList;

    private final Score score;

    private Integer lives;

    private Integer numPoints;
    private Integer numLevel;

    private int numGhosts;

    private int currentValueFruit;


    /**
     * Current level
     */
    Integer[][] cLevel;

    WindowClient() {
        ghostLinkedList = new LinkedList<>();
        this.player = new Player();
        this.score = new Score();
        this.setFocusable(true);
        this.setPreferredSize(SCREEN_SIZE);
        this.setBackground(Color.BLACK);
        this.gameThread = new Thread(this);
        this.gameThread.start();
        this.cLevel = new Levels().level1;
        this.lives = 3;
        this.numPoints = 0;
        this.numLevel = 1;
        ghostFactory = new EnemyFactory();
        this.numGhosts = 0;
        this.currentValueFruit=0;


    }

    /**
     * It's the game loop.
     */
    public void run() {
    }

    /**
     * Refresh the elements of the panel
     *
     * @param g the <code>Graphics</code> context in which to paint
     */
    public void paint(Graphics g) {
        image = createImage(getWidth(), getHeight());
        graphics = image.getGraphics();
        drawStaticE(graphics);
        drawOnMove(graphics);

        g.drawImage(image, 0, 0, this);
    }

    /**
     * Draws all the elements on the panel
     *
     * @param g the <code>Graphics</code> context in which to paint
     */
    void drawStaticE(Graphics g) {
        for (int i = 0; i < cLevel.length; i++) {
            elementsFactory = new ResourceFactory();

            for (int j = 0; j < cLevel[0].length; j++) {
                if (cLevel[i][j] == 4) {
                    //Draws the blocks
                    switch (numLevel) {
                        case 1:
                            elementsFactory.createElement(g, j * 20, i * 20, "b1");
                            break;
                        case 2:
                            elementsFactory.createElement(g, j * 20, i * 20, "b2");
                            break;
                        case 3:
                            elementsFactory.createElement(g, j * 20, i * 20, "b3");
                            break;

                    }
                } else if (cLevel[i][j] == 1) {
                    //Draws the dots
                    elementsFactory.createElement(g, j * 20, i * 20, "d");
                } else if (cLevel[i][j] == 2) {
                    //Draws the pills
                    elementsFactory.createElement(g, j * 20, i * 20, "f");
                } else if (cLevel[i][j] == 3) {
                    elementsFactory.createElement(g, j * 20, i * 20, "p");
                }
            }
        }

    }

    void drawOnMove(Graphics g) {
        this.player.draw(g);
        this.score.draw(g, lives, numPoints, numLevel);
        switch (numGhosts) {
            case 1:
                pinky.draw(g);
            case 2:
                blinky.draw(g);
            case 3:
                inky.draw(g);
            case 4:
                clyde.draw(g);
        }
    }

    public void setNumPoints(Integer num) {
        this.numPoints = num;
    }

    public Integer getNumPoints() {
        return this.numPoints;
    }

    public void moveGhost() {
        pinky.move();
    }
    public void createGhost() {
        switch (numGhosts) {
            case 0:
                pinky = ghostFactory.createGhost(20, 20, 'p');
                numGhosts++;
                break;
            case 1:
                blinky = ghostFactory.createGhost(20,20,'b');
                numGhosts++;
                break;
            case 2:
                inky = ghostFactory.createGhost(20,20,'i');
                numGhosts++;
                break;
            case 3:
                clyde = ghostFactory.createGhost(20,20,'c');
                numGhosts++;
                break;
        }
    }
    public void increseLives(){
        this.lives++;
    }

    public int getCurrentValueFruit() {
        return currentValueFruit;
    }

    public void setCurrentValueFruit(int currentValueFruit) {
        this.currentValueFruit = currentValueFruit;
    }
}
