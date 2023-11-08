package AbstractFactory;

import javax.swing.*;
import java.awt.*;

public class Pill extends Resource {
    Pill(Graphics g, Integer posX, Integer posY) {
        this.image = new ImageIcon("src/media/pill.png").getImage();
        draw(g,posX,posY);
        createResource();
    }
}
