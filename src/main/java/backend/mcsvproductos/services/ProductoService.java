package backend.mcsvproductos.services;

import backend.mcsvproductos.models.dto.request.ProductoDtoRequest;
import backend.mcsvproductos.models.dto.response.ProductoDtoResponse;
import backend.mcsvproductos.util.Paginado;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProductoService {

    ProductoDtoResponse add(ProductoDtoRequest dto);

    ProductoDtoResponse update(ProductoDtoRequest dto, Integer id);

    Page<ProductoDtoResponse> listAll(Integer page, Integer size, String orderBy);

    ProductoDtoResponse getById(Integer id);

    Page<ProductoDtoResponse> listByname(String nombre, Paginado paginado);

    void updateStock(Integer idProducto, Integer cantidad, String tipoMovimiento);
}
