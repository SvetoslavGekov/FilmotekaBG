package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import dbManager.DBManager;
import model.Genre;
import webSite.WebSite;

public final class GenreDao implements IGenreDao {
	//Fields
	private static GenreDao instance;
	private static Connection con;

	//Constructor
	private GenreDao() {
		//Create the connection object from the DBManager
		GenreDao.con = DBManager.getInstance().getCon();
	}
	
	//Methods
	public synchronized static GenreDao getInstance() {
		if(instance == null) {
			instance = new GenreDao();
		}
		return instance;
	}

	@Override
	public void saveGenre(Genre g) throws SQLException {
		try(PreparedStatement ps = con.prepareStatement("INSERT INTO genres (value) VALUES(?);", PreparedStatement.RETURN_GENERATED_KEYS)){
			ps.setString(1, g.getValue());
			//If the insertion is successful
			if(ps.executeUpdate() > 0) {
				//Update the genre's Id and add it to the GENRES collection
				try(ResultSet rs = ps.getGeneratedKeys()){
					rs.next();
					g.setId(rs.getInt("GENERATED_KEY"));
					WebSite.addGenre(g);
				}
			}
		}
	}

	@Override
	public void editGenre(Genre g) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Collection<Genre> getAllGenres() throws SQLException {
		Collection<Genre> allGenres = new ArrayList();
		try(PreparedStatement ps = con.prepareStatement("SELECT genre_id, value FROM genres;");){
			try(ResultSet rs = ps.executeQuery();){
				//While there are genres to be created
				while(rs.next()) {
					//Create next genre with full data
					Genre g = new Genre(rs.getInt("genre_id"), rs.getString("value"));
					allGenres.add(g);
				}
			}
		}
		if(allGenres.isEmpty()) {
			return Collections.emptyList();
		}
		return allGenres;
	}
}
