package DAO;

import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;

public class HistoryDAO {

	public void addToHistory(String username, Timestamp time, String message) {	
		int id = getIdByUserName(username);
		String query = "INSERT INTO hotel.chat VALUES('"+id+"','"+message+"','"+time+"')";
		try {
			new DBConn().updateDB(query);
			System.out.println("Đã thêm:"+message);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private int getIdByUserName(String username) {
		int id = 0;
		String query = "SELECT id FROM hotel.employee WHERE username = '"+username+"'";
		
		try {
			ResultSet resultSet = new DBConn().queryDB(query);
			
			while (resultSet.next()) {
				id = resultSet.getInt("id");
			}
			return id;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

	public ArrayList<String> getMessage() {
		ArrayList<String> mess = new ArrayList<>();
		String query = "SELECT * FROM hotel.chat";
		
		try {
			ResultSet resultSet = new DBConn().queryDB(query);
			
			while (resultSet.next()) {
				int id = resultSet.getInt("idSender");
				String message = resultSet.getString("message");
				Timestamp time = resultSet.getTimestamp("time");
				String username = getUserNameById(id);
				String str = time + "\n" +username+":"+message;
				
				mess.add(str);
			}
			return mess;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ArrayList<>();
	}
	
	public String getUserNameById(int id) {
		String username = null;
		String query = "SELECT name FROM hotel.employee WHERE id = '"+id+"';";
		try {
			ResultSet resultSet = new DBConn().queryDB(query);
			
			while (resultSet.next()) {
				username = resultSet.getString("name");
			}
			return username;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public String getNameByUserName(String username) {
		String name = null;
		String query = "SELECT name FROM hotel.employee WHERE username = '"+username+"'";
		try {
			ResultSet resultSet = new DBConn().queryDB(query);
			
			while (resultSet.next()) {
				name = resultSet.getString("name");
			}
			return name;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return name;
	}
}
