package Service;

import DTO.OrdenDetalleDTO;
import Entity.Orden;
import Entity.OrdenDetalle;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;


public interface IOrdenService {

    public void crearOrden(List<OrdenDetalleDTO> detalles);

    public List<Orden> findAll();

    // //GET /ordenes/id para que devuelva una orden en particular.

    public Orden findById(Long id);
}
