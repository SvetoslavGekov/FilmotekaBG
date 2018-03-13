package user;

public interface IUser {
	void exitApplication();
	
	void printMainMenu();
	
	void selectFromMainMenu();
	
	void signIn();
	
	void logout();
	
	String getUsername();
	
	String getPassword();
}
