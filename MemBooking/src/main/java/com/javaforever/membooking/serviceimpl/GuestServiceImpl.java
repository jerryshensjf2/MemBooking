package com.javaforever.membooking.serviceimpl;

import com.javaforever.membooking.dao.GuestDao;
import com.javaforever.membooking.daoimpl.GuestDaoImpl;
import com.javaforever.membooking.domain.Guest;
import com.javaforever.membooking.service.GuestService;
import java.util.List;

public class GuestServiceImpl implements GuestService {
	protected GuestDao dao = new GuestDaoImpl();

	@Override
	public void activateAllGuests(String ids) throws Exception {
		dao.activateAllGuests(ids);
	}

	@Override
	public boolean activateGuest(Long id) throws Exception {
		return dao.activateGuest(id);
	}

	@Override
	public boolean addGuest(Guest guest) throws Exception {
		return dao.addGuest(guest);
	}

	@Override
	public Long countActiveGuestRecords() throws Exception {
		return dao.countActiveGuestRecords();
	}

	@Override
	public Long countAllGuestRecords() throws Exception {
		return dao.countAllGuestRecords();
	}

	@Override
	public int countGuestsPage(int pagesize) throws Exception {
		return dao.countGuestsPage(pagesize);
	}

	@Override
	public Long countSearchGuestsByFieldsRecords(Guest guest) throws Exception {
		return dao.countSearchGuestsByFieldsRecords(guest);
	}

	@Override
	public void deleteAllGuests(String ids) throws Exception {
		dao.deleteAllGuests(ids);
	}

	@Override
	public boolean deleteGuest(Long id) throws Exception {
		return dao.deleteGuest(id);
	}

	@Override
	public Guest findGuestByGuestName(String guestName) throws Exception {
		return dao.findGuestByGuestName(guestName);
	}

	@Override
	public Guest findGuestById(Long id) throws Exception {
		return dao.findGuestById(id);
	}

	@Override
	public List<Guest> listActiveGuests() throws Exception {
		return dao.listActiveGuests();
	}

	@Override
	public List<Guest> listAllGuests() throws Exception {
		return dao.listAllGuests();
	}

	@Override
	public List<Guest> listAllGuestsByPage(int pagesize, int pagenum) throws Exception {
		return dao.listAllGuestsByPage(pagesize, pagenum);
	}

	@Override
	public List<Guest> searchGuestsByFieldsByPage(Guest guest, Long pagenum, Long pagesize) throws Exception {
		return dao.searchGuestsByFieldsByPage(guest, pagenum, pagesize);
	}

	@Override
	public List<Guest> searchGuestsByGuestName(String guestName) throws Exception {
		return dao.searchGuestsByGuestName(guestName);
	}

	@Override
	public void softDeleteAllGuests(String ids) throws Exception {
		dao.softDeleteAllGuests(ids);
	}

	@Override
	public boolean softDeleteGuest(Long id) throws Exception {
		return dao.softDeleteGuest(id);
	}

	@Override
	public Boolean toggleGuest(Long id) throws Exception {
		return dao.toggleGuest(id);
	}

	@Override
	public Boolean toggleOneGuest(Long id) throws Exception {
		Guest guest = dao.findGuestById(id);
		if (guest.getActive() == false) {
			dao.toggleGuest(id);
		} else {
			Long count = dao.countActiveGuestRecords();
			if (count > 1) {
				dao.toggleGuest(id);
			}
		}
		return true;
	}

	@Override
	public boolean updateGuest(Guest guest) throws Exception {
		return dao.updateGuest(guest);
	}

}
