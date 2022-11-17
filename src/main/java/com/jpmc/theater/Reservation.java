package com.jpmc.theater;

/**
 * @author jpmc
 * Class Reservation
 */
public class Reservation {
	
    private Customer customer;
    
    private Show show;

	private int numberOfTickets;

    /**
     * @param customer
     * @param show
     * @param audienceCount
     */
    public Reservation(Customer customer, Show show, int audienceCount) {
        this.customer = customer;
        this.show = show;
        this.numberOfTickets = audienceCount;
    }

	/**
	 * Get customer
	 * @return
	 */
	public Customer getCustomer() {
		return customer;
	}
	
	/**
	 * Get show
	 * @return show
	 */
	public Show getShow() {
		return show;
	}
	
    
	/**
	 * Get total reservation Price
	 * @return get total original price
	 */
	public double totalReservationPrice() {
        return show.getMovieTicketPrice() * numberOfTickets;
    }
    
    
	/**
	 * Get discounted Reservation Price
	 * @return discountedTotal price
	 */
	public double totalDiscountedPrice() {
    	return show.getMovieDiscountPrice() * numberOfTickets;
    }
}