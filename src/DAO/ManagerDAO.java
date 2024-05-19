package DAO;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Timestamp;
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


	public void changPassWord(String password, String email, int id) {
		try {
			String query = "UPDATE employee SET password = '"+password+"' WHERE email = '"+email+"' AND id = '"+id+"'";
			new DBConn().updateDB(query);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}


	public boolean isValidEmail(String email, int id) {
		try {
			String Email = null;
			String query = "SELECT email FROM hotel.employee WHERE id = '"+id+"'";
			ResultSet resultSet = new DBConn().queryDB(query);
			
			while (resultSet.next()) {
				Email = resultSet.getString("email");
			}
			
			if (Email == null || !Email.equals(email)) {
				return false;
			}
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
		return false;
	}

	public Employee getEmployeeById(int idEmp) {
		Employee employee = null;
		String query = "SELECT * FROM hotel.employee WHERE id = '"+idEmp+"'";
		try {
			ResultSet resultSet = new DBConn().queryDB(query);
			
			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				String name = resultSet.getString("name");
				Date birthdate = resultSet.getDate("birthdate");
				String email = resultSet.getString("email");
				boolean sex = resultSet.getBoolean("sex");
				String position = resultSet.getString("position");
				String username = resultSet.getString("username");
				String password = resultSet.getString("password");
				
				employee = new Employee(id, name, birthdate, email, sex, position, username, password);
			}
			return employee;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}


	public void changeEmployeeInfo(int idEmp, String name, Date birthdate, String email, boolean sex, String username, String password) {
		int sexInt = sex ? 1 : 0;
		String query = "UPDATE hotel.employee"
				+ " SET name = '"+name+"', birthdate = '"+birthdate+"', email = '"+email+"', sex = '"+sexInt+"', username = '"+username+"', password = '"+password+"'"
				+ " WHERE id = '"+idEmp+"'";
		
		try {
			new DBConn().updateDB(query);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	public String getEmpNameByUserName(String senderUserName) {
		String name = null;
		String query = "SELECT name FROM hotel.employee WHERE username = '"+senderUserName+"'";
		try {
			ResultSet resultSet = new DBConn().queryDB(query);
			
			while (resultSet.next()) {
				name = resultSet.getString("name");
			}
			return name;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}


	public ArrayList<Object[]> getEmpSalary(int month, int year) {
		ArrayList<Object[]> result = new ArrayList<>();
		String query = "SELECT e.id AS employee_id, e.name AS employee_name, e.position, COUNT(b.id) AS customer_count\r\n"
				+ "FROM employee e\r\n"
				+ "LEFT JOIN customer c ON e.id = c.idEmp\r\n"
				+ "LEFT JOIN bill b ON c.id = b.id AND MONTH(b.dateOrder) = '"+month+"' AND YEAR(b.dateOrder) = '"+year+"'\r\n"
				+ "GROUP BY e.id, e.name, e.position\r\n"
				+ "ORDER BY customer_count DESC;";
		
		ResultSet resultSet;
		try {
			resultSet = new DBConn().queryDB(query);
			
			while (resultSet.next()) {
				int id = resultSet.getInt("employee_id");
				String name = resultSet.getString("employee_name");
				String position = resultSet.getString("position");
				int customer_count = resultSet.getInt("customer_count");
				
				Object[] object = new Object[] {id, name, position, customer_count};
				result.add(object);
			}
			
			return result;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ArrayList<>();
	}


	public int getMaleNumber() {
		int number = 0;
		String query = "SELECT COUNT(*) FROM hotel.employee WHERE sex = 1;";
		ResultSet resultSet;
		try {
			resultSet = new DBConn().queryDB(query);
			while (resultSet.next()) {
				number = resultSet.getInt("COUNT(*)");
			}
			
			return number;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return number;
	}


	public int getFemaleNumber() {
		int number = 0;
		String query = "SELECT COUNT(*) FROM hotel.employee WHERE sex = 0;";
		ResultSet resultSet;
		try {
			resultSet = new DBConn().queryDB(query);
			while (resultSet.next()) {
				number = resultSet.getInt("COUNT(*)");
			}
			
			return number;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return number;
	}


	public int getTiepTanNumber() {
		int number = 0;
		String query = "SELECT COUNT(*) FROM hotel.employee WHERE position = 'Tiếp tân'";
		try {
			ResultSet resultSet = new DBConn().queryDB(query);
			while (resultSet.next()) {
				number = resultSet.getInt("COUNT(*)");
			}
			return number;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return number;
	}


	public int getBaoVeNumber() {
		int number = 0;
		String query = "SELECT COUNT(*) FROM hotel.employee WHERE position = 'Bảo vệ'";
		try {
			ResultSet resultSet = new DBConn().queryDB(query);
			while (resultSet.next()) {
				number = resultSet.getInt("COUNT(*)");
			}
			return number;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return number;
	}


	public int getLaoCongNumber() {
		int number = 0;
		String query = "SELECT COUNT(*) FROM hotel.employee WHERE position = 'Lao công'";
		try {
			ResultSet resultSet = new DBConn().queryDB(query);
			while (resultSet.next()) {
				number = resultSet.getInt("COUNT(*)");
			}
			return number;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return number;
	}


	public ArrayList<Object[]> getEmpEachPosition(String position) {
		ArrayList<Object[]> result = new ArrayList<>();
		String query = "SELECT e.id, e.name, e.position, COUNT(DISTINCT c.id) AS customer_count"
				+ " FROM employee e"
				+ " LEFT JOIN customer c ON e.id = c.idEmp\r\n"
				+ " LEFT JOIN bill b ON c.id = b.ID AND MONTH(b.dateOrder) = MONTH(CURRENT_DATE()) AND YEAR(b.dateOrder) = YEAR(CURRENT_DATE())"
				+ " WHERE e.position = '"+position+"'"
				+ " GROUP BY e.id, e.name, e.position"
				+ " ORDER BY customer_count DESC;";
		
		try {
			ResultSet resultSet = new DBConn().queryDB(query);
			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				String name = resultSet.getString("name");
				String Position = resultSet.getString("position");
				int customer_count = resultSet.getInt("customer_count");
				
				result.add(new Object[] {id, name, Position, customer_count});
			}
			
			return result;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}


	public ArrayList<Object[]> getRowByTime(int month, int year) {
		ArrayList<Object[]> row = new ArrayList<>();
		String query = "SELECT e.id AS employee_id, e.name AS employee_name, e.position, COUNT(b.id) AS customer_count\r\n"
				+ "FROM employee e\r\n"
				+ "LEFT JOIN customer c ON e.id = c.idEmp\r\n"
				+ "LEFT JOIN bill b ON c.id = b.id AND MONTH(b.dateOrder) = '"+month+"' AND YEAR(b.dateOrder) = '"+year+"'\r\n"
				+ "GROUP BY e.id, e.name, e.position\r\n"
				+ "ORDER BY customer_count DESC;";
		try {
			ResultSet resultSet = new DBConn().queryDB(query);
			while (resultSet.next()) {
				int id = resultSet.getInt("employee_id");
				String name = resultSet.getString("employee_name");
				String position = resultSet.getString("position");
				int customer_count = resultSet.getInt("customer_count");
				
				row.add(new Object[] {id, name, position, customer_count});
			}
			return row;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return row;
	}
	
}
