package AbstractFactory;

import javax.swing.*;
import java.awt.*;

public class Pinky extends Ghost{
    Pinky(Graphics g, Integer posX, Integer posY) {
        this.image = new ImageIcon("src/media/pinky.png").getImage();
        draw(g,posX,posY);
        createGhost();
    }
    @Override
    public void move(){
        draw(g, posX+20, posY);
    }

}
