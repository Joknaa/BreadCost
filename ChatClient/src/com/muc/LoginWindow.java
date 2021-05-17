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

    private final JPanel mainPanel = new JPanel();
    private final JPanel headerPanel = new JPanel();

    private UserListPane onlineListPanel;
    private MessageGrpPane groupChatPanel;
    private UserPaneOffLine offlineListPanel;

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
                onlineListPanel = new UserListPane(client);
                groupChatPanel = new MessageGrpPane(client, logg);
                offlineListPanel = new UserPaneOffLine(client);

                //SetupGUI();

                JFrame frame = new JFrame("BreadCost - " + logg);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.add(mainPanel);
                frame.setSize(new Dimension(800, 600));
                frame.setVisible(true);

                frame.add(onlineListPanel, BorderLayout.WEST);
                frame.add(groupChatPanel, BorderLayout.CENTER);
                frame.add(offlineListPanel, BorderLayout.EAST);


                /*JFrame jframe = new JFrame("OFFLINE USERS List");
                jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                jframe.setSize(400, 600);

                jframe.getContentPane().add(userListOffLine, BorderLayout.CENTER);
                jframe.setVisible(true);

                JFrame f = new JFrame("Message en Groupe");
                f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                f.setSize(500, 500);
                f.getContentPane().add(messageGrpPane, BorderLayout.CENTER);
                f.setVisible(true);
                 */
                setVisible(false);
            } else {
                JOptionPane.showMessageDialog(this, "Invalid login/password.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void SetupGUI() {
        mainPanel.setPreferredSize(new java.awt.Dimension(800, 600));
        SetupHeaderPanel();
        SetupGroupChatPanel();
        SetupOnlineListPanel();
        SetupOfflineListPanel();
        SetupMainPanelLayout();
    }

    //<editor-fold desc="Setup GUI">
    private void SetupHeaderPanel() {
        GroupLayout headerPanelLayout = new GroupLayout(headerPanel);
        headerPanel.setLayout(headerPanelLayout);
        headerPanelLayout.setHorizontalGroup(
                headerPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGap(0, 0, Short.MAX_VALUE)
        );
        headerPanelLayout.setVerticalGroup(
                headerPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGap(0, 100, Short.MAX_VALUE)
        );
    }
    private void SetupOnlineListPanel() {
        onlineListPanel.setPreferredSize(new Dimension(200, 600));

        GroupLayout onlineUsersPanelLayout = new GroupLayout(onlineListPanel);
        onlineListPanel.setLayout(onlineUsersPanelLayout);
        onlineUsersPanelLayout.setHorizontalGroup(
                onlineUsersPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGap(0, 200, Short.MAX_VALUE)
        );
        onlineUsersPanelLayout.setVerticalGroup(
                onlineUsersPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGap(0, 500, Short.MAX_VALUE)
        );
    }
    private void SetupOfflineListPanel() {
        offlineListPanel.setPreferredSize(new Dimension(200, 600));

        GroupLayout offlineUsersPanelLayout = new GroupLayout(offlineListPanel);
        offlineListPanel.setLayout(offlineUsersPanelLayout);
        offlineUsersPanelLayout.setHorizontalGroup(
                offlineUsersPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGap(0, 200, Short.MAX_VALUE)
        );
        offlineUsersPanelLayout.setVerticalGroup(
                offlineUsersPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGap(0, 0, Short.MAX_VALUE)
        );
    }
    private void SetupGroupChatPanel() {
        groupChatPanel.setPreferredSize(new Dimension(400, 600));

        GroupLayout groupChatPanelLayout = new GroupLayout(groupChatPanel);
        groupChatPanel.setLayout(groupChatPanelLayout);
        groupChatPanelLayout.setHorizontalGroup(
                groupChatPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGap(0, 400, Short.MAX_VALUE)
        );
        groupChatPanelLayout.setVerticalGroup(
                groupChatPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGap(0, 0, Short.MAX_VALUE)
        );
    }
    private void SetupMainPanelLayout() {
        GroupLayout layout = new GroupLayout(mainPanel);
        mainPanel.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(onlineListPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(groupChatPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(offlineListPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                        .addComponent(headerPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(headerPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                        .addComponent(onlineListPanel, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 500, Short.MAX_VALUE)
                                        .addComponent(groupChatPanel, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 500, Short.MAX_VALUE)
                                        .addComponent(offlineListPanel, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 500, Short.MAX_VALUE)))
        );
    }
    //</editor-fold>
}
