package SERVER;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import com.orsoncharts.OnDrawHandler;

import DAO.DBConn;

public class Server {
	public static HashMap<String, ClientHandler> clients = new HashMap<>();
	
	public Server() throws IOException {
		ServerSocket serverSocket = new ServerSocket(9999);
		System.out.println("Server started");
		setMaxConnection();
		
		while (true) {
			Socket socket = serverSocket.accept();
			System.out.println("Client connected");
			try {
				BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
				
				String username = reader.readLine();
				System.out.println("Server tiếp nhận: "+username);
				if (isDuplicateAccount(username)) {
					writer.println("DUPLICATE_LOGIN");
					socket.close();
					continue;
				} else {
					writer.println("OKE");
					ClientHandler clientHandler = new ClientHandler(username, reader, writer, clients);
				}
			} catch (Exception e) {
				socket.close();
				e.printStackTrace();
			}
		}
	}

	private void setMaxConnection() {
		try {
			new DBConn().updateDB("SET GLOBAL max_connections = 100000");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private boolean isDuplicateAccount(String username) {
		for (String userName : clients.keySet()) {
			if (userName.equals(username)) {
				return true;
			}
		}
		return false;
	}
	
	public static void broadCastMessage(Timestamp time, String message, String username, ClientHandler sender) {
		for (ClientHandler client : clients.values()) {
			if (client != sender) {
				client.sendMessage(message, time, username);
			}
		}
	}
	
	
	public static void sendOnlineUsers() {
		String id = clients.keySet().stream()
                .map(String::valueOf)
                .collect(Collectors.joining(","));
		for (ClientHandler client : clients.values()) {
			client.sendOnlineList(id);
		}
	}
	
	public static void updateUserList() {
		sendOnlineUsers();
	}
	
	public static void main(String[] args) throws IOException {
		new Server();
	}

	public static void broadCastAction(String message, ClientHandler sender) {
		for (ClientHandler client : clients.values()) {
			if (client != sender) {
				client.sendAction(message);
			}
		}
	}
}
