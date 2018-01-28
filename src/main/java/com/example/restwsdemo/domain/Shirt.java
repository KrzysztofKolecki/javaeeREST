package com.example.restwsdemo.domain;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Collection;

@XmlRootElement
@NamedQueries({ 
	@NamedQuery(name = "shirt.all", query = "Select s from Shirt s"),
	@NamedQuery(name = "shirt.delete.all", query = "Delete from Shirt "),
	@NamedQuery(name = "shirt.findBySize", query = "Select s from Shirt s where s.size = :size"),
	@NamedQuery(name = "shirt.findByCustomer",
	query = "Select c.name, c.surname, s.name, s.size, s.color from Shirt s JOIN s.customer c where c.surname = :surname")
	})
@Entity
public class Shirt {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String name;
	private String size;
	private String color;
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Customer customer;
	
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Collection<Supplier> suppliers;
	
	
	public Customer getCustomer() {
		return customer;
	}
	

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	
	public Collection<Supplier> getSuppliers() {
		return suppliers;
	}


	public void setSuppliers(Collection<Supplier> suppliers) {
		this.suppliers = suppliers;
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
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}

	
	public Shirt(long id, String name, String size, String color, Customer customer, Collection<Supplier> suppliers) {
		super();
		this.id = id;
		this.name = name;
		this.size = size;
		this.color = color;
		this.customer = customer;
		this.suppliers = suppliers;
	}

	public Shirt(String name, String size, String color) {
		super();
		this.name = name;
		this.size = size;
		this.color = color;
	}

	public Shirt() {
		super();
	}

	
}
