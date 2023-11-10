package AbstractFactory;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Pinky extends Ghost{



    Pinky(Integer posX, Integer posY) {
        this.speed=20;
        this.posX=1;
        this.posY=1;
        this.name="Pinky";
        x = posX;
        y = posY;
        this.image = new ImageIcon("src/media/pinky.png").getImage();
        createGhost();
        this.isAlive=true;
    }
    @Override
    public void createMovement(Integer[][] nlevel){
        Random random = new Random();
        int randomNumber = random.nextInt(2);
        int change = random.nextInt(2);
        int other;
        if (randomNumber ==1){
            other = 0;
        }
        else{
            other = 1;
        }
        if (change == 1){
            randomNumber = randomNumber *-1;
            other = other * -1;
        }
        move(nlevel,randomNumber, other);


    }





}
