package ticketmachine;

/**
 * TicketMachine models a naive ticket machine that issues flat-fare tickets. The price of a ticket is specified via the
 * constructor. It is a naive machine in the sense that it trusts its users to insert enough money before trying to print a
 * ticket. It also assumes that users enter sensible amounts.
 *
 * @author David J. Barnes and Michael Kolling
 * @version 2006.03.30
 */
public class TicketMachine {

	private final int price;
	private int balance;
	private int total;

	/**
	 * Create a machine that issues tickets of the given price.
	 *
	 * @param ticketCost the price of a ticket, >=0
	 */
	public TicketMachine(int ticketCost) {
		// Test de validité du paramètre
		if (ticketCost <= 0) {
			throw new IllegalArgumentException("Ticket price must be positive");
		}
		price = ticketCost;
		balance = 0;
		total = 0;
	}


	public int getPrice() {
		return price;
	}


	public int getTotal() {
		return total;
	}


	public int getBalance() {
		return balance;
	}


	public void insertMoney(int amount) throws ExceptionInsertionMontantNegatif {

		if(amount <= 0) {
			throw new ExceptionInsertionMontantNegatif();
		}
		else {
			balance = balance + amount;
		}
	}


	public int refund() {

		System.out.println("Je vous rends : " + balance + " centimes");
		this.balance = 0;
		return balance;
	}


	public boolean printTicket() {
		// Simulate the printing of a ticket.
		if(this.price<= balance){
		System.out.println("##################");
		System.out.println("# The BlueJ Line");
		System.out.println("# Ticket");
		System.out.println("# " + price + " cents.");
		System.out.println("##################");
		System.out.println();
		this.balance = balance - price;
		this.total = this.price + this.total;
		return true;
		}
		return false;

	}
}
