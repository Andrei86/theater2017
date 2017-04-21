package com.shalkevich.andrei.training2017.datamodel;

import com.shalkevich.andrei.training2017.datamodel.customData.Role;

public class Customer {
	
	private Integer id;
	private String login;
	private String password;
	private String firstName;
	private String lastName;
	private String eMail;
	private Role role;
	
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
	
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	
	
	
	@Override
	public boolean equals(Object obj) {
		
		if(this == obj) return true;
		if(!(obj instanceof Customer)) return false;
		
		Customer c = (Customer) obj;
		
		return id.equals(c.id) && login.equals(c.login) && password.equals(c.password) 
				&& firstName.equals(c.firstName) && lastName.equals(c.lastName) 
				&& eMail.equals(c.eMail);
	}
	
	@Override
	public int hashCode() {
		
		Integer code = 17;
        code = 31 * code + id;
        code = 31 * code + login.hashCode();
        code = 31 * code + password.hashCode();
        code = 31 * code + firstName.hashCode();
        code = 31 * code + lastName.hashCode();
        code = 31 * code + eMail.hashCode();
        //code = 31 * code + role.hashCode();
        
        return code;
	}
	
	
	@Override
	public String toString() {
		return  "Customer [id= " + id + "]" + " login " + login + " password " + password +
				" first name " + firstName + " last name " + lastName + "Email " + eMail ;
	}
	
	
	
	
	

}
