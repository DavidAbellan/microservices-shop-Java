package com.magicshop.store.serviceshopping.shopping.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.magicshop.store.serviceshopping.shopping.entity.InvoiceItem;

public interface InvoiceItemRepository extends JpaRepository<InvoiceItem,Long>{
	

}
