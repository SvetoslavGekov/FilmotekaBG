package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;
import java.util.Set;

import dbManager.DBManager;
import exceptions.InvalidProductDataException;
import model.Genre;
import model.Product;

public class ProductDao implements IProductDao {
	// Fields
	private static ProductDao instance;
	private static Connection con;

	// Constructors
	private ProductDao() {
		// Create the connection object from the DBManager
		ProductDao.con = DBManager.getInstance().getCon();
	}

	// Methods
	public synchronized static ProductDao getInstance() {
		if (instance == null) {
			instance = new ProductDao();
		}
		return instance;
	}

	@Override
	public void saveProduct(Product p) throws SQLException, InvalidProductDataException {
		try (PreparedStatement ps = con
				.prepareStatement("INSERT INTO products (name, release_year, pg_rating, duration, rent_cost, buy_cost)"
						+ " VALUES (?,YEAR(?),?,?,?,?)", PreparedStatement.RETURN_GENERATED_KEYS);) {
			ps.setString(1, p.getName());
			ps.setDate(2, java.sql.Date.valueOf(p.getReleaseDate()));
			ps.setString(3, p.getPgRating());
			ps.setInt(4, p.getDuration());
			ps.setDouble(5, p.getRentCost());
			ps.setDouble(6, p.getBuyCost());

			// If the statement is successful --> update product ID
			if (ps.executeUpdate() > 0) {
				try (ResultSet rs = ps.getGeneratedKeys()) {
					rs.next();
					p.setId(rs.getInt("GENERATED_KEY"));
				}
			}
		}
	}

	@Override
	public void updateProduct(Product p) throws SQLException {
		// Update the basic information
		try (PreparedStatement ps = con
				.prepareStatement("UPDATE products SET name = ?, release_year = ?, pg_rating = ?,"
						+ " duration = ?, rent_cost = ?, buy_cost = ?, description = ?, poster = ?, trailer = ?, writers = ?,"
						+ "actors = ?, viewer_rating = ?, total_votes = ? WHERE product_id = ?")) {
			ps.setString(1, p.getName());
			ps.setInt(2, p.getReleaseDate().getYear());
			ps.setString(3, p.getPgRating());
			ps.setInt(4, p.getDuration());
			ps.setDouble(5, p.getRentCost());
			ps.setDouble(6, p.getBuyCost());
			ps.setString(7, p.getDescription());
			ps.setString(8, p.getPoster());
			ps.setString(9, p.getTrailer());
			ps.setString(10, p.getWriters());
			ps.setString(11, p.getActors());
			ps.setDouble(12, p.getViewerRating());
			ps.setInt(13, p.getTotalVotes());
			ps.setInt(14, p.getId());
			ps.executeUpdate();
		}

		// Delete all the product's genres
		try (PreparedStatement ps = con.prepareStatement("DELETE FROM product_has_genres WHERE product_id = ?;")) {
			ps.setInt(1, p.getId());
			ps.executeUpdate();
		}

		// Update all the genres if any
		Set<Genre> pGenres = p.getGenres();
		if (!pGenres.isEmpty()) {
			Statement st = con.createStatement();
			for (Genre genre : pGenres) {
				st.addBatch(String.format("INSERT INTO product_has_genres VALUES(%d,%d);", p.getId(), genre.getId()));
			}
			st.executeBatch();
		}
	}

	@Override
	public Collection<Product> getAllProducts() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}
}
