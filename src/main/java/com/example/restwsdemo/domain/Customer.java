package com.example.restwsdemo.domain;

import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonIgnore;

@XmlRootElement
@NamedQueries({ 
	@NamedQuery(name = "customer.all", query = "Select c from Customer c"),
	@NamedQuery(name = "customer.delete.all", query = "Delete from Customer"),
	})
@Entity
public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String name;
	private String surname;
	
	@OneToOne
	private Address address;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy="customer")
	@JsonIgnore
	private Collection<Shirt> shirts;
	
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}	
	public Collection<Shirt> getShirts() {
		return shirts;
	}
	public void setShirts(Collection<Shirt> shirts) {
		this.shirts = shirts;
	}
	
	public Customer(long id, String name, String surname, Address address) {
		super();
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.address = address;
	}
	
	public Customer(String name, String surname) {
		super();
		this.name = name;
		this.surname = surname;
	}
	
	public Customer() {
		super();
	}
	
}
