package ru.grishchenko.rest;

import dto.CategoryDto;
import dto.ProductDto;
import jdk.nashorn.internal.objects.annotations.Getter;

import javax.ejb.Local;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Local
@Path("/v1/categories")
public interface CategoryServiceRest {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    List<CategoryDto> findAll();

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    CategoryDto findById(@PathParam("id") Long id);

    @GET
    @Path("/count")
    Long getCategoryCount();

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    void insert(CategoryDto categoryDto);

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    void update(CategoryDto categoryDto);

    @DELETE
    @Path("/{id}")
    void deleteById(@PathParam("id") Long id);
}
