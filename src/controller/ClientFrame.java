/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import view.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.*;
import java.net.Socket;
import java.util.List;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author AnhTu
 */


public class ClientFrame extends JFrame implements Runnable {
    public static final String NICKNAME_EXIST = "This nickname is already login in another place! Please using another nickname";
    public static final String NICKNAME_VALID = "This nickname is OK";
    public static final String NICKNAME_INVALID = "Nickname or password is incorrect";
    public static final String SIGNUP_SUCCESS = "Sign up successful!";
    public static final String ACCOUNT_EXIST = "This nickname has been used! Please use another nickname!";
    int Count = 0;
    List<String> AllUsersList = new ArrayList<>();
    List<String> OnlineUsersList = new ArrayList<>();

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

    SendFileFrame sendFileFrame;

    StringTokenizer tokenizer;
    String myDownloadFolder;

    Socket socketOfSender, socketOfReceiver;

    DefaultListModel<String> listModel, listModelThisRoom, listModel_rp;

    boolean isConnectToServer;

    int timeClicked = 0;

    Hashtable<String, PrivateChat> listReceiver;
    Runnable counting = () -> {
        try {
            Thread.sleep(500);
        } catch (InterruptedException ex) {
            Logger.getLogger(ClientFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        timeClicked = 0;
    };


    public ClientFrame(String name) {
        this.name = name;
        socketOfClient = null;
        bw = null;
        br = null;
        isRunning = true;
        listModel = new DefaultListModel<>();
        listModelThisRoom = new DefaultListModel<>();
        listModel_rp = new DefaultListModel<>();
        isConnectToServer = false;
        listReceiver = new Hashtable<>();

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

        addEventsForSignUpPanel();
        addEventsForLoginPanel();
        addEventsForChatLabPanel();

        add(mainPanel);
        setResizable(true);
        setSize(new Dimension(900, 540));
        mainPanel.setBackground(new java.awt.Color(74, 78, 105));
        setLocation(400, 100);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("BreadCost - " + name);

        chatLabPanel.appendMessage_ConversationsList("Group Chat", "/Resources/ChatApp/group_chat_45px.png", this);

    }

    public static void main(String[] args) {

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            Logger.getLogger(ClientFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        ClientFrame client = new ClientFrame(null);
        client.setVisible(true);
    }

    private void addEventsForSignUpPanel() {
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

    private void addEventsForLoginPanel() {
        loginPanel.getTfNickname().addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent ke) {
                if (ke.getKeyCode() == KeyEvent.VK_ENTER) btOkEvent();
            }

        });
        loginPanel.getTfPass().addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent ke) {
                if (ke.getKeyCode() == KeyEvent.VK_ENTER) btOkEvent();
            }

        });
        loginPanel.getBtOK().addActionListener(ae -> btOkEvent());
        loginPanel.GetSignUpButton().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent me) {
                signUpPanel.setVisible(true);
                loginPanel.setVisible(false);
                chatLabPanel.setVisible(false);
            }
        });

    }

    private void addEventsForChatLabPanel() {
        chatLabPanel.getBtSend().addActionListener(ae -> btSendEvent());
        chatLabPanel.getBtExit().addActionListener(ae -> btExitEvent());
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
        /*timeClicked++;
        if (timeClicked == 1) {
            Thread countingTo500ms = new Thread(counting);
            countingTo500ms.start();
        }

        if (timeClicked == 2) {
        String nameClicked = clientPanel.getOnlineList().getSelectedValue();*/

        if (clickedUserName.equals(ClientFrame.this.name)) {
            JOptionPane.showMessageDialog(ClientFrame.this, "Can't send a message to yourself!", "Info", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        chatLabPanel.appendMessage_ConversationsList(clickedUserName, "/Resources/ChatApp/direct_chat_45px.png", this);

        if (!listReceiver.containsKey(clickedUserName)) {
            PrivateChat pc = new PrivateChat(name, clickedUserName, serverHost, bw, br);

            pc.getLbReceiver().setText("Private chat with \"" + pc.receiver + "\"");
            pc.setTitle(pc.receiver);
            pc.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
            pc.setVisible(true);

            listReceiver.put(clickedUserName, pc);
        } else {
            PrivateChat pc = listReceiver.get(clickedUserName);
            pc.setVisible(true);
        }
        //}
    }

    private void leaveRoom() {
        this.sendToServer("CMD_LEAVE_ROOM|" + this.room);
        try {
            Thread.sleep(200);
        } catch (InterruptedException ex) {
            Logger.getLogger(ClientFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.chatLabPanel.setVisible(false);

        //todo: the chat gets emptied when leaving !!
        this.setTitle("\"" + this.name + "\"");
    }


    private void btOkEvent() {
        //String hostname = loginPanel.getTfHost().getText().trim();
        String hostname = "localhost";
        String nickname = loginPanel.getTfNickname().getText().trim();
        String pass = loginPanel.getTfPass().getText().trim();

        this.serverHost = hostname;
        this.name = nickname;

        if (hostname.equals("") || nickname.equals("") || pass.equals("")) {
            JOptionPane.showMessageDialog(this, "Please fill up all fields", "Notice!", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (!isConnectToServer) {
            isConnectToServer = true;
            this.connectToServer(hostname);
        }
        this.sendToServer("CMD_CHECK_NAME|" + this.name + "|" + pass);


        String response = this.recieveFromServer();
        if (response != null) {
            if (response.equals(NICKNAME_EXIST) || response.equals(NICKNAME_INVALID)) {
                JOptionPane.showMessageDialog(this, response, "Error", JOptionPane.ERROR_MESSAGE);

            } else {
                loginPanel.setVisible(false);
                chatLabPanel.setVisible(true);
                chatLabPanel.setUserName(name);
                this.setTitle("\"" + name + "\"");

                clientThread = new Thread(this);
                clientThread.start();
                this.sendToServer("CMD_ROOM|" + this.room);

                System.out.println("this is \"" + name + "\"");
            }
        } else System.out.println("[btOkEvent()] Server is not open yet, or already closed!");
    }

    private void btSignUpEvent() {
        String pass = this.signUpPanel.getTfPass().getText();
        String pass2 = this.signUpPanel.getTfPass2().getText();
        if (!pass.equals(pass2)) {
            JOptionPane.showMessageDialog(this, "Passwords don't match", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            String nickname = signUpPanel.getTfID().getText().trim();
            //String hostName = signUpPanel.getTfHost().getText().trim();
            String hostName = "localhost";
            if (hostName.equals("") || nickname.equals("") || pass.equals("") || pass2.equals("")) {
                JOptionPane.showMessageDialog(this, "Please fill up all fields", "Notice!", JOptionPane.WARNING_MESSAGE);
                return;
            }
            if (!isConnectToServer) {
                isConnectToServer = true;


                this.connectToServer(hostName);
            }
            this.sendToServer("CMD_SIGN_UP|" + nickname + "|" + pass);

            String response = this.recieveFromServer();
            if (response != null) {
                if (response.equals(NICKNAME_EXIST) || response.equals(ACCOUNT_EXIST)) {
                    JOptionPane.showMessageDialog(this, response, "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(this, response + "\nYou can now go back and login to join chat room", "Success!", JOptionPane.INFORMATION_MESSAGE);
                    signUpPanel.clearTf();
                    signUpPanel.setVisible(false);
                    loginPanel.setVisible(true);
                    chatLabPanel.setVisible(false);
                }
            }
        }

    }

    private void btSendEvent() {
        String message = chatLabPanel.getTaInput().getText().trim();
        if (message.equals("")) return;
        this.sendToServer("CMD_CHAT|" + message);
        this.btClearEvent();
    }

    private void btClearEvent() {
        chatLabPanel.getTaInput().setText("");
    }


    private void btExitEvent() {
        try {
            //isRunning = false;
            //System.exit(0);
            chatLabPanel.setVisible(false);
            signUpPanel.setVisible(false);
            loginPanel.setVisible(true);
            disconnect();
            //todo: fix the Logout


        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void connectToServer(String hostAddress) {
        try {
            socketOfClient = new Socket(hostAddress, 9999);
            bw = new BufferedWriter(new OutputStreamWriter(socketOfClient.getOutputStream()));
            br = new BufferedReader(new InputStreamReader(socketOfClient.getInputStream()));

        } catch (java.net.UnknownHostException e) {
            JOptionPane.showMessageDialog(this, "Host IP is not correct.\nPlease try again!", "Failed to connect to server", JOptionPane.ERROR_MESSAGE);
        } catch (java.net.ConnectException e) {
            JOptionPane.showMessageDialog(this, "Server is unreachable, maybe server is not open yet, or can't find this host.\nPlease try again!", "Failed to connect to server", JOptionPane.ERROR_MESSAGE);
        } catch (java.net.NoRouteToHostException e) {
            JOptionPane.showMessageDialog(this, "Can't find this host!\nPlease try again!", "Failed to connect to server", JOptionPane.ERROR_MESSAGE);
        } catch (IOException ex) {
            Logger.getLogger(ClientFrame.class.getName()).log(Level.SEVERE, null, ex);

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
            System.out.println("[sendToServer()] Server is not open yet, or already closed!");
        } catch (IOException ex) {
            Logger.getLogger(ClientFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String recieveFromServer() {
        try {
            return this.br.readLine();
        } catch (java.lang.NullPointerException e) {
            System.out.println("[recieveFromServer()] Server is not open yet, or already closed!");
        } catch (IOException ex) {
            Logger.getLogger(ClientFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public void disconnect() {
        System.out.println("disconnect()");
        try {
            if (br != null) this.br.close();
            if (bw != null) this.bw.close();
            if (socketOfClient != null) this.socketOfClient.close();
            System.out.println("trong khoi try catch");
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
            Logger.getLogger(ClientFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void run() {
        String response;
        String sender, receiver, fileName, thePersonIamChattingWith, thePersonSendFile;
        String msg;
        String cmd, icon;
        PrivateChat pc;

        while (isRunning) {
            boolean Erase;
            response = this.recieveFromServer();
            tokenizer = new StringTokenizer(response, "|");
            cmd = tokenizer.nextToken();
            switch (cmd) {
                case "CMD_CHAT":
                    sender = tokenizer.nextToken();
                    msg = response.substring(cmd.length() + sender.length() + 2);

                    if (sender.equals(this.name)) {
                        this.chatLabPanel.appendMessage(sender + ": ", msg, Color.BLACK, new Color(0, 102, 204));
                    } else {
                        this.chatLabPanel.appendMessage(sender + ": ", msg, Color.MAGENTA, new Color(56, 224, 0));
                    }
                    break;

                case "CMD_PRIVATECHAT":
                    sender = tokenizer.nextToken();
                    msg = response.substring(cmd.length() + sender.length() + 2);

                    pc = listReceiver.get(sender);

                    if (pc == null) {
                        pc = new PrivateChat(name, sender, serverHost, bw, br);
                        pc.sender = name;
                        pc.receiver = sender;
                        pc.serverHost = this.serverHost;
                        pc.bw = ClientFrame.this.bw;
                        pc.br = ClientFrame.this.br;

                        pc.getLbReceiver().setText("Private chat with \"" + pc.receiver + "\"");
                        pc.setTitle(pc.receiver);
                        pc.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                        pc.setVisible(true);

                        listReceiver.put(sender, pc);
                    } else {
                        pc.setVisible(true);
                    }

                    //pc.appendMessage_Left(sender + ": ", msg);
                    pc.appendMessage(sender + ": ", msg, Color.MAGENTA, new Color(56, 224, 0));
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

                case "CMD_ONLINE_THIS_ROOM":
                    listModelThisRoom.clear();
                    while (tokenizer.hasMoreTokens()) {
                        cmd = tokenizer.nextToken();
                        listModelThisRoom.addElement(cmd);
                    }
                    break;


                case "CMD_FILEAVAILABLE":
                    System.out.println("file available");
                    fileName = tokenizer.nextToken();
                    thePersonIamChattingWith = tokenizer.nextToken();
                    thePersonSendFile = tokenizer.nextToken();

                    pc = listReceiver.get(thePersonIamChattingWith);

                    if (pc == null) {
                        sender = this.name;
                        receiver = thePersonIamChattingWith;
                        pc = new PrivateChat(sender, receiver, serverHost, bw, br);

                        pc.getLbReceiver().setText("Private chat with \"" + pc.receiver + "\"");
                        pc.setTitle(pc.receiver);
                        pc.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

                        listReceiver.put(receiver, pc);
                    }

                    pc.setVisible(true);
                    pc.insertButton(thePersonSendFile, fileName);
                    break;

                case "CMD_ICON":
                    icon = tokenizer.nextToken();
                    cmd = tokenizer.nextToken();

                    if (cmd.equals(this.name)) {
                        this.chatLabPanel.appendMessage(cmd + ": ", "\n  ", Color.BLACK, Color.BLACK);
                    } else {
                        this.chatLabPanel.appendMessage(cmd + ": ", "\n   ", Color.MAGENTA, Color.MAGENTA);
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
                        } else { System.out.println(response); }
                    }
            }
        }
        System.out.println("Disconnected to server!");
    }
}