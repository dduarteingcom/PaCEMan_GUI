package AbstractFactory;

import GUI.WindowClient;

import javax.swing.*;
import java.awt.*;
import GUI.WindowClient;


public class Block extends Resource {

    Block(Graphics g, Integer posX, Integer posY) {
        this.image = new ImageIcon("C:\\Projects\\PaCEMan_GUI\\paceman_gui\\src\\media\\blockN1.png").getImage();
        draw(g,posX,posY);
        createResource();
    }


}

