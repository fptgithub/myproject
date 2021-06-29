package com.example.entity;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.example.utils.EOrderState;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Table(name = "oders")
@Entity
@Builder
@NoArgsConstructor @AllArgsConstructor
public class Oder {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String orderIdStr;
	private String address;
	private Double totalPrice;
	@Enumerated(EnumType.STRING)
	private EOrderState state;
	private String description;
	private LocalDate createDate;
	private String phone;
	
	@ManyToOne
	@JoinColumn(name = "username")
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	private User user;
	
	@JsonIgnore
	@OneToMany(mappedBy = "oder",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	private List<OderDetail> oderDetails;
}
