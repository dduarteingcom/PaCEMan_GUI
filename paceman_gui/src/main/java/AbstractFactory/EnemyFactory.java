package AbstractFactory;

import java.awt.*;

public class EnemyFactory implements GhostFactory{
    @Override
    public Ghost createGhost( Integer posX, Integer posY, Character type) {
        if (type=='p'){
            return new Pinky(posX, posY);
        } else if (type == 'b') {
            return new Blinky(posX, posY);
        }
        else if (type == 'c')
            return new Clyde(posX, posY);
        else if (type == 'i') {
            return new Inky(posX, posY);

        }
        return null;
    }
}
