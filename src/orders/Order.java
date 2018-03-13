package orders;

import java.time.LocalDate;

public final class Order {
	//Fields
	private static int currentID;
	
	private int id;
	private LocalDate date;
	private IConsumer consumer;
	private double totalCost;

}
