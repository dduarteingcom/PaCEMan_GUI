package GUI;

import java.awt.*;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;


public class Player extends Rectangle {
    int speed;
    int xDirection;
    int yDirection;
    Image image;


    int posX;
    int posY;
    Player(int speed){
        this.speed=speed;
        this.xDirection =0;
        this.yDirection=0;
        this.image = new ImageIcon("C:\\Projects\\PaCEMan_GUI\\paceman_gui\\src\\media\\pacman-icon.png").getImage();
        this.x=49;
        this.y=49;
        this.posX=1;
        this.posY=1;

    }
    public void keyPressed(KeyEvent e) {


        if(e.getKeyCode()==KeyEvent.VK_LEFT) {
            if(y % 49 == 0) {
                xDirection = -1;
                yDirection = 0;
            }
        }
        else if(e.getKeyCode()==KeyEvent.VK_RIGHT) {
            if(y % 49==0) {
                xDirection = 1;
                yDirection = 0;
            }
        }
        else if (e.getKeyCode()==KeyEvent.VK_UP) {
            if(x % 49==0) {
                xDirection = 0;
                yDirection = -1;
            }
        } else if (e.getKeyCode()==KeyEvent.VK_DOWN) {
            if(x % 49==0) {
                xDirection = 0;
                yDirection = 1;
            }
        }
        move();
    }
    public void move(){
        getPosition();
        //Se está moviendo horizontalmente
        if(yDirection==0){
            //Se mueve hacia la izquierda
            if(xDirection==-1){
                if(WindowPlayer.nlevel[posY][posX-1]==1){
                    xDirection=0;
                }
            }
            else if (xDirection==1) {
                if(WindowPlayer.nlevel[posY][posX+1]==1){
                    xDirection=0;
                }
            }
        }
        //Se está moviendo verticalmente
        else if(xDirection==0){
            //Se mueve hacia arriba
            if(yDirection==-1){
                if(WindowPlayer.nlevel[posY-1][posX]==1){
                    yDirection=0;
                }
            }
            //Se mueve hacia abajo
            else if(yDirection==1){
                if(WindowPlayer.nlevel[posY+1][posX]==1){
                    yDirection=0;
                }
            }

        }
        y = y +(speed*yDirection);
        x = x +(speed*xDirection);
    }
    public void draw(Graphics g){
        g.drawImage(image,x,y,49,49,null);
    }

    public void getPosition(){
        if(x% 49 ==0){
            posX=x/49;

        }
        if(y % 49==0){
            posY=y/49;
        }

    }
}
