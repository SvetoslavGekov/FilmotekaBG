package user;

import products.Product;

public interface IAdministrator {

	void addToCatalog(Product product);
	void removeFromCatalog(Product product);
	void editProduct(Product product);
}
