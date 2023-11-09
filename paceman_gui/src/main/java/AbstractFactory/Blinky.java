package AbstractFactory;

import GUI.Pathfinder;
import org.w3c.dom.Node;

import javax.swing.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.PriorityQueue;

public class Blinky extends Ghost{


    Blinky(Integer posX, Integer posY) {
        this.speed=20;
        this.posX=1;
        this.posY=1;

        x = posX;
        y = posY;
        this.image = new ImageIcon("src/media/blinky.png").getImage();
        createGhost();
    }
    @Override
    public void createMovement(Integer[][] nlevel){
        if (path == null || path.size() == 0){
            Random random = new Random();
            Integer destinyX;
            Integer destinyY;
            Pathfinder.Point start = new Pathfinder.Point(posX, posY, null);
            destinyX = random.nextInt(15)+1;
            destinyY = random.nextInt(13)+1;
            while (nlevel[destinyY][destinyX] == 4){
                destinyX = random.nextInt(15)+1;
                destinyY = random.nextInt(13)+1;
            }
            Pathfinder.Point end = new Pathfinder.Point(destinyX, destinyY, null);
            path = Pathfinder.FindPath(nlevel, start, end);
            if (path != null) {
                Pathfinder.Point current = start;
                for (Pathfinder.Point point : path) {
                    point.xmovement = point.x - current.x;
                    point.ymovement = point.y - current.y;
                    current = point;
                }
            }
            else
                System.out.println("No path found");
        }
        else{
            move(nlevel, path.getFirst().ymovement, path.getFirst().xmovement);
            path.removeFirst();

        }
        }

}
