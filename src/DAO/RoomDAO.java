package DAO;

import java.sql.ResultSet;
import java.util.ArrayList;

import DTO.Room;

public class RoomDAO {

	public boolean isOrdered(int roomNumber2) {
		try {
			ArrayList<Integer> rooms = new ArrayList<>();
			ResultSet resultSet = new DBConn().queryDB("SELECT roomNumber FROM hotel.bill WHERE dateReturn IS NULL;");
			while (resultSet.next()) {
				int roomNumber = resultSet.getInt("roomNumber");
				rooms.add(roomNumber);
			}
			
			for (Integer integer : rooms) {
				if (integer == roomNumber2) {
					return true;
				}
			}
			
			if (rooms.contains(roomNumber2)) {
				return true;
			}
			return false;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public ArrayList<Room> getListRooms() {
		ArrayList<Room> rooms = new ArrayList<>();
		try {
			ResultSet resultSet = new DBConn().queryDB("SELECT roomnumber FROM room");
			while (resultSet.next()) {
				int roomNumber = resultSet.getInt("roomnumber");
				rooms.add(new Room(roomNumber));
			}
			return rooms;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ArrayList<>();
	}

	public ArrayList<Room> getListEmptyRoom() {
		ArrayList<Room> rooms = new ArrayList<>();
		try {
			String query = "SELECT roomnumber\r\n"
					+ "FROM hotel.room \r\n"
					+ "WHERE roomnumber NOT IN(\r\n"
					+ "SELECT roomNumber FROM hotel.bill WHERE dateReturn IS NULL\r\n"
					+ ") ORDER BY roomnumber;";
			ResultSet resultSet = new DBConn().queryDB(query);
			while (resultSet.next()) {
				int roomNumber = resultSet.getInt("roomnumber");
				rooms.add(new Room(roomNumber));
			}
			return rooms;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ArrayList<>();
	}

	public ArrayList<Room> getListOrderedRoom() {
		ArrayList<Room> rooms = new ArrayList<>();
		try {
			String query = "SELECT roomnumber\r\n"
					+ "FROM hotel.room \r\n"
					+ "WHERE roomnumber IN(\r\n"
					+ "SELECT roomNumber FROM hotel.bill WHERE dateReturn IS NULL\r\n"
					+ ") ORDER BY roomnumber;";
			ResultSet resultSet = new DBConn().queryDB(query);
			while (resultSet.next()) {
				int roomNumber = resultSet.getInt("roomnumber");
				rooms.add(new Room(roomNumber));
			}
			return rooms;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return new ArrayList<>();
	}

}
