package com.muc;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MessagePane extends JPanel implements MessageListener {

    private final ChatClient client;
    private final String receiver;
    private final DefaultListModel<String> listModel = new DefaultListModel<>();
    private final JList<String> messageList = new JList<>(listModel);
    private final JTextField inputField = new JTextField();
    public String currentUser;

    public MessagePane(ChatClient client, String receiver) {
        this.client = client;
        client.addMessageListener(this);

        this.receiver = receiver;
        currentUser = LoginWindow.getCurrentUser();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            java.sql.Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/chatapp", "root", "oknaa");
            Statement st = conn.createStatement();
            String fil = "select `MSG_TEXT`,`SENDER`, `RECEIVER`,`ID_MESSAGE` from `messages` where ((SENDER = '" + currentUser + "' AND RECEIVER='" + receiver + "') OR (SENDER = '" + receiver + "' AND RECEIVER='" + currentUser + "')) AND `ID_GRP`=0 ORDER BY `ID_MESSAGE` ASC";
            ResultSet rs = st.executeQuery(fil);

            while (rs.next()) {
                String msgtxt = rs.getString("MSG_TEXT");
                String sender = rs.getString("SENDER");
                int idmsg = rs.getInt("ID_MESSAGE");
                String reciever = rs.getString("RECEIVER");
                String oldmsgs = reciever + " : " + msgtxt + "\n";
                listModel.addElement(oldmsgs);
            }
        } catch (ClassNotFoundException | SQLException e2) {
            e2.printStackTrace();
        }

        setLayout(new BorderLayout());
        add(new JScrollPane(messageList), BorderLayout.CENTER);
        add(inputField, BorderLayout.SOUTH);

        inputField.addActionListener(e -> {
            try {
                String text = inputField.getText();
                if (text == null || text.length() == 0 || text.trim().isEmpty()) return;
                client.msg(currentUser, receiver, text);
                listModel.addElement(currentUser + ": " + text);
                inputField.setText("");
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });
    }

    @Override
    public void onMessage(String sender, String msgBody) {
        if (receiver.equalsIgnoreCase(sender)) {
            String line = sender + ": " + msgBody;
            listModel.addElement(line);
        }
    }

}
