package controller;

import EncDec.Blowfish;
import EncDec.RSA;
import view.*;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.swing.*;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.StyledDocument;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.*;
import java.net.Socket;
import java.security.NoSuchAlgorithmException;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClientController extends JFrame implements Runnable {
    public static final String NICKNAME_LOGGED_IN = "This nickname is already login in another place! Please using another nickname";
    public static final String NICKNAME_INVALID = "Nickname or password is incorrect";
    public static final String ACCOUNT_EXIST = "This nickname has been used! Please use another nickname!";
    public static final String PASSWORD_MISMATCH = "Passwords don't match!";
    public static final String PASSWORD_INSECURE = """
                                                Password Invalid !!
                                                
                                                Password must contain at least one digit [0-9].
                                                Password must contain at least one lowercase Latin character [a-z].
                                                Password must contain at least one uppercase Latin character [A-Z].
                                                Password must contain at least one special character like ! @ # & ( ).
                                                Password must contain a length of at least 8 characters and a maximum of 20 characters.
                                                """;
    public static final String RELOAD_OLD_GROUP_CHAT = "$charge_old_msgs$";
    private static String Base64Private;
    private static SecretKey originalBlowfishKey;
    private static String decryptedString;
    List<String> AllUsersList = new ArrayList<>();
    List<String> OnlineUsersList = new ArrayList<>();
    HashMap<String, String> Base64Public = new HashMap<>();
    Hashtable<String, PrivateChat> listReceiver;
    String serverHost;
    String name;
    String room = "Room1";
    Socket socketOfClient;
    BufferedWriter bw;
    BufferedReader br;
    JPanel mainPanel;
    LoginPanel loginPanel;
    SignUpPanel signUpPanel;
    ChatLab chatLabPanel;
    ClientPanel clientPanel = new ClientPanel();
    Thread clientThread;
    boolean isRunning;
    StringTokenizer tokenizer;
    boolean isConnectToServer;


    public ClientController(String name) {
        this.name = name;
        socketOfClient = null;
        bw = null;
        br = null;
        isRunning = true;
        isConnectToServer = false;
        listReceiver = new Hashtable<>();

        SetupPanels();
        SetupSignupEvents();
        SetupLoginEvents();
        SetupChatLabEvents();

        chatLabPanel.AddToConversationsDocList("Group Chat", new DefaultStyledDocument());
        chatLabPanel.appendMessage_ConversationsList("Group Chat", "/Resources/ChatApp/group_chat_45px.png", this);
    }

    private void SetupPanels() {
        mainPanel = new JPanel();
        loginPanel = new LoginPanel();
        signUpPanel = new SignUpPanel();
        chatLabPanel = new ChatLab();

        chatLabPanel.setVisible(false);
        signUpPanel.setVisible(false);
        loginPanel.setVisible(true);

        mainPanel.add(chatLabPanel);
        mainPanel.add(signUpPanel);
        mainPanel.add(loginPanel);

        add(mainPanel);
        setResizable(true);
        setSize(new Dimension(900, 540));
        mainPanel.setBackground(new java.awt.Color(74, 78, 105));
        setLocation(400, 100);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("BreadCost");
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            Logger.getLogger(ClientController.class.getName()).log(Level.SEVERE, null, ex);
        }
        ClientController client = new ClientController(null);
        client.setVisible(true);
    }

    public static String getPrivateKeyRSA() {
        return Base64Private;
    }

    public static SecretKey getBlowfishKey() {
        return originalBlowfishKey;
    }

    private void SetupSignupEvents() {
        signUpPanel.getLbBack_signup().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent me) {
                signUpPanel.setVisible(false);
                loginPanel.setVisible(true);
                chatLabPanel.setVisible(false);
            }
        });
        signUpPanel.getBtSignUp().addActionListener(ae -> btSignUpEvent());
    }
    private void SetupLoginEvents() {
        loginPanel.getTfNickname().addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent ke) {
                if (ke.getKeyCode() == KeyEvent.VK_ENTER) SetupLoginButtonEvent();
            }

        });
        loginPanel.getTfPass().addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent ke) {
                if (ke.getKeyCode() == KeyEvent.VK_ENTER) SetupLoginButtonEvent();
            }

        });
        loginPanel.getBtOK().addActionListener(ae -> SetupLoginButtonEvent());
        loginPanel.GetSignUpButton().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent me) {
                signUpPanel.setVisible(true);
                loginPanel.setVisible(false);
                chatLabPanel.setVisible(false);
            }
        });

    }
    private void SetupChatLabEvents() {
        chatLabPanel.getBtSend().addActionListener(ae -> btSendEvent());
        chatLabPanel.getTaInput().addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent ke) {
                if (ke.getKeyCode() == KeyEvent.VK_ENTER) {
                    btSendEvent();
                    btClearEvent();
                }
            }
        });

        clientPanel.getLbLike().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent me) {
                sendToServer("CMD_ICON|LIKE");
            }
        });
        clientPanel.getLbDislike().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent me) {
                sendToServer("CMD_ICON|DISLIKE");
            }
        });
        clientPanel.getLbPacMan().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent me) {
                sendToServer("CMD_ICON|PAC_MAN");
            }
        });
        clientPanel.getLbCry().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent me) {
                sendToServer("CMD_ICON|CRY");
            }
        });
        clientPanel.getLbGrin().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent me) {
                sendToServer("CMD_ICON|GRIN");
            }
        });
        clientPanel.getLbSmile().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent me) {
                sendToServer("CMD_ICON|SMILE");
            }
        });

        HashMap<String, JLabel> UsernamesJLabelsList = chatLabPanel.GetUsernamesJLabelsList();
        for (String userName : UsernamesJLabelsList.keySet()) {
            UsernamesJLabelsList.get(userName).addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent me) {
                    openPrivateChatInsideRoom(userName);
                }
            });
        }
    }

    public void openPrivateChatInsideRoom(String clickedUserName) {
        if (clickedUserName.equals(ClientController.this.name)) {
            JOptionPane.showMessageDialog(ClientController.this, "Sending messages to yourself is not supported yet. \nLook for it in the next releases !", "Info", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        chatLabPanel.EnableInput();
        chatLabPanel.SetRoomName(clickedUserName);

        HashMap<String, StyledDocument> ConversationsDocList = chatLabPanel.GetConversationsDocList();
        if (ConversationsDocList.containsKey(clickedUserName)) {
            chatLabPanel.SetCurrentConversationDoc(chatLabPanel.GetConversationsDoc(clickedUserName));
            return;
        }

        StyledDocument document = new DefaultStyledDocument();
        chatLabPanel.AddToConversationsDocList(clickedUserName, document);
        chatLabPanel.SetCurrentConversationDoc(chatLabPanel.GetConversationsDoc(clickedUserName));
        LoadOldPrivateMessages(name, clickedUserName, 0);
        chatLabPanel.appendMessage_ConversationsList(clickedUserName, "/Resources/ChatApp/direct_chat_45px.png", this);
    }

    private void LoadOldPrivateMessages(String sender, String receiver, int idGroup) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            java.sql.Connection conn1 = DriverManager.getConnection("jdbc:mysql://localhost:3306/chat_db", "root", "oknaa");
            Statement st1 = conn1.createStatement();
            String fil1 = "select `MSG_TEXT`,`ID_SENDER`, `ID_RECIEVER`,`ID_MESSAGE`, `PATH` from chat_db.messages " +
                    "where ((ID_SENDER = '" + sender + "' AND ID_RECIEVER='" + receiver + "') OR (ID_SENDER = '" + receiver + "' AND ID_RECIEVER='" + sender + "')) " +
                    "AND `ID_GRP`=" + idGroup + " ORDER BY `DATETIME`";
            ResultSet rs1 = st1.executeQuery(fil1);


            while (rs1.next()) {
                String msgtxt = rs1.getString("MSG_TEXT");
                String senderr = rs1.getString("ID_SENDER");
                String reciever = rs1.getString("ID_RECIEVER");
                String path = rs1.getString("PATH");

                if (!path.equals("")) {
                    if (reciever.equals(sender)) {
                        chatLabPanel.insertButton(senderr, path);
                    } else {
                        chatLabPanel.insertButton(senderr, path);
                    }
                } else if (!msgtxt.equals("")) {
                    if (reciever.equals(sender)) {
                        chatLabPanel.appendMessage_Received(senderr + ": ", msgtxt, new Color(33, 72, 127), new Color(0, 0, 0));
                    } else {
                        chatLabPanel.appendMessage_Sent(senderr + ": ", msgtxt, new Color(33, 72, 127), new Color(0, 0, 0));
                    }
                }
            }
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }

    private void SetupEncryptionKeys() {
        try {
            RSA keyPairGenerator = new RSA();
            Base64Public.put(this.name, Base64.getEncoder().encodeToString(keyPairGenerator.getPublicKey().getEncoded()));
            Base64Private = Base64.getEncoder().encodeToString(keyPairGenerator.getPrivateKey().getEncoded());
            System.out.println("(CONSTRUCTOR)PUBLIC AND PRIVATE KEYS CREATED, PUBLIC KEY : " + Base64Public.get(name));
            sendToServer("CMD_PUBLICRSAKEY|" + Base64Public.get(name) + "|" + name);
            System.out.println("(CONSTRUCTOR)PUBLIC AND PRIVATE KEYS SENT \n");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    private void SetupLoginButtonEvent() {
        //String hostname = loginPanel.getTfHost().getText().trim();
        String hostname = "localhost";
        String username = loginPanel.getTfNickname().getText().trim();
        String password = loginPanel.getTfPass().getText().trim();

        this.serverHost = hostname;
        this.name = username;

        if (hostname.equals("") || username.equals("") || password.equals("")) {
            JOptionPane.showMessageDialog(this, "Please fill up all fields", "Notice!", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (!isConnectToServer) {
            isConnectToServer = true;
            this.connectToServer(hostname);
        }
        this.sendToServer("CMD_CHECK_NAME|" + this.name + "|" + password);


        String response = this.recieveFromServer();
        if (response != null) {
            if (response.equals(NICKNAME_LOGGED_IN) || response.equals(NICKNAME_INVALID)) {
                JOptionPane.showMessageDialog(this, response, "Error", JOptionPane.ERROR_MESSAGE);

            } else {
                loginPanel.setVisible(false);
                chatLabPanel.setVisible(true);
                chatLabPanel.setUserName(name);
                this.setTitle("\"" + name + "\"");
                SetupEncryptionKeys();
                clientThread = new Thread(this);
                clientThread.start();
                this.sendToServer("CMD_ROOM|" + this.room);
            }
        }
    }

    private void btSignUpEvent() {
        String password = this.signUpPanel.GetPassword().getText();
        String passwordRepeat = this.signUpPanel.GetPasswrdRepeat().getText();
        if (!password.equals(passwordRepeat)) {
            JOptionPane.showMessageDialog(this, PASSWORD_MISMATCH, "Error", JOptionPane.ERROR_MESSAGE);
        } if(!PasswordValidator.isValid(password)){

            JOptionPane.showMessageDialog(this, PASSWORD_INSECURE, "Error", JOptionPane.ERROR_MESSAGE);

        }else {
            String nickname = signUpPanel.getTfID().getText().trim();
            //String hostName = signUpPanel.getTfHost().getText().trim();
            String hostName = "localhost";
            if (hostName.equals("") || nickname.equals("") || password.equals("") || passwordRepeat.equals("")) {
                JOptionPane.showMessageDialog(this, "Please fill up all fields", "Notice!", JOptionPane.WARNING_MESSAGE);
                return;
            }
            if (!isConnectToServer) {
                isConnectToServer = true;


                this.connectToServer(hostName);
            }
            this.sendToServer("CMD_SIGN_UP|" + nickname + "|" + password);

            String response = this.recieveFromServer();
            if (response != null) {
                if (response.equals(NICKNAME_LOGGED_IN) || response.equals(ACCOUNT_EXIST)) {
                    JOptionPane.showMessageDialog(this, response, "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(this, response + "\nRedirecting to the Login page ....", "Success!", JOptionPane.INFORMATION_MESSAGE);
                    signUpPanel.clearTf();
                    signUpPanel.setVisible(false);
                    loginPanel.setVisible(true);
                    chatLabPanel.setVisible(false);
                }
            }
        }

    }

    private void btSendEvent() {
        String userName = "Group Chat";
        String message = chatLabPanel.getTaInput().getText().trim();
        if (message.equals("")) return;

        HashMap<String, StyledDocument> ConversationsDocList = chatLabPanel.GetConversationsDocList();
        StyledDocument CurrentDoc = chatLabPanel.GetCurrentConversationDoc();
        for (String name : ConversationsDocList.keySet()) {
            if (ConversationsDocList.get(name).equals(CurrentDoc)) {
                userName = name;
                break;
            }
        }

        if (userName.equals("Group Chat")) {
            this.sendToServer("CMD_CHAT|" + message);
        } else {
            try {
                SecretKey blk = ClientController.getBlowfishKey();
                System.out.println("(SENDER) GETTED KEY : " + blk);
                String encryptedMsgBlowfish = Blowfish.encryption(message, blk);
                System.out.println("(SENDER) SEND ENCRYPTED MSG : " + encryptedMsgBlowfish);
                chatLabPanel.appendMessage_Sent(this.name + ": ", message, new Color(33, 72, 127), Color.black);
                this.sendToServer("CMD_PRIVATECHAT|" + this.name + "|" + userName + "|" + encryptedMsgBlowfish);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        this.btClearEvent();
    }

    private void btClearEvent() {
        chatLabPanel.getTaInput().setText("");
    }

    public void connectToServer(String hostAddress) {
        try {
            socketOfClient = new Socket(hostAddress, 9999);
            bw = new BufferedWriter(new OutputStreamWriter(socketOfClient.getOutputStream()));
            br = new BufferedReader(new InputStreamReader(socketOfClient.getInputStream()));

        } catch (java.net.UnknownHostException e) {
            JOptionPane.showMessageDialog(this, "Host IP Incorrect !", "Failed to connect to server", JOptionPane.ERROR_MESSAGE);
        } catch (java.net.ConnectException e) {
            JOptionPane.showMessageDialog(this, "Server is unreachable, Try opening the server first.\nPlease try again!", "Failed to connect to server", JOptionPane.ERROR_MESSAGE);
        } catch (java.net.NoRouteToHostException e) {
            JOptionPane.showMessageDialog(this, "Can't find this host!\nPlease try again!", "Failed to connect to server", JOptionPane.ERROR_MESSAGE);
        } catch (IOException ex) {
            Logger.getLogger(ClientController.class.getName()).log(Level.SEVERE, null, ex);

        }
    }

    public void sendToServer(String line) {
        try {
            this.bw.write(line);
            this.bw.newLine();
            this.bw.flush();
        } catch (java.net.SocketException e) {
            JOptionPane.showMessageDialog(this, "Server is closed, can't send message!", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (java.lang.NullPointerException e) {
            System.out.println("Server is not open yet, or already closed!");
        } catch (IOException ex) {
            Logger.getLogger(ClientController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String recieveFromServer() {
        try {
            return this.br.readLine();
        } catch (java.lang.NullPointerException e) {
            System.out.println("Server is not open yet, or already closed!");
        } catch (IOException ex) {
            Logger.getLogger(ClientController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public void run() {
        String response;
        String sender, fileName, thePersonSendFile;
        String msg;
        String cmd, icon;
        boolean isin = true;

        while (isRunning) {
            try {
                response = this.recieveFromServer();
                tokenizer = new StringTokenizer(response, "|");
                cmd = tokenizer.nextToken();
                switch (cmd) {
                    case "CMD_CHAT":
                        sender = tokenizer.nextToken();
                        msg = response.substring(cmd.length() + sender.length() + 2);

                        if (isin && msg.equals(RELOAD_OLD_GROUP_CHAT)) {
                            try {
                                Class.forName("com.mysql.cj.jdbc.Driver");
                                java.sql.Connection conn1 = DriverManager.getConnection("jdbc:mysql://localhost:3306?chat_db", "root", "oknaa");
                                Statement st1 = conn1.createStatement();
                                String fil1 = "select `MSG_TEXT`,`ID_SENDER` from chat_db.messages where `ID_GRP`=1 ORDER BY `DATETIME` ASC";
                                ResultSet rs1 = st1.executeQuery(fil1);

                                while (rs1.next()) {
                                    String msgtxt = rs1.getString("MSG_TEXT");
                                    String senderr = rs1.getString("ID_SENDER");

                                    System.out.println("INSERED TO CHATPANEL : " + msgtxt);

                                    if (senderr.equals(this.name))
                                        this.chatLabPanel.appendMessage_Sent(senderr + ": ", msgtxt, new Color(33, 72, 127), new Color(0, 0, 0));
                                    else
                                        this.chatLabPanel.appendMessage_Received(senderr + ": ", msgtxt, new Color(33, 72, 127), new Color(0, 0, 0));
                                }
                            } catch (Exception e1) {
                                e1.printStackTrace();
                            }
                            isin = false;
                        }

                        if (sender.equals(this.name)) {
                            this.chatLabPanel.appendMessage_Sent(sender + ": ", msg, new Color(33, 72, 127), new Color(0, 0, 0));
                        } else {
                            this.chatLabPanel.appendMessage_Received(sender + ": ", msg, new Color(33, 72, 127), new Color(0, 0, 0));
                        }
                        break;

                    case "CMD_BLOWFISHKEYTOCLIENT":
                        String blowfishkey = response.substring(cmd.length() + 1, response.length());
                        System.out.println("(CLIENT) GETTING BLOWFISH ENCRYPTEDKEY : " + blowfishkey);
                        String base64private = getPrivateKeyRSA();
                        System.out.println("(CLIENT) GETTING RSA PRIVATE KEY : " + base64private);
                        try {
                            decryptedString = RSA.decryptRSA(blowfishkey, base64private);
                            System.out.println("(CLIENT) DECRYPT BLOWFISH KEY WITH RSA PRIVATEKEY" + decryptedString);
                            byte[] decodedKey = Base64.getDecoder().decode(decryptedString);
                            originalBlowfishKey = new SecretKeySpec(decodedKey, 0, decodedKey.length, "Blowfish");
                            System.out.println("(CLIENT) GETTING ORIGINAL FIRST BLOWFISH KEY : " + originalBlowfishKey);
                        } catch (Exception e1) {
                            e1.printStackTrace();
                        }
                        break;

                    case "CMD_PRIVATECHAT":
                        sender = tokenizer.nextToken();
                        msg = response.substring(cmd.length() + sender.length() + 2);
                        String decreptedBlowfishMsg = "";
                        try {
                            decreptedBlowfishMsg = Blowfish.decryption(msg, originalBlowfishKey);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                        if (sender.equals(this.name)) {
                            this.chatLabPanel.appendMessage_Sent(sender + ": ", decreptedBlowfishMsg, new Color(33, 72, 127), new Color(0, 0, 0));
                        } else {
                            this.chatLabPanel.appendMessage_Received(sender + ": ", decreptedBlowfishMsg, new Color(33, 72, 127), new Color(0, 0, 0));
                        }
                        break;

                    case "CMD_USERS":
                        this.chatLabPanel.getOnlineList().setText("");
                        AllUsersList.clear();
                        while (tokenizer.hasMoreTokens()) {
                            cmd = tokenizer.nextToken();
                            AllUsersList.add(cmd);
                            this.chatLabPanel.appendMessage_OnlineUsersList(cmd, cmd.equals(this.name) ? Color.GREEN : Color.red, this);
                        }
                        break;

                    case "CMD_ONLINE_USERS":
                        this.chatLabPanel.getOnlineList().setText("");
                        OnlineUsersList.clear();
                        while (tokenizer.hasMoreTokens()) {
                            cmd = tokenizer.nextToken();
                            OnlineUsersList.add(cmd);
                        }
                        for (String userName : AllUsersList) {
                            if (OnlineUsersList.contains(userName) || userName.equals(this.name)) {
                                this.chatLabPanel.appendMessage_OnlineUsersList(userName, new Color(173, 231, 115), this);
                            } else {
                                this.chatLabPanel.appendMessage_OnlineUsersList(userName, new Color(231, 115, 115), this);
                            }
                        }
                        break;

                    case "CMD_FILEAVAILABLE":
                        System.out.println("file available");
                        fileName = tokenizer.nextToken();
                        thePersonSendFile = tokenizer.nextToken();

                        chatLabPanel.insertButton(thePersonSendFile, fileName);
                        break;

                    case "CMD_ICON":
                        icon = tokenizer.nextToken();
                        cmd = tokenizer.nextToken();

                        if (cmd.equals(this.name)) {
                            this.chatLabPanel.appendMessage_Sent(cmd + ": ", "\n  ", Color.BLACK, Color.BLACK);
                        } else {
                            this.chatLabPanel.appendMessage_Received(cmd + ": ", "\n   ", Color.gray, Color.gray);
                        }

                        switch (icon) {
                            case "LIKE" -> this.clientPanel.getTpMessage().insertIcon(new ImageIcon(getClass().getResource("/images/like2.png")));
                            case "DISLIKE" -> this.clientPanel.getTpMessage().insertIcon(new ImageIcon(getClass().getResource("/images/dislike.png")));
                            case "PAC_MAN" -> this.clientPanel.getTpMessage().insertIcon(new ImageIcon(getClass().getResource("/images/pacman.png")));
                            case "SMILE" -> this.clientPanel.getTpMessage().insertIcon(new ImageIcon(getClass().getResource("/images/smile.png")));
                            case "GRIN" -> this.clientPanel.getTpMessage().insertIcon(new ImageIcon(getClass().getResource("/images/grin.png")));
                            case "CRY" -> this.clientPanel.getTpMessage().insertIcon(new ImageIcon(getClass().getResource("/images/cry.png")));
                            default -> throw new AssertionError("The icon is invalid, or can't find icon!");
                        }
                        break;
                    default:
                        if (!response.startsWith("CMD_")) {
                            if (response.equals("Warnning: Server has been closed!")) {
                                this.chatLabPanel.appendMessage_Alert(response, Color.RED);
                            } else if (response.contains("has just entered!")) {
                                this.chatLabPanel.appendMessage_Alert(response, Color.CYAN);
                            } else if (response.contains("has quited")) {
                                this.chatLabPanel.appendMessage_Alert(response, Color.CYAN);
                            } else {
                                System.out.println(response);
                            }
                        }
                }
            } catch (NoSuchElementException xx) {
                System.out.println("ClientF:605");
            }
        }
        System.out.println("Disconnected to server!");
    }

}