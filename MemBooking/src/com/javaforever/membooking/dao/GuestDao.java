package com.javaforever.membooking.dao;

import com.javaforever.membooking.domain.Guest;
import java.sql.Connection;
import java.util.List;

public interface GuestDao{
	public List<Guest> listAllGuests(Connection connection) throws Exception;
	public boolean updateGuest(Connection connection,Guest guest) throws Exception;
	public boolean deleteGuest(Connection connection,Long id) throws Exception;
	public boolean addGuest(Connection connection,Guest guest) throws Exception;
	public boolean softDeleteGuest(Connection connection,Long id) throws Exception;
	public Guest findGuestById(Connection connection,Long id) throws Exception;
	public Guest findGuestByGuestName(Connection connection,String guestName) throws Exception;
	public List<Guest> searchGuestsByGuestName(Connection connection,String guestName) throws Exception;
	public List<Guest> listActiveGuests(Connection connection) throws Exception;
	public List<Guest> listAllGuestsByPage(Connection connection,int pagesize,int pagenum) throws Exception;
	public void deleteAllGuests(Connection connection,String ids) throws Exception;
	public void softDeleteAllGuests(Connection connection,String ids) throws Exception;
	public Boolean toggleGuest(Connection connection,Long id) throws Exception;
	public List<Guest> searchGuestsByFieldsByPage(Connection connection,Guest guest,Long pagenum,Long pagesize) throws Exception;
	public boolean activateGuest(Connection connection,Long id) throws Exception;
	public void activateAllGuests(Connection connection,String ids) throws Exception;
	public int countGuestsPage(Connection connection,int pagesize) throws Exception;
	public Long countActiveGuestRecords(Connection connection) throws Exception;
	public Long countAllGuestRecords(Connection connection) throws Exception;
	public Long countSearchGuestsByFieldsRecords(Connection connection,Guest guest) throws Exception;
}
