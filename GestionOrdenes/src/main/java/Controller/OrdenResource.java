package Controller;

import DTO.OrdenDetalleDTO;
import Entity.Orden;
import Service.IOrdenService;
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

    //GET /ordenes para que devuelva la lista de órdenes realizadas.

    @GET
    public Response getAll() {
        //crear response
        List<Orden> ordenes = ordenService.findAll();
        return Response.status(Response.Status.OK).entity(ordenes).build();
    }


    //GET /ordenes/id para que devuelva una orden en particular.
    @GET
    @Path("/{id}")
    public Response getById(@PathParam("id") Long id) {
        Orden orden = ordenService.findById(id);

        if (orden == null) {
            return Response.status(Response.Status.NOT_FOUND).
                    entity("orden no encontrada").
                    build();
        }
        return Response.status(Response.Status.OK).entity(orden).build();
    }

}