package AbstractFactory;

import javax.swing.*;
import java.awt.*;

public class Pinky extends Ghost{
    Pinky(Integer posX, Integer posY) {
        x = posX;
        y = posY;
        this.image = new ImageIcon("src/media/pinky.png").getImage();
        createGhost();
    }

    public void move(){
        x = x+20;
    }


}
