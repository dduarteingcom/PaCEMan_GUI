package GUI;

import javax.swing.*;
import java.awt.*;


public class Block extends Rectangle {
    Image image;
    Block(){
        this.image = new ImageIcon("C:\\Projects\\PaCEMan_GUI\\paceman_gui\\src\\media\\cubo par diseño 2.jpg").getImage();
    }
    public void draw(Graphics g,int posX,int posY){
        g.drawImage(image,posX,posY, WindowClient.WINDOW_HEIGHT/15, WindowClient.WINDOW_HEIGHT/15,null);
    }

}
