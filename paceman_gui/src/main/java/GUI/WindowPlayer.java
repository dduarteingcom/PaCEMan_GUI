package GUI;

import java.awt.event.*;
import java.util.LinkedList;


public class WindowPlayer extends WindowClient  {

    LinkedList<Window> observers;

    boolean prueba = false;


    WindowPlayer() {
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                player.keyPressed(e);

            }

            public void keyReleased(KeyEvent e){
                player.keyReleased(e);
            }
        });
        player.arduino();


    }
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
                elementsMovement();
                updateClients();


            }
        }
    }

    public void addObserver(){
        Window observer = new Window(false);
        observers.add(observer);

    }


    public void updateClients(){


        observers.getFirst().panelObserver.upDate(player.x,player.y);
    }
}

