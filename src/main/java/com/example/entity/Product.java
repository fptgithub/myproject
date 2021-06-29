package com.example.entity;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.example.utils.StringListConverter;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Entity
@Table(name = "products")
@Builder
@NoArgsConstructor @AllArgsConstructor
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@NotBlank(message = "name is not empty")
	private String name;
	@NotNull(message = "price is not empty")
	@Min(value =  1,message = "price bigger than 1")
	private double price;
	@NotNull(message = "sale price is not empty")
	@Min(value =  1,message = "sale price bigger than 1")
	private double salePrice;
	@Column(columnDefinition = "TEXT")
	private String description;
	private boolean active;
	private LocalDate createDate;
	private String color;
	@NotBlank(message = "slug is not empty")
	private String slug;
	@NotBlank(message = "poster is not empty")
	@Column(columnDefinition = "TEXT")
	private String poster;
	
	@Convert(converter = StringListConverter.class)
	private List<String> images;
	
	@ManyToOne
	@JoinColumn(name = "createBy")
	private User user;
	
	@JsonIgnore
	@OneToMany(mappedBy = "product",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	private List<Card> cards;
	
	@JsonIgnore
	@OneToMany(mappedBy = "product",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	private List<OderDetail> orderDetails;
	
	@ManyToOne
	@JoinColumn(name = "category_id")
	private Category category;
	
	
	@ManyToOne
	@JoinColumn(name = "customtype_id")
	private CustomType customtype;
	
	
	@ManyToOne
	@JoinColumn(name = "brand_id")
	private Brand brand;
	
	@JsonIgnore
	@OneToMany(mappedBy = "product",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	private List<Evaluate> evaluates;
	
	public String[] imagesToArray() {
		return images.toArray(new String[images.size()]);
	}
}
