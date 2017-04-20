package com.shalkevich.andrei.training2017.dao.impl.db.exception;

public class DaoException extends Exception{ // !! развить тему -)
	
	private String message;
	
	public DaoException()
	{
		this.message = "Some message";
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
