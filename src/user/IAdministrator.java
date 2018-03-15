package user;

import products.Product;

public interface IAdministrator {
	
	void createNewProduct();

	void editProductInfo();
	
	void addProductToCatalog(Product product);
	
	void removeProductFromCatalog(Product product);
	
	void printProductsManagementMenu();
	
	void selectFromProductsManagementMenu();
}
