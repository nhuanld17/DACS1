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
}
