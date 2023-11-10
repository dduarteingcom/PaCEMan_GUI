package AbstractFactory;

import Windows.WindowClient;

import java.awt.*;

public class Resource extends Rectangle {
    Image image;

    /**
     * Draws the resource on the panel
     * @param g The Graphics
     * @param posX Horizontal position on the panel.
     * @param posY Vertical position on the panel.
     */
    public void draw(Graphics g, Integer posX, Integer posY) {
        g.drawImage(image, posX, posY, WindowClient.WINDOW_HEIGHT / 15, WindowClient.WINDOW_HEIGHT / 15, null);
    }

    /**
     * Returns it self
     * @return The resource.
     */
    public Resource createResource(){
        return this;
    }


}
