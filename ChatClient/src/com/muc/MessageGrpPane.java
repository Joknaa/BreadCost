package com.muc;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import static com.muc.NewUI.OutputView.*;

public class MessageGrpPane extends JPanel implements MessageListener {

    private final ChatClient client;
    private String currentUser;
    private final DefaultListModel<String> listModel1 = new DefaultListModel<>();
    private final JList<String> messageList = new JList<>(listModel1);
    private final JTextField inputField = new JTextField();

    public MessageGrpPane(ChatClient client, String currentUser) {
        this.client = client;
        this.currentUser = currentUser;

        client.addMessageListener(this);
        System.out.println("\n LOGIN : " + currentUser);

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            java.sql.Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/chatapp", "root", "oknaa");
            Statement st = conn.createStatement();
            String fil = "select `MSG_TEXT`,`SENDER`, `RECEIVER`,`ID_MESSAGE` from `messages` where `ID_GRP`=1 ORDER BY `ID_MESSAGE` ASC";
            ResultSet rs = st.executeQuery(fil);

            while (rs.next()) {
                String msgtxt = rs.getString("MSG_TEXT");
                String sender = rs.getString("SENDER");
                int idmsg = rs.getInt("ID_MESSAGE");
                String oldmsgs = "(GRP) " + sender + " : " + msgtxt + "\n";
                listModel1.addElement(oldmsgs);
            }


        } catch (ClassNotFoundException | SQLException e2) {
            e2.printStackTrace();
        }

        messageList.setBackground(HELIOTROPE_GRAY);
        messageList.setForeground(Color.white);
        messageList.setSelectionBackground(INDEPENDENCE);
        messageList.setSelectionForeground(Color.white);

        setPreferredSize(new Dimension(400, 350));
        setLayout(new BorderLayout());
        add(new JScrollPane(messageList), BorderLayout.CENTER);
        add(inputField, BorderLayout.SOUTH);

        inputField.addActionListener(e -> {
            try {
                String text = inputField.getText();
                client.msg(currentUser, "#1", text);
                inputField.setText("");
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });
    }

    @Override
    public void onMessage(String sender, String msgBody) {
        if (sender.charAt(0) == '#') {
            String line = "(GRP) " + sender + " : " + msgBody;
            listModel1.addElement(line);
        }
        currentUser = sender;
        System.out.println(currentUser);
    }

}
