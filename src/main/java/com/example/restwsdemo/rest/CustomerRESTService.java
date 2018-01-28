package com.example.restwsdemo.rest;

import java.util.Collection;
import java.util.List;

import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.example.restwsdemo.domain.Customer;
import com.example.restwsdemo.domain.Shirt;
import com.example.restwsdemo.service.CustomerManager;

@Path("customer")
public class CustomerRESTService {
	
	@Inject
	private CustomerManager manager;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Customer> getAll(){
		return manager.getAll();
	}
	
	@GET
	@Path("/{customerId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Customer getCustomer(@PathParam("customerId") Long id) {
		Customer s = manager.getCustomer(id);
		return s;
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addCustomer(Customer customer) {
		manager.addCustomer(customer);

		return Response.status(201).entity("Customer").build();
	}
	
	@DELETE
	public Response deleteAll(){
		manager.deleteAll();
		return Response.status(Response.Status.OK).build();
	}
	
	@GET
	@Path("/shirts/{customerId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getShirts(@PathParam("customerId") Long id){
		
		Customer c = manager.getCustomer(id);
		
		Collection<Shirt> rawList = c.getShirts();
		JsonArrayBuilder builder = Json.createArrayBuilder();
		
		for(Shirt rawObject : rawList){
			
			Long shirtId = rawObject.getId();
			String name = rawObject.getName();
			String color = rawObject.getColor();
			String size = rawObject.getSize();
			
			builder.add(Json.createObjectBuilder()
					.add("id", shirtId)
					.add("name", name)
					.add("color", color)
					.add("size", size)
					);
			
		}
		
		JsonArray arr = builder.build();
		
		return Response.ok(arr, MediaType.APPLICATION_JSON).build();
			
	}

	@GET
	@Path("/test")
	@Produces(MediaType.TEXT_PLAIN)
	public String test() {
		return "Customer API is working!";
	}


}
