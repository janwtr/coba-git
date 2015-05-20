package gochatserver;

import java.io.IOException;
import java.net.Socket;
import java.net.ServerSocket;
import java.util.HashMap;

public class ServerChat {
	private static ServerSocket serverSocket;
	private static final int PORT = 1234;	
	static HashMap<String,Socket> onlineUser = new HashMap<>();
	
	public static void main(String[] args) throws IOException {		
		
		try {
			serverSocket = new ServerSocket(PORT);
                        System.out.println("GoChat server was running...");
		} catch (IOException ioEx) {
			System.out.println("Unable to set up port!");
			System.exit(1);
		}
		
		do {			                        
			Socket client = serverSocket.accept();		                        
			System.out.println("New client connected: "+ client.toString());
                        ClientHandler handler = new ClientHandler(client, onlineUser);
			handler.start();
		} while (true);
	}
	
}