package AbstractFactory;

import GUI.Pathfinder;

import javax.swing.*;
import java.util.Random;

public class Clyde extends Ghost{

    Clyde(Integer posX, Integer posY) {
        this.speed=20;
        this.posX=1;
        this.posY=1;

        x = posX;
        y = posY;
        this.image = new ImageIcon("src/media/clyde.png").getImage();
        createGhost();
    }
    @Override
    public void createMovement(Integer[][] nlevel){
        if (path == null || path.size() == 0){
            Random random = new Random();
            Integer destinyX;
            Integer destinyY;
            System.out.println("crea nuevo path");
            Pathfinder.Point start = new Pathfinder.Point(posX, posY, null);
            destinyX = random.nextInt(15)+1;
            destinyY = random.nextInt(13)+1;
            while (nlevel[destinyY][destinyX] == 4){
                destinyX = random.nextInt(15)+1;
                destinyY = random.nextInt(13)+1;
            }
            Pathfinder.Point end = new Pathfinder.Point(destinyX, destinyY, null);
            path = Pathfinder.FindPath(nlevel, start, end);
            System.out.println("Inicio" + posX + " " + posY);
            System.out.println("Destino " + destinyX + " " +destinyY);
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
            System.out.println("sigue su path");
            move(nlevel, path.getFirst().ymovement, path.getFirst().xmovement);
            path.removeFirst();

        }
    }

}
