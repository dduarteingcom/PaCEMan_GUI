package AbstractFactory;

import Movement.Pathfinder;

import java.awt.*;
import java.util.LinkedList;

public class Ghost extends Rectangle  {
    Image image;
    Integer posX;
    Integer posY;

    Integer speed;

    String name;
    LinkedList<Pathfinder.Point> path;

    Boolean isAlive;




    public void draw(Graphics g){
        g.drawImage(image,x,y,20,20,null);
    }

    public void move(Integer[][]nlevel, Integer yDirection, Integer xDirection){
        if(isAlive) {
            getPosition();
            //Checking colissions
            //He's moving horizontally
            if (yDirection == 0) {
                //He's moving to the left
                if (xDirection == -1) {
                    if (nlevel[posY][posX - 1] == 4) {
                        xDirection = 0;
                    }
                }
                //He's moving to the right
                else if (xDirection == 1) {
                    if (nlevel[posY][posX + 1] == 4) {
                        xDirection = 0;
                    }
                }
            }
            //He's moving vertically
            else if (xDirection == 0) {
                //He is moving upward
                if (yDirection == -1) {
                    if (nlevel[posY - 1][posX] == 4) {
                        yDirection = 0;
                    }
                }
                //He is moving downward
                else if (yDirection == 1) {
                    if (nlevel[posY + 1][posX] == 4) {
                        yDirection = 0;
                    }
                }

            }
            //Movement
            y = y + (speed * yDirection);
            x = x + (speed * xDirection);
        }


    }

    public void createMovement(Integer[][]nlevel){

    }

    /**
     * Returns it self
     * @return The resource.
     */
    public Ghost createGhost(){
        return this;
    }

    private void getPosition(){
        if(x% 20 ==0){
            posX=x/20;

        }
        if(y % 20==0){
            posY=y/20;
        }
    }

    public String getName() {
        return name;
    }

    public Integer getPosX() {
        return posX;
    }

    public Integer getPosY() {
        return posY;
    }

    public void setAlive(Boolean alive) {
        isAlive = alive;
    }

    public void setPosX(Integer posX) {
        this.posX = posX;
    }

    public void setPosY(Integer posY) {
        this.posY = posY;
    }
}