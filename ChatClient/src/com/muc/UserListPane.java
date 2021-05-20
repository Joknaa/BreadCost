package com.muc;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;

public class UserListPane extends JPanel implements UserStatusListener {


    private static DefaultListModel<String> userListModel;
    private final ChatClient client;
    private JList<String> userListUI;
    private HashMap<String, JFrame> DirectMessagePan = new HashMap<>();
    private String currentUser;

    public UserListPane(ChatClient client, String currentUser) {
        this.client = client;
        this.client.addUserStatusListener(this);
        //this.currentUser = LoginWindow.getCurrentUser();
        this.currentUser = currentUser;

        userListModel = new DefaultListModel<>();
        userListUI = new JList<>(userListModel);
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(150, 400));
        add(new JScrollPane(userListUI), BorderLayout.CENTER);

        userListUI.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() > 1) {
                    String selectedUser = userListUI.getSelectedValue();
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

    public static DefaultListModel<String> getUserOnList() {
        return userListModel;
    }

    @Override
    public void online(String login) {
        userListModel.addElement(login);
    }

    @Override
    public void offline(String login) {
        userListModel.removeElement(login);
    }
}
