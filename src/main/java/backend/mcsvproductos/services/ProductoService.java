package backend.mcsvproductos.services;

import backend.mcsvproductos.models.dto.request.ProductoDtoRequest;
import backend.mcsvproductos.models.dto.response.ProductoDtoResponse;

public interface ProductoService {

    ProductoDtoResponse agregarProducto(ProductoDtoRequest productoDtoResponse);
}
