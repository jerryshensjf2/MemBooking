package com.javaforever.membooking.domain;


public class Room  {
	protected String roomNo;
	protected String description;
	protected String roomName;
	protected Boolean active;
	protected Long id;

	public Boolean getActive(){
		return this.active;
	}

	public String getDescription(){
		return this.description;
	}

	public Long getId(){
		return this.id;
	}

	public String getRoomName(){
		return this.roomName;
	}

	public String getRoomNo(){
		return this.roomNo;
	}

	public void setActive(Boolean active){
		this.active = active;
	}

	public void setDescription(String description){
		this.description = description;
	}

	public void setId(Long id){
		this.id = id;
	}

	public void setRoomName(String roomName){
		this.roomName = roomName;
	}

	public void setRoomNo(String roomNo){
		this.roomNo = roomNo;
	}

}
