package BUS;

import java.sql.Date;
import java.util.ArrayList;

import DAO.EmployeeDAO;
import DAO.ManagerDAO;
import DTO.Employee;

public class ManagerBUS {
	public ArrayList<Employee> listEmployees() {
		return new ManagerDAO().listEmployees();
	}
	
	public void addEmployee(Employee employee) {
		new ManagerDAO().addEmployee(employee);
	}

	public ArrayList<String> getListUserName() {
		return new ManagerDAO().getListUserName();
	}
	public void deleteEmployee(int id) {
		new ManagerDAO().deleteEmployee(id);
	}

	public void updateEmployee(Employee newEmployee) {
		new ManagerDAO().updateEmployee(newEmployee);
	}

	public ArrayList<Employee> getEmpByNameAndPosition(String name, String position) {
		return new ManagerDAO().getEmpByNameAndPosition(name, position);
	}

	public ArrayList<Employee> getEmpByName(String name) {
		return new ManagerDAO().getEmpByName(name);
	}

	public ArrayList<Employee> getEmpByPosition(String position) {
		return new ManagerDAO().getEmpByPosition(position);
	}

	public String getEmpNameById(int idEmp) {
		return new ManagerDAO().getEmpNameById(idEmp);
	}


	public void changePassWord(String password, String email, int id) {
		new ManagerDAO().changPassWord(password, email, id);
	}

	public boolean isValidEmail(String email, int id) {
		return new ManagerDAO().isValidEmail(email, id);
	}

	public Employee getEmployeeById(int idEmp) {
		return new ManagerDAO().getEmployeeById(idEmp);
	}
	
	// Lưu thay đổi thông tin nhân viên
	public void changeEmployeeInfo(int idEmp, String name, Date birthdate, String email, boolean sex, String username, String password) {
		new ManagerDAO().changeEmployeeInfo(idEmp,name, birthdate, email,sex, username, password);
	}

	public String getEmpNameByUserName(String senderUserName) {
		return new ManagerDAO().getEmpNameByUserName(senderUserName);
	}

}
