package BUS;

import java.sql.Timestamp;

import DAO.HistoryDAO;

public class HistoryBUS {

	public void addToHistory(String username, Timestamp time, String message) {
		new HistoryDAO().addToHistory(username, time, message);
	}

	public String getNameByUserName(String username) {
		return new HistoryDAO().getNameByUserName(username);
	}

}
