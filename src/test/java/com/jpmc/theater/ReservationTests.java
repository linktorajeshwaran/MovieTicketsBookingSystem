package com.jpmc.theater;

import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ReservationTests {

    @Test
    void testTotalReservationPrice() {
    	Customer customer = new Customer("John", 1001);
        Show show = new Show(
                new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90), 12.5, 1),
                1,
                LocalDateTime.now()
        );
        assertTrue(new Reservation(customer, show, 3).totalReservationPrice() == 37.5);
    }
    
    @Test
    void testReservedCustomer() {    	
    	Customer customer = new Customer("John Doe", 1002);
    	Movie theBatMan = new Movie("The Batman", Duration.ofMinutes(85),11, 10);
    	Show show = new Show(theBatMan,
                1,
                LocalDateTime.now()
        );    	
    	Reservation reservation = new Reservation(customer, show, 3);
    	
    	assertEquals(reservation.getCustomer().getName(), "John Doe");
    	assertEquals(reservation.getCustomer().getId(), 1002);
    }
    
    @Test
    void testTotalDiscountedPrice() {
    	Customer customer = new Customer("John", 1001);
        Movie spiderMan = new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90),50, 10);
        Show show = new Show(spiderMan, 5, LocalDateTime.of(LocalDate.now(), LocalTime.of(12, 30, 30)));
        Reservation reservation = new Reservation(customer, show, 3);

        assertEquals(112.5, reservation.totalDiscountedPrice());        
    }
}
