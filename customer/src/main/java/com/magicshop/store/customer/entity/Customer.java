package com.magicshop.store.customer.entity;

import java.io.Serializable;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@SuppressWarnings("serial")
@Data
@Entity
@Table(name="customer")
public class Customer implements Serializable {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@NotEmpty(message="El número de documento no puede estar vacío")
	@Size(min=8,max=8, message="El tamaño de documento debe ser 8")
	@Column(name="number_id",unique=true, length=8, nullable=false)
	private String numberId;
	
	@NotEmpty(message="El campo nombre no puede estar vacío")
	@Column(name="first_name",nullable=false)
	private String firstName;
	
	@NotEmpty(message="El campo apellido no puede estar vacío")
	@Column(name="last_name",nullable=false)
	private String lastName;
	
	@NotEmpty(message="El campo e-mail no puede estar vacío")
	@Email(message="El email es incorrecto")
	@Column(name="email",nullable=false)
	private String email;
	
	@Column(name="photo_url")
	private String photoUrl;
	
	@NotNull(message="La región no puede ser nula")
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="region_id")
	@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
	private Region region;
	
	private String state;

}
