/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import javax.swing.*;
import java.io.*;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author AnhTu
 */


public class ServerThread extends Thread {
    public static final String NICKNAME_EXIST = "This nickname is already login in another place! Please using another nickname";
    public static final String NICKNAME_VALID = "This nickname is OK";
    public static final String NICKNAME_INVALID = "Nickname or password is incorrect";
    public static final String SIGNUP_SUCCESS = "Sign up successful!";
    public static final String ACCOUNT_EXIST = "This nickname has been used! Please use another nickname!";


    public static Hashtable<String, ServerThread> listUser = new Hashtable<>();
    static Socket senderSocket, receiverSocket;
    static boolean isBusy = false;
    private final int BUFFER_SIZE = 1024;
    public JTextArea taServer;


    Socket socketOfServer;
    BufferedWriter bw;
    BufferedReader br;
    String clientName, clientPass, clientRoom;
    SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
    StringTokenizer tokenizer;
    /*
    Chú ý: có 3 loại socket của server:
    - 1 loại đc tạo ra khi có 1 client tới kết nối bình thường, lúc này thuộc tính socketOfServer của class này đc khởi tạo, còn 2 thuộc tính senderSocket = receiverSocket = null
    - 1 loại đc tạo ra khi client sender tạo 1 socket mới tới server, lúc này thuộc tính socketOfServer của class này cũng đc khởi tạo. nhưng thuộc tính senderSocket cũng đc khởi tạo và receiverSocket = null
    - 1 loại đc tạo ra khi client receiver tạo 1 socket mới tới server, lúc này thuộc tính socketOfServer của class này cũng đc khởi tạo. nhưng thuộc tính receiverSocket cũng đc khởi tạo và senderSocket = null
    
    do đó 2 thuộc tính senderSocket và receiverSocket phải static để với mọi đối tượng đc tạo ra 2 thằng này ko đổi
    Nếu chúng ko phải static, giả sử socket của sender tới, 1 đối tượng của lớp này tạo ra và có senderSocket = socket của thằng gửi, nhưng receiverSocket=null, nghĩa là socket của thằng nhận ko có, do đó file chả ko đc đi đâu cả
    Tương tự, bên receiver có receiverSocket !=null, do đó nó ko biết nguồn gừi là ai
    */
    String senderName, receiverName;
    UserDatabase userDB;

    public ServerThread(Socket socketOfServer) {
        this.socketOfServer = socketOfServer;
        this.bw = null;
        this.br = null;

        clientName = "";
        clientPass = "";
        clientRoom = "";

        userDB = new UserDatabase();
        userDB.connect();
    }

    public void appendMessage(String message) {
        taServer.append(message);
        taServer.setCaretPosition(taServer.getText().length() - 1);
    }

    public String recieveFromClient() {
        try {
            return br.readLine();
        } catch (IOException ex) {
            System.out.println(clientName + " is disconnected!");
        }
        return null;
    }

    public void sendToClient(String response) {
        try {
            bw.write(response);
            bw.newLine();
            bw.flush();
        } catch (IOException ex) {
            Logger.getLogger(ServerFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void sendToSpecificClient(ServerThread socketOfClient, String response) {
        try {
            BufferedWriter writer = socketOfClient.bw;
            writer.write(response);
            writer.newLine();
            writer.flush();
        } catch (IOException ex) {
            Logger.getLogger(ServerFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void sendToSpecificClient(Socket socket, String response) {
        try {
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            writer.write(response);
            writer.newLine();
            writer.flush();
        } catch (IOException ex) {
            Logger.getLogger(ServerFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void notifyToAllUsers(String message) {
        Enumeration<ServerThread> clients = listUser.elements();
        ServerThread st;
        BufferedWriter writer;

        while (clients.hasMoreElements()) {
            st = clients.nextElement();
            writer = st.bw;

            try {
                writer.write(message);
                writer.newLine();
                writer.flush();
            } catch (IOException ex) {
                Logger.getLogger(ServerFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void notifyToUsersInRoom(String message) {
        Enumeration<ServerThread> clients = listUser.elements();
        ServerThread st;
        BufferedWriter writer;

        while (clients.hasMoreElements()) {
            st = clients.nextElement();
            if (st.clientRoom.equals(this.clientRoom)) {
                writer = st.bw;

                try {
                    writer.write(message);
                    writer.newLine();
                    writer.flush();
                } catch (IOException ex) {
                    Logger.getLogger(ServerFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    public void notifyToUsersInRoom(String room, String message) {
        Enumeration<ServerThread> clients = listUser.elements();
        ServerThread st;
        BufferedWriter writer;

        while (clients.hasMoreElements()) {
            st = clients.nextElement();
            if (st.clientRoom.equals(room)) {
                writer = st.bw;

                try {
                    writer.write(message);
                    writer.newLine();
                    writer.flush();
                } catch (IOException ex) {
                    Logger.getLogger(ServerFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    public void closeServerThread() {
        try {
            br.close();
            bw.close();
            socketOfServer.close();
        } catch (IOException ex) {
            Logger.getLogger(ServerFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String getAllUsers() {
        String CMD = "";
        List<String> usersList = userDB.GetAllUsers();

        for (String username : usersList) {
            CMD = CMD.concat(username).concat("|");
        }

        return CMD;
    }
    public String getOnlineUsers() {
        StringBuffer kq = new StringBuffer();
        String temp = null;

        Enumeration<String> keys = listUser.keys();
        if (keys.hasMoreElements()) {
            String str = keys.nextElement();
            kq.append(str);
        }

        while (keys.hasMoreElements()) {
            temp = keys.nextElement();
            kq.append("|").append(temp);
        }

        return kq.toString();
    }

    public String getUsersThisRoom() {
        StringBuffer kq = new StringBuffer();
        String temp = null;
        ServerThread st;
        Enumeration<String> keys = listUser.keys();

        while (keys.hasMoreElements()) {
            temp = keys.nextElement();
            st = listUser.get(temp);
            if (st.clientRoom.equals(this.clientRoom)) kq.append("|").append(temp);
        }

        if (kq.equals("")) return "|";
        return kq.toString();
    }

    public String getUsersAtRoom(String room) {
        StringBuffer kq = new StringBuffer();
        String temp = null;
        ServerThread st;
        Enumeration<String> keys = listUser.keys();

        while (keys.hasMoreElements()) {
            temp = keys.nextElement();
            st = listUser.get(temp);
            if (st.clientRoom.equals(room)) kq.append("|").append(temp);
        }

        if (kq.equals("")) return "|";
        return kq.toString();
    }

    public void clientQuit() {
        if (clientName != null) {
            this.appendMessage("\n[" + sdf.format(new Date()) + "] Client \"" + clientName + "\" is disconnected!");
            listUser.remove(clientName);
            if (listUser.isEmpty())
                this.appendMessage("\n[" + sdf.format(new Date()) + "] Now there's no one is connecting to server\n");
            notifyToAllUsers("CMD_ONLINE_USERS|" + getOnlineUsers());
            notifyToUsersInRoom("CMD_ONLINE_THIS_ROOM" + getUsersThisRoom());
            notifyToUsersInRoom(clientName + " has quitted");
        }
    }

    public void changeUserRoom() {
        ServerThread st = listUser.get(this.clientName);
        st.clientRoom = this.clientRoom;
        listUser.put(this.clientName, st);
    }

    public void removeUserRoom() {
        ServerThread st = listUser.get(this.clientName);
        st.clientRoom = this.clientRoom;
        listUser.put(this.clientName, st);
    }

    @Override
    public void run() {
        try {

            bw = new BufferedWriter(new OutputStreamWriter(socketOfServer.getOutputStream()));
            br = new BufferedReader(new InputStreamReader(socketOfServer.getInputStream()));

            boolean isUserExist = true;
            String message, sender, receiver, fileName;
            StringBuffer str;
            String cmd, icon;
            while (true) {
                try {
                    message = recieveFromClient();
                    tokenizer = new StringTokenizer(message, "|");
                    cmd = tokenizer.nextToken();

                    switch (cmd) {
                        case "CMD_CHAT":
                            str = new StringBuffer(message);
                            str = str.delete(0, 9);
                            notifyToUsersInRoom("CMD_CHAT|" + this.clientName + "|" + str.toString());
                            break;

                        case "CMD_PRIVATECHAT":
                            String privateSender = tokenizer.nextToken();
                            String privateReceiver = tokenizer.nextToken();
                            String messageContent = message.substring(cmd.length() + privateSender.length() + privateReceiver.length() + 3);

                            ServerThread st_receiver = listUser.get(privateReceiver);

                            sendToSpecificClient(st_receiver, "CMD_PRIVATECHAT|" + privateSender + "|" + messageContent);

                            System.out.println("[ServerThread] message = " + messageContent);
                            break;

                        case "CMD_ROOM":
                            clientRoom = tokenizer.nextToken();
                            changeUserRoom();
                            notifyToAllUsers("CMD_USERS|" + getAllUsers());
                            notifyToAllUsers("CMD_ONLINE_USERS|" + getOnlineUsers());
                            notifyToUsersInRoom("CMD_ONLINE_THIS_ROOM" + getUsersThisRoom());
                            notifyToUsersInRoom(clientName + " has just entered!");
                            break;

                        case "CMD_LEAVE_ROOM":
                            String room = clientRoom;
                            clientRoom = "";
                            removeUserRoom();
                            notifyToUsersInRoom(room, "CMD_ONLINE_THIS_ROOM" + getUsersAtRoom(room));
                            notifyToUsersInRoom(room, clientName + " has just leaved this room!");

                            break;

                        case "CMD_CHECK_NAME":
                            clientName = tokenizer.nextToken();
                            clientPass = tokenizer.nextToken();
                            isUserExist = listUser.containsKey(clientName);

                            if (isUserExist) {
                                sendToClient(NICKNAME_EXIST);
                            } else {
                                int kq = userDB.checkUser(clientName, clientPass);
                                if (kq == 1) {
                                    sendToClient(NICKNAME_VALID);

                                    this.appendMessage("\n[" + sdf.format(new Date()) + "] Client \"" + clientName + "\" is connecting to server");
                                    listUser.put(clientName, this);
                                } else sendToClient(NICKNAME_INVALID);
                            }
                            break;

                        case "CMD_SIGN_UP":
                            String name = tokenizer.nextToken();
                            String pass = tokenizer.nextToken();
                            System.out.println("name, pass = " + name + " - " + pass);
                            isUserExist = listUser.containsKey(name);

                            if (isUserExist) {
                                sendToClient(NICKNAME_EXIST);
                            } else {
                                int kq = userDB.insertUser(new User(name, pass));
                                if (kq > 0) {
                                    sendToClient(SIGNUP_SUCCESS);
                                } else sendToClient(ACCOUNT_EXIST);
                            }
                            break;
/*
                        case "CMD_ONLINE_USERS":
                            sendToClient("CMD_ONLINE_USERS|" + getOnlineUsers());
                            notifyToUsersInRoom("CMD_ONLINE_THIS_ROOM" + getUsersThisRoom());
                            break;

 */

                        case "CMD_SENDFILETOSERVER":
                            sender = tokenizer.nextToken();
                            receiver = tokenizer.nextToken();
                            fileName = tokenizer.nextToken();
                            int len = Integer.valueOf(tokenizer.nextToken());

                            String path = System.getProperty("user.dir") + "\\sendfile\\" + fileName;


                            BufferedInputStream bis = new BufferedInputStream(socketOfServer.getInputStream());
                            FileOutputStream fos = new FileOutputStream(path);

                            byte[] buffer = new byte[BUFFER_SIZE];
                            int count = -1;
                            while ((count = bis.read(buffer)) > 0) {
                                fos.write(buffer, 0, count);
                            }

                            Thread.sleep(300);
                            bis.close();
                            fos.close();
                            socketOfServer.close();


                            ServerThread stSender = listUser.get(sender);


                            ServerThread stReceiver = listUser.get(receiver);

                            sendToSpecificClient(stSender, "CMD_FILEAVAILABLE|" + fileName + "|" + receiver + "|" + sender);
                            sendToSpecificClient(stReceiver, "CMD_FILEAVAILABLE|" + fileName + "|" + sender + "|" + sender);

                            isBusy = false;
                            break;

                        case "CMD_DOWNLOADFILE":
                            fileName = tokenizer.nextToken();
                            path = System.getProperty("user.dir") + "\\sendfile\\" + fileName;
                            FileInputStream fis = new FileInputStream(path);
                            BufferedOutputStream bos = new BufferedOutputStream(socketOfServer.getOutputStream());

                            byte[] buffer2 = new byte[BUFFER_SIZE];
                            int count2 = 0;

                            while ((count2 = fis.read(buffer2)) > 0) {
                                bos.write(buffer2, 0, count2);
                            }

                            bos.close();
                            fis.close();
                            socketOfServer.close();

                            break;

                        case "CMD_ICON":
                            icon = tokenizer.nextToken();
                            notifyToUsersInRoom("CMD_ICON|" + icon + "|" + this.clientName);
                            break;

                        default:
                            notifyToAllUsers(message);
                            break;
                    }

                } catch (Exception e) {
                    //todo: Dont disconnect the user when sending a private message to an offline user
                    clientQuit();
                    break;
                }
            }
        } catch (IOException ex) {
            clientQuit();
            Logger.getLogger(ServerThread.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}