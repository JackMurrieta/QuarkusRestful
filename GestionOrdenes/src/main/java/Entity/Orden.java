package Entity;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;

import java.time.LocalDate;


@Entity
@Table(name = "ordenes")
public class Orden extends PanacheEntityBase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    public LocalDate fecha;

    public double total;

    @PrePersist
    public void prePersist() {
        this.fecha = LocalDate.now();
    }
}
