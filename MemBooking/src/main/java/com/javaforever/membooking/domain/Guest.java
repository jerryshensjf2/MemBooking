package com.javaforever.membooking.domain;


public class Guest  {
	protected String gender;
	protected String description;
	protected String guestName;
	protected Boolean active;
	protected Long id;

	public Boolean getActive(){
		return this.active;
	}

	public String getDescription(){
		return this.description;
	}

	public String getGender(){
		return this.gender;
	}

	public String getGuestName(){
		return this.guestName;
	}

	public Long getId(){
		return this.id;
	}

	public void setActive(Boolean active){
		this.active = active;
	}

	public void setDescription(String description){
		this.description = description;
	}

	public void setGender(String gender){
		this.gender = gender;
	}

	public void setGuestName(String guestName){
		this.guestName = guestName;
	}

	public void setId(Long id){
		this.id = id;
	}

}
