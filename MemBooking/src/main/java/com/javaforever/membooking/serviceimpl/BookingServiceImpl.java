package com.javaforever.membooking.serviceimpl;

import java.util.List;

import com.javaforever.membooking.dao.BookingDao;
import com.javaforever.membooking.daoimpl.BookingDaoImpl;
import com.javaforever.membooking.domain.Booking;
import com.javaforever.membooking.service.BookingService;

public class BookingServiceImpl implements BookingService{
	protected BookingDao dao = new BookingDaoImpl();
	public void activateAllBookings(String ids) throws Exception{		
			dao.activateAllBookings(ids);
	}

	public boolean activateBooking(Long id) throws Exception{		
			return dao.activateBooking(id);
	}

	public boolean addBooking(Booking booking) throws Exception{		
			return dao.addBooking(booking);
	}

	@Override
	public Long countActiveBookingRecords() throws Exception{		
			return dao.countActiveBookingRecords();
	}

	@Override
	public Long countAllBookingRecords() throws Exception{		
			return dao.countAllBookingRecords();
	}

	public int countBookingsPage(int pagesize) throws Exception{		
			return dao.countBookingsPage(pagesize);
	}

	@Override
	public Long countSearchBookingsByFieldsRecords(Booking booking) throws Exception{		
			return dao.countSearchBookingsByFieldsRecords(booking);
	}

	public void deleteAllBookings(String ids) throws Exception{		
			dao.deleteAllBookings(ids);
	}

	public boolean deleteBooking(Long id) throws Exception{		
			return dao.deleteBooking(id);
	}

	public Booking findBookingByBookingName(String bookingName) throws Exception{
			return dao.findBookingByBookingName(bookingName);
	}

	public Booking findBookingById(Long id) throws Exception{
			return dao.findBookingById(id);
	}

	public List<Booking> listActiveBookings() throws Exception{		
			return dao.listActiveBookings();
	}

	public List<Booking> listAllBookings() throws Exception{		
			return dao.listAllBookings();
	}

	public List<Booking> listAllBookingsByPage(int pagesize,int pagenum) throws Exception{		
			return dao.listAllBookingsByPage(pagesize,pagenum);
	}

	public List<Booking> searchBookingsByBookingName(String bookingName) throws Exception{		
			return dao.searchBookingsByBookingName(bookingName);
	}

	public List<Booking> searchBookingsByFieldsByPage(Booking booking,Long pagenum,Long pagesize) throws Exception{
			return dao.searchBookingsByFieldsByPage(booking,pagenum,pagesize);
	}

	public void softDeleteAllBookings(String ids) throws Exception{		
			dao.softDeleteAllBookings(ids);
	}

	public boolean softDeleteBooking(Long id) throws Exception{		
			return dao.softDeleteBooking(id);
	}

	public Boolean toggleBooking(Long id) throws Exception{		
			return dao.toggleBooking(id);
	}

	public Boolean toggleOneBooking(Long id) throws Exception{		
			Booking booking = dao.findBookingById(id);
			if (booking.getActive()==false) {
				dao.toggleBooking(id);
			}
			else {
				Long count = dao.countActiveBookingRecords();
				if (count > 1){
					dao.toggleBooking(id);
				}
			}
			return true;
	}

	public boolean updateBooking(Booking booking) throws Exception{		
			return dao.updateBooking(booking);
	}

}
