package DAO;

import java.sql.Date;
import java.sql.ResultSet;
import java.util.ArrayList;

import DTO.Employee;

public class ManagerDAO {
	public ArrayList<Employee> listEmployees() {
		ArrayList<Employee> employees = new ArrayList<>();
		try {
			ResultSet resultSet = new DBConn().queryDB("SELECT * FROM employee");
			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				String name = resultSet.getString("name");
				Date birthDate = resultSet.getDate("birthDate");
				String email = resultSet.getString("email");
				boolean sex = resultSet.getBoolean("sex");
				String position = resultSet.getString("position");
				
				employees.add(new Employee(id, name, birthDate, email, sex, position));
			}
			return employees;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return new ArrayList<Employee>();
	}
	
	public void addEmployeeWithOutAccount(Employee employee) {
		try {
			int sexInt = employee.isSex() ? 1 : 0;
			String query = "INSERT INTO employee(name,birthdate,email,sex,position)"
					+ " VALUES('"+employee.getName()+"','"+employee.getBirthDate()+"','"+employee.getEmail()+"','"+sexInt+"','"+employee.getPosition()+"')";
			new DBConn().updateDB(query);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void addEmployeeWithAccount(Employee employee) {
		try {
			int sexInt = employee.isSex() ? 1 : 0;
			String query = "INSERT INTO employee(name,birthdate,email,sex,position,username,password)"
					+ " VALUES('"+employee.getName()+"','"+employee.getBirthDate()+"','"+employee.getEmail()+"','"+sexInt+"','"+employee.getPosition()+"','"+employee.getUserName()+"','"+employee.getPassword()+"')";
			new DBConn().updateDB(query);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<String> getListUserName() {
		ArrayList<String> list = new ArrayList<>();
		try {
			ResultSet resultSet = new DBConn().queryDB("SELECT username FROM employee");
			while (resultSet.next()) {
				String userName = resultSet.getString("username");
				list.add(userName);
			}
			return list;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return new ArrayList<>();
	}
}
