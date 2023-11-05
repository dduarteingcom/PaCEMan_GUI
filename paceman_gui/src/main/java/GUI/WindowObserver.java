package GUI;

public class WindowObserver extends WindowClient{
    WindowObserver(){
    }

    /**
     * Receives the updates from the player
     * @param x Horizontal position in the panel.
     * @param y Vertical position in the panel.
     * @param nlevel Current level
     */
    void upDate(int x, int y,int[][]nlevel){
       player.x=x;
       player.y=y;
       this.cLevel =nlevel;
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

}
