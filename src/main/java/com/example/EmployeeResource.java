package com.example;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("employees")
public class EmployeeResource {

    @EJB
    EmployeeDb db;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Employee> getAll(@DefaultValue("1") @QueryParam("page") int pageNumber) {
        return db.getAll(pageNumber);
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Employee getEmployee(@PathParam("id") int id) {
        return db.find(id);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createEmployee(Employee employee) {
        db.create(employee);
        return Response.status(Response.Status.CREATED).entity(employee).build();
    }

    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateEmployee(Employee employee) {
        db.update(employee);
        return Response.status(Response.Status.OK).entity(employee).build();
    }

    @DELETE
    @Path("{id}")
    public Response deletePerson(@PathParam("id") int id) {
        db.delete(db.find(id));
        return Response.noContent().status(Response.Status.NO_CONTENT).build();
    }
}