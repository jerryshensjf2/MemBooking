package com.javaforever.membooking.dao;

import com.javaforever.membooking.domain.Room;
import java.sql.Connection;
import java.util.List;

public interface RoomDao{
	public List<Room> listAllRooms(Connection connection) throws Exception;
	public boolean updateRoom(Connection connection,Room room) throws Exception;
	public boolean deleteRoom(Connection connection,Long id) throws Exception;
	public boolean addRoom(Connection connection,Room room) throws Exception;
	public boolean softDeleteRoom(Connection connection,Long id) throws Exception;
	public Room findRoomById(Connection connection,Long id) throws Exception;
	public Room findRoomByRoomName(Connection connection,String roomName) throws Exception;
	public List<Room> searchRoomsByRoomName(Connection connection,String roomName) throws Exception;
	public List<Room> listActiveRooms(Connection connection) throws Exception;
	public List<Room> listAllRoomsByPage(Connection connection,int pagesize,int pagenum) throws Exception;
	public void deleteAllRooms(Connection connection,String ids) throws Exception;
	public void softDeleteAllRooms(Connection connection,String ids) throws Exception;
	public Boolean toggleRoom(Connection connection,Long id) throws Exception;
	public List<Room> searchRoomsByFieldsByPage(Connection connection,Room room,Long pagenum,Long pagesize) throws Exception;
	public boolean activateRoom(Connection connection,Long id) throws Exception;
	public void activateAllRooms(Connection connection,String ids) throws Exception;
	public int countRoomsPage(Connection connection,int pagesize) throws Exception;
	public Long countActiveRoomRecords(Connection connection) throws Exception;
	public Long countAllRoomRecords(Connection connection) throws Exception;
	public Long countSearchRoomsByFieldsRecords(Connection connection,Room room) throws Exception;
}
