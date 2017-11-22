package com.example.restwsdemo.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.ejb.Singleton;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.example.restwsdemo.domain.Shirt;

@Stateless
public class ShirtManager {
	
	@PersistenceContext
	EntityManager em;

	public ShirtManager() {
		addShirt(new Shirt());
	}

	public void addShirt(Shirt shirt) {
		em.persist(shirt);
	}

//	public void deleteShirt(Shirt shirt){
//		em.remove(shirt);
//	}
	
	public Shirt getShirt(Integer id) {
		
		Shirt sh = new Shirt();
		sh.setId(1);
		
		return em.find(Shirt.class, sh);
		
	}
//	
//	public List<Shirt> getAllShirts(){
//		return db;
//	}

}
