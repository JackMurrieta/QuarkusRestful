package Entity;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "orden_detalle")
public class OrdenDetalle extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    public int cantidad;

    public double precio;

    @Column(name = "id_producto", nullable = false)
    public Long idProducto;

    @ManyToOne
    @JoinColumn(name = "id_orden", nullable = false)
    public Orden orden;


}
