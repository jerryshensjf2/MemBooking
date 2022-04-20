package com.javaforever.membooking.daoimpl;

import com.javaforever.membooking.dao.GuestDao;
import com.javaforever.membooking.domain.Guest;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class GuestDaoImpl implements GuestDao{
	public void activateAllGuests(Connection connection,String ids) throws Exception{
		String [] idArr = ids.split(",");
		for (String idString : idArr){
			Long id = Long.valueOf(idString);
			activateGuest(connection,id);
		}
	}

	public boolean activateGuest(Connection connection,Long id) throws Exception{
		String query = "update mb_guests set active = true where id = ? ;";
		PreparedStatement ps = connection.prepareStatement(query);
		ps.setLong(1,id);
		int result = ps.executeUpdate();
		if (result > 0) {
			return true;
		}
		return false;
	}

	public boolean addGuest(Connection connection,Guest guest) throws Exception{
		String query = "insert into mb_guests ( active,description,gender,guest_name) values (?,?,?,?);";
		PreparedStatement ps = connection.prepareStatement(query,PreparedStatement.RETURN_GENERATED_KEYS);
		ps.setBoolean(1,guest.getActive());
		ps.setString(2,guest.getDescription());
		ps.setString(3,guest.getGender());
		ps.setString(4,guest.getGuestName());
		int result = ps.executeUpdate();
		ResultSet rsNewId = ps.getGeneratedKeys();
		if (rsNewId.next()) { 
			long newId = rsNewId.getLong(1);
			guest.setId(newId);
		}
		if (result > 0) {
			return true;
		}
		return false;
	}

	public Long countActiveGuestRecords(Connection connection) throws Exception{
		String query = "select count(id) as countNum from mb_guests where active = true;";
		PreparedStatement ps = connection.prepareStatement(query);
		ResultSet result = ps.executeQuery();
		Long recordcount = 0L;
		while(result.next()) {
			recordcount = result.getLong("countNum");
		}
		return recordcount;
	}

	public Long countAllGuestRecords(Connection connection) throws Exception{
		String query = "select count(id) as countNum from mb_guests;";
		PreparedStatement ps = connection.prepareStatement(query);
		ResultSet result = ps.executeQuery();
		Long recordcount = 0L;
		while(result.next()) {
			recordcount = result.getLong("countNum");
		}
		return recordcount;
	}

	public int countGuestsPage(Connection connection,int pagesize) throws Exception{
		try {
			int pagecount = 1;
			double recordcount = 0;
			String query = "select count(id) as countNum from mb_guests;";
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

	public Long countSearchGuestsByFieldsRecords(Connection connection,Guest guest){
		try {
		String query = " select count(*) countSum from mb_guests where 1=1 ";
		if (guest.getActive()!=null ){
			query += " and active = ? ";
		}
		if (guest.getDescription()!=null && !"".equals(guest.getDescription())){
			query += " and description like ? ";
		}
		if (guest.getGender()!=null && !"".equals(guest.getGender())){
			query += " and gender like ? ";
		}
		if (guest.getGuestName()!=null && !"".equals(guest.getGuestName())){
			query += " and guest_name like ? ";
		}
		query += ";";
		//System.out.println("JerryDebug:query:"+query);
		
		int serial = 1;
		PreparedStatement ps = connection.prepareStatement(query);
		
		if (guest.getActive()!=null ){
			ps.setBoolean(serial++,guest.getActive());
		}
		if (guest.getDescription()!=null && !"".equals(guest.getDescription())){
			ps.setString(serial++,"%"+guest.getDescription()+"%");
		}
		if (guest.getGender()!=null && !"".equals(guest.getGender())){
			ps.setString(serial++,"%"+guest.getGender()+"%");
		}
		if (guest.getGuestName()!=null && !"".equals(guest.getGuestName())){
			ps.setString(serial++,"%"+guest.getGuestName()+"%");
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

	public void deleteAllGuests(Connection connection,String ids) throws Exception{
		String [] idArr = ids.split(",");
		for (String idString : idArr){
			Long id = Long.valueOf(idString);
			deleteGuest(connection,id);
		}
	}

	public boolean deleteGuest(Connection connection,Long id) throws Exception{
		String query = "delete from mb_guests where id = ?;";
		PreparedStatement ps = connection.prepareStatement(query);
		ps.setLong(1,id);
		int result = ps.executeUpdate();
		if (result > 0) {
			return true;
		}
		return false;
	}

	public Guest findGuestByGuestName(Connection connection,String guestName) throws Exception{
		try {
			String query = "select gender,description,guest_name,active,id from mb_guests where guest_name = ?;";
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setString(1,guestName);
			ResultSet result = ps.executeQuery();
			Guest guest = new Guest();
			result.last();
			//Build the object.
			guest.setGender(result.getString("gender"));
			guest.setDescription(result.getString("description"));
			guest.setGuestName(result.getString("guest_name"));
			guest.setActive(result.getBoolean("active"));
			guest.setId(result.getLong("id"));
			return guest;
		} catch (Exception e){
			e.printStackTrace();
			return null;
		}
	}

	public Guest findGuestById(Connection connection,Long id) throws Exception{
		try {
			String query = "select gender,description,guest_name,active,id from mb_guests where id = ?;";
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setLong(1,id);
			ResultSet result = ps.executeQuery();
			Guest guest = new Guest();
			result.last();
			//Build the object.
			guest.setGender(result.getString("gender"));
			guest.setDescription(result.getString("description"));
			guest.setGuestName(result.getString("guest_name"));
			guest.setActive(result.getBoolean("active"));
			guest.setId(result.getLong("id"));
			return guest;
		} catch (Exception e){
			e.printStackTrace();
			return null;
		}
	}

	public List<Guest> listActiveGuests(Connection connection) throws Exception{
		try {
			String query = "select gender,description,guest_name,active,id from mb_guests where active = true;";
			PreparedStatement ps = connection.prepareStatement(query);
			ResultSet result = ps.executeQuery();
			ArrayList<Guest> list = new ArrayList<Guest>();
			while(result.next()) {
				//Build the list object.
				Guest guest = new Guest();
				guest.setGender(result.getString("gender"));
				guest.setDescription(result.getString("description"));
				guest.setGuestName(result.getString("guest_name"));
				guest.setActive(result.getBoolean("active"));
				guest.setId(result.getLong("id"));
				//Build the object list.
				list.add(guest);
			}
			return list;
		} catch (Exception e){
			e.printStackTrace();
			return null;
		}
	}

	public List<Guest> listAllGuests(Connection connection) throws Exception{
		try {
			String query = "select gender,description,guest_name,active,id from mb_guests;";
			PreparedStatement ps = connection.prepareStatement(query);
			ResultSet result = ps.executeQuery();
			ArrayList<Guest> list = new ArrayList<Guest>();
			while(result.next()) {
				//Build the list object.
				Guest guest = new Guest();
				guest.setGender(result.getString("gender"));
				guest.setDescription(result.getString("description"));
				guest.setGuestName(result.getString("guest_name"));
				guest.setActive(result.getBoolean("active"));
				guest.setId(result.getLong("id"));
				//Build the object list.
				list.add(guest);
			}
			return list;
		} catch (Exception e){
			e.printStackTrace();
			return new ArrayList<Guest>();
		}
	}

	public List<Guest> listAllGuestsByPage(Connection connection,int pagesize,int pagenum) throws Exception{
		try {
			String query = "select gender,description,guest_name,active,id from mb_guests limit ?,?;";
			int limitstart = (pagenum-1)*pagesize;
			int limitcount = pagesize;
			PreparedStatement ps = connection.prepareStatement(query);
			int pagecount = this.countGuestsPage(connection,pagesize);
			if (pagenum <= 1) pagenum = 1;
			if (pagenum >= pagecount) pagenum = pagecount;
			ps.setInt(1,limitstart);
			ps.setInt(2,limitcount);
			ResultSet result = ps.executeQuery();
			ArrayList<Guest> list = new ArrayList<Guest>();
			while(result.next()) {
				//Build the list object.
				Guest guest = new Guest();
				guest.setGender(result.getString("gender"));
				guest.setDescription(result.getString("description"));
				guest.setGuestName(result.getString("guest_name"));
				guest.setActive(result.getBoolean("active"));
				guest.setId(result.getLong("id"));
				//Build the object list.
				list.add(guest);
			}
			return list;
		} catch (Exception e){
			e.printStackTrace();
			return new ArrayList<Guest>();
		}
	}

	public List<Guest> searchGuestsByFieldsByPage(Connection connection,Guest guest,Long pagenum,Long pagesize) throws Exception{
		try {
		Long start = (pagenum-1)*pagesize;
		Long limit = pagesize;
		String query = " select gender,description,guest_name,active,id from mb_guests where 1=1 ";
		if (guest.getActive()!=null ){
			query += " and active = ? ";
		}
		if (guest.getDescription()!=null && !"".equals(guest.getDescription())){
			query += " and description like ? ";
		}
		if (guest.getGender()!=null && !"".equals(guest.getGender())){
			query += " and gender like ? ";
		}
		if (guest.getGuestName()!=null && !"".equals(guest.getGuestName())){
			query += " and guest_name like ? ";
		}
		query += "limit ? offset ?;";
		//System.out.println("JerryDebug:query:"+query);
		
		Integer serial = 1;
		PreparedStatement ps = connection.prepareStatement(query);
		
		if (guest.getActive()!=null ){
			ps.setBoolean(serial++,guest.getActive());
		}
		if (guest.getDescription()!=null && !"".equals(guest.getDescription())){
			ps.setString(serial++,"%"+guest.getDescription()+"%");
		}
		if (guest.getGender()!=null && !"".equals(guest.getGender())){
			ps.setString(serial++,"%"+guest.getGender()+"%");
		}
		if (guest.getGuestName()!=null && !"".equals(guest.getGuestName())){
			ps.setString(serial++,"%"+guest.getGuestName()+"%");
		}
		ps.setLong(serial++, limit);
		ps.setLong(serial++,start);
		
		ResultSet result = ps.executeQuery();
		ArrayList<Guest> list = new ArrayList<Guest>();
		while(result.next()) {
			//Build the list object.
			Guest guest2 = new Guest();
			guest2.setGender(result.getString("gender"));
			guest2.setDescription(result.getString("description"));
			guest2.setGuestName(result.getString("guest_name"));
			guest2.setActive(result.getBoolean("active"));
			guest2.setId(result.getLong("id"));
			//Build the object list.
			list.add(guest2);
		}
		return list;
		} catch (Exception e){
			e.printStackTrace();
			return null;
		}
	}

	public List<Guest> searchGuestsByGuestName(Connection connection,String guestName) throws Exception{
		try {
			String query = "select gender,description,guest_name,active,id from mb_guests where guest_name like ?";
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setString(1,"%"+guestName+"%");
			ResultSet result = ps.executeQuery();
			ArrayList<Guest> list = new ArrayList<Guest>();
			while(result.next()) {
				//Build the list object.
				Guest guest = new Guest();
				guest.setGender(result.getString("gender"));
				guest.setDescription(result.getString("description"));
				guest.setGuestName(result.getString("guest_name"));
				guest.setActive(result.getBoolean("active"));
				guest.setId(result.getLong("id"));
				//Build the object list.
				list.add(guest);
			}
			return list;
		} catch (Exception e){
			e.printStackTrace();
			return null;
		}
	}

	public void softDeleteAllGuests(Connection connection,String ids) throws Exception{
		String [] idArr = ids.split(",");
		for (String idString : idArr){
			Long id = Long.valueOf(idString);
			softDeleteGuest(connection,id);
		}
	}

	public boolean softDeleteGuest(Connection connection,Long id) throws Exception{
		String query = "update mb_guests set active = false where id = ? ;";
		PreparedStatement ps = connection.prepareStatement(query);
		ps.setLong(1,id);
		int result = ps.executeUpdate();
		if (result > 0) {
			return true;
		}
		return false;
	}

	public Boolean toggleGuest(Connection connection,Long id) throws Exception{
		String query = "update mb_guests set active = not active where id = ? ";
		PreparedStatement ps = connection.prepareStatement(query);
		ps.setLong(1,id);
		int result = ps.executeUpdate();
		if (result > 0) {
			return true;
		}
		return false;
	}

	public boolean updateGuest(Connection connection,Guest guest) throws Exception{
		String query = "update mb_guests set gender = ? ,description = ? ,guest_name = ? ,active = ? where id = ?;";
		PreparedStatement ps = connection.prepareStatement(query);
		ps.setBoolean(1,guest.getActive());
		ps.setString(2,guest.getDescription());
		ps.setString(3,guest.getGender());
		ps.setString(4,guest.getGuestName());
		ps.setLong(5,guest.getId());
		int result = ps.executeUpdate();
		if (result > 0) {
			return true;
		}
		return false;
	}

}
