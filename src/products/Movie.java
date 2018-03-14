package products;

import java.time.LocalDate;

public final class Movie extends Product {
	//Fields
	private String director;

	//Constructor
	public Movie(String name, LocalDate releaseDate, String pgRating, int duration,
			double rentCost, double buyCost) {
		super(name, ProductType.MOVIE, releaseDate, pgRating, duration, rentCost, buyCost);
	}
}
