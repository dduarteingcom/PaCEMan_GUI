package Windows;

import javax.swing.*;
import java.awt.*;

public class Window extends JFrame {
    WindowPlayer panelPlayer;
    WindowObserver panelObserver;


    String player; //Indica el nombre del jugador de la partida

    Window(boolean type, String playername){ //Constructor de ventanas de expectador y jugador
        this.player = playername;
        if(type) {
            panelPlayer= new WindowPlayer(this.player);
            this.add(panelPlayer);
        }
        else{
            panelObserver = new WindowObserver();
            this.add(panelObserver);
        }
        this.setTitle(playername);
        this.setResizable(false);
        this.setBackground(Color.black);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }
    Window(){ //Constructor de la ventana principal (SINGLETON)
        this.setTitle("PaCEman");
        this.setResizable(false);
        this.setBackground(Color.black);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }
    public String getPlayer() {
        return player;
    }
}
