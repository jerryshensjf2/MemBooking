package com.javaforever.membooking.domain;


public class Booking  {
	protected Long roomId;
	protected Long guestId;
	protected String occuDate;
	protected String description;
	protected String bookingName;
	protected Boolean active;
	protected Long id;

	public Boolean getActive(){
		return this.active;
	}

	public String getBookingName(){
		return this.bookingName;
	}

	public String getDescription(){
		return this.description;
	}

	public Long getId(){
		return this.id;
	}

	public String getOccuDate(){
		return this.occuDate;
	}

	public Long getRoomId(){
		return this.roomId;
	}

	public void setActive(Boolean active){
		this.active = active;
	}

	public void setBookingName(String bookingName){
		this.bookingName = bookingName;
	}

	public void setDescription(String description){
		this.description = description;
	}

	public void setId(Long id){
		this.id = id;
	}

	public void setOccuDate(String occuDate){
		this.occuDate = occuDate;
	}

	public void setRoomId(Long roomId){
		this.roomId = roomId;
	}

	public Long getGuestId() {
		return guestId;
	}

	public void setGuestId(Long guestId) {
		this.guestId = guestId;
	}

}
