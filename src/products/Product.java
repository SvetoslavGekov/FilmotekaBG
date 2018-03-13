package products;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public abstract class Product {
	//Enumerations
	public enum ProductType{
		MOVIE, TVSERIES;
	}
	
	public enum Genre{
		ACTION, ADVENTURE, ANIMATION, BIOGRAPHY, COMEDY, CRIME,
		DOCUMENTARY, DRAMA, FAMILY, FANTASY, HISTORY, HORROR,
		MUSIC, MUSICAL, MYSTERY, ROMANCE, SCIFI, SPORT,
		SUPERHERO, THRILLER, WAR, WESTERN;
	}
	
	//Fields
	private static final double MAX_RATING = 10.0d; //Maximum rating that can be given to the product 
	private static final int RENT_DURATION = 7;
	private static int currentID;
	
	private int id;
	private String name;
	private String description;
	private ProductType type;
	private String trailer; //Link to trailer
	private Set<String> writers = new HashSet<>();
	private Set<String> actors = new HashSet<>();
	private LocalDate releaseDate;
	private double viewerRating;
	private int totalVotes; //TODO 1 user should vote only once per product;
	private String pgRating;
	private int duration; //Product duration in minutes
	private Set<Genre> genres = new HashSet<>();
	private double rentCost;
	private double buyCost;
	
	
}
