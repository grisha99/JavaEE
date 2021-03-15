package ru.grishchenko.rest;

import dto.ProductDto;

import javax.ejb.Local;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Local
@Path("/v1/products")
public interface ProductServiceRest {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    List<ProductDto> findAll();

    @GET
    @Path("/count")
    @Produces(MediaType.APPLICATION_JSON)
    Long getProductsCount();

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    ProductDto findById(@PathParam("id") Long id);

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    void insert(ProductDto productDto);

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    void update(ProductDto productDto);

    @DELETE
    @Path("/{id}")
    void deleteById(@PathParam("id") Long id);
}
