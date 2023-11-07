package GUI;

import java.awt.*;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;
import jssc.SerialPort;
import static jssc.SerialPort.MASK_RXCHAR;
import jssc.SerialPortEvent;
import jssc.SerialPortException;
import jssc.SerialPortList;


public class Player extends Rectangle {

    int speed;
    int xDirection;
    int yDirection;
    Image image;
    Arduino ard = Arduino.getInstance();


    int posX;
    int posY;
    Player(int speed){
        this.speed=20;
        this.xDirection =0;
        this.yDirection=0;
        this.image = new ImageIcon("C:\\Users\\andre\\Desktop\\Paradigmas\\PaCEMan_GUI\\paceman_gui\\src\\media\\pacman-icon.png").getImage();
        this.x=20;
        this.y=20;
        this.posX=1;
        this.posY=1;
    }
    public void arduino(){
        if (ard.msg != null){
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
            move();
        }
    }
    public void keyPressed(KeyEvent e) {


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
        move();
    }
    public void keyReleased(KeyEvent e){
        if(e.getKeyCode()==KeyEvent.VK_LEFT || e.getKeyCode()==KeyEvent.VK_RIGHT) {
                xDirection = 0;
        }
        else if(e.getKeyCode()==KeyEvent.VK_UP || e.getKeyCode()==KeyEvent.VK_DOWN) {
                yDirection = 0;


        }
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
        xDirection=0;
        yDirection=0;
    }
    public void draw(Graphics g){
        g.drawImage(image,x,y,20,20,null);
    }

    public void getPosition(){
        if(x% 20 ==0){
            posX=x/20;

        }
        if(y % 20==0){
            posY=y/20;
        }

    }
}
