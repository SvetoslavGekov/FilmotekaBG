package products;

import java.time.LocalDate;

import validation.Supp;

public final class Movie extends Product {
	//Fields
	private String director = "";

	//Constructor
	public Movie(String name, LocalDate releaseDate, String pgRating, int duration,
			double rentCost, double buyCost) {
		super(name, ProductType.MOVIE, releaseDate, pgRating, duration, rentCost, buyCost);
	}
	
	//Methods
	
	@Override
	protected void printFullInfo() {
		super.printFullInfo();
		System.out.printf("Director: %s%n", this.director);
	}
	
	@Override
	public void printProductEditingMenu() {
		//Print standard menu
		super.printProductEditingMenu();
		
		//Print advanced menu
		System.out.println("	13) Edit director");
		selectFromProductEditingMenu();
	}
	
	private void editDirector() {
		System.out.println("\nCurrent director: " + this.director);
		this.setDirector(this.inputMovieDirector());
	}
	
	@Override
	protected void selectFromAdvancedEditingMenu(int option) {
		switch (option) {
		case 13: this.editDirector(); this.printProductEditingMenu(); break;
		default:
			System.out.println("You've chosen an invalid option for this menu. Please try again.");
			selectFromProductEditingMenu();
			break;
		}
	}
	
	private String inputMovieDirector() {
		//User inputs director (can be empty)
		System.out.print("Please enter the product's director: ");
		return Supp.inputString();
	}
	
	//Setters
	public void setDirector(String director) {
		if(director != null) {
			this.director = director;
		}
	}
}
