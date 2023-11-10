package AbstractFactory;

import javax.swing.*;
import java.awt.*;


public class Block extends Resource {

    Block(Graphics g, Integer posX, Integer posY, String level) {
        this.image = new ImageIcon(choosePath(level)).getImage();
        draw(g,posX,posY);
        createResource();
    }
    private String choosePath(String level){
        String path;
        if(level=="b1"){
            path = "src/media/blockN1.png";
        } else if (level=="b2") {
            path = "src/media/blockN2.png";
        }
        else {
            path = "src/media/blockN3.png";
        }
        return path;
    }


}

