package shoppingcart;

import java.util.HashMap;
import java.util.Map;

import products.Product;

public final class ShoppingCart {
	//Fields
	private Map<Product, Boolean> products = new HashMap<>(); //Map with products and flag for renting or buying // buy(true) - rent(false)

	public void addProduct(Product product, boolean buy){
		if(product != null){
			products.put(product, buy);
		}
	}
	
	public void removeProduct(Product product){
		if(product != null){
			products.remove(product);
		}
	}
}
