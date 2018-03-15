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
}
