package products;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import customexceptions.InvalidProductInfoException;
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
	
	public Product(int id, String name, ProductType type, LocalDate releaseDate, String pgRating, int duration,
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
	
	
}
