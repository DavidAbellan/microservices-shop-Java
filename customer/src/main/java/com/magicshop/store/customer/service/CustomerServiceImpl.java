package com.magicshop.store.customer.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.magicshop.store.customer.entity.Customer;
import com.magicshop.store.customer.entity.Region;
import com.magicshop.store.customer.repository.CustomerRepository;


import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl  implements CustomerService{
	@Autowired
	CustomerRepository customerRepository;
	@Override
	public List<Customer> findCustomerAll(){return customerRepository.findAll();}
	@Override
	public List<Customer> findCustomerByRegion(Region region){return customerRepository.findByRegion(region);}
	@Override
	public Customer createCustomer(Customer customer) 
	{
		Customer customerInDb= customerRepository.findByNumberId(customer.getNumberId());
		if(null!=customerInDb) 
		{
			return customerInDb;
		}
		customer.setState("NEW MEMBER");
		customerInDb = customerRepository.save(customer);
		return customerInDb;
	}
	@Override
	public Customer updateCustomer(Customer customer) 
	{
		Customer customerInDb= customerRepository.findByNumberId(customer.getNumberId());
		if(null==customerInDb) 
		{
			return null;
		}
		customerInDb.setFirstName(customer.getFirstName());
		customerInDb.setLastName(customer.getLastName());
		customerInDb.setEmail(customer.getEmail());
		customerInDb.setPhotoUrl(customer.getEmail());
		return customerRepository.save(customerInDb);
		
		
	}
	@Override
	public Customer deleteCustomer(Customer customer) 
	{
		Customer customerInDb= customerRepository.findByNumberId(customer.getNumberId());
		if(null==customerInDb) 
		{
			return null;
		}
		customer.setState("DELETED");
		return customerRepository.save(customer);
		
	}
	@Override
	public Customer getCustomer(Long id) {return customerRepository.findById(id).orElse(null);}
}
