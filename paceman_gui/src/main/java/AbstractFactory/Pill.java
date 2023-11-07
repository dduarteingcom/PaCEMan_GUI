package AbstractFactory;

import javax.swing.*;
import java.awt.*;

public class Pill extends Resource {
    Pill(Graphics g, Integer posX, Integer posY) {
        this.image = new ImageIcon("C:\\Users\\andre\\Desktop\\Paradigmas\\PaCEMan_GUI\\paceman_gui\\src\\media\\pill.png").getImage();
        draw(g,posX,posY);
        createResource();
    }
}
