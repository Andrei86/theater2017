package com.shalkevich.andrei.training2017.dao.impl.db.exception;

public class DaoTheaterIncorrectCityException extends DaoException{ /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String message;
	
	public DaoTheaterIncorrectCityException()
	{
		this.message = "You insert incorrect city, please try again";
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}