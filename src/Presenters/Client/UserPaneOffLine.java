package Presenters.Client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;


public class UserPaneOffLine extends JPanel implements UserStatusListener {


    private final ChatClient client;
    private JList<String> userListUI;
    private JList<String> userList;
    private JList<String> userListOf;
    private DefaultListModel<String> userListModel = new DefaultListModel();;
    private DefaultListModel<String> userListTotMod = new DefaultListModel();;
    private DefaultListModel<String> userListOffMod = new DefaultListModel();;

    public UserPaneOffLine(ChatClient client) {
        this.client = client;
        this.client.addUserStatusListener(this);
        
        
        
        userListUI = new JList<>(userListModel);
        userList = new JList<>(userListTotMod);
        userListOf = new JList<>(userListOffMod);
        
        
        try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			java.sql.Connection conn =  DriverManager.getConnection("jdbc:mysql://localhost:3308/chatapp","root","");
			java.sql.Statement st1 = conn.createStatement();
	 		String fil = "select LOGIN from `users`;";
	 		ResultSet rs1 = st1.executeQuery(fil);
	 		while(rs1.next()) {
	 			String logg = rs1.getString("LOGIN");
	 			userListTotMod.addElement(logg);
	 		}
		} catch (ClassNotFoundException | SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
        for(int i=0; i < userListTotMod.size();i++) {
        	for(int j=0; j < userListModel.size();j++) {
        		if(userListTotMod.get(i) != userListModel.get(i)) {
        			userListOffMod.addElement(userListTotMod.get(i));
        		}
        	}
        }
        
        

        
        setLayout(new BorderLayout());
        
        
        
        
        
        
        add(new JScrollPane(userListOf), BorderLayout.CENTER);

        userListOf.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() > 1) {
                    String login = userListOf.getSelectedValue();
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
        JFrame frame = new JFrame("User List");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 600);

        frame.getContentPane().add(userListOffLine, BorderLayout.CENTER);
        frame.setVisible(true);

        if (client.connect()) {
            try {
                client.login("guest", "guest");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        
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










		