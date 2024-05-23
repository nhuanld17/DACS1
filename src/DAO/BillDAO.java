package DAO;


import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import DTO.Bill;
import DTO.CustomerServedChart;

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
				Timestamp dateOrder = resultSet.getTimestamp("dateOrder");
				Timestamp dateReturn = resultSet.getTimestamp("dateReturn");
				long price = resultSet.getLong("price");
				if (resultSet.wasNull()) {
				    price = -1; // Hoặc bất kỳ giá trị mặc định nào bạn muốn gán khi có NULL
				}
				
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
				Timestamp dateOrder = resultSet.getTimestamp("dateOrder");
				Timestamp dateReturn = resultSet.getTimestamp("dateReturn");
				long price = resultSet.getLong("price");
				
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

	public boolean isBillAbated(int id) {
		try {
			String query = "SELECT dateReturn FROM bill WHERE id = '"+id+"'";
			ResultSet resultSet = new DBConn().queryDB(query);
			
			Date returnDate = null;
			while (resultSet.next()) {
				returnDate = resultSet.getDate("dateReturn");
			}
			if (returnDate == null) {
				return false;
			}
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return false;
	}

	public ArrayList<Bill> getListNotAbatedBill() {
		ArrayList<Bill> bills = new ArrayList<>();
		try {
			String query = "SELECT * FROM bill WHERE dateReturn IS NULL";
			ResultSet resultSet = new DBConn().queryDB(query);
			
			while (resultSet.next()) {
				int ID = resultSet.getInt("ID");
				int roomNumber = resultSet.getInt("roomNumber");
				String CCCD = resultSet.getString("CCCD");
				Timestamp dateOrder = resultSet.getTimestamp("dateOrder");
				Timestamp dateReturn = resultSet.getTimestamp("dateReturn");
				long price = resultSet.getLong("price");
				
				bills.add(new Bill(ID, roomNumber, CCCD, dateOrder, dateReturn, price));
			}
			return bills;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return new ArrayList<>();
	}

	public void abateBill(int id, long price, Timestamp dateReturn) {
		try {
			String query = "UPDATE bill SET dateReturn = '"+dateReturn+"', price='"+price+"' WHERE ID = '"+id+"'";
			new DBConn().updateDB(query);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}

	public int getTotalBookingByDate(String formattedDate) {
		int number = 0;
		String query = "SELECT COUNT(*) FROM hotel.bill WHERE date(dateOrder) = '"+formattedDate+"'";
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

	public ArrayList<Integer> getDailyTotalBooking(LocalDate[] date) {
		ArrayList<Integer> list = new ArrayList<>();
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		
		try {
			String query1 = "SELECT COUNT(*) FROM hotel.bill WHERE date(dateOrder) = '"+date[0].format(formatter)+"'";
			String query2 = "SELECT COUNT(*) FROM hotel.bill WHERE date(dateOrder) = '"+date[1].format(formatter)+"'";
			String query3 = "SELECT COUNT(*) FROM hotel.bill WHERE date(dateOrder) = '"+date[2].format(formatter)+"'";
			String query4 = "SELECT COUNT(*) FROM hotel.bill WHERE date(dateOrder) = '"+date[3].format(formatter)+"'";
			String query5 = "SELECT COUNT(*) FROM hotel.bill WHERE date(dateOrder) = '"+date[4].format(formatter)+"'";
			String query6 = "SELECT COUNT(*) FROM hotel.bill WHERE date(dateOrder) = '"+date[5].format(formatter)+"'";
			String query7 = "SELECT COUNT(*) FROM hotel.bill WHERE date(dateOrder) = '"+date[6].format(formatter)+"'";
			
			int total = 0;
			ResultSet resultSet = new DBConn().queryDB(query1);
			if (!resultSet.next()) {
				total = 0;
			}else {
				total = resultSet.getInt("COUNT(*)");
			}
			list.add(total);
			
			resultSet = new DBConn().queryDB(query2);
			if (!resultSet.next()) {
				total = 0;
			}else {
				total = resultSet.getInt("COUNT(*)");
			}
			list.add(total);
			
			resultSet = new DBConn().queryDB(query3);
			if (!resultSet.next()) {
				total = 0;
			}else {
				total = resultSet.getInt("COUNT(*)");
			}
			list.add(total);
			
			resultSet = new DBConn().queryDB(query4);
			if (!resultSet.next()) {
				total = 0;
			}else {
				total = resultSet.getInt("COUNT(*)");
			}
			list.add(total);
			
			resultSet = new DBConn().queryDB(query5);
			if (!resultSet.next()) {
				total = 0;
			}else {
				total = resultSet.getInt("COUNT(*)");
			}
			list.add(total);
			
			resultSet = new DBConn().queryDB(query6);
			if (!resultSet.next()) {
				total = 0;
			}else {
				total = resultSet.getInt("COUNT(*)");
			}
			list.add(total);
			
			resultSet = new DBConn().queryDB(query7);
			if (!resultSet.next()) {
				total = 0;
			}else {
				total = resultSet.getInt("COUNT(*)");
			}
			list.add(total);
			
			
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}

	public double getCurrentRevenue() {
		LocalDate today = LocalDate.now();
		double result = 0;
		String formatedDate = today.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		String query = "SELECT SUM(price) FROM hotel.bill WHERE date(dateReturn) = '"+formatedDate+"'";
		try {
			ResultSet resultSet = new DBConn().queryDB(query);
			while (resultSet.next()) {
				result = resultSet.getDouble("SUM(price)");
			}
			return result;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	public int getNumberOfCurrentUsers() {
		int res = 0;
		LocalDate today = LocalDate.now();
		String formatedDate = today.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		String query = "SELECT COUNT(*) FROM hotel.bill WHERE date(dateOrder) = '"+formatedDate+"'";
		try {
			ResultSet resultSet = new DBConn().queryDB(query);
			while (resultSet.next()) {
				res = resultSet.getInt("COUNT(*)");
			}
			return res;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return res;
	}

	public int getCurrentNumberOfBillAbated() {
		int res = 0;
		LocalDate today = LocalDate.now();
		String formatedDate = today.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		
		String query = "SELECT COUNT(*) FROM hotel.bill WHERE date(dateReturn) = '"+formatedDate+"'";
		try {
			ResultSet resultSet = new DBConn().queryDB(query);
			while (resultSet.next()) {
				res = resultSet.getInt("COUNT(*)");
			}
			return res;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return res;
	}

	public double getYesterdayRevenue() {
		double res = 0;
		LocalDate today = LocalDate.now();
		// Lấy ngày hôm qua
        LocalDate yesterday = today.minusDays(1);
        String formatedDate = yesterday.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        
        String query = "SELECT SUM(price) FROM hotel.bill WHERE date(dateReturn) = '"+formatedDate+"'";
        try {
			ResultSet resultSet = new DBConn().queryDB(query);
			while (resultSet.next()) {
				res = resultSet.getDouble("SUM(price)");
			}
			return res;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return res;
	}

	public int getTotalUserYesterday() {
		int res = 0;
		LocalDate today = LocalDate.now();
		// Lấy ngày hôm qua
        LocalDate yesterday = today.minusDays(1);
        String formatedDate = yesterday.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        
        String query = "SELECT COUNT(*) FROM hotel.bill WHERE date(dateOrder) = '"+formatedDate+"'";
        try {
			ResultSet resultSet = new DBConn().queryDB(query);
			while (resultSet.next()) {
				res = resultSet.getInt("COUNT(*)");
			}
			return res;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return res;
	}

	public int getYesterdayBillAbated() {
		int res = 0;
		LocalDate today = LocalDate.now();
		// Lấy ngày hôm qua
        LocalDate yesterday = today.minusDays(1);
        String formatedDate = yesterday.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        
        String query = "SELECT COUNT(*) FROM hotel.bill WHERE date(dateReturn) = '"+formatedDate+"'";
        try {
			ResultSet resultSet = new DBConn().queryDB(query);
			while (resultSet.next()) {
				res = resultSet.getInt("COUNT(*)");
			}
			return res;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return res;
	}

	public double[] getRevenueEachMonth() {
		double[] revenues = new double[12];
		LocalDate today = LocalDate.now();
		int year = today.getYear();
		for (int i = 0; i < revenues.length; i++) {
			String query = "SELECT SUM(price) FROM hotel.bill WHERE month(dateReturn) = '"+(i+1)+"' AND year(dateReturn) = '"+year+"'";
			try {
				ResultSet resultSet = new DBConn().queryDB(query);
				if (resultSet.next()) {
					revenues[i] = resultSet.getDouble("SUM(price)");
				}else {
					revenues[i] = 0;
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				revenues[i] = 0;
			}
		}
		
		return revenues;
	}

	public double[] getBookingEachMonth() {
		double[] bookings = new double[12];
		
		for (int i = 0; i < bookings.length; i++) {
			String query = "SELECT COUNT(*) FROM hotel.bill WHERE month(dateOrder) = '"+(i+1)+"'";
			try {
				ResultSet resultSet = new DBConn().queryDB(query);
				if (resultSet.next()) {
					bookings[i] = resultSet.getDouble("COUNT(*)");
				}else {
					bookings[i] = 0;
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				bookings[i] = 0;
			}
		}
		return bookings;
	}	
	
}
