package com.javaforever.membooking.serviceimpl;

import java.sql.Connection;
import java.util.List;

import com.javaforever.membooking.dao.BookingDao;
import com.javaforever.membooking.daoimpl.BookingDaoImpl;
import com.javaforever.membooking.database.DBConf;
import com.javaforever.membooking.domain.Booking;
import com.javaforever.membooking.service.BookingService;

public class BookingServiceImpl implements BookingService{
	protected BookingDao dao = new BookingDaoImpl();
	public void activateAllBookings(String ids) throws Exception{
		try (Connection connection = DBConf.initDB()) {
			dao.activateAllBookings(connection,ids);
		}
	}

	public boolean activateBooking(Long id) throws Exception{
		try (Connection connection = DBConf.initDB()) {
			return dao.activateBooking(connection,id);
		}
	}

	public boolean addBooking(Booking booking) throws Exception{
		try (Connection connection = DBConf.initDB()) {
			return dao.addBooking(connection,booking);
		}
	}

	@Override
	public Long countActiveBookingRecords() throws Exception{
		try (Connection connection = DBConf.initDB()) {
			return dao.countActiveBookingRecords(connection);
		}
	}

	@Override
	public Long countAllBookingRecords() throws Exception{
		try (Connection connection = DBConf.initDB()) {
			return dao.countAllBookingRecords(connection);
		}
	}

	public int countBookingsPage(int pagesize) throws Exception{
		try (Connection connection = DBConf.initDB()) {
			return dao.countBookingsPage(connection,pagesize);
		}
	}

	@Override
	public Long countSearchBookingsByFieldsRecords(Booking booking) throws Exception{
		try (Connection connection = DBConf.initDB()) {
			return dao.countSearchBookingsByFieldsRecords(connection,booking);
		}
	}

	public void deleteAllBookings(String ids) throws Exception{
		try (Connection connection = DBConf.initDB()) {
			dao.deleteAllBookings(connection,ids);
		}
	}

	public boolean deleteBooking(Long id) throws Exception{
		try (Connection connection = DBConf.initDB()) {
			return dao.deleteBooking(connection,id);
		}
	}

	public Booking findBookingByBookingName(String bookingName) throws Exception{
		try (Connection connection = DBConf.initDB()) {
			return dao.findBookingByBookingName(connection,bookingName);
		}
	}

	public Booking findBookingById(Long id) throws Exception{
		try (Connection connection = DBConf.initDB()) {
			return dao.findBookingById(connection,id);
		}
	}

	public List<Booking> listActiveBookings() throws Exception{
		try (Connection connection = DBConf.initDB()) {
			return dao.listActiveBookings(connection);
		}
	}

	public List<Booking> listAllBookings() throws Exception{
		try (Connection connection = DBConf.initDB()) {
			return dao.listAllBookings(connection);
		}
	}

	public List<Booking> listAllBookingsByPage(int pagesize,int pagenum) throws Exception{
		try (Connection connection = DBConf.initDB()) {
			return dao.listAllBookingsByPage(connection,pagesize,pagenum);
		}
	}

	public List<Booking> searchBookingsByBookingName(String bookingName) throws Exception{
		try (Connection connection = DBConf.initDB()) {
			return dao.searchBookingsByBookingName(connection,bookingName);
		}
	}

	public List<Booking> searchBookingsByFieldsByPage(Booking booking,Long pagenum,Long pagesize) throws Exception{
		try (Connection connection = DBConf.initDB()) {
			return dao.searchBookingsByFieldsByPage(connection,booking,pagenum,pagesize);
		}
	}

	public void softDeleteAllBookings(String ids) throws Exception{
		try (Connection connection = DBConf.initDB()) {
			dao.softDeleteAllBookings(connection,ids);
		}
	}

	public boolean softDeleteBooking(Long id) throws Exception{
		try (Connection connection = DBConf.initDB()) {
			return dao.softDeleteBooking(connection,id);
		}
	}

	public Boolean toggleBooking(Long id) throws Exception{
		try (Connection connection = DBConf.initDB()) {
			return dao.toggleBooking(connection,id);
		}
	}

	public Boolean toggleOneBooking(Long id) throws Exception{
		try (Connection connection = DBConf.initDB()) {
			Booking booking = dao.findBookingById(connection,id);
			if (booking.getActive()==false) {
				dao.toggleBooking(connection,id);
			}
			else {
				Long count = dao.countActiveBookingRecords(connection);
				if (count > 1){
					dao.toggleBooking(connection,id);
				}
			}
			return true;
		}
	}

	public boolean updateBooking(Booking booking) throws Exception{
		try (Connection connection = DBConf.initDB()) {
			return dao.updateBooking(connection,booking);
		}
	}

}
