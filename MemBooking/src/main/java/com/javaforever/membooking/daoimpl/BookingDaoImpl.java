package com.javaforever.membooking.daoimpl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.javaforever.membooking.dao.BookingDao;
import com.javaforever.membooking.domain.Booking;
import com.javaforever.membooking.exception.ValidateException;

public class BookingDaoImpl implements BookingDao{
	public static List<Booking> db = new ArrayList<>();
	
	static {
		try{
			Booking booking0 = new Booking();
			booking0.setId(1L);
			booking0.setGuestId(1L);
			booking0.setRoomId(1L);
			booking0.setBookingName("Jerry booking");
			booking0.setDescription("");
			booking0.setActive(true);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			booking0.setOccuDate(sdf.parse("2022-4-23"));
			
			Booking booking1 = new Booking();
			booking1.setId(2L);
			booking1.setGuestId(2L);
			booking1.setRoomId(2L);
			booking1.setBookingName("Mala booking");
			booking1.setDescription("");
			booking1.setActive(true);
			booking1.setOccuDate(sdf.parse("2022-4-23"));
			
			db.add(booking0);
			db.add(booking1);
		} catch (Exception e) {
			
		}
	}
	
	public void activateAllBookings(String ids) throws Exception{
		String [] idArr = ids.split(",");
		for (String idString : idArr){
			Long id = Long.valueOf(idString);
			activateBooking(id);
		}
	}

	public void deleteAllBookings(String ids) throws Exception{
		String [] idArr = ids.split(",");
		for (String idString : idArr){
			Long id = Long.valueOf(idString);
			deleteBooking(id);
		}
	}


	public void softDeleteAllBookings(String ids) throws Exception{
		String [] idArr = ids.split(",");
		for (String idString : idArr){
			Long id = Long.valueOf(idString);
			softDeleteBooking(id);
		}
	}

	@Override
	public List<Booking> listAllBookings() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean updateBooking(Booking booking) throws Exception {
		if (booking.getId() ==null) return false;
		for (int i = 0; i < db.size(); i++) {
            if(db.get(i).getId()==booking.getId()){
                db.remove(i);
                db.add(i, booking);
                return true;
            }
        }
		return false;
	}

	@Override
	public boolean deleteBooking(Long id) throws Exception {
		for (int i = 0; i < db.size(); i++) {
            if(db.get(i).getId()==id){
                db.remove(i);
                return true;
            }
        }
		return false;
	}

	@Override
	public boolean addBooking(Booking booking) throws Exception {
		Long index = nextIndex();
		booking.setId(index);
		db.add(booking);
		return true;
	}

	@Override
	public boolean softDeleteBooking(Long id) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Booking findBookingById(Long id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Booking findBookingByBookingName(String bookingName) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Booking> searchBookingsByBookingName(String bookingName) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Booking> listActiveBookings() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Booking> listAllBookingsByPage(int pagesize, int pagenum) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean toggleBooking(Long id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Booking> searchBookingsByFieldsByPage(Booking booking, Long pagenum, Long pagesize) throws Exception {
		return db;
	}

	@Override
	public boolean activateBooking(Long id) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int countBookingsPage(int pagesize) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Long countActiveBookingRecords() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long countAllBookingRecords() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long countSearchBookingsByFieldsRecords(Booking booking) throws Exception {
		return Long.valueOf(db.size());
	}
	
	private Long nextIndex(){
		Long index = 0L;
		for (Booking b:db){
			if (b.getId()>index) index = b.getId();
		}
		return index +1L;
	}
	
	
	public boolean validate(Booking booking) throws ValidateException{
		for (Booking b:db) {
			if ((booking.getId()!=null && b.getId()==booking.getId())||!b.getOccuDate().equals(booking.getOccuDate())) continue;
			else {
				if (b.getGuestId()==booking.getGuestId()) {
					throw new ValidateException("Guest had booked a room this day.");
				}else if (b.getRoomId()==booking.getRoomId()) {
					throw new ValidateException("Room had been booked this day.");
				}
			}
		}
		return true;
	}

}
