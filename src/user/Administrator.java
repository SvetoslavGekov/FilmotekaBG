package user;

import products.Product;

public class Administrator extends User implements IAdministrator {
	
	//Constructors
	public Administrator(String names, String username, String password, String email) throws Exception {
		super(names, username, password, email);
	}

	//Methods
	@Override
	public void signIn() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void printMainMenu() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void selectFromMainMenu() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void addProductToCatalog(Product product) {
		if(product != null){
			this.getFilmoteka().addProductToCatalog(product);
		}
	}

	@Override
	public void removeProductFromCatalog(Product product) {
		if(product != null){
			this.getFilmoteka().removeProductFromCatalog(product);
		}
	}

	@Override
	public void editProduct(Product product) {
		// TODO allow Admin to edit products in WebSite
	}

}
