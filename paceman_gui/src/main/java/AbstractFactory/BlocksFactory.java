package AbstractFactory;

import java.awt.*;

public class BlocksFactory implements ElementsFactory {
    public Rectangle createElement(Graphics g, Integer posX, Integer posY,String type){
        return new Block(g,posX,posY,type);
    }
}
