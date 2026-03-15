package Repository;


import Entity.Producto;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;

@ApplicationScoped
public class ProductoRepository implements PanacheRepository<Producto> {

    // METODOS POR DEFAULT CON PANACHE findAll(), findById(), persist(), delete()

    // METODOS PERSONALIZADOS
    public List<Producto> findByNombre(String nombre) {
        return find("nombre", nombre).list();
    }

//    public List<Producto> findByPrecioMenorA(double precio) {
//        return find("precio <= ?1", precio).list();
//    }
}