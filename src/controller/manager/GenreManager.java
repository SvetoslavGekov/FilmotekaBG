package controller.manager;

import model.dao.GenreDao;

public final class GenreManager {
	//Fields
	private static GenreManager instance;
	private static GenreDao dao;
	
	//Constructor
	private GenreManager() {
		//Instantiate the dao object
		dao = GenreDao.getInstance();
	}
	
	//Methods
	public synchronized static GenreManager getInstance() {
		if(instance == null) {
			instance = new GenreManager();
		}
		return instance;
	}
}
