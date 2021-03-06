package com.javaforever.membooking.daoimpl;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import com.javaforever.membooking.dao.RoomDao;
import com.javaforever.membooking.domain.Guest;
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
		
		Room room2 = new Room();
		room2.setId(3L);
		room2.setRoomNo("3");
		room2.setRoomName("Roma");
		room2.setActive(true);
		room2.setDescription("");
		
		Room room3 = new Room();
		room3.setId(4L);
		room3.setRoomNo("4");
		room3.setRoomName("Washington");
		room3.setActive(true);
		room3.setDescription("");
		
		Room room4 = new Room();
		room4.setId(5L);
		room4.setRoomNo("5");
		room4.setRoomName("Paris");
		room4.setActive(true);
		room4.setDescription("");
	
		db.add(room0);
		db.add(room1);
		db.add(room2);
		db.add(room3);
		db.add(room4);
	}

	@Override
	public List<Room> listAllRooms() throws Exception {
		return db;
	}

	@Override
	public boolean updateRoom(Room room) throws Exception {
		if (room.getId() ==null) return false;
		for (int i = 0; i < db.size(); i++) {
            if(db.get(i).getId()==room.getId()){
                db.remove(i);
                db.add(i, room);
                return true;
            }
        }
		return false;
	}

	@Override
	public boolean deleteRoom(Long id) throws Exception {
		for (int i = 0; i < db.size(); i++) {
            if(db.get(i).getId()==id){
                db.remove(i);
                return true;
            }
        }
		return false;
	}

	@Override
	public boolean addRoom(Room room) throws Exception {
		Long index = nextIndex();
		room.setId(index);
		db.add(room);
		return true;
	}

	@Override
	public boolean softDeleteRoom(Long id) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Room findRoomById(Long id) throws Exception {
		for (Room r:db) {
			if (r.getId() == id) return r;
		}
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
		return db;
	}

	@Override
	public List<Room> listAllRoomsByPage(int pagesize, int pagenum) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteAllRooms(String ids) throws Exception {
		String [] idArr = ids.split(",");
		for (String idString : idArr){
			Long id = Long.valueOf(idString);
			deleteRoom(id);
		}
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
	
	private Long nextIndex(){
		Long index = 0L;
		for (Room r:db){
			if (r.getId()>index) index = r.getId();
		}
		return index +1L;
	}

}
