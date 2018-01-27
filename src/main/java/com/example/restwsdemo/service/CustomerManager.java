package com.example.restwsdemo.service;

import com.example.restwsdemo.domain.Customer;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class CustomerManager {
	
	@PersistenceContext
	EntityManager em;


	public void addCustomer(Customer shirt) {
		em.persist(shirt);
	}

	public void deleteCustomer(Customer shirt){
		em.remove(shirt);
	}
	
	public Customer getCustomer(Long id) {		
		Customer retrived = em.find(Customer.class, id);
		return retrived;		
	}
	
	@SuppressWarnings("unchecked")
	public List<Customer> getAll(){
		return em.createNamedQuery("customer.all").getResultList();
	}
	
	public void deleteAll(){
		em.createNamedQuery("customer.delete.all").executeUpdate();
	}
	
}
