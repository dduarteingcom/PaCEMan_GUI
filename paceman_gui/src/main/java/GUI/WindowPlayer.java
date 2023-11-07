package GUI;

import java.awt.event.*;
import java.util.LinkedList;
import java.util.Random;


public class WindowPlayer extends WindowClient  {
    private LinkedList<Window> observers;
    private Integer numObservers;

    String playername;

    WindowPlayer(String playername) {
        this.playername = playername;
        numObservers = 0;
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode()==KeyEvent.VK_F){
                    Integer[] coordenates=chooseLoc();
                    cLevel[coordenates[0]][coordenates[1]]=2;
                }
                if(e.getKeyCode()==KeyEvent.VK_P){
                    Integer[] coordenates=chooseLoc();
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
        int counter = 0;
        observers = new LinkedList<>();
        while(true) {
            counter++;
            long now = System.nanoTime();
            delta += (now -lastTime)/ns;
            lastTime = now;
            if(delta >=1) {
                repaint();

                if(observers.size() > 0) {
                    updateClients();
                }



            }
            if (counter == 2000000){
                player.arduino(cLevel);
                counter = 0;
            }
        }
    }

    /**
     * Creates and adds observers to the Linked List observers.
     */
    public void addObserver(){
        Integer number = observers.size() + 1;
        Window observer = new Window(false, playername + " Observer" + number);
        numObservers ++;
        observer.panelObserver.setId(numObservers);
        observers.add(observer);

    }

    /**
     * Updates its clients with positions and the map status
     */
    public void updateClients() {
        for (Integer i = 0; i < observers.size(); i++) {
            observers.get(i).panelObserver.upDate(player.x, player.y, cLevel, getNumPoints());
        }
    }

    /**
     * Checks if PaCE has eatean a pill, fruit or dot
     */
    private void checkResources(){
        int valuePos = cLevel[player.posY][player.posX];
        if(valuePos==1||valuePos==2||valuePos==3){
            cLevel[player.posY][player.posX]=0;
            if(valuePos==1){
                Integer newScore = getNumPoints()+1;
                setNumPoints(newScore);
            }
        }
    }

    /**
     * Decides where a resource must be located.
     * @return A array with the chosen coordinates.
     */
    private Integer[] chooseLoc(){
        Integer[] coordenates = new Integer[2];
        Random random1 = new Random();
        Random random2 = new Random();
        int randomX = random1.nextInt(15);
        int randomY =random2.nextInt(17);
        int valcellFound = cLevel[randomX][randomY];
        if(valcellFound==0 && randomX!= player.posX && randomY != player.posY){
            coordenates[0]=randomX;
            coordenates[1]=randomY;
            return coordenates;
        }
        else {
            return chooseLoc();
        }
    }
}

