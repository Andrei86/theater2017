package com.shalkevich.andrei.training2017.datamodel;


public class Genre {
	
	private Integer id;
	private String name;

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
	
	@Override
	public boolean equals(Object obj) {
	
		if(this == obj) return true;
		if(!(obj instanceof Genre)) return false;
		
		Genre genre = (Genre) obj;
		
		return id.equals(genre.id) && name.equals(genre.name);

	}
	@Override
	public int hashCode() {
		
		Integer code = 17;
        code = 31 * code + id;
        code = 31 * code + name.hashCode();
 
		return code;
	}
	@Override
	public String toString() {
		
		return  "Genre [id=" + id + "]" + " name " + name;
	}
	
	
}
