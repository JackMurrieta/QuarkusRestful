package Entity;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;

@Entity
@Table(name = "productos")
public class Producto extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @Column(name = "nombre", nullable = false)
    public String nombre;

    @Column(name = "precio")
    public double precio;

    @Column(name = "stock")
    public int stock;
}