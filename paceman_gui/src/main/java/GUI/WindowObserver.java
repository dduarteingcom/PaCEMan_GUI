package GUI;

import java.awt.*;

public class WindowObserver extends WindowClient{
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
     */
    void upDate(Integer x, Integer y,Integer[][]nlevel, Integer score){
       player.x=x;
       player.y=y;
       this.cLevel =nlevel;
       setNumPoints(score);
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
        while(true) {
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

}
