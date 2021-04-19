import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Client {
    private Socket socket = null;
    private DataInputStream input = null;
    private DataOutputStream output = null;

    public Client(String ipAddress, int port){
        try {
            socket = new Socket(ipAddress, port);
            System.out.println("Connected");
            input = new DataInputStream(System.in);
            output = new DataOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }

        String line = "";
        while (!line.equals("Over")){
            try {
                line = input.readLine();
                output.writeUTF(line);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try {
            input.close();
            output.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Client client = new Client("127.0.0.1", 5000);
    }

}
