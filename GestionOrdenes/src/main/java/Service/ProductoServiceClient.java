package Service;

import DTO.OrdenDetalleDTO;
import DTO.ProductoDTO;
import jakarta.ws.rs.*;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@RegisterRestClient(baseUri = "http://localhost:8081")
@Path("/productos")
public interface ProductoServiceClient {
    @GET
    @Path("/{id}")
        //ProductoDT o producto entity aunque no se guarde en la BD de ordenes
    ProductoDTO getById(@PathParam("id") Long id);

    //actualizar Stock prducto del cual se obtuvo en el id
    @PATCH
    @Path("/{id}/stock")
    void actualizarStock(@PathParam("id") Long id, @QueryParam("cantidad") int cantidad);
}
