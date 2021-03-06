package com.example.restwsdemo.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@NamedQueries({ 
	@NamedQuery(name = "supplier.all", query = "Select s from Supplier s"),
	@NamedQuery(name = "supplier.delete.all", query = "Delete from Supplier"),
	})
@Entity
public class Supplier {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String companyName;
	private String phoneNumber;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	public Supplier(long id, String companyName, String phoneNumber) {
		super();
		this.id = id;
		this.companyName = companyName;
		this.phoneNumber = phoneNumber;
	}
	
	public Supplier(String companyName, String phoneNumber) {
		super();
		this.companyName = companyName;
		this.phoneNumber = phoneNumber;
	}
	
	public Supplier() {
		super();
	}
	
	
}
