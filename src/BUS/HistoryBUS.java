package BUS;

import java.sql.Timestamp;
import java.util.ArrayList;

import DAO.HistoryDAO;

public class HistoryBUS {

	public void addToHistory(String username, Timestamp time, String message) {
		new HistoryDAO().addToHistory(username, time, message);
	}

	public String getNameByUserName(String username) {
		return new HistoryDAO().getNameByUserName(username);
	}

	public ArrayList<String> getMessages() {
		return new HistoryDAO().getMessage();
	}

}
