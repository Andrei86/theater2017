package com.shalkevich.andrei.training2017.datamodel;

public class Customer {
	
	private Integer id;
	private String login;
	private String password;
	private String firstName;
	private String lastName;
	private String eMail;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public String geteMail() {
		return eMail;
	}
	public void seteMail(String eMail) {
		this.eMail = eMail;
	}
	@Override
	public String toString() {
		return  "Customer [id= " + id + "]" + " login " + login + " password " + password +
				" first name " + firstName + " last name " + lastName + "Email " + eMail ;
	}
	
	
	
	
	

}
