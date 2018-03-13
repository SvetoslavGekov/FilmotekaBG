package user;

import java.time.LocalDate;
import java.time.LocalDateTime;

import demo.WebSite;

public abstract class User {
	
	protected static WebSite filmoteka;
	protected String names;
	protected String username;
	protected String password;
	protected String email;
	protected String phone;
	protected LocalDate registrationDate;
	protected LocalDateTime lastLogin;

	
	public User(){
		
	}
}
