package model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.InputMismatchException;

import validation.Supp;
import webSite.WebSite;

public class User {
	//Fields
	private static WebSite filmoteka = WebSite.getInstance();
	private int userId;
	private int userTypeId;
	private String firstName;
	private String lastName;
	private String username;
	private String password;
	private String email;
	private String phone = "";
	private LocalDate registrationDate;
	private LocalDateTime lastLogin;
	private String profilePicture;
	private double money;
	
	
	//Constructors
	public User(int userTypeId,
				String firstName,
				String lastName, 
				String username,
				String password,
				String email,
				String phone,
				LocalDate registrationDate,
				LocalDateTime lastLogin,
				double money) throws InputMismatchException{
		
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
	
	public User(int userId,
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
		
		this(userTypeId, firstName, lastName, username, password, email, phone, registrationDate, lastLogin, money);
		this.setUserId(userId);
	}

	//Getters and Setters
	public int getUserId() {
		return userId;
	}

	protected void setUserId(int userId) throws InputMismatchException {
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

	protected void setFirstName(String firstName) {
		if(Supp.isValidStr(firstName)){
			this.firstName = firstName;
			return;
		}
	}

	public String getLastName() {
		return lastName;
	}

	protected void setLastName(String lastName) {
		if(Supp.isValidStr(lastName)){
			this.lastName = lastName;
			return;
		}
	}

	public String getUsername() {
		return username;
	}

	protected void setUsername(String username) {
		if(Supp.isValidStr(username)){
			this.username = username;
			return;
		}
	}

	public String getPassword() {
		return password;
	}

	protected boolean setPassword(String password) {
		if(Supp.isValidStr(password) && Supp.isValidPassword(password)){
			this.password = password;
			return true;
		}
		return false;
	}

	public String getEmail() {
		return email;
	}

	protected boolean setEmail(String email) {
		if(Supp.isValidStr(email) && Supp.isValidEmail(email)){
			this.email = email;
			return true;
		}
		return false;
	}

	public String getPhone() {
		return phone;
	}

	protected void setPhone(String phone) {
		if(Supp.isValidPhoneNumber(phone)) {
			this.phone = phone;
		}	
	}

	public double getMoney() {
		return money;
	}

	protected void setMoney(double money) throws InputMismatchException{
		if(money >= 0) {
		   this.money = money;
		}
	}
	
	public LocalDate getRegisrtationDate() {
		return this.registrationDate;
	}
	
	protected void setRegistrationdate(LocalDate registrationDate) {
		this.registrationDate = registrationDate;
	}

	public LocalDateTime getLastLogin() {
		return lastLogin;
	}

	protected void setLastLogin(LocalDateTime lastLogin) {
		this.lastLogin = lastLogin;
	}
	
	public String getProfilePicture() {
		return this.profilePicture;
	}

	protected void setProfilePicture(String profilePicture) {
		//TODO check if string is valid path
		this.profilePicture = profilePicture;
	}

	@Override
	public String toString() {
		return String.format("User: %s	FirstName: %s LastName: %s	Email: %s	Phone number: %s", this.username,
																								   this.firstName,
																								   this.lastName, 
																								   this.email, 
																								   this.phone);
	}
}
