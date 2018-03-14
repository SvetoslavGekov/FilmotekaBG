package user;

import products.Product;

public interface IAdministrator {
	
	void createNewProduct();

	void addProductToCatalog(Product product);
	
	void removeProductFromCatalog(Product product);
	
	void editProduct(Product product);
	
	void printProductsManagementMenu();
	
	void selectFromProductsManagementMenu();
}
