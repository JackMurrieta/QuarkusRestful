package Repository;


import Entity.Producto;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.WebApplicationException;
import jdk.jfr.TransitionTo;

import java.util.List;

@ApplicationScoped
public class ProductoRepository implements PanacheRepository<Producto> {

    // METODOS POR DEFAULT CON PANACHE findAll(), findById(), persist(), delete()

    // METODOS PERSONALIZADOS
    public List<Producto> findByNombre(String nombre) {
        return find("nombre", nombre).list();
    }

    //Actualizar stock
    @Transactional
    public void actualizarProductoStock(Long idProducto, int cantidad) {
        Producto producto = findById(idProducto);
        if (producto == null) {
            throw new WebApplicationException("Producto no encontrado", 404);
        }
        if (producto.stock < cantidad) {
            throw new WebApplicationException("Stock insuficiente", 400);
        }
        producto.stock -= cantidad;

    }
//    public List<Producto> findByPrecioMenorA(double precio) {
//        return find("precio <= ?1", precio).list();
//    }
}