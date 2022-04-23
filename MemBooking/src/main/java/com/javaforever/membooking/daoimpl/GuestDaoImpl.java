package com.javaforever.membooking.daoimpl;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import com.javaforever.membooking.dao.GuestDao;
import com.javaforever.membooking.domain.Guest;

public class GuestDaoImpl implements GuestDao{
	public static List<Guest> db = new CopyOnWriteArrayList<>();
	
	static {
		Guest guest0 = new Guest();
		guest0.setId(1L);
		guest0.setGuestName("Jerry");
		guest0.setGender("Male");
		guest0.setActive(true);
		guest0.setDescription("");
		
		Guest guest1 = new Guest();
		guest1.setId(2L);
		guest1.setGuestName("Mala");
		guest1.setGender("Female");
		guest1.setActive(true);
		guest1.setDescription("");
		
		Guest guest2 = new Guest();
		guest2.setId(3L);
		guest2.setGuestName("Linda");
		guest2.setGender("Female");
		guest2.setActive(true);
		guest2.setDescription("");
		
		Guest guest3 = new Guest();
		guest3.setId(4L);
		guest3.setGuestName("Peter");
		guest3.setGender("Male");
		guest3.setActive(true);
		guest3.setDescription("");
		
		db.add(guest0);
		db.add(guest1);
		db.add(guest2);
		db.add(guest3);
	}
	
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
		Long index = nextIndex();
		guest.setId(index);
		db.add(guest);
		return true;
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
		return Long.valueOf(db.size());
	}

	public void deleteAllGuests(String ids) throws Exception{
		String [] idArr = ids.split(",");
		for (String idString : idArr){
			Long id = Long.valueOf(idString);
			deleteGuest(id);
		}
	}

	public boolean deleteGuest(Long id) throws Exception{
		for (int i = 0; i < db.size(); i++) {
            if(db.get(i).getId()==id){
                db.remove(i);
                return true;
            }
        }
		return false;
	}

	public Guest findGuestByGuestName(String guestName) throws Exception{
		return null;
	}

	public Guest findGuestById(Long id) throws Exception{
		for (Guest g:db) {
			if (g.getId() == id) return g;
		}
		return null;
	}

	public List<Guest> listActiveGuests() throws Exception{
		return db;
	}

	public List<Guest> listAllGuests() throws Exception{
		return db;
	}

	public List<Guest> listAllGuestsByPage(int pagesize,int pagenum) throws Exception{
		return db;
	}

	public List<Guest> searchGuestsByFieldsByPage(Guest guest,Long pagenum,Long pagesize) throws Exception{
		try {
		Long start = (pagenum-1)*pagesize;
		Long limit = pagesize;

		return db;
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
		if (guest.getId() ==null) return false;
		for (int i = 0; i < db.size(); i++) {
            if(db.get(i).getId()==guest.getId()){
                db.remove(i);
                db.add(i, guest);
                return true;
            }
        }
		return false;
	}

	private Long nextIndex(){
		Long index = 0L;
		for (Guest g:db){
			if (g.getId()>index) index = g.getId();
		}
		return index +1L;
	}
}
