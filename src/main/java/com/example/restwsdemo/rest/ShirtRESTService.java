package com.example.restwsdemo.rest;

import java.util.List;

import javax.ejb.EJB;
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
public class ShirtRESTService {
	
	@EJB
	private ShirtManager manager;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Shirt> getAll(){
		return manager.getAll();
	}
	
	@GET
	@Path("/{shirtId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Shirt getShirt(@PathParam("shirtId") Long id) {
		Shirt s = manager.getShirt(id);
		return s;
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addShirt(Shirt shirt) {
		manager.addShirt(shirt);

		return Response.status(201).entity("Shirt").build();
	}
	
	@DELETE
	public Response deleteAll(){
		manager.deleteAll();
		return Response.status(Response.Status.OK).build();
	}
	
	@GET
	@Path("/query/size/{size}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Shirt> getShirt(@PathParam("size") String size){
		
		return manager.findBySize(size);
	}

	@GET
	@Path("/test")
	@Produces(MediaType.TEXT_PLAIN)
	public String test() {
		return "REST API /shirt is running today!";
	}


}
