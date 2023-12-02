package com.prestixpress.customers.framework.adapters.input.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.prestixpress.customers.application.usecases.CustomerManagementUseCase;
import com.prestixpress.customers.domain.entity.Customer;
import com.prestixpress.customers.domain.entity.factory.CustomerFactory;
import com.prestixpress.customers.framework.adapters.input.rest.customer.CreateCustomer;
import com.prestixpress.customers.framework.adapters.input.rest.deserializer.CustomerDeserializer;
import io.smallrye.mutiny.Uni;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.logging.Logger;

@ApplicationScoped
@Path("/customer")
@Tag(name= "Customer Operations",description = "Customer management operation")
public class CustomerManagementAdapter {

    @Inject
    CustomerManagementUseCase customerManagementUseCase;

    private static final Logger LOG = Logger.getLogger(CustomerManagementAdapter.class.getName());

    @Transactional
    @POST
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Operation(operationId = "createCustomer",description = "Create and persist a new customer")
    public Uni<Response> createCustomer(String createCustomer){
      try{
           ObjectMapper mapper = new ObjectMapper();
           SimpleModule module = new SimpleModule();
           module.addDeserializer(Customer.class, new CustomerDeserializer());
           mapper.registerModule(module);

           Customer customer = mapper.readValue(createCustomer, Customer.class);

           return Uni.createFrom()
                .item(customerManagementUseCase.persistCustomer(customer))
                .onItem()
                .transform( f -> f != null ? Response.ok(f) : Response.ok("Error al procesar los dattos"))
                .onItem()
                .transform(Response.ResponseBuilder::build);
       }
       catch (Exception e){
           LOG.info("Error: "+e.getMessage());
           return null;
       }

    }

    @Transactional
    @POST
    @Path("/update/")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Operation(operationId = "updateCustomer",description = "Update and persist a customer")
   public Uni<Response> updateCustomer(String createCustomer){

        try{
            ObjectMapper mapper = new ObjectMapper();
            SimpleModule module = new SimpleModule();
            module.addDeserializer(Customer.class, new CustomerDeserializer());
            mapper.registerModule(module);

            Customer customer = mapper.readValue(createCustomer, Customer.class);

            return Uni.createFrom()
                    .item(customerManagementUseCase.updateCustomer(customer))
                    .onItem()
                    .transform( f -> f != null ? Response.ok(f) : Response.ok(null))
                    .onItem()
                    .transform(Response.ResponseBuilder::build);
        }
        catch (Exception e){
            LOG.info("Error: "+e.getMessage());
            return null;
        }
   }

    @Transactional
    @GET
    @Path("/getcustomers/{filtro}/{valor}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.TEXT_PLAIN)
    @Operation(operationId = "getCustomers",description = "get customers by filter")
    public Uni<Response> getCustomers(@PathParam("filtro") String filter, @PathParam("valor") String value){
        return Uni.createFrom()
                .item(customerManagementUseCase.retrieveCustomers(filter,value))
                .onItem()
                .transform( f -> f != null ? Response.ok(f) : Response.ok(null))
                .onItem()
                .transform(Response.ResponseBuilder::build);
    }

    @Transactional
    @GET
    @Path("/getreference/{uuid}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.TEXT_PLAIN)
    @Operation(operationId = "getReference",description = "get reference by uuid")
    public Uni<Response> getReference(@PathParam("uuid") String uuid){
        return Uni.createFrom()
                .item(customerManagementUseCase.retrieveReferences(uuid))
                .onItem()
                .transform( f -> f != null ? Response.ok(f) : Response.ok(null))
                .onItem()
                .transform(Response.ResponseBuilder::build);
    }

    @Transactional
    @GET
    @Path("/getpicture/{uuid}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.TEXT_PLAIN)
    @Operation(operationId = "getPicture",description = "get picture by uuid")
    public Uni<Response> getPicture(@PathParam("uuid") String uuid){
        return Uni.createFrom()
                .item(customerManagementUseCase.retrievePhotos(uuid))
                .onItem()
                .transform( f -> f != null ? Response.ok(f) : Response.ok(null))
                .onItem()
                .transform(Response.ResponseBuilder::build);
    }

}
