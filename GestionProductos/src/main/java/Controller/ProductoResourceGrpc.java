package Controller;

import DTO.ProductoDTO;
import com.itson.grpc.*;
import io.quarkus.grpc.GrpcClient;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;
import java.util.stream.Collectors;

@Path("/productos")
@Produces(MediaType.APPLICATION_JSON)
public class ProductoResourceGrpc {

    @GrpcClient("producto-service")
    MutinyProductoServiceGrpc.MutinyProductoServiceStub productoService;

    @GET
    @Path("/{id}")
    public Response getById(@PathParam("id") Long id) {
        ProductoResponse response = productoService
                .verificarYObtener(ProductoRequest.newBuilder().setId(id).build())
                .await().indefinitely();

        return Response.ok(toDTO(response)).build();
    }

    @GET
    public Response getAll() {
        ListaProductosResponse response = productoService.
                obtenerTodos(Empty.newBuilder().build())
                .await().indefinitely();

        List<ProductoDTO> productos = response.getProductosList()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
        return Response.ok(productos).build();

    }

    @PUT
    @Path("{id}/stock")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response actualizarStock(@PathParam("id") Long id, ProductoDTO productoDTO) {
        productoService.actualizarProductoStock(
                ProductoUpdateRequest.newBuilder()
                        .setIdProducto(id)
                        .setCantidad(productoDTO.stock)
                        .build()
        ).await().indefinitely();
        return Response.ok().build();
    }

    // Mapper protobuf → DTO
    private ProductoDTO toDTO(ProductoResponse response) {
        ProductoDTO dto = new ProductoDTO();
        dto.id = response.getId();
        dto.nombre = response.getNombre();
        dto.precio = response.getPrecio();
        dto.stock = response.getStockDisponible();
        return dto;
    }
}


