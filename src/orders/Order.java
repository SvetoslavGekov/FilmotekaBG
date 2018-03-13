package orders;

import java.time.LocalDate;

import user.IConsumer;

public final class Order {
	//Fields
	private static int currentID;
	
	private int id;
	private LocalDate date;
	private IConsumer consumer;
	private double totalCost;

}
