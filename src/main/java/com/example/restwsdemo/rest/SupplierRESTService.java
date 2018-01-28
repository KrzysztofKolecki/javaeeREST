package com.example.restwsdemo.rest;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.example.restwsdemo.domain.Supplier;
import com.example.restwsdemo.service.SupplierManager;

@Path("supplier")
public class SupplierRESTService {
	
	@Inject
	private SupplierManager manager;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Supplier> getAll(){
		return manager.getAll();
	}
	
	@GET
	@Path("/{supplierId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Supplier getSupplier(@PathParam("supplierId") Long id) {
		Supplier s = manager.getSupplier(id);
		return s;
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addSupplier(Supplier supplier) {
		manager.addSupplier(supplier);

		return Response.status(201).entity("Supplier").build();
	}
	
	@DELETE
	public Response deleteAll(){
		manager.deleteAll();
		return Response.status(Response.Status.OK).build();
	}

	@GET
	@Path("/test")
	@Produces(MediaType.TEXT_PLAIN)
	public String test() {
		return "Supplier API is working!";
	}


}
