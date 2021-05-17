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
    private final String login;
    public String rec;
    public String[] tabrec;
    int i = 0;
    int j = 0;
    int nb = 0;
    private final DefaultListModel<String> listModel = new DefaultListModel<>();
    private final JList<String> messageList = new JList<>(listModel);
    private final JTextField inputField = new JTextField();

    public MessagePane(ChatClient client, String login) {
        this.client = client;
        this.login = login;

        //rec = LoginWindow.getLogg();
        rec = LoginWindow.getLogg();

        client.addMessageListener(this);
        System.out.println("\n LOGIN : " + login);

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            java.sql.Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/chatapp", "root", "oknaa");
            Statement st = conn.createStatement();
            String fil = "select `MSG_TEXT`,`ID_SENDER`, `ID_RECIEVER`,`ID_MESSAGE` from `messages` where ((ID_SENDER = '" + rec + "' AND ID_RECIEVER='" + login + "') OR (ID_SENDER = '" + login + "' AND ID_RECIEVER='" + rec + "')) AND `ID_GRP`=0 ORDER BY `ID_MESSAGE` ASC";
            ResultSet rs = st.executeQuery(fil);

            //String fil1 = "select `MSG_TEXT`,`ID_SENDER`, `ID_RECIEVER`,`ID_MESSAGE` from `messages` where ID_SENDER = '" + login +"' AND ID_RECIEVER='"+rec+"' ORDER BY `ID_MESSAGE` ASC;";
            //ResultSet rs1 = st.executeQuery(fil1);

            while (rs.next()) {
                String msgtxt = rs.getString("MSG_TEXT");
                String sender = rs.getString("ID_SENDER");
                int idmsg = rs.getInt("ID_MESSAGE");
                String reciever = rs.getString("ID_RECIEVER");
                //String msgtxt1 = rs1.getString("MSG_TEXT");
                String oldmsgs = reciever + " : " + msgtxt + "\n";
                if (login.equals(sender)) {
                    oldmsgs = "You : " + msgtxt + "\n";
                }
                listModel.addElement(oldmsgs);
            }


        } catch (ClassNotFoundException | SQLException e2) {
            // TODO Auto-generated catch block
            e2.printStackTrace();
        }


        setLayout(new BorderLayout());
        add(new JScrollPane(messageList), BorderLayout.CENTER);
        add(inputField, BorderLayout.SOUTH);

        inputField.addActionListener(e -> {
            try {
                String text = inputField.getText();
                client.msg(login, text);
                listModel.addElement("You: " + text);
                inputField.setText("");
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });
    }

    @Override
    public void onMessage(String fromLogin, String msgBody) {
        if (login.equalsIgnoreCase(fromLogin)) {
            String line = fromLogin + ": " + msgBody;
            listModel.addElement(line);
        }
        rec = fromLogin;
    }

}
