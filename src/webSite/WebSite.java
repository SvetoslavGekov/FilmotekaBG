package webSite;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentHashMap;

import exceptions.InvalidGenreDataException;
import exceptions.InvalidProductDataException;
import model.Genre;
import model.Movie;
import model.Product;
import model.TVSeries;
import model.dao.GenreDao;
import model.dao.MovieDao;
import model.dao.TVSeriesDao;

public final class WebSite {
	// Fields
	private static WebSite instance;
	private static final Map<Integer,Genre> GENRES = new TreeMap<>();
	private static final Map<Integer,Product> PRODUCTS = new ConcurrentHashMap<>(); 
	// Constructors
	private WebSite() {

	}

	// Methods
	public synchronized static WebSite getInstance() {
		if (instance == null) {
			instance = new WebSite();
		}
		return instance;
	}
	
	public static Genre getGenreById(int id) {
		return GENRES.get(id);
	}
	
	public static void addGenre(Genre g) {
		if (g != null) {
			GENRES.put(g.getId(),g);
		}
	}
	
	public static void addProduct(Product p) {
		if(p != null) {
			PRODUCTS.put(p.getId(), p);
		}
	}

	public static void main(String[] args) throws SQLException, InvalidGenreDataException, InvalidProductDataException {
		GENRES.putAll(GenreDao.getInstance().getAllGenres());
		
		for (Movie m : MovieDao.getInstance().getAllMovies()) {
			System.out.println(m);
		}
		
		for (TVSeries tvs : TVSeriesDao.getInstance().getAllTVSeries()) {
			System.out.println(tvs);
		}
		
//		Movie m = new Movie("Jikus", LocalDate.now(), "asd", 123, 123, 322);
//		MovieDao.getInstance().saveMovie(m);
//		m.setActors("Bace pepi");
//		m.setTrailer("wwwwwwwwww");
//		m.setDirector("Cameron John");
//		MovieDao.getInstance().updateMovie(m);
	}
}
