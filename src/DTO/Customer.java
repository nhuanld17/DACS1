package DTO;

import java.sql.Date;

public class Customer {
	private int id;
	private String name;
	private String CCCD;
	private Date birthDate;
	private int idEmp;
	
	public Customer() {
		// TODO Auto-generated constructor stub
	}

	public Customer(int id, String name, String cCCD, Date birthDate, int idEmp) {
		this.id = id;
		this.name = name;
		CCCD = cCCD;
		this.birthDate = birthDate;
		this.idEmp = idEmp;
	}

	public Customer(String name, String cCCD, Date birthDate, int idEmp) {
		this.name = name;
		CCCD = cCCD;
		this.birthDate = birthDate;
		this.idEmp = idEmp;
	}
	
	public Object[] toObjects() {
		return new Object[] {""+id, name, CCCD,birthDate};
	}
}
