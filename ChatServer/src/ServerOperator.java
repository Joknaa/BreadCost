import java.io.*;
import java.net.Socket;

public class ServerOperator extends Thread{
    private final Socket clientSocket;
    private InputStream input;
    private OutputStream output;
    private final String EXIT = "over";
    private final String LOGIN = "login";

    public ServerOperator(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    @Override
    public void run() {
        try {
            HandlingClientSocket();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void HandlingClientSocket() throws IOException, InterruptedException {
        OutputStream outputStream = clientSocket.getOutputStream();
        input = clientSocket.getInputStream();
        output = clientSocket.getOutputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(input));
        String line;
        Reply("Type something ..\n");
        while ((line = reader.readLine()) != null) {
            String[] tokens = line.split(" ");
            if (tokens != null && tokens.length > 0) {
                String cmd = tokens[0];
                if (cmd.equalsIgnoreCase(EXIT)) {
                    break;
                } else if (cmd.equalsIgnoreCase(LOGIN)) {
                    LoginClient(output, tokens);
                } else {
                    Reply("Unknown " + cmd + "\n");
                }
            }
        }
        clientSocket.close();
    }

    private void LoginClient(OutputStream output, String[] tokens) throws IOException {
        String reply;
        if (tokens.length < 3){
            Reply("You have to enter a 'login' and a 'password'");
            return;
        }
        if (tokens[1] == null)


        reply = "You want to Login ! \n";
        output.write(reply.getBytes());
    }

    private void Reply(String s) throws IOException {
        output.write(s.getBytes());
    }
}
