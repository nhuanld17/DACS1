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
	
	public ClientHandler(String username, BufferedReader reader, PrintWriter writer, HashMap<String, ClientHandler> clients) {
		this.username = username;
		this.reader = reader;
		System.out.println(username+" here");
		this.writer = writer;
		this.clients = clients;
		clients.put(username, this);
		start();
	}
	
	@Override
	public void run() {
		try {
			Server.updateUserList();
			while (true) {

				String message = reader.readLine();
//				Timestamp time = Timestamp.valueOf(reader.readLine());
				
				if (message == null) {
					throw new IOException("Client disconnected");
				}
				

				
				switch (message) {
				case "THÊM":
					Server.broadCastAction("SYSTEM_ADD_A_CUSTOMER", this);
					break;
				case "XÓA":
					Server.broadCastAction("SYSTEM_DELETE_A_CUSTOMER", this);
					break;
				case "CẬP NHẬT":
					Server.broadCastAction("SYSTEM_UPDATE_A_CUSTOMER", this);
					break;
				case "THANH TOÁN":
					Server.broadCastAction("SYSTEM_ABATE_A_BILL", this);
					break;

				default:
					break;
				}
				
//				new HistoryBUS().addToHistory(this.username, time, message);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
	        clients.remove(username);
	        System.out.println("Đã remove "+ username);
	        Server.updateUserList();
	        try {
	            reader.close();
	            writer.close();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
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

	public void sendAction(String message) {
		writer.println(message);
	}
}
