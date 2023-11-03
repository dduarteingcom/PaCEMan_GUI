package Window;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.awt.image.BufferedImage;

public class GameWindow {
    public static void main(String[] args) {
        JFrame mainWindow = new JFrame("Mover Imagen");
        mainWindow.setSize(400, 400);
        mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainWindow.setLocationRelativeTo(null); // Centrar la ventana en la pantalla

        // Panel personalizado para dibujar y mover la imagen
        JPanel squarePanel = new SquarePanel("C:\\Projects\\PaCEMan_GUI\\paceman_gui\\src\\media\\pacman-icon.png",50,50);
        mainWindow.add(squarePanel);

        // Hacer que el panel sea enfocable para que pueda recibir eventos de teclado
        squarePanel.setFocusable(true);
        squarePanel.requestFocus();

        // Agregar un oyente de eventos de teclado al panel
        squarePanel.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                int keyCode = e.getKeyCode();
                int step = 10; // Tamaño del paso para mover la imagen

                if (keyCode == KeyEvent.VK_LEFT) {
                    ((SquarePanel) squarePanel).moveImage(-step, 0); // Mover a la izquierda
                } else if (keyCode == KeyEvent.VK_RIGHT) {
                    ((SquarePanel) squarePanel).moveImage(step, 0); // Mover a la derecha
                } else if (keyCode == KeyEvent.VK_UP) {
                    ((SquarePanel) squarePanel).moveImage(0, -step); // Mover hacia arriba
                } else if (keyCode == KeyEvent.VK_DOWN) {
                    ((SquarePanel) squarePanel).moveImage(0, step); // Mover hacia abajo
                }
            }
        });

        mainWindow.setVisible(true);
    }

    // Clase para el panel personalizado que dibuja y mueve la imagen

    }


