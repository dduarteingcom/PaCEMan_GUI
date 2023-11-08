package AbstractFactory;

import GUI.Levels;

import javax.swing.*;
import java.awt.*;


public class Fruit extends Resource{

    private Integer value;
    Fruit(Graphics g, Integer posX, Integer posY) {
        this.image = new ImageIcon("src/media/fruit.png").getImage();
        draw(g,posX,posY);
        createResource();
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }
}
