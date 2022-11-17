package com.jpmc.theater;

import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MovieTests {
	
    @Test
    void testSpecialMovieWith20PercentDiscount() {
        Movie spiderMan = new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90),12.5, 1);
        Show show = new Show(spiderMan, 5, LocalDateTime.of(LocalDate.now(), LocalTime.now()));
        assertEquals(10, spiderMan.getDiscountPrice(show));
    }
    
    @Test
    void testSequenceDiscount() {
    	//Test for discount $3
    	Movie turningRed = new Movie("Turning Red", Duration.ofMinutes(85),11, 10);
    	Show show = new Show(turningRed, 1, LocalDateTime.of(LocalDate.now(), LocalTime.now()));
    	assertEquals(8, turningRed.getDiscountPrice(show));
    	
    	//Test for discount $2
    	Movie turningRed1 = new Movie("Turning Red", Duration.ofMinutes(85),11, 10);
    	Show showing1 = new Show(turningRed1, 2, LocalDateTime.of(LocalDate.now(), LocalTime.now()));
    	assertEquals(9, turningRed.getDiscountPrice(showing1));
    	
    	//Test for No Discount
    	Movie theBatMan = new Movie("The Batman", Duration.ofMinutes(85),11, 10);
    	Show showing2 = new Show(theBatMan, 6, LocalDateTime.of(LocalDate.now(), LocalTime.now()));
    	assertEquals(11, theBatMan.getDiscountPrice(showing2));
    }
    
    @Test
    void test25PercentDiscount() {
        Movie spiderMan = new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90),50, 10);
        Show show = new Show(spiderMan, 5, LocalDateTime.of(LocalDate.now(), LocalTime.of(12, 30, 30)));
        assertEquals(37.5, spiderMan.getDiscountPrice(show));
        
        //No 25% discount applies test
        Movie spiderMan1 = new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90),50, 10);
        Show showing1 = new Show(spiderMan1, 5, LocalDateTime.of(LocalDate.now(), LocalTime.of(17, 30, 30)));
        assertEquals(50, spiderMan1.getDiscountPrice(showing1));
    }
    
    
    @Test
    void testSeventhDayDiscount() {
        Movie spiderMan = new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90),50, 10);
        Show show = new Show(spiderMan, 5, LocalDateTime.of(LocalDate.of(2022, 11, 7), LocalTime.now()));
        assertEquals(49, spiderMan.getDiscountPrice(show));
    }
    
    @Test
    void testBigDiscountWins() {
    	//Applying both Special Discount 20% and time based discount 25%
    	Movie spiderMan = new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90),50, 1);
    	Show show = new Show(spiderMan, 5, LocalDateTime.of(LocalDate.now(), LocalTime.of(12, 30, 30)));
    	//check 25% discount
    	assertEquals(37.5, spiderMan.getDiscountPrice(show));
    }
    
}
