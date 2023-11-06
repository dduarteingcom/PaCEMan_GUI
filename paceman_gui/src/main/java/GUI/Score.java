package GUI;

import java.awt.*;

public class Score extends Rectangle {
    public void draw(Graphics g, Integer lives, Integer points, Integer currentL){
        g.setColor(Color.white);
        g.setFont(new Font("Consolas",Font.PLAIN,10));
        g.drawString("LIVES: "+lives, WindowClient.WINDOW_WIDTH-100,20);
        g.drawString("SCORE: "+ points, WindowClient.WINDOW_WIDTH-100, 60);
        g.drawString("LEVEL: "+ currentL, WindowClient.WINDOW_WIDTH-100, 100);
    }
}
