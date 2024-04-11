package BUS;

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

}
