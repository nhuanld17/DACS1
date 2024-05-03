package BUS;

import java.util.ArrayList;

import DAO.EmployeeDAO;
import DTO.Customer;

public class EmployeeBUS {
	public ArrayList<Customer> getListCustomer() {
		return new EmployeeDAO().getListCustomer();
	}

	public boolean isDuplicateCCCD(String cccd) {
		return new EmployeeDAO().isDuplicateCCCD(cccd);
	}

	public boolean isDuplicateName(String name, String cccd) {
		return new EmployeeDAO().isDuplicateName(name, cccd);
	}

	public boolean isDuplicateBirthDate(String birthDateText, String cccd) {
		return new EmployeeDAO().isDuplicateBirthDate(birthDateText, cccd);
	}

	public void addCustomer(Customer customer) {
		new EmployeeDAO().addCustomer(customer);
	}

	public void deleteCustomer(int id) {
		new EmployeeDAO().deleteCustomer(id);
	}

	public void updateCustomer(Customer customer) {
		new EmployeeDAO().updateCustomer(customer);
	}

	public ArrayList<Customer> findCustomerByName(String name) {
		return new EmployeeDAO().findCustomerByName(name);
	}

	public ArrayList<Customer> sortByName() {
		return new EmployeeDAO().sortByName();
	}

	public ArrayList<Customer> sortByDoB() {
		return new EmployeeDAO().sortByDoB();
	}

	public String getNameByID(int iD) {
		return new EmployeeDAO().getNameByID(iD);
	}

	public int getTotalCustomerThisDay(int id) {
		return new EmployeeDAO().getTotalCustomerThisDay(id);
	}
}
