package com.example.restwsdemo.domain;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@Entity
public class Shirt {
	
	public enum Size {
        s, m, l, xl;
    }
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String name;
	private Size size;
	private String color;
	
	@ManyToOne
	private Customer customer;
	
	@ManyToMany
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
	public Size getSize() {
		return size;
	}
	public void setSize(Size size) {
		this.size = size;
	}
	public void setSize(String size) {
		
		if(size.equalsIgnoreCase("s")) this.size = Size.s;
		if(size.equalsIgnoreCase("m")) this.size = Size.m;
		if(size.equalsIgnoreCase("l")) this.size = Size.l;
		if(size.equalsIgnoreCase("xl")) this.size = Size.xl;
		
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}

	
	public Shirt(long id, String name, Size size, String color, Customer customer, Collection<Supplier> suppliers) {
		super();
		this.id = id;
		this.name = name;
		this.size = size;
		this.color = color;
		this.customer = customer;
		this.suppliers = suppliers;
	}


	public Shirt() {
		super();
	}

	
}
