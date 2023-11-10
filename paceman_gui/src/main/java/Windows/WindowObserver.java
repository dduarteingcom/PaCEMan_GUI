package Windows;

import GUIElements.ObserverLabel;

import javax.swing.*;
import java.awt.*;

public class WindowObserver extends WindowClient {
    private Integer id;
    private ObserverLabel label;
    WindowObserver(){
        label = new ObserverLabel();
    }

    /**
     * Receives the updates from the player
     * @param x Horizontal position in the panel.
     * @param y Vertical position in the panel.
     * @param nlevel Current level
     * @param score Current score
     */
    void upDate(Integer x, Integer y,Integer[][]nlevel, Integer score){
       player.x=x;
       player.y=y;
       this.cLevel =nlevel;
       setNumPoints(score);
    }

    /**
     * Overcharge ot the previous method, the difference is that this one receives the number of the current level
     * @param x Horizontal position in the panel.
     * @param y Vertical position in the panel.
     * @param nlevel Current level
     * @param score Current score
     * @param numlevel Number of the current level.
     */
    void upDate(Integer x, Integer y,Integer[][]nlevel, Integer score,Integer numlevel){
        player.x=x;
        player.y=y;
        this.cLevel =nlevel;
        setNumPoints(score);
        setNumLevel(numlevel);
    }

    public void disconnect(){
        running = false;
        this.removeAll();
        JPanel panel = new JPanel();
        panel.setLayout(null);
        ImageIcon backgroundImage = new ImageIcon("src/media/disconnected.png");
        Image img=backgroundImage.getImage();
        Image temp=img.getScaledInstance(650,650,Image.SCALE_SMOOTH);
        backgroundImage=new ImageIcon(temp);
        JLabel backgroundLabel = new JLabel(backgroundImage);
        backgroundLabel.setBounds(0, 0, backgroundImage.getIconWidth(), backgroundImage.getIconHeight());
        panel.add(backgroundLabel);
        this.add(panel);


    }

    /**
     * It's the game loop
     */
    @Override
    public void run(){
        //game loop
        long lastTime = System.nanoTime();
        double amountOfTicks =60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        while(running) {
            long now = System.nanoTime();
            delta += (now -lastTime)/ns;
            lastTime = now;
            if(delta >=1) {
                repaint();
                delta--;
            }
        }
    }

    Integer getId() {
        return id;
    }

    void setId(Integer id) {
        this.id = id;
    }
    @Override
    void drawOnMove(Graphics g) {
        super.drawOnMove(g);
        label.draw(g,getId());


    }
    /***
     * Updates Clydes's position
     * @param x Horizontal Position
     * @param y Vertical Position
     */
    void updateClyde(Integer x, Integer y) {
        clyde.x = x;
        clyde.y = y;
    }

    /**
     * Updates Blinky's position
     * @param x Horizontal Position
     * @param y Vertical Position
     */
    void updateBlinky(Integer x, Integer y) {
        blinky.x = x;
        blinky.y = y;
    }

    /**
     * Updates Inky's position
     * @param x Horizontal Position
     * @param y Vertical Position
     */
    void updateInky(Integer x, Integer y) {
        inky.x = x;
        inky.y = y;
    }

    /**
     * Updates Pinky's position
     * @param x Horizontal Position
     * @param y Vertical Position
     */
    void updatePinky(Integer x, Integer y) {
        pinky.x = x;
        pinky.y = y;
    }

    /**
     * Updates the player's lives
     * @param lives
     */
    void updateLives(Integer lives){
        setLives(lives);
    }

}
