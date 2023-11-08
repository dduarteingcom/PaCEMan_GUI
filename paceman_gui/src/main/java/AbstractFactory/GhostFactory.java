package AbstractFactory;

import java.awt.*;

public interface GhostFactory {
    public Ghost createGhost( Integer posX, Integer posY, Character type);
}
