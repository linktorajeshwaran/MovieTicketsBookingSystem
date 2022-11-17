package com.jpmc.theater;

import java.util.Objects;

/**
 * @author jpmc
 *
 *Customer class
 */
public class Customer {

    private String name;

	private int id;
	
    /**
     * @param name customer name
     * @param id customer id
     */
    public Customer(String name, int id) {
        this.id = id; // NOTE - id is not used anywhere at the moment
        this.name = name;
    }

    /**
     * Get Customer name
     * @return name
     */
    public String getName() {
		return name;
	}

    
    /**
     * Get customer id
     * @return id
     */
    public int getId() {
		return id;
	}
	
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Customer)) return false;
        Customer customer = (Customer) o;
        return Objects.equals(name, customer.name) && Objects.equals(id, customer.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, id);
    }

    @Override
    public String toString() {
        return "name: " + name;
    }
}