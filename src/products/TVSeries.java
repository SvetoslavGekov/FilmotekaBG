package products;

import java.time.LocalDate;
import java.util.Map;
import java.util.TreeMap;

public final class TVSeries extends Product {
	//Fields
	private LocalDate finishedAiring;
	private Map<Integer, Integer> seasons = new TreeMap<>(); //Key: Total Seasons, value: Total episodes per season 
}
