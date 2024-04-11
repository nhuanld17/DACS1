package DTO;

import java.sql.Date;

public class Bill {
	private int id;
	private int roomNumber;
	private String cccd;
	private Date dateOrder;
	private Date dateReturn;
	private double price;

	public Bill(int id, int roomNumber, String cccd, Date dateOrder, Date dateReturn, double price) {
		this.id = id;
		this.roomNumber = roomNumber;
		this.cccd = cccd;
		this.dateOrder = dateOrder;
		this.dateReturn = dateReturn;
		this.price = price;
	}

	public Bill(int roomNumber, String cccd, Date dateOrder) {
		this.roomNumber = roomNumber;
		this.cccd = cccd;
		this.dateOrder = dateOrder;
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

	public Date getDateOrder() {
		return dateOrder;
	}

	public void setDateOrder(Date dateOrder) {
		this.dateOrder = dateOrder;
	}

	public Date getDateReturn() {
		return dateReturn;
	}

	public void setDateReturn(Date dateReturn) {
		this.dateReturn = dateReturn;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
	public Object[] toObject() {
		return new Object[] {""+id,cccd,roomNumber+"",dateOrder, dateReturn, (price > 0) ? price : ""};
	}
}
