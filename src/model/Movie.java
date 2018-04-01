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
			double buyCost, String description,String poster, String trailer, String writers, String actors, double viewerRating,
			int totalVotes, double sumOfUsersRatings, Set<Genre> genres, String director) throws InvalidProductDataException {
		super(id, name, releaseDate, pgRating, duration, rentCost, buyCost, description,poster, trailer, writers, actors, viewerRating,
				totalVotes, sumOfUsersRatings, genres);
		setDirector(director);
	}

	//Methods
	@Override
	public String toString() {
		return String.format("Type:%s	Id:%d	Name:%s	Year:%s	PG:%s	Duration:%d	Rent: %.2f	Price: %.2f	%nDescr: %s%n	Post: %s	Trailer:%s"
				+ "	Writers:%s	Actors:%s	%nViewer_rating:%.2f	Votes:%d	Director:%s%n", this.getClass().getSimpleName(),
				this.getId(), getName(), getReleaseDate().getYear(), getPgRating(), getDuration(), getRentCost(),getBuyCost(),
				getDescription(), getPoster(), getTrailer(), getWriters(), getActors(), getViewerRating(), getTotalVotes(), getDirector());
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
