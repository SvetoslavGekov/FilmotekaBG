
package demo;

import java.time.LocalTime;
import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;

import products.Product;
import user.Administrator;
import user.Consumer;
import user.IUser;
import user.User;
import validation.Supp;

public final class WebSite {
	
	//Fields
	public static final int MAX_SIGN_IN_ATTEMPTS = 3;
	private static WebSite instance;
	private static IUser guest;
	private static final String name = "Filmoteka.bg";
	private static final Map<String, IUser> allUsers = new TreeMap<String,IUser>(); //Key: Username, Value: User 
	private static final Collection <Product> catalog = new TreeSet(); //TODO Add comparator for products by name and users by name
	private IUser currentUser;
	private int money;
	
	//Constructor
	private WebSite(){
		this.currentUser = WebSite.getGuest();
	}
	
	//Methods
	
	public boolean checkUserName(String username) {
		if(!this.allUsers.containsKey(username)) {
			return false;
		}
		return true;
	};
	
	public boolean checkUserPassword(String username, String password) {
		if(!this.allUsers.get(username).getPassword().equals(password)) {
			System.out.println("You've entered a wrong password.");
			return false;
		}
		return true;
	}
	
	public void loginUser(String username) {
		//Logs in the user based on his username
		setCurrentUser(this.allUsers.get(username));
		System.out.printf("User: %s has signed in at %s%n", this.currentUser.getUsername(), LocalTime.now());
		this.currentUser.printMainMenu();
	}
	
	public void logoutUser() {
		//Attempts to logout the current user if not the guest one
		if(this.currentUser != WebSite.guest) {
			System.out.printf("User: %s has logged out at %s%n", this.currentUser.getUsername(), LocalTime.now());
			setCurrentUser(guest);
			System.out.println("Switching to Guest user.");
		}
		this.currentUser.printMainMenu();
	}
	
	public void stopApp() {
		System.exit(1);
	}
	
	public void registerUser(IUser newUser) {
		//Add user to collection
		WebSite.allUsers.put(newUser.getUsername(), newUser);
		//Sign in the new user
		this.loginUser(newUser.getUsername());
	}
	
	private static IUser getGuest(){
		if(WebSite.guest == null){
			try {
				WebSite.guest = new Consumer("Guest", "Guest", "Guest123", "Guest@filmoteka.bg") ;
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		return WebSite.guest;
	}
	
	public static WebSite getInstance(){
		if(instance == null){
			instance = new WebSite();
		}
		return instance;
	}
	
	//Setters
	public void setCurrentUser(IUser currentUser) {
		if(currentUser != null) {
			this.currentUser = currentUser;
		}
	}
	
	//Getters
	public String getName() {
		return this.name;
	}
	
	private IUser getCurrentUser() {
		return this.currentUser;
	}
	
	public void addProductToCatalog(Product product) {
		if(product != null){
			catalog.add(product);
		}
	}
	
	public void removeProductFromCatalog(Product product) {
		if(product != null){
			catalog.remove(product);
		}
	}
	
	//Demonstration starts from here
	public static void main(String[] args) {
		WebSite.getInstance().getCurrentUser().printMainMenu();
	}
	
	
	//Static block for filling collections with initial data
	static {
		HashSet<User> initalUsers = new HashSet<>();
		try {
			//Creation of 3 users
			initalUsers.add(new Consumer("Svetoslav Gekov", "sgekov", "Sgekov123", "svetoslav_gekov@abv.bg"));
			initalUsers.add(new Consumer("Mario Dimitrov", "mdimitrov", "Mdimitrov123", "napishiSiMeilaTuk@abv.bg"));
			initalUsers.add(new Administrator("Admin Adminov", "admin", "Admin123", "admin@filmoteka.bg"));
			
			for (User user : initalUsers) {
				WebSite.allUsers.put(user.getUsername(), user);
			}
			
			//TODO Creation of X products
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
