package backend.mcsvproductos.services.impl;

import backend.mcsvproductos.exceptions.ProductoException;
import backend.mcsvproductos.mappers.ProductoMapper;
import backend.mcsvproductos.models.dto.request.ProductoDtoRequest;
import backend.mcsvproductos.models.dto.response.ProductoDtoResponse;
import backend.mcsvproductos.models.entities.Producto;
import backend.mcsvproductos.repositories.ProductoRepository;
import backend.mcsvproductos.services.ProductoService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class ProductoServiceImpl implements ProductoService {
    private final ProductoRepository productoRepository;
    private final ProductoMapper productoMapper = ProductoMapper.INSTANCE;

    public ProductoServiceImpl(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public ProductoDtoResponse agregarProducto(ProductoDtoRequest dto) {
        verificarDatos(dto);
        Producto producto = productoMapper.toEntity(dto);
        Producto productoGuardado = productoRepository.save(producto);
        return productoMapper.toDto(productoGuardado);
    }

    @Override
    public void verificarDatos(ProductoDtoRequest dto) {
        final String nombre = dto.nombre();
        final String descripcion = dto.descripcion();
        final Double precio = dto.precio();

        if (nombre == null || nombre.isBlank()) {
            throw new ProductoException(ProductoException.PRODUCTO_NOMBRE_VACIO);
        }

        if (descripcion == null || descripcion.isBlank()) {
            throw new ProductoException(ProductoException.PRODUCTO_DESCRIPCION_VACIA);
        }

        if (precio == null || precio <= 0) {
            throw new ProductoException(ProductoException.PRODUCTO_PRECIO_INVALIDO);
        }
    }
}
