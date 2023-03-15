package com.magicshop.store.serviceproduct;

import java.util.Date;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.magicshop.store.serviceproduct.entity.Category;
import com.magicshop.store.serviceproduct.entity.Product;
import com.magicshop.store.serviceproduct.repository.ProductRepository;

@DataJpaTest
public class ProductRepositoryMockTest {

	@Autowired
	private ProductRepository productRepository;
	@Test
	public void whenFindByCategory_thenReturnListOfProduct() {

		Product product01 = Product.builder()
				.name("Dragon Ball")
				.category(Category.builder().id(1L).build())
				.description("Bola brillante con una o más estrellas")
				.stock(Double.parseDouble("7"))
				.price(Double.parseDouble("1900"))
				.status("Usada")
				.createdAt(new Date()).build();
		productRepository.save(product01);

		List<Product> found = productRepository.findByCategory(
				product01.getCategory());

		Assertions.assertThat(found).hasSize(3);


	}

}
