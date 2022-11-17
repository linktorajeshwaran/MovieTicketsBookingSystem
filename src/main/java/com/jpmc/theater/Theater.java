package com.jpmc.theater;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * @author jpmc
 * Class Theater
 */
public class Theater {

    private LocalDateTimeProvider localDateTimeProvider;
    private List<Show> schedule;

    /**
     * @param provider
     */
    public Theater(LocalDateTimeProvider provider) {
        this.localDateTimeProvider = provider;

        Movie spiderMan = new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90), 12.5, 1);
        Movie turningRed = new Movie("Turning Red", Duration.ofMinutes(85), 11, 0);
        Movie theBatMan = new Movie("The Batman", Duration.ofMinutes(95), 9, 0);
        schedule = List.of(
            new Show(turningRed, 1, LocalDateTime.of(provider.currentDate(), LocalTime.of(9, 0))),
            new Show(spiderMan, 2, LocalDateTime.of(provider.currentDate(), LocalTime.of(11, 0))),
            new Show(theBatMan, 3, LocalDateTime.of(provider.currentDate(), LocalTime.of(12, 50))),
            new Show(turningRed, 4, LocalDateTime.of(provider.currentDate(), LocalTime.of(14, 30))),
            new Show(spiderMan, 5, LocalDateTime.of(provider.currentDate(), LocalTime.of(16, 10))),
            new Show(theBatMan, 6, LocalDateTime.of(provider.currentDate(), LocalTime.of(17, 50))),
            new Show(turningRed, 7, LocalDateTime.of(provider.currentDate(), LocalTime.of(19, 30))),
            new Show(spiderMan, 8, LocalDateTime.of(provider.currentDate(), LocalTime.of(21, 10))),
            new Show(theBatMan, 9, LocalDateTime.of(provider.currentDate(), LocalTime.of(23, 0)))
        );
    }

    /**
     * Get Reservation
     * @param customer
     * @param sequence
     * @param howManyTickets
     * @return Reservation
     */
    public Reservation getReservation(Customer customer, int sequence, int howManyTickets) {
        Show show;
        try {
            show = schedule.get(sequence);
        } catch (RuntimeException ex) {
            ex.printStackTrace();
            throw new IllegalStateException("not able to find any showing for given sequence " + sequence);
        }
        return new Reservation(customer, show, howManyTickets);
    }
    
    /**
     * Print Schedule
     * @param printFormat
     */
    public void printSchedule(String printFormat) {
    	System.out.println("-----------------SCHEDULE-----------------------------");
        System.out.println(localDateTimeProvider.currentDate() +" "+ localDateTimeProvider.currentTime());
        System.out.println("===================================================");
    	if(printFormat != null) {    		
    		if (printFormat.equalsIgnoreCase("json")) {
    			System.out.println("Schedule in Json Format:");
    	        Map<String, String> json = new HashMap<String, String>();
    	        schedule.forEach( s -> json.put(String.valueOf(s.getSequenceOfTheDay()), s.getShowStartTime() + " " + s.getMovie().getTitle() + " " + humanReadableFormat(s.getMovie().getRunningTime()) + " $" + s.getMovieTicketPrice()));
    	        Gson gson = new GsonBuilder().setPrettyPrinting().create();
    	        System.out.println(gson.toJson(json));
    		} else {
    			System.out.println("Schedule in Text Format:");
    	        schedule.forEach(s ->
                System.out.println(s.getSequenceOfTheDay() + ": " + s.getShowStartTime() + " " + s.getMovie().getTitle() + " " + humanReadableFormat(s.getMovie().getRunningTime()) + " $" + s.getMovieTicketPrice()));
    		}
    		
    	}
        System.out.println("===================================================");
    }

    /**
     * @param duration
     * @return
     */
    private String humanReadableFormat(Duration duration) {
        long hour = duration.toHours();
        long remainingMin = duration.toMinutes() - TimeUnit.HOURS.toMinutes(duration.toHours());
        return String.format("(%s hour%s %s minute%s)", hour, handlePlural(hour), remainingMin, handlePlural(remainingMin));
    }

    
    /**
     * handlePlural
     * @param value
     * @return
     */
    private String handlePlural(long value) {
        return (value == 1)?"":"s";
    }

    /**
     * Application's Main method
     * @param args
     */
    public static void main(String[] args) {
        Theater theater = new Theater(LocalDateTimeProvider.getInstance());
        theater.printSchedule("text");
        theater.printSchedule("json");
    }
}
