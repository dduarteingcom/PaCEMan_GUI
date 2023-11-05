package GUI;

public class WindowObserver extends WindowClient{
    WindowObserver(){
    }

    public void upDate(int x, int y){
       player.x=x;
       player.y=y;
    }
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
