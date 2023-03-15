package com.magicshop.store.serviceshopping.shopping.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
@Entity
@Table(name="invoice_item")
public class InvoiceItem {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Positive(message="El stock debe ser mayor que 0")
	private Double quantity;
	
	@Positive(message="El precio debe ser mayor que 0")
	private Double price;
	
	@Column(name="product_id")
	private Long productId;
	
	@Transient
	private Double subTotal;
	
	public Double getSubtotal() 
	{ 
		if (this.price>0 && this.quantity>0)
		{
			return this.quantity*this.price;
		} else {
			return (double)0;
		}
		
	}
	public InvoiceItem() {
		this.quantity = (double)0;
		this.price = (double)0;
	}

}
