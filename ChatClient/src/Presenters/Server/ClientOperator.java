package Presenters.Server;

import java.io.*;
import java.net.Socket;
import java.util.HashSet;
import java.util.List;

import org.apache.commons.lang3.*;

public class ClientOperator extends Thread {
    private Socket clientSocket;
    private ServerOperator server;
    private String userName;
    private OutputStream outputStream;
    private HashSet<String> groupSet = new HashSet<>();

    public ClientOperator(ServerOperator server, Socket clientSocket) {
        this.server = server;
        this.clientSocket = clientSocket;
    }

    @Override
    public void run() {
        try {
            handleClientSocket();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void handleClientSocket() throws IOException, InterruptedException {
        //InputStream input = clientSocket.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        this.outputStream = clientSocket.getOutputStream();

        String line;
        while ((line = reader.readLine()) != null) {
            String[] tokens = StringUtils.split(line);
            if (tokens != null && tokens.length > 0) {
                String commande = tokens[0];
                if ("logoff".equalsIgnoreCase(commande)) {
                    handleLogout();
                    break;
                } else if (commande.equalsIgnoreCase("Login")) {
                    HandleLogin(outputStream, tokens);
                } else if ("msg".equalsIgnoreCase(commande)) {
                    String[] tokenMsg = StringUtils.split(line, null, 3);
                    handleMessage(tokenMsg);
                } else if ("join".equalsIgnoreCase(commande)) {
                    handleJoin(tokens);
                } else if ("leave".equalsIgnoreCase(commande)) {
                    handleLeave(tokens);
                } else {
                    String msg = "Commande inconnue !" + commande + "\n";
                    outputStream.write(msg.getBytes());
                }
            }
        }
        clientSocket.close();
    }

    private void handleJoin(String[] tokens) {
        if (tokens.length > 1) {
            String grp = tokens[1];
            groupSet.add(grp);
        }
    }

    private void handleLeave(String[] tokens) {
        if (tokens.length > 1) {
            String grp = tokens[1];
            groupSet.remove(grp);
        }
    }

    //format : msg #grp message_text
    //format : msg login message_text
    private void handleMessage(String[] tokens) throws IOException {
        String sendTo = tokens[1];
        String messageText = tokens[2];

        boolean isGrp = sendTo.charAt(0) == '#';

        List<ClientOperator> workerList = server.getClientList();
        for (ClientOperator worker : workerList) {
            if (isGrp) {
                if (worker.isMemberOfGrp(sendTo)) {
                    String message = sendTo + "(" + userName + ") : " + messageText + "\n";
                    worker.send(message);
                }
            } else {
                if (sendTo.equalsIgnoreCase(worker.getUserName())) {
                    String message = userName + " : " + messageText + "\n";
                    worker.send(message);
                }
            }

        }

    }

    public boolean isMemberOfGrp(String grp) {
        return groupSet.contains(grp);
    }

    private void handleLogout() throws IOException {
        server.RemoveOperator(this);
        List<ClientOperator> workerList = server.getClientList();
        String offline = "Offline " + userName + "\n";
        for (ClientOperator worker : workerList) {
            if (!userName.equals(worker.getUserName())) {
                worker.send(offline);
            }

        }
        clientSocket.close();


    }

    private void HandleLogin(OutputStream outputStream, String[] tokens) throws IOException {
        String userName = tokens[1];
        this.userName = userName;
        String logMessage = "LOGGED IN ! \n";
        outputStream.write(logMessage.getBytes());
        System.out.println("Le client : " + userName + " est connecte !");

        //send other users current user s status
        List<ClientOperator> workerList = server.getClientList();
        String online = "Online :" + userName + "\n";
        for (ClientOperator worker : workerList) {
            if (!userName.equals(worker.getUserName())) {
                worker.send(online);
            }
        }
        //send current user other users online logins
        for (ClientOperator worker : workerList) {
            if (worker.getUserName() != null) {
                if (!userName.equals(worker.getUserName())) {
                    String online1 = "Online : " + worker.getUserName() + "\n";
                    send(online1);
                }
            }
        }
    }

    public String getUserName() {
        return userName;
    }

    private void send(String onoffline) throws IOException {
        if (userName != null) {
            outputStream.write(onoffline.getBytes());
        }
    }
}
