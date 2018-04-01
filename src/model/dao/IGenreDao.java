package model.dao;

import java.sql.SQLException;
import java.util.Collection;

import model.Genre;

public interface IGenreDao {
	
	void saveGenre(Genre g) throws SQLException;
	
	void editGenre(Genre g);
	
	Collection<Genre> getAllGenres() throws SQLException;
}
