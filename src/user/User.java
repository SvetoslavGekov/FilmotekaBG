package user;

import java.time.LocalDate;
import java.time.LocalDateTime;

import demo.WebSite;
import validation.Supp;

public abstract class User implements IUser {
	//Fields
	private static WebSite filmoteka = WebSite.getInstance();
	private String names;
	private String username;
	private String password;
	private String email;
	private String phone;
	private LocalDate registrationDate;
	private LocalDateTime lastLogin;

	//Constructors
	public User(String names, String username, String password, String email) throws Exception {
		this.setNames(names);
		this.setUsername(username);
		this.setPassword(password);
		this.setEmail(email);
		this.registrationDate = LocalDate.now();
	}
	
	//Methods
	@Override
	public String toString() {
		return String.format("User: %s	Names: %s	Email: %s", this.username, this.names, this.email);
	}
	
	@Override
	public void signIn() {
		//Automatic logout when the user wants to sign in
		this.logout();
		
		System.out.println("\n========== SIGN IN FORM ===========");
		String username = "";
		String password = "";
		int signInAttempts = 0;
		//Attempt to sign in
		while(signInAttempts < WebSite.MAX_SIGN_IN_ATTEMPTS) {
			//Iterate sign in attempts
			signInAttempts++;
			
			System.out.print("Please enter your username: ");
			username = Supp.inputString();
			
			//Check if name is present in the users collection
			if(this.getFilmoteka().checkUserName(username)) {
				System.out.println("Please enter your password: ");
				password = Supp.inputString();
				
				//Check if password is correct
				if(this.getFilmoteka().checkUserPassword(username, password)) {
					this.getFilmoteka().loginUser(username);
					return;
				}
			}
		}
		if(signInAttempts >= WebSite.MAX_SIGN_IN_ATTEMPTS) {
			System.out.println("\nYou've expended all your attempts at logging in. Redirecting to main menu.");
			this.printMainMenu();
		}
	}
	
	@Override
	public void exitApplication() {
		this.getFilmoteka().stopApp();
	}
	
	@Override
	public void logout() {
		this.filmoteka.logoutUser();
	}
	
	//Setters
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
		//You can delete your phone number from the site info - optional field
		this.phone = phone;
	}
	
	//Getters
	public String getUsername() {
		return this.username;
	}
	
	public String getEmail() {
		return this.email;
	}
	
	protected WebSite getFilmoteka() {
		return this.filmoteka;
	}
	
	public String getPassword() {
		return this.password;
	}
}
