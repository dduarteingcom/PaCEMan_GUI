package AbstractFactory;

import javax.swing.*;
import java.awt.*;

public class Dot extends Resource {
    Dot(Graphics g, int posX, int posY) {
        this.image = new ImageIcon("C:\\Projects\\PaCEMan_GUI\\paceman_gui\\src\\media\\dot.png").getImage();
        draw(g,posX,posY);
        createResource();
    }

}
