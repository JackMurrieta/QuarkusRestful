package Service;

import DTO.OrdenDetalleDTO;
import Entity.Orden;
import Entity.OrdenDetalle;

import java.util.List;

public interface IOrdenService {

    public void crearOrden(List<OrdenDetalleDTO> detalles);
}
