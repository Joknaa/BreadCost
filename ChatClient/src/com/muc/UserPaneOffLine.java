package com.muc;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;


public class UserPaneOffLine extends JPanel implements UserStatusListener {


    private final ChatClient client;
    private JList<String> userListUI;
    private JList<String> userListON;
    private JList<String> userList;
    private JList<String> userListOf;
    private DefaultListModel<String> userListModel = new DefaultListModel();
    private DefaultListModel<String> userListModel1 = new DefaultListModel();
    private DefaultListModel<String> userListTotMod = new DefaultListModel();
    private DefaultListModel<String> userListOffMod = new DefaultListModel();

    public UserPaneOffLine(ChatClient client) {
        this.client = client;
        this.client.addUserStatusListener(this);


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
        setPreferredSize(new Dimension(200, 600));

        add(new JScrollPane(userList), BorderLayout.CENTER);

        userList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() > 1) {
                    String login = userList.getSelectedValue();
                    MessagePane messagePane = new MessagePane(client, login);


                    JFrame f = new JFrame("Message: " + login);
                    f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    f.setSize(500, 500);
                    f.getContentPane().add(messagePane, BorderLayout.CENTER);
                    f.setVisible(true);
                }
            }
        });
    }

    public static void main(String[] args) {
        ChatClient client = new ChatClient("localhost", 8818);

        UserPaneOffLine userListOffLine = new UserPaneOffLine(client);
        JFrame frame = new JFrame("OFFLINE USERS List");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 600);

        frame.getContentPane().add(userListOffLine, BorderLayout.CENTER);
        frame.setVisible(true);


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










		