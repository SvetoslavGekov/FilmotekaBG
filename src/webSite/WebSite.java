package webSite;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

import exceptions.InvalidGenreDataException;
import exceptions.InvalidProductDataException;
import model.Genre;
import model.Movie;
import model.Product;
import model.dao.GenreDao;
import model.dao.MovieDao;

public final class WebSite {
	// Fields
	private static WebSite instance;
	private static final List<Genre> GENRES = new CopyOnWriteArrayList<>();
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

	public static void addGenre(Genre g) {
		if (g != null) {
			GENRES.add(g);
		}
	}
	
	public static void addProduct(Product p) {
		if(p != null) {
			PRODUCTS.put(p.getId(), p);
		}
	}

	public static void main(String[] args) throws SQLException, InvalidGenreDataException, InvalidProductDataException {
		GENRES.addAll(GenreDao.getInstance().getAllGenres());
		
		Movie m = new Movie("Jikus", LocalDate.now(), "asd", 123, 123, 322);
		MovieDao.getInstance().saveMovie(m);
		m.setActors("Bace pepi");
		m.setTrailer("wwwwwwwwww");
		m.setGenres(new HashSet<>(GENRES));
		MovieDao.getInstance().updateMovie(m);
	}
}
