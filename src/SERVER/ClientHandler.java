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
//		this.clients = clients;
		clients.put(this.username, this);
		this.clients = clients;
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
				
//				switch (message) {
//				case "THÊM":
//					Server.broadCastAction("SYSTEM_ADD_A_CUSTOMER", this);
//					break;
//				case "XÓA":
//					Server.broadCastAction("SYSTEM_DELETE_A_CUSTOMER", this);
//					break;
//				case "CẬP NHẬT":
//					Server.broadCastAction("SYSTEM_UPDATE_A_CUSTOMER", this);
//					break;
//				case "THANH TOÁN":
//					Server.broadCastAction("SYSTEM_ABATE_A_BILL", this);
//					break;
//				case "GỬI":
//					Timestamp time = Timestamp.valueOf(reader.readLine());
//					String textChat = reader.readLine();
//					
//					new HistoryBUS().addToHistory(this.username, time, textChat);
//					String name = new HistoryBUS().getNameByUserName(this.username);
//					
//					String mess = time + "\n" + username + ": "+textChat;
//					System.out.println(mess);
//					Server.broadCastMessage(time, textChat, username, this);
//					break;
//				case "UPDATE_EMP_INFO":
//					String newUserName = reader.readLine();
//					String oldUserName = this.username;
//					
//					if (this.clients.containsKey(oldUserName)) {
//						ClientHandler clientHandler = this.clients.get(oldUserName);
//						this.clients.remove(oldUserName);
//						Server.clients.remove(oldUserName);
//						this.username = newUserName;
//						System.out.println("Đã đổi từ "+oldUserName +" thành "+newUserName);
//						this.clients.put(this.username, clientHandler);
//						Server.clients.put(this.username, clientHandler);
//					}
//					
//					Server.updateUserList();
//					break;
//				default:
//					break;
//				}
				
				if ("THÊM".equals(message)) {
				    Server.broadCastAction("SYSTEM_ADD_A_CUSTOMER", this);
				} else if ("XÓA".equals(message)) {
				    Server.broadCastAction("SYSTEM_DELETE_A_CUSTOMER", this);
				} else if ("CẬP NHẬT".equals(message)) {
				    Server.broadCastAction("SYSTEM_UPDATE_A_CUSTOMER", this);
				} else if ("THANH TOÁN".equals(message)) {
				    Server.broadCastAction("SYSTEM_ABATE_A_BILL", this);
				} else if ("GỬI".equals(message)) {
				    Timestamp time = Timestamp.valueOf(reader.readLine());
				    String textChat = reader.readLine();

				    new HistoryBUS().addToHistory(this.username, time, textChat);
				    String name = new HistoryBUS().getNameByUserName(this.username);

				    String mess = time + "\n" + username + ": " + textChat;
				    System.out.println(mess);
				    Server.broadCastMessage(time, textChat, username, this);
				} else if ("UPDATE_EMP_INFO".equals(message)) {
				    String newUserName = reader.readLine();
				    String oldUserName = this.username;

				    if (this.clients.containsKey(oldUserName)) {
				        ClientHandler clientHandler = this.clients.get(oldUserName);
				        this.clients.remove(oldUserName);
				        Server.clients.remove(oldUserName);
				        this.username = newUserName;
				        System.out.println("Đã đổi từ " + oldUserName + " thành " + newUserName);
				        this.clients.put(this.username, clientHandler);
				        Server.clients.put(this.username, clientHandler);
				    }
				    Server.broadCastAction("UPDATE_EMP_INFO", this);
				    Server.updateUserList();
				} else if (message.startsWith("DELETE_EMP_")) {
					int id = Integer.valueOf(message.substring(11));
					Server.broadCastAction("SYSTEM_DELETE_EMP_"+id, this);
				}
				
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

	public void sendMessage(String message, Timestamp time, String username2) {
		writer.println("NEW_MESSAGE");
		writer.println(time);
		writer.println(message);
		writer.println(username2);
	}

	public void sendOnlineList(String id2) {
		writer.println("ONLINE_USERS:"+id2);
	}

	public void sendAction(String message) {
		writer.println(message);
	}
}
