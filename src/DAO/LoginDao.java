package DAO;

import java.sql.ResultSet;

import DTO.Manager;

public class LoginDao {
	public boolean isValidManager(Manager manager) {
		try {
			ResultSet resultSet = new DBConn().queryDB("SELECT * FROM manager WHERE username = '"+manager.getUserName()+"'");
			
			if (resultSet.next()) {
				String passWord = resultSet.getString("password");
				if (manager.getPassWord().equals(passWord)) {
					return true;
				}else {
					return false;
				}
			}else {
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
}
