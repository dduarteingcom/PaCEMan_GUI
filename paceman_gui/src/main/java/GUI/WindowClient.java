package GUI;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.net.Socket;
import java.util.LinkedList;

import AbstractFactory.*;
import AbstractFactory.Pinky;

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

    public Ghost ghost;

    public LinkedList<Ghost> ghostLinkedList;

    private final Score score;

    private Integer lives;

    private Integer numPoints;
    private Integer numLevel;
    private Integer toNextLife;
    private Integer toNextLevel;

    private Integer speed;

    /**
     * Current level
     */
    Integer[][] cLevel;
    WindowClient(){
        ghostLinkedList = new LinkedList<>();

        this.player = new Player();
        this.score = new Score();
        this.setFocusable(true);
        this.setPreferredSize(SCREEN_SIZE);
        this.setBackground(Color.BLACK);
        this.gameThread = new Thread(this);
        this.gameThread.start();
        this.cLevel = new Levels().level1;
        this.lives=3;
        this.numPoints=0;
        this.numLevel=1;
        this.speed = 5;
        this.toNextLevel = 500;
        this.toNextLife = 10000;
        ghostFactory = new EnemyFactory();
        this.ghost = ghostFactory.createGhost(20,20,'p');

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
     void draw(Graphics g) {
        for (int i = 0; i < cLevel.length; i++) {
            elementsFactory = new ResourceFactory();

            for (int j = 0; j < cLevel[0].length; j++) {
                if (cLevel[i][j] == 4) {
                    //Draws the blocks
                    switch (numLevel){
                        case 1:
                            elementsFactory.createElement(g,j*20,i*20, "b1");
                            break;
                        case 2:
                            elementsFactory.createElement(g,j*20,i*20, "b2");
                            break;
                        case 3:
                            elementsFactory.createElement(g,j*20,i*20, "b3");
                            break;

                    }
                } else if (cLevel[i][j] == 1) {
                    //Draws the dots
                    elementsFactory.createElement(g,j*20,i*20, "d");
                } else if (cLevel[i][j] == 2) {
                    //Draws the pills
                    elementsFactory.createElement(g,j*20,i*20, "f");
                } else if (cLevel[i][j] == 3) {
                    elementsFactory.createElement(g,j*20,i*20, "p");
                }
            }
        }
        this.player.draw(g);
        this.score.draw(g,lives,numPoints,numLevel);
        ghost.draw(g);

    }
    public void setNumPoints(Integer num){
        this.numPoints=num;
    }
    public Integer getNumPoints(){
        return this.numPoints;
    }

    public void moveGhost(){
         ghost.createMovement(cLevel);
    }

    public Integer getSpeed(){return this.speed;}
    public void setSpeed(Integer speed){this.speed = speed;}

    public Integer getToNextLife() {
        return toNextLife;
    }

    public void setToNextLife(Integer toNextLife) {
        this.toNextLife = toNextLife;
    }

    public Integer getToNextLevel() {
        return toNextLevel;
    }

    public void setToNextLevel(Integer toNextLevel) {
        this.toNextLevel = toNextLevel;
    }
}
