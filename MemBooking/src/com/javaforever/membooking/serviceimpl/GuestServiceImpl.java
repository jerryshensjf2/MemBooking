package com.javaforever.membooking.serviceimpl;

import com.javaforever.membooking.dao.GuestDao;
import com.javaforever.membooking.daoimpl.GuestDaoImpl;
import com.javaforever.membooking.database.DBConf;
import com.javaforever.membooking.domain.Guest;
import com.javaforever.membooking.service.GuestService;
import java.sql.Connection;
import java.util.List;

public class GuestServiceImpl implements GuestService{
	protected GuestDao dao = new GuestDaoImpl();
	public void activateAllGuests(String ids) throws Exception{
		try (Connection connection = DBConf.initDB()) {
			dao.activateAllGuests(connection,ids);
		}
	}

	public boolean activateGuest(Long id) throws Exception{
		try (Connection connection = DBConf.initDB()) {
			return dao.activateGuest(connection,id);
		}
	}

	public boolean addGuest(Guest guest) throws Exception{
		try (Connection connection = DBConf.initDB()) {
			return dao.addGuest(connection,guest);
		}
	}

	@Override
	public Long countActiveGuestRecords() throws Exception{
		try (Connection connection = DBConf.initDB()) {
			return dao.countActiveGuestRecords(connection);
		}
	}

	@Override
	public Long countAllGuestRecords() throws Exception{
		try (Connection connection = DBConf.initDB()) {
			return dao.countAllGuestRecords(connection);
		}
	}

	public int countGuestsPage(int pagesize) throws Exception{
		try (Connection connection = DBConf.initDB()) {
			return dao.countGuestsPage(connection,pagesize);
		}
	}

	@Override
	public Long countSearchGuestsByFieldsRecords(Guest guest) throws Exception{
		try (Connection connection = DBConf.initDB()) {
			return dao.countSearchGuestsByFieldsRecords(connection,guest);
		}
	}

	public void deleteAllGuests(String ids) throws Exception{
		try (Connection connection = DBConf.initDB()) {
			dao.deleteAllGuests(connection,ids);
		}
	}

	public boolean deleteGuest(Long id) throws Exception{
		try (Connection connection = DBConf.initDB()) {
			return dao.deleteGuest(connection,id);
		}
	}

	public Guest findGuestByGuestName(String guestName) throws Exception{
		try (Connection connection = DBConf.initDB()) {
			return dao.findGuestByGuestName(connection,guestName);
		}
	}

	public Guest findGuestById(Long id) throws Exception{
		try (Connection connection = DBConf.initDB()) {
			return dao.findGuestById(connection,id);
		}
	}

	public List<Guest> listActiveGuests() throws Exception{
		try (Connection connection = DBConf.initDB()) {
			return dao.listActiveGuests(connection);
		}
	}

	public List<Guest> listAllGuests() throws Exception{
		try (Connection connection = DBConf.initDB()) {
			return dao.listAllGuests(connection);
		}
	}

	public List<Guest> listAllGuestsByPage(int pagesize,int pagenum) throws Exception{
		try (Connection connection = DBConf.initDB()) {
			return dao.listAllGuestsByPage(connection,pagesize,pagenum);
		}
	}

	public List<Guest> searchGuestsByFieldsByPage(Guest guest,Long pagenum,Long pagesize) throws Exception{
		try (Connection connection = DBConf.initDB()) {
			return dao.searchGuestsByFieldsByPage(connection,guest,pagenum,pagesize);
		}
	}

	public List<Guest> searchGuestsByGuestName(String guestName) throws Exception{
		try (Connection connection = DBConf.initDB()) {
			return dao.searchGuestsByGuestName(connection,guestName);
		}
	}

	public void softDeleteAllGuests(String ids) throws Exception{
		try (Connection connection = DBConf.initDB()) {
			dao.softDeleteAllGuests(connection,ids);
		}
	}

	public boolean softDeleteGuest(Long id) throws Exception{
		try (Connection connection = DBConf.initDB()) {
			return dao.softDeleteGuest(connection,id);
		}
	}

	public Boolean toggleGuest(Long id) throws Exception{
		try (Connection connection = DBConf.initDB()) {
			return dao.toggleGuest(connection,id);
		}
	}

	public Boolean toggleOneGuest(Long id) throws Exception{
		try (Connection connection = DBConf.initDB()) {
			Guest guest = dao.findGuestById(connection,id);
			if (guest.getActive()==false) {
				dao.toggleGuest(connection,id);
			}
			else {
				Long count = dao.countActiveGuestRecords(connection);
				if (count > 1){
					dao.toggleGuest(connection,id);
				}
			}
			return true;
		}
	}

	public boolean updateGuest(Guest guest) throws Exception{
		try (Connection connection = DBConf.initDB()) {
			return dao.updateGuest(connection,guest);
		}
	}

}
