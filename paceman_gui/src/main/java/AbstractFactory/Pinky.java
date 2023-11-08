package AbstractFactory;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Pinky extends Ghost{

    Integer posX;
    Integer posY;

    Integer speed;
    Pinky(Integer posX, Integer posY) {
        this.speed=20;
        this.posX=1;
        this.posY=1;

        x = posX;
        y = posY;
        this.image = new ImageIcon("src/media/pinky.png").getImage();
        createGhost();
    }
    private void move(Integer[][]nlevel, Integer yDirection, Integer xDirection){
        getPosition();
        //Checking colissions
        //He's moving horizontally
        if(yDirection==0){
            //He's moving to the left
            if(xDirection==-1){
                if(nlevel[posY][posX-1]==4){
                    xDirection=0;
                }
            }
            //He's moving to the right
            else if (xDirection==1) {
                if(nlevel[posY][posX+1]==4){
                    xDirection=0;
                }
            }
        }
        //He's moving vertically
        else if(xDirection==0){
            //He is moving upward
            if(yDirection==-1){
                if(nlevel[posY-1][posX]==4){
                    yDirection=0;
                }
            }
            //He is moving downward
            else if(yDirection==1){
                if(nlevel[posY+1][posX]==4){
                    yDirection=0;
                }
            }

        }
        //Movement
        y = y +(speed*yDirection);
        x = x +(speed*xDirection);

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


    private void getPosition(){
        if(x% 20 ==0){
            posX=x/20;

        }
        if(y % 20==0){
            posY=y/20;
        }
    }



}
