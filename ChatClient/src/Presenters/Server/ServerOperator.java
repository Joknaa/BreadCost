package Presenters.Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ServerOperator extends Thread {
	private int serverPort;
	private ArrayList<ClientOperator> clientList = new ArrayList<>();

	@Override
	public void run() {
		try {
			ServerSocket serverSocket = new ServerSocket(serverPort);
			
			while(true) {
				System.out.println("Connection avec le client ...");
				Socket clientSocket = serverSocket.accept();
				ClientOperator clientOperator = new ClientOperator(this, clientSocket);
				clientList.add(clientOperator);
				clientOperator.start();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public ServerOperator(int serverPort) {
		this.serverPort = serverPort;
	}
	public List<ClientOperator> getClientList(){
		return clientList;
	}
	public void RemoveOperator(ClientOperator clientOperator) {
		clientList.remove(clientOperator);
	}
}