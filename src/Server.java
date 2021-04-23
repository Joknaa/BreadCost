import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private Socket socket = null;
    private ServerSocket serverSocket = null;
    private DataInputStream input = null;

    public Server(int port){
        try {
            serverSocket = new ServerSocket(port);
            System.out.println("Starting Server ... \nWaiting for Client ...");
            socket = serverSocket.accept();
            System.out.println("Client Accepted !");

            input = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
            String line = "";
            while (!line.equals("Over")){
                try {
                    line = input.readUTF();
                    System.out.println(line);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("Closing Connection ...");
            input.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Server server = new Server(5000);
    }
}
