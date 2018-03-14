package user;

import java.util.Comparator;

import products.Product;

public interface IUser {
	
	Product searchForProduct();
	
	void exitApplication();
	
	void printMainMenu();
	
	void selectFromMainMenu();
	
	void signIn();
	
	void logout();
	
	String getUsername();
	
	String getPassword();
	
	Comparator<IUser> compareUsername = new Comparator<IUser>() {
		public int compare(IUser o1, IUser o2) {
			return o1.getUsername().compareTo(o2.getUsername());
		}; 
	};
}
