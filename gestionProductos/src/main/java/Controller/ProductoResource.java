package Controller;

import DTO.ProductoDTO;
import Service.ProductoService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/productos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProductoResource {

    @Inject
    ProductoService productoService;

    // GET /productos
    @GET
    public Response getAll() {
        List<ProductoDTO> lista = productoService.findAll();
        return Response.ok(lista).build();
    }

    // GET /productos/{id}
    @GET
    @Path("/{id}")
    public Response getById(@PathParam("id") Long id) {
        ProductoDTO dto = productoService.findById(id);
        if (dto == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Producto no encontrado")
                    .build();
        }
        return Response.ok(dto).build();
    }


}