package com.jpmc.theater;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 * @author jpmc
 * class LocalDateProvider
 */
public class LocalDateTimeProvider implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private static volatile LocalDateTimeProvider instance = null;
    
    
    //cannot be instantiated this class from other class - private constructor
    private LocalDateTimeProvider() {
    	if(instance!=null) {
    		new RuntimeException("This is singleton class, use getInstance method to get single instance");
    	}
    }

    /**
     * Provides an Instance
     * @return LocalDateProvider
     */
    public static LocalDateTimeProvider getInstance() {
        if (instance == null) {
        	synchronized (LocalDateTimeProvider.class) {
				if (instance == null) 
					instance = new LocalDateTimeProvider();
			}            
        }
        return instance;
    }

    /**
     * Provides Local current date
     * @return LocalDate
     */
    public LocalDate currentDate() {
        return LocalDate.now();
    }
    
    
    /**
     * Provides Local current time
     * @return LocalTime
     */
    public LocalTime currentTime() {
    	return LocalTime.now();
    }
    
    
    /**
     * handle serialize and deserialize operation.
     * @return
     */
    protected LocalDateTimeProvider readResolve() {
        return getInstance();
    }
}
