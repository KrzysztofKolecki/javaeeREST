package com.example.restwsdemo.service;

import com.example.restwsdemo.domain.Shirt;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class ShirtManager {
	
	@PersistenceContext
	EntityManager em;


	public void addShirt(Shirt shirt) {
		em.persist(shirt);
	}

	public void deleteShirt(Shirt shirt){
		em.remove(shirt);
	}
	
	public Shirt getShirt(Long id) {		
		Shirt retrived = em.find(Shirt.class, id);
		return retrived;		
	}
	
	@SuppressWarnings("unchecked")
	public List<Shirt> getAll(){
		return em.createNamedQuery("shirt.all").getResultList();
	}
	
	public void deleteAll(){
		em.createNamedQuery("shirt.delete.all").executeUpdate();
	}
	
	@SuppressWarnings("unchecked")
	public List<Shirt> findBySize(String size){
		return em.createNamedQuery("shirt.findBySize").setParameter("size", size).getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getShirtsOfCustomerSurname(String surname){		
		return em.createNamedQuery("shirt.findByCustomer").setParameter("surname", surname).getResultList();
	}
}
