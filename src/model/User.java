package model;

import java.time.LocalDate;
import java.time.LocalDateTime;

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
	private double money;
	
	
	//Constructors
	public User(int userTypeId, String firstName,String lastName, String username, String password, String email) {
		this.setFirstName(firstName);
		this.setLastName(lastName);
		this.setUsername(username);
		this.setPassword(password);
		this.setEmail(email);
		this.registrationDate = LocalDate.now();
		this.lastLogin = LocalDateTime.now();
	}
	
	public User(int userId, int userTypeId, String firstName,String lastName, String username, String password, String email) {
		this(userTypeId, firstName, lastName, username, password, email);
		this.setUserId(userId);
	}

	//Getter and Setters
	protected int getUserId() {
		return userId;
	}

	protected void setUserId(int userId) {
		if(userId > 0) {
		   this.userId = userId;
		}

	}

	protected int getUserTypeId() {
		return userTypeId;
	}

	protected void setUserTypeId(int userTypeId) {
		if(userTypeId == 1 || userTypeId == 2) {
			this.userTypeId = userTypeId;
		}

	}

	protected String getFirstName() {
		return firstName;
	}

	protected void setFirstName(String firstName) {
		if(Supp.isValidStr(firstName)){
			this.firstName = firstName;
			return;
		}
	}

	protected String getLastName() {
		return lastName;
	}

	protected void setLastName(String lastName) {
		if(Supp.isValidStr(lastName)){
			this.lastName = lastName;
			return;
		}
	}

	protected String getUsername() {
		return username;
	}

	protected void setUsername(String username) {
		if(Supp.isValidStr(username)){
			this.username = username;
			return;
		}
	}

	protected String getPassword() {
		return password;
	}

	protected boolean setPassword(String password) {
		if(Supp.isValidStr(password) && Supp.isValidPassword(password)){
			this.password = password;
			return true;
		}
		return false;
	}

	protected String getEmail() {
		return email;
	}

	protected boolean setEmail(String email) {
		if(Supp.isValidStr(email) && Supp.isValidEmail(email)){
			this.email = email;
			return true;
		}
		return false;
	}

	protected String getPhone() {
		return phone;
	}

	protected void setPhone(String phone) {
		if(Supp.isValidPhoneNumber(phone)) {
			this.phone = phone;
		}	
	}

	protected double getMoney() {
		return money;
	}

	protected void setMoney(double money) {
		if(money >= 0) {
		   this.money = money;
		}
	}
	
	@Override
	public String toString() {
		return String.format("User: %s	FirstName: %s LastName: %s	Email: %s	Phone number: %s", this.username, this.firstName, this.lastName, this.email, this.phone);
	}
}
