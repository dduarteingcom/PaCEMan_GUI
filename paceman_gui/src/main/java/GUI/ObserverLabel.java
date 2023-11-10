package GUI;

import Windows.WindowClient;

import java.awt.*;

public class ObserverLabel extends Rectangle {
    public void draw(Graphics g, Integer numObservers){
        g.setColor(Color.BLUE);
        g.setFont(new Font("Consolas",Font.PLAIN,20));
        g.drawString("OBSERVER: "+ numObservers, WindowClient.WINDOW_WIDTH-125,140);

    }
}
