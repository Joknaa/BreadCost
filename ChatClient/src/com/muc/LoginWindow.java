package com.muc;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class LoginWindow extends JFrame {
    public static String logg;
    private final ChatClient client;
    JTextField loginField = new JTextField();
    JPasswordField passwordField = new JPasswordField();
    JButton loginButton = new JButton("Login");

    public LoginWindow() {
        super("Login");

        this.client = new ChatClient("localhost", 8818);
        client.connect();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel p = new JPanel();
        p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
        p.add(loginField);
        p.add(passwordField);
        p.add(loginButton);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                doLogin();
            }
        });

        getContentPane().add(p, BorderLayout.CENTER);

        pack();

        setVisible(true);
    }

    public static String getLogg() {
        return logg;
    }

    public static void main(String[] args) {
        LoginWindow loginWin = new LoginWindow();
        loginWin.setVisible(true);
    }

    private void doLogin() {
        logg = loginField.getText();
        String password = passwordField.getText();


        try {
            if (client.login(logg, password)) {
                // bring up the user list window
                UserListPane userListPane = new UserListPane(client);
                MessageGrpPane messageGrpPane = new MessageGrpPane(client, logg);
                UserPaneOffLine userListOffLine = new UserPaneOffLine(client);

                JFrame frame = new JFrame("BreadCost - " + logg);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setSize(400, 600);
                frame.getContentPane().add(userListPane, BorderLayout.WEST);
                frame.getContentPane().add(messageGrpPane, BorderLayout.CENTER);
                frame.getContentPane().add(userListOffLine, BorderLayout.EAST);
                frame.setVisible(true);



                /*JFrame jframe = new JFrame("OFFLINE USERS List");
                jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                jframe.setSize(400, 600);

                jframe.getContentPane().add(userListOffLine, BorderLayout.CENTER);
                jframe.setVisible(true);
                 */


                /*
                JFrame f = new JFrame("Message en Groupe");
                f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                f.setSize(500, 500);
                f.getContentPane().add(messageGrpPane, BorderLayout.CENTER);
                f.setVisible(true);
                 */


                setVisible(false);
            } else {
                // show error message
                JOptionPane.showMessageDialog(this, "Invalid login/password.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
