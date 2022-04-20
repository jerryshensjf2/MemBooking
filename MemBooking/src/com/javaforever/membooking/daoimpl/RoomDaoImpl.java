package com.javaforever.membooking.daoimpl;

import com.javaforever.membooking.dao.RoomDao;
import com.javaforever.membooking.domain.Room;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class RoomDaoImpl implements RoomDao{
	public void activateAllRooms(Connection connection,String ids) throws Exception{
		String [] idArr = ids.split(",");
		for (String idString : idArr){
			Long id = Long.valueOf(idString);
			activateRoom(connection,id);
		}
	}

	public boolean activateRoom(Connection connection,Long id) throws Exception{
		String query = "update mb_rooms set active = true where id = ? ;";
		PreparedStatement ps = connection.prepareStatement(query);
		ps.setLong(1,id);
		int result = ps.executeUpdate();
		if (result > 0) {
			return true;
		}
		return false;
	}

	public boolean addRoom(Connection connection,Room room) throws Exception{
		String query = "insert into mb_rooms ( active,description,room_name,room_no) values (?,?,?,?);";
		PreparedStatement ps = connection.prepareStatement(query,PreparedStatement.RETURN_GENERATED_KEYS);
		ps.setBoolean(1,room.getActive());
		ps.setString(2,room.getDescription());
		ps.setString(3,room.getRoomName());
		ps.setString(4,room.getRoomNo());
		int result = ps.executeUpdate();
		ResultSet rsNewId = ps.getGeneratedKeys();
		if (rsNewId.next()) { 
			long newId = rsNewId.getLong(1);
			room.setId(newId);
		}
		if (result > 0) {
			return true;
		}
		return false;
	}

	public Long countActiveRoomRecords(Connection connection) throws Exception{
		String query = "select count(id) as countNum from mb_rooms where active = true;";
		PreparedStatement ps = connection.prepareStatement(query);
		ResultSet result = ps.executeQuery();
		Long recordcount = 0L;
		while(result.next()) {
			recordcount = result.getLong("countNum");
		}
		return recordcount;
	}

	public Long countAllRoomRecords(Connection connection) throws Exception{
		String query = "select count(id) as countNum from mb_rooms;";
		PreparedStatement ps = connection.prepareStatement(query);
		ResultSet result = ps.executeQuery();
		Long recordcount = 0L;
		while(result.next()) {
			recordcount = result.getLong("countNum");
		}
		return recordcount;
	}

	public int countRoomsPage(Connection connection,int pagesize) throws Exception{
		try {
			int pagecount = 1;
			double recordcount = 0;
			String query = "select count(id) as countNum from mb_rooms;";
			PreparedStatement ps = connection.prepareStatement(query);
			ResultSet result = ps.executeQuery();
			while(result.next()) {
				recordcount = result.getDouble("countNum");
				pagecount = (int)Math.ceil(recordcount/pagesize);
				if (pagecount <= 1)pagecount = 1;
			}
			return pagecount;
		} catch (Exception e){
			e.printStackTrace();
			return 1;
		}
	}

	public Long countSearchRoomsByFieldsRecords(Connection connection,Room room){
		try {
		String query = " select count(*) countSum from mb_rooms where 1=1 ";
		if (room.getActive()!=null ){
			query += " and active = ? ";
		}
		if (room.getDescription()!=null && !"".equals(room.getDescription())){
			query += " and description like ? ";
		}
		if (room.getRoomName()!=null && !"".equals(room.getRoomName())){
			query += " and room_name like ? ";
		}
		if (room.getRoomNo()!=null && !"".equals(room.getRoomNo())){
			query += " and room_no like ? ";
		}
		query += ";";
		//System.out.println("JerryDebug:query:"+query);
		
		int serial = 1;
		PreparedStatement ps = connection.prepareStatement(query);
		
		if (room.getActive()!=null ){
			ps.setBoolean(serial++,room.getActive());
		}
		if (room.getDescription()!=null && !"".equals(room.getDescription())){
			ps.setString(serial++,"%"+room.getDescription()+"%");
		}
		if (room.getRoomName()!=null && !"".equals(room.getRoomName())){
			ps.setString(serial++,"%"+room.getRoomName()+"%");
		}
		if (room.getRoomNo()!=null && !"".equals(room.getRoomNo())){
			ps.setString(serial++,"%"+room.getRoomNo()+"%");
		}
		
		ResultSet result = ps.executeQuery();
		Long countSum = 0L;
		while(result.next()) {
			countSum = result.getLong("countSum");
		}
		return countSum;
		} catch (Exception e){
			e.printStackTrace();
			return 0L;
		}
	}

	public void deleteAllRooms(Connection connection,String ids) throws Exception{
		String [] idArr = ids.split(",");
		for (String idString : idArr){
			Long id = Long.valueOf(idString);
			deleteRoom(connection,id);
		}
	}

	public boolean deleteRoom(Connection connection,Long id) throws Exception{
		String query = "delete from mb_rooms where id = ?;";
		PreparedStatement ps = connection.prepareStatement(query);
		ps.setLong(1,id);
		int result = ps.executeUpdate();
		if (result > 0) {
			return true;
		}
		return false;
	}

	public Room findRoomById(Connection connection,Long id) throws Exception{
		try {
			String query = "select room_no,description,room_name,active,id from mb_rooms where id = ?;";
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setLong(1,id);
			ResultSet result = ps.executeQuery();
			Room room = new Room();
			result.last();
			//Build the object.
			room.setRoomNo(result.getString("room_no"));
			room.setDescription(result.getString("description"));
			room.setRoomName(result.getString("room_name"));
			room.setActive(result.getBoolean("active"));
			room.setId(result.getLong("id"));
			return room;
		} catch (Exception e){
			e.printStackTrace();
			return null;
		}
	}

	public Room findRoomByRoomName(Connection connection,String roomName) throws Exception{
		try {
			String query = "select room_no,description,room_name,active,id from mb_rooms where room_name = ?;";
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setString(1,roomName);
			ResultSet result = ps.executeQuery();
			Room room = new Room();
			result.last();
			//Build the object.
			room.setRoomNo(result.getString("room_no"));
			room.setDescription(result.getString("description"));
			room.setRoomName(result.getString("room_name"));
			room.setActive(result.getBoolean("active"));
			room.setId(result.getLong("id"));
			return room;
		} catch (Exception e){
			e.printStackTrace();
			return null;
		}
	}

	public List<Room> listActiveRooms(Connection connection) throws Exception{
		try {
			String query = "select room_no,description,room_name,active,id from mb_rooms where active = true;";
			PreparedStatement ps = connection.prepareStatement(query);
			ResultSet result = ps.executeQuery();
			ArrayList<Room> list = new ArrayList<Room>();
			while(result.next()) {
				//Build the list object.
				Room room = new Room();
				room.setRoomNo(result.getString("room_no"));
				room.setDescription(result.getString("description"));
				room.setRoomName(result.getString("room_name"));
				room.setActive(result.getBoolean("active"));
				room.setId(result.getLong("id"));
				//Build the object list.
				list.add(room);
			}
			return list;
		} catch (Exception e){
			e.printStackTrace();
			return null;
		}
	}

	public List<Room> listAllRooms(Connection connection) throws Exception{
		try {
			String query = "select room_no,description,room_name,active,id from mb_rooms;";
			PreparedStatement ps = connection.prepareStatement(query);
			ResultSet result = ps.executeQuery();
			ArrayList<Room> list = new ArrayList<Room>();
			while(result.next()) {
				//Build the list object.
				Room room = new Room();
				room.setRoomNo(result.getString("room_no"));
				room.setDescription(result.getString("description"));
				room.setRoomName(result.getString("room_name"));
				room.setActive(result.getBoolean("active"));
				room.setId(result.getLong("id"));
				//Build the object list.
				list.add(room);
			}
			return list;
		} catch (Exception e){
			e.printStackTrace();
			return new ArrayList<Room>();
		}
	}

	public List<Room> listAllRoomsByPage(Connection connection,int pagesize,int pagenum) throws Exception{
		try {
			String query = "select room_no,description,room_name,active,id from mb_rooms limit ?,?;";
			int limitstart = (pagenum-1)*pagesize;
			int limitcount = pagesize;
			PreparedStatement ps = connection.prepareStatement(query);
			int pagecount = this.countRoomsPage(connection,pagesize);
			if (pagenum <= 1) pagenum = 1;
			if (pagenum >= pagecount) pagenum = pagecount;
			ps.setInt(1,limitstart);
			ps.setInt(2,limitcount);
			ResultSet result = ps.executeQuery();
			ArrayList<Room> list = new ArrayList<Room>();
			while(result.next()) {
				//Build the list object.
				Room room = new Room();
				room.setRoomNo(result.getString("room_no"));
				room.setDescription(result.getString("description"));
				room.setRoomName(result.getString("room_name"));
				room.setActive(result.getBoolean("active"));
				room.setId(result.getLong("id"));
				//Build the object list.
				list.add(room);
			}
			return list;
		} catch (Exception e){
			e.printStackTrace();
			return new ArrayList<Room>();
		}
	}

	public List<Room> searchRoomsByFieldsByPage(Connection connection,Room room,Long pagenum,Long pagesize) throws Exception{
		try {
		Long start = (pagenum-1)*pagesize;
		Long limit = pagesize;
		String query = " select room_no,description,room_name,active,id from mb_rooms where 1=1 ";
		if (room.getActive()!=null ){
			query += " and active = ? ";
		}
		if (room.getDescription()!=null && !"".equals(room.getDescription())){
			query += " and description like ? ";
		}
		if (room.getRoomName()!=null && !"".equals(room.getRoomName())){
			query += " and room_name like ? ";
		}
		if (room.getRoomNo()!=null && !"".equals(room.getRoomNo())){
			query += " and room_no like ? ";
		}
		query += "limit ? offset ?;";
		//System.out.println("JerryDebug:query:"+query);
		
		Integer serial = 1;
		PreparedStatement ps = connection.prepareStatement(query);
		
		if (room.getActive()!=null ){
			ps.setBoolean(serial++,room.getActive());
		}
		if (room.getDescription()!=null && !"".equals(room.getDescription())){
			ps.setString(serial++,"%"+room.getDescription()+"%");
		}
		if (room.getRoomName()!=null && !"".equals(room.getRoomName())){
			ps.setString(serial++,"%"+room.getRoomName()+"%");
		}
		if (room.getRoomNo()!=null && !"".equals(room.getRoomNo())){
			ps.setString(serial++,"%"+room.getRoomNo()+"%");
		}
		ps.setLong(serial++, limit);
		ps.setLong(serial++,start);
		
		ResultSet result = ps.executeQuery();
		ArrayList<Room> list = new ArrayList<Room>();
		while(result.next()) {
			//Build the list object.
			Room room2 = new Room();
			room2.setRoomNo(result.getString("room_no"));
			room2.setDescription(result.getString("description"));
			room2.setRoomName(result.getString("room_name"));
			room2.setActive(result.getBoolean("active"));
			room2.setId(result.getLong("id"));
			//Build the object list.
			list.add(room2);
		}
		return list;
		} catch (Exception e){
			e.printStackTrace();
			return null;
		}
	}

	public List<Room> searchRoomsByRoomName(Connection connection,String roomName) throws Exception{
		try {
			String query = "select room_no,description,room_name,active,id from mb_rooms where room_name like ?";
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setString(1,"%"+roomName+"%");
			ResultSet result = ps.executeQuery();
			ArrayList<Room> list = new ArrayList<Room>();
			while(result.next()) {
				//Build the list object.
				Room room = new Room();
				room.setRoomNo(result.getString("room_no"));
				room.setDescription(result.getString("description"));
				room.setRoomName(result.getString("room_name"));
				room.setActive(result.getBoolean("active"));
				room.setId(result.getLong("id"));
				//Build the object list.
				list.add(room);
			}
			return list;
		} catch (Exception e){
			e.printStackTrace();
			return null;
		}
	}

	public void softDeleteAllRooms(Connection connection,String ids) throws Exception{
		String [] idArr = ids.split(",");
		for (String idString : idArr){
			Long id = Long.valueOf(idString);
			softDeleteRoom(connection,id);
		}
	}

	public boolean softDeleteRoom(Connection connection,Long id) throws Exception{
		String query = "update mb_rooms set active = false where id = ? ;";
		PreparedStatement ps = connection.prepareStatement(query);
		ps.setLong(1,id);
		int result = ps.executeUpdate();
		if (result > 0) {
			return true;
		}
		return false;
	}

	public Boolean toggleRoom(Connection connection,Long id) throws Exception{
		String query = "update mb_rooms set active = not active where id = ? ";
		PreparedStatement ps = connection.prepareStatement(query);
		ps.setLong(1,id);
		int result = ps.executeUpdate();
		if (result > 0) {
			return true;
		}
		return false;
	}

	public boolean updateRoom(Connection connection,Room room) throws Exception{
		String query = "update mb_rooms set room_no = ? ,description = ? ,room_name = ? ,active = ? where id = ?;";
		PreparedStatement ps = connection.prepareStatement(query);
		ps.setBoolean(1,room.getActive());
		ps.setString(2,room.getDescription());
		ps.setLong(3,room.getId());
		ps.setString(4,room.getRoomName());
		ps.setString(5,room.getRoomNo());
		int result = ps.executeUpdate();
		if (result > 0) {
			return true;
		}
		return false;
	}

}
