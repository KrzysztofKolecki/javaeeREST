package com.example.restwsdemo.service;

import com.example.restwsdemo.domain.Supplier;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class SupplierManager {
	
	@PersistenceContext
	EntityManager em;


	public void addSupplier(Supplier supplier) {
		em.persist(supplier);
	}

	public void deleteSupplier(Supplier supplier){
		em.remove(supplier);
	}
	
	public Supplier getSupplier(Long id) {		
		Supplier retrived = em.find(Supplier.class, id);
		return retrived;		
	}
	
	@SuppressWarnings("unchecked")
	public List<Supplier> getAll(){
		return em.createNamedQuery("supplier.all").getResultList();
	}
	
	public void deleteAll(){
		em.createNamedQuery("supplier.delete.all").executeUpdate();
	}
	
}
