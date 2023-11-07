package AbstractFactory;

import javax.swing.*;
import java.awt.*;

public class Dot extends Resource {
    Dot(Graphics g, Integer posX, Integer posY) {
        this.image = new ImageIcon("C:\\Users\\andre\\Desktop\\Paradigmas\\PaCEMan_GUI\\paceman_gui\\src\\media\\dot.png").getImage();
        draw(g,posX,posY);
        createResource();
    }

}
