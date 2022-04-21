package com.javaforever.membooking.daoimpl;

import com.javaforever.membooking.dao.BookingDao;
import com.javaforever.membooking.domain.Booking;
import java.util.ArrayList;
import java.util.List;

public class BookingDaoImpl implements BookingDao{
	public void activateAllBookings(Connection connection,String ids) throws Exception{
		String [] idArr = ids.split(",");
		for (String idString : idArr){
			Long id = Long.valueOf(idString);
			activateBooking(connection,id);
		}
	}

	public boolean activateBooking(Connection connection,Long id) throws Exception{
		String query = "update mb_bookings set active = true where id = ? ;";
		PreparedStatement ps = connection.prepareStatement(query);
		ps.setLong(1,id);
		int result = ps.executeUpdate();
		if (result > 0) {
			return true;
		}
		return false;
	}

	public boolean addBooking(Connection connection,Booking booking) throws Exception{
		String query = "insert into mb_bookings ( active,booking_name,description,occu_date,room_id) values (?,?,?,?,?);";
		PreparedStatement ps = connection.prepareStatement(query,PreparedStatement.RETURN_GENERATED_KEYS);
		ps.setBoolean(1,booking.getActive());
		ps.setString(2,booking.getBookingName());
		ps.setString(3,booking.getDescription());
		ps.setString(4,booking.getOccuDate());
		ps.setLong(5,booking.getRoomId());
		int result = ps.executeUpdate();
		ResultSet rsNewId = ps.getGeneratedKeys();
		if (rsNewId.next()) { 
			long newId = rsNewId.getLong(1);
			booking.setId(newId);
		}
		if (result > 0) {
			return true;
		}
		return false;
	}

	public Long countActiveBookingRecords(Connection connection) throws Exception{
		String query = "select count(id) as countNum from mb_bookings where active = true;";
		PreparedStatement ps = connection.prepareStatement(query);
		ResultSet result = ps.executeQuery();
		Long recordcount = 0L;
		while(result.next()) {
			recordcount = result.getLong("countNum");
		}
		return recordcount;
	}

	public Long countAllBookingRecords(Connection connection) throws Exception{
		String query = "select count(id) as countNum from mb_bookings;";
		PreparedStatement ps = connection.prepareStatement(query);
		ResultSet result = ps.executeQuery();
		Long recordcount = 0L;
		while(result.next()) {
			recordcount = result.getLong("countNum");
		}
		return recordcount;
	}

	public int countBookingsPage(Connection connection,int pagesize) throws Exception{
		try {
			int pagecount = 1;
			double recordcount = 0;
			String query = "select count(id) as countNum from mb_bookings;";
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

	public Long countSearchBookingsByFieldsRecords(Connection connection,Booking booking){
		try {
		String query = " select count(*) countSum from mb_bookings where 1=1 ";
		if (booking.getActive()!=null ){
			query += " and active = ? ";
		}
		if (booking.getBookingName()!=null && !"".equals(booking.getBookingName())){
			query += " and booking_name like ? ";
		}
		if (booking.getDescription()!=null && !"".equals(booking.getDescription())){
			query += " and description like ? ";
		}
		if (booking.getOccuDate()!=null && !"".equals(booking.getOccuDate())){
			query += " and occu_date like ? ";
		}
		if (booking.getRoomId()!=null ){
			query += " and room_id = ? ";
		}
		query += ";";
		//System.out.println("JerryDebug:query:"+query);
		
		int serial = 1;
		PreparedStatement ps = connection.prepareStatement(query);
		
		if (booking.getActive()!=null ){
			ps.setBoolean(serial++,booking.getActive());
		}
		if (booking.getBookingName()!=null && !"".equals(booking.getBookingName())){
			ps.setString(serial++,"%"+booking.getBookingName()+"%");
		}
		if (booking.getDescription()!=null && !"".equals(booking.getDescription())){
			ps.setString(serial++,"%"+booking.getDescription()+"%");
		}
		if (booking.getOccuDate()!=null && !"".equals(booking.getOccuDate())){
			ps.setString(serial++,"%"+booking.getOccuDate()+"%");
		}
		if (booking.getRoomId()!=null ){
			ps.setLong(serial++,booking.getRoomId());
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

	public void deleteAllBookings(Connection connection,String ids) throws Exception{
		String [] idArr = ids.split(",");
		for (String idString : idArr){
			Long id = Long.valueOf(idString);
			deleteBooking(connection,id);
		}
	}

	public boolean deleteBooking(Connection connection,Long id) throws Exception{
		String query = "delete from mb_bookings where id = ?;";
		PreparedStatement ps = connection.prepareStatement(query);
		ps.setLong(1,id);
		int result = ps.executeUpdate();
		if (result > 0) {
			return true;
		}
		return false;
	}

	public Booking findBookingByBookingName(Connection connection,String bookingName) throws Exception{
		try {
			String query = "select room_id,occu_date,description,booking_name,active,id from mb_bookings where booking_name = ?;";
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setString(1,bookingName);
			ResultSet result = ps.executeQuery();
			Booking booking = new Booking();
			result.last();
			//Build the object.
			booking.setRoomId(result.getLong("room_id"));
			booking.setOccuDate(result.getString("occu_date"));
			booking.setDescription(result.getString("description"));
			booking.setBookingName(result.getString("booking_name"));
			booking.setActive(result.getBoolean("active"));
			booking.setId(result.getLong("id"));
			return booking;
		} catch (Exception e){
			e.printStackTrace();
			return null;
		}
	}

	public Booking findBookingById(Connection connection,Long id) throws Exception{
		try {
			String query = "select room_id,occu_date,description,booking_name,active,id from mb_bookings where id = ?;";
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setLong(1,id);
			ResultSet result = ps.executeQuery();
			Booking booking = new Booking();
			result.last();
			//Build the object.
			booking.setRoomId(result.getLong("room_id"));
			booking.setOccuDate(result.getString("occu_date"));
			booking.setDescription(result.getString("description"));
			booking.setBookingName(result.getString("booking_name"));
			booking.setActive(result.getBoolean("active"));
			booking.setId(result.getLong("id"));
			return booking;
		} catch (Exception e){
			e.printStackTrace();
			return null;
		}
	}

	public List<Booking> listActiveBookings(Connection connection) throws Exception{
		try {
			String query = "select room_id,occu_date,description,booking_name,active,id from mb_bookings where active = true;";
			PreparedStatement ps = connection.prepareStatement(query);
			ResultSet result = ps.executeQuery();
			ArrayList<Booking> list = new ArrayList<Booking>();
			while(result.next()) {
				//Build the list object.
				Booking booking = new Booking();
				booking.setRoomId(result.getLong("room_id"));
				booking.setOccuDate(result.getString("occu_date"));
				booking.setDescription(result.getString("description"));
				booking.setBookingName(result.getString("booking_name"));
				booking.setActive(result.getBoolean("active"));
				booking.setId(result.getLong("id"));
				//Build the object list.
				list.add(booking);
			}
			return list;
		} catch (Exception e){
			e.printStackTrace();
			return null;
		}
	}

	public List<Booking> listAllBookings(Connection connection) throws Exception{
		try {
			String query = "select room_id,occu_date,description,booking_name,active,id from mb_bookings;";
			PreparedStatement ps = connection.prepareStatement(query);
			ResultSet result = ps.executeQuery();
			ArrayList<Booking> list = new ArrayList<Booking>();
			while(result.next()) {
				//Build the list object.
				Booking booking = new Booking();
				booking.setRoomId(result.getLong("room_id"));
				booking.setOccuDate(result.getString("occu_date"));
				booking.setDescription(result.getString("description"));
				booking.setBookingName(result.getString("booking_name"));
				booking.setActive(result.getBoolean("active"));
				booking.setId(result.getLong("id"));
				//Build the object list.
				list.add(booking);
			}
			return list;
		} catch (Exception e){
			e.printStackTrace();
			return new ArrayList<Booking>();
		}
	}

	public List<Booking> listAllBookingsByPage(Connection connection,int pagesize,int pagenum) throws Exception{
		try {
			String query = "select room_id,occu_date,description,booking_name,active,id from mb_bookings limit ?,?;";
			int limitstart = (pagenum-1)*pagesize;
			int limitcount = pagesize;
			PreparedStatement ps = connection.prepareStatement(query);
			int pagecount = this.countBookingsPage(connection,pagesize);
			if (pagenum <= 1) pagenum = 1;
			if (pagenum >= pagecount) pagenum = pagecount;
			ps.setInt(1,limitstart);
			ps.setInt(2,limitcount);
			ResultSet result = ps.executeQuery();
			ArrayList<Booking> list = new ArrayList<Booking>();
			while(result.next()) {
				//Build the list object.
				Booking booking = new Booking();
				booking.setRoomId(result.getLong("room_id"));
				booking.setOccuDate(result.getString("occu_date"));
				booking.setDescription(result.getString("description"));
				booking.setBookingName(result.getString("booking_name"));
				booking.setActive(result.getBoolean("active"));
				booking.setId(result.getLong("id"));
				//Build the object list.
				list.add(booking);
			}
			return list;
		} catch (Exception e){
			e.printStackTrace();
			return new ArrayList<Booking>();
		}
	}

	public List<Booking> searchBookingsByBookingName(Connection connection,String bookingName) throws Exception{
		try {
			String query = "select room_id,occu_date,description,booking_name,active,id from mb_bookings where booking_name like ?";
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setString(1,"%"+bookingName+"%");
			ResultSet result = ps.executeQuery();
			ArrayList<Booking> list = new ArrayList<Booking>();
			while(result.next()) {
				//Build the list object.
				Booking booking = new Booking();
				booking.setRoomId(result.getLong("room_id"));
				booking.setOccuDate(result.getString("occu_date"));
				booking.setDescription(result.getString("description"));
				booking.setBookingName(result.getString("booking_name"));
				booking.setActive(result.getBoolean("active"));
				booking.setId(result.getLong("id"));
				//Build the object list.
				list.add(booking);
			}
			return list;
		} catch (Exception e){
			e.printStackTrace();
			return null;
		}
	}

	public List<Booking> searchBookingsByFieldsByPage(Connection connection,Booking booking,Long pagenum,Long pagesize) throws Exception{
		try {
		Long start = (pagenum-1)*pagesize;
		Long limit = pagesize;
		String query = " select room_id,occu_date,description,booking_name,active,id from mb_bookings where 1=1 ";
		if (booking.getActive()!=null ){
			query += " and active = ? ";
		}
		if (booking.getBookingName()!=null && !"".equals(booking.getBookingName())){
			query += " and booking_name like ? ";
		}
		if (booking.getDescription()!=null && !"".equals(booking.getDescription())){
			query += " and description like ? ";
		}
		if (booking.getOccuDate()!=null && !"".equals(booking.getOccuDate())){
			query += " and occu_date like ? ";
		}
		if (booking.getRoomId()!=null ){
			query += " and room_id = ? ";
		}
		query += "limit ? offset ?;";
		//System.out.println("JerryDebug:query:"+query);
		
		Integer serial = 1;
		PreparedStatement ps = connection.prepareStatement(query);
		
		if (booking.getActive()!=null ){
			ps.setBoolean(serial++,booking.getActive());
		}
		if (booking.getBookingName()!=null && !"".equals(booking.getBookingName())){
			ps.setString(serial++,"%"+booking.getBookingName()+"%");
		}
		if (booking.getDescription()!=null && !"".equals(booking.getDescription())){
			ps.setString(serial++,"%"+booking.getDescription()+"%");
		}
		if (booking.getOccuDate()!=null && !"".equals(booking.getOccuDate())){
			ps.setString(serial++,"%"+booking.getOccuDate()+"%");
		}
		if (booking.getRoomId()!=null ){
			ps.setLong(serial++,booking.getRoomId());
		}
		ps.setLong(serial++, limit);
		ps.setLong(serial++,start);
		
		ResultSet result = ps.executeQuery();
		ArrayList<Booking> list = new ArrayList<Booking>();
		while(result.next()) {
			//Build the list object.
			Booking booking2 = new Booking();
			booking2.setRoomId(result.getLong("room_id"));
			booking2.setOccuDate(result.getString("occu_date"));
			booking2.setDescription(result.getString("description"));
			booking2.setBookingName(result.getString("booking_name"));
			booking2.setActive(result.getBoolean("active"));
			booking2.setId(result.getLong("id"));
			//Build the object list.
			list.add(booking2);
		}
		return list;
		} catch (Exception e){
			e.printStackTrace();
			return null;
		}
	}

	public void softDeleteAllBookings(Connection connection,String ids) throws Exception{
		String [] idArr = ids.split(",");
		for (String idString : idArr){
			Long id = Long.valueOf(idString);
			softDeleteBooking(connection,id);
		}
	}

	public boolean softDeleteBooking(Connection connection,Long id) throws Exception{
		String query = "update mb_bookings set active = false where id = ? ;";
		PreparedStatement ps = connection.prepareStatement(query);
		ps.setLong(1,id);
		int result = ps.executeUpdate();
		if (result > 0) {
			return true;
		}
		return false;
	}

	public Boolean toggleBooking(Connection connection,Long id) throws Exception{
		String query = "update mb_bookings set active = not active where id = ? ";
		PreparedStatement ps = connection.prepareStatement(query);
		ps.setLong(1,id);
		int result = ps.executeUpdate();
		if (result > 0) {
			return true;
		}
		return false;
	}

	public boolean updateBooking(Connection connection,Booking booking) throws Exception{
		String query = "update mb_bookings set room_id = ? ,occu_date = ? ,description = ? ,booking_name = ? ,active = ? where id = ?;";
		PreparedStatement ps = connection.prepareStatement(query);
		ps.setBoolean(1,booking.getActive());
		ps.setString(2,booking.getBookingName());
		ps.setString(3,booking.getDescription());
		ps.setLong(4,booking.getId());
		ps.setString(5,booking.getOccuDate());
		ps.setLong(6,booking.getRoomId());
		int result = ps.executeUpdate();
		if (result > 0) {
			return true;
		}
		return false;
	}

}
