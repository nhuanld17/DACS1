package SERVER;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.HashMap;

import BUS.HistoryBUS;
import DAO.HistoryDAO;

public class ClientHandler extends Thread{
	private BufferedReader reader;
	private PrintWriter writer;
	private HashMap<String, ClientHandler> clients;
	private String username;
	
	public ClientHandler(String username, BufferedReader reader, PrintWriter writer, HashMap<String, ClientHandler> clients2) {
		this.reader = reader;
		this.writer = writer;
		this.clients = clients2;
		clients2.put(username, this);
		start();
	}
	
	@Override
	public void run() {
		try {
			Server.updateUserList();
			while (true) {
				Timestamp time = Timestamp.valueOf(reader.readLine());
				String message = reader.readLine();
				
				if (message == null) {
					throw new IOException("Client Connected");
				}
				
				new HistoryBUS().addToHistory(this.username, time, message);
			}
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public void sendMessage(String message, Timestamp time, int id2) {
		writer.println(message);
		writer.println(time);
		writer.println(id2);
	}

	public void sendOnlineList(String id2) {
		writer.println("ONLINE_USERS:"+id2);
	}
}
