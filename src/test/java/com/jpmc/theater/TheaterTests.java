package com.jpmc.theater;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TheaterTests {
    @Test
    void testTheaterReservation() {
        Theater theater = new Theater(LocalDateTimeProvider.getInstance());
        Customer john = new Customer("John Doe", 12345);
        Reservation reservation = theater.getReservation(john, 2, 4);
        assertEquals(reservation.totalReservationPrice(), 36);
    }

    @Test
    void printMovieSchedule() {
        Theater theater = new Theater(LocalDateTimeProvider.getInstance());
        theater.printSchedule("text");
        theater.printSchedule("json");
    }
}
