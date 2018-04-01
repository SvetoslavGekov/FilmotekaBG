package model.dao;

import java.sql.SQLException;
import java.util.Collection;

import exceptions.InvalidProductDataException;
import model.Product;


public interface IProductDao {
	void saveProduct(Product p) throws SQLException, InvalidProductDataException;
	
	void updateProduct(Product p) throws SQLException;
	
	Collection<Product> getAllProducts() throws SQLException;
}
