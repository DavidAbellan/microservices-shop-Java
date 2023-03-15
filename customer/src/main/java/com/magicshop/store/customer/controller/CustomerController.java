package com.magicshop.store.customer.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.magicshop.store.customer.entity.Customer;
import com.magicshop.store.customer.entity.Region;
import com.magicshop.store.customer.service.CustomerService;

import jakarta.validation.Valid;



@RestController
@RequestMapping("/customers")
public class CustomerController {

	@Autowired
	CustomerService customerService;
	
	@GetMapping
	public ResponseEntity<List<Customer>>
	 listAllCustomers(@RequestParam(name="regionId",required=false)Long regionId){
		List<Customer> customers = new ArrayList<>();
		if (null==regionId) 
		{
			customers= customerService.findCustomerAll();
			 if (customers.isEmpty()) 
			 {
				 return ResponseEntity.noContent().build();
			 }
			return ResponseEntity.ok(customers);
		} else {
			Region region = new Region();
			region.setId(regionId);
			customers= customerService.findCustomerByRegion(region);
			if (!customers.isEmpty()) 
			{
				return ResponseEntity.ok(customers);
				
			}
			return ResponseEntity.noContent().build();
			
		}
	}
	
	@GetMapping(value="/{id}")
	public ResponseEntity<Customer> getCustomer (@PathVariable("id") Long id)
	{
	   if(null!=id) 
	   {	
		Customer c = customerService.getCustomer(id);
		return ResponseEntity.ok(c);
	   }
	   return ResponseEntity.notFound().build();
	} 
	
	@PutMapping(value="/{id}") 
	public ResponseEntity<Customer> 
	  updateCustomer(@PathVariable("id") Long id,@RequestBody Customer c)
	{
		c.setId(id);
		Customer cDB= customerService.updateCustomer(c);
		if(null!=cDB) {
			return ResponseEntity.ok(cDB);
		}
		return ResponseEntity.notFound().build();
		   
	}
	@PostMapping
	public ResponseEntity<Customer> 
	 createCustomer(@Valid @RequestBody Customer c,BindingResult result)
	{
		if (result.hasErrors()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,this.formatMessage(result));
		}
		if(null!=c) 
		{
			Customer cCreated = customerService.createCustomer(c);
			return ResponseEntity.status(HttpStatus.CREATED).body(cCreated);
		}
		return ResponseEntity.badRequest().build();
	}
	
	@DeleteMapping(value="/{id}")
	public ResponseEntity<Customer>deleteCustomer(@PathVariable("id") Long id)
	{
		Customer customerInDB = customerService.deleteCustomer(customerService.getCustomer(id));
		if (null!=customerInDB) 
		{ 
			return ResponseEntity.ok(customerInDB);
			
		}
		return ResponseEntity.notFound().build();
		
		
	}
	
	
	private String formatMessage(BindingResult r) {
		List<Map<String,String>> errors = r.getFieldErrors().stream()
		      .map(err -> {
		    	 Map<String,String> error = new HashMap<>();
		    	 error.put(err.getField(), err.getDefaultMessage());
		    	 return error;
		      }).collect(Collectors.toList());
		ErrorMessage errorMessage = ErrorMessage.builder()
				.code("01") 
				.messages(errors).build();
		/*Pasar a JSON la lista*/
		ObjectMapper mapper = new ObjectMapper();
		String jsonString = "";
		try {
			jsonString = mapper.writeValueAsString(errorMessage);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return jsonString;
	}
	
	
}
