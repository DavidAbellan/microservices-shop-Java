package com.magicshop.store.serviceproduct.entity;

import java.util.Date;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="product")
@Data
@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
@Builder
public class Product {
	@Id
	@GeneratedValue(strategy =GenerationType.IDENTITY)
	private Long id;

    @NotEmpty(message="El nombre no puede ser un campo vacío")
	private String name;
	private String description;
	@Positive(message="El stock debe ser mayor que 0")
	private Double stock;
	private Double price;
	private String status;

	@Column(name="created_at")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdAt;

	@NotNull(message="Tiene que pertenecer a una categoria")
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="category_id")
	private Category category;

	public Category getCategory() {
		return category;
	}

}
