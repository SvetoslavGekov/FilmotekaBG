package user;

import java.time.LocalDate;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.TreeSet;

import orders.Order;
import products.Product;
import shoppingcart.ShoppingCart;

public class Consumer extends User implements IConsumer {
	
	private Collection<Product> favourites = new HashSet<>();
	private Collection<Product> watchList = new HashSet<>();
	//private Map<Product, Review> reviews;
	private Map<Product, LocalDate> products = new HashMap<>();
	private Collection <Order> ordersHistory = new TreeSet<>();;
	private ShoppingCart shoppingCart;
	private double money;
	

	public Consumer(String names, String username, String password, String email) throws Exception {
		super(names, username, password, email);
	}
	
}
