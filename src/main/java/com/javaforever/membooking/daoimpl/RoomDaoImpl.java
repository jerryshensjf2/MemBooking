package com.javaforever.membooking.daoimpl;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import com.javaforever.membooking.dao.RoomDao;
import com.javaforever.membooking.domain.Room;

public class RoomDaoImpl implements RoomDao{
	public static List<Room> db = new CopyOnWriteArrayList<>();
	
	static {
		Room room0 = new Room();
		room0.setId(1L);
		room0.setRoomNo("1");
		room0.setRoomName("New York");
		room0.setActive(true);
		room0.setDescription("");
		
		Room room1 = new Room();
		room1.setId(2L);
		room1.setRoomNo("2");
		room1.setRoomName("London");
		room1.setActive(true);
		room1.setDescription("");
	
		db.add(room0);
		db.add(room1);
	}

	@Override
	public List<Room> listAllRooms() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean updateRoom(Room room) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteRoom(Long id) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean addRoom(Room room) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean softDeleteRoom(Long id) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Room findRoomById(Long id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Room findRoomByRoomName(String roomName) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Room> searchRoomsByRoomName(String roomName) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Room> listActiveRooms() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Room> listAllRoomsByPage(int pagesize, int pagenum) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteAllRooms(String ids) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void softDeleteAllRooms(String ids) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Boolean toggleRoom(Long id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Room> searchRoomsByFieldsByPage(Room room, Long pagenum, Long pagesize) throws Exception {
		return db;
	}

	@Override
	public boolean activateRoom(Long id) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void activateAllRooms(String ids) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int countRoomsPage(int pagesize) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Long countActiveRoomRecords() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long countAllRoomRecords() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long countSearchRoomsByFieldsRecords(Room room) throws Exception {
		return Long.valueOf(db.size());
	}

}
