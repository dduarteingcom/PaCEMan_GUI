package AbstractFactory;

import java.awt.*;

public interface GhostFactory {
    public Ghost createGhost(Graphics g, Integer posX, Integer posY, Character type);
}
