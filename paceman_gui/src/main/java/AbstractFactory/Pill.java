package AbstractFactory;

import javax.swing.*;
import java.awt.*;

public class Pill extends Resource {
    Pill(Graphics g, int posX, int posY) {
        this.image = new ImageIcon("C:\\Projects\\PaCEMan_GUI\\paceman_gui\\src\\media\\pill.png").getImage();
        draw(g,posX,posY);
        createResource();
    }
}
