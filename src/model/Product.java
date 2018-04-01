package model;



import java.time.LocalDate;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import exceptions.InvalidProductDataException;
import validation.Supp;

public abstract class Product{
	//Mandatory fields
	private int id;
	private String name;
	private LocalDate releaseDate;
	private String pgRating;
	private int duration; //Product duration in minutes
	private double rentCost;
	private double buyCost;
	
	//Optional fields
	private String description;
	private String poster; //Link to poster
	private String trailer; //Link to trailer
	private String writers;
	private String actors;
	private double viewerRating;
	private int totalVotes;
	private double sumOfUsersRatings;
	private Set<Genre> genres = new HashSet<>();
//	private Map<IUser, Double> raters = new HashMap<>();


	//Constructors
	//Constructor for creating a new product
	public Product(String name, LocalDate releaseDate, String pgRating, int duration, double rentCost, double buyCost) 
			throws InvalidProductDataException {
		super();
		setName(name);
		setReleaseDate(releaseDate);
		setPgRating(pgRating);
		setDuration(duration);
		setRentCost(rentCost);
		setBuyCost(buyCost);
	}

	//Constructor for loading a product from the DB
	public Product(int id, String name, LocalDate releaseDate, String pgRating, int duration, double rentCost,
			double buyCost, String description, String poster, String trailer, String writers, String actors, double viewerRating,
			int totalVotes, double sumOfUsersRatings, Set<Genre> genres) throws InvalidProductDataException {
		this(name, releaseDate, pgRating, duration, rentCost, buyCost);
		setId(id);
		setDescription(description);
		setPoster(poster);
		setTrailer(trailer);
		setWriters(writers);
		setActors(actors);
		setViewerRating(viewerRating);
		setTotalVotes(totalVotes);
		setSumOfUsersRatings(sumOfUsersRatings);
		setGenres(genres);
	}

	//Setters
	public void setId(int id) throws InvalidProductDataException {
		if(id > 0) {
			this.id = id;
		}
		else {
			throw new InvalidProductDataException("Invalid product id.");
		}
	}

	public void setName(String name) throws InvalidProductDataException {
		if(Supp.isValidStr(name)) {
			this.name = name;
		}
		else {
			throw new InvalidProductDataException("Invalid product name.");
		}
	}

	public void setReleaseDate(LocalDate releaseDate) throws InvalidProductDataException {
		if(releaseDate != null) {
			this.releaseDate = releaseDate;
		}
		else {
			throw new InvalidProductDataException("Invalid product release date.");
		}
	}

	public void setPgRating(String pgRating) throws InvalidProductDataException {
		if(Supp.isValidStr(pgRating)) {
			this.pgRating = pgRating;
		}
		else {
			throw new InvalidProductDataException("Invalid product PG Rating");
		}
	}

	public void setDuration(int duration) throws InvalidProductDataException {
		if(duration > 0) {
			this.duration = duration;
		}
		else {
			throw new InvalidProductDataException("Invalid product duration");
		}
	}

	public void setRentCost(double rentCost) throws InvalidProductDataException {
		if(rentCost > 0) {
			this.rentCost = rentCost;
		}
		else {
			throw new InvalidProductDataException("Invalid product rent cost");
		}
	}

	public void setBuyCost(double buyCost) throws InvalidProductDataException {
		if(buyCost > 0) {
			this.buyCost = buyCost;
		}
		else {
			throw new InvalidProductDataException("Invalid product buying price");
		}
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setPoster(String poster) {
		this.poster = poster;
	}
	
	public void setTrailer(String trailer) {
		this.trailer = trailer;
	}

	public void setWriters(String writers) {
		this.writers = writers;
	}

	public void setActors(String actors) {
		this.actors = actors;
	}

	public void setViewerRating(double viewerRating) {
		if(viewerRating >= 0) {
			this.viewerRating = viewerRating;
		}
	}

	public void setTotalVotes(int totalVotes) {
		if(totalVotes >= 0) {
			this.totalVotes = totalVotes;
		}
	}

	public void setSumOfUsersRatings(double sumOfUsersRatings) {
		if(sumOfUsersRatings >= 0) {
			this.sumOfUsersRatings = sumOfUsersRatings;
		}
	}

	public void setGenres(Set<Genre> genres) {
		this.genres = genres;
	}

	
	
	//Getters
	public int getId() {
		return this.id;
	}

	public String getName() {
		return this.name;
	}

	public LocalDate getReleaseDate() {
		return this.releaseDate;
	}

	public String getPgRating() {
		return this.pgRating;
	}

	public int getDuration() {
		return this.duration;
	}

	public double getRentCost() {
		return this.rentCost;
	}

	public double getBuyCost() {
		return this.buyCost;
	}

	public String getDescription() {
		return this.description;
	}

	public String getPoster() {
		return this.poster;
	}

	public String getTrailer() {
		return this.trailer;
	}

	public String getWriters() {
		return this.writers;
	}

	public String getActors() {
		return this.actors;
	}

	public double getViewerRating() {
		return this.viewerRating;
	}

	public int getTotalVotes() {
		return this.totalVotes;
	}

	public double getSumOfUsersRatings() {
		return this.sumOfUsersRatings;
	}

	public Set<Genre> getGenres() {
		return Collections.unmodifiableSet(this.genres);
	}
	
	
}
