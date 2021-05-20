package com.muc;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

public class LoginWindow extends JFrame {
    public static String currentUser;
    private final JPanel mainPanel = new JPanel();
    private final JTextField loginField = new JTextField();
    private final JPasswordField passwordField = new JPasswordField();
    private final JButton loginButton = new JButton("Login");
    private ChatClient client;

    public LoginWindow() {
        super("Login");


        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new BoxLayout(inputPanel, BoxLayout.Y_AXIS));
        inputPanel.add(loginField);
        inputPanel.add(passwordField);
        inputPanel.add(loginButton);
        inputPanel.setSize(inputPanel.getPreferredSize());
        loginButton.addActionListener(e -> doLogin());

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().add(inputPanel, BorderLayout.EAST);
        setSize(new Dimension(600, 400));
        setVisible(true);
    }

    public static String getCurrentUser() {
        return currentUser;
    }

    public static void main(String[] args) {
        LoginWindow loginWin = new LoginWindow();
        loginWin.setVisible(true);
    }

    private void doLogin() {
        currentUser = loginField.getText();
        String password = passwordField.getText();
        client = new ChatClient("127.0.0.1", 8818);
        client.connect();
        try {
            if (client.login(currentUser, password)) {
                UserListPane onlineListPanel = new UserListPane(client, currentUser);
                MessageGrpPane groupChatPanel = new MessageGrpPane(client, currentUser);
                UserPaneOffLine offlineListPanel = new UserPaneOffLine(client, currentUser);

                JFrame frame = new JFrame("BreadCost - " + currentUser);
                frame.add(mainPanel);
                frame.add(onlineListPanel, BorderLayout.WEST);
                frame.add(groupChatPanel, BorderLayout.CENTER);
                frame.add(offlineListPanel, BorderLayout.EAST);

                frame.setSize(new Dimension(600, 400));
                frame.setVisible(true);
                frame.setResizable(false);
                frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
                frame.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosing(WindowEvent we) {
                        String ObjButtons[] = {"Yes", "No"};
                        int PromptResult = JOptionPane.showOptionDialog(null, "Are you sure you want to exit?", "Online Examination System", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, ObjButtons, ObjButtons[1]);
                        if (PromptResult == JOptionPane.YES_OPTION) {
                            System.exit(0);
                        }
                    }
                });

                setVisible(false);
            } else {
                JOptionPane.showMessageDialog(this, "Invalid login/password.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
