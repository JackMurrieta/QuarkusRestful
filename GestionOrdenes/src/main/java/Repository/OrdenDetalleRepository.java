package Repository;

import Entity.OrdenDetalle;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class OrdenDetalleRepository implements PanacheRepository<OrdenDetalle> {

    //crear orden detalle
    public void crearOrdenDetalle(OrdenDetalle ordenDetalle) {
        this.persist(ordenDetalle);
    }

    // Buscar detalles por orden
    public java.util.List<OrdenDetalle> findByOrden(Long idOrden) {
        return find("orden.id", idOrden).list();
    }
}
