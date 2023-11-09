package GUI;

import AbstractFactory.Pinky;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.Socket;
import java.util.LinkedList;
import java.util.Random;



public class WindowPlayer extends WindowClient  {
    private LinkedList<Window> observers;
    private Integer numObservers;
    WindowMenu menu = WindowMenu.getInstance();

    String playername;
    Pinky ghost;
    private int currentValueFruit;

    WindowPlayer(String playername) {

        this.currentValueFruit=0;

        this.playername = playername;
        numObservers = 0;
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {

                if(e.getKeyCode()==KeyEvent.VK_G){ //With key G is possible to create a ghost
                    Integer[] coordenates=chooseLoc();
                    cLevel[coordenates[0]][coordenates[1]]=6; //Updates de level matrix
                }

                player.keyPressed(e, cLevel);
                player.getPosition();
                checkResources();

            }
            public void keyReleased(KeyEvent e){
                player.keyReleased(e);

            }
        });

        addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                player.permission = true;
            }
            @Override
            public void focusLost(FocusEvent e) {
                player.permission = false;
            }
        });
    }
    /**
     * It's the game loop.
     */
    @Override
    public void run(){
        //game loop
        long lastTime = System.nanoTime();
        double amountOfTicks =60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        int counter = 0;
        observers = new LinkedList<>();
        while(running) {
            counter++;
            long now = System.nanoTime();
            delta += (now -lastTime)/ns;
            lastTime = now;
            if(delta >=1) {
                repaint();

                if(observers.size() > 0) {
                    updateClients();
                }

            }
            if ((menu != null)&& (menu.getGames().size() != 0)&&(counter == (2000000/menu.getGames().size()))){
                //player.arduino(cLevel); //DESCOMENTAR LUEGO!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
                try {
                    getMessageFromServer();
                } catch (IOException e) {
                    System.out.println("Desconectado del servidor");
                }
                checkResources();
                checkColissions();
                moveGhost();
                counter = 0;
            }
        }
        disconnectObservers();

    }

    /**
     * Creates and adds observers to the Linked List observers.
     */
    public void addObserver(){
        Integer number = observers.size() + 1;
        Window observer = new Window(false, playername + " Observer" + number);
        numObservers ++;
        observer.panelObserver.setId(numObservers);
        observer.panelObserver.setNumLevel(getNumlevel());
        observers.add(observer);

    }
    private void disconnectObservers(){
        for (Window observer : observers) {
            observer.panelObserver.disconnect();

        }
    }

    /**
     * Updates its clients with positions and the map status
     */
    public void updateClients() {
        for (Integer i = 0; i < observers.size(); i++) {
            observers.get(i).panelObserver.upDate(player.x, player.y, cLevel, getNumPoints());
        }
    }

    public void updateClients(Integer numLevel) {
        for (Window observer : observers) {
            observer.panelObserver.upDate(player.x, player.y, cLevel, getNumPoints(), numLevel);
        }
    }

    /**
     * Checks if PaCE has eatean a pill, fruit or dot
     */
    private void checkResources(){
        int valuePos = cLevel[player.posY][player.posX];
        if(valuePos==1||valuePos==2||valuePos==3){
            cLevel[player.posY][player.posX]=0;
            if(valuePos==1){
                Integer newScore = getNumPoints()+10;
                setToNextLevel(getToNextLevel()-10);
                setNumPoints(newScore);
            }
            else if (valuePos==2){
                Integer newScore = getNumPoints()+getCurrentValueFruit();
                setNumPoints(newScore);
            }
        }
    }

    /**
     * Decides where a resource must be located.
     * @return An array with the chosen coordinates.
     */
    private Integer[] chooseLoc(){
        Integer[] coordenates = new Integer[2];
        Random random1 = new Random();
        Random random2 = new Random();
        int randomX = random1.nextInt(15);
        int randomY =random2.nextInt(17);
        int valcellFound = cLevel[randomX][randomY];
        if(valcellFound==0 && randomX!= player.posX && randomY != player.posY){
            coordenates[0]=randomX;
            coordenates[1]=randomY;
            return coordenates;
        }
        else {
            return chooseLoc();
        }
    }

    private boolean locAvailable(Integer posX, Integer posY){
        int valcellFound = cLevel[posX][posY];
        if(valcellFound==0 && posX!= player.posX && posY != player.posY){
            return true;
        }
        else {
            return false;
        }
    }
    public int getCurrentValueFruit() {
        return currentValueFruit;
    }

    public void setCurrentValueFruit(Integer currentValueFruit) {
        this.currentValueFruit = currentValueFruit;
    }
    public void createFruit(Integer value, Integer posX, Integer posY) {
        System.out.println("Entré");
        setCurrentValueFruit(value);
        if (locAvailable(posX,posY)){
            cLevel[posX][posY]=2;
        }

    }
    public void createPill(Integer posX, Integer posY) {
        if (locAvailable(posX, posY)) {
            cLevel[posX][posY] = 3;
        }
    }
    public void nextLevel() {
        player.posX=0;
        player.posY=0;
        player.x = 20;
        player.y = 20;
        switch (getNumlevel()) {
            case 1:
                setcLevel(new Levels().level2);

                break;
            case 2:
                setcLevel(new Levels().level3);
                break;

        }
        countPoints();
        setNumLevel(getNumlevel()+1);
        updateClients(getNumlevel());


    }


    private void processMessageBack(String message){
        String command = message.split("_")[0];
        if(command.equals("ghost")){
            String ghostName = message.split("_")[1];
            System.out.println("Generate ghost, type: " + ghostName);
            Integer columna = Integer.valueOf(message.split("_")[2]);
            Integer fila = Integer.valueOf(message.split("_")[3]);
            String nameOfUser = message.split("_")[4];
            if(nameOfUser.equals(playername)){
                switch (ghostName){
                    case "Pinky":
                        createGhost(columna, fila, 'p');
                        break;
                    case "Blinky":
                        createGhost(columna, fila, 'b');
                        break;
                    case "Clyde":
                        createGhost(columna, fila, 'c');
                        break;
                    case "Inky":
                        createGhost(columna, fila, 'i');
                        break;
                }
            }


        } else if (command.equals("pill")) {
            Integer columna = Integer.valueOf((message.split("_")[1]));
            Integer fila = Integer.valueOf((message.split("_")[2]));
            System.out.println("Generate pill en " + columna + " " + fila);
            String nameOfUser = message.split("_")[3];
            if(nameOfUser.equals(playername)){
                createPill(columna,fila);
            }

        }else if (command.equals("fruit")) {
            Integer columna = Integer.valueOf((message.split("_")[2]));
            Integer fila = Integer.valueOf((message.split("_")[3]));
            Integer pointsWorth = Integer.valueOf((message.split("_")[1]));
            System.out.println("Generate fruit of " + pointsWorth + " points");
            String nameOfUser = message.split("_")[4];

            if(nameOfUser.equals(playername)){
                createFruit(pointsWorth,columna,fila);
            }

        }else if (command.equals("speed")) {
            Integer speed = Integer.valueOf((message.split("_")[1]));
            System.out.println("Change ghost speed to " + speed);
            String nameOfUser = message.split("_")[2];

        }
        else if (command.equals("addLife")) {
            this.setLastExtraLife(this.getNumPoints());
            this.setLives(this.getLives()+1);
            String nameOfUser = message.split("_")[1];
        }

        else if (command.equals("next")) {
            System.out.println(message);
            System.out.println("Jump to next level");
            Integer[] lastCoord={player.posY, player.posX};

            String nameOfUser = message.split("_")[1];
            if(nameOfUser.equals(playername)){
                nextLevel();
            }

        }
    }

    private void getMessageFromServer() throws IOException {
        Socket socket = new Socket("127.0.0.1", 12345); // Ip y puerto
        InputStream inputStream = socket.getInputStream();
        InputStreamReader reader = new InputStreamReader(inputStream);
        BufferedReader in = new BufferedReader(reader);
        sendMessageToServer(socket);
        processMessageBack(in.readLine());
    }
    private void sendMessageToServer(Socket socket) throws IOException {
        OutputStream outputStream = socket.getOutputStream();
        PrintWriter out = new PrintWriter(outputStream, true);
        out.println(craftMessageToServer()); //Enviar un mensaje al servidor
    }

    private String craftMessageToServer(){
        return this.playername + "_" + getNumPoints() + "_" + (getNumPoints() - getLastExtraLife())+ "_" + getToNextLevel() + "_" + getSpeed();

    }

}

