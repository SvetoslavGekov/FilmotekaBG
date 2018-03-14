package products;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;

import customexceptions.DatabaseConflictException;
import customexceptions.InvalidProductInfoException;
import customexceptions.InvalidUserInfoException;
import products.Product.ProductType;
import validation.Supp;

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
	
	public Product(String name, ProductType type, LocalDate releaseDate, String pgRating, int duration,
			double rentCost, double buyCost) {
		setId();
		setName(name);
		setType(type);
		setReleaseDate(releaseDate);
		setPgRating(pgRating);
		setDuration(duration);
		setRentCost(rentCost);
		setBuyCost(buyCost);
	}
	
	//Methods
	@Override
	public String toString() {
		return String.format("Name: %s	Type: %s	Release date: %s	PGRating: %s	Duration: %s	RentCost: %.2f	Price: %.2f", 
				this.name, this.type, this.releaseDate, this.pgRating, this.duration, this.rentCost, this.buyCost);
	}
	
	public static double inputBuyCost() throws InvalidProductInfoException{
		//User inputs product cost
		System.out.print("Please enter the product's price: ");
		double buyCost = Supp.getDouble();
		if(buyCost <= 0d) {
			throw new InvalidProductInfoException("You've entered an invalid price. Prices should be positive numbers.");
		}
		return buyCost;
	}
	
	public static double inputRentCost() throws InvalidProductInfoException{
		//User inputs product rent cost
		System.out.print("Please enter the product's rent cost: ");
		double rentCost = Supp.getDouble();
		if(rentCost <= 0d) {
			throw new InvalidProductInfoException("You've entered an invalid rent cost. Rent costs should be positive numbers.");
		}
		return rentCost;
	}
	
	public static int inputDuration() throws InvalidProductInfoException{
		//User inputs product duration
		System.out.print("Please enter the product duration: ");
		int duration = Supp.getPositiveNumber();
		if(duration <= 0) {
			throw new InvalidProductInfoException("You've entered an invalid duration. Product durations should be positive numbers");
		}
		return duration;
	}
	
	public static String inputPGRating() throws InvalidProductInfoException {
		//User inputs product pgRating
		System.out.print("Please enter the product pgRating: ");
		String pgRating = Supp.inputString();
		if(!Supp.validStr(pgRating)) {
			throw new InvalidProductInfoException("Sorry, you've entered an invalid string for the PG Rating.");
		}
		return pgRating;
	}
	
	public static LocalDate inputProductReleaseDate() throws InvalidProductInfoException{
		//User inputs product release date
		System.out.print("Please enter the product release date: ");
		String input = Supp.inputString();
		LocalDate releaseDate = null;
		try {
			releaseDate = LocalDate.parse(input);
		}
		catch (DateTimeParseException e) {
			throw new InvalidProductInfoException("You've entered an invalid date.\nDates should have the following format - \"yyyy-mm-dd\"");
		}
		return releaseDate;
	}
	
	public static String inputProductName() throws InvalidProductInfoException{
		//User inputs product name
		System.out.print("Please enter the product name: ");
		String name = Supp.inputString();
		
		//Check if productname is valid
		if(!Supp.validStr(name)) {
			throw new InvalidProductInfoException("Sorry, you've entered an invalid string for the product name.");
		}
		return name;
	}
	
	public static int inputProductType() throws InvalidProductInfoException{
		ProductType[] allProducts = ProductType.values();
		System.out.println("\nAvailable product types:");
		int counter = 1;
		for (ProductType pType : allProducts) {
			System.out.printf("%d) %s;%n",counter++, pType);
		}
		
		System.out.print("Choose a product type to continue: ");
		int pType = Supp.getPositiveNumber();
		if(pType <= 0 || pType > allProducts.length) {
			throw new InvalidProductInfoException("You've entered an invalid product type.");
		}
		return pType;
	}
	
	//Setters
	public static void setCurrentID(int currentID) {
		Product.currentID = currentID;
	}
	
	private void setId() {
		this.id = Product.currentID++;
	}

	private void setName(String name) {
		if(Supp.validStr(name)) {
			this.name = name;
		}
	}

	private void setDescription(String description) {
		if(Supp.validStr(description)) {
			this.description = description;
		}
	}

	private void setType(ProductType type) {
		if(type != null) {
			this.type = type;
		}
	}

	private void setTrailer(String trailer) {
		if(Supp.validStr(trailer)) {
			this.trailer = trailer;
			
		}
	}

	private void setWriters(Set<String> writers) {
		if(writers != null && !writers.isEmpty() && !writers.contains(null)) {
			this.writers = writers;
		}
	}

	private void setActors(Set<String> actors) {
		if(actors != null && !actors.isEmpty() && !actors.contains(null)) {
			this.actors = actors;
		}
	}

	private void setReleaseDate(LocalDate releaseDate) {
		if(releaseDate != null) {
			this.releaseDate = releaseDate;
		}
	}

	private void setViewerRating(double viewerRating) {
		if( viewerRating >= 0 && viewerRating < Product.MAX_RATING){
			this.viewerRating = viewerRating;
		}
	}

	private void setTotalVotes(int totalVotes) {
		if(totalVotes > 0) {
			this.totalVotes = totalVotes;
		}
	}

	private void setPgRating(String pgRating) {
		if(Supp.validStr(pgRating)) {
			this.pgRating = pgRating;
		}
	}

	private void setDuration(int duration) {
		if(duration > 0) {
			this.duration = duration;
		}
	}

	private void setGenres(Set<Genre> genres) {
		if(genres != null && !genres.isEmpty() && !genres.contains(null)) {
			this.genres = genres;
		}
	}

	private void setRentCost(double rentCost) {
		if(rentCost > 0) {
			this.rentCost = rentCost;	
		}
	}

	private void setBuyCost(double buyCost){
		if(buyCost > 0) {
			this.buyCost = buyCost;
		}
	}
	
	//Comparators
	public static final Comparator<Product> compareByNameAndReleaseDate = new Comparator<Product>() {
		public int compare(Product o1, Product o2) {
			//If they have same names
			if(o1.name.equalsIgnoreCase(o2.name)) {
				//If they have same releaseDates
				if(o1.releaseDate.isEqual(o2.releaseDate)) {
					return 0;
				}
				//Return date comparison
				return o1.releaseDate.compareTo(o2.releaseDate);
			}
			else {
				//If they don't have same names --> return comparison by names
				return o1.name.compareTo(o2.name);
			}
		};
	};
	
}
