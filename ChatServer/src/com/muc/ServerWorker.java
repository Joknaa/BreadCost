package com.muc;

import ch.qos.logback.classic.Logger;
import org.apache.commons.lang3.StringUtils;

import java.io.*;
import java.net.Socket;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ServerWorker extends Thread {
    private final String DBPassword = "oknaa";
    private final Socket clientSocket;
    private final Server server;
    private String login = null;
    private OutputStream outputStream;
    private HashSet<String> topicSet = new HashSet<>();
    public String sender;

    public ServerWorker(Server server, Socket clientSocket) {
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
        InputStream inputStream = clientSocket.getInputStream();
        this.outputStream = clientSocket.getOutputStream();

        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        String line;
        while ((line = reader.readLine()) != null) {
            String[] tokens = StringUtils.split(line);
            if (tokens != null && tokens.length > 0) {
                String cmd = tokens[0];
                if ("logoff".equals(cmd) || "quit".equalsIgnoreCase(cmd)) {
                    handleLogoff();
                    break;
                } else if ("login".equalsIgnoreCase(cmd)) {
                    try {
                        sender = tokens[1];
                        handleLogin(outputStream, tokens);
                        String lineJoin = tokens[1] + " #1";
                        String[] jointok = StringUtils.split(lineJoin);
                        handleJoin(jointok);
                    } catch (ClassNotFoundException | IOException | SQLException e) {
                        e.printStackTrace();
                    }
                } else if ("msg".equalsIgnoreCase(cmd)) {
                    String[] tokensMsg = StringUtils.split(line, null, 3);
                    try {
                        handleMessage(tokensMsg);
                    } catch (ClassNotFoundException | IOException | SQLException e) {
                        e.printStackTrace();
                    }
                } else if ("join".equalsIgnoreCase(cmd)) {
                    handleJoin(tokens);
                } else if ("signup".equalsIgnoreCase(cmd)) {
                    try {
                        String[] tokensSU = StringUtils.split(line, null, 4);
                        handleSignUp(tokensSU);
                    } catch (ClassNotFoundException | SQLException e) {
                        e.printStackTrace();
                    }
                } else if ("leave".equalsIgnoreCase(cmd)) {
                    handleLeave(tokens);
                } else {
                    String msg = "unknown " + cmd + "\n";
                    outputStream.write(msg.getBytes());
                }
            }
        }

        clientSocket.close();
    }

    private void handleSignUp(String[] tokens) throws SQLException, ClassNotFoundException, IOException {
        if (tokens.length == 4) {
            String logi = tokens[1];
            String pwd = tokens[2];
            String conpwd = tokens[3];
            String ss = "";
            System.out.println("CONNEXION A LA BD\n");
            Class.forName("com.mysql.cj.jdbc.Driver");
            java.sql.Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/chatapp", "root", DBPassword);
            Statement addh = conn.createStatement();
            Statement check = conn.createStatement();
            ResultSet rs = check.executeQuery("select `LOGIN` FROM `users` WHERE LOGIN='" + logi + "';");

            while (rs.next()) {
                ss = rs.getString("LOGIN");
            }
            if (!ss.equals(logi) && pwd.equals(conpwd)) {
                System.out.println("INSERTION\n");
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
                LocalDateTime now = LocalDateTime.now();
                String stadd = "INSERT INTO `users`(`ID_USER`, `LOGIN`, `PASSWORD`, `ID_GRP`, `STATUS`, `LAST_CONN`) VALUES (NULL,'" + logi + "', '" + pwd + "', 1, 0, '" + now + "')";
                addh.executeUpdate(stadd);
                System.out.println("INSERED \n");
                String msg = "signed up\n";
                outputStream.write(msg.getBytes());
            } else if (ss.equals(logi)) {
                String msg = "exist\n";
                outputStream.write(msg.getBytes());
            } else if (!pwd.equals(conpwd)) {
                String msg = "mdp\n";
                outputStream.write(msg.getBytes());
            }

        } else {
            String msg = "error\n";
            outputStream.write(msg.getBytes());
            System.out.println("Incorrect Token number \n");
        }

    }

    private void handleLeave(String[] tokens) {
        if (tokens.length > 1) {
            String topic = tokens[1];
            topicSet.remove(topic);
        }
    }

    public boolean isMemberOfTopic(String topic) {
        return topicSet.contains(topic);
    }

    private void handleJoin(String[] tokens) {
        if (tokens.length > 1) {
            System.out.println("USER ADDED TO GROUPE : " + tokens[0] + "\n");
            String topic = tokens[1];
            topicSet.add(topic);
        }
    }

    // format: "msg" "login" body...
    // format: "msg" "#topic" body...
    private void handleMessage(String[] tokens) throws IOException, ClassNotFoundException, SQLException {
        String sendTo = tokens[1];
        String body = tokens[2];
        boolean isTopic = sendTo.charAt(0) == '#';


        if (isTopic) {

            Class.forName("com.mysql.cj.jdbc.Driver");
            java.sql.Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/chatapp", "root", "oknaa");
            Statement addh = conn.createStatement();
            System.out.println("INSERTION\n");
            System.out.println("SENDER :" + sender);
            System.out.println("\n RECIEVER : " + sendTo);
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
            LocalDateTime now = LocalDateTime.now();
            String stadd = "INSERT INTO `messages`(`ID_MESSAGE`, `SENDER`, `RECEIVER`, `ID_GRP`, `MSG_TEXT`, `DATETIME`, `NAME`, `PATH`, `DATA`) VALUES (NULL,'" + login + "', '" + sendTo + "', 1,'" + body + "' ,'" + now + "', '', '', '')";
            addh.executeUpdate(stadd);

        }


        List<ServerWorker> workerList = server.getWorkerList();
        for (ServerWorker worker : workerList) {
            if (isTopic) {
                if (worker.isMemberOfTopic(sendTo)) {
                    String outMsg = "msg " + sendTo + " (" + sender + "):" + body + "\n";
                    worker.send(outMsg);
                }
            } else {
                if (sendTo.equalsIgnoreCase(worker.getLogin())) {

                    Class.forName("com.mysql.cj.jdbc.Driver");
                    java.sql.Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/chatapp", "root", "oknaa");
                    Statement addh = conn.createStatement();
                    System.out.println("INSERTION\n");
                    System.out.println("SENDER :" + sender);
                    System.out.println("\n RECIEVER : " + sendTo);
                    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
                    LocalDateTime now = LocalDateTime.now();
                    String stadd = "INSERT INTO `messages`(`ID_MESSAGE`, `ID_SENDER`, `ID_RECIEVER`, `ID_GRP`, `MSG_TEXT`, `DATETIME`, `NAME`, `PATH`, `DATA`) VALUES (NULL,'" + sendTo + "', '" + sender + "', 0,'" + body + "' ,'" + now + "', '', '', '')";
                    addh.executeUpdate(stadd);

                    String outMsg = "msg " + login + " " + body + "\n";
                    worker.send(outMsg);
                }
            }
        }
    }

    private void handleLogoff() throws IOException {
        server.removeWorker(this);
        List<ServerWorker> workerList = server.getWorkerList();

        // send other online users current user's status
        String onlineMsg = "offline " + login + "\n";
        for (ServerWorker worker : workerList) {
            if (!login.equals(worker.getLogin())) {
                worker.send(onlineMsg);
            }
        }
        clientSocket.close();
    }

    public String getLogin() {
        return login;
    }

    private void handleLogin(OutputStream outputStream, String[] tokens) throws IOException, ClassNotFoundException, SQLException {
        if (tokens.length == 3) {
            String login = tokens[1];
            String password = tokens[2];
            String pwd = "";
            Class.forName("com.mysql.cj.jdbc.Driver");
            java.sql.Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/chatapp", "root", "oknaa");
            Statement addh = conn.createStatement();
            String fil = "select PASSWORD from users where LOGIN =  '" + login + "' ;";
            ResultSet rs = addh.executeQuery(fil);

            while (rs.next()) {
                pwd = rs.getString("PASSWORD");

            }

            if ((password.equals(pwd))) {
                String msg = "ok login\n";
                outputStream.write(msg.getBytes());
                this.login = login;
                System.out.println("User logged in succesfully: " + login);

                List<ServerWorker> workerList = server.getWorkerList();

                // send current user all other online logins
                for (ServerWorker worker : workerList) {
                    if (worker.getLogin() != null) {
                        if (!login.equals(worker.getLogin())) {
                            String msg2 = "online " + worker.getLogin() + "\n";
                            send(msg2);
                        }
                    }
                }

                // send other online users current user's status
                String onlineMsg = "online " + login + "\n";
                for (ServerWorker worker : workerList) {
                    if (!login.equals(worker.getLogin())) {
                        worker.send(onlineMsg);
                    }
                }
            } else {
                String msg = "error login\n";
                outputStream.write(msg.getBytes());
                System.err.println("Login failed for " + login);
            }
        }
    }

    private void send(String msg) throws IOException {
        if (login != null) {
            try {
                outputStream.write(msg.getBytes());
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}
