package com.javaforever.membooking.daoimpl;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import com.javaforever.membooking.dao.GuestDao;
import com.javaforever.membooking.domain.Guest;

public class GuestDaoImpl implements GuestDao{
	public void activateAllGuests(String ids) throws Exception{
		String [] idArr = ids.split(",");
		for (String idString : idArr){
			Long id = Long.valueOf(idString);
			activateGuest(id);
		}
	}

	public boolean activateGuest(Long id) throws Exception{
		return false;
	}

	public boolean addGuest(Guest guest) throws Exception{
		return false;
	}

	public Long countActiveGuestRecords() throws Exception{
		Long recordcount = 0L;
		return recordcount;
	}

	public Long countAllGuestRecords() throws Exception{
		Long recordcount = 0L;
		return recordcount;
	}

	public int countGuestsPage(int pagesize) throws Exception{
		int pagecount = 1;
		return pagecount;
	}

	public Long countSearchGuestsByFieldsRecords(Guest guest){
		return 2L;
	}

	public void deleteAllGuests(String ids) throws Exception{
		String [] idArr = ids.split(",");
		for (String idString : idArr){
			Long id = Long.valueOf(idString);
			deleteGuest(id);
		}
	}

	public boolean deleteGuest(Long id) throws Exception{
		return false;
	}

	public Guest findGuestByGuestName(String guestName) throws Exception{
		return null;
	}

	public Guest findGuestById(Long id) throws Exception{
		return null;
	}

	public List<Guest> listActiveGuests() throws Exception{
		return null;
	}

	public List<Guest> listAllGuests() throws Exception{
		return null;
	}

	public List<Guest> listAllGuestsByPage(int pagesize,int pagenum) throws Exception{
		return null;
	}

	public List<Guest> searchGuestsByFieldsByPage(Guest guest,Long pagenum,Long pagesize) throws Exception{
		try {
		Long start = (pagenum-1)*pagesize;
		Long limit = pagesize;
		CopyOnWriteArrayList<Guest> list = new CopyOnWriteArrayList<>();
		
		Guest guest0 = new Guest();
		guest0.setId(1L);
		guest0.setGuestName("jerry");
		guest0.setGender("Male");
		guest0.setActive(true);
		guest0.setDescription("");
		
		Guest guest1 = new Guest();
		guest1.setId(1L);
		guest1.setGuestName("mala");
		guest1.setGender("Female");
		guest1.setActive(true);
		guest1.setDescription("");
		
		list.add(guest0);
		list.add(guest1);

		return list;
		} catch (Exception e){
			e.printStackTrace();
			return null;
		}
	}

	public List<Guest> searchGuestsByGuestName(String guestName) throws Exception{
		return null;
	}

	public void softDeleteAllGuests(String ids) throws Exception{
		String [] idArr = ids.split(",");
		for (String idString : idArr){
			Long id = Long.valueOf(idString);
			softDeleteGuest(id);
		}
	}

	public boolean softDeleteGuest(Long id) throws Exception{
		return false;
	}

	public Boolean toggleGuest(Long id) throws Exception{
		return false;
	}

	public boolean updateGuest(Guest guest) throws Exception{
		return false;
	}

}
