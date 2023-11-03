package Window;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;
import java.awt.image.BufferedImage;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class SquarePanel extends JPanel {
    private int x = 150; // Posición X inicial del cuadrado
    private int y = 150; // Posición Y inicial del cuadrado
    private BufferedImage image; // Imagen a mostrar en lugar del cuadrado
    private int imageWidth; // Ancho de la imagen redimensionada
    private int imageHeight; // Alto de la imagen redimensionada

    public SquarePanel(String imagePath, int width, int height) {
        try {
            image = ImageIO.read(new File(imagePath)); // Carga la imagen desde el archivo
            imageWidth = width;
            imageHeight = height;
            image = resizeImage(image, imageWidth, imageHeight); // Redimensiona la imagen
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void moveImage(int dx, int dy) {
        x += dx;
        y += dy;
        repaint(); // Volver a dibujar el panel
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (image != null) {
            g.drawImage(image, x, y, this); // Dibuja la imagen redimensionada en la posición actual (x, y)
        }
    }

    // Método para redimensionar la imagen
    private BufferedImage resizeImage(BufferedImage originalImage, int newWidth, int newHeight) {
        BufferedImage resizedImage = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = resizedImage.createGraphics();

        AffineTransform at = AffineTransform.getScaleInstance((double) newWidth / originalImage.getWidth(),
                (double) newHeight / originalImage.getHeight());
        g.drawRenderedImage(originalImage, at);

        g.dispose();

        return resizedImage;
    }
}

