package com.shalkevich.andrei.training2017.datamodel;

public class MovieTheater {
	
	private Integer id;
	private String name;
	private String city;
	private String address;
	private Boolean isActive;
	
	
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Boolean getIsActive() {
		return isActive;
	}
	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	
	
	
	@Override
	public boolean equals(Object obj) {
		
		if(this == obj) return true;
		if(!(obj instanceof MovieTheater)) return false;
		
		MovieTheater mt = (MovieTheater) obj;
		
		return id.equals(mt.id) && name.equals(mt.name) && city.equals(mt.city) 
								&& address.equals(mt.address) && isActive.equals(mt.isActive);
	}
	
	
	@Override
	public int hashCode() {
		
		Integer code = 17;
        code = 31 * code + id;
        code = 31 * code + name.hashCode();
        code = 31 * code + city.hashCode();
        code = 31 * code + address.hashCode();
        code = 31 * code + isActive.hashCode();
 
		return code;
	}
	
	@Override
	public String toString() {
		
		return "MovieTheater [id=" + id + "]" + " name " + name + " city " + city + 
				" address " + address + " active status " + isActive ;
	}
	
	
	
	

}
