package com.jbs.posbe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.jbs.posbe.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
	
	/*
	 * 1. save(Customer customer): Saves a new customer or updates an existing one.
	 * 2. saveAndFlush(Customer customer): Saves a new customer or updates an existing one and flushes changes instantly.
	 * 3. findById(Long customerId): Retrieves a customer by its ID.
	 * 4. findAll(): Retrieves all customers.
	 * 5. deleteById(Long customerId): Deletes a customer by its ID.
	 * 6. delete(Customer customer): Deletes a specific customer.
	 * 7. deleteAll(): Deletes all customers.
	 * 8. count(): Returns the total number of customers.
	 * 9. existsById(Long customerId): Checks if a customer exists by its ID.
	 */
}
