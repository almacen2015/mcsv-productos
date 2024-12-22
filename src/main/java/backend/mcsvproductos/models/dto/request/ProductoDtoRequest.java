package backend.mcsvproductos.models.dto.request;

public record ProductoDtoRequest(String nombre,
                                 String descripcion,
                                 String precio,
                                 String stock) {
}
