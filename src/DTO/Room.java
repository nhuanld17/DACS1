package DTO;

public class Room {
	private int roomNumber;
	
	public Room() {
		// TODO Auto-generated constructor stub
	}

	public Room(int roomNumber) {
		this.roomNumber = roomNumber;
	}

	public int getRoomNumber() {
		return roomNumber;
	}

	public void setRoomNumber(int roomNumber) {
		this.roomNumber = roomNumber;
	}
	
	public Object[] toObjects() {
		return new Object[] {""+roomNumber};
	}
	
}
