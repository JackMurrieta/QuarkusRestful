package Service;

import DTO.OrdenDetalleDTO;
import DTO.ProductoDTO;
import Entity.Orden;
import Entity.OrdenDetalle;
import Repository.OrdenDetalleRepository;
import Repository.OrdenRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import java.util.List;

@ApplicationScoped
public class OrdenService implements IOrdenService {
    @Inject
    OrdenRepository ordenRepository;

    @Inject
    OrdenDetalleRepository ordenDetalleRepository;

    @Inject
    @RestClient
    ProductoServiceClient productoServiceClient;

    @Override
    @Transactional
    public void crearOrden(List<OrdenDetalleDTO> detalles) {

        Orden orden = new Orden();
        orden.total = 0.0;
        ordenRepository.crearOrden(orden);

        double totalOrden = 0.0;

        for (OrdenDetalleDTO detalleDTO : detalles) {

            // Validar que el producto existe en el microservicio de catálogo
            ProductoDTO producto;
            try {
                producto = productoServiceClient.getById(detalleDTO.idProducto);
            } catch (WebApplicationException e) {
                throw new WebApplicationException(
                        "Producto con id " + detalleDTO.idProducto + " no encontrado en catálogo",
                        Response.Status.NOT_FOUND
                );
            }

            OrdenDetalle detalle = toEntity(detalleDTO, producto, orden);
            totalOrden += detalle.precio;
            ordenDetalleRepository.crearOrdenDetalle(detalle);

            // Descontar stock
            try {
                productoServiceClient.actualizarStock(producto.id, detalle.cantidad);
            } catch (WebApplicationException e) {
                // Captura el mensaje exacto que viene del microservicio de productos
                String mensajeOrigen = e.getResponse().readEntity(String.class);
                throw new WebApplicationException(
                        "Error al actualizar stock: " + mensajeOrigen,
                        e.getResponse().getStatus()
                );
            }
        }
        orden.total = totalOrden;

    }


    @Override
    public List<Orden> findAll() {
        return ordenRepository.obtenerOrdenes();
    }

    @Override
    public Orden findById(Long id) {
        return ordenRepository.ordenPorId(id);
    }


    // Convierte DTO Entity usando el producto del otro microservicio
    private static OrdenDetalle toEntity(OrdenDetalleDTO detalleDTO, ProductoDTO producto, Orden orden) {
        OrdenDetalle detalle = new OrdenDetalle();
        detalle.idProducto = detalleDTO.idProducto;
        detalle.cantidad = detalleDTO.cantidad;
        detalle.precio = producto.precio * detalleDTO.cantidad;
        detalle.orden = orden;
        return detalle;
    }
}

