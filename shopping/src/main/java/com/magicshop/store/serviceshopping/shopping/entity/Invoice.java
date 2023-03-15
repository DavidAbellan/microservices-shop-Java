package com.magicshop.store.serviceshopping.shopping.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.Valid;
import lombok.Data;

@Data
@Entity
@Table(name="invoice")
public class Invoice {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="number_invoice")
	private String numberInvoice;
	
	@Column(name="customer_id")
	private Long customerId;
	
	@Column(name="description")
	private String description;
	
	@Column(name="created_at")
	@Temporal(TemporalType.DATE)
	private Date createdAt;
	
    @Valid
    @JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
    @OneToMany(fetch=FetchType.LAZY,cascade=CascadeType.ALL)
	private List<InvoiceItem>items;
    
    private String state;
    
    public Invoice() {items = new ArrayList<>();}
    
    @PrePersist
    public void prePersist() {this.createdAt = new Date();}
}
