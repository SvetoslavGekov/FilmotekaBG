package model.dao;

import java.sql.SQLException;
import java.util.Collection;

import exceptions.InvalidGenreDataException;
import model.Genre;

public interface IGenreDao {
	
	void saveGenre(Genre g) throws SQLException, InvalidGenreDataException;
	
	void updateGenre(Genre g) throws SQLException;
	
	Collection<Genre> getAllGenres() throws SQLException, InvalidGenreDataException;
}
