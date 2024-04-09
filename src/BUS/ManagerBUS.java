package BUS;

import java.util.ArrayList;

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

}
