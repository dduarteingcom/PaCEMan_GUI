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
    private Integer lastExtraLife;
    private Integer toNextLevel;


    private Integer numGhosts;

    private Integer speed;


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
        this.lives=3;
        this.numPoints=0;
        this.numLevel=1;
        this.toNextLevel = 0;
        countPoints();
        this.speed = 5;

        this.lastExtraLife = 0;
        this.ghostFactory = new EnemyFactory();
        this.numGhosts = 0;
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

        if (ghostLinkedList != null && ghostLinkedList.size() > 0) {
            for (Ghost ghost : ghostLinkedList) {
                if (ghost != null){
                    ghost.draw(g);
                }
            }
        }
    }

    public void setNumPoints(Integer num) {
        this.numPoints = num;
    }

    public Integer getNumPoints() {
        return this.numPoints;
    }

    public void moveGhost() {
        if (ghostLinkedList != null && ghostLinkedList.size() > 0) {
            for (Ghost ghost : ghostLinkedList) {
                if (ghost != null){
                    ghost.createMovement(cLevel);
                }
            }
        }
    }
    public void createGhost(Integer x, Integer y, Character type) {
        switch (type){
            case 'p':
                pinky = ghostFactory.createGhost(x*20, y*20, type);
                ghostLinkedList.add(pinky);
                break;
            case 'b':
                blinky = ghostFactory.createGhost(x*20, y*20, type);
                ghostLinkedList.add(blinky);
            case 'i':
                inky = ghostFactory.createGhost(x*20, y*20, type);
                ghostLinkedList.add(inky);
            case 'c':
                clyde = ghostFactory.createGhost(x*20, y*20, type);
                ghostLinkedList.add(clyde);

        }
        numGhosts++;
    }

    public void setcLevel(Integer[][] cLevel){
        this.cLevel = cLevel;
    }

    public void setNumLevel(Integer numLevel){
        this.numLevel = numLevel;
    }
    public Integer getNumlevel(){
        return this.numLevel;
    }

    public Integer getLives() {
        return lives;
    }

    public void setLives(Integer lives) {
        this.lives = lives;
    }

    public Integer getSpeed(){return this.speed;}
    public void setSpeed(Integer speed){this.speed = speed;}

    public Integer getLastExtraLife() {
        return lastExtraLife;
    }

    public void setLastExtraLife(Integer lastExtraLife) {
        this.lastExtraLife = lastExtraLife;
    }

    public Integer getToNextLevel() {
        return toNextLevel;
    }

    public void setToNextLevel(Integer toNextLevel) {
        this.toNextLevel = toNextLevel;
    }

    void countPoints(){
        for (Integer i=0;i<cLevel.length;i++){
            for(Integer j=0;j<cLevel[0].length;j++){
                if (cLevel[i][j]==1){
                    toNextLevel+=10;
                }
            }
        }
    }

}
