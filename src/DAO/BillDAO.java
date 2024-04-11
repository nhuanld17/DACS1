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

	public int getRoomNumberByID(int id) {
		int roomNumber = 0;
		try {
			ResultSet resultSet = new DBConn().queryDB("SELECT roomNumber FROM hotel.bill WHERE ID = '"+id+"'");
			while (resultSet.next()) {
				roomNumber = resultSet.getInt("roomNumber");
			}
			return roomNumber;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return roomNumber;
	}

	public boolean isRoomReturned(int stt) {
		try {
			ResultSet resultSet = new DBConn().queryDB("SELECT dateReturn FROM hotel.bill where ID = '"+stt+"'");
			Date date = null;
			while (resultSet.next()) {
				date = resultSet.getDate("dateReturn");
			}
			// Nếu ngày trả là null, tức là khách hàng chưa trả phòng
			if (date == null) {
				return false;
			}
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return false;
	}

	public void updateBill(Bill bill) {
		try {
			new DBConn().updateDB("UPDATE hotel.bill "
					+ " SET CCCD = '"+bill.getCccd()+"'"
					+ ", roomNumber = '"+bill.getRoomNumber()+"'"
					+ " WHERE ID = '"+bill.getId()+"'");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public ArrayList<Bill> findBillByCCCD(String cCCD) {
		ArrayList<Bill> bills = new ArrayList<>();
		try {
			String query = "SELECT * FROM hotel.bill WHERE CCCD = '"+cCCD+"'";
			ResultSet resultSet = new DBConn().queryDB(query);
			
			while (resultSet.next()) {
				int id = resultSet.getInt("ID");
				int roomNumber = resultSet.getInt("roomNumber");
				String CCCD = resultSet.getString("CCCD");
				Date dateOrder = resultSet.getDate("dateOrder");
				Date dateReturn = resultSet.getDate("dateReturn");
				double price = resultSet.getDouble("price");
				
				Bill bill = new Bill(id, roomNumber, CCCD, dateOrder, dateReturn, price);
				bills.add(bill);
			}
			return bills;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return bills;
	}
	
}
