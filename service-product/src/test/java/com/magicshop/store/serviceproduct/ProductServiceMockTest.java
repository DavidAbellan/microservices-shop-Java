package com.magicshop.store.serviceproduct;

import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.magicshop.store.serviceproduct.entity.Category;
import com.magicshop.store.serviceproduct.entity.Product;
import com.magicshop.store.serviceproduct.repository.ProductRepository;
import com.magicshop.store.serviceproduct.service.ProductService;
import com.magicshop.store.serviceproduct.service.ProductServiceImpl;

@SpringBootTest
public class ProductServiceMockTest {

	@Mock
	private ProductRepository productRepository;

	private ProductService productService;

	@SuppressWarnings("deprecation")
	@BeforeEach
	public void setup() {
		MockitoAnnotations.initMocks(this);
		productService = new ProductServiceImpl(productRepository);
		Product p = Product.builder()
				.id(1L)
				.name("Bastón de Gandalf")
				.category(Category.builder().id(1L).build())
				.price(Double.parseDouble("550"))
				.stock(Double.parseDouble("2"))
				.build();
		Mockito.when(productRepository.findById(1L))
		       .thenReturn(Optional.of(p));
		Mockito.when(productRepository.save(p))
		       .thenReturn(p);
	}
	@Test
	public void whenValidGetId_TheReturnProduct() {
		Product found = productService.getProduct(1L);
		Assertions.assertThat(found.getName()).isEqualTo("Bastón de Gandalf");

	}
	@Test
	public void whenValidUpdateStock_ThenReturnNewStock() {
		Product newStock = productService.updateStock(1L,Double.parseDouble("8"));
		Assertions.assertThat(newStock.getStock()).isEqualTo(10);

	}

}
