package model;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.TreeSet;

import validation.Supp;
import webSite.WebSite;

public class User {
	//Fields
	private static WebSite filmoteka = WebSite.getInstance();
	private long userId;
	private int userTypeId;
	private String firstName;
	private String lastName;
	private String username;
	private String password;
	private String email;
	private String phone = "";
	private LocalDate registrationDate;
	private LocalDateTime lastLogin;//TODO LoacalDateTime
	private String profilePicture;
	private double money;
	
	//private Collection <Order> ordersHistory = new TreeSet<>();;
	//private ShoppingCart shoppingCart = new ShoppingCart();
	
	//Collections
	private Collection<Product> favourites = new HashSet<>();
	private Collection<Product> watchList = new HashSet<>();
	private Map<Product, LocalDate> products = new HashMap<>();
	
	//Constructors
	public User(int userTypeId,
				String firstName,
				String lastName, 
				String username,
				String password,
				String email,
				String phone) throws InputMismatchException{
		
		this.setUserTypeId(userTypeId);
		this.setFirstName(firstName);
		this.setLastName(lastName);
		this.setUsername(username);
		this.setPassword(password);
		this.setEmail(email);
		this.setPhone(phone);
		this.setRegistrationdate(registrationDate);
		this.setLastLogin(lastLogin);
		this.setMoney(money);
	}
	
	public User(long userId,
				int userTypeId,
				String firstName,
				String lastName, 
				String username,
				String password,
				String email,
				String phone,
				LocalDate registrationDate,
				LocalDateTime lastLogin,
				double money) {
		
		this(userTypeId, firstName, lastName, username, password, email, phone);
		this.setUserId(userId);
		this.setRegistrationdate(registrationDate);
		this.setLastLogin(lastLogin);
		this.setMoney(money);
	}

	//Getters and Setters
	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) throws InputMismatchException {
		if(userId > 0) {
		   this.userId = userId;
		}
	}

	public int getUserTypeId() {
		return userTypeId;
	}

	protected void setUserTypeId(int userTypeId) throws InputMismatchException {
		if(userTypeId == 1 || userTypeId == 2) {
			this.userTypeId = userTypeId;
		}

	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		if(Supp.isValidStr(firstName)){
			this.firstName = firstName;
			return;
		}
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		if(Supp.isValidStr(lastName)){
			this.lastName = lastName;
			return;
		}
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		if(Supp.isValidStr(username)){
			this.username = username;
			return;
		}
	}

	public String getPassword() {
		return password;
	}

	public boolean setPassword(String password) {
		if(Supp.isValidStr(password) && Supp.isValidPassword(password)){
			this.password = password;
			return true;
		}
		return false;
	}

	public String getEmail() {
		return email;
	}

	public boolean setEmail(String email) {
		if(Supp.isValidStr(email) && Supp.isValidEmail(email)){
			this.email = email;
			return true;
		}
		return false;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		if(Supp.isValidPhoneNumber(phone)) {
			this.phone = phone;
		}	
	}

	public double getMoney() {
		return money;
	}

	public void setMoney(double money) throws InputMismatchException{
		if(money >= 0) {
		   this.money = money;
		}
	}
	
	public LocalDate getRegisrtationDate() {
		return this.registrationDate;
	}
	
	public void setRegistrationdate(LocalDate registrationDate) {
		this.registrationDate = registrationDate;
	}

	public LocalDateTime getLastLogin() {
		return lastLogin;
	}

	public void setLastLogin(LocalDateTime lastLogin) {
		this.lastLogin = lastLogin;
	}
	
	public String getProfilePicture() {
		return this.profilePicture;
	}

	public void setProfilePicture(String profilePicture) throws IOException{
		if(Supp.isValidImagePath(profilePicture)) {
			this.profilePicture = profilePicture;
		}
	}

	public Collection<Product> getFavourites() {
		return Collections.unmodifiableCollection(favourites);
	}

	public Collection<Product> getWatchList() {
		return Collections.unmodifiableCollection(watchList);
	}

	public Map<Product, LocalDate> getProducts() {
		return Collections.unmodifiableMap(products);
	}
	

	public void setFavourites(Collection<Product> favourites) {
		if(favourites != null && !favourites.isEmpty()) {
			this.favourites.addAll(favourites);
		}
	}

	public void setWatchList(Collection<Product> watchList) {
		if(watchList != null && !watchList.isEmpty()) {
			this.watchList.addAll(watchList);
		}
	}

	public void setProducts(Map<Product, LocalDate> products) {
		if(products != null && !products.isEmpty()) {
			this.products.putAll(products);;
		}
	}

	@Override
	public String toString() {
		return String.format("ID: %d\tUser: %s\tFirstName: %s\tLastName: %s \t Email: %s\tPhone number: %s\tRegDate: %s\tLastLogin: %s",
																								   this.userId,
																								   this.username,
																								   this.firstName,
																								   this.lastName, 
																								   this.email, 
																								   this.phone,
																								   this.registrationDate,
																								   this.lastLogin.format(DateTimeFormatter.ofPattern("yyyy/MM/dd - HH:mm:ss")));
	}
}
