package Repository;

import Entity.Orden;
import Entity.OrdenDetalle;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class OrdenRepository implements PanacheRepository {

    // METODOS POR DEFAULT CON PANACHE findAll(), findById(), persist(), delete()

    //crear orden
    public void crearOrden(Orden orden) {
        this.persist(orden);
    }


}
