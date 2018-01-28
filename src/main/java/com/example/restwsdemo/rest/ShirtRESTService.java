package com.example.restwsdemo.rest;

import java.util.List;

import javax.ejb.EJB;
import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
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
import com.example.restwsdemo.domain.Supplier;
import com.example.restwsdemo.service.CustomerManager;
import com.example.restwsdemo.service.ShirtManager;
import com.example.restwsdemo.service.SupplierManager;

@Path("shirt")
public class ShirtRESTService {
	
	@EJB
	private ShirtManager manager;
	
	@EJB
	private SupplierManager supplierManager;
	
	@EJB
	private CustomerManager customerManager;
	
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
	@Path("/size/{size}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Shirt> getShirt(@PathParam("size") String size){
		
		return manager.findBySize(size);
	}
	
	@GET
	@Path("/relations")
	@Produces(MediaType.TEXT_PLAIN)
	public String makeRelations(){
		
		Shirt shirt = manager.getAll().get(0);
		
		List<Supplier> suppliers = supplierManager.getAll();
		Customer customer = customerManager.getAll().get(0);
		
		shirt.setSuppliers(suppliers);
		shirt.setCustomer(customer);
		
		manager.updateShirt(shirt);
			

		return "Relations added";
	}
	
	@GET
	@Path("/lazy")
	@Produces(MediaType.TEXT_PLAIN)
	public String lazy(){
		
		Shirt shirt = manager.getAll().get(0);
		
		List<Supplier> suppliers = (List<Supplier>) shirt.getSuppliers();		

		return "Supplier: " + suppliers.get(0).getCompanyName();
	}
	
	@GET
	@Path("/customer/{surname}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getShirtsByCustomer(@PathParam("surname") String surname){
		
		List<Object[]> rawList = manager.getShirtsOfCustomerSurname(surname);
		JsonArrayBuilder builder = Json.createArrayBuilder();
		
		for(Object[] rawObject: rawList){
			
			String customerName = (String) rawObject[0];
			String customerSurname = (String) rawObject[1];
			String shirtName = (String) rawObject[2];
			String shirtSize = (String) rawObject[3];
			String shirtColor = (String) rawObject[4];
			
			builder.add(Json.createObjectBuilder()
					.add("customerName", customerName)
					.add("customerSurname", customerSurname)
					.add("shirtName", shirtName)
					.add("shirtSize", shirtSize)
					.add("shirtColor", shirtColor));
			
		}
		
		JsonObject json =  Json.createObjectBuilder().add("result", builder).build();
		
		return Response.ok(json, MediaType.APPLICATION_JSON).build();
}
	

	@GET
	@Path("/test")
	@Produces(MediaType.TEXT_PLAIN)
	public String test() {
		return "REST API /shirt is running today!";
	}


}
