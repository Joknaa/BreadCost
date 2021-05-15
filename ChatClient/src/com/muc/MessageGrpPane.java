package com.muc;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.Date;

public class MessageGrpPane extends JPanel implements MessageListener {

    private final ChatClient client;
    private final String login;
    public String rec;
    private DefaultListModel<String> listModel1 = new DefaultListModel<>();
    private JList<String> messageList = new JList<>(listModel1);
    private JTextField inputField = new JTextField();
    public String[] tabrec;
    int i=0;
    int j=0;
    int nb=0;
    
    public MessageGrpPane(ChatClient client, String login) {
        this.client = client;
        this.login = login;
        
        rec = LoginWindow.getLogg();
        
        client.addMessageListener(this);
        System.out.println("\n LOGIN : "+ login);
        
        try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			java.sql.Connection conn =  DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/chatapp","root","oknaa");
	 		Statement st =  conn.createStatement();
	 		String fil = "select `MSG_TEXT`,`ID_SENDER`, `ID_RECIEVER`,`ID_MESSAGE` from `messages` where `ID_GRP`=1 ORDER BY `ID_MESSAGE` ASC";
	 		ResultSet rs = st.executeQuery(fil);
	 		
	 		while(rs.next()) {
	 			String msgtxt = rs.getString("MSG_TEXT");
	 			String sender = rs.getString("ID_SENDER");
	 			int idmsg = rs.getInt("ID_MESSAGE");
	 			String oldmsgs = "(GRP) "+sender +" : " + msgtxt+"\n";
	 			if(login.equals(sender)) {
	 				oldmsgs = "(GRP) You : " + msgtxt+"\n";
	 			}
	 	    	listModel1.addElement(oldmsgs);
	 			}
	 		
	 		
	 		
		} catch (ClassNotFoundException | SQLException e2) {
			e2.printStackTrace();
		}
 		
        
        
        
        
        setLayout(new BorderLayout());
        add(new JScrollPane(messageList), BorderLayout.CENTER);
        add(inputField, BorderLayout.SOUTH);

        inputField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String text = inputField.getText();
                    client.msg("#1", text);
                    //listModel1.addElement("You: " + text);
                    inputField.setText("");
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });
    }
    @Override
    public void onMessage(String fromLogin, String msgBody) {
    	if (fromLogin.charAt(0) == '#') {
            String line = "(GRP) "+fromLogin + " : " + msgBody;
            listModel1.addElement(line);
    	}
        rec = fromLogin;
        System.out.println(rec);
    }
    
}
