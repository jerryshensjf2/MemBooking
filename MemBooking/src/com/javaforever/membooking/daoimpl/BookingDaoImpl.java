package com.javaforever.membooking.daoimpl;

import java.util.List;

import com.javaforever.membooking.dao.BookingDao;
import com.javaforever.membooking.domain.Booking;

public class BookingDaoImpl implements BookingDao{
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
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteBooking(Long id) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean addBooking(Booking booking) throws Exception {
		// TODO Auto-generated method stub
		return false;
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
		// TODO Auto-generated method stub
		return null;
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
		// TODO Auto-generated method stub
		return null;
	}

}
