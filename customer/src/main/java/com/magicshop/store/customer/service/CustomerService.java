package com.magicshop.store.customer.service;

import java.util.List;

import com.magicshop.store.customer.entity.Customer;
import com.magicshop.store.customer.entity.Region;

public interface CustomerService  {
	public List<Customer> findCustomerAll();
	public List<Customer> findCustomerByRegion(Region region);
	public Customer createCustomer(Customer customer);
	public Customer updateCustomer(Customer customer);
	public Customer deleteCustomer(Customer customer);
	public Customer getCustomer(Long id);
}
