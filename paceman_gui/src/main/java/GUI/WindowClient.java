package GUI;

import javax.swing.*;
import java.awt.*;

public class WindowClient extends JPanel implements Runnable {
    static final int WINDOW_WIDTH = 470;
    static final int WINDOW_HEIGHT = 300;

    static final Dimension SCREEN_SIZE = new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT);

    Graphics graphics;

    Player player;
    Thread gameThread;

    Image image;
    Block block;

    static int[][] nlevel ;

    WindowClient(){
        player = new Player(2);
        this.setFocusable(true);
        this.setPreferredSize(SCREEN_SIZE);
        this.setBackground(Color.BLACK);
        gameThread = new Thread(this);
        gameThread.start();
        block = new Block();
        nlevel = Levels.level1;
    }
    public void run(){

    }


    public void paint(Graphics g) {
        image = createImage(getWidth(), getHeight());
        graphics = image.getGraphics();
        draw(graphics);
        g.drawImage(image, 0, 0, this);
    }


    public void draw(Graphics g) {
        this.player.draw(g);
        Toolkit.getDefaultToolkit().sync(); // I forgot to add this line of code in the video, it helps with the animation


        for (int i = 0; i < nlevel.length; i++) {
            for (int j = 0; j < nlevel[0].length; j++) {
                if (nlevel[i][j] == 1) {
                    block.draw(g, j * 20, i * 20);
                }
            }
        }

    }

    public void elementsMovement() {
        this.player.move();

    }

}
