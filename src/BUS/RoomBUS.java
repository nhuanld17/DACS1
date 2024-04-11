package BUS;

import java.util.ArrayList;

import DAO.RoomDAO;
import DTO.Room;

public class RoomBUS {

	public boolean isOrdered(int roomNumber) {
		return new RoomDAO().isOrdered(roomNumber);
	}

	public ArrayList<Room> getListRooms() {
		return new RoomDAO().getListRooms();
	}

	public ArrayList<Room> getListEmptyRoom() {
		return new RoomDAO().getListEmptyRoom();
	}

	public ArrayList<Room> getListOrderedRoom() {
		return new RoomDAO().getListOrderedRoom();
	}
}
