package GUI;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;

//WindowMenu es clase hija de Window. Se encuentra implementada con un Singleton para asegurar que solo exista un menu principal.
//Actua de referencia para juegos y expectadores. LLeva una lista enlazada de los juegos y expectadores.
public class WindowMenu extends Window{
    LinkedList<Window> games = new LinkedList<>();

    private WindowMenu(){
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(650, 650);
        this.setResizable(false);
        // Create a JPanel with a null layout to hold components
        JPanel panel = new JPanel();
        panel.setLayout(null);

        // Load the background image
        ImageIcon backgroundImage = new ImageIcon("src/media/bg.png");
        Image img=backgroundImage.getImage();
        Image temp=img.getScaledInstance(650,650,Image.SCALE_SMOOTH);
        backgroundImage=new ImageIcon(temp);
        ImageIcon jugar = new ImageIcon("src/media/jugar.png");
        ImageIcon expectar = new ImageIcon("src/media/Expectar.png");


        // Create a JLabel and set the image as its icon
        JLabel backgroundLabel = new JLabel(backgroundImage);
        backgroundLabel.setBounds(0, 0, backgroundImage.getIconWidth(), backgroundImage.getIconHeight());
        panel.add(backgroundLabel);



        // Add other components to the panel
        JButton button = new JButton(jugar);
        JButton expect = new JButton(expectar);
        expect.setBounds(265,450,130,50);
        button.setBounds(265, 160, 130, 50);
        button.setVisible(true);
        button.addActionListener(e -> { // El usuario presiona el boton de jugar
            String playername = JOptionPane.showInputDialog(this, "Coloque su nombre", "Jugar", JOptionPane.PLAIN_MESSAGE);
            if ((playername != null) && (playername.length() > 0)){
                Window window = new Window(true, playername);
                games.add(window);

            }
        });
        expect.addActionListener(e -> {
            if (games.size() == 0){
                JOptionPane.showMessageDialog(this, "No hay juegos para expectar", "Modo expectador no disponible", JOptionPane.PLAIN_MESSAGE);
            }
            else{
                String[] playerlist = new String[games.size()];
                ImageIcon icon = new ImageIcon("src/media/expectator.png");
                Image iconimage = icon.getImage();
                Image temp2=iconimage.getScaledInstance(500,500,Image.SCALE_SMOOTH);
                icon=new ImageIcon(temp2);
                for (Integer i = 0; i < games.size(); i++){
                    playerlist[i] = games.get(i).getPlayer();
                }
                String playertobserve = (String) JOptionPane.showInputDialog(this, "Seleccione una partida para expectar", "Modo expectador", JOptionPane.PLAIN_MESSAGE, icon, playerlist, playerlist[0]);
                if ((playertobserve != null)){
                    for (Integer i = 0; i < games.size(); i++){
                        if (games.get(i).player.equals(playertobserve)){
                            games.get(i).panelPlayer.addObserver();
                        }
                    }
                }
            }

        });




        panel.add(button);
        panel.add(expect);
        // Add the panel to the frame
        this.add(panel);
        this.setVisible(true);

    }

    private static class Singleton{
        private static final WindowMenu INSTANCE = new WindowMenu();
    }

    public static WindowMenu getInstance(){ return Singleton.INSTANCE;}

    public LinkedList<Window> getGames() {
        return games;
    }
}
