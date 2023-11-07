package AbstractFactory;

import GUI.Levels;

import javax.swing.*;
import java.awt.*;


public class Fruit extends Resource{
    Fruit(Graphics g, Integer posX, Integer posY) {
        this.image = new ImageIcon("src/media/fruit.png").getImage();
        draw(g,posX,posY);
        createResource();
    }

}
