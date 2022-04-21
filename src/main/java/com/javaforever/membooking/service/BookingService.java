package com.javaforever.membooking.service;

import com.javaforever.membooking.domain.Booking;
import java.util.List;

public interface BookingService{
	public List<Booking> listAllBookings() throws Exception;
	public boolean updateBooking(Booking booking) throws Exception;
	public boolean deleteBooking(Long id) throws Exception;
	public boolean addBooking(Booking booking) throws Exception;
	public boolean softDeleteBooking(Long id) throws Exception;
	public Booking findBookingById(Long id) throws Exception;
	public Booking findBookingByBookingName(String bookingName) throws Exception;
	public List<Booking> searchBookingsByBookingName(String bookingName) throws Exception;
	public List<Booking> listActiveBookings() throws Exception;
	public List<Booking> listAllBookingsByPage(int pagesize,int pagenum) throws Exception;
	public void deleteAllBookings(String ids) throws Exception;
	public void softDeleteAllBookings(String ids) throws Exception;
	public Boolean toggleBooking(Long id) throws Exception;
	public Boolean toggleOneBooking(Long id) throws Exception;
	public List<Booking> searchBookingsByFieldsByPage(Booking booking,Long pagenum,Long pagesize) throws Exception;
	public boolean activateBooking(Long id) throws Exception;
	public void activateAllBookings(String ids) throws Exception;
	public int countBookingsPage(int pagesize) throws Exception;
	public Long countActiveBookingRecords() throws Exception;
	public Long countAllBookingRecords() throws Exception;
	public Long countSearchBookingsByFieldsRecords(Booking booking) throws Exception;
}
