package com.example.restwsdemo.rest;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.example.restwsdemo.domain.Shirt;
import com.example.restwsdemo.service.ShirtManager;

@Path("shirt")
@Stateless
public class ShirtRESTService {

	@PersistenceContext
	EntityManager em;

	@GET
	@Path("/{shirtId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Shirt getShirt(@PathParam("shirtId") Long id) {
		
		Shirt s = em.find(Shirt.class, id);
		return s;
	}
//
//	@GET
//	@Produces(MediaType.APPLICATION_JSON)
//	public List<Shirt> getShirts() {
//		return sm.getAllShirts();
//	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addShirt(Shirt shirt) {
		em.persist(shirt);

		return Response.status(201).entity("Shirt").build();
	}

	@GET
	@Path("/test")
	@Produces(MediaType.TEXT_PLAIN)
	public String test() {
		return "REST API /shirt is running today!";
	}

	@DELETE
	public Response clearShirts() {
		return Response.status(200).build();
	}

}
