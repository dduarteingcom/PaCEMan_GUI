package AbstractFactory;

import javax.swing.*;
import java.awt.*;

public class Dot extends Resource {
    Dot(Graphics g, Integer posX, Integer posY) {
        this.image = new ImageIcon("src/media/dot.png").getImage();
        draw(g,posX,posY);
        createResource();
    }

}
