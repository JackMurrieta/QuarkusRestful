package Repository;

import Entity.Orden;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;

@ApplicationScoped
public class OrdenRepository implements PanacheRepository<Orden> {

    // METODOS POR DEFAULT CON PANACHE findAll(), findById(), persist(), delete()

    //crear orden
    public void crearOrden(Orden orden) {
        this.persist(orden);
    }

    // Obtener todas las órdenes
    public List<Orden> obtenerOrdenes() {
        return this.findAll().list();
    }

    // Buscar orden por ID
    public Orden ordenPorId(Long id) {
        return this.findById(id);
    }


}
