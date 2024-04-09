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
	
	
	public void addEmployee(Employee employee) {
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

	public void deleteEmployee(int id) {
		try {
			new DBConn().updateDB("DELETE FROM employee WHERE id = '"+id+"'");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	public void updateEmployee(Employee newEmployee) {
		try {
			int sexInt = newEmployee.isSex() ? 1 : 0;
			new DBConn().updateDB("UPDATE employee SET name = '"+newEmployee.getName()+"', birthdate = '"+newEmployee.getBirthDate()+"', email = '"+newEmployee.getEmail()+"', sex = '"+sexInt+"', position = '"+newEmployee.getPosition()+"', username = '"+newEmployee.getUserName()+"', password = '"+newEmployee.getPassword()+"' WHERE id = '"+newEmployee.getId()+"';");
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
