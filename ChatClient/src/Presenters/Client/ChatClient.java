package Presenters.Client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;

import org.apache.commons.lang3.StringUtils;

public class ChatClient {
    private static String serverName;
    private static int serverPort;
    private static Socket serverSocket;
    private static OutputStream output;
    private static InputStream input;
    private static BufferedReader reader;
    private ArrayList<MessageListener> messageListener = new ArrayList<>();

    public ChatClient(String server, int port) {
        serverName = server;
        serverPort = port;
    }

    public static void StartConnection(String userName) {
        ChatClient user = new ChatClient("localhost", 8818);

        user.addMsg(ChatClient::onMessage);

        if (!Server_Connect()) System.out.println("Pas de connexion");
        else {
            System.out.println("Connexion avec succes");
            if (Server_Login(userName)) {
                System.out.println("welcome ");
                Server_Chat("med", "Hola");
                System.out.println("welcome ");
            } else
                System.err.println("Not welcome");

            //client.logOff();
        }
    }

    public static boolean Server_Connect() {
        try {
            serverSocket = new Socket(serverName, serverPort);
            output = serverSocket.getOutputStream();
            input = serverSocket.getInputStream();
            reader = new BufferedReader(new InputStreamReader(input));
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean Server_Login(String userName) {
        try {
            String commande = "login " + userName + "\n";
            output.write(commande.getBytes());
            String resp = reader.readLine();
            System.out.println("Server : " + resp);
            if ("LOGGED IN ! ".equalsIgnoreCase(resp)) {
                StartMessageReader();
                return true;
            } else {
                return false;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    private static void onMessage(String fromLogin, String msgText) {
        System.out.println("MSG : " + fromLogin + " : " + msgText + "\n");
    }

    public static void Server_Chat(String receiver, String msgTexte) {
        receiver = "#Group1";
        String commande = "msg " + receiver + " " + msgTexte + "\n";
        try {
            output.write(commande.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void Server_Logout() throws IOException {
        String commande = "login \n";
        output.write(commande.getBytes());

    }

    public static void StartMessageReader() {
        Thread th = new Thread() {
            public void run() {
                ReadMessages();
            }

        };
        th.start();
    }

    public static void ReadMessages() {
        try {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] tokens = StringUtils.split(line);
                if (tokens != null && tokens.length > 0) {
                    String commande = tokens[0];
                    String userName = tokens[1];
                    if (commande.equalsIgnoreCase("Online")) {
                        System.out.println("Going Online");
                    } else if (commande.equalsIgnoreCase("Offline")) {
                        System.out.println("Going Offline");
                    } else if (commande.equalsIgnoreCase("Msg")) {
                        String[] tokenMsg = StringUtils.split(line, null, 3);
                        handleMessage(tokenMsg);
                    }

                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            try {
                serverSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public static void handleMessage(String[] tokenMsg) {
        String userName = tokenMsg[1];
        String msgText = tokenMsg[2];
        //for (MessageListener listener : messageListener) {
        //    listener.onMessage(userName, msgText);
        //}
    }

    public void addMsg(MessageListener listnr) {
        messageListener.add(listnr);
    }

    public void removeMsg(MessageListener listnr) {
        messageListener.remove(listnr);
    }
}

