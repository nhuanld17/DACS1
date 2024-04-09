package DAO;

import java.sql.ResultSet;
import java.util.ArrayList;

import DTO.Account;
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
	
	public ArrayList<Account> getListAccount() {
		ArrayList<Account> accounts = new ArrayList<>();
		try {
			ResultSet resultSet = new DBConn().queryDB("SELECT username, password FROM employee WHERE username IS NOT NULL AND password IS NOT NULL;");
			while (resultSet.next()) {
				String userName = resultSet.getString("username");
				String password = resultSet.getString("password");
				
				Account account = new Account(userName, password);
				accounts.add(account);
			}
			
			return accounts;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return new ArrayList<>();
	}
	
	public boolean isAccountExist(Account account) {
		ArrayList<Account> accounts = getListAccount();
		for (Account anAccount : accounts) {
			if (account.getUserName().equals(anAccount.getUserName()) && account.getPassWord().equals(anAccount.getPassWord())) {
				return true;
			}
		}
		return false;
	}
}
