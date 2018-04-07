package controller.manager;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;

import model.Product;
import model.User;
import model.dao.UserDao;
import validation.Supp;
import webSite.WebSite;

public class UserManager {

	private static UserManager instance;
	private static UserDao userDao = UserDao.getInstance();
	
	//Collections
	private Collection<Product> favourites = new HashSet<>();
	private Collection<Product> watchList = new HashSet<>();
	private Map<Product, LocalDate> products = new HashMap<>();

	private UserManager() {
		
	}
	
	public static synchronized UserManager getInstance() {
		if(instance == null) {
			instance = new UserManager();
		}
		return instance;
	}
	
	
	public synchronized boolean register(int userTypeId, 
							String firstName, 
							String lastName, 
							String username, 
							String password, 
							String email, 
							String phone) {
		
		User u = null;
		try {
			u = new User(userTypeId, firstName, lastName, username, password, email, phone);
			u.setRegistrationdate(LocalDate.now());
			u.setLastLogin(LocalDateTime.now());
		}catch(InputMismatchException e) {
			System.out.println("Sorry, one of the fields you entered is incorrect");
			System.out.println(e.getMessage());
			return false;
		}
		try {
			if(!WebSite.getUsers().contains(u)) {
				userDao.saveUser(u);
				WebSite.addUser(u);
				return true;
			}
			return false;
		} catch (SQLException e) {
			System.out.println("Sorry, the system couldn't save the user into the Database.\n"+e.getMessage());
			return false;
		}
	}
	
	public synchronized boolean signIn(String username, String password) {
		Scanner scan = new Scanner(System.in);
		do {
			username = scan.next();
			password = scan.next();
		}while(!Supp.isValidUsername(username) || !Supp.isValidPassword(password));
		
		User u = null;
		try {
			u = userDao.getUserByUsername(username);
			if(password.equals(u.getPassword())) {
				u.setLastLogin(LocalDateTime.now());
				scan.close();
				return true;
			}
			System.out.println("Wrong password.");
			scan.close();
			return false;
			
		} catch (SQLException e) {
			System.out.println("User with this username doesn't exists. "+e.getMessage());
			scan.close();
			return false;
		}
	}
	
	public boolean addProductToFavourites(User user, Product product) {
		if(product != null){

			try{
				userDao.addToFavourites(user, product);
				favourites.add(product);
				System.out.println("\nSuccessfully added product to your favourite list!");
			}catch(SQLException e) {
				System.out.println("Unsuccessfully added product to your favourite list! "+e.getMessage());
				return false;
			}
			return true;
		}
		return false;
	}
	
	public boolean removeProductFromFavourites(User user, Product product) {
		if(product != null){

			try{
				userDao.removeFromFavourites(user, product);
				favourites.add(product);
				System.out.println("\nSuccessfully removed product from your favourite list!");
			}catch(SQLException e) {
				System.out.println("Unsuccessfully removed product from your favourite list! "+e.getMessage());
				return false;
			}
			return true;
		}
		return false;
	}
	
	public boolean buyProduct(User user, Product product) {
		if(product != null){

			try{
				LocalDate validity = LocalDate.now();
				userDao.addToProducts(user, product, validity);
				products.put(product, validity);
				System.out.println("\nSuccessfully bought product!");
			}catch(SQLException e) {
				System.out.println("Unsuccessfully bought product! "+e.getMessage());
				return false;
			}
			return true;
		}
		return false;
	}
	
	public boolean removeProduct(User user, Product product) {
		if(product != null){

			try{
				userDao.removeFromProducts(user, product);
				products.remove(product);
				System.out.println("\nSuccessfully removed product!");
			}catch(SQLException e) {
				System.out.println("Unsuccessfully removed product! "+e.getMessage());
				return false;
			}
			return true;
		}
		return false;
	}
	
	public boolean addProductToWatchlist(User user, Product product) {
		if(product != null){

			try{
				userDao.addToWatchlist(user, product);
				watchList.add(product);
				System.out.println("\nSuccessfully added product to your watchlist!");
			}catch(SQLException e) {
				System.out.println("Unsuccessfully added product to your watchlist! "+e.getMessage());
				return false;
			}
			return true;
		}
		return false;
	}
	
	public boolean removeProductFromWatchlist(User user, Product product) {
		if(product != null){

			try{
				userDao.removeFromWatchlist(user, product);
				watchList.remove(product);
				System.out.println("\nSuccessfully removed product from your watchlist!");
			}catch(SQLException e) {
				System.out.println("Unsuccessfully removed product from your watchlist! "+e.getMessage());
				return false;
			}
			return true;
		}
		return false;
	}
	
}
