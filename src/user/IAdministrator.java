package user;

import products.Product;

public interface IAdministrator {

	void addProductToCatalog(Product product);
	void removeProductFromCatalog(Product product);
	void editProduct(Product product);
}
