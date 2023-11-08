import java.io.*;
import java.net.*;
import java.util.Scanner;

public class JavaClient {
    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(System.in);
        System.out.println("0 para terminar, 1 para leer mensaje del server");
        if(in.nextInt() == 1){
            while(true){
                getMessageFromServer();
                System.out.println("0 para terminar, 1 para leer mensaje del server");
                if(in.nextInt() == 0){
                    break;
                }
            }
        }

    }

    public static void getMessageFromServer() throws IOException {
        Socket socket = new Socket("127.0.0.1", 12345); // Ip y puerto
        InputStream inputStream = socket.getInputStream();
        InputStreamReader reader = new InputStreamReader(inputStream);
        BufferedReader in = new BufferedReader(reader);
        sendMessageToServer(socket, "Andres_51_24_12_48");
        System.out.println("Message from C Server: " + in.readLine());
    }
    public static void sendMessageToServer(Socket socket, String message) throws IOException {
        OutputStream outputStream = socket.getOutputStream();
        PrintWriter out = new PrintWriter(outputStream, true);

        out.println(message); //Enviar un mensaje al servidor
    }
}