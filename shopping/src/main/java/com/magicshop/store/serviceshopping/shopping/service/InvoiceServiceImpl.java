package com.magicshop.store.serviceshopping.shopping.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.magicshop.store.serviceshopping.shopping.entity.Invoice;
import com.magicshop.store.serviceshopping.shopping.repository.InvoiceItemRepository;
import com.magicshop.store.serviceshopping.shopping.repository.InvoiceRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class InvoiceServiceImpl implements InvoiceService 
{
	    @Autowired
	    InvoiceRepository invoiceRepository;

	    @Autowired
	    InvoiceItemRepository invoiceItemsRepository;
	   
	    @Override
	    public List<Invoice> findInvoiceAll() {
	        return  invoiceRepository.findAll();
	    }
	    @Override
	    public Invoice createInvoice(Invoice invoice) {
	        Invoice invoiceDB = invoiceRepository.findByNumberInvoice ( invoice.getNumberInvoice () );
	        if (invoiceDB !=null){
	            return  invoiceDB;
	        }
	        invoice.setState("CREATED");
	        invoiceDB = invoiceRepository.save(invoice);
	        return invoiceDB;
	        
	    } 
	    @Override
	    public Invoice updateInvoice(Invoice invoice) {
	        Invoice invoiceDB = getInvoice(invoice.getId());
	        if (invoiceDB == null){
	            return  null;
	        }
	        invoiceDB.setCustomerId(invoice.getCustomerId());
	        invoiceDB.setDescription(invoice.getDescription());
	        invoiceDB.setNumberInvoice(invoice.getNumberInvoice());
	        invoiceDB.getItems().clear();
	        invoiceDB.setItems(invoice.getItems());
	        return invoiceRepository.save(invoiceDB);
	    
	    } 
	    @Override
	    public Invoice deleteInvoice(Invoice invoice) {
	        Invoice invoiceDB = getInvoice(invoice.getId());
	        if (invoiceDB == null){
	            return  null;
	        }
	        invoiceDB.setState("DELETED");
	        return invoiceRepository.save(invoiceDB);
	    }
	    @Override
	    public Invoice getInvoice(Long id) 
	    {
	    	Invoice invoice= invoiceRepository.findById(id).orElse(null);
	    	return invoice ;
	    } 	
	    
}
