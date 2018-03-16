package user;

import java.time.LocalDate;

import products.Movie;
import products.Product;
import products.TVSeries;
import validation.Supp;

public class Administrator extends User implements IAdministrator {
	
	//Constructors
	public Administrator(String names, String username, String password, String email) throws Exception {
		super(names, username, password, email);
	}

	//Methods	
	
	@Override
	public void printProductsManagementMenu() {
		System.out.printf("\n============  PRODUCTS MANAGEMENT MENU  ============%n%n", this.getFilmoteka().getName());
		System.out.println(this);
		System.out.println("	1) Add new product");
		System.out.println("	2) Edit product info");
		System.out.println("	3) Delete product");
		System.out.println("	4) Return to main menu");
		selectFromProductsManagementMenu();
	}
	
	@Override
	public void selectFromProductsManagementMenu() {
		System.out.println("\nPlease enter one of the products management menu options to continue:");
		//Get input for the chosen option
		int option = Supp.getPositiveNumber();
		
		switch(option) {
			case 1: this.createNewProduct();break;
			case 2: this.editProductInfo();break;
			case 3: break;//TODO delete product
			case 4: break;
		default:
			System.out.println("You've chosen an invalid option for this menu. Please try again.");
			selectFromProductsManagementMenu();
			break;
		}
	}
	
	@Override
	public void editProductInfo() {
		System.out.println("\n=========== PRODUCT EDITING FORM ===========");
		System.out.println("To continue editing a product please select one from the catalog.");
		
		//Admin attempts to select a product from the catalog
		Product pr = this.searchForProduct();
		
		//If there is no such product --> return to main menu
		if(pr == null) {
			System.out.println("No product matches your search criteria. Redirecting to main menu.");
		}
		else {
			pr.printProductEditingMenu();
		}
	}
	
	@Override
	public void createNewProduct() {
		System.out.println("\n========== NEW PRODUCT CREATION FORM =========");
		
		//Attempt to collect information about the product
		try {
			Product product = null;
			
			int pType = Product.inputProductType();
			String name = Product.inputProductName();
			LocalDate releaseDate = Product.inputProductReleaseDate();
			String pgRating = Product.inputPGRating();
			int duration = Product.inputDuration();
			double rentCost = Product.inputRentCost();
			double buyCost = Product.inputBuyCost();
			
			//Attempt to create product
			switch (pType) {
			case 1:	product = new Movie(name,releaseDate, pgRating, duration, rentCost, buyCost); break;
			case 2: product = new TVSeries(name, releaseDate, pgRating, duration, rentCost, buyCost); break;
			default:
				throw new Exception("Sorry, something went wrong while creating the product.");
			}
			
			//Add product to catalog and return user to management menu
			this.addProductToCatalog(product);
			
			this.printProductsManagementMenu();
		}
		catch (Exception e) {
			//User recieves an error message for his input and is prompted to either try again or go back to a menu; 
			System.out.println(e.getMessage());
			System.out.print("\nWould you like to attempt to create a new product again?\n 1) Add new product;\n 2) Exit to product management menu;."
					+ "\nPlease enter your choice: ");
			int option = Supp.getPositiveNumber();
			switch (option) {
			case 1: this.createNewProduct(); break;
			case 2: this.printProductsManagementMenu(); break;
			default:
				System.out.println("You've entered an invalid option for this menu. Redirecting to the main menu");
				break;
			}
		}
	}
	
	@Override
	public void printMainMenu() {
		System.out.printf("\n============  %s  MAIN  MENU  ============%n%n", this.getFilmoteka().getName());
		System.out.println(this);
		System.out.println("	1) Manage Products");
		System.out.println("	2) Manage Users");
		System.out.println("	3) Logout");
		System.out.println("	99) Exit Application");
//		selectFromMainMenu();
	}
	
	@Override
	public void selectFromMainMenu() {
		System.out.print("\nPlease enter one of the main menu options to continue: ");
		
		//Get input for the chosen option
		int option = Supp.getPositiveNumber();
		switch (option) {
		case 1: this.printProductsManagementMenu();break;
		case 2: ; break; //Register
		case 3: 
			this.logout();
//			this.printMainMenu();
			break;
		case 99: this.exitApplication(); break;
		default: 
			System.out.println("You've chosen an invalid option for this menu. Please try again.");
			selectFromMainMenu();
			break;
		}
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

}
