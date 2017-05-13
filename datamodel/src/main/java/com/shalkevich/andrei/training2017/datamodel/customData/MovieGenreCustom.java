package com.shalkevich.andrei.training2017.datamodel.customData;

public class MovieGenreCustom {
	
	private Integer id;
	private Integer movieId;
	private Integer genreId;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}

	
	public Integer getMovieId() {
		return movieId;
	}
	public void setMovieId(Integer movieId) {
		this.movieId = movieId;
	}
	public Integer getGenreId() {
		return genreId;
	}
	public void setGenreId(Integer genreId) {
		this.genreId = genreId;
	}
	@Override
	public boolean equals(Object obj) {
	
		if(this == obj) return true;
		if(!(obj instanceof MovieGenreCustom)) return false;
		
		MovieGenreCustom mg = (MovieGenreCustom) obj;
		
		return id.equals(mg.id) && ((movieId == null && mg.movieId == null) || movieId.equals(mg.movieId)) 
				&& ((genreId == null && mg.genreId == null) || genreId.equals(mg.genreId));

	}
	@Override
	public int hashCode() {
		
		Integer code = 17;
        code = 31 * code + id;
        code = 31 * code + ((movieId != null)? movieId.hashCode(): 0);
        code = 31 * code + ((genreId != null)? genreId.hashCode(): 0);
 
		return code;
	}
	@Override
	public String toString() {
		
		return  "Genre id" + genreId + " movie id " + movieId;
	}
}
