package com.javaforever.membooking.serviceimpl;

import com.javaforever.membooking.dao.RoomDao;
import com.javaforever.membooking.daoimpl.RoomDaoImpl;
import com.javaforever.membooking.database.DBConf;
import com.javaforever.membooking.domain.Room;
import com.javaforever.membooking.service.RoomService;
import java.sql.Connection;
import java.util.List;

public class RoomServiceImpl implements RoomService{
	protected RoomDao dao = new RoomDaoImpl();
	public void activateAllRooms(String ids) throws Exception{
		try (Connection connection = DBConf.initDB()) {
			dao.activateAllRooms(connection,ids);
		}
	}

	public boolean activateRoom(Long id) throws Exception{
		try (Connection connection = DBConf.initDB()) {
			return dao.activateRoom(connection,id);
		}
	}

	public boolean addRoom(Room room) throws Exception{
		try (Connection connection = DBConf.initDB()) {
			return dao.addRoom(connection,room);
		}
	}

	@Override
	public Long countActiveRoomRecords() throws Exception{
		try (Connection connection = DBConf.initDB()) {
			return dao.countActiveRoomRecords(connection);
		}
	}

	@Override
	public Long countAllRoomRecords() throws Exception{
		try (Connection connection = DBConf.initDB()) {
			return dao.countAllRoomRecords(connection);
		}
	}

	public int countRoomsPage(int pagesize) throws Exception{
		try (Connection connection = DBConf.initDB()) {
			return dao.countRoomsPage(connection,pagesize);
		}
	}

	@Override
	public Long countSearchRoomsByFieldsRecords(Room room) throws Exception{
		try (Connection connection = DBConf.initDB()) {
			return dao.countSearchRoomsByFieldsRecords(connection,room);
		}
	}

	public void deleteAllRooms(String ids) throws Exception{
		try (Connection connection = DBConf.initDB()) {
			dao.deleteAllRooms(connection,ids);
		}
	}

	public boolean deleteRoom(Long id) throws Exception{
		try (Connection connection = DBConf.initDB()) {
			return dao.deleteRoom(connection,id);
		}
	}

	public Room findRoomById(Long id) throws Exception{
		try (Connection connection = DBConf.initDB()) {
			return dao.findRoomById(connection,id);
		}
	}

	public Room findRoomByRoomName(String roomName) throws Exception{
		try (Connection connection = DBConf.initDB()) {
			return dao.findRoomByRoomName(connection,roomName);
		}
	}

	public List<Room> listActiveRooms() throws Exception{
		try (Connection connection = DBConf.initDB()) {
			return dao.listActiveRooms(connection);
		}
	}

	public List<Room> listAllRooms() throws Exception{
		try (Connection connection = DBConf.initDB()) {
			return dao.listAllRooms(connection);
		}
	}

	public List<Room> listAllRoomsByPage(int pagesize,int pagenum) throws Exception{
		try (Connection connection = DBConf.initDB()) {
			return dao.listAllRoomsByPage(connection,pagesize,pagenum);
		}
	}

	public List<Room> searchRoomsByFieldsByPage(Room room,Long pagenum,Long pagesize) throws Exception{
		try (Connection connection = DBConf.initDB()) {
			return dao.searchRoomsByFieldsByPage(connection,room,pagenum,pagesize);
		}
	}

	public List<Room> searchRoomsByRoomName(String roomName) throws Exception{
		try (Connection connection = DBConf.initDB()) {
			return dao.searchRoomsByRoomName(connection,roomName);
		}
	}

	public void softDeleteAllRooms(String ids) throws Exception{
		try (Connection connection = DBConf.initDB()) {
			dao.softDeleteAllRooms(connection,ids);
		}
	}

	public boolean softDeleteRoom(Long id) throws Exception{
		try (Connection connection = DBConf.initDB()) {
			return dao.softDeleteRoom(connection,id);
		}
	}

	public Boolean toggleOneRoom(Long id) throws Exception{
		try (Connection connection = DBConf.initDB()) {
			Room room = dao.findRoomById(connection,id);
			if (room.getActive()==false) {
				dao.toggleRoom(connection,id);
			}
			else {
				Long count = dao.countActiveRoomRecords(connection);
				if (count > 1){
					dao.toggleRoom(connection,id);
				}
			}
			return true;
		}
	}

	public Boolean toggleRoom(Long id) throws Exception{
		try (Connection connection = DBConf.initDB()) {
			return dao.toggleRoom(connection,id);
		}
	}

	public boolean updateRoom(Room room) throws Exception{
		try (Connection connection = DBConf.initDB()) {
			return dao.updateRoom(connection,room);
		}
	}

}
