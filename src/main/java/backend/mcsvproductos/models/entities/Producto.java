package backend.mcsvproductos.models.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Table(name = "productos")
@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "nombre", nullable = false, unique = true)
    private String nombre;
    @Column(name = "descripcion", nullable = false, unique = true)
    private String descripcion;
    private Double precio;
    private Boolean estado;
    private LocalDate fechaCreacion;
}
