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

	public Customer(int id, String name, String cCCD, Date birthDate) {
		this.id = id;
		this.name = name;
		CCCD = cCCD;
		this.birthDate = birthDate;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCCCD() {
		return CCCD;
	}

	public void setCCCD(String cCCD) {
		CCCD = cCCD;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public int getIdEmp() {
		return idEmp;
	}

	public void setIdEmp(int idEmp) {
		this.idEmp = idEmp;
	}

	public Object[] toObjects() {
		return new Object[] { "" + id, name, CCCD, birthDate };
	}
}
