package DTO;

import java.sql.Date;

public class Employee {
	private int id;
	private String name;
	private Date birthDate;
	private String email;
	private boolean sex;
	private String position;
	private String userName;
	private String password;

	public Employee() {
		
	}

	public Employee(int id, String name, Date birthDate, String email, boolean sex, String position) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.birthDate = birthDate;
		this.sex = sex;
		this.position = position;
	}

	public Employee(String name, Date birthDate, String email, boolean sex, String position) {
		this.name = name;
		this.email = email;
		this.birthDate = birthDate;
		this.sex = sex;
		this.position = position;
	}

	public Employee(String name, Date birthDate, String email, boolean sex, String position, String userName,
			String password) {
		this.name = name;
		this.email = email;
		this.birthDate = birthDate;
		this.sex = sex;
		this.position = position;
		this.userName = userName;
		this.password = password;
	}

	

	public Employee(int id, String name, Date birthDate, String email, boolean sex, String position, String userName,
			String password) {
		this.id = id;
		this.name = name;
		this.birthDate = birthDate;
		this.email = email;
		this.sex = sex;
		this.position = position;
		this.userName = userName;
		this.password = password;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public boolean isSex() {
		return sex;
	}

	public void setSex(boolean sex) {
		this.sex = sex;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public Object[] toObjects() {
		return new Object[]{""+id,name,birthDate,email ,sex ? "Nam":"Nữ",position};
	}
}
