package backend.mcsvproductos.services;

import backend.mcsvproductos.models.dto.request.ProductoDtoRequest;
import backend.mcsvproductos.models.dto.response.ProductoDtoResponse;

import java.util.List;

public interface ProductoService {

    ProductoDtoResponse add(ProductoDtoRequest dto);

    ProductoDtoResponse update(ProductoDtoRequest dto, Integer id);

    List<ProductoDtoResponse> listAll();

    ProductoDtoResponse getById(Integer id);

    ProductoDtoResponse getByName(String nombre);

    void updateStock(Integer idProducto, Integer cantidad, String tipoMovimiento);
}
