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
	public void deleteProductFromCollections(Product pr) {
		//User receives notification from website to delete product from their collections
		this.favourites.remove(pr);
		this.watchList.remove(pr);
		this.products.remove(pr);
	}
	
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
	
	private String inputUserPhone() throws InvalidUserInfoException{
		//User inputs phone
		System.out.print("Please enter your phone number: ");
		String phone = Supp.inputValidString();
		
		//Check if phone is valid
		if(!Supp.validPhoneNumber(phone)) {
			throw new InvalidUserInfoException("Sorry, you've entered an invalid phone numbers.\n"
					+ "Phone numbers should have 10 digits.");
		}
		return phone;
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
		System.out.println("	1) Sign In With Existing Account");
		System.out.println("	2) Register New Account");
		System.out.println("	3) Logout");
		System.out.println("	4) My Account");
		System.out.println("	5) Browse Catalog");
		System.out.println("	99) Exit Application");
//		selectFromMainMenu();
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
		case 4:
			while(true) {
				this.printMyAccountMenu();
				if(!this.selectFromMyAccountMenu()) {
					break;
				}	
			}
			break;
		case 5: 
				while(true){
					if(!this.browseCatalog()){
						break;
					}
				}
				break;
		case 99: this.exitApplication(); break;
		default: 
			System.out.println("You've chosen an invalid option for this menu.");
//			selectFromMainMenu();
			break;
		}
	}
	
	private boolean browseCatalog() {
		System.out.println("\n\n============================ CATALOG ============================\n");
		for(Product product: WebSite.getCatalog()){
			System.out.println(product);
		}
		Product product = this.searchForProduct();
		
		if(product != null){
			while(true){
				this.printOptionsForProduct(product);
				if(!selectFromOptionsForProduct(product)){
					break;
				}
			}
			return true;
		}
		return false;
	}
	
	private void printOptionsForProduct(Product product){
		System.out.println("\n\n===========================  CHOSEN PRODUCT  ===========================\n");
		System.out.println(product+"\n");
		System.out.println("	1) Add To Favourites");
		System.out.println("	2) Add to Cart");
		System.out.println("	3) Rent");
		System.out.println("	4) Rate");
		System.out.println("	5) Full Info");
		System.out.println("	6) Back To Catalog");
		System.out.println("	99) Exit Application");
	}
	
	private boolean selectFromOptionsForProduct(Product product){
		System.out.print("\nPlease enter one of the Options for Product menu to continue: ");
		
		//Get input for the chosen option
		int option = Supp.getPositiveNumber();
		switch (option) {
		case 1: this.addToFavourites(product);return true;
		case 2: this.addToShoppingCart(product); return true;
		case 3: this.rentProduct(product); return true;
		case 4: this.rateProduct(product); return true;
		case 5:	product.printFullInfo(); return true;
		case 6:	return false;
		case 99: this.exitApplication();
		default: 
			System.out.println("You've chosen an invalid option for this menu.");
			return true;
		}
	}

	private void rateProduct(Product product) {
		
		
		
	}

	private void rentProduct(Product product) {
		// TODO Auto-generated method stub
		
	}

	private void buyProduct(Product product) {
		// TODO Auto-generated method stub
		
	}

	private void printMyAccountMenu() {
		System.out.println("\n===================  MY ACCOUNT  ===================\n");
		System.out.println(this);
		System.out.println("	1) Edit Profile");
		System.out.println("	2) View Watchlist");
		System.out.println("	3) View Favourites");
		System.out.println("	4) View Products");
		System.out.println("	5) Go To Cart");
		System.out.println("	6) Back To Main Menu");
		System.out.println("	99) Exit Application");
//		selectFromMyAccountMenu();
	}

	private boolean selectFromMyAccountMenu() {
		
		System.out.print("\nPlease enter one of the my account menu options to continue: ");
		
		//Get input for the chosen option
		int option = Supp.getPositiveNumber();
		switch (option) {
			case 1:
				while(true) {
					this.printEditProfileMenu();
					if(!this.selectFromEditProfileMenu()) {
						break;
					}
				}
				 return true;
			case 2: this.showWatchList(); return true; 
			case 3: this.showFavourites(); return true;
			case 4: this.showProducts(); return true;
			case 5: 
				while(true) {
					this.goToCart();
					if(!this.selectFromGoToCartMenu()) {
						break;
					}
				} 
				return true;
			case 6: return false;//this.printMainMenu();break;
			case 99: this.exitApplication(); return true;
		default: 
			System.out.println("You've chosen an invalid option for this menu. Please try again.");
//			selectFromMyAccountMenu();
			return true;
		}
//		printMyAccountMenu();
		
	}

	private void goToCart() {
		System.out.println("\n\n================  MY SHOPPING CART  ================\n");
		System.out.println("	1) Show Products In Cart");
		System.out.println("	2) Buy All Products");
		System.out.println("	3) Remove Product From Cart");
		System.out.println("	4) Empty Cart");
		System.out.println("	5) Back To My Account");
		System.out.println("	99) Exit Application");
//		selectFromGoToCartMenu();
	}
	
	private void buyAllProducts(){
		 double moneyForProductsInCart = this.shoppingCart.getAllProductsPrice();
			if(this.shoppingCart.buyProductsInCart(this.money)){
				this.money -= moneyForProductsInCart;
			}
	}
	
	private boolean selectFromGoToCartMenu() {
		
		System.out.print("\nPlease enter one of the Go To Cart Menu options to continue: ");
		
		//Get input for the chosen option
		int option = Supp.getPositiveNumber();
		switch (option) {
			case 1: this.shoppingCart.showProducts(); return true;
			case 2: this.buyAllProducts(); return true; 
			case 3: this.removeFromShoppingCart(); return true; 	
			case 4: this.shoppingCart.clearShoppingCart(); return true;
			case 5: return false;
			case 99: this.exitApplication();
		default: 
			System.out.println("You've chosen an invalid option for this menu. Please try again.");
//			selectFromGoToCartMenu();
			return true;
		}
//		goToCart();
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

	private void printEditProfileMenu() {
		System.out.println("\n=================  EDIT PROFILE MENU  =================");
		System.out.println(this);
		System.out.println("	1) Edit Name");
		System.out.println("	2) Edit Password");
		System.out.println("	3) Edit Email");
		System.out.println("	4) Edit Phone");
		System.out.println("	5) Back To My Account");
		System.out.println("	99) Exit Application");
//		try {
//			this.selectFromEditProfileMenu();
//		} catch (InvalidUserInfoException e) {
//			System.out.println(e.getMessage());
//		}
	}
	
	private void editNames() {
		try {
			if(!this.setNames(this.inputUserNames()))
				System.out.println("\nUnsuccessfully changed names!");
		}
		catch (InvalidUserInfoException e) {
			System.out.println(e.getMessage());
		}
	}
	
	private boolean selectFromEditProfileMenu() {
		System.out.print("\nPlease enter one of the edit profile menu options to continue: ");
		
		//Get input for the chosen option
		int option = Supp.getPositiveNumber();
		switch (option) {
			case 1: this.editNames(); return true;
			case 2: this.changePassword(); return true; 
			case 3: this.editEmail(); return true;
			case 4: this.editPhone(); return true;
			case 5: return false;
			case 99: this.exitApplication();
		default: 
			System.out.println("You've chosen an invalid option for this menu. Please try again.");
//			selectFromEditProfileMenu();
			return true;
		}
//		printEditProfileMenu();
	}
	
	private void editPhone() {
		System.out.println("\nCurrent phone is " + this.getPhone());
		try {
			this.setPhone(inputUserPhone());
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
	} 
	
	private boolean editEmail(){
		System.out.println("\nCurrent email is " + this.getEmail());
		
		try {
			this.setEmail(inputUserEmail());
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println("\nEmail change not successfull!");
			return false;
		}
		
		System.out.println("\nSuccessfully changed email!");
		return true;
	}
	
	private void changePassword(){
		System.out.print("\nPlease, enter your old password: ");
		String password = Supp.inputString();
		
		if(this.getFilmoteka().checkUserPassword(this.getUsername(), password)){
			
			try {
				this.setPassword(inputUserPassword());
				System.out.println("\nPassword change successfull!");
			}
			catch (InvalidUserInfoException e) {
				System.out.println(e.getMessage());
				System.out.println("\nPassword change not successfull!");
			}
		}
//		this.printEditProfileMenu();
	}

	public void addToFavourites(Product product){
		if(product != null){
			System.out.println("\nSuccessfully added product to your favourite list!");
			favourites.add(product);
		}
	}
	
	public void removeFromFavourites(Product product){
		if(product != null){
			favourites.remove(product);
			System.out.println("\nSuccessfully removed product from your favourite list!");
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
	
	public void addToShoppingCart(Product product){
		if(product != null){
			
			boolean buy = false;
			System.out.println("\nSelect:\n\t1) Buy\n\t2)Rent");
			int option = 0;
			
			do{
				option = Supp.getPositiveNumber();
			}while(option > 2);
			
			buy = (option==1) ? true : false;
			
			shoppingCart.addProduct(product, buy);
		}
	}
	
	public void removeFromShoppingCart(){
		System.out.println("\nEnter id of the product you want to remove: ");
		this.shoppingCart.removeProduct(Supp.getPositiveNumber());
	}

}
