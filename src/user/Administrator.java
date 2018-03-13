package user;

import products.Product;

public class Administrator extends User implements IAdministrator {

	public Administrator(String names, String username, String password, String email) throws Exception {
		super(names, username, password, email);
	}

	@Override
	public void addToCatalog(Product product) {
		if(product != null){
			filmoteka.addToCatalog(product);
		}
	}

	@Override
	public void removeFromCatalog(Product product) {
		if(product != null){
			filmoteka.removeFromCatalog(product);
		}
	}

	@Override
	public void editProduct(Product product) {
		// TODO allow Admin to edit products in WebSite
		
	}

}
