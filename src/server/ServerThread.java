package server;

import EncDec.Blowfish;
import EncDec.RSA;

import javax.crypto.SecretKey;
import javax.swing.*;
import java.io.*;
import java.net.Socket;
import java.net.SocketException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ServerThread extends Thread {
    public static final String NICKNAME_LOGGED_IN = "This nickname is already login in another place! Please using another nickname";
    public static final String NICKNAME_VALID = "This nickname is OK";
    public static final String NICKNAME_INVALID = "Nickname or password is incorrect";
    public static final String SIGNUP_SUCCESS = "Sign up successful!";
    public static final String ACCOUNT_EXIST = "This nickname has been used! Please use another nickname!";
    private static final HashMap<String, SecretKey> BlowfishKeys = new HashMap<String, SecretKey>();
    public static Hashtable<String, ServerThread> listUser = new Hashtable<>();
    static boolean isBusy = false;
    private final int BUFFER_SIZE = 1024;
    public String base64publicRSA;
    public String encryptedKeyBlowfish;
    public JTextArea taServer;
    Socket socketOfServer;
    BufferedWriter bw;
    BufferedReader br;
    String clientName, clientPass, clientRoom;
    StringTokenizer tokenizer;
    UserDatabase userDB;
    String senderr;
    int UserCount = 0;
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

            this.appendMessage("\nClient \"" + clientName + "\" is disconnected!");
            listUser.remove(clientName);
            notifyToAllUsers("CMD_ONLINE_USERS|" + getOnlineUsers());
            notifyToUsersInRoom("CMD_ONLINE_THIS_ROOM" + getUsersThisRoom());
            notifyToUsersInRoom(clientName + " has quited");
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
                    message = br.readLine();
                    tokenizer = new StringTokenizer(message, "|");
                    cmd = tokenizer.nextToken();

                    switch (cmd) {
                        case "CMD_CHAT":
                            str = new StringBuffer(message);
                            str = str.delete(0, 9);
                            notifyToUsersInRoom("CMD_CHAT|" + this.clientName + "|" + str.toString());
                            try {
                                userDB.StoreMessage(clientName, "GRP1", 1, str.toString(), "");
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            break;

                        case "CMD_PUBLICRSAKEY":
                            base64publicRSA = tokenizer.nextToken();
                            senderr = tokenizer.nextToken();
                            System.out.println("(SERVER) RECUPERAT PUBLIC KEY : " + base64publicRSA);
                            //BLOWFISH ENCRYPTION
                            Blowfish encryption = new Blowfish();
                            SecretKey blowfishKey = encryption.getBlowfishKey();
                            System.out.println("(SERVER) BLOWFISH KEY CREATION : " + blowfishKey);
                            BlowfishKeys.put(senderr, blowfishKey);
                            System.out.println("(SERVER) SENDERRR : " + senderr + " KEY : " + BlowfishKeys.get(senderr));
                            //ENCRYPT KEY RSA
                            String encoded64Blowfish = Base64.getEncoder().encodeToString(blowfishKey.getEncoded());
                            encryptedKeyBlowfish = Base64.getEncoder().encodeToString(RSA.encryptRSA(encoded64Blowfish, base64publicRSA));
                            System.out.println("(SERVER) ENCRYPT BLOWFISH KEY WITH RSA PUBLIC KEY ");
                            sendToClient("CMD_BLOWFISHKEYTOCLIENT|" + encryptedKeyBlowfish);
                            System.out.println("(SERVER) SEND TO CLIENT ENCRYPTED BLOWFISH KEY");
                            break;

                        case "CMD_PRIVATECHAT":
                            String privateSender = tokenizer.nextToken();
                            String privateReceiver = tokenizer.nextToken();
                            String messageContent = message.substring(cmd.length() + privateSender.length() + privateReceiver.length() + 3);


                            boolean x = listUser.containsKey(privateReceiver);
                            System.out.println("(SERVER) GET MSG FROM SENDER : " + messageContent);
                            ServerThread st_receiver = listUser.get(privateReceiver);


                            System.out.println("****************PRIVATE RECEIVER - PRIVATE SENDER****************");
                            System.out.println("(SERVER) BLOWFISH RECEIVER : " + privateReceiver + " KEY : " + BlowfishKeys.get(privateReceiver));
                            System.out.println("(SERVER) BLOWFISH SENDER : " + privateSender + " KEY : " + BlowfishKeys.get(privateSender));
                            System.out.println("********************************");

                            SecretKey keyy = BlowfishKeys.get(privateSender);
                            String decreptedSenderMsg = Blowfish.decryption(messageContent, keyy);
                            System.out.println("(SERVER)MSG DECRYPTED : " + decreptedSenderMsg);

                            try {
                                userDB.StoreMessage(privateSender, privateReceiver, 0, decreptedSenderMsg, "");
                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                            SecretKey keyy2 = BlowfishKeys.get(privateReceiver);
                            String encreptedMsgBlowfish = Blowfish.encryption(decreptedSenderMsg, keyy2);
                            System.out.println("(SERVER) LAST ENCRYPTION : " + keyy2);
                            System.out.println("(SERVER) SEND MSG TO RECEIVER : " + privateReceiver + " FROM : " + privateSender);
                            System.out.println("CMD_PRIVATECHAT|" + privateSender + "|" + encreptedMsgBlowfish);
                            if (st_receiver != null){
                                sendToSpecificClient(st_receiver, "CMD_PRIVATECHAT|" + privateSender + "|" + encreptedMsgBlowfish);
                            }
                            System.out.println("(SERVER) MSG SENT");
                            System.out.println("[ServerThread] message = " + encreptedMsgBlowfish);
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
                                sendToClient(NICKNAME_LOGGED_IN);
                            } else {
                                int kq = userDB.checkUser(clientName, clientPass);
                                if (kq == 1) {
                                    sendToClient(NICKNAME_VALID);
                                    this.appendMessage("\nClient \"" + clientName + "\" is connecting to server");
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
                                sendToClient(NICKNAME_LOGGED_IN);
                            } else {
                                int kq = userDB.insertUser(new UserModel(name, pass));
                                if (kq > 0) {
                                    sendToClient(SIGNUP_SUCCESS);
                                } else sendToClient(ACCOUNT_EXIST);
                            }
                            break;

                        case "CMD_SENDFILETOSERVER":
                            sender = tokenizer.nextToken();
                            receiver = tokenizer.nextToken();
                            fileName = tokenizer.nextToken();
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
                            sendToSpecificClient(stSender, "CMD_FILEAVAILABLE|" + fileName + "|" + receiver + "|" + sender);

                            if (receiver.equals("Group Chat")) {
                                for (String user : listUser.keySet()) {
                                    if (!user.equals(sender))
                                        sendToSpecificClient(listUser.get(user), "CMD_FILEAVAILABLE|" + fileName + "|" + sender + "|" + sender);
                                }
                            } else {
                                ServerThread stReceiver = listUser.get(receiver);
                                sendToSpecificClient(stReceiver, "CMD_FILEAVAILABLE|" + fileName + "|" + sender + "|" + sender);
                            }

                            try {
                                userDB.StoreMessage(sender, receiver, 0, "", fileName);
                            } catch (Exception e) {
                                System.out.println("ServerT:400");
                            }

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
                } catch (SocketException se) {
                    clientQuit();
                    break;
                } catch (NoSuchElementException x) {
                    System.out.println("Blowfish");
                    break;
                } catch (NullPointerException ex) {
                    clientQuit();
                    System.out.println(ex.toString());
                    break;
                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println("(SERVER) USER DISCONNECTED");
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