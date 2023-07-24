package io.wferdinando.controller;

import java.util.List;

import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import io.wferdinando.dto.ProductDTO;
import io.wferdinando.service.ProductService;
import lombok.RequiredArgsConstructor;

@Path(value = "/api/products")
@RequiredArgsConstructor
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProductController {

    private final ProductService service;

    @GET
    public List<ProductDTO> findAllProducts() {
        return service.getAllProducts();
    }

    @GET
    @Path("/{id}")
    public ProductDTO findProductByid(@PathParam(value = "id") Long id) {
        return service.getProductById(id);
    }

    @POST
    @Transactional
    public Response saveProduct(ProductDTO productDTO) {
        try {
            ProductDTO product = new ProductDTO();
            product = service.createNewProduct(productDTO);
            return Response.ok(product).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.serverError().build();
        }
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public Response updateProduct(@PathParam(value = "id") Long id, ProductDTO productDTO) {
        try {
            ProductDTO product = new ProductDTO();
            product = service.updateProduct(id, productDTO);
            return Response.ok(product).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.serverError().build();
        }
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public Response deleteProduct(@PathParam(value = "id") Long id) {
        try {
            service.deleteProduct(id);
            return Response.ok().build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.serverError().build();
        }
    }
}
