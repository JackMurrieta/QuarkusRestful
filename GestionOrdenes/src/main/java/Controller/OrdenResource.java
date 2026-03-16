package Controller;

import DTO.OrdenDetalleDTO;
import Service.IOrdenService;
import Service.OrdenService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/ordenes")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class OrdenResource {

    @Inject
    IOrdenService ordenService;

    // POST /ordenes
    // Recibe lista de detalles y crea la orden completa
    @POST
    public Response crearOrden(List<OrdenDetalleDTO> detalles) {
        if (detalles == null || detalles.isEmpty()) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("La orden debe tener al menos un producto")
                    .build();
        }
        ordenService.crearOrden(detalles);
        return Response.status(Response.Status.CREATED)
                .entity("Orden creada exitosamente")
                .build();
    }


}