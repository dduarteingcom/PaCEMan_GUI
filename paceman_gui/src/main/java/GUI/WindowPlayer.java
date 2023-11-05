package GUI;

import java.awt.event.*;
import java.util.LinkedList;
import java.util.Random;


public class WindowPlayer extends WindowClient  {
    LinkedList<Window> observers;

    WindowPlayer() {
        //Make decisions on the basis of the keypressed
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode()==KeyEvent.VK_F){
                    int[] coordenates=chooseLoc();
                    cLevel[coordenates[0]][coordenates[1]]=2;
                }
                if(e.getKeyCode()==KeyEvent.VK_P){
                    int[] coordenates=chooseLoc();
                    cLevel[coordenates[0]][coordenates[1]]=3;
                }
                player.keyPressed(e, cLevel);
                checkResources();
            }
            public void keyReleased(KeyEvent e){
                player.keyReleased(e);
            }
        });
    }

    /**
     * It's the game loop.
     */
    @Override
    public void run(){
        //game loop
        long lastTime = System.nanoTime();
        double amountOfTicks =60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        observers = new LinkedList<>();
        addObserver();
        while(true) {
            long now = System.nanoTime();
            delta += (now -lastTime)/ns;
            lastTime = now;
            if(delta >=1) {
                repaint();
                updateClients();
            }
        }
    }

    /**
     * Creates and adds observers to the Linked List observers.
     */
    public void addObserver(){
        Window observer = new Window(false);
        observers.add(observer);
    }

    /**
     * Updates its clients with positions and the map status
     */
    public void updateClients(){
        observers.getFirst().panelObserver.upDate(player.x,player.y, cLevel);
    }

    /**
     * Checks if PaCE has eatean a pill, fruit or dot
     */
    private void checkResources(){
        int valuePos = cLevel[player.posY][player.posX];
        if(valuePos==1||valuePos==2||valuePos==3){
            cLevel[player.posY][player.posX]=0;
        }
    }

    /**
     * Decides where a resource must be located.
     * @return A array with the chosen coordinates.
     */
    private int[] chooseLoc(){
        int[] coordenates = new int[2];
        Random random1 = new Random();
        Random random2 = new Random();
        int randomX = random1.nextInt(15);
        int randomY =random2.nextInt(17);
        int valcellFound = cLevel[randomX][randomY];
        if(valcellFound==0 && randomX!= player.posX && randomY != player.posY){
            System.out.println(valcellFound);
            coordenates[0]=randomX;
            coordenates[1]=randomY;
            return coordenates;
        }
        else {
            return chooseLoc();
        }

    }
}

