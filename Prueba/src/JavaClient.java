import java.io.*;
import java.net.*;

public class JavaClient {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("127.0.0.1", 12345); // Ip y puerto
            InputStream inputStream = socket.getInputStream();
            InputStreamReader reader = new InputStreamReader(inputStream);
            OutputStream outputStream = socket.getOutputStream();
            PrintWriter out = new PrintWriter(outputStream, true);

            out.println("Hello_from_Java_Client!"); //Enviar un mensaje al servidor

           BufferedReader in = new BufferedReader(reader);

           String message = in.readLine(); //Recibir un mensaje del servidor
           System.out.println("Message from C Server: " + message);

            socket.close(); //Cerrar el socket (acabar la conexion)
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}