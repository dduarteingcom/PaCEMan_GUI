package Windows;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

import AbstractFactory.*;
import Data.Levels;
import GUIElements.Player;
import GUIElements.Score;

public class WindowClient extends JPanel implements Runnable {
    public static final Integer WINDOW_WIDTH = 470;
    public static final Integer WINDOW_HEIGHT = 300;
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
    Double speed_mod;


    Boolean pCreated;
    Boolean cCreated;
    Boolean bCreated;
    Boolean iCreated;

    int speed_changes;
    private Integer numGhosts;

    private Integer speed;
    boolean running;

    boolean powerActivated;


    /**
     * Current level
     */
    Integer[][] cLevel;

    WindowClient() {
        this.speed_changes = 0;
        this.speed_mod = 1.0;
        running = true;
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
        this.ghostFactory = new EnemyFactory();
        this.numGhosts = 0;
        this.bCreated=false;
        this.cCreated=false;
        this.iCreated=false;
        this.pCreated=false;
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


            for (int j = 0; j < cLevel[0].length; j++) {
                if (cLevel[i][j] == 4) {
                    elementsFactory = new BlocksFactory();
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
                    elementsFactory = new ResourceFactory();
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

    /**
     * Draws all the elements that are moving
     * @param g The graphics
     */
    void drawOnMove(Graphics g) {
        this.player.draw(g);
        this.score.draw(g, lives, numPoints, numLevel);

        if (ghostLinkedList != null && !ghostLinkedList.isEmpty()) {
            for (Ghost ghost : ghostLinkedList) {
                if (ghost != null){
                    ghost.draw(g);
                }
            }
        }
    }

    /**
     * Check if a collision occurred
     */
    void checkColissions(){ //Checks for colissions between player and ghosts
        if (ghostLinkedList != null && ghostLinkedList.size() > 0) {
            for (Ghost ghost : ghostLinkedList) {
                if (ghost != null){
                    if (ghost.x == player.x && ghost.y == player.y){
                        System.out.println("Choque");
                        if(!powerActivated){
                            player.x = 20; //Returns player to start position
                            player.y = 20;
                            lives--;
                        }
                        else {
                            ghost.setAlive(false);
                            ghost.y=10000;
                            ghost.x=10000;
                            Timer timer = new Timer(1000, new ActionListener() {
                                int contador = 10; // Establecer el tiempo inicial en segundos
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    if (contador > 0) {

                                        contador--;
                                    }
                                    else {
                                        ghost.x=20;
                                        ghost.y=20;
                                        ghost.setPosX(1);
                                        ghost.setPosY(1);
                                        ghost.setAlive(true);
                                        ((Timer) e.getSource()).stop(); // Detener el temporizador cuando el tiempo llega a cero
                                    }
                                }
                            });
                            timer.start();
                        }
                        if (lives == 0){
                            this.setVisible(false);
                            JOptionPane.showMessageDialog(this, "Has perdido el juego", "No hay vidas restantes", JOptionPane.PLAIN_MESSAGE);
                            running = false;
                        }
                    }
                }
            }
        }
    }

    /**
     * Moves ghosts around
     */
    public void moveGhost() {
        if (ghostLinkedList != null && !ghostLinkedList.isEmpty()) {
            for (Ghost ghost : ghostLinkedList) {
                if (ghost != null){
                    ghost.createMovement(cLevel);
                }
            }
        }
    }

    /**
     * Creates a ghost on the panel
     * @param x Horizontal position
     * @param y Vertical postition
     * @param type The ghost that is going to be created
     */
    public void createGhost(Integer x, Integer y, Character type) {
        switch (type){
            case 'p':
                if(!pCreated) {
                    pinky = ghostFactory.createGhost(x * 20, y * 20, type);
                    ghostLinkedList.add(pinky);
                    pCreated = true;
                    break;
                }
            case 'b':
                if(!bCreated) {
                    blinky = ghostFactory.createGhost(x * 20, y * 20, type);
                    bCreated = true;
                    ghostLinkedList.add(blinky);
                }break;
            case 'i':
                if(!iCreated) {
                    inky = ghostFactory.createGhost(x * 20, y * 20, type);
                    iCreated = true;
                    ghostLinkedList.add(inky);
                }break;
            case 'c':
                if(!cCreated) {
                    clyde = ghostFactory.createGhost(x * 20, y * 20, type);
                    cCreated = true;
                    ghostLinkedList.add(clyde);
                }break;

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
    public void setNumPoints(Integer num) {
        this.numPoints = num;
    }

    public Integer getNumPoints() {
        return this.numPoints;
    }

}
