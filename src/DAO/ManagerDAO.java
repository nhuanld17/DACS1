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
			e.printStackTrace();
		}
	}


	public ArrayList<Employee> getEmpByNameAndPosition(String name, String position) {
		ArrayList<Employee> employees = new ArrayList<>();
		try {
			String query = "SELECT id, name, birthdate, email, sex, position FROM employee "
					+ " WHERE name LIKE '%"+name+"%' AND position LIKE '%"+position+"%'";
			ResultSet resultSet = new DBConn().queryDB(query);
			
			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				String Name = resultSet.getString("name");
				Date birthdate = resultSet.getDate("birthdate");
				String email = resultSet.getString("email");
				boolean sex = resultSet.getBoolean("sex");
				String Position = resultSet.getString("position");
				
				employees.add(new Employee(id, Name, birthdate, email, sex, Position));
			}
			return employees;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ArrayList<>();
 	}


	public ArrayList<Employee> getEmpByName(String name) {
		ArrayList<Employee> employees = new ArrayList<>();
		try {
			String query = "SELECT id, name, birthdate, email, sex, position FROM employee "
					+ " WHERE name LIKE '%"+name+"%'";
			ResultSet resultSet = new DBConn().queryDB(query);
			
			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				String Name = resultSet.getString("name");
				Date birthdate = resultSet.getDate("birthdate");
				String email = resultSet.getString("email");
				boolean sex = resultSet.getBoolean("sex");
				String Position = resultSet.getString("position");
				
				employees.add(new Employee(id, Name, birthdate, email, sex, Position));
			}
			return employees;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ArrayList<>();
	}


	public ArrayList<Employee> getEmpByPosition(String position) {
		ArrayList<Employee> employees = new ArrayList<>();
		try {
			String query = "SELECT id, name, birthdate, email, sex, position FROM employee "
					+ " WHERE position LIKE '%"+position+"%'";
			ResultSet resultSet = new DBConn().queryDB(query);
			
			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				String Name = resultSet.getString("name");
				Date birthdate = resultSet.getDate("birthdate");
				String email = resultSet.getString("email");
				boolean sex = resultSet.getBoolean("sex");
				String Position = resultSet.getString("position");
				
				employees.add(new Employee(id, Name, birthdate, email, sex, Position));
			}
			return employees;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ArrayList<>();
	}


	public String getEmpNameById(int idEmp) {
		String name = "";
		try {
			String query = "SELECT name FROM employee WHERE id = '"+idEmp+"'";
			ResultSet resultSet = new DBConn().queryDB(query);
			
			while (resultSet.next()) {
				name = resultSet.getString("name");
			}
			return name;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}


	public boolean isValidEmail(String email) {
		ArrayList<String> emails = new ArrayList<>();
		try {
			String query = "SELECT email FROM hotel.employee WHERE username IS NOT NULL";
			ResultSet resultSet = new DBConn().queryDB(query);
			
			while (resultSet.next()) {
				String mail = resultSet.getString("email");
				emails.add(mail);
			}
			
			for (String string : emails) {
				if (email.equals(string)) {
					return true;
				}
			}
			return false;
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return false;
	}
}
