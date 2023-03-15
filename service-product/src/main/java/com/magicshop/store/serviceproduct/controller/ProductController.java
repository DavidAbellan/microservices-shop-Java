package com.magicshop.store.serviceproduct.controller;

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
import com.magicshop.store.serviceproduct.entity.Category;
import com.magicshop.store.serviceproduct.entity.Product;
import com.magicshop.store.serviceproduct.service.ProductService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value="/products")
public class ProductController {

	@Autowired
     private ProductService productService;

	@GetMapping
	 public ResponseEntity<List<Product>> listProduct(@RequestParam(name="categoryId", required=false)Long categoryId)
	{
		 List<Product> products= new ArrayList<>();
		 if(null == categoryId) {
		   products = productService.listAllProducts();
		  
		   if(products.isEmpty()) {
			 return ResponseEntity.noContent().build();
		  }
		   return ResponseEntity.ok(products);
		 } 
	     products = productService.findByCategory(Category.builder().id(categoryId).build());
	     return ResponseEntity.ok(products);
	 }
	@GetMapping(value="/{id}")
	 public ResponseEntity<Product> getProduct(@PathVariable("id") Long id){
		 if(null != id) {
		   Product p = productService.getProduct(id);
		   return ResponseEntity.ok(p);
		 }
		 return ResponseEntity.notFound().build();
	 }
	@PostMapping
	public ResponseEntity<Product> createProduct(@Valid @RequestBody Product p,BindingResult result)
	{
		if (result.hasErrors()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,this.formatMessage(result));
		}
		if(null!=p) 
		{
			Product pCreated = productService.createProduct(p);
			return ResponseEntity.status(HttpStatus.CREATED).body(pCreated);
		}
		return ResponseEntity.badRequest().build();
	}
	@PutMapping(value="/{id}")
	public ResponseEntity<Product> updateProduct(@PathVariable("id") Long id,@RequestBody Product p) {
		p.setId(id);
		Product pDB= productService.updateProduct(p);
		if(null!=pDB) {
			return ResponseEntity.ok(pDB);
		}
		return ResponseEntity.notFound().build();
		
	}
	@DeleteMapping(value="/{id}")
	public ResponseEntity<Product> deleteProduct(@PathVariable("id") Long id) {
		Product pDB= productService.deleteProduct(id);
		if(null!=pDB) {
			return ResponseEntity.ok(pDB);
		}
		return ResponseEntity.notFound().build();
		
	}
	@GetMapping(value="/{id}/stock")
	public ResponseEntity<Product> updateStockProduct(@PathVariable("id") Long id,@RequestParam(name="quantity", required=true) Double quantity ) {
		Product pDB= productService.updateStock(id, quantity);
		if(null!=pDB) {
			return ResponseEntity.ok(pDB);
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
