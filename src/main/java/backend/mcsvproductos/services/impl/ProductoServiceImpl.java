package backend.mcsvproductos.services.impl;

import backend.mcsvproductos.enums.Estado;
import backend.mcsvproductos.enums.TipoMovimiento;
import backend.mcsvproductos.exceptions.ProductoException;
import backend.mcsvproductos.mappers.ProductoMapper;
import backend.mcsvproductos.models.dto.request.ProductoDtoRequest;
import backend.mcsvproductos.models.dto.response.ProductoDtoResponse;
import backend.mcsvproductos.models.entities.Producto;
import backend.mcsvproductos.repositories.ProductoRepository;
import backend.mcsvproductos.services.ProductoService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ProductoServiceImpl implements ProductoService {
    private final ProductoRepository productoRepository;
    private final ProductoMapper productoMapper = ProductoMapper.INSTANCE;

    public ProductoServiceImpl(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public ProductoDtoResponse update(ProductoDtoRequest dto, Integer id) {
        validateData(dto);
        validateId(id);

        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new ProductoException(ProductoException.PRODUCT_NOT_FOUND));

        producto.setNombre(dto.nombre());
        producto.setDescripcion(dto.descripcion());
        producto.setPrecio(dto.precio());

        Producto productoGuardado = productoRepository.save(producto);
        return productoMapper.toDto(productoGuardado);
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public void updateStock(Integer idProducto, Integer cantidad, String tipoMovimiento) {
        validateAmount(cantidad);
        Producto productoEncontrado = productoRepository.findById(idProducto)
                .orElseThrow(() -> new ProductoException(ProductoException.PRODUCT_NOT_FOUND));

        final int stock = productoEncontrado.getStock();
        int stockFinal;
        if (Objects.equals(tipoMovimiento, TipoMovimiento.SALIDA.name())) {
            stockFinal = stock - cantidad;
        } else {
            stockFinal = stock + cantidad;
        }

        validateStock(stockFinal);
        productoEncontrado.setId(productoEncontrado.getId());
        productoEncontrado.setStock(stockFinal);
        productoRepository.save(productoEncontrado);
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public ProductoDtoResponse add(ProductoDtoRequest dto) {
        validateData(dto);
        Producto producto = productoMapper.toEntity(dto);
        producto.setEstado(Estado.ACTIVO.getValor());
        producto.setFechaCreacion(LocalDate.now());
        producto.setStock(0);
        Producto productoGuardado = productoRepository.save(producto);
        return productoMapper.toDto(productoGuardado);
    }

    private void validateStock(Integer stock) {
        if (stock == null || stock < 0) {
            throw new ProductoException(ProductoException.INVALID_STOCK);
        }
    }

    private void validateAmount(Integer cantidad) {
        if (cantidad == null || cantidad <= 0) {
            throw new ProductoException(ProductoException.AMOUNT_INVALID);
        }
    }

    private void validateId(Integer id) {
        if (id == null || id <= 0) {
            throw new ProductoException(ProductoException.INVALID_ID);
        }
    }

    private void validateData(ProductoDtoRequest dto) {
        final String nombre = dto.nombre();
        final String descripcion = dto.descripcion();
        final Double precio = dto.precio();

        if (nombre == null || nombre.isBlank()) {
            throw new ProductoException(ProductoException.PRODUCT_NAME_EMPTY);
        }

        if (descripcion == null || descripcion.isBlank()) {
            throw new ProductoException(ProductoException.PRODUCT_DESCRIPTION_EMPTY);
        }

        if (precio == null || precio <= 0) {
            throw new ProductoException(ProductoException.PRODUCT_PRICE_INVALID);
        }
    }

    @Override
    public List<ProductoDtoResponse> listAll() {
        List<Producto> productos = productoRepository.findAll();
        return productoMapper.toDtoList(productos);
    }

    @Override
    public ProductoDtoResponse getById(Integer id) {
        if (id == null || id <= 0) {
            throw new ProductoException(ProductoException.INVALID_ID);
        }

        Optional<Producto> producto = productoRepository.findById(id);
        if (producto.isEmpty()) {
            return null;
        }

        ProductoDtoResponse response = productoMapper.toDto(producto.get());

        return response;
    }

    @Override
    public ProductoDtoResponse getByName(String nombre) {
        if (nombre == null || nombre.isBlank()) {
            throw new ProductoException(ProductoException.PRODUCT_NAME_EMPTY);
        }

        Optional<Producto> producto = productoRepository.findByNombre(nombre);
        if (producto.isEmpty()) {
            return null;
        }

        ProductoDtoResponse response = productoMapper.toDto(producto.get());

        return response;
    }
}
