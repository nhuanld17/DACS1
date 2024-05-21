package BUS;

import java.sql.Timestamp;
import java.util.ArrayList;

import DAO.BillDAO;
import DTO.Bill;

public class BillBUS {

	public void addBill(Bill bill) {
		new BillDAO().addBill(bill);
	}

	public ArrayList<Bill> getListBill() {
	   return new BillDAO().getListBill();
	}

	public int getRoomNumberByID(int id) {
		return new BillDAO().getRoomNumberByID(id);
	}

	public boolean isRoomReturned(int stt) {
		return new BillDAO().isRoomReturned(stt);
	}

	public void updateBill(Bill bill) {
		new BillDAO().updateBill(bill);
	}

	public ArrayList<Bill> findBillByCCCD(String cCCD) {
		return new BillDAO().findBillByCCCD(cCCD);
	}

	public boolean isBillAbated(int id) {
		return new BillDAO().isBillAbated(id);
	}

	public ArrayList<Bill> getListNotAbatedBill() {
		return new BillDAO().getListNotAbatedBill();
	}

	public void abateBill(int id, long price, Timestamp dateReturn) {
		new BillDAO().abateBill(id, price, dateReturn);
	}

	public int getTotalBookingByDate(String formattedDate) {
		return new BillDAO().getTotalBookingByDate(formattedDate);
	}

}
