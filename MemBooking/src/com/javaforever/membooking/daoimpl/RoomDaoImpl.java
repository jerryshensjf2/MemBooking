package com.javaforever.membooking.daoimpl;

import java.util.List;

import com.javaforever.membooking.dao.RoomDao;
import com.javaforever.membooking.domain.Room;

public class RoomDaoImpl implements RoomDao{

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
		// TODO Auto-generated method stub
		return null;
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
		// TODO Auto-generated method stub
		return null;
	}

}
