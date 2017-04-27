package com.shalkevich.andrei.training2017.dao.impl.db.exception;

public abstract class DaoException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String msg;
	
	public DaoException(String msg)
	{
		this.msg = msg;
	}

}
