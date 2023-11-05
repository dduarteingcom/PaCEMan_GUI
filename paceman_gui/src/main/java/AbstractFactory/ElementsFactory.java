package AbstractFactory;

import java.awt.*;

public interface ElementsFactory  {
   public Rectangle createElement(Graphics g, int posX, int posY, char type);
}
