package DTO;

import java.sql.Date;

public class CustomerServedChart {
	private Date date;
	private int totalCustomer;
	
	public CustomerServedChart(Date date, int totalCustomer) {
		this.date = date;
		this.totalCustomer = totalCustomer;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getTotalCustomer() {
		return totalCustomer;
	}

	public void setTotalCustomer(int totalCustomer) {
		this.totalCustomer = totalCustomer;
	}
}
