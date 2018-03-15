package user;

import java.time.LocalDate;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.TreeSet;

import customexceptions.DatabaseConflictException;
import customexceptions.InvalidUserInfoException;
import demo.WebSite;
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
	private ShoppingCart shoppingCart = new ShoppingCart();
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
			System.out.print("\nWould you like to attempt to register again?\n	1) Continue registration;\n	2) Exit to main menu."
					+ "\n	Please enter your choice: ");
			int option = Supp.getPositiveNumber();
			switch (option) {
			case 1: this.createAccount(); break;
			case 2: break;//this.printMainMenu(); break;
			default:
				System.out.println("You've entered an invalid option for this menu. Redirecting to the main menu");
//				this.printMainMenu();
				break;
			}
		}
	}
	
	private String inputUsername() throws InvalidUserInfoException, DatabaseConflictException{
		//User inputs username
		System.out.print("Please enter your username: ");
		String username = Supp.inputValidString();
		
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
		String email = Supp.inputValidString().toLowerCase();
		
		//Check if email is valid
		if(!Supp.validEmail(email)) {
			throw new InvalidUserInfoException("Sorry, you've entered an invalid email.\nEmails should look like this: "
					+ "myeMail@myMailSite.com");
		}
		return email;
	}
	
	private String inputUserNames() throws InvalidUserInfoException{
		//User inputs real names
		System.out.print("Please enter your names:");
		String names = Supp.inputValidString();
		
		//Check if names is valid string
		if(!Supp.validStr(names)) {
			throw new InvalidUserInfoException("Sorry, you've entered and invalid name.Please try again: ");
		}
		return names;
	}
	
	private String inputUserPassword() throws InvalidUserInfoException{
		//User inputs password
		System.out.print("Please enter your password: ");
		String password = Supp.inputValidString();
		
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
		System.out.println("4) My Account");
		System.out.println("5) Browse Catalog");
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
		case 2: this.createAccount(); break;
		case 3: 
			this.logout();
//			this.printMainMenu();
			break;
		case 4: this.myAccountMenu();break;
		case 5: this.browseCatalog();break;
		case 99: this.exitApplication(); break;
		default: 
			System.out.println("You've chosen an invalid option for this menu.");
//			selectFromMainMenu();
			break;
		}
	}
	
	private void browseCatalog() {
		System.out.println("\n\n============================ CATALOG ============================\n");
		for(Product product: WebSite.getCatalog()){
			System.out.println(product);
		}
		Product product = this.searchForProduct();
		//TODO Meke Consumer Favourite/Rate/Add to Cart/Buy if not null
	}

	private void myAccountMenu() {
		
		System.out.println("\n===================  MY ACCOUNT  ===================\n");
		System.out.println(this);
		System.out.println("1) Edit Profile");
		System.out.println("2) View Watchlist");
		System.out.println("3) View Favourites");
		System.out.println("4) View Products");
		System.out.println("5) Go To Cart");
		System.out.println("6) Back To Main Menu");
		System.out.println("99) Exit Application");
		selectFromMyAccountMenu();
		
	}

	private void selectFromMyAccountMenu() {
		
		System.out.print("\nPlease enter one of the my account menu options to continue: ");
		
		//Get input for the chosen option
		int option = Supp.getPositiveNumber();
		switch (option) {
		case 1: this.editProfileMenu();;break;
		case 2: this.showWatchList(); break; 
		case 3: this.showFavourites();break;
		case 4: this.showProducts();break;
		case 5: this.goToCart();break;
		case 6: this.printMainMenu();break;
		case 99: this.exitApplication(); break;
		default: 
			System.out.println("You've chosen an invalid option for this menu. Please try again.");
			selectFromMyAccountMenu();
			break;
		}
		myAccountMenu();
		
	}

	private void goToCart() {
		System.out.println("\n\n================  MY SHOPPING CART  ================\n");
		System.out.println("1) Show Products In Cart");
		System.out.println("2) Buy All Products");
		System.out.println("3) Remove Product From Cart");
		System.out.println("4) Empty Cart");
		System.out.println("5) Back To My Account");
		System.out.println("99) Exit Application");
		selectFromGoToCartMenu();
	}
	
	private void selectFromGoToCartMenu() {
		
		System.out.print("\nPlease enter one of the Go To Cart Menu options to continue: ");
		
		//Get input for the chosen option
		int option = Supp.getPositiveNumber();
		switch (option) {
		case 1: this.shoppingCart.showProducts();break;
		
		case 2: double moneyForProductsInCart = this.shoppingCart.getAllProductsPrice();
				if(this.shoppingCart.buyProductsInCart(this.money)){
					this.money -= moneyForProductsInCart;
				}
				break; 
				
		case 3: this.removeFromShoppingCart(); break; 	
		case 4: this.shoppingCart.clearShoppingCart();break;
		case 5: this.myAccountMenu();break;
		case 99: this.exitApplication(); break;
		default: 
			System.out.println("You've chosen an invalid option for this menu. Please try again.");
			selectFromGoToCartMenu();
			break;
		}
		goToCart();
	}

	private void showProducts() {
		System.out.println("\n\nMY PRODUCTS:");
		
		if(this.isCollectionEmpty(products.entrySet()))return;
		
		for (Product product : this.products.keySet()) {
			System.out.println(product);
		}
	}

	private void showFavourites() {
		System.out.println("\n\nMY FAVOURITES:");
		
		if(this.isCollectionEmpty(this.favourites))return;
		
		for (Product product : this.favourites) {
			System.out.println(product);
		}
	}

	private void showWatchList() {
		System.out.println("\n\nMY WATCHLIST:");
		
		if(this.isCollectionEmpty(this.watchList))return;
		
		for (Product product : this.watchList) {
			System.out.println(product);
		}
	}
	
	private boolean isCollectionEmpty(Collection c){
		if(c.isEmpty()){
			System.out.println("\nEmpty collection");
			return true;
		}
		return false;
	}

	private void editProfileMenu() {
		
		System.out.println("\n=================  EDIT PROFILE MENU  =================");
		System.out.println(this);
		System.out.println("1) Edit Name");
		System.out.println("2) Edit Password");
		System.out.println("3) Edit Email");
		System.out.println("4) Back To My Account");
		System.out.println("99) Exit Application");
		try {
			this.selectFromEditProfileMenu();
		} catch (InvalidUserInfoException e) {
			System.out.println(e.getMessage());
		}
	}
	
	private void selectFromEditProfileMenu() throws InvalidUserInfoException {
		System.out.print("\nPlease enter one of the edit profile menu options to continue: ");
		
		//Get input for the chosen option
		int option = Supp.getPositiveNumber();
		switch (option) {
		case 1: if(!this.setNames(this.inputUserNames()))
				System.out.println("\nUnsuccessfully changed names!");break;
		case 2: this.changePassword(); break; 
		case 3: this.editEmail();break;
		case 4: this.myAccountMenu();break;
		case 99: this.exitApplication(); break;
		default: 
			System.out.println("You've chosen an invalid option for this menu. Please try again.");
			selectFromEditProfileMenu();
			break;
		}
		editProfileMenu();
	}
	
	private boolean editEmail(){
		System.out.println("\nEnter your new email: ");
		if(!this.setEmail(Supp.inputString())){
			System.out.println("\nUnsuccessfully changed email!");
			return false;
		}
		System.out.println("\nSuccessfully changed email!");
		return true;
	}
	
	private void changePassword(){
		System.out.println("\nPlease, enter your old password: ");
		String password = Supp.inputString();
		
		if(this.getFilmoteka().checkUserPassword(this.getUsername(), password)){
			System.out.println("\nEnter your new password: ");
			String newPassword = Supp.inputString();
			
			if(this.setPassword(newPassword)){
				System.out.println("\nSuccessfully changed password!");
				return;
			}
			System.out.println("\nUnsuccessfully changed password!");
		}
		this.editProfileMenu();
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
	
	public void removeFromShoppingCart(){
		System.out.println("\nEnter id of the product you want to remove: ");
		this.shoppingCart.removeProduct(Supp.getPositiveNumber());
	}

}
