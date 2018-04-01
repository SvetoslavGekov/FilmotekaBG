package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;

import dbManager.DBManager;
import exceptions.InvalidProductDataException;
import model.Movie;

public class MovieDao implements IMovieDao {
	//Fields
	private static MovieDao instance;
	private static Connection con;
	
	//Constructors
	private MovieDao() {
		//Create the connection object from the DBManager
		MovieDao.con = DBManager.getInstance().getCon();
	}
	
	//Methods
	public synchronized static MovieDao getInstance() {
		if(instance == null) {
			instance = new MovieDao();
		}
		return instance;
	}

	@Override
	//Save a newly created movie with the basic mandatory fields
	public void saveMovie(Movie m) throws SQLException, InvalidProductDataException {
		//TODO --> Transactions
		synchronized (con) {
			con.setAutoCommit(false);
			try {
				//Insert the movie in the products table
				ProductDao.getInstance().saveProduct(m);
				
				//Insert the movie in the movies table
				try(PreparedStatement ps = con.prepareStatement("INSERT INTO movies (product_id) VALUES (?);")){
					ps.setInt(1, m.getId());
					ps.executeUpdate();
				}
				con.commit();
			}
			catch(SQLException e) {
				//Rollback 
				con.rollback();
				throw e;
			}
			finally {
				con.setAutoCommit(true);
			}
		}
	}

	@Override
	public void updateMovie(Movie m) throws SQLException {
		//TODO -> transactions
		synchronized (con) {
			con.setAutoCommit(false);
			try {
				//Update the product basic information
				ProductDao.getInstance().updateProduct(m);
				
				//Update the movie specific information
				
				con.commit();
			}
			catch (SQLException e) {
				//Rollback
				con.rollback();
				throw e;
			}
			finally {
				// TODO: handle finally clause
			}
		}
		
	}

	@Override
	public Collection<Movie> getAllMovies() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
