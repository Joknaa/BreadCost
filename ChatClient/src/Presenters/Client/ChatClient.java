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
    private String serverName;
    private int serverPort;
    private Socket serverSocket;
    private OutputStream output;
    private InputStream input;
    private BufferedReader reader;
    private ArrayList<MessageListener> messageListener = new ArrayList<>();
    private ArrayList<Status> status = new ArrayList<>();

    public ChatClient(String server, int port) {
        this.serverName = server;
        this.serverPort = port;
    }

    public static void StartConnection(String userName) {
        ChatClient user = new ChatClient("localhost", 8818);
        user.addStatus(new Status() {
            @Override
            public void online(String login) {
                System.out.println("En ligne : " + login);
            }

            @Override
            public void offline(String logout) {
                System.out.println("Hors ligne : " + logout);
            }
        });
        user.addMsg((fromLogin, msgText) -> System.out.println("MSG : " + fromLogin + " : " + msgText + "\n"));

        if (!user.Server_Connect()) System.out.println("Pas de connexion");
        else {
            System.out.println("Connexion avec succes");
            if (user.Server_Login(userName)) {
                System.out.println("welcome ");
                user.Server_Chat("med", "Hola");
                System.out.println("welcome ");
            } else
                System.err.println("Not welcome");

            //client.logOff();
        }
    }

    public boolean Server_Connect() {
        try {
            this.serverSocket = new Socket(serverName, serverPort);
            this.output = serverSocket.getOutputStream();
            this.input = serverSocket.getInputStream();
            this.reader = new BufferedReader(new InputStreamReader(input));
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean Server_Login(String userName) {
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

    public void Server_Chat(String receiver, String msgTexte) {
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

    public void StartMessageReader() {
        Thread th = new Thread() {
            public void run() {
                ReadMessages();
            }

        };
        th.start();
    }

    public void ReadMessages() {
        try {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] tokens = StringUtils.split(line);
                if (tokens != null && tokens.length > 0) {
                    String commande = tokens[0];
                    String userName = tokens[1];
                    if (commande.equalsIgnoreCase("Online")) {
                        handleOnline(userName);
                    } else if (commande.equalsIgnoreCase("Offline")) {
                        handleOffline(userName);
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

    public void handleMessage(String[] tokenMsg) {
        String userName = tokenMsg[1];
        String msgText = tokenMsg[2];
        for (MessageListener listener : messageListener) {
            listener.onMessage(userName, msgText);
        }
    }

    public void handleOffline(String UserName) {
        for (Status listnr : status) {
            listnr.offline(UserName);
        }

    }

    public void handleOnline(String UserName) {
        for (Status listnr : status) {
            listnr.online(UserName);
        }
    }


    public void addStatus(Status listnr) {
        status.add(listnr);
    }

    public void removeStatus(Status listnr) {
        status.remove(listnr);
    }

    public void addMsg(MessageListener listnr) {
        messageListener.add(listnr);
    }

    public void removeMsg(MessageListener listnr) {
        messageListener.remove(listnr);
    }
}

