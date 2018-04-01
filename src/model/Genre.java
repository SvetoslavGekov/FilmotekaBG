package model;

import validation.Supp;

public class Genre {
	//Fields
	private int id;
	private String value;


	//Constructors
	//Constructor for creating a new genre
	public Genre(String value) {
		setValue(value);
	}

	//Constructor for loading an existing genre from the DB
	public Genre(int id, String value) {
		this(value);
		setId(id);
	}
	
	//Methods
	@Override
	public String toString() {
		return String.format("Id: %d	Genre: %s", this.id, this.value);
	}

	//Setters
	public void setId(int id) {
		if(id > 0) {
			this.id = id;
		}
	}
	
	private void setValue(String value) {
		if(Supp.isValidStr(value)) {
			this.value = value;
		}
	}
	
	//Getters
	public int getId() {
		return this.id;
	}
	
	public String getValue() {
		return this.value;
	}

}
