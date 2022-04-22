package com.javaforever.membooking.exception;

public class ValidateException extends Exception{
	private static final long serialVersionUID = 2506542517173893138L;
	protected String info;
	
	public ValidateException(String info){
		super();
		this.info=info;
	}

	public String getMessage() {
		return info;
	}
}
