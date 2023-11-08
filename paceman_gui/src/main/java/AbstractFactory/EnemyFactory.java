package AbstractFactory;

import java.awt.*;

public class EnemyFactory implements GhostFactory{
    @Override
    public Ghost createGhost(Graphics g, Integer posX, Integer posY, Character type) {
        if (type=='p'){
            return new Pinky(g, posX, posY);
        }
        return null;
    }
}
