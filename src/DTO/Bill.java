package DTO;

import java.sql.Timestamp;

public class Bill {
	private int id;
	private int roomNumber;
	private String cccd;
	private Timestamp dateOrder;
	private Timestamp dateReturn;
	private long price;

	public Bill(int id, int roomNumber, String cccd, Timestamp dateOrder, Timestamp dateReturn, long price) {
		this.id = id;
		this.roomNumber = roomNumber;
		this.cccd = cccd;
		this.dateOrder = dateOrder;
		this.dateReturn = dateReturn;
		this.price = price;
	}

	public Bill(int roomNumber, String cccd, Timestamp dateOrder) {
		this.roomNumber = roomNumber;
		this.cccd = cccd;
		this.dateOrder = dateOrder;
	}
	
	public Bill(int id, int roomNumber, String cccd) {
		this.id = id;
		this.roomNumber = roomNumber;
		this.cccd = cccd;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getRoomNumber() {
		return roomNumber;
	}

	public void setRoomNumber(int roomNumber) {
		this.roomNumber = roomNumber;
	}

	public String getCccd() {
		return cccd;
	}

	public void setCccd(String cccd) {
		this.cccd = cccd;
	}

	public Timestamp getDateOrder() {
		return dateOrder;
	}

	public void setDateOrder(Timestamp dateOrder) {
		this.dateOrder = dateOrder;
	}

	public Timestamp getDateReturn() {
		return dateReturn;
	}

	public void setDateReturn(Timestamp dateReturn) {
		this.dateReturn = dateReturn;
	}

	public long getPrice() {
		return price;
	}

	public void setPrice(long price) {
		this.price = price;
	}
	public Object[] toObject() {
		return new Object[] {""+id,cccd,roomNumber+"",dateOrder, dateReturn, (price > 0) ? price : ""};
	}
}
