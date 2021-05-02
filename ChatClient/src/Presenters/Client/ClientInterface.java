package Presenters.Client;

import java.awt.*;

import javax.swing.*;


public class ClientInterface extends JPanel implements Status {
	private ChatClient client;
	private JList<String> userListUI;
	private DefaultListModel<String> userListModel;

	public ClientInterface(ChatClient client) {
		this.client = client;
		this.client.addStatus(this);
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
		
		if(client.Server_Connect()) {
			client.Server_Login("oussama");
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
