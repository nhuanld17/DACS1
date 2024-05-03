package DAO;

import java.sql.Date;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import DTO.Customer;
import DTO.CustomerServedChart;

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

	public void deleteCustomer(int id) {
		try {
			new DBConn().updateDB("DELETE FROM customer WHERE id = '"+id+"'");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void updateCustomer(Customer customer) {
		try {
			new DBConn().updateDB("UPDATE customer "
					+ " SET name = '"+customer.getName()+"'"
					+ ", cccd = '"+customer.getCCCD()+"'"
					+ ", birthdate = '"+customer.getBirthDate()+"'"
					+ ", idEmp = '"+customer.getIdEmp()+"'"
					+ " WHERE id = '"+customer.getId()+"'");
		} catch (Exception e) {	
			e.printStackTrace();
		}
	}

	public ArrayList<Customer> findCustomerByName(String name) {
		ArrayList<Customer> customers = new ArrayList<>();
		try {
			ResultSet resultSet = new DBConn().queryDB("SELECT * FROM customer WHERE name LIKE '%"+name+"%'");
			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				String Name = resultSet.getString("name");
				String cccd = resultSet.getString("cccd");
				Date birthDate = resultSet.getDate("birthdate");
				
				customers.add(new Customer(id, Name, cccd, birthDate));
			}
			return customers;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return new ArrayList<>();
	}

	public ArrayList<Customer> sortByName() {
		ArrayList<Customer> customers = new ArrayList<>();
		try {
			ResultSet resultSet = new DBConn().queryDB("SELECT * FROM customer ORDER BY name");
			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				String Name = resultSet.getString("name");
				String cccd = resultSet.getString("cccd");
				Date birthDate = resultSet.getDate("birthdate");
				
				customers.add(new Customer(id, Name, cccd, birthDate));
			}
			return customers;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return customers;
	}

	public ArrayList<Customer> sortByDoB() {
		ArrayList<Customer> customers = new ArrayList<>();
		try {
			ResultSet resultSet = new DBConn().queryDB("SELECT * FROM customer ORDER BY birthdate");
			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				String Name = resultSet.getString("name");
				String cccd = resultSet.getString("cccd");
				Date birthDate = resultSet.getDate("birthdate");
				
				customers.add(new Customer(id, Name, cccd, birthDate));
			}
			return customers;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ArrayList<>();
	}

	public String getNameByID(int iD) {
		String name = "";
		try {
			String query = "SELECT name FROM customer WHERE id = '"+iD+"'";
			ResultSet resultSet = new DBConn().queryDB(query);
			
			while (resultSet.next()) {
				name = resultSet.getString("name");
			}
			return name;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		return null;
	}

	public int getTotalCustomerThisDay(int id) {
		int number = 0;
		try {
			Date thisDate = new Date(System.currentTimeMillis());
			System.out.println(thisDate.toString());
			String query = "SELECT COUNT(*)"
					+ " FROM hotel.bill INNER JOIN hotel.customer"
					+ " ON bill.ID = customer.id"
					+ " WHERE date(bill.dateOrder) = '"+thisDate+"' AND customer.IdEmp = '"+id+"';";
			ResultSet resultSet = new DBConn().queryDB(query);
			
			while (resultSet.next()) {
				number = resultSet.getInt("COUNT(*)");
			}
			
			return number;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	// Khá là cồng kềnh :VVV
	public ArrayList<CustomerServedChart> getDailyCustomerServed(LocalDate[] date, int idEmp) {
		ArrayList<CustomerServedChart> list = new ArrayList<>();
		try {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//			String query = "SELECT COUNT(*), date(bill.dateOrder)"
//					+ " FROM hotel.bill"
//					+ " INNER JOIN hotel.customer ON bill.ID = customer.id"
//					+ " INNER JOIN hotel.employee ON customer.idEmp = employee.id"
//					+ " WHERE date(bill.dateOrder) BETWEEN '"+date[0].format(formatter)+"' AND '"+date[6].format(formatter)+"' AND idEmp = '"+idEmp+"'"
//					+ " GROUP BY date(bill.dateOrder);";
//			
//			ResultSet resultSet = new DBConn().queryDB(query);
//			
//			while (resultSet.next()) {
//				Date date1 = resultSet.getDate("date(bill.dateOrder)");
//				int total = resultSet.getInt("COUNT(*)");
//				
//				list.add(new CustomerServedChart(date1, total));
//			}
			
			String query1 = "SELECT COUNT(*), date(bill.dateOrder)"
					+ " FROM hotel.bill"
					+ " INNER JOIN hotel.customer ON bill.ID = customer.id"
					+ " INNER JOIN hotel.employee ON customer.idEmp = employee.id"
					+ " WHERE date(bill.dateOrder) = '"+date[0].format(formatter)+"' AND idEmp = '"+idEmp+"'"
					+ " GROUP BY date(bill.dateOrder);";
			
			String query2 = "SELECT COUNT(*), date(bill.dateOrder)"
					+ " FROM hotel.bill"
					+ " INNER JOIN hotel.customer ON bill.ID = customer.id"
					+ " INNER JOIN hotel.employee ON customer.idEmp = employee.id"
					+ " WHERE date(bill.dateOrder) = '"+date[1].format(formatter)+"' AND idEmp = '"+idEmp+"'"
					+ " GROUP BY date(bill.dateOrder);";
			
			String query3 = "SELECT COUNT(*), date(bill.dateOrder)"
					+ " FROM hotel.bill"
					+ " INNER JOIN hotel.customer ON bill.ID = customer.id"
					+ " INNER JOIN hotel.employee ON customer.idEmp = employee.id"
					+ " WHERE date(bill.dateOrder) = '"+date[2].format(formatter)+"' AND idEmp = '"+idEmp+"'"
					+ " GROUP BY date(bill.dateOrder);";
			
			String query4 = "SELECT COUNT(*), date(bill.dateOrder)"
					+ " FROM hotel.bill"
					+ " INNER JOIN hotel.customer ON bill.ID = customer.id"
					+ " INNER JOIN hotel.employee ON customer.idEmp = employee.id"
					+ " WHERE date(bill.dateOrder) = '"+date[3].format(formatter)+"' AND idEmp = '"+idEmp+"'"
					+ " GROUP BY date(bill.dateOrder);";
			
			String query5 = "SELECT COUNT(*), date(bill.dateOrder)"
					+ " FROM hotel.bill"
					+ " INNER JOIN hotel.customer ON bill.ID = customer.id"
					+ " INNER JOIN hotel.employee ON customer.idEmp = employee.id"
					+ " WHERE date(bill.dateOrder) = '"+date[4].format(formatter)+"' AND idEmp = '"+idEmp+"'"
					+ " GROUP BY date(bill.dateOrder);";
			
			String query6 = "SELECT COUNT(*), date(bill.dateOrder)"
					+ " FROM hotel.bill"
					+ " INNER JOIN hotel.customer ON bill.ID = customer.id"
					+ " INNER JOIN hotel.employee ON customer.idEmp = employee.id"
					+ " WHERE date(bill.dateOrder) = '"+date[5].format(formatter)+"' AND idEmp = '"+idEmp+"'"
					+ " GROUP BY date(bill.dateOrder);";
			
			String query7 = "SELECT COUNT(*), date(bill.dateOrder)"
					+ " FROM hotel.bill"
					+ " INNER JOIN hotel.customer ON bill.ID = customer.id"
					+ " INNER JOIN hotel.employee ON customer.idEmp = employee.id"
					+ " WHERE date(bill.dateOrder) = '"+date[6].format(formatter)+"' AND idEmp = '"+idEmp+"'"
					+ " GROUP BY date(bill.dateOrder);";
			
			Date datee = null;
			int total = 0;
			ResultSet resultSet = new DBConn().queryDB(query1);
			if (!resultSet.next()) {
				datee = Date.valueOf(date[0]);
				total = 0;
			}else {
				datee = resultSet.getDate("date(bill.dateOrder)");
				total = resultSet.getInt("COUNT(*)");
			}
			list.add(new CustomerServedChart(datee, total));
			
			resultSet = new DBConn().queryDB(query2);
			if (!resultSet.next()) {
				datee = Date.valueOf(date[1]);
				total = 0;
			}else {
				datee = resultSet.getDate("date(bill.dateOrder)");
				total = resultSet.getInt("COUNT(*)");
			}
			list.add(new CustomerServedChart(datee, total));
			
			resultSet = new DBConn().queryDB(query3);
			if (!resultSet.next()) {
				datee = Date.valueOf(date[2]);
				total = 0;
			}else {
				datee = resultSet.getDate("date(bill.dateOrder)");
				total = resultSet.getInt("COUNT(*)");
			}
			list.add(new CustomerServedChart(datee, total));
			
			resultSet = new DBConn().queryDB(query4);
			if (!resultSet.next()) {
				datee = Date.valueOf(date[3]);
				total = 0;
			}else {
				datee = resultSet.getDate("date(bill.dateOrder)");
				total = resultSet.getInt("COUNT(*)");
			}
			list.add(new CustomerServedChart(datee, total));
			
			resultSet = new DBConn().queryDB(query5);
			if (!resultSet.next()) {
				datee = Date.valueOf(date[4]);
				total = 0;
			}else {
				datee = resultSet.getDate("date(bill.dateOrder)");
				total = resultSet.getInt("COUNT(*)");
			}
			list.add(new CustomerServedChart(datee, total));
			
			resultSet = new DBConn().queryDB(query6);
			if (!resultSet.next()) {
				datee = Date.valueOf(date[5]);
				total = 0;
			}else {
				datee = resultSet.getDate("date(bill.dateOrder)");
				total = resultSet.getInt("COUNT(*)");
			}
			list.add(new CustomerServedChart(datee, total));
			
			resultSet = new DBConn().queryDB(query7);
			if (!resultSet.next()) {
				datee = Date.valueOf(date[6]);
				total = 0;
			}else {
				datee = resultSet.getDate("date(bill.dateOrder)");
				total = resultSet.getInt("COUNT(*)");
			}
			list.add(new CustomerServedChart(datee, total));
			
			
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ArrayList<>();
	}
}