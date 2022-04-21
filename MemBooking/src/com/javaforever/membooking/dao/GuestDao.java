package com.javaforever.membooking.dao;

import com.javaforever.membooking.domain.Guest;
import java.sql.Connection;
import java.util.List;

public interface GuestDao{
	public List<Guest> listAllGuests() throws Exception;
	public boolean updateGuest(Guest guest) throws Exception;
	public boolean deleteGuest(Long id) throws Exception;
	public boolean addGuest(Guest guest) throws Exception;
	public boolean softDeleteGuest(Long id) throws Exception;
	public Guest findGuestById(Long id) throws Exception;
	public Guest findGuestByGuestName(String guestName) throws Exception;
	public List<Guest> searchGuestsByGuestName(String guestName) throws Exception;
	public List<Guest> listActiveGuests() throws Exception;
	public List<Guest> listAllGuestsByPage(int pagesize,int pagenum) throws Exception;
	public void deleteAllGuests(String ids) throws Exception;
	public void softDeleteAllGuests(String ids) throws Exception;
	public Boolean toggleGuest(Long id) throws Exception;
	public List<Guest> searchGuestsByFieldsByPage(Guest guest,Long pagenum,Long pagesize) throws Exception;
	public boolean activateGuest(Long id) throws Exception;
	public void activateAllGuests(String ids) throws Exception;
	public int countGuestsPage(int pagesize) throws Exception;
	public Long countActiveGuestRecords() throws Exception;
	public Long countAllGuestRecords() throws Exception;
	public Long countSearchGuestsByFieldsRecords(Guest guest) throws Exception;
}
