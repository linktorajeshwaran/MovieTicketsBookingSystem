package com.jpmc.theater;

import java.time.LocalDateTime;

/**
 * @author jpmc
 * Class show
 */
public class Show {
	
    private Movie movie;
    
    private int sequenceOfTheDay;
    
    private LocalDateTime showStartTime;

    /**
     * @param movie
     * @param sequenceOfTheDay
     * @param showStartTime
     */
    public Show(Movie movie, int sequenceOfTheDay, LocalDateTime showStartTime) {
        this.movie = movie;
        this.sequenceOfTheDay = sequenceOfTheDay;
        this.showStartTime = showStartTime;
    }

    /**
     * Get Movie
     * @return movie
     */
    public Movie getMovie() {
        return movie;
    }

    /**  
     * get 
     * @return showStartTime
     */
    public LocalDateTime getShowStartTime() {
        return showStartTime;
    }

    /**
     * @param sequence
     * @return true or false
     */
    public boolean isSequence(int sequence) {
        return this.sequenceOfTheDay == sequence;
    }

    /**
     * 
     * @return ticketPrice
     */
    public double getMovieTicketPrice() {
        return movie.getTicketPrice();
    }

    /**
     * @return sequenceOfTheDay
     */
    public int getSequenceOfTheDay() {
        return sequenceOfTheDay;
    }
    
    /**
     * Get the movie discount price
     * @return discounted Price
     */
    public double getMovieDiscountPrice() {
    	return movie.getDiscountPrice(this);
    }
}
