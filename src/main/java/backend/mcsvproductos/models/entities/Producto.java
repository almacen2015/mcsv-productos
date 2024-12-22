package backend.mcsvproductos.models.entities;

import jakarta.persistence.*;
import lombok.*;

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
    private String nombre;
    private String descripcion;
    private Double precio;
    private Boolean estado;

}
