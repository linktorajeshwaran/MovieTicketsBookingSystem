package com.jpmc.theater;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

/**
 * @author jpmc
 * Movie Class
 */
public class Movie {
	
    private static final int MOVIE_CODE_SPECIAL = 1;
    
    private static final int SEQ_DISCOUNT_INT_3 = 3;

    private static final int SEQ_DISCOUNT_INT_2 = 2;
    
    private String title = null;
    private String description = null;
    private Duration runningTime;
    private double ticketPrice = 0.0;
    private int specialCode = 1;

    /**
     * @param title
     * @param runningTime
     * @param ticketPrice
     * @param specialCode
     */
    public Movie(String title, Duration runningTime, double ticketPrice, int specialCode) {
        this.title = title;
        this.runningTime = runningTime;
        this.ticketPrice = ticketPrice;
        this.specialCode = specialCode;
    }

    /**
     * Get Movie Title
     * @return title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Get Movie running time
     * @return runningTime
     */
    public Duration getRunningTime() {
        return runningTime;
    }

    /**
     * Get Movie Ticket Price
     * @return ticketPrice
     */
    public double getTicketPrice() {
        return ticketPrice;
    }

    /**
     * Calculate the ticket Price with applicable discount
     * @param show
     * @return Discounted Ticket Price
     */
    public double getDiscountPrice(Show show) {
        return ticketPrice - calculateDiscount(show);
    }

    /**
     * calculates the discount
     * @param show
     * @return discount for the showing
     */
    private double calculateDiscount(Show show) {
    	int showSequence = show.getSequenceOfTheDay();
        double specialDiscount = 0.0;
        double sequenceDiscount = 0.0;
        double discountOnSpecificHours = 0.0;
        double discountOnSpecificDate = 0.0;        
        List<Double> discountsAmt = new ArrayList<Double>();
        
        //Special Code Discount - 20%
        if (MOVIE_CODE_SPECIAL == specialCode) {
            specialDiscount = ticketPrice * 0.2;  // 20% discount for special movie
            discountsAmt.add(specialDiscount);
        }
        
        //Sequence based Discount
        if (showSequence == 1) {
            sequenceDiscount = SEQ_DISCOUNT_INT_3; // $3 discount for 1st show
        } else if (showSequence == 2) {
            sequenceDiscount = SEQ_DISCOUNT_INT_2; // $2 discount for 2nd show
        }
        discountsAmt.add(sequenceDiscount);
        
        //Specific Hour based discount 25%
        if( show.getShowStartTime().getHour() > 11 && show.getShowStartTime().getHour() < 16 ) {
    		//25% discount
    		discountOnSpecificHours = ticketPrice * 0.25; 
    		discountsAmt.add(discountOnSpecificHours);
    	}    	
        
        //Specific date based discount
    	if (show.getShowStartTime().getDayOfMonth()==7) {
    		discountOnSpecificDate = 1.0;
    		discountsAmt.add(discountOnSpecificDate);
    	}   	
        // biggest discount wins    	
    	return discountsAmt.stream().max(Comparator.naturalOrder()).get();
    }

    @Override
    public boolean equals(Object o) {
    	
    	if (o == null || getClass() != o.getClass()) return false;
    	 
        if (this == o) return true;
       
        Movie movie = (Movie) o;
        return Double.compare(movie.ticketPrice, ticketPrice) == 0
                && Objects.equals(title, movie.title)
                && Objects.equals(description, movie.description)
                && Objects.equals(runningTime, movie.runningTime)
                && Objects.equals(specialCode, movie.specialCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, description, runningTime, ticketPrice, specialCode);
    }
}