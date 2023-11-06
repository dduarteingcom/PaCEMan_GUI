package AbstractFactory;

import java.awt.*;

public interface ElementsFactory  {
   public Rectangle createElement(Graphics g, Integer posX, Integer posY, Character type);
}
