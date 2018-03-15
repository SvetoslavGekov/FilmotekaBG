package products;

import java.time.LocalDate;
import java.util.Map;
import java.util.TreeMap;

public final class TVSeries extends Product {
	//Fields
	private LocalDate finishedAiring;
	private Map<Integer, Integer> seasons = new TreeMap<>(); //Key: Total Seasons, value: Total episodes per season 
	
	//Constructor
	public TVSeries(String name, LocalDate releaseDate, String pgRating, int duration,
			double rentCost, double buyCost) {
		super(name, ProductType.TVSERIES, releaseDate, pgRating, duration, rentCost, buyCost);
	}

	//Methods
	@Override
	public void printProductEditingMenu() {
		super.printProductEditingMenu();
		System.out.println("New items start here");
		selectFromProductEditingMenu();
	}
	
	@Override
	protected void selectFromAdvancedEditingMenu(int option) {
		switch (option) {
		case 13: //TODO ;break;
		default:
			System.out.println("You've chosen an invalid option for this menu. Please try again.");
			selectFromProductEditingMenu();
			break;
		}
	}
}
