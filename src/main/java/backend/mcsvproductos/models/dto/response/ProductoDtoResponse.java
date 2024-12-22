package backend.mcsvproductos.models.dto.response;

import java.time.LocalDate;

public record ProductoDtoResponse(Integer id,
                                  String nombre,
                                  String descripcion,
                                  String precio,
                                  String stock,
                                  Boolean estado,
                                  LocalDate fechaCreacion) {
}
