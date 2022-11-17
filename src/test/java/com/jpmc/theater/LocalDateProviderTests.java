package com.jpmc.theater;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

public class LocalDateProviderTests {
	
	 /**
	 * Test the current Date returned from LocalDateProvider 
	 */
	@Test
	 void testCurrentDate() {	    
    	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-YYYY");
    	String localProviderDate = formatter.format(LocalDateTimeProvider.getInstance().currentDate());	    
	    assertEquals(LocalDate.now().getMonthValue(), Integer.parseInt(localProviderDate.substring(0, 2)));
        assertEquals(LocalDate.now().getDayOfMonth(), Integer.parseInt(localProviderDate.substring(3, 5)));        
        assertEquals(LocalDate.now().getYear(), Integer.parseInt(localProviderDate.substring(6, 10)));  
	 }

	 /**
	 * Test the current time returned from LocalDate
	 */
	@Test
     void testCurrentTime() {
        System.out.println("Current time is - " + LocalDateTimeProvider.getInstance().currentTime());
    	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
    	String localProviderCurrentTime = formatter.format(LocalDateTimeProvider.getInstance().currentTime());
    	
        assertEquals(LocalTime.now().getHour(), Integer.parseInt(localProviderCurrentTime.substring(0, 2)));
        assertEquals(LocalTime.now().getMinute(), Integer.parseInt(localProviderCurrentTime.substring(3, 5)));        
     }
}
