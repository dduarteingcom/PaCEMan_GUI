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
    public Rectangle createElement(Graphics g, Integer posX, Integer posY, Character type) {
        if(type == 'd') {
            return new Dot(g, posX, posY);
        }
        else if (type == 'f') {
            return new Fruit(g,posX,posY);
        }
        else if (type == 'b') {
            return new Block(g,posX,posY);
        }
        else if (type == 'p') {
            return new Pill(g,posX,posY);
        }
        return null;

    }


}
