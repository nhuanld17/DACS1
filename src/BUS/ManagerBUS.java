package BUS;

import java.util.ArrayList;

import DAO.ManagerDAO;
import DTO.Employee;

public class ManagerBUS {
	public ArrayList<Employee> listEmployees() {
		return new ManagerDAO().listEmployees();
	}
	public void addEmployeeWithAccount(Employee employee) {
		new ManagerDAO().addEmployeeWithAccount(employee);
	}
	public void addEmployeeWithOutAccount(Employee employee) {
		new ManagerDAO().addEmployeeWithOutAccount(employee);
	}
	public ArrayList<String> getListUserName() {
		return new ManagerDAO().getListUserName();
	}
}
