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

        //Crear la orden vacía
        Orden orden = new Orden();
        orden.total = 0.0;

        //Persistir la orden primero para obtener su ID
        ordenRepository.crearOrden(orden);

        // Procesar cada detalle
        double totalOrden = 0.0;

        for (OrdenDetalleDTO detalleDTO : detalles) {

            // Consultar producto al microservicio de catálogo
            ProductoDTO producto = productoServiceClient.getById(detalleDTO.idProducto);

            // Convertir DTO a Entity
            OrdenDetalle detalle = toEntity(detalleDTO, producto, orden);

            // Acumular total
            totalOrden += detalle.precio;

            // Persistir detalle
            ordenDetalleRepository.crearOrdenDetalle(detalle);
        }

        // 8. Actualizar total de la orden
        orden.total = totalOrden;
    }

    // Convierte DTO Entity usando el producto del otro microservicio
    private OrdenDetalle toEntity(OrdenDetalleDTO detalleDTO, ProductoDTO producto, Orden orden) {
        OrdenDetalle detalle = new OrdenDetalle();
        detalle.idProducto = detalleDTO.idProducto;
        detalle.cantidad = detalleDTO.cantidad;
        detalle.precio = producto.precio * detalleDTO.cantidad;
        detalle.orden = orden;
        return detalle;
    }
}
