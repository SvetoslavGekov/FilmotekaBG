package demo;

import java.util.Collection;
import java.util.HashSet;
import java.util.Map;

import products.Product;
import user.Consumer;
import user.User;

public final class WebSite {

	private static WebSite instance;
	private static User guest;
	private Map<String, User> allUsers;
	private Collection <Product> catalog = new HashSet<>();
	private User currentUser;
	private int money;
	
	private WebSite(){
		this.currentUser = WebSite.getGuest();
	}
	
	static User getGuest(){
		if(guest == null){
			try {
				guest = new Consumer("Guest", "guest", "guest", "guest") ;
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		return guest;
	}
	
	static WebSite getInstance(){
		if(instance == null){
			instance = new WebSite();
		}
		return instance;
	}
	
	public static void main(String[] args) {
		System.out.println("Demonstrationfiiiiii test.");
		System.out.println("hi");
		System.out.println("Sending one message back.");
	}
	
	static void showMenu(){
		System.out.println();
	}

	public void addToCatalog(Product product) {
		if(product != null){
			catalog.add(product);
		}
	}
	
	public void removeFromCatalog(Product product) {
		if(product != null){
			catalog.remove(product);
		}
	}

}
