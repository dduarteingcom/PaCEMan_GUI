package GUI;

import java.awt.event.*;
import java.util.LinkedList;


public class WindowPlayer extends WindowClient  {

    LinkedList<Window> observers;
    String playername;

    boolean prueba = false;

    WindowPlayer(String playername) {
        this.playername = playername;
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                player.keyPressed(e);

            }

            public void keyReleased(KeyEvent e){
                player.keyReleased(e);
            }
        });


    }
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
                elementsMovement();

                if(observers.size() > 0) {
                    updateClients();
                }



            }
            if (counter == 2000000){
                player.arduino();
                counter = 0;
            }
        }
    }

    public void addObserver(){
        Integer number = observers.size() + 1;
        Window observer = new Window(false, playername + " Observer" + number);
        observers.add(observer);

    }


    public void updateClients(){
        for (Integer i = 0; i < observers.size(); i++){
            observers.get(i).panelObserver.upDate(player.x, player.y);
        }
    }
}

