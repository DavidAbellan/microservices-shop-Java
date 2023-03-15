package com.magicshop.store.customer.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.magicshop.store.customer.entity.Customer;
import com.magicshop.store.customer.entity.Region;

public interface CustomerRepository extends JpaRepository<Customer,Long>{
	public Customer findByNumberId(String numberId);
	public List<Customer> findByLastName(String lastName);
	public List<Customer> findByRegion(Region region);

}
