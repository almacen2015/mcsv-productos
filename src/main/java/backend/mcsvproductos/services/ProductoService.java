package backend.mcsvproductos.services;

import backend.mcsvproductos.models.dto.request.ProductoDtoRequest;
import backend.mcsvproductos.models.dto.response.ProductoDtoResponse;

import java.util.List;

public interface ProductoService {

    ProductoDtoResponse agregarProducto(ProductoDtoRequest dto);

    void verificarDatos(ProductoDtoRequest dto);

    List<ProductoDtoResponse> listar();
}
