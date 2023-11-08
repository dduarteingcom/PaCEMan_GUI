package AbstractFactory;

import GUI.WindowClient;

import java.awt.*;

public class Ghost extends Rectangle {
    Image image;
    Integer posX;
    Integer posY;
    Graphics g;

    /**
     * Draws the resource on the panel
     * @param g The Graphics
     * @param posX Horizontal position on the panel.
     * @param posY Vertical position on the panel.
     */
    public void draw(Graphics g, Integer posX, Integer posY) {
        this.posX = posX;
        this.posY = posY;
        this.g = g;
        g.drawImage(image, posX, posY, WindowClient.WINDOW_HEIGHT / 15, WindowClient.WINDOW_HEIGHT / 15, null);
    }

    public void move(){

    }

    /**
     * Returns it self
     * @return The resource.
     */
    public Ghost createGhost(){
        return this;
    }

}
