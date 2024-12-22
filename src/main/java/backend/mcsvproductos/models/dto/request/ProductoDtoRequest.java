package backend.mcsvproductos.models.dto.request;

public record ProductoDtoRequest(String nombre,
                                 String descripcion,
                                 Double precio) {
}
