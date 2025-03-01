package backend.mcsvproductos.services;

import backend.mcsvproductos.models.dto.request.ProductoDtoRequest;
import backend.mcsvproductos.models.dto.response.ProductoDtoResponse;

import java.util.List;

public interface ProductoService {

    ProductoDtoResponse agregarProducto(ProductoDtoRequest dto);

    ProductoDtoResponse actualizarProducto(ProductoDtoRequest dto, Integer id);

    void verificarDatos(ProductoDtoRequest dto);

    List<ProductoDtoResponse> listar();

    ProductoDtoResponse buscarPorId(Integer id);

    ProductoDtoResponse buscarPorNombre(String nombre);

    void actualizarStock(Integer idProducto, Integer cantidad, String tipoMovimiento);
}
