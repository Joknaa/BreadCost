package Presenters.Client;

import java.awt.*;

import javax.swing.*;

import static Presenters.Client.ChatClient.Server_Connect;
import static Presenters.Client.ChatClient.Server_Login;


public class ClientInterface extends JPanel implements Status {
	private ChatClient client;
	private JList<String> userListUI;
	private DefaultListModel<String> userListModel;

	public ClientInterface(ChatClient client) {
		this.client = client;
		userListModel = new DefaultListModel<>();
		userListUI = new JList<>(userListModel);
		
		setLayout(new BorderLayout());
		add(new JScrollPane(userListUI), BorderLayout.CENTER);
	}

	public static void main(String[] args) {
		ChatClient client = new ChatClient("localhost", 8818);
		ClientInterface clientInterface = new ClientInterface(client);
		
		JFrame frame = new JFrame("User List");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(400, 600);
		frame.getContentPane().add(clientInterface, BorderLayout.CENTER);
		frame.setVisible(true);
		
		if(Server_Connect()) {
			Server_Login("oussama");
		}
		
		
	}

	@Override
	public void online(String login) {
		userListModel.addElement(login);
	}

	@Override
	public void offline(String logout) {
		userListModel.removeElement(logout);
	}
}
