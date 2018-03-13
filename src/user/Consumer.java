package user;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Map;

public class Consumer extends User implements IConsumer {

	private Collection<Product> favourites;
	private Collection<Product> watchList;
	private Map<Product, Review> reviews;
	private Map<Product, LocalDate> products;
	private Collection <Order> ordersHistory;
	private ShoppingCart shoppingCart;
	private double money;
	
}
