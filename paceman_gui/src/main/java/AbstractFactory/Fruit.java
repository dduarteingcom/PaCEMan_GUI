package AbstractFactory;

import GUI.Levels;

import javax.swing.*;
import java.awt.*;


public class Fruit extends Resource{
    Fruit(Graphics g, int posX, int posY) {
        this.image = new ImageIcon("C:\\Projects\\PaCEMan_GUI\\paceman_gui\\src\\media\\fruit.png").getImage();
        draw(g,posX,posY);
        createResource();
    }

}
