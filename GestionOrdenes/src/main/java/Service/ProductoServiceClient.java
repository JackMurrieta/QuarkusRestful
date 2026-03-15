package Service;

import DTO.ProductoDTO;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@RegisterRestClient(baseUri = "http://localhost:8081")
@Path("/productos")
public interface ProductoServiceClient {
    @GET
    @Path("/{id}")
        //ProductoDT o producto entity aunque no se guarde en la BD de ordenes
    ProductoDTO getById(@PathParam("id") Long id);
}
