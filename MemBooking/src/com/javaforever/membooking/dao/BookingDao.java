package com.javaforever.membooking.dao;

import com.javaforever.membooking.domain.Booking;
import java.sql.Connection;
import java.util.List;

public interface BookingDao{
	public List<Booking> listAllBookings(Connection connection) throws Exception;
	public boolean updateBooking(Connection connection,Booking booking) throws Exception;
	public boolean deleteBooking(Connection connection,Long id) throws Exception;
	public boolean addBooking(Connection connection,Booking booking) throws Exception;
	public boolean softDeleteBooking(Connection connection,Long id) throws Exception;
	public Booking findBookingById(Connection connection,Long id) throws Exception;
	public Booking findBookingByBookingName(Connection connection,String bookingName) throws Exception;
	public List<Booking> searchBookingsByBookingName(Connection connection,String bookingName) throws Exception;
	public List<Booking> listActiveBookings(Connection connection) throws Exception;
	public List<Booking> listAllBookingsByPage(Connection connection,int pagesize,int pagenum) throws Exception;
	public void deleteAllBookings(Connection connection,String ids) throws Exception;
	public void softDeleteAllBookings(Connection connection,String ids) throws Exception;
	public Boolean toggleBooking(Connection connection,Long id) throws Exception;
	public List<Booking> searchBookingsByFieldsByPage(Connection connection,Booking booking,Long pagenum,Long pagesize) throws Exception;
	public boolean activateBooking(Connection connection,Long id) throws Exception;
	public void activateAllBookings(Connection connection,String ids) throws Exception;
	public int countBookingsPage(Connection connection,int pagesize) throws Exception;
	public Long countActiveBookingRecords(Connection connection) throws Exception;
	public Long countAllBookingRecords(Connection connection) throws Exception;
	public Long countSearchBookingsByFieldsRecords(Connection connection,Booking booking) throws Exception;
}
