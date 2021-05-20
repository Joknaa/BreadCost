package com.muc;

import com.muc.NewUI.OutputView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import static com.muc.NewUI.OutputView.HELIOTROPE_GRAY;
import static com.muc.NewUI.OutputView.INDEPENDENCE;

public class UserPaneOffLine extends JPanel implements UserStatusListener {
    private final ChatClient client;
    private final JList<String> userListUI;
    private final JList<String> userListON;
    private final JList<String> userList;
    private final JList<String> userListOf;
    private final DefaultListModel<String> userListModel = new DefaultListModel();
    private DefaultListModel<String> userListModel1 = new DefaultListModel();
    private final DefaultListModel<String> userListTotMod = new DefaultListModel();
    private final DefaultListModel<String> userListOffMod = new DefaultListModel();
    private final HashMap<String, JFrame> DirectMessagePan = new HashMap<>();
    private final String currentUser;

    public UserPaneOffLine(ChatClient client, String currentUser) {
        this.client = client;
        this.client.addUserStatusListener(this);
        //currentUser = LoginWindow.getCurrentUser();
        this.currentUser = currentUser;

        userListUI = new JList<>(userListModel);
        userList = new JList<>(userListTotMod);
        userListOf = new JList<>(userListOffMod);
        userListON = new JList<>(userListModel1);

        Try_FillOfflineList();
        RemoveOnlineUsers();

        setLayout(new BorderLayout());
        userList.setBackground(HELIOTROPE_GRAY);
        userList.setForeground(Color.white);
        userList.setSelectionBackground(INDEPENDENCE);
        userList.setSelectionForeground(Color.white);
        setPreferredSize(new Dimension(150, 350));
        add(new JScrollPane(userList), BorderLayout.CENTER);

        userList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() > 1) {
                    String selectedUser = userList.getSelectedValue();
                    if (DirectMessagePan.containsKey(selectedUser)) {
                        DirectMessagePan.get(selectedUser).setVisible(true);
                    } else {
                        MessagePane messagePane = new MessagePane(client, selectedUser);

                        JFrame frame = new JFrame(currentUser + " - Message: " + selectedUser);
                        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
                        frame.setSize(500, 500);
                        frame.getContentPane().add(messagePane, BorderLayout.CENTER);
                        frame.setVisible(true);

                        DirectMessagePan.put(selectedUser, frame);
                    }
                }
            }
        });
    }

    private void RemoveOnlineUsers() {
        for (int i = 0; i < userListTotMod.size(); i++) {
            if (currentUser.equals(userListTotMod.get(i))) {
                userListTotMod.removeElement(userListTotMod.get(i));
            }
        }

        userListModel1 = UserListPane.getUserOnList();
        if (userListTotMod.size() > 0) {
            for (int i = 0; i < userListTotMod.size(); i++) {
                if (userListModel1.contains(userListTotMod.get(i))) {
                    userListTotMod.removeElement(userListTotMod.get(i));
                }
            }
        }
        userList.remove(userListON);
    }

    private void Try_FillOfflineList() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            java.sql.Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/chatapp", "root", "oknaa");
            java.sql.Statement st1 = conn.createStatement();
            String fil = "select LOGIN from `users`;";
            ResultSet rs1 = st1.executeQuery(fil);
            while (rs1.next()) {
                String username = rs1.getString("LOGIN");
                userListTotMod.addElement(username);
            }
        } catch (ClassNotFoundException | SQLException e1) {
            e1.printStackTrace();
        }
    }

    @Override
    public void online(String login) {
        userListModel.addElement(login);
        userListTotMod.removeElement(login);
        userListTotMod.removeElement(userListUI);
    }

    @Override
    public void offline(String login) {
        userListModel.removeElement(login);
        userListTotMod.addElement(login);
    }
}