package AbstractFactory;

import java.awt.*;

public class ResourceFactory implements ElementsFactory {
    /**
     * Creates the resource depending on the type.
     * @param g The graphics.
     * @param posX Horizontal position on the panel.
     * @param posY Vertical position on the panel.
     * @param type The resource that is wanted to be created.
     * @return The resource.
     */
    public Rectangle createElement(Graphics g, Integer posX, Integer posY, String type) {
        if(type == "d") {
            return new Dot(g, posX, posY);
        }
        else if (type == "f") {
            return new Fruit(g,posX,posY);
        }
        else  {
            return new Pill(g,posX,posY);
        }
    }
}
