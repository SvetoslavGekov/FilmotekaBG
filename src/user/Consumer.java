package user;

import java.time.LocalDate;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.TreeSet;

import orders.Order;
import products.Product;
import shoppingcart.ShoppingCart;
import validation.Supp;

public class Consumer extends User implements IConsumer {
	//Fields
	private Collection<Product> favourites = new HashSet<>();
	private Collection<Product> watchList = new HashSet<>();
	//private Map<Product, Review> reviews;
	private Map<Product, LocalDate> products = new HashMap<>();
	private Collection <Order> ordersHistory = new TreeSet<>();;
	private ShoppingCart shoppingCart;
	private double money;
	
	
	//Constructor
	public Consumer(String names, String username, String password, String email) throws Exception {
		super(names, username, password, email);
	}
	
	//Methods
	
	@Override
	public void printMainMenu() {
		System.out.printf("\n============  %s  MAIN  MENU  ============%n%n", this.getFilmoteka().getName());
		System.out.println(this);
		System.out.println("1) Sign In With Existing Account");
		System.out.println("2) Register New Account");
		System.out.println("3) Logout");
		System.out.println("99) Exit Application");
		selectFromMainMenu();
	}
	
	@Override
	public void selectFromMainMenu() {
		System.out.print("\nPlease enter one of the main menu options to continue: ");
		
		//Get input for the chosen option
		int option = Supp.getPositiveNumber();
		switch (option) {
		case 1: this.signIn();break;
		case 2: break; //Register
		case 3: 
			this.logout();
			this.printMainMenu();
			break;
		case 99: this.exitApplication(); break;
		default: 
			System.out.println("You've chosen an invalid option for this menu. Please try again.");
			selectFromMainMenu();
			break;
		}
	}
	
	public void addToFavourites(Product product){
		if(product != null){
			favourites.add(product);
		}
	}
	
	public void removeFromFavourites(Product product){
		if(product != null){
			favourites.remove(product);
		}
	}
	
	public void addToWatchList(Product product){
		if(product != null){
			watchList.add(product);
		}
	}
	
	public void removeFromWatchList(Product product){
		if(product != null){
			watchList.remove(product);
		}
	}
	
	public void addToShoppingCart(Product product, boolean buy){
		if(product != null){
			shoppingCart.addProduct(product, buy);
		}
	}
	
	public void removeFromShoppingCart(Product product){
		if(product != null){
			shoppingCart.removeProduct(product);
		}
	}

}
