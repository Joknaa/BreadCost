package com.muc;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;


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

    public UserPaneOffLine(ChatClient client) {
        this.client = client;
        this.client.addUserStatusListener(this);
        currentUser = LoginWindow.getLogg();


        userListUI = new JList<>(userListModel);
        userList = new JList<>(userListTotMod);
        userListOf = new JList<>(userListOffMod);
        userListON = new JList<>(userListModel1);


        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            java.sql.Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/chatapp", "root", "oknaa");
            java.sql.Statement st1 = conn.createStatement();
            String fil = "select LOGIN from `users`;";
            ResultSet rs1 = st1.executeQuery(fil);
            while (rs1.next()) {
                String logg = rs1.getString("LOGIN");
                userListTotMod.addElement(logg);
            }
        } catch (ClassNotFoundException | SQLException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }

        String logg = LoginWindow.getLogg();


        for (int i = 0; i < userListTotMod.size(); i++) {
            if (logg.equals(userListTotMod.get(i))) {
                userListTotMod.removeElement(userListTotMod.get(i));
            }
        }


        userListModel1 = UserListPane.getUserOnList();
        //userListTotMod.removeElement(userListModel1);

        for (int i = 0; i < userListModel1.size(); i++) {
            System.out.println("\n IMPORTED ONLINE : " + userListModel1.get(i));
        }

        System.out.println("\nOFFLINE LIST :\n");
        if (userListTotMod.size() > 0) {
            for (int i = 0; i < userListTotMod.size(); i++) {

                if (userListModel1.contains(userListTotMod.get(i))) {
                    System.out.println("\n REMOVED2 : " + userListTotMod.get(i));

                    userListTotMod.removeElement(userListTotMod.get(i));
                    //userListModel1.removeElement(userListTotMod.get(i));
                }
            }
        }

        System.out.println("AFTER REMOVING : \n");
        for (int i = 0; i < userListModel1.size(); i++) {
            System.out.println("\n AFTER MODEL1  : " + userListModel1.get(i));
        }

        for (int i = 0; i < userListTotMod.size(); i++) {
            System.out.println("\n TOTMOD : " + userListTotMod.get(i));
        }


        userList.remove(userListON);

        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(100, 600));

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










		