package user;

import java.time.LocalDate;
import java.time.LocalDateTime;

import demo.WebSite;
import validation.Supp;

public abstract class User {
	
	protected static WebSite filmoteka;
	protected String names;
	protected String username;
	protected String password;
	protected String email;
	protected String phone;
	protected LocalDate registrationDate;
	protected LocalDateTime lastLogin;

	public User(String names, String username, String password, String email) throws Exception {
		
		this.setNames(names);
		this.setUsername(username);
		this.setPassword(password);
		this.setEmail(email);
		this.registrationDate = LocalDate.now();
	}
	protected void setNames(String names) throws Exception {
		if(Supp.validStr(names)){
			this.names = names;
			return;
		}
		throw new Exception("You have entered an invalid name! Please enter a new one.");
	}

	protected void setUsername(String username) throws Exception {
		if(Supp.validStr(username)){
			this.username = username;
			return;
		}
		throw new Exception("You have entered an invalid username! Please enter a new one.");
	}

	protected void setPassword(String password) throws Exception {
		if(Supp.validStr(password)){
			this.password = password;
			//TODO validate password
			return;
		}
		throw new Exception("You have entered an invalid password! Please enter a new one.");
	}

	protected void setEmail(String email) throws Exception {
		if(Supp.validStr(email)){
			this.email = email;
			//TODO validate email
			return;
		}
		throw new Exception("You have entered an invalid email! Please enter a new one.");
	}

	protected void setPhone(String phone) {
		this.phone = phone;
	}
	
}
