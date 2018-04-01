package webSite;

import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import controller.manager.GenreManager;
import exceptions.InvalidGenreDataException;
import model.Genre;
import model.dao.GenreDao;

public final class WebSite {
	// Fields
	private static WebSite instance;
	private static final List<Genre> GENRES = new CopyOnWriteArrayList<>();

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

	public static void main(String[] args) throws SQLException, InvalidGenreDataException {
		GENRES.addAll(GenreDao.getInstance().getAllGenres());

		for (Genre g : GENRES) {
			System.out.println(g);
		}

	}
}
