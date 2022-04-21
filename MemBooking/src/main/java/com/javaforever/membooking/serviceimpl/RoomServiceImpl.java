package com.javaforever.membooking.serviceimpl;

import java.util.List;

import com.javaforever.membooking.dao.RoomDao;
import com.javaforever.membooking.daoimpl.RoomDaoImpl;
import com.javaforever.membooking.domain.Room;
import com.javaforever.membooking.service.RoomService;

public class RoomServiceImpl implements RoomService {
	protected RoomDao dao = new RoomDaoImpl();

	public void activateAllRooms(String ids) throws Exception {
		dao.activateAllRooms(ids);
	}

	public boolean activateRoom(Long id) throws Exception {
		return dao.activateRoom(id);
	}

	public boolean addRoom(Room room) throws Exception {
		return dao.addRoom(room);
	}

	@Override
	public Long countActiveRoomRecords() throws Exception {
		return dao.countActiveRoomRecords();
	}

	@Override
	public Long countAllRoomRecords() throws Exception {
		return dao.countAllRoomRecords();
	}

	public int countRoomsPage(int pagesize) throws Exception {
		return dao.countRoomsPage(pagesize);
	}

	@Override
	public Long countSearchRoomsByFieldsRecords(Room room) throws Exception {
		return dao.countSearchRoomsByFieldsRecords(room);
	}

	public void deleteAllRooms(String ids) throws Exception {
		dao.deleteAllRooms(ids);
	}

	public boolean deleteRoom(Long id) throws Exception {
		return dao.deleteRoom(id);
	}

	public Room findRoomById(Long id) throws Exception {
		return dao.findRoomById(id);
	}

	public Room findRoomByRoomName(String roomName) throws Exception {
		return dao.findRoomByRoomName(roomName);
	}

	public List<Room> listActiveRooms() throws Exception {
		return dao.listActiveRooms();
	}

	public List<Room> listAllRooms() throws Exception {
		return dao.listAllRooms();
	}

	public List<Room> listAllRoomsByPage(int pagesize, int pagenum) throws Exception {
		return dao.listAllRoomsByPage(pagesize, pagenum);
	}

	public List<Room> searchRoomsByFieldsByPage(Room room, Long pagenum, Long pagesize) throws Exception {
		return dao.searchRoomsByFieldsByPage(room, pagenum, pagesize);
	}

	public List<Room> searchRoomsByRoomName(String roomName) throws Exception {
		return dao.searchRoomsByRoomName(roomName);
	}

	public void softDeleteAllRooms(String ids) throws Exception {
		dao.softDeleteAllRooms(ids);
	}

	public boolean softDeleteRoom(Long id) throws Exception {
		return dao.softDeleteRoom(id);
	}

	public Boolean toggleOneRoom(Long id) throws Exception {
		Room room = dao.findRoomById(id);
		if (room.getActive() == false) {
			dao.toggleRoom(id);
		} else {
			Long count = dao.countActiveRoomRecords();
			if (count > 1) {
				dao.toggleRoom(id);
			}
		}
		return true;

	}

	public Boolean toggleRoom(Long id) throws Exception {
		return dao.toggleRoom(id);
	}

	public boolean updateRoom(Room room) throws Exception {
		return dao.updateRoom(room);
	}

}
