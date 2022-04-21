package com.javaforever.membooking.service;

import com.javaforever.membooking.domain.Room;
import java.util.List;

public interface RoomService{
	public List<Room> listAllRooms() throws Exception;
	public boolean updateRoom(Room room) throws Exception;
	public boolean deleteRoom(Long id) throws Exception;
	public boolean addRoom(Room room) throws Exception;
	public boolean softDeleteRoom(Long id) throws Exception;
	public Room findRoomById(Long id) throws Exception;
	public Room findRoomByRoomName(String roomName) throws Exception;
	public List<Room> searchRoomsByRoomName(String roomName) throws Exception;
	public List<Room> listActiveRooms() throws Exception;
	public List<Room> listAllRoomsByPage(int pagesize,int pagenum) throws Exception;
	public void deleteAllRooms(String ids) throws Exception;
	public void softDeleteAllRooms(String ids) throws Exception;
	public Boolean toggleRoom(Long id) throws Exception;
	public Boolean toggleOneRoom(Long id) throws Exception;
	public List<Room> searchRoomsByFieldsByPage(Room room,Long pagenum,Long pagesize) throws Exception;
	public boolean activateRoom(Long id) throws Exception;
	public void activateAllRooms(String ids) throws Exception;
	public int countRoomsPage(int pagesize) throws Exception;
	public Long countActiveRoomRecords() throws Exception;
	public Long countAllRoomRecords() throws Exception;
	public Long countSearchRoomsByFieldsRecords(Room room) throws Exception;
}
