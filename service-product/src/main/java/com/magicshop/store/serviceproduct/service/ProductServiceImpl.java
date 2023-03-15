package com.magicshop.store.serviceproduct.service;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.magicshop.store.serviceproduct.entity.Category;
import com.magicshop.store.serviceproduct.entity.Product;
import com.magicshop.store.serviceproduct.repository.ProductRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor

public class ProductServiceImpl implements ProductService{

	private final ProductRepository productRepository;

	@Override
	public List<Product> listAllProducts() {
		return productRepository.findAll();
	}

	@Override
	public Product getProduct(Long id) {
		return productRepository.findById(id).orElse(null);
	}
	@Override
	public Product createProduct(Product product) {
		product.setStatus("New");
		product.setCreatedAt(new Date());
		return productRepository.save(product);
	}
	@Override
	public Product updateProduct(Product product) {
		Product productInDB = getProduct(product.getId());
		if (null == productInDB) {
			return null;
		}
		productInDB.setName(product.getName());
		productInDB.setDescription(product.getDescription());
		productInDB.setCategory(product.getCategory());
		productInDB.setPrice(product.getPrice());
		productInDB.setStatus("Updated");
		return productRepository.save(productInDB);
	}
	@Override
	public Product deleteProduct(Long id) {
		Product productInDB = getProduct(id);
		if (null == productInDB) {
			return null;
		}
		productInDB.setStatus("Deleted");
		return productRepository.save(productInDB);
	}

	@Override
	public List<Product> findByCategory(Category category) {
		return productRepository.findByCategory(category);
	}

	@Override
	public Product updateStock(Long id, Double quantity) {
		Product productInDB = getProduct(id);
		if (null == productInDB) {
			return null;
		}
		Double stock = productInDB.getStock() + quantity;
		productInDB.setStock(stock);
		return productRepository.save(productInDB);

	}


}
