package GUI;

import javax.swing.*;
import java.awt.*;

public class WindowClient extends JPanel implements Runnable {
    static final int WINDOW_WIDTH = 1151;
    static final int WINDOW_HEIGHT = 785;

    static final Dimension SCREEN_SIZE = new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT);

    Graphics graphics;

    Player player;
    Thread gameThread;

    Image image;
    Block block;

    static int[][] nlevel ;

    WindowClient(){
        player = new Player(7);
        this.setFocusable(true);
        this.setPreferredSize(SCREEN_SIZE);
        this.setBackground(Color.BLACK);
        gameThread = new Thread(this);
        gameThread.start();
        block = new Block();
        nlevel = Levels.level1;
    }
    public void run(){
        //game loop
        long lastTime = System.nanoTime();
        double amountOfTicks =60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        while(true) {
            long now = System.nanoTime();
            delta += (now -lastTime)/ns;
            lastTime = now;
            if(delta >=1) {
                repaint();
                elementsMovement();
                delta--;
            }
        }
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
                    block.draw(g, j * 49, i * 49);
                }
            }
        }

    }

    public void elementsMovement() {
        this.player.move();

    }

}
