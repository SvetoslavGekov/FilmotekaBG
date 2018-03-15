package user;

import java.time.LocalDate;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.TreeSet;

import customexceptions.DatabaseConflictException;
import customexceptions.InvalidUserInfoException;
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
	public Consumer(String names, String username, String password, String email){
		super(names, username, password, email);
	}
	
	//Methods
	@Override
	public void createAccount() {
		System.out.printf("\n============ %s REGISTRATION FORM ===============%n%n", this.getFilmoteka().getName().toUpperCase());
		
		try {
			//Collect user account information
			String username = inputUsername();
			String email = inputUserEmail();
			String password = inputUserPassword();
			String names = inputUserNames();
			
			//Create user
			IUser newUser = new Consumer(names, username, password, email);
			System.out.println(newUser);
			//WebSite registers user
			this.getFilmoteka().registerUser(newUser);
		}
		catch (InvalidUserInfoException| DatabaseConflictException e) {
			//User recieves an error message for his input and is prompted to either try again or go back to the main menu; 
			System.out.println(e.getMessage());
			System.out.print("\nWould you like to attempt to register again?\n 1) Continue registration;\n 2) Exit to main menu."
					+ "\nPlease enter your choice: ");
			int option = Supp.getPositiveNumber();
			switch (option) {
			case 1: this.createAccount(); break;
			case 2: this.printMainMenu(); break;
			default:
				System.out.println("You've entered an invalid option for this menu. Redirecting to the main menu");
				this.printMainMenu();
				break;
			}
		}
	}
	
	private String inputUsername() throws InvalidUserInfoException, DatabaseConflictException{
		//User inputs username
		System.out.print("Please enter your username: ");
		String username = Supp.inputString();
		
		//Check if username is valid
		if(!Supp.validUsername(username)) {
			throw new InvalidUserInfoException("Sorry, you've entered an invalid username.\nUsernames should consist of only lowercase characters"
					+ "(no special symbols or white space) with a minimum length of 4 symbols.");
		}
		
		//Check if username is not already taken
		//TODO --> Think about simultenious username and email validation
		if(this.getFilmoteka().checkUserName(username)) {
			throw new DatabaseConflictException("Sorry, the username that you've chosen has already been taken by another user.");
		}
		return username;
	}
	
	private String inputUserEmail() throws InvalidUserInfoException{
		//User inputs email + makes it lowercase
		System.out.print("Please enter your email: ");
		String email = Supp.inputString().toLowerCase();
		
		//Check if email is valid
		if(!Supp.validEmail(email)) {
			throw new InvalidUserInfoException("Sorry, you've entered an invalid email.\nEmails should look like this: "
					+ "myeMail@myMailSite.com");
		}
		return email;
	}
	
	private String inputUserNames() throws InvalidUserInfoException{
		//User inputs real names
		System.out.println("Please enter your names:");
		String names = Supp.inputString();
		
		//Check if names is valid string
		if(!Supp.validStr(names)) {
			throw new InvalidUserInfoException("Sorry, you've entered and invalid name.Please try again: ");
		}
		return names;
	}
	
	private String inputUserPassword() throws InvalidUserInfoException{
		//User inputs password
		System.out.print("Please enter your password: ");
		String password = Supp.inputString();
		
		//Check if password is valid
		if(!Supp.validPassword(password)) {
			throw new InvalidUserInfoException("Sorry, you've entered an invalid password. \nPasswords should be at least 6 symbols long,"
					+ "have at least 1 lowercase letter, 1 upercase letter and 1 digit.");
		}
		return password;
	}
	
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
		case 2: this.createAccount(); break; //Register
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
