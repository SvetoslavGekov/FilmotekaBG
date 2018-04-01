package model;

import java.time.LocalDate;
import java.util.Set;

import exceptions.InvalidProductDataException;

public class Movie extends Product {
	//Optional fields
	private String director;
	
	//Constructors
	//Constructor for creating a new movie
	public Movie(String name, LocalDate releaseDate, String pgRating, int duration, double rentCost, double buyCost)
			throws InvalidProductDataException {
		super(name, releaseDate, pgRating, duration, rentCost, buyCost);
	}
	
	
	//Constructor for loading a movie from the DB
	public Movie(int id, String name, LocalDate releaseDate, String pgRating, int duration, double rentCost,
			double buyCost, String description, String trailer, String writers, String actors, double viewerRating,
			int totalVotes, double sumOfUsersRatings, Set<Genre> genres, String director) throws InvalidProductDataException {
		super(id, name, releaseDate, pgRating, duration, rentCost, buyCost, description, trailer, writers, actors, viewerRating,
				totalVotes, sumOfUsersRatings, genres);
		setDirector(director);
	}

	//Setters
	public void setDirector(String director) {
		this.director = director;
	}
	
	//Getters
	public String getDirector() {
		return this.director;
	}
	
}
