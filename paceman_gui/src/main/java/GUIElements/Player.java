package GUIElements;

import Movement.Arduino;

import java.awt.*;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;


public class Player extends Rectangle {
    //
    private Integer speed;
    Integer xDirection;
    Integer yDirection;
    private Image image;

    public Integer posX;
    public Integer posY;
    public Boolean permission;

    Arduino ard = Arduino.getInstance();
    public Player(){
        Boolean permission = false;
        this.speed=20;
        this.xDirection =0;
        this.yDirection=0;
        this.image = new ImageIcon("src/media/pacman-icon.png").getImage();
        this.x=20;
        this.y=20;
        this.posX=1;
        this.posY=1;
    }
    public void arduino(Integer[][] nlevel){
        if (permission) {
            if (ard.msg != null && !ard.msg.equals("0")) {
                switch (ard.msg) {
                    case "1":
                        if (x % 20 == 0) {
                            xDirection = 0;
                            yDirection = -1;
                        }
                        break;
                    case "2":
                        if (x % 20 == 0) {
                            xDirection = 0;
                            yDirection = 1;
                        }
                        break;
                    case "3":
                        if (y % 20 == 0) {
                            xDirection = 1;
                            yDirection = 0;
                        }
                        break;
                    case "4":
                        if (y % 20 == 0) {
                            xDirection = -1;
                            yDirection = 0;
                        }
                        break;
                }
                move(nlevel);

            }
        }
    }

    /**
     * Indicates the new direction of PaCEman by the Key pressed
     * @param e event
     * @param nlevel current level
     */
     public void keyPressed(KeyEvent e, Integer[][] nlevel) {
        if(e.getKeyCode()==KeyEvent.VK_LEFT) {
            if(y % 20 == 0) {
                xDirection = -1;
                yDirection = 0;
            }
        }
        else if(e.getKeyCode()==KeyEvent.VK_RIGHT) {
            if(y % 20==0) {
                xDirection = 1;
                yDirection = 0;
            }
        }
        else if (e.getKeyCode()==KeyEvent.VK_UP) {
            if(x % 20==0) {
                xDirection = 0;
                yDirection = -1;
            }
        } else if (e.getKeyCode()==KeyEvent.VK_DOWN) {
            if(x % 20==0) {
                xDirection = 0;
                yDirection = 1;
            }
        }
        move(nlevel);
    }

    /**
     * Indicates when PaCEman must stop moving when the key is not pressed anymore.
     * @param e Event
     */
    public void keyReleased(KeyEvent e){
        if(e.getKeyCode()==KeyEvent.VK_LEFT || e.getKeyCode()==KeyEvent.VK_RIGHT) {
                xDirection = 0;
        }
        else if(e.getKeyCode()==KeyEvent.VK_UP || e.getKeyCode()==KeyEvent.VK_DOWN) {
                yDirection = 0;


        }
    }

    /**
     * Movement of PaCeman depending on his direction
     * @param nlevel current level
     */
    private void move(Integer[][]nlevel){
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
        //This way PaceMan just moves ones
        xDirection=0;
        yDirection=0;
    }

    /**
     * Draws PaCEman on the Panel
     * @param g The Graphics
     */
    public void draw(Graphics g){
        g.drawImage(image,x,y,20,20,null);
    }

    /**
     * Calculates the position in the matrix
     */
    public void getPosition(){
        if(x% 20 ==0){
            posX=x/20;

        }
        if(y % 20==0){
            posY=y/20;
        }
    }
    public Integer getSpeed() {
        return speed;
    }

    public void setSpeed(Integer speed) {
        this.speed = speed;
    }
}
