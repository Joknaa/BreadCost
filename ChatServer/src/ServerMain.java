import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

public class ServerMain {
    public static void main(String[] args) {
        int port = 5000;

        try {
            ServerSocket serverSocket = new ServerSocket(port);

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Accepted client connection from " + clientSocket);

                ServerOperator operator = new ServerOperator(clientSocket);
                operator.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
