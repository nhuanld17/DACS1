package DAO;

import java.sql.Date;
import java.sql.ResultSet;
import java.util.ArrayList;

import DTO.Bill;

public class BillDAO {

	public void addBill(Bill bill) {
		try {
			new DBConn().updateDB("INSERT INTO bill(roomNumber,CCCD,dateOrder)"
					+ " VALUES('"+bill.getRoomNumber()+"','"+bill.getCccd()+"','"+bill.getDateOrder()+"');");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public ArrayList<Bill> getListBill() {
		ArrayList<Bill> bills = new ArrayList<>();
		try {
			ResultSet resultSet = new DBConn().queryDB("SELECT * FROM bill");
			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				int roomNumber = resultSet.getInt("roomNumber");
				String CCCD = resultSet.getString("CCCD");
				Date dateOrder = resultSet.getDate("dateOrder");
				Date dateReturn = resultSet.getDate("dateReturn");
				double price = resultSet.getDouble("price");
				
				bills.add(new Bill(id, roomNumber, CCCD, dateOrder, dateReturn, price));
			}
			return bills;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ArrayList<>();
	}
	
}
