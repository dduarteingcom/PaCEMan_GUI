package AbstractFactory;

import GUI.WindowClient;

import java.awt.*;

public class Ghost extends Rectangle {
    Image image;





    public void draw(Graphics g){
        g.drawImage(image,x,y,20,20,null);
    }

    private void move(Integer[][]nlevel, Integer yDirection, Integer xDirection){

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

}
