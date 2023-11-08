package AbstractFactory;

import java.awt.*;

public class EnemyFactory implements GhostFactory{
    @Override
    public Ghost createGhost( Integer posX, Integer posY, Character type) {
        if (type=='p'){
            return new Pinky(posX, posY);
        }
        return null;
    }
}
