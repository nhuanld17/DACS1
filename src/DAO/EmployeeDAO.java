package DAO;

import java.sql.Date;
import java.sql.ResultSet;
import java.util.ArrayList;

import DTO.Customer;

public class EmployeeDAO {

	public ArrayList<Customer> getListCustomer() {
		ArrayList<Customer> customers = new ArrayList<>();
		try {
			ResultSet resultSet = new DBConn().queryDB("SELECT id, name, cccd, birthdate FROM customer");
			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				String name = resultSet.getString("name");
				String cccd = resultSet.getString("cccd");
				Date birthDate = resultSet.getDate("birthdate");

				customers.add(new Customer(id, name, cccd, birthDate));
			}
			return customers;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ArrayList<>();
	}

	public boolean isDuplicateCCCD(String cccd) {
		try {
			ArrayList<String> strings = new ArrayList<>();
			ResultSet resultSet = new DBConn().queryDB("SELECT cccd FROM customer");
			while (resultSet.next()) {
				String CCCD = resultSet.getString("cccd");
				strings.add(CCCD);
			}
			if (strings.contains(cccd)) {
				return true;
			}
			return false;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean isDuplicateName(String name, String cccd) {
		try {
			String Name = null;
			ResultSet resultSet = new DBConn().queryDB("SELECT name "
					+ "	FROM customer WHERE cccd = '"+cccd+"'");
			while (resultSet.next()) {
				Name = resultSet.getString("name");
			}
			if (!Name.equals(name)) {
				return false;
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean isDuplicateBirthDate(String birthDateText, String cccd) {
		try {
			Date birthDate = null;
			ResultSet resultSet = new DBConn().queryDB("SELECT birthdate "
					+ " FROM customer WHERE cccd = '"+cccd+"'");
			while (resultSet.next()) {
				birthDate = resultSet.getDate("birthdate");
			}
			if (!birthDate.toString().equals(birthDateText)) {
				return false;
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public void addCustomer(Customer customer) {
		try {
			int res = new DBConn().updateDB("INSERT INTO customer(name,cccd,birthdate,idEmp) "
					+ " VALUES('"+customer.getName()+"','"+customer.getCCCD()+"','"+customer.getBirthDate()+"','"+customer.getIdEmp()+"');");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}