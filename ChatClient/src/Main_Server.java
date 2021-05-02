import Presenters.Server.ServerOperator;

public class Main_Server {
    public static void main(String[] args) {
        int port = 8818;
        ServerOperator server = new ServerOperator(port);
        server.start();
    }
}
