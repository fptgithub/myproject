package com.example.entity;

import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.example.utils.ERole;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Entity
@Table(name = "users")
@Builder
@NoArgsConstructor @AllArgsConstructor
public class User {
	
	@Id
	private String username;
	
	@JsonIgnore
	private String password;
	private String sex;
	private String email;
	private String phone;
	
	@JsonIgnore
	@OneToMany(mappedBy = "createBy",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	private List<Category> categorys;
	
	@JsonIgnore
	@OneToMany(mappedBy = "user",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	private List<Card> cards;
	
	@JsonIgnore
	@OneToMany(mappedBy = "user",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	private List<Oder> oders;
	
	@JsonIgnore
	@OneToMany(mappedBy = "user",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	private List<Evaluate> evaluates;
	
	@ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
	@JoinTable(name = "user_roles",
	joinColumns = @JoinColumn(name = "username"),
	inverseJoinColumns = @JoinColumn(name = "role_id")
			)
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	Set<Role> roles;
	
	public String[] getRoleArr() {
		String[] roleArr = new String[this.roles.size()];
		int i=0;
		for(Role r:this.roles) {
			roleArr[i] = r.getName().name();
			i++;
		}
		return roleArr;
	}
	
	public String roleToString() {
		String ro = "";
		for(Role r:roles) {
			ro+=r.name.name()+", ";
		}
		return ro;
	}
}
