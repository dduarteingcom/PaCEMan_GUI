package GUI;

import javax.swing.*;
import java.awt.*;

public class Window extends JFrame {
    WindowPlayer  panelPlayer;
    WindowObserver panelObserver;
    Window(boolean type){
        if(type) {
             panelPlayer= new WindowPlayer();
             this.add(panelPlayer);
        }
        else{
            panelObserver = new WindowObserver();
            this.add(panelObserver);
        }
        this.setTitle("Nivel1");
        this.setResizable(false);
        this.setBackground(Color.black);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }
}
